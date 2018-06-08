package br.com.paxtecnologia.pma.relatorio.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ObservacaoDAO {
	private DataSourcePMA connection;

	public String getObservacao(Integer idCliente, String mesRelatorio) {
		String retorno = null;
		connection = new DataSourcePMA();
		PreparedStatement pstmt = null;
		try {
			String sql = "select observacao from rel_observacao " + "where projeto_jira_id = ? " + "and data = ?";
			pstmt = connection.getPreparedStatement(sql);
			pstmt.setInt(1, idCliente);
			pstmt.setString(2, mesRelatorio);
			ResultSet rs = connection.executaQuery(pstmt);
			try {
				while (rs.next()) {
					retorno = rs.getString(1);
				}
			} catch (SQLException e) {
				e.getMessage();
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return retorno;
	}

	public void salvarObservacao(Integer idCliente, String mesRelatorio, String observacao) throws SQLException {
		connection = new DataSourcePMA();
		PreparedStatement pstmt = null;
		try {
			String sql = "INSERT INTO rel_observacao (projeto_jira_id, data, observacao) " + "VALUES (?, ?, ?)";
			pstmt = connection.getPreparedStatement(sql);
			pstmt.setInt(1, idCliente);
			pstmt.setString(2, mesRelatorio);
			pstmt.setString(3, observacao);
			connection.executaSave(pstmt);
		} catch (SQLException e) {
			String errorCode = e.getMessage();
			if (errorCode.equals("23000")) {
				updateObservacao(idCliente, mesRelatorio, observacao);
			} else {
				throw new SQLException(e);
			}
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					throw new SQLException(e);
				}
			}
		}
	}

	public void updateObservacao(Integer idCliente, String mesRelatorio, String observacao) throws SQLException {
		connection = new DataSourcePMA();
		PreparedStatement pstmt = null;
		try {
			String sql = "UPDATE rel_observacao SET observacao = ? WHERE projeto_jira_id = ? AND data = ?";
			pstmt = connection.getPreparedStatement(sql);
			pstmt.setString(1, observacao);
			pstmt.setInt(2, idCliente);
			pstmt.setString(3, mesRelatorio);
			connection.executaUpdate(pstmt);
		} catch (SQLException e) {
			throw new SQLException(e);
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					throw new SQLException(e);
				}
			}
		}
	}
}
