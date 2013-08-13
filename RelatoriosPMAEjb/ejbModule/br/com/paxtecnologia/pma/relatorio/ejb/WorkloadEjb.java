package br.com.paxtecnologia.pma.relatorio.ejb;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ejb.Stateless;

import br.com.paxtecnologia.pma.relatorio.dao.WorkloadDAO;
import br.com.paxtecnologia.pma.relatorio.util.FormataDataUtil;
import br.com.paxtecnologia.pma.relatorio.vo.GraficoMetricaVO;
import br.com.paxtecnologia.pma.relatorio.vo.TimeFrameVO;

@Stateless
public class WorkloadEjb {

	private WorkloadDAO workloadDao = new WorkloadDAO();
	
	public String getLabel(Integer idCliente, Integer idGraficoControle, Integer idTf) {
		return workloadDao.getLabel(idCliente, idGraficoControle, idTf);
	}

	public String getLabelTitulo(Integer idCliente, Integer idGraficoControle) {
		return workloadDao.getLabelTitulo(idCliente, idGraficoControle);
	}
	
	public int getDiasNoMes(String mesRelatorio) {
		return FormataDataUtil.diasNoMes(mesRelatorio);
	}

	public String getTf(Integer idCliente, String mesRelatorio, Integer idGraficoControle, Integer idTf) {
		String tf = null;
		GraficoMetricaVO graficoMetrica = workloadDao.getMetrica(idCliente, idGraficoControle, idTf);
		Integer tipoHorario = graficoMetrica.getTipoHorario();
		if (tipoHorario != null) {
			switch (tipoHorario) {
			 case 1:
				tf = getTfCalculo8as18(graficoMetrica.getMetrica(), mesRelatorio, idGraficoControle);
				break;
			 case 2:
				tf = getTfCalculo24horas(graficoMetrica.getMetrica(), mesRelatorio, idGraficoControle);
			 	break;
			 case 3:
				tf = getTfCalculo0a8e23a24(graficoMetrica.getMetrica(), mesRelatorio, idGraficoControle);
			 	break;
			 case 4:
				tf = getTfCalculo18a23(graficoMetrica.getMetrica(), mesRelatorio, idGraficoControle);
			 	break;
			 case 5:
				tf = getTfCalculo0a8e18a24(graficoMetrica.getMetrica(), mesRelatorio, idGraficoControle);
			 	break;
			 default:
				break;
			}
		}	
		return tf;
	}

	private String getTfCalculo18a23(Integer metrica, String mesRelatorio, Integer idGraficoControle) {
		List<TimeFrameVO> timeFrameList = null;
		List<String> periodo = new ArrayList<String>();
		periodo.add("18");
		periodo.add("19");
		periodo.add("20");
		periodo.add("21");
		periodo.add("22");
		if (idGraficoControle%2!=0) { //impar = mensal
			 timeFrameList = workloadDao.getTimeFrame(metrica, mesRelatorio, periodo);
			return formataTimeFram(timeFrameList);
		} else{ //par = anual
			timeFrameList = workloadDao.getTimeFrameAno(metrica, mesRelatorio, periodo);
			return formataTimeFramAno(timeFrameList);
		}	
	}

	private String getTfCalculo0a8e18a24(Integer metrica, String mesRelatorio, Integer idGraficoControle) {
		List<TimeFrameVO> timeFrameList = null;
		List<String> periodo = new ArrayList<String>();
		periodo.add("00");
		periodo.add("01");
		periodo.add("02");
		periodo.add("03");
		periodo.add("04");
		periodo.add("05");
		periodo.add("06");
		periodo.add("07");
		periodo.add("18");
		periodo.add("19");
		periodo.add("20");
		periodo.add("21");
		periodo.add("22");
		periodo.add("23");
		if (idGraficoControle%2!=0) { //impar = mensal
			timeFrameList = workloadDao.getTimeFrame(metrica, mesRelatorio,periodo);
			return formataTimeFram(timeFrameList);
		} else { //par = anual
			timeFrameList = workloadDao.getTimeFrameAno(metrica, mesRelatorio,periodo);
			return formataTimeFramAno(timeFrameList);
		}
	}

	private String getTfCalculo0a8e23a24(Integer metrica, String mesRelatorio, Integer idGraficoControle) {
		List<TimeFrameVO> timeFrameList = null;
		List<String> periodo = new ArrayList<String>();
		periodo.add("00");
		periodo.add("01");
		periodo.add("02");
		periodo.add("04");
		periodo.add("05");
		periodo.add("06");
		periodo.add("07");
		periodo.add("23");
		if (idGraficoControle%2!=0) { //impar = mensal
			timeFrameList = workloadDao.getTimeFrame(metrica, mesRelatorio, periodo);
			return formataTimeFram(timeFrameList);
		} else { //par = anual
			timeFrameList = workloadDao.getTimeFrameAno(metrica, mesRelatorio, periodo);
			return formataTimeFramAno(timeFrameList);
		}
	}

	private String getTfCalculo24horas(Integer metrica, String mesRelatorio, Integer idGraficoControle) {
		List<TimeFrameVO> timeFrameList = null;
		if (idGraficoControle%2!=0) { //impar
			timeFrameList = workloadDao.getTimeFrame24horas(
					metrica, mesRelatorio);
			return formataTimeFram(timeFrameList);
		} else { //par
			timeFrameList = workloadDao.getTimeFrameAno24horas(
					metrica, mesRelatorio);
			return formataTimeFramAno(timeFrameList);
		}
	}

	private String getTfCalculo8as18(Integer metrica, String mesRelatorio, Integer idGraficoControle) {
		List<TimeFrameVO> timeFrameList = null;
		List<String> periodo = new ArrayList<String>();
		periodo.add("07");
		periodo.add("08");
		periodo.add("09");
		periodo.add("10");
		periodo.add("11");
		periodo.add("12");
		periodo.add("13");
		periodo.add("14");
		periodo.add("15");
		periodo.add("16");
		periodo.add("17");
		if (idGraficoControle%2!=0) { //impar
			timeFrameList = workloadDao.getTimeFrame(metrica, mesRelatorio, periodo);
			return formataTimeFram(timeFrameList);
		} else { //par
			timeFrameList = workloadDao.getTimeFrameAno(metrica,mesRelatorio, periodo);
			return formataTimeFramAno(timeFrameList);
		}
	}

	private String formataTimeFram(List<TimeFrameVO> timeFrameList) {
		String saida = "[";
		Iterator<TimeFrameVO> itTime = timeFrameList.iterator();
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat sdf2 = new SimpleDateFormat("dd");
		DecimalFormat df = new DecimalFormat("###");
		while (itTime.hasNext()) {
			TimeFrameVO timeFrame = itTime.next();
			try {
				saida = saida
						+ "["
						+ sdf2.format(sdf1.parse(timeFrame.getData()).getTime())
						+ "," + df.format(timeFrame.getValor()) + "],";
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		saida = saida.substring(0,saida.length()-1);
		saida = saida + "]";
		return saida;
	}

	private String formataTimeFramAno(List<TimeFrameVO> timeFrameList) {
		String saida = "[";
		Iterator<TimeFrameVO> itTime = timeFrameList.iterator();
		DecimalFormat df = new DecimalFormat("###");
		while (itTime.hasNext()) {
			TimeFrameVO timeFrame = itTime.next();
				saida = saida
						+ "["
						+ "(new Date("+timeFrame.getData().substring(3, 7) +","+(Integer.parseInt(timeFrame.getData().substring(0, 2))-1)+")).getTime()"
						+ "," + df.format(timeFrame.getValor()) + "],";
		}
		saida = saida.substring(0,saida.length()-1);
		saida = saida + "]";
		return saida;
	}	
}
