package br.com.paxtecnologia.pma.relatorio.ejb;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;

import br.com.paxtecnologia.pma.relatorio.dao.LinhaValorDAO;
import br.com.paxtecnologia.pma.relatorio.util.FormataValorUtil;
import br.com.paxtecnologia.pma.relatorio.vo.DBSizeTabelaVO;
import br.com.paxtecnologia.pma.relatorio.vo.HostVO;
import br.com.paxtecnologia.pma.relatorio.vo2.LinhaValorVO;

@Stateless
public class LinhaValorEjb {
	private LinhaValorDAO linhaValorDAO = new LinhaValorDAO();
	private Map<String, Integer> controleIdCliente = new HashMap<String, Integer>();
	private Map<String, String> controleMesCliente = new HashMap();
	private List<HostVO> hosts;
	private Integer diasNoMes;
	private Long diferenca;

	public String getLinhaValorJS(Integer linhaId, Integer graficoId, String mesRelatorio, Integer tipoPeriodoId,
			Integer tipoConsolidacaoDadoId, Boolean isByte) {
		List<LinhaValorVO> listaLinhaValorVO = null;
		List<Integer> listaHoras = getListaHorasTF(tipoConsolidacaoDadoId);

		switch (tipoConsolidacaoDadoId.intValue()) {
		case 1:
			return "Erro: Tipo de dado nao implementado.";
		case 2:
			if (tipoPeriodoId.intValue() == 1) {
				return "Erro: Tipo de dado nao implementado.";
			}
			if (tipoPeriodoId.intValue() == 2) {
				listaLinhaValorVO = this.linhaValorDAO.getLinhaValorMesAnual(linhaId, graficoId, mesRelatorio);
				return formataDsJSAno(listaLinhaValorVO, isByte);
			}
		case 3:
			if (tipoPeriodoId.intValue() == 1) {
				listaLinhaValorVO = this.linhaValorDAO.getLinhaValorDiaMensal(linhaId, graficoId, mesRelatorio);
				return formataDsJSMes(listaLinhaValorVO, isByte);
			}
			if (tipoPeriodoId.intValue() == 2) {
				listaLinhaValorVO = this.linhaValorDAO.getLinhaValorMesAnual(linhaId, graficoId, mesRelatorio);
				return formataDsJSAno(listaLinhaValorVO, isByte);
			}
		case 5:
			if (tipoPeriodoId.intValue() == 1) {
				return "Erro: Tipo de dado nao implementado.";
			}
			if (tipoPeriodoId.intValue() == 2) {
				listaLinhaValorVO = this.linhaValorDAO.getLinhaValorUdmAnual(linhaId, graficoId, mesRelatorio);
				return formataDsJSAno(listaLinhaValorVO, isByte);
			}
		case 6:
		case 7:
		case 8:
		case 9:
			if (tipoPeriodoId.intValue() == 1) {
				listaLinhaValorVO = this.linhaValorDAO.getLinhaValorTimeFrameHrMensal(linhaId, graficoId, mesRelatorio,
						listaHoras);
				return formataDsJSMes(listaLinhaValorVO, isByte);
			}
			if (tipoPeriodoId.intValue() == 2) {
				listaLinhaValorVO = this.linhaValorDAO.getLinhaValorTimeFrameHrAnual(linhaId, graficoId, mesRelatorio,
						listaHoras);
				return formataDsJSAno(listaLinhaValorVO, isByte);
			}
			break;
		}
		return "Erro: Tipo de dado nao implementado.";
	}

	private List<Integer> getListaHorasTF(Integer tipoConsolidacaoDadoId) {
		List<Integer> listaHoras = new ArrayList<Integer>();
		switch (tipoConsolidacaoDadoId.intValue()) {
		case 6:
			listaHoras.add(Integer.valueOf(8));
			listaHoras.add(Integer.valueOf(9));
			listaHoras.add(Integer.valueOf(10));
			listaHoras.add(Integer.valueOf(11));
			listaHoras.add(Integer.valueOf(12));
			listaHoras.add(Integer.valueOf(13));
			listaHoras.add(Integer.valueOf(14));
			listaHoras.add(Integer.valueOf(15));
			listaHoras.add(Integer.valueOf(16));
			listaHoras.add(Integer.valueOf(17));
			break;
		case 7:
			listaHoras.add(Integer.valueOf(0));
			listaHoras.add(Integer.valueOf(1));
			listaHoras.add(Integer.valueOf(2));
			listaHoras.add(Integer.valueOf(3));
			listaHoras.add(Integer.valueOf(4));
			listaHoras.add(Integer.valueOf(5));
			listaHoras.add(Integer.valueOf(6));
			listaHoras.add(Integer.valueOf(7));
			listaHoras.add(Integer.valueOf(18));
			listaHoras.add(Integer.valueOf(19));
			listaHoras.add(Integer.valueOf(20));
			listaHoras.add(Integer.valueOf(21));
			listaHoras.add(Integer.valueOf(22));
			listaHoras.add(Integer.valueOf(23));
			break;
		case 8:
			listaHoras.add(Integer.valueOf(18));
			listaHoras.add(Integer.valueOf(19));
			listaHoras.add(Integer.valueOf(20));
			listaHoras.add(Integer.valueOf(21));
			listaHoras.add(Integer.valueOf(22));
			break;
		case 9:
			listaHoras.add(Integer.valueOf(0));
			listaHoras.add(Integer.valueOf(1));
			listaHoras.add(Integer.valueOf(2));
			listaHoras.add(Integer.valueOf(3));
			listaHoras.add(Integer.valueOf(4));
			listaHoras.add(Integer.valueOf(5));
			listaHoras.add(Integer.valueOf(6));
			listaHoras.add(Integer.valueOf(7));
			listaHoras.add(Integer.valueOf(23));
			break;
		default:
			listaHoras = null;
		}
		return listaHoras;
	}

	private String formataDsJSMes(List<LinhaValorVO> listaLinhaValorVO, Boolean isByte) {
		String saida = "[";
		Iterator<LinhaValorVO> itTime = listaLinhaValorVO.iterator();
		Collections.sort(listaLinhaValorVO, new LinhaValorVO());
		SimpleDateFormat sdfIn = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat sdfOut = new SimpleDateFormat("dd");
		if (isByte) {
			while (itTime.hasNext()) {
				LinhaValorVO linhaValorVO;
				linhaValorVO = (LinhaValorVO) itTime.next();
				try {
					saida = saida + "[" + sdfOut.format(Long.valueOf(sdfIn.parse(linhaValorVO.getData()).getTime()))
									+ "," + linhaValorVO.getValor().doubleValue() / 1024.0D / 1024.0D / 1024.0D + "],";
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		} else {
			while (itTime.hasNext()) {
				LinhaValorVO linhaValorVO;
				linhaValorVO = (LinhaValorVO) itTime.next();
				try {
					saida = saida + "[" + sdfOut.format(Long.valueOf(sdfIn.parse(linhaValorVO.getData()).getTime()))
									+ "," + linhaValorVO.getValor() + "],";
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}
		saida = saida.substring(0, saida.length() - 1);
		saida = saida + "]";
		return saida;
	}

	private String formataDsJSAno(List<LinhaValorVO> listaLinhaValorVO, Boolean isByte) {
		String saida = "[";
		Iterator<LinhaValorVO> itTime = listaLinhaValorVO.iterator();
		Collections.sort(listaLinhaValorVO, new LinhaValorVO());
		if (isByte.booleanValue()) {
			while (itTime.hasNext()) {
				LinhaValorVO linhaValorVO;
				linhaValorVO = (LinhaValorVO) itTime.next();
				try {
					saida = saida + "[" + "(new Date(" + linhaValorVO.getData().substring(3, 7) + ","
									+ (Integer.parseInt(linhaValorVO.getData().substring(0, 2)) - 1) + ")).getTime()"
									+ "," + linhaValorVO.getValor().doubleValue() / 1024.0D / 1024.0D / 1024.0D + "],";
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} else {
			while (itTime.hasNext()) {
				LinhaValorVO linhaValorVO;
				linhaValorVO = (LinhaValorVO) itTime.next();
				try {
					saida = saida + "[" + "(new Date(" + linhaValorVO.getData().substring(3, 7) + ","
									+ (Integer.parseInt(linhaValorVO.getData().substring(0, 2)) - 1) + ")).getTime()"
									+ "," + linhaValorVO.getValor() + "],";
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		saida = saida.substring(0, saida.length() - 1);
		saida = saida + "]";
		return saida;
	}

	public List<DBSizeTabelaVO> getTabelaDBsize(Integer graficoId, String mesRelatorio) {
		List<DBSizeTabelaVO> retorno = this.linhaValorDAO.getTabelaDBsize(graficoId, mesRelatorio);
		for (DBSizeTabelaVO i : retorno) {
			i.setEspTotal(FormataValorUtil.humanReadableByteCount(Long.parseLong(i.getEspTotal()), true));
			i.setVarAbs(FormataValorUtil.humanReadableByteCount(Long.parseLong(i.getVarAbs()), true));
			i.setValorA(FormataValorUtil.humanReadableByteCount(Long.parseLong(i.getValorA()), true));
			i.setValorD(FormataValorUtil.humanReadableByteCount(Long.parseLong(i.getValorD()), true));
		}
		return retorno;
	}
}