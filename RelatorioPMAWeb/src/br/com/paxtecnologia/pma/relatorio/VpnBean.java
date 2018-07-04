package br.com.paxtecnologia.pma.relatorio;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.com.paxtecnologia.pma.relatorio.ejb.VpnEJB;
import br.com.paxtecnologia.pma.relatorio.vo2.EstatisticaGeralVO;
import br.com.paxtecnologia.pma.relatorio.vo2.KeyValue;

@ViewScoped
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
	private List<EstatisticaGeralVO> estatisticaGeral;
	
	public Integer parseIntString(String val) {
		return new Double(val).intValue();
	}

	public List<EstatisticaGeralVO> getEstatisticaGeral() {
		if (estatisticaGeral == null)
			estatisticaGeral = vpnEJB.estatisticaGeral();
		return estatisticaGeral;
	}

	public void setEstatisticaGeral(List<EstatisticaGeralVO> estatisticaGeral) {
		this.estatisticaGeral = estatisticaGeral;
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

	public List<KeyValue> getSegundosConectadosTop() {
		if (segundosConectadosTop == null) {
			segundosConectadosTop = vpnEJB.segundosConectadosTop10();
		}
		return segundosConectadosTop;
	}

	public void setSegundosConectadosTop(List<KeyValue> segundosConectadosTop) {
		this.segundosConectadosTop = segundosConectadosTop;
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
