package br.com.paxtecnologia.pma.relatorio.vo;

public class ParadasPorTipoVO {
	private String idchamado;
	private String data;
	private String dataParada;
	private Double horas;
	private String host;
	private String titulo;

	public String getIdchamado() {
		return this.idchamado;
	}

	public void setIdchamado(String idchamado) {
		this.idchamado = idchamado;
	}

	public String getData() {
		return this.data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Double getHoras() {
		return this.horas;
	}

	public void setHoras(Double horas) {
		this.horas = horas;
	}

	public String getHost() {
		return this.host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDataParada() {
		return this.dataParada;
	}

	public void setDataParada(String dataParada) {
		this.dataParada = dataParada;
	}
}