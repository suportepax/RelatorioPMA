package br.com.paxtecnologia.pma.relatorio.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DataSourcePMA {
	protected static DataSource dataSource;
	private Connection conn;

	public DataSourcePMA() {
		this.conn = pegaConexao();
	}

	private Connection pegaConexao() {
		conn = null;
		try {
			Context ic = new InitialContext();
			dataSource = (DataSource) ic.lookup("java:jboss/datasource/OracleMonitor");
			try {
				conn = dataSource.getConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		} catch (NamingException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public PreparedStatement getPreparedStatement(String sql) {
		PreparedStatement pstmt = null;
		try {
			pstmt = this.conn.prepareStatement(sql);
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
			this.conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Boolean.valueOf(true);
	}
}