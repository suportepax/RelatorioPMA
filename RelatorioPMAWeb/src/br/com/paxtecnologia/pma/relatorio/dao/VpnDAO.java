package br.com.paxtecnologia.pma.relatorio.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.paxtecnologia.pma.relatorio.vo2.EstatisticaGeralVO;
import br.com.paxtecnologia.pma.relatorio.vo2.KeyValue;

public class VpnDAO {
	private DataSourcePMA connection;

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
				kv.setValue(Double.toString(rs.getDouble("num")));
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
				kv.setValue(Integer.toString(rs.getInt("segundos")));
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
				kv.setValue(Double.toString(rs.getDouble("bytes")));
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
				kv.setValue(Double.toString(rs.getDouble("bytes")));
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

	public List<EstatisticaGeralVO> estatisticaGeral() {
		List<EstatisticaGeralVO> retorno = null;
		connection = new DataSourcePMA();
		PreparedStatement pstmt = null;
		try {
			String sql = "SELECT a.acesso_mes, b.total_users - a.acesso_mes sem_acesso, b.total_users, segundos , Ubytes, Dbytes "
					+ "FROM (SELECT COUNT (DISTINCT usuario) acesso_mes FROM PAXREL.PMP_FORNAX_VPN) a, "
					+ "PAXREL.PMP_TOTAL_VPN b, " + "(SELECT SUM (segundos_con) segundos FROM PAXREL.PMP_FORNAX_VPN) c, "
					+ "(SELECT SUM (upload) Ubytes FROM PAXREL.PMP_FORNAX_VPN) d, "
					+ "(SELECT SUM (download) Dbytes FROM PAXREL.PMP_FORNAX_VPN) e "
					+ "WHERE data_insert = ADD_MONTHS (TRUNC (SYSDATE, 'mm'), -1)";

			pstmt = connection.getPreparedStatement(sql);
			ResultSet rs = connection.executaQuery(pstmt);

			retorno = new ArrayList<EstatisticaGeralVO>();
			while (rs.next()) {
				EstatisticaGeralVO a = new EstatisticaGeralVO();
				a = new EstatisticaGeralVO();
				a.setAcessoMes(rs.getInt("acesso_mes"));
				a.setSemAcesso(rs.getInt("sem_acesso"));
				a.setTotalUsuarios(rs.getInt("total_users"));
				a.setSegundos(rs.getInt("segundos"));
				a.setDownloadBytes(rs.getDouble("Dbytes"));
				a.setUploadBytes(rs.getDouble("Ubytes"));
				retorno.add(a);
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
