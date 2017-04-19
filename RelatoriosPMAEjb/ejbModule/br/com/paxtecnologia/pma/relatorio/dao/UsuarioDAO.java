package br.com.paxtecnologia.pma.relatorio.dao;

import br.com.paxtecnologia.pma.relatorio.vo.UsuarioVO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {
	private DataSourcePMA connection;
	private UsuarioVO usuario;

	public UsuarioVO getUsuario(String username) {
		try {
			this.usuario = new UsuarioVO();

			this.usuario.setUsername(getPassword(username));
			this.usuario.setUserRole(getRole(username));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return this.usuario;
	}

	public String getPassword(String username) {
		String retorno = null;
		this.connection = new DataSourcePMA();

		String sql = "SELECT password FROM pmp_users WHERE name = ?";
		PreparedStatement pstmt = this.connection.getPreparedStatement(sql);
		try {
			pstmt.setString(1, username);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ResultSet rs = this.connection.executaQuery(pstmt);
		try {
			while (rs.next()) {
				retorno = rs.getString("password");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.connection.closeConnection(pstmt);
		return retorno;
	}

	public String getRole(String username) {
		String retorno = null;
		this.connection = new DataSourcePMA();

		String sql = "SELECT role_name FROM pmp_user_roles WHERE user_name = ?";
		PreparedStatement pstmt = this.connection.getPreparedStatement(sql);
		try {
			pstmt.setString(1, username);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ResultSet rs = this.connection.executaQuery(pstmt);
		try {
			while (rs.next()) {
				retorno = rs.getString("role_name");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.connection.closeConnection(pstmt);
		return retorno;
	}
}