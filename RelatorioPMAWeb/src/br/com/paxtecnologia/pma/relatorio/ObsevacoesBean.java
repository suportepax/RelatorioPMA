package br.com.paxtecnologia.pma.relatorio;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.com.paxtecnologia.pma.relatorio.ejb.ObservacaoEjb;

@ViewScoped
@ManagedBean(name="observacao")
public class ObsevacoesBean {
	@EJB
	private ObservacaoEjb observacaoEjb;
	
	@ManagedProperty(value = "#{clientesBean.idCliente}")
	private Integer idCliente;

	@ManagedProperty(value = "#{clientesBean.mesRelatorio}")
	private String mesRelatorio;
	
	private String textObservacao;
	
	public void salvar() {
		observacaoEjb.salvar(idCliente, mesRelatorio, textObservacao);
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public String getMesRelatorio() {
		return mesRelatorio;
	}

	public void setMesRelatorio(String mesRelatorio) {
		this.mesRelatorio = mesRelatorio;
	}

	public String getTextObservacao() {
		return textObservacao;
	}

	public void setTextObservacao(String textObservacao) {
		this.textObservacao = textObservacao;
	}
	
}
