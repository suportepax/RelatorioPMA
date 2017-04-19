package br.com.paxtecnologia.pma.relatorio.dao;

import br.com.paxtecnologia.pma.relatorio.vo2.CapituloVO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CapituloDAO {
	private DataSourcePMA connection;

	public List<CapituloVO> getListaCapitulosByRelId(Integer relatorioId, String mesRelatorio) {
		List<CapituloVO> retorno = new ArrayList<CapituloVO>();

		this.connection = new DataSourcePMA();
		HostDAO hostDAO = new HostDAO();

		String sql = "select a.capitulo ,a.nome ,a.display_name ,a.host_instancia ,a.nivel ,a.descricao from rel_capitulos_relatorio a, rel_relatorios b where a.tipo_relatorio_id=b.tipo_relatorio_id and b.relatorio_id = ? order by a.capitulo";

		PreparedStatement pstmt = this.connection.getPreparedStatement(sql);
		try {
			pstmt.setInt(1, relatorioId.intValue());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ResultSet rs = this.connection.executaQuery(pstmt);

		try {
			while (rs.next()) {
				CapituloVO temp = new CapituloVO();

				temp.setHostInstancia(Integer.valueOf(rs.getInt("host_instancia")));
				temp.setCapitulo(rs.getString("capitulo"));
				temp.setNome(rs.getString("nome"));
				temp.setDisplay_name(rs.getString("display_name"));
				temp.setNivel(Integer.valueOf(rs.getInt("nivel")));
				temp.setDescricao(rs.getString("descricao"));
				temp.setHostVO(hostDAO.getListaHostsByRelId(relatorioId, mesRelatorio));

				retorno.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.connection.closeConnection(pstmt);

		return retorno;
	}
}
