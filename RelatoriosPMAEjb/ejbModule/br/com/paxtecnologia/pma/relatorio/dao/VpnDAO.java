package br.com.paxtecnologia.pma.relatorio.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.paxtecnologia.pma.relatorio.vo2.AcessoUsuario;
import br.com.paxtecnologia.pma.relatorio.vo2.KeyValue;

public class VpnDAO {
	private DataSourcePMA connection;

	public AcessoUsuario acessoUsuario(Integer projetoJiraId, String mesRelatorio) {
		AcessoUsuario retorno = null;
		connection = new DataSourcePMA();
		PreparedStatement pstmt = null;
		try {
			String sql = "SELECT a.acesso_mes acessoMes, b.total_users - a.acesso_mes semAcesso, b.total_users total "
					+ "FROM (SELECT COUNT (DISTINCT usuario) acesso_mes FROM PAXREL.PMP_FORNAX_VPN) a, PAXREL.PMP_TOTAL_VPN b "
					+ "WHERE data_insert = ADD_MONTHS (TRUNC (SYSDATE, 'mm'), -1)";
			pstmt = connection.getPreparedStatement(sql);
//			pstmt.setString(1, mesRelatorio);
			ResultSet rs = connection.executaQuery(pstmt);
			retorno = new AcessoUsuario();
			while (rs.next()) {
				retorno.setAcessoMes(rs.getDouble("acessoMes"));
				retorno.setSemAcesso(rs.getDouble("semAcesso"));
				retorno.setTotalUsuarios(rs.getDouble("total"));
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

	public List<KeyValue> acessoUsuariosDia() {
		List<KeyValue> retorno = null;
		connection = new DataSourcePMA();
		PreparedStatement pstmt = null;
		try {
			String sql = "SELECT * from "
					+ "(SELECT usuario, count(1) num FROM PAXREL.PMP_FORNAX_VPN group by usuario order by 2 desc) "
					+ "where rownum <=10";
			pstmt = connection.getPreparedStatement(sql);
			ResultSet rs = connection.executaQuery(pstmt);
			retorno = new ArrayList<KeyValue>();
			while (rs.next()) {
				KeyValue kv = new KeyValue();
				kv.setKey(rs.getString("usuario"));
				kv.setValue(rs.getDouble("num"));
				retorno.add(kv);
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

	public List<KeyValue> segundosConectadosTop10() {
		List<KeyValue> retorno = null;
		connection = new DataSourcePMA();
		PreparedStatement pstmt = null;
		try {
			String sql = "SELECT * from "
					+ "(SELECT usuario, SUM (segundos_con) segundos FROM PAXREL.PMP_FORNAX_VPN group by usuario order by 2 desc) "
					+ "where rownum <=10";
			pstmt = connection.getPreparedStatement(sql);
			ResultSet rs = connection.executaQuery(pstmt);
			retorno = new ArrayList<KeyValue>();
			while (rs.next()) {
				KeyValue kv = new KeyValue();
				kv.setKey(rs.getString("usuario"));
				kv.setValue(rs.getDouble("segundos"));				
				retorno.add(kv);
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

	public List<KeyValue> downloadTop10() {
		List<KeyValue> retorno = null;
		connection = new DataSourcePMA();
		PreparedStatement pstmt = null;
		try {
			String sql = "SELECT * from "
					+ "(SELECT usuario, SUM (download) bytes FROM PAXREL.PMP_FORNAX_VPN group by usuario order by 2 desc) "
					+ "where rownum <=10";
			pstmt = connection.getPreparedStatement(sql);
			ResultSet rs = connection.executaQuery(pstmt);
			retorno = new ArrayList<KeyValue>();
			while (rs.next()) {
				KeyValue kv = new KeyValue();
				kv.setKey(rs.getString("usuario"));
				kv.setValue(rs.getDouble("bytes"));
				retorno.add(kv);
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

	public List<KeyValue> uploadTop10() {
		List<KeyValue> retorno = null;
		connection = new DataSourcePMA();
		PreparedStatement pstmt = null;
		try {
			String sql = "SELECT * from "
					+ "(SELECT usuario, SUM (upload) bytes FROM PAXREL.PMP_FORNAX_VPN group by usuario order by 2 desc) "
					+ "where rownum <=10";
			pstmt = connection.getPreparedStatement(sql);
			ResultSet rs = connection.executaQuery(pstmt);
			retorno = new ArrayList<KeyValue>();
			while (rs.next()) {
				KeyValue kv = new KeyValue();
				kv.setKey(rs.getString("usuario"));
				kv.setValue(rs.getDouble("bytes"));
				retorno.add(kv);
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

	public Integer segundosConectados() {
		Integer retorno = null;
		connection = new DataSourcePMA();
		PreparedStatement pstmt = null;
		try {
			String sql = "SELECT sum(segundos_con) segundos FROM PAXREL.PMP_FORNAX_VPN";
			pstmt = connection.getPreparedStatement(sql);
			ResultSet rs = connection.executaQuery(pstmt);
			while (rs.next()) {
				retorno = rs.getInt("segundos");
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

	public Double download() {
		Double retorno = null;
		connection = new DataSourcePMA();
		PreparedStatement pstmt = null;
		try {
			String sql = "SELECT sum(download) bytes FROM PAXREL.PMP_FORNAX_VPN";
			pstmt = connection.getPreparedStatement(sql);
			ResultSet rs = connection.executaQuery(pstmt);
			while (rs.next()) {
				retorno = rs.getDouble("bytes");
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

	public Double upload() {
		Double retorno = null;
		connection = new DataSourcePMA();
		PreparedStatement pstmt = null;
		try {
			String sql = "SELECT sum(upload) bytes FROM PAXREL.PMP_FORNAX_VPN";
			pstmt = connection.getPreparedStatement(sql);
			ResultSet rs = connection.executaQuery(pstmt);
			while (rs.next()) {
				retorno = rs.getDouble("bytes");
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

}
