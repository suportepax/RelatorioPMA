package br.com.paxtecnologia.pma.relatorio.vo2;

public class GraficoLinhaVO {
	private Integer linhaId;
	private String legenda;
	private Integer ordem;
	private String nome;
	private Integer tipoGraficoId;
	private Integer tipoConsolidacaoDadoId;
	private String cor;

	public Integer getLinhaId() {
		return this.linhaId;
	}

	public void setLinhaId(Integer linhaId) {
		this.linhaId = linhaId;
	}

	public String getLegenda() {
		return this.legenda;
	}

	public void setLegenda(String legenda) {
		this.legenda = legenda;
	}

	public Integer getOrdem() {
		return this.ordem;
	}

	public void setOrdem(Integer ordem) {
		this.ordem = ordem;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getTipoGraficoId() {
		return this.tipoGraficoId;
	}

	public void setTipoGraficoId(Integer tipoGraficoId) {
		this.tipoGraficoId = tipoGraficoId;
	}

	public Integer getTipoConsolidacaoDadoId() {
		return this.tipoConsolidacaoDadoId;
	}

	public void setTipoConsolidacaoDadoId(Integer tipoConsolidacaoDadoId) {
		this.tipoConsolidacaoDadoId = tipoConsolidacaoDadoId;
	}

	public String getCor() {
		return this.cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}
}