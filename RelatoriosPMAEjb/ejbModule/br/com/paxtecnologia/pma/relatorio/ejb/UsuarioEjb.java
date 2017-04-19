package br.com.paxtecnologia.pma.relatorio.ejb;

import br.com.paxtecnologia.pma.relatorio.dao.UsuarioDAO;
import br.com.paxtecnologia.pma.relatorio.vo.UsuarioVO;
import javax.ejb.Stateless;

@Stateless
public class UsuarioEjb {
	private UsuarioDAO usuarioDAO = new UsuarioDAO();
	private UsuarioVO usuarioVO;

	public UsuarioVO getUsuario(String username) {
		this.usuarioVO = this.usuarioDAO.getUsuario(username);

		return this.usuarioVO;
	}
}
