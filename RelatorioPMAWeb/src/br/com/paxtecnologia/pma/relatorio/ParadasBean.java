package br.com.paxtecnologia.pma.relatorio;

import br.com.paxtecnologia.pma.relatorio.ejb.ParadasEjb;
import br.com.paxtecnologia.pma.relatorio.vo.ParadasPorTipoVO;
import br.com.paxtecnologia.pma.relatorio.vo.ParadasVO;
import br.com.paxtecnologia.pma.relatorio.vo.UltimoAnoVO;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

@ViewScoped
@ManagedBean(name="paradasBean")
public class ParadasBean {
    @EJB
    private ParadasEjb paradasEjb;
    @ManagedProperty(value="#{relatorioBean.projetoJiraId}")
    private Integer projetoJiraId;
    @ManagedProperty(value="#{relatorioBean.mesRelatorio}")
    private String mesRelatorio;
    private List<ParadasVO> listaItem;
    private List<UltimoAnoVO> listaUltimosAnosHoras;
    private List<ParadasPorTipoVO> listaParadasEvitadasMes;
    private List<ParadasPorTipoVO> listaParadasNaoProgramadasMes;
    private List<ParadasPorTipoVO> listaParadasProgramadasEstrategicasMes;
    private List<ParadasPorTipoVO> listaParadasProgramadasMes;
    private Integer diasTrabalhados;
    private Integer qtdeParadasEvitadasTotal;
    private static String PARADAS_EVITADAS = "PE";
    private static String PARADAS_NAO_PROGRAMADAS = "PNP";
    private static String PARADAS_PROGRAMADAS_ESTRATEGICAS = "PPE";
    private static String PARADAS_PROGRAMADAS = "PP";

    public Integer getDiasTrabalhados() {
        if (this.diasTrabalhados == null) {
            this.diasTrabalhados = this.paradasEjb.getDiasTrabalhados(this.projetoJiraId, this.mesRelatorio);
        }
        return this.diasTrabalhados;
    }

    public Integer getQtdeParadasEvitadasTotal() {
        if (this.qtdeParadasEvitadasTotal == null) {
            this.qtdeParadasEvitadasTotal = this.paradasEjb.getQtdeParadaEvitadasTotal(this.projetoJiraId, this.mesRelatorio);
        }
        return this.qtdeParadasEvitadasTotal;
    }

    public void setMesRelatorio(String mesRelatorio) {
        this.mesRelatorio = mesRelatorio;
    }

    public List<UltimoAnoVO> getListaUltimosAnosHoras(String tipo) {
        if (this.listaUltimosAnosHoras == null) {
            this.listaUltimosAnosHoras = this.paradasEjb.getListaUltimosAnosHoras(this.projetoJiraId, tipo, this.mesRelatorio);
        }
        return this.listaUltimosAnosHoras;
    }

    public List<ParadasVO> getListaResumo() {
        if (this.listaItem == null) {
            this.listaItem = new ArrayList<ParadasVO>();
            ParadasVO a = new ParadasVO();
            a.setTipo("Paradas Evitadas");
            a.setSigla(PARADAS_EVITADAS);
            a.setQtde(this.paradasEjb.getQtdeParadaEvitadasMes(this.projetoJiraId, this.mesRelatorio, PARADAS_EVITADAS));
            this.listaItem.add(a);
            ParadasVO b = new ParadasVO();
            b.setTipo("Paradas N\u00e3o Programadas");
            b.setSigla(PARADAS_NAO_PROGRAMADAS);
            b.setQtde(this.paradasEjb.getQtdeParadaNaoProgramadasMes(this.projetoJiraId, this.mesRelatorio, PARADAS_NAO_PROGRAMADAS));
            this.listaItem.add(b);
            ParadasVO c = new ParadasVO();
            c.setTipo("Paradas Programadas Estrat\u00e9gicas");
            c.setSigla(PARADAS_PROGRAMADAS_ESTRATEGICAS);
            c.setQtde(this.paradasEjb.getQtdeProgramadasEstrategicasMes(this.projetoJiraId, this.mesRelatorio, PARADAS_PROGRAMADAS_ESTRATEGICAS));
            this.listaItem.add(c);
            ParadasVO d = new ParadasVO();
            d.setTipo("Paradas Programadas");
            d.setSigla(PARADAS_PROGRAMADAS);
            d.setQtde(this.paradasEjb.getQtdeParadaProgramadasMes(this.projetoJiraId, this.mesRelatorio, PARADAS_PROGRAMADAS));
            this.listaItem.add(d);
        }
        return this.listaItem;
    }

    public List<ParadasPorTipoVO> getListaParadasEvitadasMes() {
        if (this.listaParadasEvitadasMes == null) {
            this.listaParadasEvitadasMes = this.paradasEjb.getListaParadasEvitadasMes(this.projetoJiraId, this.mesRelatorio, PARADAS_EVITADAS);
        }
        return this.listaParadasEvitadasMes;
    }

    public List<ParadasPorTipoVO> getListaParadasNaoProgramadasMes() {
        if (this.listaParadasNaoProgramadasMes == null) {
            this.listaParadasNaoProgramadasMes = this.paradasEjb.getListaParadasNaoProgramadasMes(this.projetoJiraId, this.mesRelatorio, PARADAS_NAO_PROGRAMADAS);
        }
        return this.listaParadasNaoProgramadasMes;
    }

    public List<ParadasPorTipoVO> getListaParadasProgramadasEstrategicasMes() {
        if (this.listaParadasProgramadasEstrategicasMes == null) {
            this.listaParadasProgramadasEstrategicasMes = this.paradasEjb.getListaParadasProgramadasEstrategicasMes(this.projetoJiraId, this.mesRelatorio, PARADAS_PROGRAMADAS_ESTRATEGICAS);
        }
        return this.listaParadasProgramadasEstrategicasMes;
    }

    public List<ParadasPorTipoVO> getListaParadasProgramadasMes() {
        if (this.listaParadasProgramadasMes == null) {
            this.listaParadasProgramadasMes = this.paradasEjb.getListaParadasProgramadasMes(this.projetoJiraId, this.mesRelatorio, PARADAS_PROGRAMADAS);
        }
        return this.listaParadasProgramadasMes;
    }

    public String getParadas(String tipo) {
        return this.paradasEjb.getParadas(tipo, this.mesRelatorio);
    }

    public String getParadasAcumulado(String tipo) {
        return this.paradasEjb.getParadasAcumulado(tipo, this.mesRelatorio);
    }

    public Double getTempoParadasMes(String tipo) {
        return this.paradasEjb.getTempoParadasMes(tipo, this.mesRelatorio);
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

    public Integer getProjetoJiraId() {
        return this.projetoJiraId;
    }

    public void setProjetoJiraId(Integer projetoJiraId) {
        this.projetoJiraId = projetoJiraId;
    }
}