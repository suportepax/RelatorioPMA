package br.com.paxtecnologia.pma.relatorio.ejb;

import br.com.paxtecnologia.pma.relatorio.dao.UsuarioDAO;
import br.com.paxtecnologia.pma.relatorio.vo.UsuarioVO;

public class UsuarioEjb {
	
	private UsuarioDAO usuarioDAO = new UsuarioDAO();
	private UsuarioVO usuarioVO;

	public UsuarioVO getUsuario(String username){
		usuarioVO = usuarioDAO.getUsuario(username);

		return usuarioVO;

	}	
	
}