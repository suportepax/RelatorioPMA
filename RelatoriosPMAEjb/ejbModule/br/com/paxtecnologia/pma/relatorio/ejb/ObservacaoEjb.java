package br.com.paxtecnologia.pma.relatorio.ejb;

import javax.ejb.Stateless;

import br.com.paxtecnologia.pma.relatorio.dao.ObservacaoDAO;

@Stateless
public class ObservacaoEjb {
	private final ObservacaoDAO observacaoDAO = new ObservacaoDAO();
	
	public void salvar(Integer idCliente, String mesRelatorio, String observacao) {
		try {
			observacaoDAO.salvarObservacao(idCliente, mesRelatorio, observacao);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
