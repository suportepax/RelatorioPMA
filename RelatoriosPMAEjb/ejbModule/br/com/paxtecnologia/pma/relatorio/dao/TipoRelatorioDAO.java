package br.com.paxtecnologia.pma.relatorio.dao;

import br.com.paxtecnologia.pma.relatorio.vo2.TipoRelatorioVO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TipoRelatorioDAO {
	private DataSourcePMA connection;

	public TipoRelatorioVO getTipoRelatorioById(Integer id) {
		this.connection = new DataSourcePMA();

		String sql = "select tipo_relatorio_id,tipo_relatorio from rel_tipo_relatorio where tipo_relatorio_id = ?";

		PreparedStatement pstmt = this.connection.getPreparedStatement(sql);
		try {
			pstmt.setInt(1, id.intValue());
		} catch (SQLException e) {
			e.printStackTrace();
		}

		ResultSet rs = this.connection.executaQuery(pstmt);

		TipoRelatorioVO retorno = new TipoRelatorioVO();
		try {
			while (rs.next()) {
				retorno.setId(Integer.valueOf(rs.getInt("tipo_relatorio_id")));
				retorno.setTipoRelatorio(rs.getString("tipo_relatorio"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		this.connection.closeConnection(pstmt);

		return retorno;
	}
}