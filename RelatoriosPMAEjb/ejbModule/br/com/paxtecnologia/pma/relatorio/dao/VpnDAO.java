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
			String sql = "SELECT * FROM(SELECT usuario, COUNT( distinct to_char(to_date('01-JAN-1970','dd-mon-yyyy')+(data/60/60/24),'dd')) as dias FROM FORNAX_LOGIN group by usuario order by 2 desc) where rownum <=10";
			pstmt = connection.getPreparedStatement(sql);
			ResultSet rs = connection.executaQuery(pstmt);
			retorno = new ArrayList<KeyValue>();
			while (rs.next()) {
				KeyValue kv = new KeyValue();
				kv.setKey(rs.getString("usuario"));
				kv.setValue(Double.toString(rs.getDouble("dias")));
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
			String sql = "select * from(select usuario, sum(tempo_conectado) as tempo  FROM FORNAX_LOGOUT group by usuario order by 2 desc) where rownum <=10";
			pstmt = connection.getPreparedStatement(sql);
			ResultSet rs = connection.executaQuery(pstmt);
			retorno = new ArrayList<KeyValue>();
			while (rs.next()) {
				KeyValue kv = new KeyValue();
				kv.setKey(rs.getString("usuario"));
				kv.setValue(Integer.toString(rs.getInt("tempo")));
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
			String sql = "select * from(select usuario, sum(bytes_recebidos) as download  FROM FORNAX_LOGOUT group by usuario order by 2 desc) where rownum <=10";
			pstmt = connection.getPreparedStatement(sql);
			ResultSet rs = connection.executaQuery(pstmt);
			retorno = new ArrayList<KeyValue>();
			while (rs.next()) {
				KeyValue kv = new KeyValue();
				kv.setKey(rs.getString("usuario"));
				kv.setValue(Double.toString(rs.getDouble("download")));
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
			String sql = "select * from(select usuario, sum(bytes_enviados) as upload  FROM FORNAX_LOGOUT group by usuario order by 2 desc) where rownum <=10";
			pstmt = connection.getPreparedStatement(sql);
			ResultSet rs = connection.executaQuery(pstmt);
			retorno = new ArrayList<KeyValue>();
			while (rs.next()) {
				KeyValue kv = new KeyValue();
				kv.setKey(rs.getString("usuario"));
				kv.setValue(Double.toString(rs.getDouble("upload")));
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
