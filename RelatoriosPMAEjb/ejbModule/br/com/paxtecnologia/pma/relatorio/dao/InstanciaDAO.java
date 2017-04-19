package br.com.paxtecnologia.pma.relatorio.dao;

import br.com.paxtecnologia.pma.relatorio.vo.InstanciaVO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InstanciaDAO {
	private DataSourcePMA connection;

	public List<InstanciaVO> getListaInstanciaByHostId(Integer relatorioId, Integer hostId, String mesRelatorio) {
		List<InstanciaVO> retorno = new ArrayList<InstanciaVO>();

		this.connection = new DataSourcePMA();
		GraficoDAO graficoDAO = new GraficoDAO();

		String sql = "select distinct b.instancia_id, b.nome, b.display_name, b.descricao from rel_hosts a, rel_instancias b, rel_graficos c, rel_relatorios d where a.host_id=b.host_id and c.tipo_relatorio_id = d.tipo_relatorio_id and b.instancia_id = c.instancia_id and d.relatorio_id = ? and a.host_id = ? and c.instancia_id in (select  instancia_id from rel_grafico_dados_ano where trunc(data,'MM')= trunc(to_date(?,'yyyy/mm/dd'),'MM'))";

		PreparedStatement pstmt = this.connection.getPreparedStatement(sql);
		try {
			pstmt.setInt(1, relatorioId.intValue());
			pstmt.setInt(2, hostId.intValue());
			pstmt.setString(3, mesRelatorio);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ResultSet rs = this.connection.executaQuery(pstmt);

		try {
			while (rs.next()) {
				InstanciaVO temp = new InstanciaVO();

				temp.setId(Integer.valueOf(rs.getInt("instancia_id")));
				temp.setNome(rs.getString("nome"));
				temp.setDisplayName(rs.getString("display_name"));
				temp.setDescricao(rs.getString("descricao"));
				temp.setGraficoVO(graficoDAO.getListaGraficoByInstanciaId(relatorioId, temp.getId(), mesRelatorio));

				retorno.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.connection.closeConnection(pstmt);

		return retorno;
	}
}