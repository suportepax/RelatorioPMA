package br.com.paxtecnologia.pma.relatorio.dao;

import br.com.paxtecnologia.pma.relatorio.util.FormataDataUtil;
import br.com.paxtecnologia.pma.relatorio.vo.ChamadoVO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class AtendimentoDAO {
	private DataSourcePMA connection;

	public List<ChamadoVO> getChamadosAbertos(Integer idCliente, String mesRelatorio) {
		List<ChamadoVO> retorno = new ArrayList<ChamadoVO>();
		this.connection = new DataSourcePMA();

		String sql = "SELECT chamado,        titulo,        solicitante, \t\t tipo_chamado, \t\t status, \t\t segundos_trabalhados, \t\t data_criacao, \t\t data_fechamento,        pmp_get_hosts_task(task_id) nome_fantasia   FROM pmp_task WHERE cliente_id = ?    AND trunc(data_insercao,'MM') = trunc(?,'MM')    AND trunc(data_criacao,'MM') = trunc(?,'MM')  order by data_criacao";

		PreparedStatement pstmt = this.connection.getPreparedStatement(sql);
		try {
			pstmt.setInt(1, idCliente.intValue());
			pstmt.setDate(2, FormataDataUtil.formataDataInicio(mesRelatorio));
			pstmt.setDate(3, FormataDataUtil.formataDataInicio(mesRelatorio));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ResultSet rs = this.connection.executaQuery(pstmt);
		try {
			while (rs.next()) {
				ChamadoVO temp = new ChamadoVO();
				temp.setDataAbertura(new SimpleDateFormat("yyyy-MM-dd").format(rs.getDate("data_criacao")));
				if (rs.getDate("data_fechamento") != null) {
					temp.setDataFechamento(new SimpleDateFormat("yyyy-MM-dd").format(rs.getDate("data_fechamento")));
				}
				temp.setIdChamado(rs.getString("chamado"));
				temp.setTitulo(rs.getString("titulo"));
				temp.setStatus(rs.getString("status"));
				temp.setTipoChamado(rs.getString("tipo_chamado"));
				temp.setSolicitante(rs.getString("solicitante"));
				temp.setSegundosTrabalhos(Integer.valueOf(rs.getInt("segundos_trabalhados")));
				temp.setHost(rs.getString("nome_fantasia"));
				retorno.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.connection.closeConnection(pstmt);
		return retorno;
	}

	public List<ChamadoVO> getChamadosFechados(Integer idCliente, String mesRelatorio) {
		List<ChamadoVO> retorno = new ArrayList<ChamadoVO>();
		this.connection = new DataSourcePMA();

		String sql = "SELECT chamado,        titulo,        solicitante,        tipo_chamado,        status,        segundos_trabalhados,        data_criacao,        data_fechamento,        pmp_get_hosts_task(task_id) nome_fantasia   FROM pmp_task  WHERE cliente_id = ?    AND trunc(data_insercao,'MM') = trunc(?,'MM')    AND trunc(data_fechamento,'MM') = trunc(?,'MM')  order by data_criacao";

		PreparedStatement pstmt = this.connection.getPreparedStatement(sql);
		try {
			pstmt.setInt(1, idCliente.intValue());
			pstmt.setDate(2, FormataDataUtil.formataDataInicio(mesRelatorio));
			pstmt.setDate(3, FormataDataUtil.formataDataInicio(mesRelatorio));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ResultSet rs = this.connection.executaQuery(pstmt);
		try {
			while (rs.next()) {
				ChamadoVO temp = new ChamadoVO();
				temp.setDataAbertura(new SimpleDateFormat("yyyy-MM-dd").format(rs.getDate("data_criacao")));
				temp.setDataFechamento(new SimpleDateFormat("yyyy-MM-dd").format(rs.getDate("data_fechamento")));
				temp.setIdChamado(rs.getString("chamado"));
				temp.setTitulo(rs.getString("titulo"));
				temp.setStatus(rs.getString("status"));
				temp.setTipoChamado(rs.getString("tipo_chamado"));
				temp.setSolicitante(rs.getString("solicitante"));
				temp.setSegundosTrabalhos(Integer.valueOf(rs.getInt("segundos_trabalhados")));
				temp.setHost(rs.getString("nome_fantasia"));
				retorno.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.connection.closeConnection(pstmt);
		return retorno;
	}

	public List<ChamadoVO> getChamadosEmAbertos(Integer idCliente, String mesRelatorio) {
		List<ChamadoVO> retorno = new ArrayList<ChamadoVO>();
		this.connection = new DataSourcePMA();

		String sql = "select p.chamado,        p.titulo,        p.solicitante,        p.tipo_chamado,        p.status,        p.segundos_trabalhados,        p.data_criacao,        pmp_get_hosts_task(p.task_id) nome_fantasia   from pmp_task p,        (         SELECT  p.chamado,                 max(p.data_insercao) data_insercao           FROM pmp_task p          WHERE cliente_id = ?            and trunc(data_insercao, 'MM') <= trunc(?, 'MM')            and trunc(data_criacao, 'MM') <= trunc(?, 'MM')         \t and chamado in         \t\t (select chamado         \t\t\tfrom pmp_task         \t\t   WHERE cliente_id = p.cliente_id         \t\t\t and data_fechamento IS NULL         \t\t  union         \t\t  select chamado         \t\t\tfrom pmp_task         \t\t   WHERE cliente_id = p.cliente_id         \t\t\t and trunc(data_fechamento,'MM') >= trunc(?, 'MM'))         \t and chamado not in         \t\t (select chamado         \t\t\tfrom pmp_task         \t\t   where cliente_id = p.cliente_id         \t\t\t and trunc(data_fechamento, 'MM') <= trunc(?, 'MM'))         group by p.chamado) x  where p.chamado = x.chamado    and p.data_insercao = x.data_insercao  order by p.data_criacao";

		PreparedStatement pstmt = this.connection.getPreparedStatement(sql);
		try {
			pstmt.setInt(1, idCliente.intValue());
			pstmt.setDate(2, FormataDataUtil.formataDataInicio(mesRelatorio));
			pstmt.setDate(3, FormataDataUtil.formataDataInicio(mesRelatorio));
			pstmt.setDate(4, FormataDataUtil.formataDataInicio(mesRelatorio));
			pstmt.setDate(5, FormataDataUtil.formataDataInicio(mesRelatorio));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ResultSet rs = this.connection.executaQuery(pstmt);
		try {
			while (rs.next()) {
				ChamadoVO temp = new ChamadoVO();
				temp.setDataAbertura(new SimpleDateFormat("yyyy-MM-dd").format(rs.getDate("data_criacao")));
				temp.setIdChamado(rs.getString("chamado"));
				temp.setTitulo(rs.getString("titulo"));
				temp.setStatus(rs.getString("status"));
				temp.setTipoChamado(rs.getString("tipo_chamado"));
				temp.setSolicitante(rs.getString("solicitante"));
				temp.setSegundosTrabalhos(Integer.valueOf(rs.getInt("segundos_trabalhados")));
				temp.setHost(rs.getString("nome_fantasia"));
				retorno.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.connection.closeConnection(pstmt);
		return retorno;
	}

	public List<String> getListaFechadosComHosts(Integer idCliente, String mesRelatorio) {
		List<String> retorno = new ArrayList<String>();
		this.connection = new DataSourcePMA();

		String sql = "SELECT c.nome   FROM pmp_task a, pmp_task_host b, rel_hosts c  WHERE a.task_id = b.task_id    AND c.host_id = b.host_id    AND a.cliente_id = ?    AND a.data_insercao = ?    AND trunc(a.data_fechamento,'MM') = trunc(?,'MM')";

		PreparedStatement pstmt = this.connection.getPreparedStatement(sql);
		try {
			pstmt.setInt(1, idCliente.intValue());
			pstmt.setDate(2, FormataDataUtil.formataDataInicio(mesRelatorio));
			pstmt.setDate(3, FormataDataUtil.formataDataInicio(mesRelatorio));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ResultSet rs = this.connection.executaQuery(pstmt);
		try {
			while (rs.next()) {
				String temp = new String();
				temp = rs.getString("nome");
				retorno.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.connection.closeConnection(pstmt);
		return retorno;
	}

	public List<String> getListaAbertosComHosts(Integer idCliente, String mesRelatorio) {
		List<String> retorno = new ArrayList<String>();
		this.connection = new DataSourcePMA();

		String sql = "SELECT c.nome   FROM pmp_task a, pmp_task_host b, rel_hosts c  WHERE a.task_id = b.task_id    AND c.host_id = b.host_id    AND a.cliente_id = ?    AND a.data_insercao = ?   AND a.data_criacao >= ?";

		PreparedStatement pstmt = this.connection.getPreparedStatement(sql);
		try {
			pstmt.setInt(1, idCliente.intValue());
			pstmt.setDate(2, FormataDataUtil.formataDataInicio(mesRelatorio));
			pstmt.setDate(3, FormataDataUtil.formataDataInicio(mesRelatorio));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ResultSet rs = this.connection.executaQuery(pstmt);
		try {
			while (rs.next()) {
				String temp = new String();
				temp = rs.getString("nome");
				retorno.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.connection.closeConnection(pstmt);
		return retorno;
	}
}