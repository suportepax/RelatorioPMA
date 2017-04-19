package br.com.paxtecnologia.pma.relatorio.vo;

import br.com.paxtecnologia.pma.relatorio.vo2.GraficoLinhaVO;
import java.util.List;

public class GraficoVO {
	private Integer graficoId;
	private String tituloDisplayName;
	private String descricaoAntes;
	private String descricaoDepois;
	private Integer ordemPlot;
	private String descricaoCustomizada;
	private String nickName;
	private String capitulo;
	private Integer tipoPeriodoId;
	private List<GraficoLinhaVO> graficoLinhaVO;

	public Integer getGraficoId() {
		return this.graficoId;
	}

	public void setGraficoId(Integer graficoId) {
		this.graficoId = graficoId;
	}

	public String getTituloDisplayName() {
		return this.tituloDisplayName;
	}

	public void setTituloDisplayName(String tituloDisplayName) {
		this.tituloDisplayName = tituloDisplayName;
	}

	public String getDescricaoAntes() {
		return this.descricaoAntes;
	}

	public void setDescricaoAntes(String descricaoAntes) {
		this.descricaoAntes = descricaoAntes;
	}

	public String getDescricaoDepois() {
		return this.descricaoDepois;
	}

	public void setDescricaoDepois(String descricaoDepois) {
		this.descricaoDepois = descricaoDepois;
	}

	public Integer getOrdemPlot() {
		return this.ordemPlot;
	}

	public void setOrdemPlot(Integer ordemPlot) {
		this.ordemPlot = ordemPlot;
	}

	public String getDescricaoCustomizada() {
		return this.descricaoCustomizada;
	}

	public void setDescricaoCustomizada(String descricaoCustomizada) {
		this.descricaoCustomizada = descricaoCustomizada;
	}

	public String getNickName() {
		return this.nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public List<GraficoLinhaVO> getGraficoLinhaVO() {
		return this.graficoLinhaVO;
	}

	public void setGraficoLinhaVO(List<GraficoLinhaVO> graficoLinhaVO) {
		this.graficoLinhaVO = graficoLinhaVO;
	}

	public String getCapitulo() {
		return this.capitulo;
	}

	public void setCapitulo(String capitulo) {
		this.capitulo = capitulo;
	}

	public Integer getTipoPeriodoId() {
		return this.tipoPeriodoId;
	}

	public void setTipoPeriodoId(Integer tipoPeriodoId) {
		this.tipoPeriodoId = tipoPeriodoId;
	}
}