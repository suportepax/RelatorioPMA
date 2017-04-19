package br.com.paxtecnologia.pma.relatorio.vo;

public class WorkloadGraficoVO {
	private Integer graficoId;
	private String titulo;
	private Integer mes_ano;
	private Integer tipo_calculo;

	public Integer getGraficoId() {
		return this.graficoId;
	}

	public void setGraficoId(Integer graficoId) {
		this.graficoId = graficoId;
	}

	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Integer getMes_ano() {
		return this.mes_ano;
	}

	public void setMes_ano(Integer mes_ano) {
		this.mes_ano = mes_ano;
	}

	public Integer getTipo_calculo() {
		return this.tipo_calculo;
	}

	public void setTipo_calculo(Integer tipo_calculo) {
		this.tipo_calculo = tipo_calculo;
	}
}