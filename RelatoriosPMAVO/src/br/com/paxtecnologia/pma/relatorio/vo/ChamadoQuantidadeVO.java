package br.com.paxtecnologia.pma.relatorio.vo;

public class ChamadoQuantidadeVO {
	private String nome;
	private Integer qtdeAberto;
	private Integer qtdeFechado;
	private Integer qtdeEmAberto;
	private Double porcentoFechado;
	private Double porcentoAberto;
	private Double porcentoEmAberto;

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getQtdeAberto() {
		return this.qtdeAberto;
	}

	public void setQtdeAberto(Integer qtdeAberto) {
		this.qtdeAberto = qtdeAberto;
	}

	public Double getPorcentoAberto() {
		return this.porcentoAberto;
	}

	public void setPorcentoAberto(Double porcentoAberto) {
		this.porcentoAberto = porcentoAberto;
	}

	public Integer getQtdeFechado() {
		return this.qtdeFechado;
	}

	public void setQtdeFechado(Integer qtdeFechado) {
		this.qtdeFechado = qtdeFechado;
	}

	public Double getPorcentoFechado() {
		return this.porcentoFechado;
	}

	public void setPorcentoFechado(Double porcentoFechado) {
		this.porcentoFechado = porcentoFechado;
	}

	public Integer getQtdeEmAberto() {

		return this.qtdeEmAberto;
	}

	public void setQtdeEmAberto(Integer qtdeEmAberto) {
		this.qtdeEmAberto = qtdeEmAberto;
	}

	public Double getPorcentoEmAberto() {
		return this.porcentoEmAberto;
	}

	public void setPorcentoEmAberto(Double porcentoEmAberto) {
		this.porcentoEmAberto = porcentoEmAberto;
	}
}