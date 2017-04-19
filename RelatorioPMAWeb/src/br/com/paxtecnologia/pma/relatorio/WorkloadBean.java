package br.com.paxtecnologia.pma.relatorio;

import br.com.paxtecnologia.pma.relatorio.ejb.WorkloadEjb;
import br.com.paxtecnologia.pma.relatorio.vo.DBSizeTabelaVO;
import br.com.paxtecnologia.pma.relatorio.vo.HostVO;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

@ViewScoped
@ManagedBean(name="workloadBean")
public class WorkloadBean
implements Serializable {
    private static final long serialVersionUID = 1;
    @EJB
    private WorkloadEjb workloadEjb;
    @ManagedProperty(value="#{clientesBean.idCliente}")
    private Integer idCliente;
    @ManagedProperty(value="#{clientesBean.mesRelatorio}")
    private String mesRelatorio;
    private Integer diasNoMes;
    private List<HostVO> hosts;
    private List<DBSizeTabelaVO> dbSizeTabelaVO;

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public void setMesRelatorio(String mesRelatorio) {
        this.mesRelatorio = mesRelatorio;
    }

    public String getTf(Integer idInstancia, Integer idGraficoControle, Integer idTf) {
        return this.workloadEjb.getTf(idInstancia, this.mesRelatorio, idGraficoControle, idTf);
    }

    public String getLegenda(Integer idInstancia, Integer idGraficoControle, Integer idTf) {
        return this.workloadEjb.getLegenda(idInstancia, idGraficoControle, idTf);
    }

    public Integer getDiasNoMes() {
        if (this.diasNoMes == null) {
            this.diasNoMes = this.workloadEjb.getDiasNoMes(this.mesRelatorio);
        }
        return this.diasNoMes;
    }

    public List<HostVO> getCapHosts(Integer capitulo) {
        this.hosts = null;
        if (this.hosts == null) {
            this.hosts = this.workloadEjb.getHosts(this.idCliente, capitulo);
        }
        return this.hosts;
    }

    public List<HostVO> getHosts() {
        this.hosts = null;
        if (this.hosts == null) {
            this.hosts = this.workloadEjb.getHosts(this.idCliente);
        }
        return this.hosts;
    }

    public List<DBSizeTabelaVO> getDBSizeTabela(Integer metricaLinkId) {
        this.dbSizeTabelaVO = this.workloadEjb.getDBSizeTabela(this.mesRelatorio, metricaLinkId);
        return this.dbSizeTabelaVO;
    }
}