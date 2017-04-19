package br.com.paxtecnologia.pma.relatorio.ejb;

import br.com.paxtecnologia.pma.relatorio.dao.ParadasDAO;
import br.com.paxtecnologia.pma.relatorio.util.FormataDataUtil;
import br.com.paxtecnologia.pma.relatorio.util.FormataValorUtil;
import br.com.paxtecnologia.pma.relatorio.vo.ParadasPorTipoVO;
import br.com.paxtecnologia.pma.relatorio.vo.UltimoAnoVO;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import org.joda.time.DateTime;
import org.joda.time.DateTime.Property;
import org.joda.time.Days;

@Stateless
public class ParadasEjb {
	private ParadasDAO paradasDao = new ParadasDAO();

	private List<UltimoAnoVO> listaUltimosAnosHoras;
	private List<ParadasPorTipoVO> listaParadasEvitadas;
	private List<ParadasPorTipoVO> listaParadasEvitadasMes;
	private List<ParadasPorTipoVO> listaParadasNaoProgramadas;
	private List<ParadasPorTipoVO> listaParadasNaoProgramadasMes;
	private List<ParadasPorTipoVO> listaParadasProgramadasEstrategicas;
	private List<ParadasPorTipoVO> listaParadasProgramadasEstrategicasMes;
	private List<ParadasPorTipoVO> listaParadasProgramadas;
	private List<ParadasPorTipoVO> listaParadasProgramadasMes;
	private Integer qtdeParadaProgramadas;
	private Integer qtdeParadaProgramadasMes;
	private Integer qtdeProgramadasEstrategicas;
	private Integer qtdeProgramadasEstrategicasMes;
	private Integer qtdeParadaNaoProgramadas;
	private Integer qtdeParadaNaoProgramadasMes;
	private Integer qtdeParadaEvitadas;
	private Integer qtdeParadaEvitadasMes;
	private Integer diasTrabalhados;
	private static String PARADAS_EVITADAS = "PE";
	private static String PARADAS_NAO_PROGRAMADAS = "PNP";
	private static String PARADAS_PROGRAMADAS_ESTRATEGICAS = "PPE";
	private static String PARADAS_PROGRAMADAS = "PP";

	Map<String, Integer> controleIdCliente = new HashMap<String, Integer>();
	private Map<String, String> controleMesCliente = new HashMap<String, String>();

	public Integer getDiasTrabalhados(Integer idCliente, String mesRelatorio) {
		if ((this.diasTrabalhados == null) || (this.controleIdCliente.get("getDiasTrabalhados") != idCliente)
				|| ((this.controleIdCliente.get("getDiasTrabalhados") == idCliente)
						&& (this.controleMesCliente.get("mesCliente") != mesRelatorio))) {
			Calendar gc = this.paradasDao.getDataUltimoPNP(idCliente, mesRelatorio);

			Calendar calendar = Calendar.getInstance();
			calendar.set(FormataDataUtil.getCampoMesRelatorio("ano", mesRelatorio).intValue(),
					FormataDataUtil.getCampoMesRelatorio("mes", mesRelatorio).intValue() - 1,
					FormataDataUtil.getCampoMesRelatorio("dia", mesRelatorio).intValue(), 0, 0, 0);
			int lastDate = calendar.getActualMaximum(5);
			calendar.set(5, lastDate);

			DateTime start = new DateTime(gc.getTime());
			DateTime end = new DateTime(calendar);

			int days = Days.daysBetween(start, end).getDays();
			this.diasTrabalhados = Integer.valueOf(days);

			this.controleIdCliente.put("getDiasTrabalhados", idCliente);
			this.controleMesCliente.put("controleMesCliente", mesRelatorio);
		}
		return this.diasTrabalhados;
	}

	public Integer getQtdeParadaEvitadasTotal(Integer idCliente, String mesRelatorio) {
		return this.paradasDao.getQtdeParadaEvitadasTotal(idCliente, mesRelatorio);
	}

	public List<UltimoAnoVO> getListaUltimosAnosHoras(Integer idCliente, String tipo, String mesRelatorio) {
		if ((this.listaUltimosAnosHoras == null)
				|| (this.controleIdCliente.get("getListaUltimosAnosHoras") != idCliente)
				|| ((this.controleIdCliente.get("getListaUltimosAnosHoras") == idCliente)
						&& (this.controleMesCliente.get("mesCliente") != mesRelatorio))) {
			this.listaUltimosAnosHoras = this.paradasDao.getListaUltimosAnosHoras(idCliente, tipo, mesRelatorio);
			this.controleIdCliente.put("getListaUltimosAnosHoras", idCliente);
			this.controleMesCliente.put("controleMesCliente", mesRelatorio);
		}
		return this.listaUltimosAnosHoras;
	}

	public Integer getQtdeParadaProgramadas(Integer idCliente, String mesRelatorio, String tipo) {
		if ((this.qtdeParadaProgramadas == null)
				|| (this.controleIdCliente.get("getQtdeParadaProgramadas") != idCliente)
				|| ((this.controleIdCliente.get("getQtdeParadaProgramadas") == idCliente)
						&& (this.controleMesCliente.get("mesCliente") != mesRelatorio))) {
			if ((this.listaParadasProgramadas == null)
					|| (this.controleIdCliente.get("getQtdeParadaProgramadas") != idCliente)
					|| ((this.controleIdCliente.get("getQtdeParadaProgramadas") == idCliente)
							&& (this.controleMesCliente.get("mesCliente") != mesRelatorio))) {
				getListaParadasProgramadas(idCliente, mesRelatorio, tipo);
				this.controleIdCliente.put("getQtdeParadaProgramadas", idCliente);
				this.controleMesCliente.put("controleMesCliente", mesRelatorio);
			}
			this.qtdeParadaProgramadas = Integer.valueOf(this.listaParadasProgramadas.size());
		}
		return this.qtdeParadaProgramadas;
	}

	public Integer getQtdeParadaProgramadasMes(Integer idCliente, String mesRelatorio, String tipo) {
		if ((this.qtdeParadaProgramadasMes == null)
				|| (this.controleIdCliente.get("getQtdeParadaProgramadasMes") != idCliente)
				|| ((this.controleIdCliente.get("getQtdeParadaProgramadasMes") == idCliente)
						&& (this.controleMesCliente.get("mesCliente") != mesRelatorio))) {
			if ((this.listaParadasProgramadasMes == null)
					|| (this.controleIdCliente.get("getQtdeParadaProgramadasMes") != idCliente)
					|| ((this.controleIdCliente.get("getQtdeParadaProgramadasMes") == idCliente)
							&& (this.controleMesCliente.get("mesCliente") != mesRelatorio))) {
				getListaParadasProgramadasMes(idCliente, mesRelatorio, tipo);
				this.controleIdCliente.put("getQtdeParadaProgramadasMes", idCliente);
				this.controleMesCliente.put("controleMesCliente", mesRelatorio);
			}
			this.qtdeParadaProgramadasMes = Integer.valueOf(this.listaParadasProgramadasMes.size());
		}
		return this.qtdeParadaProgramadasMes;
	}

	public Integer getQtdeProgramadasEstrategicas(Integer idCliente, String mesRelatorio, String tipo) {
		if ((this.qtdeProgramadasEstrategicas == null)
				|| (this.controleIdCliente.get("getQtdeProgramadasEstrategicas") != idCliente)
				|| ((this.controleIdCliente.get("getQtdeProgramadasEstrategicas") == idCliente)
						&& (this.controleMesCliente.get("mesCliente") != mesRelatorio))) {
			if ((this.listaParadasProgramadas == null)
					|| (this.controleIdCliente.get("getQtdeProgramadasEstrategicas") != idCliente)
					|| ((this.controleIdCliente.get("getQtdeProgramadasEstrategicas") == idCliente)
							&& (this.controleMesCliente.get("mesCliente") != mesRelatorio))) {
				getListaParadasProgramadasEstrategicas(idCliente, mesRelatorio, tipo);
				this.controleIdCliente.put("getQtdeProgramadasEstrategicas", idCliente);
				this.controleMesCliente.put("controleMesCliente", mesRelatorio);
			}
			this.qtdeProgramadasEstrategicas = Integer.valueOf(this.listaParadasProgramadasEstrategicas.size());
		}
		return this.qtdeProgramadasEstrategicas;
	}

	public Integer getQtdeProgramadasEstrategicasMes(Integer idCliente, String mesRelatorio, String tipo) {
		if ((this.qtdeProgramadasEstrategicasMes == null)
				|| (this.controleIdCliente.get("getQtdeProgramadasEstrategicasMes") != idCliente)
				|| ((this.controleIdCliente.get("getQtdeProgramadasEstrategicasMes") == idCliente)
						&& (this.controleMesCliente.get("mesCliente") != mesRelatorio))) {
			if ((this.listaParadasProgramadasMes == null)
					|| (this.controleIdCliente.get("getQtdeProgramadasEstrategicasMes") != idCliente)
					|| ((this.controleIdCliente.get("getQtdeProgramadasEstrategicasMes") == idCliente)
							&& (this.controleMesCliente.get("mesCliente") != mesRelatorio))) {
				getListaParadasProgramadasEstrategicasMes(idCliente, mesRelatorio, tipo);
				this.controleIdCliente.put("getQtdeProgramadasEstrategicasMes", idCliente);
				this.controleMesCliente.put("controleMesCliente", mesRelatorio);
			}
			this.qtdeProgramadasEstrategicasMes = Integer.valueOf(this.listaParadasProgramadasEstrategicasMes.size());
		}
		return this.qtdeProgramadasEstrategicasMes;
	}

	public Integer getQtdeParadaNaoProgramadas(Integer idCliente, String mesRelatorio, String tipo) {
		if ((this.qtdeParadaNaoProgramadas == null)
				|| (this.controleIdCliente.get("getQtdeParadaNaoProgramadas") != idCliente)
				|| ((this.controleIdCliente.get("getQtdeParadaNaoProgramadas") == idCliente)
						&& (this.controleMesCliente.get("mesCliente") != mesRelatorio))) {
			if ((this.listaParadasProgramadas == null)
					|| (this.controleIdCliente.get("getQtdeParadaNaoProgramadas") != idCliente)
					|| ((this.controleIdCliente.get("getQtdeParadaNaoProgramadas") == idCliente)
							&& (this.controleMesCliente.get("mesCliente") != mesRelatorio))) {
				getListaParadasNaoProgramadas(idCliente, mesRelatorio, tipo);
				this.controleIdCliente.put("getQtdeParadaNaoProgramadas", idCliente);
				this.controleMesCliente.put("controleMesCliente", mesRelatorio);
			}
			this.qtdeParadaNaoProgramadas = Integer.valueOf(this.listaParadasNaoProgramadas.size());
		}
		return this.qtdeParadaNaoProgramadas;
	}

	public Integer getQtdeParadaNaoProgramadasMes(Integer idCliente, String mesRelatorio, String tipo) {
		if ((this.qtdeParadaNaoProgramadasMes == null)
				|| (this.controleIdCliente.get("getQtdeParadaNaoProgramadasMes") != idCliente)
				|| ((this.controleIdCliente.get("getQtdeParadaNaoProgramadasMes") == idCliente)
						&& (this.controleMesCliente.get("mesCliente") != mesRelatorio))) {
			if ((this.listaParadasProgramadasMes == null)
					|| (this.controleIdCliente.get("getQtdeParadaNaoProgramadasMes") != idCliente)
					|| ((this.controleIdCliente.get("getQtdeParadaNaoProgramadasMes") == idCliente)
							&& (this.controleMesCliente.get("mesCliente") != mesRelatorio))) {
				getListaParadasNaoProgramadasMes(idCliente, mesRelatorio, tipo);
				this.controleIdCliente.put("getQtdeParadaNaoProgramadasMes", idCliente);
				this.controleMesCliente.put("controleMesCliente", mesRelatorio);
			}
			this.qtdeParadaNaoProgramadasMes = Integer.valueOf(this.listaParadasNaoProgramadasMes.size());
		}
		return this.qtdeParadaNaoProgramadasMes;
	}

	public Integer getQtdeParadaEvitadas(Integer idCliente, String mesRelatorio, String tipo) {
		if ((this.qtdeParadaEvitadas == null) || (this.controleIdCliente.get("getQtdeParadaEvitadas") != idCliente)
				|| ((this.controleIdCliente.get("getQtdeParadaEvitadas") == idCliente)
						&& (this.controleMesCliente.get("mesCliente") != mesRelatorio))) {
			if ((this.listaParadasEvitadas == null)
					|| (this.controleIdCliente.get("getQtdeParadaEvitadas") != idCliente)
					|| ((this.controleIdCliente.get("getQtdeParadaEvitadas") == idCliente)
							&& (this.controleMesCliente.get("mesCliente") != mesRelatorio))) {
				getListaParadasEvitadas(idCliente, mesRelatorio, tipo);
				this.controleIdCliente.put("getQtdeParadaEvitadas", idCliente);
				this.controleMesCliente.put("controleMesCliente", mesRelatorio);
			}
			this.qtdeParadaEvitadas = Integer.valueOf(this.listaParadasEvitadas.size());
		}
		return this.qtdeParadaEvitadas;
	}

	public Integer getQtdeParadaEvitadasMes(Integer idCliente, String mesRelatorio, String tipo) {
		if ((this.qtdeParadaEvitadasMes == null)
				|| (this.controleIdCliente.get("getQtdeParadaEvitadasMes") != idCliente)
				|| ((this.controleIdCliente.get("getQtdeParadaEvitadasMes") == idCliente)
						&& (this.controleMesCliente.get("mesCliente") != mesRelatorio))) {
			if ((this.listaParadasEvitadasMes == null)
					|| (this.controleIdCliente.get("getQtdeParadaEvitadasMes") != idCliente)
					|| ((this.controleIdCliente.get("getQtdeParadaEvitadasMes") == idCliente)
							&& (this.controleMesCliente.get("mesCliente") != mesRelatorio))) {
				getListaParadasEvitadasMes(idCliente, mesRelatorio, tipo);
				this.controleIdCliente.put("getQtdeParadaEvitadasMes", idCliente);
				this.controleMesCliente.put("controleMesCliente", mesRelatorio);
			}
			this.qtdeParadaEvitadasMes = Integer.valueOf(this.listaParadasEvitadasMes.size());
		}
		return this.qtdeParadaEvitadasMes;
	}

	public List<ParadasPorTipoVO> getListaParadasEvitadas(Integer idCliente, String mesRelatorio, String tipo) {
		if ((this.listaParadasEvitadas == null) || (this.controleIdCliente.get("getListaParadasEvitadas") != idCliente)
				|| ((this.controleIdCliente.get("getListaParadasEvitadas") == idCliente)
						&& (this.controleMesCliente.get("mesCliente") != mesRelatorio))) {
			this.listaParadasEvitadas = this.paradasDao.getListaParadasPorTipo(idCliente, mesRelatorio, tipo);
			this.controleIdCliente.put("getListaParadasEvitadas", idCliente);
			this.controleMesCliente.put("controleMesCliente", mesRelatorio);
		}
		return this.listaParadasEvitadas;
	}

	public List<ParadasPorTipoVO> getListaParadasEvitadasMes(Integer idCliente, String mesRelatorio, String tipo) {
		if ((this.listaParadasEvitadasMes == null) || (this.listaParadasEvitadasMes.size() == 0)
				|| (this.controleIdCliente.get("getListaParadasEvitadasMes") != idCliente)
				|| ((this.controleIdCliente.get("getListaParadasEvitadasMes") == idCliente)
						&& (this.controleMesCliente.get("mesCliente") != mesRelatorio))) {
			DateTime data = new DateTime(FormataDataUtil.getCampoMesRelatorio("ano", mesRelatorio).intValue(),
					FormataDataUtil.getCampoMesRelatorio("mes", mesRelatorio).intValue(),
					FormataDataUtil.getCampoMesRelatorio("dia", mesRelatorio).intValue(), 0, 0, 0);

			this.listaParadasEvitadasMes = new ArrayList<ParadasPorTipoVO>();
			for (ParadasPorTipoVO paradasPorTipoVO : getListaParadasEvitadas(idCliente, mesRelatorio, tipo)) {
				if (Integer.parseInt(paradasPorTipoVO.getDataParada().substring(3, 5)) == data.monthOfYear().get()) {
					if (Integer.parseInt(paradasPorTipoVO.getDataParada().substring(6, 10)) == data.year().get())
						this.listaParadasEvitadasMes.add(paradasPorTipoVO);
				}
			}
			this.controleIdCliente.put("getListaParadasEvitadasMes", idCliente);
			this.controleMesCliente.put("controleMesCliente", mesRelatorio);
		}
		return this.listaParadasEvitadasMes;
	}

	public List<ParadasPorTipoVO> getListaParadasNaoProgramadas(Integer idCliente, String mesRelatorio, String tipo) {
		if ((this.listaParadasNaoProgramadas == null)
				|| (this.controleIdCliente.get("getListaParadasNaoProgramadas") != idCliente)
				|| ((this.controleIdCliente.get("getListaParadasNaoProgramadas") == idCliente)
						&& (this.controleMesCliente.get("mesCliente") != mesRelatorio))) {
			this.listaParadasNaoProgramadas = this.paradasDao.getListaParadasPorTipo(idCliente, mesRelatorio, tipo);
			this.controleIdCliente.put("getListaParadasNaoProgramadas", idCliente);
			this.controleMesCliente.put("controleMesCliente", mesRelatorio);
		}
		return this.listaParadasNaoProgramadas;
	}

	public List<ParadasPorTipoVO> getListaParadasNaoProgramadasMes(Integer idCliente, String mesRelatorio,
			String tipo) {
		if ((this.listaParadasNaoProgramadasMes == null) || (this.listaParadasNaoProgramadasMes.size() == 0)
				|| (this.controleIdCliente.get("getListaParadasNaoProgramadasMes") != idCliente)
				|| ((this.controleIdCliente.get("getListaParadasNaoProgramadasMes") == idCliente)
						&& (this.controleMesCliente.get("mesCliente") != mesRelatorio))) {
			DateTime data = new DateTime(FormataDataUtil.getCampoMesRelatorio("ano", mesRelatorio).intValue(),
					FormataDataUtil.getCampoMesRelatorio("mes", mesRelatorio).intValue(),
					FormataDataUtil.getCampoMesRelatorio("dia", mesRelatorio).intValue(), 0, 0, 0);

			this.listaParadasNaoProgramadasMes = new ArrayList<ParadasPorTipoVO>();
			for (ParadasPorTipoVO paradasPorTipoVO : getListaParadasNaoProgramadas(idCliente, mesRelatorio, tipo)) {
				if (Integer.parseInt(paradasPorTipoVO.getDataParada().substring(3, 5)) == data.monthOfYear().get()) {
					if (Integer.parseInt(paradasPorTipoVO.getDataParada().substring(6, 10)) == data.year().get()) {
						this.listaParadasNaoProgramadasMes.add(paradasPorTipoVO);
					}
				}
			}
			this.controleIdCliente.put("getListaParadasNaoProgramadasMes", idCliente);
			this.controleMesCliente.put("controleMesCliente", mesRelatorio);
		}
		return this.listaParadasNaoProgramadasMes;
	}

	public List<ParadasPorTipoVO> getListaParadasProgramadasEstrategicas(Integer idCliente, String mesRelatorio,
			String tipo) {
		if ((this.listaParadasProgramadasEstrategicas == null)
				|| (this.controleIdCliente.get("getListaParadasProgramadasEstrategicas") != idCliente)
				|| ((this.controleIdCliente.get("getListaParadasProgramadasEstrategicas") == idCliente)
						&& (this.controleMesCliente.get("mesCliente") != mesRelatorio))) {
			this.listaParadasProgramadasEstrategicas = this.paradasDao.getListaParadasPorTipo(idCliente, mesRelatorio,
					tipo);
			this.controleIdCliente.put("getListaParadasProgramadasEstrategicas", idCliente);
			this.controleMesCliente.put("controleMesCliente", mesRelatorio);
		}
		return this.listaParadasProgramadasEstrategicas;
	}

	public List<ParadasPorTipoVO> getListaParadasProgramadasEstrategicasMes(Integer idCliente, String mesRelatorio,
			String tipo) {
		if ((this.listaParadasProgramadasEstrategicasMes == null)
				|| (this.listaParadasProgramadasEstrategicasMes.size() == 0)
				|| (this.controleIdCliente.get("getListaParadasProgramadasEstrategicasMes") != idCliente)
				|| ((this.controleIdCliente.get("getListaParadasProgramadasEstrategicasMes") == idCliente)
						&& (this.controleMesCliente.get("mesCliente") != mesRelatorio))) {
			DateTime data = new DateTime(FormataDataUtil.getCampoMesRelatorio("ano", mesRelatorio).intValue(),
					FormataDataUtil.getCampoMesRelatorio("mes", mesRelatorio).intValue(),
					FormataDataUtil.getCampoMesRelatorio("dia", mesRelatorio).intValue(), 0, 0, 0);

			this.listaParadasProgramadasEstrategicasMes = new ArrayList<ParadasPorTipoVO>();
			for (ParadasPorTipoVO paradasPorTipoVO : getListaParadasProgramadasEstrategicas(idCliente, mesRelatorio,
					tipo)) {
				if (Integer.parseInt(paradasPorTipoVO.getDataParada().substring(3, 5)) == data.monthOfYear().get()) {
					if (Integer.parseInt(paradasPorTipoVO.getDataParada().substring(6, 10)) == data.year().get()) {
						this.listaParadasProgramadasEstrategicasMes.add(paradasPorTipoVO);
					}
				}
			}
			this.controleIdCliente.put("getListaParadasProgramadasEstrategicasMes", idCliente);
			this.controleMesCliente.put("controleMesCliente", mesRelatorio);
		}
		return this.listaParadasProgramadasEstrategicasMes;
	}

	public List<ParadasPorTipoVO> getListaParadasProgramadas(Integer idCliente, String mesRelatorio, String tipo) {
		if ((this.listaParadasProgramadas == null)
				|| (this.controleIdCliente.get("getListaParadasProgramadas") != idCliente)
				|| ((this.controleIdCliente.get("getListaParadasProgramadas") == idCliente)
						&& (this.controleMesCliente.get("mesCliente") != mesRelatorio))) {
			this.listaParadasProgramadas = this.paradasDao.getListaParadasPorTipo(idCliente, mesRelatorio, tipo);
			this.controleIdCliente.put("getListaParadasProgramadas", idCliente);
			this.controleMesCliente.put("controleMesCliente", mesRelatorio);
		}
		return this.listaParadasProgramadas;
	}

	public List<ParadasPorTipoVO> getListaParadasProgramadasMes(Integer idCliente, String mesRelatorio, String tipo) {
		if ((this.listaParadasProgramadasMes == null) || (this.listaParadasProgramadasMes.size() == 0)
				|| (this.controleIdCliente.get("getListaParadasProgramadasMes") != idCliente)
				|| ((this.controleIdCliente.get("getListaParadasProgramadasMes") == idCliente)
						&& (this.controleMesCliente.get("mesCliente") != mesRelatorio))) {
			DateTime data = new DateTime(FormataDataUtil.getCampoMesRelatorio("ano", mesRelatorio).intValue(),
					FormataDataUtil.getCampoMesRelatorio("mes", mesRelatorio).intValue(),
					FormataDataUtil.getCampoMesRelatorio("dia", mesRelatorio).intValue(), 0, 0, 0);

			this.listaParadasProgramadasMes = new ArrayList<ParadasPorTipoVO>();
			for (ParadasPorTipoVO paradasPorTipoVO : getListaParadasProgramadas(idCliente, mesRelatorio, tipo)) {
				if (Integer.parseInt(paradasPorTipoVO.getDataParada().substring(3, 5)) == data.monthOfYear().get()) {
					if (Integer.parseInt(paradasPorTipoVO.getDataParada().substring(6, 10)) == data.year().get())
						this.listaParadasProgramadasMes.add(paradasPorTipoVO);
				}
			}
			this.controleIdCliente.put("getListaParadasProgramadasMes", idCliente);
			this.controleMesCliente.put("controleMesCliente", mesRelatorio);
		}
		return this.listaParadasProgramadasMes;
	}

	public String getParadas(String tipo, String mesRelatorio) {
		List<ParadasPorTipoVO> listaParadas = null;

		if (tipo.equals(PARADAS_EVITADAS)) {
			listaParadas = this.listaParadasEvitadas;
		} else if (tipo.equals(PARADAS_NAO_PROGRAMADAS)) {
			listaParadas = this.listaParadasNaoProgramadas;
		} else if (tipo.equals(PARADAS_PROGRAMADAS_ESTRATEGICAS)) {
			listaParadas = this.listaParadasProgramadasEstrategicas;
		} else if (tipo.equals(PARADAS_PROGRAMADAS)) {
			listaParadas = this.listaParadasProgramadas;
		}

		String saida = "[";
		double[][] meses = new double[2][12];
		Integer j;
		for (Iterator<ParadasPorTipoVO> localIterator = listaParadas.iterator(); localIterator.hasNext();) {
			ParadasPorTipoVO paradasPorTipoVO = (ParadasPorTipoVO) localIterator.next();
			j = Integer.valueOf(0);
			continue;
		}

		for (Integer j1 = Integer.valueOf(1); j1.intValue() >= 0; j1 = Integer.valueOf(j1.intValue() - 1)) {
			Integer mesInicial = Integer.valueOf(0);
			Integer mesFinal = Integer.valueOf(0);
			if (j1.intValue() == 1) {
				mesInicial = FormataDataUtil.getCampoMesRelatorio("mes", mesRelatorio);
				mesFinal = Integer.valueOf(12);
			} else {
				mesInicial = Integer.valueOf(0);
				mesFinal = FormataDataUtil.getCampoMesRelatorio("mes", mesRelatorio);
			}
			for (Integer i = mesInicial; i.intValue() < mesFinal.intValue(); i = Integer.valueOf(i.intValue() + 1)) {
				if (i.intValue() <= mesFinal.intValue()) {
					if ((tipo.equals(PARADAS_NAO_PROGRAMADAS)) || (tipo.equals(PARADAS_PROGRAMADAS))) {
						saida =

								saida + "[(new Date(" + (Integer.parseInt(mesRelatorio.substring(0, 4)) - j1.intValue())
										+ "," + i + ").getTime())," + -meses[j1.intValue()][i.intValue()] + "],";
					} else {
						saida =

								saida + "[(new Date(" + (Integer.parseInt(mesRelatorio.substring(0, 4)) - j1.intValue())
										+ "," + i + ").getTime())," + meses[j1.intValue()][i.intValue()] + "],";
					}
				} else {
					saida =

							saida + "[(new Date(" + (Integer.parseInt(mesRelatorio.substring(0, 4)) - j1.intValue())
									+ "," + i + ").getTime())," + 0.0D + "],";
				}
			}
		}

		saida = saida.substring(0, saida.length() - 1);
		saida = saida + "]";
		return saida;
	}

	public String getParadasAcumulado(String tipo, String mesRelatorio) {
		List<ParadasPorTipoVO> listaParadas = null;

		if (tipo.equals(PARADAS_EVITADAS)) {
			listaParadas = this.listaParadasEvitadas;
		} else if (tipo.equals(PARADAS_NAO_PROGRAMADAS)) {
			listaParadas = this.listaParadasNaoProgramadas;
		} else if (tipo.equals(PARADAS_PROGRAMADAS_ESTRATEGICAS)) {
			listaParadas = this.listaParadasProgramadasEstrategicas;
		} else if (tipo.equals(PARADAS_PROGRAMADAS)) {
			listaParadas = this.listaParadasProgramadas;
		}

		String saida = "[";
		double[] meses = new double[12];
		Integer i;
		for (Iterator<ParadasPorTipoVO> localIterator = listaParadas.iterator(); localIterator.hasNext();) {
			ParadasPorTipoVO paradasPorTipoVO = (ParadasPorTipoVO) localIterator.next();
			i = Integer.valueOf(0);
			continue;
		}

		double somaHorasAcumulado = 0.0D;
		for (Integer a = Integer.valueOf(0); a.intValue() < meses.length; a = Integer.valueOf(a.intValue() + 1)) {
			somaHorasAcumulado += meses[a.intValue()];
			if (a.intValue() < FormataDataUtil.getCampoMesRelatorio("mes", mesRelatorio).intValue()) {
				if ((tipo.equals(PARADAS_NAO_PROGRAMADAS)) || (tipo.equals(PARADAS_PROGRAMADAS))) {
					saida =

							saida + "[(new Date(" + Integer.parseInt(mesRelatorio.substring(0, 4)) + "," + a
									+ ").getTime())," + -somaHorasAcumulado + "],";
				} else {
					saida =

							saida + "[(new Date(" + Integer.parseInt(mesRelatorio.substring(0, 4)) + "," + a
									+ ").getTime())," + somaHorasAcumulado + "],";
				}
			} else {
				saida =

						saida + "[(new Date(" + Integer.parseInt(mesRelatorio.substring(0, 4)) + "," + a
								+ ").getTime())," + 0.0D + "],";
			}
		}

		saida = saida.substring(0, saida.length() - 1);
		saida = saida + "]";
		return saida;
	}

	public Double getTempoParadasMes(String tipo, String mesRelatorio) {
		List<ParadasPorTipoVO> listaParadas = null;

		if (tipo.equals(PARADAS_EVITADAS)) {
			listaParadas = this.listaParadasEvitadas;
		} else if (tipo.equals(PARADAS_NAO_PROGRAMADAS)) {
			listaParadas = this.listaParadasNaoProgramadas;
		} else if (tipo.equals(PARADAS_PROGRAMADAS_ESTRATEGICAS)) {
			listaParadas = this.listaParadasProgramadasEstrategicas;
		} else if (tipo.equals(PARADAS_PROGRAMADAS)) {
			listaParadas = this.listaParadasProgramadas;
		}

		DateTime data = new DateTime(FormataDataUtil.getCampoMesRelatorio("ano", mesRelatorio).intValue(),
				FormataDataUtil.getCampoMesRelatorio("mes", mesRelatorio).intValue(),
				FormataDataUtil.getCampoMesRelatorio("dia", mesRelatorio).intValue(), 0, 0, 0);

		Double somaHorasMes = Double.valueOf(0.0D);
		for (ParadasPorTipoVO paradasPorTipoVO : listaParadas) {
			if (Integer.parseInt(paradasPorTipoVO.getDataParada().substring(3, 5)) == data.monthOfYear().get()) {
				if (Integer.parseInt(paradasPorTipoVO.getDataParada().substring(6, 10)) == data.year().get()) {
					somaHorasMes = Double
							.valueOf(somaHorasMes.doubleValue() + paradasPorTipoVO.getHoras().doubleValue());
				}
			}
		}
		return Double.valueOf(FormataValorUtil.converterDoubleDoisDecimais(somaHorasMes.doubleValue()));
	}
}
