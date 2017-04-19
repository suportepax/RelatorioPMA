package br.com.paxtecnologia.pma.relatorio.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DataSourceObservacoes {

	protected static DataSource dataSource;
	private Connection conn;

	DataSourceObservacoes() {
		conn = pegaConexao();
	}

	private Connection pegaConexao() {
		Connection con = null;
		Context ic;
		try {
			ic = new InitialContext();
			dataSource = (DataSource) ic.lookup("java:jboss/datasource/ObservacoesPMA");
			try {
				con = dataSource.getConnection();
			} catch (SQLException e) {
				System.out.println(
						"Não conseguiu conectar no banco. Exception: " + e.getErrorCode() + " " + e.getMessage());
				e.printStackTrace();
			}
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return con;
	}

	public PreparedStatement getPreparedStatement(String sql) {
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pstmt;
	}

	public ResultSet executaQuery(PreparedStatement pstmt) {
		ResultSet rs = null;
		try {
			rs = pstmt.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;

	}

	public Boolean closeConnection(PreparedStatement pstmt) {
		try {
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
}
