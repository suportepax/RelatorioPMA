package br.com.paxtecnologia.pma.relatorio.ejb;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;

import br.com.paxtecnologia.pma.relatorio.dao.AtendimentoDAO;
import br.com.paxtecnologia.pma.relatorio.vo.ChamadoQuantidadeVO;
import br.com.paxtecnologia.pma.relatorio.vo.ChamadoVO;

@Stateless
public class AtendimentoEjb {

	private AtendimentoDAO atendimentoDAO = new AtendimentoDAO();

	private Double porcentagemChamadosAbertos;
	private Double porcentagemChamadosEmAbertos;
	private Double porcentagemChamadosFechados;
	private Double porcentagemChamadosAbertosTipo;
	private Double porcentagemChamadosFechadosTipo;
	private Double porcentagemChamadosAbertosSolicitante;
	private Double porcentagemChamadosFechadosSolicitante;
	private Double porcentagemChamadosAbertosHost;
	private Double porcentagemChamadosFechadosHost;
	private Integer qtdeChamadosAbertos;
	private Integer qtdeChamadosFechados;
	private Integer qtdeChamadosEmAberto;
	private Integer qtdeChamadosFechadosHost;
	private Integer qtdeChamadosAbertosHost;
	private Integer qtdeChamadosAbertosSolicitante;
	private Integer qtdeChamadosFechadosSolicitante;
	private Integer qtdeChamadosAbertosTipo;
	private Integer qtdeChamadosFechadosTipo;
	private Double tempoMedioChamadoFechado;
	private List<ChamadoVO> listaChamadoAberto;
	private List<ChamadoVO> listaChamadoFechado;
	private List<ChamadoVO> listaChamadoEmAberto;
	private List<ChamadoQuantidadeVO> listaChamadoSolicitante;
	private List<ChamadoQuantidadeVO> listaChamadoTipo;
	private List<String> listaAbertosHost;
	private List<String> listaFechadosHost;
	private List<ChamadoQuantidadeVO> listaChamadosHost;
	private Map<String, Integer> controleIdCliente = new HashMap<String, Integer>();
	private Map<String, String> controleMesCliente = new HashMap<String, String>();
	private List<String> listaGraficoAbertosHost;
	private List<String> listaGraficoFechadosHost;

	private Double getPorcentagem(Integer valor, Integer total) {
		Double porcentagem = Double.valueOf(valor.doubleValue() / total.doubleValue());
		return porcentagem;
	}

	public Integer getQtdeChamadosAbertos(Integer idCliente, String mesRelatorio) {
		if ((this.qtdeChamadosAbertos == null) || (this.controleIdCliente.get("getQtdeChamadosAbertos") != idCliente)
				|| ((this.controleIdCliente.get("getQtdeChamadosAbertos") == idCliente)
						&& (this.controleMesCliente.get("mesCliente") != mesRelatorio))) {
			if ((this.listaChamadoAberto == null)
					|| (this.controleIdCliente.get("getListaChamadosAbertos") != idCliente)
					|| ((this.controleIdCliente.get("getListaChamadosAbertos") == idCliente)
							&& (this.controleMesCliente.get("mesCliente") != mesRelatorio))) {
				getListaChamadosAbertos(idCliente, mesRelatorio);
			}
			this.controleIdCliente.put("getQtdeChamadosAbertos", idCliente);
			this.controleMesCliente.put("controleMesCliente", mesRelatorio);
			this.qtdeChamadosAbertos = Integer.valueOf(this.listaChamadoAberto.size());
		}
		return this.qtdeChamadosAbertos;
	}

	public Integer getQtdeChamadosFechados(Integer idCliente, String mesRelatorio) {
		if ((this.qtdeChamadosFechados == null) || (this.controleIdCliente.get("getQtdeChamadosFechados") != idCliente)
				|| ((this.controleIdCliente.get("getQtdeChamadosFechados") == idCliente)
						&& (this.controleMesCliente.get("mesCliente") != mesRelatorio))) {
			if ((this.listaChamadoFechado == null)
					|| (this.controleIdCliente.get("getListaChamadosFechados") != idCliente)
					|| ((this.controleIdCliente.get("getListaChamadosFechados") == idCliente)
							&& (this.controleMesCliente.get("mesCliente") != mesRelatorio))) {
				getListaChamadosFechados(idCliente, mesRelatorio);
			}
			this.controleIdCliente.put("getQtdeChamadosFechados", idCliente);
			this.controleMesCliente.put("controleMesCliente", mesRelatorio);
			this.qtdeChamadosFechados = Integer.valueOf(this.listaChamadoFechado.size());
		}

		return this.qtdeChamadosFechados;
	}

	public Integer getQtdeChamadosEmAberto(Integer idCliente, String mesRelatorio) {
		if ((this.qtdeChamadosEmAberto == null) || (this.controleIdCliente.get("getQtdeChamadosEmAberto") != idCliente)
				|| ((this.controleIdCliente.get("getQtdeChamadosEmAberto") == idCliente)
						&& (this.controleMesCliente.get("mesCliente") != mesRelatorio))) {
			if ((this.listaChamadoEmAberto == null)
					|| (this.controleIdCliente.get("getListaChamadosEmAbertos") != idCliente)
					|| ((this.controleIdCliente.get("getListaChamadosEmAbertos") == idCliente)
							&& (this.controleMesCliente.get("mesCliente") != mesRelatorio))) {
				getListaChamadosEmAbertos(idCliente, mesRelatorio);
			}
			this.controleIdCliente.put("getQtdeChamadosEmAberto", idCliente);
			this.controleMesCliente.put("controleMesCliente", mesRelatorio);
			this.qtdeChamadosEmAberto = Integer.valueOf(this.listaChamadoEmAberto.size());
		}
		return this.qtdeChamadosEmAberto;
	}

	public Integer getQtdeChamadosAbertosSolicitante(Integer idCliente, String mesRelatorio) {
		if ((this.qtdeChamadosAbertosSolicitante == null)
				|| (this.controleIdCliente.get("getQtdeChamadosAbertosSolicitante") != idCliente)
				|| ((this.controleIdCliente.get("getQtdeChamadosAbertosSolicitante") == idCliente)
						&& (this.controleMesCliente.get("mesCliente") != mesRelatorio))) {
			if ((this.listaChamadoSolicitante == null)
					|| (this.controleIdCliente.get("getListaSolicitantes") != idCliente)
					|| ((this.controleIdCliente.get("getListaSolicitantes") == idCliente)
							&& (this.controleMesCliente.get("mesCliente") != mesRelatorio))) {
				getListaSolicitantes(idCliente, mesRelatorio);
			}
			this.controleIdCliente.put("getQtdeChamadosAbertosSolicitante", idCliente);
			this.controleMesCliente.put("controleMesCliente", mesRelatorio);
		}
		return this.qtdeChamadosAbertosSolicitante;
	}

	public Integer getQtdeChamadosFechadosSolicitante(Integer idCliente, String mesRelatorio) {
		if ((this.qtdeChamadosFechadosSolicitante == null)
				|| (this.controleIdCliente.get("getQtdeChamadosFechadosSolicitante") != idCliente)
				|| ((this.controleIdCliente.get("getQtdeChamadosFechadosSolicitante") == idCliente)
						&& (this.controleMesCliente.get("mesCliente") != mesRelatorio))) {
			if ((this.listaChamadoSolicitante == null)
					|| (this.controleIdCliente.get("getListaSolicitantes") != idCliente)
					|| ((this.controleIdCliente.get("getListaSolicitantes") == idCliente)
							&& (this.controleMesCliente.get("mesCliente") != mesRelatorio))) {
				getListaSolicitantes(idCliente, mesRelatorio);
			}
			this.controleIdCliente.put("getQtdeChamadosFechadosSolicitante", idCliente);
			this.controleMesCliente.put("controleMesCliente", mesRelatorio);
		}
		return this.qtdeChamadosFechadosSolicitante;
	}

	public Integer getQtdeChamadosAbertosTipo(Integer idCliente, String mesRelatorio) {
		if ((this.qtdeChamadosAbertosTipo == null)
				|| (this.controleIdCliente.get("getQtdeChamadosAbertosTipo") != idCliente)
				|| ((this.controleIdCliente.get("getQtdeChamadosAbertosTipo") == idCliente)
						&& (this.controleMesCliente.get("mesCliente") != mesRelatorio))) {
			if ((this.listaChamadoTipo == null) || (this.controleIdCliente.get("getListaChamadoTipo") != idCliente)
					|| ((this.controleIdCliente.get("getListaChamadoTipo") == idCliente)
							&& (this.controleMesCliente.get("mesCliente") != mesRelatorio))) {
				getListaChamadoTipo(idCliente, mesRelatorio);
			}
			this.controleIdCliente.put("getQtdeChamadosAbertosTipo", idCliente);
			this.controleMesCliente.put("controleMesCliente", mesRelatorio);
		}
		return this.qtdeChamadosAbertosTipo;
	}

	public Integer getQtdeChamadosFechadosTipo(Integer idCliente, String mesRelatorio) {
		if ((this.qtdeChamadosFechadosTipo == null)
				|| (this.controleIdCliente.get("getQtdeChamadosFechadosTipo") != idCliente)
				|| ((this.controleIdCliente.get("getQtdeChamadosFechadosTipo") == idCliente)
						&& (this.controleMesCliente.get("mesCliente") != mesRelatorio))) {
			if ((this.listaChamadoTipo == null) || (this.controleIdCliente.get("getListaChamadoTipo") != idCliente)
					|| ((this.controleIdCliente.get("getListaChamadoTipo") == idCliente)
							&& (this.controleMesCliente.get("mesCliente") != mesRelatorio))) {
				getListaChamadoTipo(idCliente, mesRelatorio);
			}
			this.controleIdCliente.put("getQtdeChamadosFechadosTipo", idCliente);
			this.controleMesCliente.put("controleMesCliente", mesRelatorio);
		}
		return this.qtdeChamadosFechadosTipo;
	}

	public Integer getQtdeChamadosAbertosHost(Integer idCliente, String mesRelatorio) {
		if ((this.qtdeChamadosAbertosHost == null)
				|| (this.controleIdCliente.get("getQtdeChamadosAbertosHost") != idCliente)
				|| ((this.controleIdCliente.get("getQtdeChamadosAbertosHost") == idCliente)
						&& (this.controleMesCliente.get("mesCliente") != mesRelatorio))) {
			if ((this.listaChamadosHost == null) || (this.controleIdCliente.get("getListaChamadoHost") != idCliente)
					|| ((this.controleIdCliente.get("getListaChamadoHost") == idCliente)
							&& (this.controleMesCliente.get("mesCliente") != mesRelatorio))) {
				getListaChamadoHost(idCliente, mesRelatorio);
			}
			this.controleIdCliente.put("getQtdeChamadosAbertosHost", idCliente);
			this.controleMesCliente.put("controleMesCliente", mesRelatorio);
		}
		return this.qtdeChamadosAbertosHost;
	}

	public Integer getQtdeChamadosFechadosHost(Integer idCliente, String mesRelatorio) {
		if ((this.qtdeChamadosFechadosHost == null)
				|| (this.controleIdCliente.get("getQtdeChamadosFechadosHost") != idCliente)
				|| ((this.controleIdCliente.get("getQtdeChamadosFechadosHost") == idCliente)
						&& (this.controleMesCliente.get("mesCliente") != mesRelatorio))) {
			if ((this.listaChamadosHost == null) || (this.controleIdCliente.get("getListaChamadoHost") != idCliente)
					|| ((this.controleIdCliente.get("getListaChamadoHost") == idCliente)
							&& (this.controleMesCliente.get("mesCliente") != mesRelatorio))) {
				getListaChamadoHost(idCliente, mesRelatorio);
			}
			this.controleIdCliente.put("getQtdeChamadosFechadosHost", idCliente);
			this.controleMesCliente.put("controleMesCliente", mesRelatorio);
		}
		return this.qtdeChamadosFechadosHost;
	}

	public Double getPorcentagemChamadosAbertos(Integer idCliente, String mesRelatorio) {
		this.porcentagemChamadosAbertos = Double.valueOf(1.0D);
		return this.porcentagemChamadosAbertos;
	}

	public Double getPorcentagemChamadosEmAbertos(Integer idCliente, String mesRelatorio) {
		if ((this.porcentagemChamadosEmAbertos == null)
				|| (this.controleIdCliente.get("getPorcentagemChamadosAbertos") != idCliente)
				|| ((this.controleIdCliente.get("getPorcentagemChamadosAbertos") == idCliente)
						&& (this.controleMesCliente.get("mesCliente") != mesRelatorio))) {
			if ((this.qtdeChamadosAbertos == null)
					|| (this.controleIdCliente.get("getQtdeChamadosAbertos") != idCliente)) {
				getQtdeChamadosAbertos(idCliente, mesRelatorio);
			}
			if ((this.qtdeChamadosEmAberto == null)
					|| (this.controleIdCliente.get("getQtdeChamadosEmAberto") != idCliente)) {
				getQtdeChamadosEmAberto(idCliente, mesRelatorio);
			}
			this.porcentagemChamadosEmAbertos = getPorcentagem(this.qtdeChamadosEmAberto, this.qtdeChamadosAbertos);
		}
		return this.porcentagemChamadosEmAbertos;
	}

	public Double getPorcentagemChamadosFechados(Integer idCliente, String mesRelatorio) {
		if ((this.porcentagemChamadosFechados == null)
				|| (this.controleIdCliente.get("getPorcentagemChamadosAbertos") != idCliente)
				|| ((this.controleIdCliente.get("getPorcentagemChamadosAbertos") == idCliente)
						&& (this.controleMesCliente.get("mesCliente") != mesRelatorio))) {
			if ((this.qtdeChamadosAbertos == null)
					|| (this.controleIdCliente.get("getQtdeChamadosAbertos") != idCliente)) {
				getQtdeChamadosAbertos(idCliente, mesRelatorio);
			}
			if ((this.qtdeChamadosFechados == null)
					|| (this.controleIdCliente.get("getQtdeChamadosFechados") != idCliente)) {
				getQtdeChamadosFechados(idCliente, mesRelatorio);
			}
			this.porcentagemChamadosFechados = getPorcentagem(this.qtdeChamadosFechados, this.qtdeChamadosAbertos);
		}
		return this.porcentagemChamadosFechados;
	}

	public Double getPorcentoAbertosSolicitante(Integer idCliente, String mesRelatorio) {
		if ((this.porcentagemChamadosAbertosSolicitante == null)
				|| (this.controleIdCliente.get("getPorcentoAbertosSolicitante") != idCliente)
				|| ((this.controleIdCliente.get("getPorcentoAbertosSolicitante") == idCliente)
						&& (this.controleMesCliente.get("mesCliente") != mesRelatorio))) {
			if ((this.listaChamadoSolicitante == null)
					|| (this.controleIdCliente.get("getListaSolicitantes") != idCliente)
					|| ((this.controleIdCliente.get("getListaSolicitantes") == idCliente)
							&& (this.controleMesCliente.get("mesCliente") != mesRelatorio))) {
				getListaSolicitantes(idCliente, mesRelatorio);
			}
			this.controleIdCliente.put("getPorcentoAbertosSolicitante", idCliente);
			this.controleMesCliente.put("controleMesCliente", mesRelatorio);
		}
		return this.porcentagemChamadosAbertosSolicitante;
	}

	public Double getPorcentoFechadosSolicitante(Integer idCliente, String mesRelatorio) {
		if ((this.porcentagemChamadosFechadosSolicitante == null)
				|| (this.controleIdCliente.get("getPorcentoFechadosSolicitante") != idCliente)
				|| ((this.controleIdCliente.get("getPorcentoFechadosSolicitante") == idCliente)
						&& (this.controleMesCliente.get("mesCliente") != mesRelatorio))) {
			if ((this.listaChamadoSolicitante == null)
					|| (this.controleIdCliente.get("getListaSolicitantes") != idCliente)
					|| ((this.controleIdCliente.get("getListaSolicitantes") == idCliente)
							&& (this.controleMesCliente.get("mesCliente") != mesRelatorio))) {
				getListaSolicitantes(idCliente, mesRelatorio);
			}
			this.controleIdCliente.put("getPorcentoFechadosSolicitante", idCliente);
			this.controleMesCliente.put("controleMesCliente", mesRelatorio);
		}
		return this.porcentagemChamadosFechadosSolicitante;
	}

	public Double getPorcentoAbertosTipo(Integer idCliente, String mesRelatorio) {
		if ((this.porcentagemChamadosAbertosTipo == null)
				|| (this.controleIdCliente.get("getPorcentoAbertosTipo") != idCliente)
				|| ((this.controleIdCliente.get("getPorcentoAbertosTipo") == idCliente)
						&& (this.controleMesCliente.get("mesCliente") != mesRelatorio))) {
			if ((this.listaChamadoSolicitante == null)
					|| (this.controleIdCliente.get("getListaChamadoTipo") != idCliente)
					|| ((this.controleIdCliente.get("getListaChamadoTipo") == idCliente)
							&& (this.controleMesCliente.get("mesCliente") != mesRelatorio))) {
				getListaChamadoTipo(idCliente, mesRelatorio);
			}
			this.controleIdCliente.put("getPorcentoAbertosTipo", idCliente);
			this.controleMesCliente.put("controleMesCliente", mesRelatorio);
		}
		return this.porcentagemChamadosAbertosTipo;
	}

	public Double getPorcentoFechadosTipo(Integer idCliente, String mesRelatorio) {
		if ((this.porcentagemChamadosFechadosTipo == null)
				|| (this.controleIdCliente.get("getPorcentoFechadosTipo") != idCliente)
				|| ((this.controleIdCliente.get("getPorcentoFechadosTipo") == idCliente)
						&& (this.controleMesCliente.get("mesCliente") != mesRelatorio))) {
			if ((this.listaChamadoSolicitante == null)
					|| (this.controleIdCliente.get("getListaChamadoTipo") != idCliente)
					|| ((this.controleIdCliente.get("getListaChamadoTipo") == idCliente)
							&& (this.controleMesCliente.get("mesCliente") != mesRelatorio))) {
				getListaChamadoTipo(idCliente, mesRelatorio);
			}
			this.controleIdCliente.put("getPorcentoFechadosTipo", idCliente);
			this.controleMesCliente.put("controleMesCliente", mesRelatorio);
		}
		return this.porcentagemChamadosFechadosTipo;
	}

	public Double getPorcentoFechadosComHost(Integer idCliente, String mesRelatorio) {
		if ((this.porcentagemChamadosFechadosHost == null)
				|| (this.controleIdCliente.get("getPorcentoFechadosComHost") != idCliente)
				|| ((this.controleIdCliente.get("getPorcentoFechadosComHost") == idCliente)
						&& (this.controleMesCliente.get("mesCliente") != mesRelatorio))) {
			if ((this.listaChamadoSolicitante == null)
					|| (this.controleIdCliente.get("getListaChamadoHost") != idCliente)
					|| ((this.controleIdCliente.get("getListaChamadoHost") == idCliente)
							&& (this.controleMesCliente.get("mesCliente") != mesRelatorio))) {
				getListaChamadoHost(idCliente, mesRelatorio);
			}
			this.controleIdCliente.put("getPorcentoFechadosComHost", idCliente);
			this.controleMesCliente.put("controleMesCliente", mesRelatorio);
		}
		return this.porcentagemChamadosFechadosHost;
	}

	public Double getPorcentoAbertosComHost(Integer idCliente, String mesRelatorio) {
		if ((this.porcentagemChamadosAbertosHost == null)
				|| (this.controleIdCliente.get("getPorcentoAbertosComHost") != idCliente)
				|| ((this.controleIdCliente.get("getPorcentoAbertosComHost") == idCliente)
						&& (this.controleMesCliente.get("mesCliente") != mesRelatorio))) {
			if ((this.listaChamadoSolicitante == null)
					|| (this.controleIdCliente.get("getListaChamadoHost") != idCliente)
					|| ((this.controleIdCliente.get("getListaChamadoHost") == idCliente)
							&& (this.controleMesCliente.get("mesCliente") != mesRelatorio))) {
				getListaChamadoHost(idCliente, mesRelatorio);
			}
			this.controleIdCliente.put("getPorcentoAbertosComHost", idCliente);
			this.controleMesCliente.put("controleMesCliente", mesRelatorio);
		}
		return this.porcentagemChamadosAbertosHost;
	}

	public Double getTempoMedio(Integer idCliente, String mesRelatorio) {
		if ((this.tempoMedioChamadoFechado == null) || (this.controleIdCliente.get("getTempoMedio") != idCliente)
				|| ((this.controleIdCliente.get("getTempoMedio") == idCliente)
						&& (this.controleMesCliente.get("mesCliente") != mesRelatorio))) {
			if ((this.listaChamadoFechado == null)
					|| (this.controleIdCliente.get("getListaChamadosFechados") != idCliente)) {
				getListaChamadosFechados(idCliente, mesRelatorio);
			}
			if ((this.qtdeChamadosFechados == null)
					|| (this.controleIdCliente.get("getListaChamadosFechados") != idCliente)) {
				getQtdeChamadosFechados(idCliente, mesRelatorio);
			}
			this.controleIdCliente.put("getTempoMedio", idCliente);
			this.controleMesCliente.put("controleMesCliente", mesRelatorio);
			this.tempoMedioChamadoFechado = Double.valueOf(0.0D);

			Iterator<ChamadoVO> itChamado = this.listaChamadoFechado.iterator();

			while (itChamado.hasNext()) {
				ChamadoVO chamado = (ChamadoVO) itChamado.next();

				this.tempoMedioChamadoFechado = Double.valueOf(
						this.tempoMedioChamadoFechado.doubleValue() + chamado.getSegundosTrabalhos().intValue());
			}

			this.tempoMedioChamadoFechado = Double.valueOf(
					this.tempoMedioChamadoFechado.doubleValue() / this.qtdeChamadosFechados.intValue() / 60.0D / 60.0D);
		}

		return this.tempoMedioChamadoFechado;
	}

	public List<ChamadoVO> getListaChamadosAbertos(Integer idCliente, String mesRelatorio) {
		if ((this.listaChamadoAberto == null) || (this.controleIdCliente.get("getListaChamadosAbertos") != idCliente)
				|| ((this.controleIdCliente.get("getListaChamadosAbertos") == idCliente)
						&& (this.controleMesCliente.get("mesCliente") != mesRelatorio))) {
			this.controleIdCliente.put("getListaChamadosAbertos", idCliente);
			this.controleMesCliente.put("controleMesCliente", mesRelatorio);
			this.listaChamadoAberto = this.atendimentoDAO.getChamadosAbertos(idCliente, mesRelatorio);
		}
		return this.listaChamadoAberto;
	}

	public List<ChamadoVO> getListaChamadosFechados(Integer idCliente, String mesRelatorio) {
		if ((this.listaChamadoFechado == null) || (this.controleIdCliente.get("getListaChamadosFechados") != idCliente)
				|| ((this.controleIdCliente.get("getListaChamadosFechados") == idCliente)
						&& (this.controleMesCliente.get("mesCliente") != mesRelatorio))) {
			this.controleIdCliente.put("getListaChamadosFechados", idCliente);
			this.controleMesCliente.put("controleMesCliente", mesRelatorio);
			this.listaChamadoFechado = this.atendimentoDAO.getChamadosFechados(idCliente, mesRelatorio);
		}
		return this.listaChamadoFechado;
	}

	public List<ChamadoVO> getListaChamadosEmAbertos(Integer idCliente, String mesRelatorio) {
		if ((this.listaChamadoEmAberto == null)
				|| (this.controleIdCliente.get("getListaChamadosEmAbertos") != idCliente)
				|| ((this.controleIdCliente.get("getListaChamadosEmAbertos") == idCliente)
						&& (this.controleMesCliente.get("mesCliente") != mesRelatorio))) {
			this.controleIdCliente.put("getListaChamadosEmAbertos", idCliente);
			this.controleMesCliente.put("controleMesCliente", mesRelatorio);
			this.listaChamadoEmAberto = this.atendimentoDAO.getChamadosEmAbertos(idCliente, mesRelatorio);
		}
		return this.listaChamadoEmAberto;
	}

	public List<ChamadoQuantidadeVO> getListaSolicitantes(Integer idCliente, String mesRelatorio) {
		if ((this.listaChamadoSolicitante == null) || (this.controleIdCliente.get("getListaSolicitantes") != idCliente)
				|| ((this.controleIdCliente.get("getListaSolicitantes") == idCliente)
						&& (this.controleMesCliente.get("mesCliente") != mesRelatorio))) {
			if ((this.listaChamadoAberto == null)
					|| (this.controleIdCliente.get("getListaChamadosAbertos") != idCliente)) {
				getListaChamadosAbertos(idCliente, mesRelatorio);
			}
			if ((this.listaChamadoFechado == null)
					|| (this.controleIdCliente.get("getListaChamadosFechados") != idCliente)) {
				getListaChamadosFechados(idCliente, mesRelatorio);
			}
			this.controleIdCliente.put("getListaSolicitantes", idCliente);
			this.controleIdCliente.put("controleMesCliente", idCliente);

			this.listaChamadoSolicitante = new ArrayList<ChamadoQuantidadeVO>();

			Iterator<ChamadoVO> itChamado = this.listaChamadoAberto.iterator();

			while (itChamado.hasNext()) {
				ChamadoVO chamado = (ChamadoVO) itChamado.next();
				Iterator<ChamadoQuantidadeVO> itSolicitante = this.listaChamadoSolicitante.iterator();
				Integer achou = Integer.valueOf(0);
				while (itSolicitante.hasNext()) {
					ChamadoQuantidadeVO solicitante = (ChamadoQuantidadeVO) itSolicitante.next();
					if (((solicitante.getNome() != null) && (solicitante.getNome().equals(chamado.getSolicitante())))
							|| ((solicitante.getNome() == null) && (chamado.getSolicitante() == null))) {
						solicitante.setQtdeAberto(Integer.valueOf(solicitante.getQtdeAberto().intValue() + 1));
						achou = Integer.valueOf(1);
					}
				}
				if (achou.intValue() == 0) {
					ChamadoQuantidadeVO solicitante = new ChamadoQuantidadeVO();
					solicitante.setNome(chamado.getSolicitante());
					solicitante.setQtdeAberto(Integer.valueOf(1));
					solicitante.setQtdeFechado(Integer.valueOf(0));
					solicitante.setQtdeEmAberto(Integer.valueOf(0));
					this.listaChamadoSolicitante.add(solicitante);
				}
			}

			itChamado = this.listaChamadoFechado.iterator();
			while (itChamado.hasNext()) {
				ChamadoVO chamado = (ChamadoVO) itChamado.next();
				Iterator<ChamadoQuantidadeVO> itSolicitante = this.listaChamadoSolicitante.iterator();
				Integer achou = Integer.valueOf(0);
				while (itSolicitante.hasNext()) {
					ChamadoQuantidadeVO solicitante = (ChamadoQuantidadeVO) itSolicitante.next();
					if (((solicitante.getNome() != null) && (solicitante.getNome().equals(chamado.getSolicitante())))
							|| ((solicitante.getNome() == null) && (chamado.getSolicitante() == null))) {
						solicitante.setQtdeFechado(Integer.valueOf(solicitante.getQtdeFechado().intValue() + 1));
						achou = Integer.valueOf(1);
					}
				}
				if (achou.intValue() == 0) {
					ChamadoQuantidadeVO solicitante = new ChamadoQuantidadeVO();
					solicitante.setNome(chamado.getSolicitante());
					solicitante.setQtdeAberto(Integer.valueOf(0));
					solicitante.setQtdeFechado(Integer.valueOf(1));
					solicitante.setQtdeEmAberto(Integer.valueOf(0));
					this.listaChamadoSolicitante.add(solicitante);
				}
			}
			Integer aberto = Integer.valueOf(0);
			Integer fechado = Integer.valueOf(0);
			Iterator<ChamadoQuantidadeVO> itSolicitante = this.listaChamadoSolicitante.iterator();
			while (itSolicitante.hasNext()) {
				ChamadoQuantidadeVO solicitante = (ChamadoQuantidadeVO) itSolicitante.next();
				aberto = Integer.valueOf(aberto.intValue() + solicitante.getQtdeAberto().intValue());
				fechado = Integer.valueOf(fechado.intValue() + solicitante.getQtdeFechado().intValue());
			}

			setQtdeChamadosAbertosSolicitante(aberto);
			setQtdeChamadosFechadosSolicitante(fechado);

			Double porcentagemAberto = Double.valueOf(0.0D);
			Double porcentagemFechado = Double.valueOf(0.0D);
			itSolicitante = this.listaChamadoSolicitante.iterator();
			while (itSolicitante.hasNext()) {
				ChamadoQuantidadeVO solicitante = (ChamadoQuantidadeVO) itSolicitante.next();
				solicitante.setPorcentoAberto(getPorcentagem(solicitante.getQtdeAberto(), aberto));
				solicitante.setPorcentoFechado(getPorcentagem(solicitante.getQtdeFechado(), fechado));

				porcentagemAberto = Double
						.valueOf(porcentagemAberto.doubleValue() + solicitante.getPorcentoAberto().doubleValue());

				porcentagemFechado = Double
						.valueOf(porcentagemFechado.doubleValue() + solicitante.getPorcentoFechado().doubleValue());
			}

			setPorcentagemChamadosAbertosSolicitante(porcentagemAberto);
			setPorcentagemChamadosFechadosSolicitante(porcentagemFechado);
		}

		List<ChamadoQuantidadeVO> orderArrayList = this.listaChamadoSolicitante;

		extracted(orderArrayList);
		return this.listaChamadoSolicitante;
	}

	private void extracted(List<ChamadoQuantidadeVO> orderArrayList) {
		Collections.sort(orderArrayList, new Comparator<Object>() {
			@SuppressWarnings("unused")
			public int compare(ChamadoQuantidadeVO o1, ChamadoQuantidadeVO o2) {
				return o2.getQtdeAberto().compareTo(o1.getQtdeAberto());
			}

			@Override
			public int compare(Object o1, Object o2) {
				// TODO Auto-generated method stub
				return 0;
			}
		});
	}

	public List<ChamadoQuantidadeVO> getListaChamadoTipo(Integer idCliente, String mesRelatorio) {
		if ((this.listaChamadoTipo == null) || (this.controleIdCliente.get("getListaChamadoTipo") != idCliente)
				|| ((this.controleIdCliente.get("getListaChamadoTipo") == idCliente)
						&& (this.controleMesCliente.get("mesCliente") != mesRelatorio))) {
			if ((this.listaChamadoAberto == null) || (this.controleIdCliente.get("getListaChamadoTipo") != idCliente)) {
				this.atendimentoDAO.getChamadosAbertos(idCliente, mesRelatorio);
			}
			if ((this.listaChamadoFechado == null)
					|| (this.controleIdCliente.get("getListaChamadoTipo") != idCliente)) {
				this.atendimentoDAO.getChamadosFechados(idCliente, mesRelatorio);
			}
			this.controleIdCliente.put("getListaChamadoTipo", idCliente);
			this.controleMesCliente.put("controleMesCliente", mesRelatorio);
			this.listaChamadoTipo = new ArrayList<ChamadoQuantidadeVO>();

			Iterator<ChamadoVO> itChamado = this.listaChamadoAberto.iterator();

			while (itChamado.hasNext()) {
				ChamadoVO chamado = (ChamadoVO) itChamado.next();
				Iterator<ChamadoQuantidadeVO> itTipoChamado = this.listaChamadoTipo.iterator();
				Integer achou = Integer.valueOf(0);
				while (itTipoChamado.hasNext()) {
					ChamadoQuantidadeVO tipoChamados = (ChamadoQuantidadeVO) itTipoChamado.next();
					if (tipoChamados.getNome().equals(chamado.getTipoChamado())) {
						tipoChamados.setQtdeAberto(Integer.valueOf(tipoChamados.getQtdeAberto().intValue() + 1));
						achou = Integer.valueOf(1);
					}
				}
				if (achou.intValue() == 0) {
					ChamadoQuantidadeVO tipoChamados = new ChamadoQuantidadeVO();
					tipoChamados.setNome(chamado.getTipoChamado());
					tipoChamados.setQtdeAberto(Integer.valueOf(1));
					tipoChamados.setQtdeFechado(Integer.valueOf(0));
					tipoChamados.setQtdeEmAberto(Integer.valueOf(0));
					this.listaChamadoTipo.add(tipoChamados);
				}
			}

			itChamado = this.listaChamadoFechado.iterator();
			while (itChamado.hasNext()) {
				ChamadoVO chamado = (ChamadoVO) itChamado.next();
				Iterator<ChamadoQuantidadeVO> itTipoChamado = this.listaChamadoTipo.iterator();
				Integer achou = Integer.valueOf(0);
				while (itTipoChamado.hasNext()) {
					ChamadoQuantidadeVO tipoChamados = (ChamadoQuantidadeVO) itTipoChamado.next();
					if (tipoChamados.getNome().equals(chamado.getTipoChamado())) {
						tipoChamados.setQtdeFechado(Integer.valueOf(tipoChamados.getQtdeFechado().intValue() + 1));
						achou = Integer.valueOf(1);
					}
				}
				if (achou.intValue() == 0) {
					ChamadoQuantidadeVO tipoChamados = new ChamadoQuantidadeVO();
					tipoChamados.setNome(chamado.getTipoChamado());
					tipoChamados.setQtdeAberto(Integer.valueOf(0));
					tipoChamados.setQtdeFechado(Integer.valueOf(1));
					tipoChamados.setQtdeEmAberto(Integer.valueOf(0));
					this.listaChamadoTipo.add(tipoChamados);
				}
			}

			Integer aberto = Integer.valueOf(0);
			Integer fechado = Integer.valueOf(0);
			Iterator<ChamadoQuantidadeVO> itTipoChamado = this.listaChamadoTipo.iterator();
			while (itTipoChamado.hasNext()) {
				ChamadoQuantidadeVO tipoChamados = (ChamadoQuantidadeVO) itTipoChamado.next();
				aberto = Integer.valueOf(aberto.intValue() + tipoChamados.getQtdeAberto().intValue());
				fechado = Integer.valueOf(fechado.intValue() + tipoChamados.getQtdeFechado().intValue());
			}

			setQtdeChamadosAbertosTipo(aberto);
			setQtdeChamadosFechadosTipo(fechado);

			Double porcentagemAberto = Double.valueOf(0.0D);
			Double porcentagemFechado = Double.valueOf(0.0D);
			itTipoChamado = this.listaChamadoTipo.iterator();
			while (itTipoChamado.hasNext()) {
				ChamadoQuantidadeVO tipoChamados = (ChamadoQuantidadeVO) itTipoChamado.next();
				tipoChamados.setPorcentoAberto(getPorcentagem(tipoChamados.getQtdeAberto(), aberto));
				tipoChamados.setPorcentoFechado(getPorcentagem(tipoChamados.getQtdeFechado(), fechado));

				porcentagemAberto = Double
						.valueOf(porcentagemAberto.doubleValue() + tipoChamados.getPorcentoAberto().doubleValue());

				porcentagemFechado = Double
						.valueOf(porcentagemFechado.doubleValue() + tipoChamados.getPorcentoFechado().doubleValue());
			}
			setPorcentagemChamadosAbertosTipo(porcentagemAberto);
			setPorcentagemChamadosFechadosTipo(porcentagemFechado);
		}
		return this.listaChamadoTipo;
	}

	public List<String> getListaChamadoAbertosHost(Integer idCliente, String mesRelatorio) {
		if ((this.listaAbertosHost == null) || (this.controleIdCliente.get("getListaChamadoAbertosHost") != idCliente)
				|| ((this.controleIdCliente.get("getListaChamadoAbertosHost") == idCliente)
						&& (this.controleMesCliente.get("mesCliente") != mesRelatorio))) {
			this.controleIdCliente.put("getListaChamadoAbertosHost", idCliente);
			this.controleMesCliente.put("controleMesCliente", mesRelatorio);
			this.listaAbertosHost = this.atendimentoDAO.getListaAbertosComHosts(idCliente, mesRelatorio);
		}
		return this.listaAbertosHost;
	}

	public List<String> getListaChamadoFechadosHost(Integer idCliente, String mesRelatorio) {
		if ((this.listaFechadosHost == null) || (this.controleIdCliente.get("getListaChamadoFechadosHost") != idCliente)
				|| ((this.controleIdCliente.get("getListaChamadoAbertosHost") == idCliente)
						&& (this.controleMesCliente.get("mesCliente") != mesRelatorio))) {
			this.controleIdCliente.put("getListaChamadoFechadosHost", idCliente);
			this.controleMesCliente.put("controleMesCliente", mesRelatorio);
			this.listaFechadosHost = this.atendimentoDAO.getListaFechadosComHosts(idCliente, mesRelatorio);
		}
		return this.listaAbertosHost;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<ChamadoQuantidadeVO> getListaChamadoHost(Integer idCliente, String mesRelatorio) {
		if ((this.listaChamadosHost == null) || (this.controleIdCliente.get("getListaChamadoHost") != idCliente)
				|| ((this.controleIdCliente.get("getListaChamadoHost") == idCliente)
						&& (this.controleMesCliente.get("mesCliente") != mesRelatorio))) {
			if ((this.listaAbertosHost == null)
					|| (this.controleIdCliente.get("getListaChamadoAbertosHost") != idCliente)) {
				getListaChamadoAbertosHost(idCliente, mesRelatorio);
			}
			if ((this.listaFechadosHost == null)
					|| (this.controleIdCliente.get("getListaChamadoFechadosHost") != idCliente)) {
				getListaChamadoFechadosHost(idCliente, mesRelatorio);
			}
			this.controleIdCliente.put("getListaChamadoHost", idCliente);
			this.controleMesCliente.put("controleMesCliente", mesRelatorio);
			this.listaChamadosHost = new ArrayList();

			Iterator<String> itHost = this.listaAbertosHost.iterator();

			while (itHost.hasNext()) {
				String host = (String) itHost.next();
				Iterator<ChamadoQuantidadeVO> itHostList = this.listaChamadosHost.iterator();
				Integer achou = Integer.valueOf(0);
				while (itHostList.hasNext()) {
					ChamadoQuantidadeVO hostVO = (ChamadoQuantidadeVO) itHostList.next();
					if (hostVO.getNome().equals(host)) {
						hostVO.setQtdeAberto(Integer.valueOf(hostVO.getQtdeAberto().intValue() + 1));
						achou = Integer.valueOf(1);
					}
				}
				if (achou.intValue() == 0) {
					ChamadoQuantidadeVO hostVO = new ChamadoQuantidadeVO();
					hostVO.setNome(host);
					hostVO.setQtdeAberto(Integer.valueOf(1));
					hostVO.setQtdeFechado(Integer.valueOf(0));
					hostVO.setQtdeEmAberto(Integer.valueOf(0));
					this.listaChamadosHost.add(hostVO);
				}
			}

			itHost = this.listaFechadosHost.iterator();
			while (itHost.hasNext()) {
				String host = (String) itHost.next();
				Iterator<ChamadoQuantidadeVO> itHostList = this.listaChamadosHost.iterator();
				Integer achou = Integer.valueOf(0);
				while (itHostList.hasNext()) {
					ChamadoQuantidadeVO hostVO = (ChamadoQuantidadeVO) itHostList.next();
					if (hostVO.getNome().equals(host)) {
						hostVO.setQtdeFechado(Integer.valueOf(hostVO.getQtdeFechado().intValue() + 1));
						achou = Integer.valueOf(1);
					}
				}
				if (achou.intValue() == 0) {
					ChamadoQuantidadeVO hostVO = new ChamadoQuantidadeVO();
					hostVO.setNome(host);
					hostVO.setQtdeAberto(Integer.valueOf(0));
					hostVO.setQtdeFechado(Integer.valueOf(1));
					hostVO.setQtdeEmAberto(Integer.valueOf(0));
					this.listaChamadosHost.add(hostVO);
				}
			}

			Integer aberto = Integer.valueOf(0);
			Integer fechado = Integer.valueOf(0);
			List<String> hostGraficoAberto = new ArrayList();
			List<String> hostGraficoFechado = new ArrayList();
			Iterator<ChamadoQuantidadeVO> itHostList = this.listaChamadosHost.iterator();
			while (itHostList.hasNext()) {
				ChamadoQuantidadeVO hostVO = (ChamadoQuantidadeVO) itHostList.next();
				aberto = Integer.valueOf(aberto.intValue() + hostVO.getQtdeAberto().intValue());
				fechado = Integer.valueOf(fechado.intValue() + hostVO.getQtdeFechado().intValue());
				hostGraficoAberto.add("{label: \"" + hostVO.getNome() + "\",  data: " + hostVO.getQtdeAberto() + "}");
				hostGraficoFechado.add("{label: \"" + hostVO.getNome() + "\",  data: " + hostVO.getQtdeFechado() + "}");
			}
			setListaGraficoAbertosHost(hostGraficoAberto);
			setListaGraficoFechadosHost(hostGraficoFechado);

			setQtdeChamadosAbertosHost(aberto);
			setQtdeChamadosFechadosHost(fechado);

			Double porcentagemAberto = Double.valueOf(0.0D);
			Double porcentagemFechado = Double.valueOf(0.0D);
			itHostList = this.listaChamadosHost.iterator();
			while (itHostList.hasNext()) {
				ChamadoQuantidadeVO hostVO = (ChamadoQuantidadeVO) itHostList.next();
				hostVO.setPorcentoAberto(getPorcentagem(hostVO.getQtdeAberto(), aberto));
				hostVO.setPorcentoFechado(getPorcentagem(hostVO.getQtdeFechado(), fechado));

				porcentagemAberto = Double
						.valueOf(porcentagemAberto.doubleValue() + hostVO.getPorcentoAberto().doubleValue());

				porcentagemFechado = Double
						.valueOf(porcentagemFechado.doubleValue() + hostVO.getPorcentoFechado().doubleValue());
			}
			setPorcentagemChamadosAbertosHost(porcentagemAberto);
			setPorcentagemChamadosFechadosHost(porcentagemFechado);
		}

		List<ChamadoQuantidadeVO> orderArrayList = this.listaChamadosHost;

		Collections.sort(orderArrayList, new Comparator() {
			@SuppressWarnings("unused")
			public int compare(ChamadoQuantidadeVO o1, ChamadoQuantidadeVO o2) {
				return o2.getQtdeAberto().compareTo(o1.getQtdeAberto());
			}

			@Override
			public int compare(Object o1, Object o2) {
				// TODO Auto-generated method stub
				return 0;
			}
		});
		return this.listaChamadosHost;
	}

	public String getGraficoFechadosHost(Integer idCliente, String mesRelatorio) {
		String saida = new String();
		if ((this.listaGraficoFechadosHost == null)
				|| (this.controleIdCliente.get("getGraficoFechadosHost") != idCliente)
				|| ((this.controleIdCliente.get("getGraficoFechadosHost") == idCliente)
						&& (this.controleMesCliente.get("mesCliente") != mesRelatorio))) {
			getListaChamadoHost(idCliente, mesRelatorio);
			this.controleIdCliente.put("getGraficoFechadosHost", idCliente);
			this.controleMesCliente.put("controleMesCliente", mesRelatorio);
		}
		Iterator<String> itHost = this.listaGraficoFechadosHost.iterator();
		saida = "[";
		while (itHost.hasNext()) {
			String host = (String) itHost.next();
			saida = saida + host + ",";
		}
		saida = saida + "]";
		return saida;
	}

	public String getGraficoAbertosHost(Integer idCliente, String mesRelatorio) {
		String saida = new String();
		if ((this.listaGraficoAbertosHost == null) || (this.controleIdCliente.get("getGraficoAbertosHost") != idCliente)
				|| ((this.controleIdCliente.get("getGraficoAbertosHost") == idCliente)
						&& (this.controleMesCliente.get("mesCliente") != mesRelatorio))) {
			getListaChamadoHost(idCliente, mesRelatorio);
			this.controleIdCliente.put("getGraficoAbertosHost", idCliente);
			this.controleMesCliente.put("controleMesCliente", mesRelatorio);
		}
		Iterator<String> itHost = this.listaGraficoAbertosHost.iterator();
		saida = "[";
		while (itHost.hasNext()) {
			String host = (String) itHost.next();
			saida = saida + host + ",";
		}
		saida = saida + "]";
		return saida;
	}

	public Double getPorcentagemChamadosAbertosTipo(Integer idCliente, String mesRelatorio) {
		Double porcentagem = Double.valueOf(0.0D);

		if ((this.porcentagemChamadosAbertosTipo == null)
				|| (this.controleIdCliente.get("getPorcentagemChamadosAbertosTipo") != idCliente)
				|| ((this.controleIdCliente.get("getPorcentagemChamadosAbertosTipo") == idCliente)
						&& (this.controleMesCliente.get("mesCliente") != mesRelatorio))) {
			this.controleIdCliente.put("getPorcentagemChamadosAbertosTipo", idCliente);
			this.controleMesCliente.put("controleMesCliente", mesRelatorio);
			if ((this.listaChamadoTipo == null) || (this.controleIdCliente.get("getListaChamadoTipo") != idCliente)
					|| ((this.controleIdCliente.get("getListaChamadoTipo") == idCliente)
							&& (this.controleMesCliente.get("mesCliente") != mesRelatorio))) {
				getListaChamadoTipo(idCliente, mesRelatorio);
			}
			Iterator<ChamadoQuantidadeVO> itTipoChamado = this.listaChamadoTipo.iterator();

			while (itTipoChamado.hasNext()) {
				ChamadoQuantidadeVO tipoChamados = (ChamadoQuantidadeVO) itTipoChamado.next();
				porcentagem = Double
						.valueOf(porcentagem.doubleValue() + tipoChamados.getPorcentoAberto().doubleValue());
			}
		}
		return porcentagem;
	}

	public Double getPorcentagemChamadosFechadosTipo(Integer idCliente, String mesRelatorio) {
		Double porcentagem = Double.valueOf(0.0D);

		if ((this.porcentagemChamadosFechadosTipo == null)
				|| (this.controleIdCliente.get("getPorcentagemChamadosFechadosTipo") != idCliente)
				|| ((this.controleIdCliente.get("getPorcentagemChamadosFechadosTipo") == idCliente)
						&& (this.controleMesCliente.get("mesCliente") != mesRelatorio))) {
			this.controleIdCliente.put("getPorcentagemChamadosFechadosTipo", idCliente);
			this.controleMesCliente.put("controleMesCliente", mesRelatorio);
			if ((this.listaChamadoTipo == null) || (this.controleIdCliente.get("getListaChamadoTipo") != idCliente)
					|| ((this.controleIdCliente.get("getListaChamadoTipo") == idCliente)
							&& (this.controleMesCliente.get("mesCliente") != mesRelatorio))) {
				getListaChamadoTipo(idCliente, mesRelatorio);
			}
			Iterator<ChamadoQuantidadeVO> itTipoChamado = this.listaChamadoTipo.iterator();

			while (itTipoChamado.hasNext()) {
				ChamadoQuantidadeVO tipoChamados = (ChamadoQuantidadeVO) itTipoChamado.next();
				porcentagem = Double
						.valueOf(porcentagem.doubleValue() + tipoChamados.getPorcentoFechado().doubleValue());
			}
		}
		return porcentagem;
	}

	private void setQtdeChamadosAbertosSolicitante(Integer qtdeChamadosAbertosSolicitante) {
		this.qtdeChamadosAbertosSolicitante = qtdeChamadosAbertosSolicitante;
	}

	private void setQtdeChamadosFechadosSolicitante(Integer qtdeChamadosFechadosSolicitante) {
		this.qtdeChamadosFechadosSolicitante = qtdeChamadosFechadosSolicitante;
	}

	private void setPorcentagemChamadosAbertosSolicitante(Double porcentagemChamadosAbertosSolicitante) {
		this.porcentagemChamadosAbertosSolicitante = porcentagemChamadosAbertosSolicitante;
	}

	private void setPorcentagemChamadosFechadosSolicitante(Double porcentagemChamadosFechadosSolicitante) {
		this.porcentagemChamadosFechadosSolicitante = porcentagemChamadosFechadosSolicitante;
	}

	private void setPorcentagemChamadosAbertosTipo(Double porcentagemChamadosAbertosTipo) {
		this.porcentagemChamadosAbertosTipo = porcentagemChamadosAbertosTipo;
	}

	private void setPorcentagemChamadosFechadosTipo(Double porcentagemChamadosFechadosTipo) {
		this.porcentagemChamadosFechadosTipo = porcentagemChamadosFechadosTipo;
	}

	private void setQtdeChamadosAbertosTipo(Integer qtdeChamadosAbertosTipo) {
		this.qtdeChamadosAbertosTipo = qtdeChamadosAbertosTipo;
	}

	private void setQtdeChamadosFechadosTipo(Integer qtdeChamadosFechadosTipo) {
		this.qtdeChamadosFechadosTipo = qtdeChamadosFechadosTipo;
	}

	private void setPorcentagemChamadosAbertosHost(Double porcentagemChamadosAbertosHost) {
		this.porcentagemChamadosAbertosHost = porcentagemChamadosAbertosHost;
	}

	private void setPorcentagemChamadosFechadosHost(Double porcentagemChamadosFechadosHost) {
		this.porcentagemChamadosFechadosHost = porcentagemChamadosFechadosHost;
	}

	private void setQtdeChamadosFechadosHost(Integer qtdeChamadosFechadosHost) {
		this.qtdeChamadosFechadosHost = qtdeChamadosFechadosHost;
	}

	private void setQtdeChamadosAbertosHost(Integer qtdeChamadosAbertosHost) {
		this.qtdeChamadosAbertosHost = qtdeChamadosAbertosHost;
	}

	private void setListaGraficoAbertosHost(List<String> listaGraficoAbertosHost) {
		this.listaGraficoAbertosHost = listaGraficoAbertosHost;
	}

	private void setListaGraficoFechadosHost(List<String> listaGraficoFechadosHost) {
		this.listaGraficoFechadosHost = listaGraficoFechadosHost;
	}
}