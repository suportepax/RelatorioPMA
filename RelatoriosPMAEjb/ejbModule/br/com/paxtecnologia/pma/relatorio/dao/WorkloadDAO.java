package br.com.paxtecnologia.pma.relatorio.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.paxtecnologia.pma.relatorio.vo.GraficoMetricaVO;
import br.com.paxtecnologia.pma.relatorio.vo.TimeFrameVO;
import br.com.paxtecnologia.pma.relatorio.util.FormataDataUtil;

public class WorkloadDAO {
	private DataSourcePMA connection;

	public GraficoMetricaVO getMetrica(Integer idCliente, Integer idGraficoControle, Integer idTf) {
		GraficoMetricaVO retorno = new GraficoMetricaVO();
		connection = new DataSourcePMA();
		PreparedStatement pstmt;
		String sql = "select t.metrica_link_id, "+
				 	 "       t.tipo_horario_id "+
				 	 "  from pmp_grafico g, pmp_time_frame t "+
				 	 " where g.cliente_id = ? "+
				 	 "   and g.grafico_controle_id = ? "+
				 	 "   and t.time_frame_controle_id = ? "+
				 	 "   and g.grafico_id = t.grafico_id";
		pstmt = connection.getPreparedStatement(sql);
		try {
			pstmt.setInt(1, idCliente);
			pstmt.setInt(2, idGraficoControle);
			pstmt.setInt(3, idTf);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResultSet rs = connection.executaQuery(pstmt);
		try {
			while (rs.next()) {
				retorno.setMetrica(rs.getInt("metrica_link_id"));
				retorno.setTipoHorario(rs.getInt("tipo_horario_id"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		connection.closeConnection(pstmt);
		return retorno;
	}

	public List<TimeFrameVO> getTimeFrame24horas(Integer metrica, String mesRelatorio) {
		List<TimeFrameVO> timeFrame = new ArrayList<TimeFrameVO>();
		connection = new DataSourcePMA();
		PreparedStatement pstmt;
		String sql = "SELECT to_char(data, 'dd/mm/yyyy') data, avg(valor_medio) valor "
				+ "FROM pmp_workload_dia "
				+ "WHERE data between ? and ? "
				+ "AND metrica_link_id = ? "
				+ "GROUP BY to_char(data, 'dd/mm/yyyy') " 
				+ "ORDER BY data";
		pstmt = connection.getPreparedStatement(sql);
		try {
			pstmt.setDate(1, FormataDataUtil.formataDataInicio(mesRelatorio));
			pstmt.setDate(2, FormataDataUtil.formataDataFim(mesRelatorio));
			pstmt.setInt(3, metrica);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResultSet rs = connection.executaQuery(pstmt);
		TimeFrameVO temp;
		try {
			while (rs.next()) {
				temp = new TimeFrameVO();
				temp.setData(rs.getString("data"));
				temp.setValor(rs.getDouble("valor"));
				timeFrame.add(temp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		connection.closeConnection(pstmt);
		return timeFrame;
	}
	
	public List<TimeFrameVO> getTimeFrameAno24horas(Integer metrica, String mesRelatorio) {
		List<TimeFrameVO> timeFrame = new ArrayList<TimeFrameVO>();
		connection = new DataSourcePMA();
		PreparedStatement pstmt;
		String sql = "SELECT to_char(data, 'mm/yyyy') data, avg(valor_medio) valor "
				+ "FROM pmp_workload_mes "
				+ "WHERE data between ? and ? "
				+ "AND metrica_link_id = ? "
				+ "GROUP BY to_char(data, 'mm/yyyy') "
				+ "ORDER BY data";
		pstmt = connection.getPreparedStatement(sql);
		try {
			pstmt.setDate(1, FormataDataUtil.formataAnoInicio(mesRelatorio));
			pstmt.setDate(2, FormataDataUtil.formataDataFim(mesRelatorio));
			pstmt.setInt(3, metrica);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResultSet rs = connection.executaQuery(pstmt);
		TimeFrameVO temp;
		try {
			while (rs.next()) {
				temp = new TimeFrameVO();
				temp.setData(rs.getString("data"));
				temp.setValor(rs.getDouble("valor"));
				timeFrame.add(temp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		connection.closeConnection(pstmt);
		return timeFrame;
	}	
	
	public List<TimeFrameVO> getTimeFrame(Integer metrica, String mesRelatorio, List<String> periodo) {
		List<TimeFrameVO> timeFrame = new ArrayList<TimeFrameVO>();
		connection = new DataSourcePMA();
		PreparedStatement pstmt;
		List<String> possibleValues = periodo;
		StringBuilder builder = new StringBuilder();
		for( int i = 0 ; i < possibleValues.size(); i++ ) {
		    builder.append("?,");
		}
		String sql = "SELECT to_char(data, 'dd/mm/yyyy') data, avg(valor_medio) valor "
				     + "FROM pmp_workload_dia "
				     + "WHERE data between ? and ? "
				     + "AND metrica_link_id = ? "
				     + "AND hora in (" + builder.deleteCharAt( builder.length() -1 ).toString() + ") "
				     + "GROUP BY to_char(data, 'dd/mm/yyyy') ORDER BY data";
		pstmt = connection.getPreparedStatement(sql);
		try {
			pstmt.setDate(1, FormataDataUtil.formataDataInicio(mesRelatorio));
			pstmt.setDate(2, FormataDataUtil.formataDataFim(mesRelatorio));
			pstmt.setInt(3, metrica);
			int index = 4;
			for( String o : possibleValues ) {
			   pstmt.setString(  index++, o ); // or whatever it applies 
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResultSet rs = connection.executaQuery(pstmt);
		TimeFrameVO temp;
		try {
			while (rs.next()) {
				temp = new TimeFrameVO();
				temp.setData(rs.getString("data"));
				temp.setValor(rs.getDouble("valor"));
				timeFrame.add(temp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		connection.closeConnection(pstmt);
		return timeFrame;
	}

	public List<TimeFrameVO> getTimeFrameAno(Integer metrica, String mesRelatorio, List<String> periodo) {
		List<TimeFrameVO> timeFrame = new ArrayList<TimeFrameVO>();
		connection = new DataSourcePMA();
		PreparedStatement pstmt;
		List<String> possibleValues = periodo;
		StringBuilder builder = new StringBuilder();
		for( int i = 0 ; i < possibleValues.size(); i++ ) {
		    builder.append("?,");
		}
		String sql = "SELECT to_char(data, 'mm/yyyy') data, avg(valor_medio) valor "
				+ "FROM pmp_workload_mes "
				+ "WHERE data between ? and ? "
				+ "AND metrica_link_id = ? "
				+ "AND hora in (" + builder.deleteCharAt( builder.length() -1 ).toString() + ") "
				+ "GROUP BY to_char(data, 'mm/yyyy') ORDER BY data";
		pstmt = connection.getPreparedStatement(sql);
		try {
			pstmt.setDate(1, FormataDataUtil.formataAnoInicio(mesRelatorio));
			pstmt.setDate(2, FormataDataUtil.formataDataFim(mesRelatorio));
			pstmt.setInt(3, metrica);
			int index = 4;
			for( String o : possibleValues ) {
			   pstmt.setString(  index++, o ); // or whatever it applies 
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResultSet rs = connection.executaQuery(pstmt);
		TimeFrameVO temp;
		try {
			while (rs.next()) {
				temp = new TimeFrameVO();
				temp.setData(rs.getString("data"));
				temp.setValor(rs.getDouble("valor"));
				timeFrame.add(temp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		connection.closeConnection(pstmt);
		return timeFrame;
	}		
	
	public String getLabel(Integer idCliente, Integer idGraficoControle, Integer idTf) {
		connection = new DataSourcePMA();
		PreparedStatement pstmt;
		String sql = "select t.legenda "+
				     "  from pmp_grafico g, pmp_time_frame t "+
				     " where g.cliente_id = ? "+
				     "   and g.grafico_controle_id = ? "+
				     "   and t.time_frame_controle_id = ? "+
				     "   and g.grafico_id = t.grafico_id";
		pstmt = connection.getPreparedStatement(sql);
		try {
			pstmt.setInt(1, idCliente);
			pstmt.setInt(2, idGraficoControle);
			pstmt.setInt(3, idTf);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResultSet rs = connection.executaQuery(pstmt);
		String label = null;
		try {
			while (rs.next()) {
				label = rs.getString("legenda");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		connection.closeConnection(pstmt);
		return label;
	}	

	public String getLabelTitulo(Integer idCliente, Integer idGraficoControle) {
		connection = new DataSourcePMA();
		PreparedStatement pstmt;
		String sql = "SELECT titulo "
				+ "FROM pmp_grafico "
				+ "WHERE cliente_id = ? "
				+ "  and grafico_controle_id = ?";
		pstmt = connection.getPreparedStatement(sql);
		try {
			pstmt.setInt(1, idCliente);
			pstmt.setInt(2, idGraficoControle);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResultSet rs = connection.executaQuery(pstmt);
		String labelTitulo = null;
		try {
			while (rs.next()) {
				labelTitulo = rs.getString("titulo");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		connection.closeConnection(pstmt);
		return labelTitulo;
	}	
}
