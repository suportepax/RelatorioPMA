package br.com.paxtecnologia.pma.relatorio.ejb;

import br.com.paxtecnologia.pma.relatorio.util.FormataDataUtil;
import javax.ejb.Stateless;

@Stateless
public class GraficoEjb {
	private Integer diasNoMes;

	public Integer getDiasNoMes(String mesRelatorio) {
		this.diasNoMes = FormataDataUtil.diasNoMes(mesRelatorio);

		return this.diasNoMes;
	}
}