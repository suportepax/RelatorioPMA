package br.com.paxtecnologia.pma.relatorio.ejb;

import java.sql.SQLException;

import br.com.paxtecnologia.pma.relatorio.dao.ObservacaoDAO;

public class ObservacaoEJB {
	private final ObservacaoDAO observacaoDAO = new ObservacaoDAO();
	
	
	public String getObservacao(Integer idCliente, String mesRelatorio) {
		String retorno = null;
		try {
			retorno = observacaoDAO.getObservacao(idCliente, mesRelatorio);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retorno;
	}


	public Boolean salvarObservacao(Integer idCliente, String mesRelatorio, String observacao) {
		Boolean retorno = false;
		Boolean error = false;
		try {
			observacaoDAO.salvarObservacao(idCliente, mesRelatorio, observacao);
		} catch (SQLException e) {
			error = true;
			e.getCause();
			e.printStackTrace();
		} catch(Exception e) {
			error = true;
			e.getCause();
			e.printStackTrace();
		} finally {
			if (!error) {
				retorno = true;
			}
		}
		return retorno;
	}


	public Boolean updateObservacao(Integer idCliente, String mesRelatorio, String observacao) {
		Boolean retorno = false;
		Boolean error = false;
		try {
			observacaoDAO.updateObservacao(idCliente, mesRelatorio, observacao);
		} catch (SQLException e) {
			error = true;
			e.printStackTrace();
		} catch(Exception e) { 
			error = true;
			e.printStackTrace();
		} finally {
			if (!error) {
				retorno = true;
			}
		}
		return retorno;
	}
}