package br.com.paxtecnologia.pma.relatorio.ejb;

import br.com.paxtecnologia.pma.relatorio.util.FormataDataUtil;

public class GraficoEjb {
	
	private Integer diasNoMes;

	public Integer getDiasNoMes(String mesRelatorio) {
		
			diasNoMes = FormataDataUtil.diasNoMes(mesRelatorio);

		return diasNoMes;
	}
	
	
}
