package br.com.paxtecnologia.pma.relatorio.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;



public class DataSourcePMA {

	protected static DataSource dataSource;
	private Connection conn;

	public DataSourcePMA() {
		conn = pegaConexao();
	}

	private Connection pegaConexao() {
		Connection con = null;
//		Context ic;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@191.209.125.73:1521:PAXMON", "paxrel", "paxazx01");
//			ic = new InitialContext();
//			dataSource = (DataSource) ic
////					.lookup("java:jboss/datasource/OracleMonitor"); // JBOSS Wildfly
//					.lookup("datasource/relpma"); // Glassfish
//			try {
//				con = dataSource.getConnection();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		} catch (NamingException e) {
//			e.printStackTrace();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
	
	public PreparedStatement getPreparedStatement(String sql){
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pstmt;
	}
	
	public ResultSet executaQuery(PreparedStatement pstmt) {
		ResultSet rs = null;
		try {
			rs = pstmt.executeQuery();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	public void executaUpdate(PreparedStatement pstmt) throws SQLException {
		try {
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new  SQLException(e);
		}
	}
	
	public void executaSave(PreparedStatement pstmt) throws SQLException {
		try {
			pstmt.execute();
		} catch (SQLException e) {
			throw new SQLException(e.getSQLState());
		}
	}
	
	public Boolean closeConnection(PreparedStatement pstmt){
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
