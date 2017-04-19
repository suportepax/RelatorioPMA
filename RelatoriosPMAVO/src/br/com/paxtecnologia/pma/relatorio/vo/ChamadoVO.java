package br.com.paxtecnologia.pma.relatorio.vo;

public class ChamadoVO {
	private String idChamado;
	private String titulo;
	private String dataAbertura;
	private String dataFechamento;
	private String status;
	private String tipoChamado;
	private String solicitante;
	private Integer segundosTrabalhos;
	private String host;

	public String getIdChamado() {

		return this.idChamado;
	}

	public void setIdChamado(String idChamado) {
		this.idChamado = idChamado;
	}

	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDataAbertura() {
		return this.dataAbertura;
	}

	public void setDataAbertura(String dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTipoChamado() {
		return this.tipoChamado;
	}

	public void setTipoChamado(String tipoChamado) {
		this.tipoChamado = tipoChamado;
	}

	public String getDataFechamento() {
		return this.dataFechamento;
	}

	public void setDataFechamento(String dataFechamento) {
		this.dataFechamento = dataFechamento;
	}

	public String getSolicitante() {
		return this.solicitante;
	}

	public void setSolicitante(String solicitante) {
		this.solicitante = solicitante;
	}

	public Integer getSegundosTrabalhos() {
		return this.segundosTrabalhos;
	}

	public void setSegundosTrabalhos(Integer segundosTrabalhos) {
		this.segundosTrabalhos = segundosTrabalhos;
	}

	public String getHost() {
		return this.host;
	}

	public void setHost(String host) {
		this.host = host;
	}
}
