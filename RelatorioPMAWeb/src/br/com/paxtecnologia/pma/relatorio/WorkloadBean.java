package br.com.paxtecnologia.pma.relatorio;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.com.paxtecnologia.pma.relatorio.ejb.WorkloadEjb;

@ViewScoped
@ManagedBean(name = "workloadBean")
public class WorkloadBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	private WorkloadEjb workloadEjb;

	@ManagedProperty(value = "#{clientesBean.idCliente}")
	private Integer idCliente;

	@ManagedProperty(value = "#{clientesBean.mesRelatorio}")
	private String mesRelatorio;

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public void setMesRelatorio(String mesRelatorio) {
		this.mesRelatorio = mesRelatorio;
	}

	public String getTf(Integer idGraficoControle, Integer idTf) {
		return workloadEjb.getTf(idCliente, mesRelatorio, idGraficoControle, idTf);
	}

	public String getLabel(Integer idGraficoControle, Integer idTf) {
		return workloadEjb.getLabel(idCliente, idGraficoControle, idTf);
	}
	
	public String getLabelTitulo(Integer idGraficoControle) {
		return workloadEjb.getLabelTitulo(idCliente, idGraficoControle);
	}
	
	public int getDiasNoMes() {
		return workloadEjb.getDiasNoMes(mesRelatorio);
	}

}
