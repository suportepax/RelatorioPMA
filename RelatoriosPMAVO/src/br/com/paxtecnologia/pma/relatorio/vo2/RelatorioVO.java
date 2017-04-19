package br.com.paxtecnologia.pma.relatorio.vo2;

import java.util.List;

public class RelatorioVO {
	public Integer relatorioId;
	private String nome;
	private String displayName;
	private Integer projetoJiraId;
	private Integer tipoRelatorioId;
	private String menuEntry;
	private String tituloCapa;
	private String logoStr;
	private String clienteDisplayName;
	private List<CapituloVO> capituloVO;

	public Integer getRelatorioId() {
		return this.relatorioId;
	}

	public void setRelatorioId(Integer relatorioId) {
		this.relatorioId = relatorioId;
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

	public String getMenuEntry() {
		return this.menuEntry;
	}

	public void setMenuEntry(String menuEntry) {
		this.menuEntry = menuEntry;
	}

	public Integer getProjetoJiraId() {
		return this.projetoJiraId;
	}

	public void setProjetoJiraId(Integer projetoJiraId) {
		this.projetoJiraId = projetoJiraId;
	}

	public Integer getTipoRelatorioId() {
		return this.tipoRelatorioId;
	}

	public void setTipoRelatorioId(Integer tipoRelatorioId) {
		this.tipoRelatorioId = tipoRelatorioId;
	}

	public String getTituloCapa() {
		return this.tituloCapa;
	}

	public void setTituloCapa(String tituloCapa) {
		this.tituloCapa = tituloCapa;
	}

	public String getLogoStr() {
		return this.logoStr;
	}

	public void setLogoStr(String logoStr) {
		this.logoStr = logoStr;
	}

	public String getClienteDisplayName() {
		return this.clienteDisplayName;
	}

	public void setClienteDisplayName(String clienteDisplayName) {
		this.clienteDisplayName = clienteDisplayName;
	}

	public List<CapituloVO> getCapituloVO() {
		return this.capituloVO;
	}

	public void setCapituloVO(List<CapituloVO> capituloVO) {
		this.capituloVO = capituloVO;
	}
}
