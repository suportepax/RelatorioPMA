package br.com.paxtecnologia.pma.relatorio.ejb;

import br.com.paxtecnologia.pma.relatorio.dao.RelatorioDAO;
import br.com.paxtecnologia.pma.relatorio.vo.MesRelatorioVO;
import br.com.paxtecnologia.pma.relatorio.vo2.RelatorioVO;
import java.util.List;
import javax.ejb.Stateless;

@Stateless
public class RelatorioEjb {
	private RelatorioDAO relatorioDAO = new RelatorioDAO();
	private List<RelatorioVO> listaRelatorios;
	private List<MesRelatorioVO> listaMesRelatorio;

	public List<RelatorioVO> getListaRelatorioMenu() {
		this.listaRelatorios = this.relatorioDAO.getListaRelatorioMenu();

		return this.listaRelatorios;
	}

	public List<MesRelatorioVO> getListaMes(Integer relatorioId) {
		this.listaMesRelatorio = this.relatorioDAO.getListaMes(relatorioId);

		return this.listaMesRelatorio;
	}

	public RelatorioVO getRelatorioById(Integer relatorioId, String mesRelatorio) {
		return this.relatorioDAO.getRelatorioById(relatorioId, mesRelatorio);
	}

	public String getLogoStr(Integer relatorioId) {
		return this.relatorioDAO.getLogoStr(relatorioId);
	}

	public String getClienteDisplayName(Integer relatorioId) {
		return this.relatorioDAO.getClienteDisplayName(relatorioId);
	}

	public String getTituloRelatorio(Integer relatorioId) {
		return this.relatorioDAO.getTituloRelatorio(relatorioId);
	}

	public Integer getProjetoJiraIdByRelatorioId(Integer relatorioId) {
		return this.relatorioDAO.getProjetoJiraIdByRelatorioId(relatorioId);
	}

	public Integer getTipoRelatorioIdByRelatorioId(Integer relatorioId) {
		return this.relatorioDAO.getTipoRelatorioIdByRelatorioId(relatorioId);
	}
}
