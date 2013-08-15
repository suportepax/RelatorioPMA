package br.com.paxtecnologia.pma.relatorio.vo;

public class GraficoVO {
	
	private String titulo;
	private String descricao;
	private Integer mesAno;
	private Integer tipoCalculo;
	private Integer controleId;
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Integer getMesAno() {
		return mesAno;
	}
	public void setMesAno(Integer mesAno) {
		this.mesAno = mesAno;
	}
	public Integer getTipoCalculo() {
		return tipoCalculo;
	}
	public void setTipoCalculo(Integer tipoCalculo) {
		this.tipoCalculo = tipoCalculo;
	}
	public Integer getControleId() {
		return controleId;
	}
	public void setControleId(Integer controleId) {
		this.controleId = controleId;
	}
	
}
