package br.com.paxtecnologia.pma.relatorio.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ObservacaoDAO {
	private DataSourceObservacoes connection;
	
	public void salvarObservacao(Integer idCliente, String mesRelatorio, String observacao) {
		PreparedStatement pstmt;
		String insertSQL = "INSERT INTO observacoes (idCliente, mesRelatorio, observacao)"
				+ "VALUES (?, ?, ?)";
		pstmt = connection.getPreparedStatement(insertSQL);
		try {
			pstmt.setInt(1, idCliente);
			pstmt.setString(2, mesRelatorio);
			pstmt.setString(3, observacao);
			connection.executaQuery(pstmt);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connection.closeConnection(pstmt);
		}
	}
}
