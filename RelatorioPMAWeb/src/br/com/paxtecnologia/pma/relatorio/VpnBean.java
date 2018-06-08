package br.com.paxtecnologia.pma.relatorio;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import br.com.paxtecnologia.pma.relatorio.ejb.VpnEJB;
import br.com.paxtecnologia.pma.relatorio.vo2.AcessoUsuario;
import br.com.paxtecnologia.pma.relatorio.vo2.KeyValue;

@SessionScoped
@ManagedBean(name = "vpnBean")
public class VpnBean {

	@EJB
	private VpnEJB vpnEJB;

	@ManagedProperty(value = "#{relatorioBean.projetoJiraId}")
	private Integer projetoJiraId;

	@ManagedProperty(value = "#{relatorioBean.mesRelatorio}")
	private String mesRelatorio;

	private List<KeyValue> segundosConectadosTop;
	private List<KeyValue> acessoUsuariosDia;
	private List<KeyValue> downloadTop;
	private List<KeyValue> uploadTop;
	private AcessoUsuario acessoUsuarios;
	private Integer segundosConectados;
	private Double download;
	private Double upload;

	public String humanRedable(Double bytes) {
		String[] dictionary = { "bytes", "KB", "MB", "GB", "TB", "PB", "EB", "ZB", "YB" };
		int index = 0;
		for (index = 0; index < dictionary.length; index++) {
			if (bytes < 1024)
				break;
			bytes /= 1024;
		}

		return String.format("%." + 2 + "f", bytes) + " " + dictionary[index];
	}

	public String humanReadableTime(Integer seconds) {
		int hoursInday = 24;
		int minutesInHour = 60;
		int secondsInMinute = 60;

		int minutes = seconds / secondsInMinute;
		seconds -= minutes * secondsInMinute;

		int hours = minutes / minutesInHour;
		minutes -= hours * minutesInHour;

		int days = hours / hoursInday;
		hours -= days * hoursInday;

		return days + " " + plural("dia", days) + ", " + hours + " " + plural("hora", hours) + ", " + minutes + " " + plural("minuto", minutes) + " e " + seconds +  " " + plural("segundo", seconds);
	}

	private String plural(String s, Integer qtd) {
		if (qtd > 1)
			s += 's';
		return s;
	}

	public AcessoUsuario getAcessoUsuarios() {
		if (acessoUsuarios == null) {
			acessoUsuarios = vpnEJB.acessoUsuario(projetoJiraId, mesRelatorio);
		}
		return acessoUsuarios;
	}

	public void setAcessoUsuarios(AcessoUsuario acessoUsuarios) {
		this.acessoUsuarios = acessoUsuarios;
	}

	public List<KeyValue> getAcessoUsuariosDia() {
		if (acessoUsuariosDia == null) {
			acessoUsuariosDia = vpnEJB.acessoUsuariosDia();
		}
		return acessoUsuariosDia;
	}

	public void setAcessoUsuariosDia(List<KeyValue> acessoUsuariosDia) {
		this.acessoUsuariosDia = acessoUsuariosDia;
	}

	public Integer getSegundosConectados() {
		if (segundosConectados == null) {
			segundosConectados = vpnEJB.segundosConectados();
		}
		return segundosConectados;
	}

	public void setSegundosConectados(Integer segundosConectados) {
		this.segundosConectados = segundosConectados;
	}

	public List<KeyValue> getSegundosConectadosTop() {
		if (segundosConectadosTop == null) {
			segundosConectadosTop = vpnEJB.segundosConectadosTop10();
		}
		return segundosConectadosTop;
	}

	public void setSegundosConectadosTop(List<KeyValue> segundosConectadosTop) {
		this.segundosConectadosTop = segundosConectadosTop;
	}

	public Double getUpload() {
		if (upload == null) {
			upload = vpnEJB.upload();
		}
		return upload;
	}

	public void setUpload(Double upload) {
		this.upload = upload;
	}

	public List<KeyValue> getUploadTop() {
		if (uploadTop == null) {
			uploadTop = vpnEJB.uploadTop10();
		}
		return uploadTop;
	}

	public void setUploadTop(List<KeyValue> uploadTop) {
		this.uploadTop = uploadTop;
	}

	public Double getDownload() {
		if (download == null) {
			download = vpnEJB.download();
		}
		return download;
	}

	public void setDownload(Double download) {
		this.download = download;
	}

	public List<KeyValue> getDownloadTop() {
		if (downloadTop == null) {
			downloadTop = vpnEJB.downloadTop10();
		}
		return downloadTop;
	}

	public void setDownloadTop(List<KeyValue> downloadTop) {
		this.downloadTop = downloadTop;
	}

	public Integer getProjetoJiraId() {
		return projetoJiraId;
	}

	public void setProjetoJiraId(Integer projetoJiraId) {
		this.projetoJiraId = projetoJiraId;
	}

	public String getMesRelatorio() {
		return mesRelatorio;
	}

	public void setMesRelatorio(String mesRelatorio) {
		this.mesRelatorio = mesRelatorio;
	}

}
