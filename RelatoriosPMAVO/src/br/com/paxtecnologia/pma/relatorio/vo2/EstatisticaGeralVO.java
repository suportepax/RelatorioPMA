package br.com.paxtecnologia.pma.relatorio.vo2;

public class EstatisticaGeralVO {

	private Integer acessoMes;
	private Integer semAcesso;
	private Integer totalUsuarios;
	private Integer segundos;
	private Double uploadBytes;
	private Double downloadBytes;
	private String hrSegundos;
	private String hrUpload;
	private String hrDownload;

	public Integer getAcessoMes() {
		return acessoMes;
	}

	public void setAcessoMes(Integer acessoMes) {
		this.acessoMes = acessoMes;
	}

	public Integer getSemAcesso() {
		return semAcesso;
	}

	public void setSemAcesso(Integer semAcesso) {
		this.semAcesso = semAcesso;
	}

	public Integer getTotalUsuarios() {
		return totalUsuarios;
	}

	public void setTotalUsuarios(Integer totalUsuarios) {
		this.totalUsuarios = totalUsuarios;
	}

	public Integer getSegundos() {
		return segundos;
	}

	public void setSegundos(Integer segundos) {
		this.segundos = segundos;
	}

	public Double getUploadBytes() {
		return uploadBytes;
	}

	public void setUploadBytes(Double uploadBytes) {
		this.uploadBytes = uploadBytes;
	}

	public Double getDownloadBytes() {
		return downloadBytes;
	}

	public void setDownloadBytes(Double downloadBytes) {
		this.downloadBytes = downloadBytes;
	}

	public String getHrSegundos() {
		return hrSegundos;
	}

	public void setHrSegundos(String hrSegundos) {
		this.hrSegundos = hrSegundos;
	}

	public String getHrUpload() {
		return hrUpload;
	}

	public void setHrUpload(String hrUpload) {
		this.hrUpload = hrUpload;
	}

	public String getHrDownload() {
		return hrDownload;
	}

	public void setHrDownload(String hrDownload) {
		this.hrDownload = hrDownload;
	}

}
