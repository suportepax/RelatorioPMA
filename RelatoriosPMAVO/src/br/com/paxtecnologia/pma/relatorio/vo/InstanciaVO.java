package br.com.paxtecnologia.pma.relatorio.vo;

import java.util.List;

public class InstanciaVO {
	private Integer id;
	private String nome;
	private String displayName;
	private String descricao;
	private List<GraficoVO> graficoVO;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDisplayName() {
		return this.displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public List<GraficoVO> getGraficoVO() {
		return this.graficoVO;
	}

	public void setGraficoVO(List<GraficoVO> graficoVO) {
		this.graficoVO = graficoVO;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}