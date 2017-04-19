package br.com.paxtecnologia.pma.relatorio.dao;

import br.com.paxtecnologia.pma.relatorio.vo.ClienteVO;
import br.com.paxtecnologia.pma.relatorio.vo.MesRelatorioVO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
	private DataSourcePMA connection;

	public List<ClienteVO> getListaClientes() {
		List<ClienteVO> retorno = new ArrayList<ClienteVO>();
		this.connection = new DataSourcePMA();

		String sql = "SELECT c.cliente_id,        c.cliente   FROM pax.pmp_cliente c  where exists (select 1 from pmp_task WHERE cliente_id = c.cliente_id)  order by cliente";

		PreparedStatement pstmt = this.connection.getPreparedStatement(sql);
		ResultSet rs = this.connection.executaQuery(pstmt);
		try {
			while (rs.next()) {
				ClienteVO temp = new ClienteVO();
				temp.setId(Integer.valueOf(rs.getInt("cliente_id")));
				temp.setNome(rs.getString("cliente"));
				retorno.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.connection.closeConnection(pstmt);
		return retorno;
	}

	public List<MesRelatorioVO> getListaMes(Integer idCliente) {
		List<MesRelatorioVO> retorno = new ArrayList<MesRelatorioVO>();
		this.connection = new DataSourcePMA();
		java.sql.Date data = null;

		String sql = "SELECT DISTINCT data_insercao FROM pmp_task WHERE cliente_id = ? order by 1 desc";
		PreparedStatement pstmt = this.connection.getPreparedStatement(sql);
		try {
			pstmt.setInt(1, idCliente.intValue());
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

	public String getLogoCliente(Integer idCliente) {
		String retorno = null;
		this.connection = new DataSourcePMA();

		String sql = "SELECT logo FROM pax.pax.pmp_cliente WHERE cliente_id = ?";
		PreparedStatement pstmt = this.connection.getPreparedStatement(sql);
		try {
			pstmt.setInt(1, idCliente.intValue());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ResultSet rs = this.connection.executaQuery(pstmt);
		try {
			while (rs.next()) {
				retorno = rs.getString("logo");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.connection.closeConnection(pstmt);
		return retorno;
	}

	public String getNomeCliente(Integer idCliente) {
		String retorno = null;
		this.connection = new DataSourcePMA();

		String sql = "SELECT cliente FROM pax.pax.pmp_cliente WHERE cliente_id = ?";
		PreparedStatement pstmt = this.connection.getPreparedStatement(sql);
		try {
			pstmt.setInt(1, idCliente.intValue());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ResultSet rs = this.connection.executaQuery(pstmt);
		try {
			while (rs.next()) {
				retorno = rs.getString("cliente");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.connection.closeConnection(pstmt);
		return retorno;
	}
}