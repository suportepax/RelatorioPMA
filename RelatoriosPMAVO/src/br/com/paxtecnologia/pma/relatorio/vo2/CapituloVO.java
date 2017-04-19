package br.com.paxtecnologia.pma.relatorio.vo2;

import br.com.paxtecnologia.pma.relatorio.vo.HostVO;
import java.util.List;

public class CapituloVO {
	private String capitulo;
	private String display_name;
	private String nome;
	private Integer hostInstancia;
	private List<HostVO> hostVO;
	private Integer nivel;
	private String descricao;

	public String getCapitulo() {
		return this.capitulo;
	}

	public void setCapitulo(String capitulo) {
		this.capitulo = capitulo;
	}

	public String getDisplay_name() {
		return this.display_name;
	}

	public void setDisplay_name(String display_name) {
		this.display_name = display_name;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getHostInstancia() {
		return this.hostInstancia;
	}

	public void setHostInstancia(Integer hostInstancia) {
		this.hostInstancia = hostInstancia;
	}

	public List<HostVO> getHostVO() {
		return this.hostVO;
	}

	public void setHostVO(List<HostVO> hostVO) {
		this.hostVO = hostVO;
	}

	public Integer getNivel() {
		return this.nivel;
	}

	public void setNivel(Integer nivel) {
		this.nivel = nivel;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}