package br.com.paxtecnologia.pma.relatorio;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import br.com.paxtecnologia.pma.relatorio.ejb.ObservacaoEJB;

@SessionScoped
@ManagedBean(name = "observacaoBean")
public class ObservacaoBean { 

	@EJB
	ObservacaoEJB observacaoEJB;

	private String observacao;
	private Boolean update = null;

	public void pegaObservacao(Integer idCliente, String mesRelatorio) {
		if (idCliente != null && mesRelatorio != null && !mesRelatorio.equals("0")) {
			observacao = observacaoEJB.getObservacao(idCliente, mesRelatorio);
			if (observacao != null) {
				update = true;
			} else {
				update = false;
			}
		}
	}

	public void salvar(Integer idCliente, String mesRelatorio) {
		String msg;
		if (observacao.length() > 0 && idCliente != null && mesRelatorio != null){
			if (observacaoEJB.salvarObservacao(idCliente, mesRelatorio, observacao)) {
				update = true;
				msg = "Salvo com sucesso!";
				facesMSG(msg);
			} else {
				msg = "Erro ao salvar no banco!";
				facesMSG(msg);
			}
		} else {
			msg = "Campos idCliente, mesRelatorio e observação são necessários!";
			facesMSG(msg);
		}
	}

	public void update(Integer idCliente, String mesRelatorio) {
		String msg;
		if (idCliente != null && mesRelatorio != null && !mesRelatorio.equals("0")
				&& update) {
			if (observacaoEJB.updateObservacao(idCliente, mesRelatorio, observacao)) {
				msg = "Salvo com sucesso!";
				facesMSG(msg); 
			} else {
				msg = "Erro ao fazer update no banco!";
				facesMSG(msg);
			}
		} else {
			msg = "Campos idCliente, mesRelatorio e observação são necessários!";
			facesMSG(msg);
		}
	}
	
	public void facesMSG(String msg) {
		try {
			FacesContext facesContext = null;
			facesContext = FacesContext.getCurrentInstance();
			FacesMessage facemsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
			facesContext.addMessage(null, facemsg);
//			facesContext.getExternalContext().getFlash().setKeepMessages(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void observacaoChange(ValueChangeEvent e) {
		observacao = e.getNewValue().toString();
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getObservacao() {
		return this.observacao;
	}

	public Boolean getUpdate() {
		return update;
	}
}
