package br.com.paxtecnologia.pma.relatorio;

import br.com.paxtecnologia.pma.relatorio.ejb.AtendimentoEjb;
import br.com.paxtecnologia.pma.relatorio.vo.ChamadoQuantidadeVO;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

@ViewScoped
@ManagedBean(name="atendimentoHostBean")
public class AtendimentoHostBean {
    @EJB
    private AtendimentoEjb atendimentoEjb;
    @ManagedProperty(value="#{relatorioBean.projetoJiraId}")
    private Integer projetoJiraId;
    @ManagedProperty(value="#{relatorioBean.mesRelatorio}")
    private String mesRelatorio;
    private List<ChamadoQuantidadeVO> listaHost;
    private Integer qtdeChamadosAbertosComHost;
    private Double porcentoAbertosComHost;
    private Double porcentoFechadosComHost;
    private Integer qtdeChamadosFechadosComHost;
    private String graficoAbertos;
    private String graficoFechados;

    public void setIdCliente(Integer idCliente) {
        this.projetoJiraId = idCliente;
    }

    public void setMesRelatorio(String mesRelatorio) {
        this.mesRelatorio = mesRelatorio;
    }

    public Integer getQtdeChamadosAbertosComHost() {
        if (this.qtdeChamadosAbertosComHost == null) {
            this.qtdeChamadosAbertosComHost = this.atendimentoEjb.getQtdeChamadosAbertosHost(this.projetoJiraId, this.mesRelatorio);
        }
        return this.qtdeChamadosAbertosComHost;
    }

    public Double getPorcentoAbertosComHost() {
        if (this.porcentoAbertosComHost == null) {
            this.porcentoAbertosComHost = this.atendimentoEjb.getPorcentoAbertosComHost(this.projetoJiraId, this.mesRelatorio);
        }
        return this.porcentoAbertosComHost;
    }

    public Double getPorcentoFechadosComHost() {
        if (this.porcentoFechadosComHost == null) {
            this.porcentoFechadosComHost = this.atendimentoEjb.getPorcentoFechadosComHost(this.projetoJiraId, this.mesRelatorio);
        }
        return this.porcentoFechadosComHost;
    }

    public Integer getQtdeChamadosFechadosComHost() {
        if (this.qtdeChamadosFechadosComHost == null) {
            this.qtdeChamadosFechadosComHost = this.atendimentoEjb.getQtdeChamadosFechadosHost(this.projetoJiraId, this.mesRelatorio);
        }
        return this.qtdeChamadosFechadosComHost;
    }

    public List<ChamadoQuantidadeVO> getListaHost() {
        if (this.listaHost == null) {
            this.listaHost = this.atendimentoEjb.getListaChamadoHost(this.projetoJiraId, this.mesRelatorio);
        }
        return this.listaHost;
    }

    public String getGraficoAbertos() {
        if (this.graficoAbertos == null) {
            this.graficoAbertos = this.atendimentoEjb.getGraficoAbertosHost(this.projetoJiraId, this.mesRelatorio);
        }
        return this.graficoAbertos;
    }

    public String getGraficoFechados() {
        if (this.graficoFechados == null) {
            this.graficoFechados = this.atendimentoEjb.getGraficoFechadosHost(this.projetoJiraId, this.mesRelatorio);
        }
        return this.graficoFechados;
    }

    public Integer getProjetoJiraId() {
        return this.projetoJiraId;
    }

    public void setProjetoJiraId(Integer projetoJiraId) {
        this.projetoJiraId = projetoJiraId;
    }
}