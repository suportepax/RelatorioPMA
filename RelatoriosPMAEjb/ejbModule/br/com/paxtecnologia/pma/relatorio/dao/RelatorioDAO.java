package br.com.paxtecnologia.pma.relatorio.dao;

import br.com.paxtecnologia.pma.relatorio.vo.MesRelatorioVO;
import br.com.paxtecnologia.pma.relatorio.vo2.RelatorioVO;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class RelatorioDAO {
	private DataSourcePMA connection;

	public RelatorioVO getRelatorioById(Integer relatorioId, String mesRelatorio) {
		RelatorioVO retorno = new RelatorioVO();

		this.connection = new DataSourcePMA();
		CapituloDAO capituloDAO = new CapituloDAO();

		String sql = "select a.relatorio_id ,a.nome ,a.display_name ,a.projeto_jira_id ,a.tipo_relatorio_id ,a.titulo_capa ,b.logo_str ,b.cliente_display_name from rel_relatorios a ,rel_projeto_jira b where a.relatorio_id = ? and a.projeto_jira_id = b.projeto_jira_id";

		PreparedStatement pstmt = this.connection.getPreparedStatement(sql);
		try {
			pstmt.setInt(1, relatorioId.intValue());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ResultSet rs = this.connection.executaQuery(pstmt);
		try {
			while (rs.next()) {
				retorno.setRelatorioId(Integer.valueOf(rs.getInt("relatorio_id")));
				retorno.setNome(rs.getString("nome"));
				retorno.setDisplayName(rs.getString("display_name"));
				retorno.setProjetoJiraId(Integer.valueOf(rs.getInt("projeto_jira_id")));
				retorno.setTipoRelatorioId(Integer.valueOf(rs.getInt("tipo_relatorio_id")));
				retorno.setTituloCapa(rs.getString("titulo_capa"));
				retorno.setLogoStr(rs.getString("logo_str"));
				retorno.setClienteDisplayName("cliente_display_name");
				retorno.setCapituloVO(capituloDAO.getListaCapitulosByRelId(retorno.getRelatorioId(), mesRelatorio));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.connection.closeConnection(pstmt);

		return retorno;
	}

	public List<RelatorioVO> getListaRelatorioMenu() {
		List<RelatorioVO> retorno = new ArrayList<RelatorioVO>();
		this.connection = new DataSourcePMA();

		String sql = "select pj.cliente_display_name || ' / ' || r.display_name menu_entry ,r.relatorio_id ,pj.projeto_jira_id ,r.nome from rel_relatorios r ,rel_projeto_jira pj where r.projeto_jira_id=pj.projeto_jira_id order by 1";

		PreparedStatement pstmt = this.connection.getPreparedStatement(sql);
		ResultSet rs = this.connection.executaQuery(pstmt);

		try {
			while (rs.next()) {
				RelatorioVO temp = new RelatorioVO();
				temp.setRelatorioId(Integer.valueOf(rs.getInt("relatorio_id")));
				temp.setMenuEntry(rs.getString("menu_entry"));
				temp.setProjetoJiraId(Integer.valueOf(rs.getInt("projeto_jira_id")));
				temp.setNome(rs.getString("nome"));

				retorno.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		this.connection.closeConnection(pstmt);

		return retorno;
	}

	public List<MesRelatorioVO> getListaMes(Integer relatorioId) {
		List<MesRelatorioVO> retorno = new ArrayList<MesRelatorioVO>();
		this.connection = new DataSourcePMA();
		Date data = null;

		String sql = "SELECT DISTINCT a.data_insercao FROM pmp_task a, rel_relatorios b  WHERE a.cliente_id = b.projeto_jira_id and b.relatorio_id = ? order by 1 desc";

		PreparedStatement pstmt = this.connection.getPreparedStatement(sql);
		try {
			pstmt.setInt(1, relatorioId.intValue());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ResultSet rs = this.connection.executaQuery(pstmt);
		try {
			while (rs.next()) {
				MesRelatorioVO temp = new MesRelatorioVO();
				data = rs.getDate("data_insercao");
				String formattedDate = new SimpleDateFormat("yyyy-MM-dd").format(data);
				String formattedDateTexto = new SimpleDateFormat("MMM/yyyy").format(data);
				temp.setLabelMes(formattedDateTexto);
				temp.setMesString(formattedDate);
				retorno.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.connection.closeConnection(pstmt);
		return retorno;
	}

	public String getLogoStr(Integer relatorioId) {
		String retorno = null;

		this.connection = new DataSourcePMA();

		String sql = "select  b.logo_str from rel_relatorios a ,rel_projeto_jira b where a.relatorio_id = ? and a.projeto_jira_id = b.projeto_jira_id";

		PreparedStatement pstmt = this.connection.getPreparedStatement(sql);
		try {
			pstmt.setInt(1, relatorioId.intValue());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ResultSet rs = this.connection.executaQuery(pstmt);
		try {
			while (rs.next()) {
				retorno = rs.getString("logo_str");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.connection.closeConnection(pstmt);

		return retorno;
	}

	public String getClienteDisplayName(Integer relatorioId) {
		String retorno = null;

		this.connection = new DataSourcePMA();

		String sql = "select  b.cliente_display_name from rel_relatorios a ,rel_projeto_jira b where a.relatorio_id = ? and a.projeto_jira_id = b.projeto_jira_id";

		PreparedStatement pstmt = this.connection.getPreparedStatement(sql);
		try {
			pstmt.setInt(1, relatorioId.intValue());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ResultSet rs = this.connection.executaQuery(pstmt);
		try {
			while (rs.next()) {
				retorno = rs.getString("cliente_display_name");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.connection.closeConnection(pstmt);

		return retorno;
	}

	public String getTituloRelatorio(Integer relatorioId) {
		String retorno = null;

		this.connection = new DataSourcePMA();

		String sql = "select  a.titulo_capa from rel_relatorios a where a.relatorio_id = ?";

		PreparedStatement pstmt = this.connection.getPreparedStatement(sql);
		try {
			pstmt.setInt(1, relatorioId.intValue());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ResultSet rs = this.connection.executaQuery(pstmt);
		try {
			while (rs.next()) {
				retorno = rs.getString("titulo_capa");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.connection.closeConnection(pstmt);

		return retorno;
	}

	public Integer getProjetoJiraIdByRelatorioId(Integer relatorioId) {
		Integer retorno = null;

		this.connection = new DataSourcePMA();

		String sql = "select  projeto_jira_id from rel_relatorios a where a.relatorio_id = ?";

		PreparedStatement pstmt = this.connection.getPreparedStatement(sql);
		try {
			pstmt.setInt(1, relatorioId.intValue());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ResultSet rs = this.connection.executaQuery(pstmt);
		try {
			while (rs.next()) {
				retorno = Integer.valueOf(rs.getInt("projeto_jira_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.connection.closeConnection(pstmt);
		return retorno;
	}

	public Integer getTipoRelatorioIdByRelatorioId(Integer relatorioId) {
		Integer retorno = null;

		this.connection = new DataSourcePMA();

		String sql = "select tipo_relatorio_id from rel_relatorios a where a.relatorio_id = ?";

		PreparedStatement pstmt = this.connection.getPreparedStatement(sql);
		try {
			pstmt.setInt(1, relatorioId.intValue());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ResultSet rs = this.connection.executaQuery(pstmt);
		try {
			while (rs.next()) {
				retorno = Integer.valueOf(rs.getInt("tipo_relatorio_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.connection.closeConnection(pstmt);
		return retorno;
	}
}