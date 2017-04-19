package br.com.paxtecnologia.pma.relatorio.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.paxtecnologia.pma.relatorio.util.FormataDataUtil;
import br.com.paxtecnologia.pma.relatorio.vo.GraficoMetricaVO;
import br.com.paxtecnologia.pma.relatorio.vo.HostVO;
import br.com.paxtecnologia.pma.relatorio.vo.TimeFrameVO;

public class WorkloadDAO {
	private DataSourcePMA connection;

	public List<TimeFrameVO> getTimeFrameAno24horasDBSize(Integer metrica, String mesRelatorio) {
		List<TimeFrameVO> timeFrame = new ArrayList<TimeFrameVO>();
		this.connection = new DataSourcePMA();

		String sql = "select to_char(f.data,'MM/YYYY') data ,max(f.valor_medio) KEEP (DENSE_RANK LAST ORDER BY f.data ASC) valor from pmp_workload_mes f where metrica_link_id = ? and data >= trunc(add_months(to_date(?,'yyyy/mm/dd') ,-12) , 'month') and data < trunc(add_months( to_date(?,'yyyy/mm/dd') ,1) , 'month') group by to_char(f.data,'MM/YYYY') order by data";

		PreparedStatement pstmt = this.connection.getPreparedStatement(sql);
		try {
			pstmt.setInt(1, metrica.intValue());
			pstmt.setString(2, mesRelatorio);
			pstmt.setString(3, mesRelatorio);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ResultSet rs = this.connection.executaQuery(pstmt);
		try {
			while (rs.next()) {
				TimeFrameVO temp = new TimeFrameVO();
				temp.setData(rs.getString("data"));
				temp.setValor(Double.valueOf(rs.getDouble("valor")));
				timeFrame.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.connection.closeConnection(pstmt);
		return timeFrame;
	}

	public List<br.com.paxtecnologia.pma.relatorio.vo.DBSizeTabelaVO> getDBSizeTabela(String paramString,
			Integer paramInteger) {
		throw new Error(
				"Unresolved compilation problems: \n\tThe method setData(String) is undefined for the type DBSizeTabelaVO\n\tThe method setValor(String) is undefined for the type DBSizeTabelaVO\n");
	}

	public GraficoMetricaVO getMetrica(Integer idInstancia, Integer idGraficoControle, Integer idTf) {
		GraficoMetricaVO retorno = new GraficoMetricaVO();
		this.connection = new DataSourcePMA();

		String sql = "select t.metrica_link_id,        t.tipo_horario_id   from pmp_grafico g, pmp_time_frame t  where g.instancia_id = ?    and g.grafico_controle_id = ?    and t.time_frame_controle_id = ?    and g.grafico_id = t.grafico_id";

		PreparedStatement pstmt = this.connection.getPreparedStatement(sql);
		try {
			pstmt.setInt(1, idInstancia.intValue());
			pstmt.setInt(2, idGraficoControle.intValue());
			pstmt.setInt(3, idTf.intValue());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ResultSet rs = this.connection.executaQuery(pstmt);
		try {
			while (rs.next()) {
				retorno.setMetrica(Integer.valueOf(rs.getInt("metrica_link_id")));
				retorno.setTipoHorario(Integer.valueOf(rs.getInt("tipo_horario_id")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.connection.closeConnection(pstmt);
		return retorno;
	}

	public List<TimeFrameVO> getTimeFrame24horas(Integer metrica, String mesRelatorio) {
		List<TimeFrameVO> timeFrame = new ArrayList<TimeFrameVO>();
		this.connection = new DataSourcePMA();

		String sql = "SELECT to_char(data, 'dd/mm/yyyy') data, avg(valor_medio) valor FROM pmp_workload_dia WHERE data between ? and ? AND metrica_link_id = ? GROUP BY to_char(data, 'dd/mm/yyyy') ORDER BY data";

		PreparedStatement pstmt = this.connection.getPreparedStatement(sql);
		try {
			pstmt.setDate(1, FormataDataUtil.formataDataInicio(mesRelatorio));
			pstmt.setDate(2, FormataDataUtil.formataDataFim_dia(mesRelatorio));
			pstmt.setInt(3, metrica.intValue());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ResultSet rs = this.connection.executaQuery(pstmt);
		try {
			while (rs.next()) {
				TimeFrameVO temp = new TimeFrameVO();
				temp.setData(rs.getString("data"));
				temp.setValor(Double.valueOf(rs.getDouble("valor")));
				timeFrame.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.connection.closeConnection(pstmt);
		return timeFrame;
	}

	public List<TimeFrameVO> getTimeFrameAno24horas(Integer metrica, String mesRelatorio) {
		List<TimeFrameVO> timeFrame = new ArrayList<TimeFrameVO>();
		this.connection = new DataSourcePMA();

		String sql = "SELECT to_char(data, 'mm/yyyy') data, avg(valor_medio) valor FROM pmp_workload_mes WHERE data between ? and ? AND metrica_link_id = ? GROUP BY to_char(data, 'mm/yyyy') ORDER BY to_date(data, 'mm/yyyy')";

		PreparedStatement pstmt = this.connection.getPreparedStatement(sql);
		try {
			pstmt.setDate(1, FormataDataUtil.formataAnoInicio(mesRelatorio));
			pstmt.setDate(2, FormataDataUtil.formataDataFim(mesRelatorio));
			pstmt.setInt(3, metrica.intValue());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ResultSet rs = this.connection.executaQuery(pstmt);
		try {
			while (rs.next()) {
				TimeFrameVO temp = new TimeFrameVO();
				temp.setData(rs.getString("data"));
				temp.setValor(Double.valueOf(rs.getDouble("valor")));
				timeFrame.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.connection.closeConnection(pstmt);
		return timeFrame;
	}

	public List<TimeFrameVO> getTimeFrame(Integer metrica, String mesRelatorio, List<String> periodo) {
		List<TimeFrameVO> timeFrame = new ArrayList<TimeFrameVO>();
		this.connection = new DataSourcePMA();

		List<String> possibleValues = periodo;
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < possibleValues.size(); i++) {
			builder.append("?,");
		}
		String sql = "SELECT to_char(data, 'dd/mm/yyyy') data, avg(valor_medio) valor FROM pmp_workload_dia WHERE data between ? and ? AND metrica_link_id = ? AND hora in ("
				+

				builder.deleteCharAt(builder.length() - 1).toString() + ") "
				+ "GROUP BY to_char(data, 'dd/mm/yyyy') ORDER BY data";
		PreparedStatement pstmt = this.connection.getPreparedStatement(sql);
		try {
			pstmt.setDate(1, FormataDataUtil.formataDataInicio(mesRelatorio));
			pstmt.setDate(2, FormataDataUtil.formataDataFim_dia(mesRelatorio));
			pstmt.setInt(3, metrica.intValue());
			int index = 4;
			for (String o : possibleValues) {
				pstmt.setString(index++, o);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ResultSet rs = this.connection.executaQuery(pstmt);
		try {
			while (rs.next()) {
				TimeFrameVO temp = new TimeFrameVO();
				temp.setData(rs.getString("data"));
				temp.setValor(Double.valueOf(rs.getDouble("valor")));
				timeFrame.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.connection.closeConnection(pstmt);
		return timeFrame;
	}

	public List<TimeFrameVO> getTimeFrameAno(Integer metrica, String mesRelatorio, List<String> periodo) {
		List<TimeFrameVO> timeFrame = new ArrayList<TimeFrameVO>();
		this.connection = new DataSourcePMA();

		List<String> possibleValues = periodo;
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < possibleValues.size(); i++) {
			builder.append("?,");
		}
		String sql = "SELECT to_char(data, 'mm/yyyy') data, avg(valor_medio) valor FROM pmp_workload_mes WHERE trunc(data,'MM') between trunc(?,'MM') and trunc(?,'MM') AND metrica_link_id = ? AND hora in ("
				+

				builder.deleteCharAt(builder.length() - 1).toString() + ") "
				+ "GROUP BY to_char(data, 'mm/yyyy') ORDER BY to_date(data, 'mm/yyyy')";
		PreparedStatement pstmt = this.connection.getPreparedStatement(sql);
		try {
			pstmt.setDate(1, FormataDataUtil.formataAnoInicio(mesRelatorio));
			pstmt.setDate(2, FormataDataUtil.formataDataFim(mesRelatorio));
			pstmt.setInt(3, metrica.intValue());
			int index = 4;
			for (String o : possibleValues) {
				pstmt.setString(index++, o);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ResultSet rs = this.connection.executaQuery(pstmt);
		try {
			while (rs.next()) {
				TimeFrameVO temp = new TimeFrameVO();
				temp.setData(rs.getString("data"));
				temp.setValor(Double.valueOf(rs.getDouble("valor")));
				timeFrame.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.connection.closeConnection(pstmt);
		return timeFrame;
	}

	public String getLegenda(Integer idInstancia, Integer idGraficoControle, Integer idTf) {
		this.connection = new DataSourcePMA();

		String sql = "select t.legenda   from pmp_grafico g, pmp_time_frame t  where g.instancia_id = ?    and g.grafico_controle_id = ?    and t.time_frame_controle_id = ?    and g.grafico_id = t.grafico_id";

		PreparedStatement pstmt = this.connection.getPreparedStatement(sql);
		try {
			pstmt.setInt(1, idInstancia.intValue());
			pstmt.setInt(2, idGraficoControle.intValue());
			pstmt.setInt(3, idTf.intValue());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ResultSet rs = this.connection.executaQuery(pstmt);
		String legenda = null;
		try {
			while (rs.next()) {
				legenda = rs.getString("legenda");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.connection.closeConnection(pstmt);
		return legenda;
	}

	public List<HostVO> getHosts(Integer paramInteger1, Integer paramInteger2) {
		throw new Error(
				"Unresolved compilation problems: \n\tThe method setHost(String) is undefined for the type HostVO\n\tThe method setDescricao(String) is undefined for the type HostVO\n\tThe method setHostName(String) is undefined for the type HostVO\n");
	}

	public List<HostVO> getHosts(Integer paramInteger) {
		throw new Error(
				"Unresolved compilation problems: \n\tThe method setHost(String) is undefined for the type HostVO\n\tThe method setDescricao(String) is undefined for the type HostVO\n\tThe method setHostName(String) is undefined for the type HostVO\n\tThe method setQuantidadeCPU(int) is undefined for the type HostVO\n\tThe method setQuantidadeMemoria(int) is undefined for the type HostVO\n\tThe method setTipoCPU(String) is undefined for the type HostVO\n");
	}
}