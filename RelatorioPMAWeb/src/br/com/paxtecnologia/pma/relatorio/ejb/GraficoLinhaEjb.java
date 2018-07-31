package br.com.paxtecnologia.pma.relatorio.ejb;

import br.com.paxtecnologia.pma.relatorio.dao.GraficoLinhaDAO;

public class GraficoLinhaEjb {

	public String parseLegenda(Integer graficoId, Integer linhaId, String legenda){
		
		GraficoLinhaDAO graficoLinhaDAO = new GraficoLinhaDAO();
		String retorno = null;
		
		String key = graficoLinhaDAO.getZabbixItemKey(graficoId, linhaId); 
		
		retorno = legenda.replaceAll("@ZABKEY@", key);
		
		return retorno;
		
	}
	
}
