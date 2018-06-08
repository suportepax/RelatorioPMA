package br.com.paxtecnologia.pma.relatorio.ejb;

import java.util.List;

import javax.ejb.Stateless;

import br.com.paxtecnologia.pma.relatorio.dao.VpnDAO;
import br.com.paxtecnologia.pma.relatorio.vo2.AcessoUsuario;
import br.com.paxtecnologia.pma.relatorio.vo2.KeyValue;

@Stateless
public class VpnEJB {
	
	private final VpnDAO vpnDAO = new VpnDAO();
	
	public AcessoUsuario acessoUsuario(Integer projetoJiraId, String mesRelatorio) {
		return vpnDAO.acessoUsuario(projetoJiraId, mesRelatorio);
	}
	
	public List<KeyValue> acessoUsuariosDia() {
		return vpnDAO.acessoUsuariosDia();
	}
	
	public List<KeyValue> segundosConectadosTop10() {
		return vpnDAO.segundosConectadosTop10();
	}
	
	public List<KeyValue> downloadTop10() {
		return vpnDAO.downloadTop10();
	}
	
	public List<KeyValue> uploadTop10() {
		return vpnDAO.uploadTop10();
	}
	
	public Integer segundosConectados() {
		return vpnDAO.segundosConectados();
	}
	
	public Double download() {
		return vpnDAO.download();
	}
	
	public Double upload() {
		return vpnDAO.upload();
	}

}
