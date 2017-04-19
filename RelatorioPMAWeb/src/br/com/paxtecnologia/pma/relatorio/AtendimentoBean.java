package br.com.paxtecnologia.pma.relatorio;

import br.com.paxtecnologia.pma.relatorio.ejb.AtendimentoEjb;
import br.com.paxtecnologia.pma.relatorio.util.FormataValorUtil;
import br.com.paxtecnologia.pma.relatorio.vo.ChamadoQuantidadeVO;
import br.com.paxtecnologia.pma.relatorio.vo.ChamadoVO;
import br.com.paxtecnologia.pma.relatorio.vo.IndicacoresQtdVO;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

@ViewScoped
@ManagedBean(name="atendimentoBean")
public class AtendimentoBean {
    @EJB
    private AtendimentoEjb atendimentoEjb;
    @ManagedProperty(value="#{relatorioBean.projetoJiraId}")
    private Integer projetoJiraId;
    @ManagedProperty(value="#{relatorioBean.mesRelatorio}")
    private String mesRelatorio;
    private List<ChamadoQuantidadeVO> listaSolicitante;
    private List<ChamadoQuantidadeVO> listaTipoChamado;
    private List<ChamadoVO> listaChamadoFechado;
    private List<ChamadoVO> listaChamadoEmAberto;
    private Integer qtdeChamadosAbertos;
    private Integer qtdeChamadosEmAbertos;
    private Integer qtdeChamadosFechados;
    private Integer qtdeChamadosAbertosSolicitante;
    private Integer qtdeChamadosFechadosSolicitante;
    private Integer qtdeChamadosAbertosTipo;
    private Integer qtdeChamadosFechadosTipo;
    private Double porcentoAbertosSolicitante;
    private Double porcentoFechadosSolicitante;
    private Double porcentoAbertos;
    private String porcentoEmAbertos;
    private String porcentoFechados;
    private Double porcentagemAbertoTipo;
    private Double porcentagemFechadoTipo;
    private String tempoMedio;

    public Integer getQtdeChamadosAbertos() {
        if (this.qtdeChamadosAbertos == null) {
            this.qtdeChamadosAbertos = this.atendimentoEjb.getQtdeChamadosAbertos(this.projetoJiraId, this.mesRelatorio);
        }
        return this.qtdeChamadosAbertos;
    }

    public Integer getQtdeChamadosFechados() {
        if (this.qtdeChamadosFechados == null) {
            this.qtdeChamadosFechados = this.atendimentoEjb.getQtdeChamadosFechados(this.projetoJiraId, this.mesRelatorio);
        }
        return this.qtdeChamadosFechados;
    }

    public String getPorcentoFechados() {
        if (this.porcentoFechados == null) {
            Double porcento = this.atendimentoEjb.getPorcentagemChamadosFechados(this.projetoJiraId, this.mesRelatorio) * 100.0;
            this.porcentoFechados = String.valueOf(FormataValorUtil.converterDoubleString((double)porcento)) + '%';
        }
        return this.porcentoFechados;
    }

    private Object getQtdeChamadosEmAberto() {
        if (this.qtdeChamadosEmAbertos == null) {
            this.qtdeChamadosEmAbertos = this.atendimentoEjb.getQtdeChamadosEmAberto(this.projetoJiraId, this.mesRelatorio);
        }
        return this.qtdeChamadosEmAbertos;
    }

    public String getPorcentoEmAbertos() {
        if (this.porcentoEmAbertos == null) {
            Double porcento = this.atendimentoEjb.getPorcentagemChamadosEmAbertos(this.projetoJiraId, this.mesRelatorio) * 100.0;
            this.porcentoEmAbertos = String.valueOf(FormataValorUtil.converterDoubleString((double)porcento)) + '%';
        }
        return this.porcentoEmAbertos;
    }

    public String getTempoMedio() {
        if (this.tempoMedio == null) {
            Double tempo = this.atendimentoEjb.getTempoMedio(this.projetoJiraId, this.mesRelatorio);
            this.tempoMedio = String.valueOf(FormataValorUtil.converterDoubleString((double)tempo)) + " h";
        }
        return this.tempoMedio;
    }

    public List<IndicacoresQtdVO> getListaGeral() {
        ArrayList<IndicacoresQtdVO> listaGeral = new ArrayList<IndicacoresQtdVO>();
        IndicacoresQtdVO a = new IndicacoresQtdVO();
        a.setTexto("N\u00famero de Chamados Abertos");
        a.setValor((Object)this.getQtdeChamadosAbertos());
        listaGeral.add(a);
        IndicacoresQtdVO b = new IndicacoresQtdVO();
        b.setTexto("N\u00famero de Chamados Solucionados");
        b.setValor((Object)this.getQtdeChamadosFechados());
        listaGeral.add(b);
        IndicacoresQtdVO c = new IndicacoresQtdVO();
        c.setTexto("% de Chamados Solucionados");
        c.setValor((Object)this.getPorcentoFechados());
        listaGeral.add(c);
        IndicacoresQtdVO d = new IndicacoresQtdVO();
        d.setTexto("Tempo M\u00e9dio para solucionar (em Horas)");
        d.setValor((Object)this.getTempoMedio());
        listaGeral.add(d);
        IndicacoresQtdVO e = new IndicacoresQtdVO();
        e.setTexto("N\u00famero de Chamados em Aberto");
        e.setValor(this.getQtdeChamadosEmAberto());
        listaGeral.add(e);
        IndicacoresQtdVO f = new IndicacoresQtdVO();
        f.setTexto("% de Chamados em Aberto");
        f.setValor((Object)this.getPorcentoEmAbertos());
        listaGeral.add(f);
        return listaGeral;
    }

    public List<ChamadoQuantidadeVO> getListaSolicitante() {
        if (this.listaSolicitante == null) {
            this.listaSolicitante = this.atendimentoEjb.getListaSolicitantes(this.projetoJiraId, this.mesRelatorio);
        }
        return this.listaSolicitante;
    }

    public Integer getQtdeChamadosAbertosSolicitante() {
        if (this.qtdeChamadosAbertosSolicitante == null) {
            this.qtdeChamadosAbertosSolicitante = this.atendimentoEjb.getQtdeChamadosAbertosSolicitante(this.projetoJiraId, this.mesRelatorio);
        }
        return this.qtdeChamadosAbertosSolicitante;
    }

    public Integer getQtdeChamadosFechadosSolicitante() {
        if (this.qtdeChamadosFechadosSolicitante == null) {
            this.qtdeChamadosFechadosSolicitante = this.atendimentoEjb.getQtdeChamadosFechadosSolicitante(this.projetoJiraId, this.mesRelatorio);
        }
        return this.qtdeChamadosFechadosSolicitante;
    }

    public Double getPorcentoAbertosSolicitante() {
        if (this.porcentoAbertosSolicitante == null) {
            this.porcentoAbertosSolicitante = this.atendimentoEjb.getPorcentoAbertosSolicitante(this.projetoJiraId, this.mesRelatorio);
        }
        return this.porcentoAbertosSolicitante;
    }

    public Double getPorcentoFechadosSolicitante() {
        if (this.porcentoFechadosSolicitante == null) {
            this.porcentoFechadosSolicitante = this.atendimentoEjb.getPorcentoFechadosSolicitante(this.projetoJiraId, this.mesRelatorio);
        }
        return this.porcentoFechadosSolicitante;
    }

    public List<ChamadoQuantidadeVO> getListaTipoChamado() {
        if (this.listaTipoChamado == null) {
            this.listaTipoChamado = this.atendimentoEjb.getListaChamadoTipo(this.projetoJiraId, this.mesRelatorio);
        }
        return this.listaTipoChamado;
    }

    public List<ChamadoVO> getListaChamadosEmAbertos() {
        if (this.listaChamadoEmAberto == null) {
            this.listaChamadoEmAberto = this.atendimentoEjb.getListaChamadosEmAbertos(this.projetoJiraId, this.mesRelatorio);
        }
        return this.listaChamadoEmAberto;
    }

    public List<ChamadoVO> getListaChamadosFechados() {
        if (this.listaChamadoFechado == null) {
            this.listaChamadoFechado = this.atendimentoEjb.getListaChamadosFechados(this.projetoJiraId, this.mesRelatorio);
        }
        return this.listaChamadoFechado;
    }

    public Integer getQtdeChamadosAbertosTipo() {
        if (this.qtdeChamadosAbertosTipo == null) {
            this.qtdeChamadosAbertosTipo = this.atendimentoEjb.getQtdeChamadosAbertosTipo(this.projetoJiraId, this.mesRelatorio);
        }
        return this.qtdeChamadosAbertosTipo;
    }

    public Integer getQtdeChamadosFechadosTipo() {
        if (this.qtdeChamadosFechadosTipo == null) {
            this.qtdeChamadosFechadosTipo = this.atendimentoEjb.getQtdeChamadosFechadosTipo(this.projetoJiraId, this.mesRelatorio);
        }
        return this.qtdeChamadosFechadosTipo;
    }

    public Double getPorcentagemAbertoTipo() {
        if (this.porcentagemAbertoTipo == null) {
            this.porcentagemAbertoTipo = this.atendimentoEjb.getPorcentagemChamadosAbertosTipo(this.projetoJiraId, this.mesRelatorio);
        }
        return this.porcentagemAbertoTipo;
    }

    public Double getPorcentagemFechadoTipo() {
        if (this.porcentagemFechadoTipo == null) {
            this.porcentagemFechadoTipo = this.atendimentoEjb.getPorcentagemChamadosFechadosTipo(this.projetoJiraId, this.mesRelatorio);
        }
        return this.porcentagemFechadoTipo;
    }

    public Integer getQtdeChamadosEmAbertos() {
        if (this.qtdeChamadosEmAbertos == null) {
            this.qtdeChamadosEmAbertos = this.atendimentoEjb.getQtdeChamadosEmAberto(this.projetoJiraId, this.mesRelatorio);
        }
        return this.qtdeChamadosEmAbertos;
    }

    public Double getPorcentoAbertos() {
        if (this.porcentoAbertos == null) {
            this.porcentoAbertos = this.atendimentoEjb.getPorcentagemChamadosAbertos(this.projetoJiraId, this.mesRelatorio);
        }
        return this.porcentoAbertos;
    }

    public Integer getRelatorioId() {
        return this.projetoJiraId;
    }

    public void setRelatorioId(Integer relatorioId) {
        this.projetoJiraId = relatorioId;
    }

    public String getMesRelatorio() {
        return this.mesRelatorio;
    }

    public void setMesRelatorio(String mesRelatorio) {
        this.mesRelatorio = mesRelatorio;
    }

    public Integer getProjetoJiraId() {
        return this.projetoJiraId;
    }

    public void setProjetoJiraId(Integer projetoJiraId) {
        this.projetoJiraId = projetoJiraId;
    }
}
