package br.com.paxtecnologia.pma.relatorio;

import br.com.paxtecnologia.pma.relatorio.ejb.GraficoEjb;
import br.com.paxtecnologia.pma.relatorio.ejb.GraficoLinhaEjb;
import br.com.paxtecnologia.pma.relatorio.ejb.LinhaValorEjb;
import br.com.paxtecnologia.pma.relatorio.vo.DBSizeTabelaVO;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

@ViewScoped
@ManagedBean(name="graficoBean")
public class GraficoBean
implements Serializable {
    @ManagedProperty(value="#{relatorioBean.mesRelatorio}")
    private String mesRelatorio;
    @EJB
    private LinhaValorEjb linhaValorEjb;
    @EJB
    private GraficoLinhaEjb graficoLinhaEjb;
    @EJB
    private GraficoEjb graficoEjb;
    private Integer diasNoMes;
    private static final long serialVersionUID = 1;

    public String getLinhaValorJS(Integer linhaId, Integer graficoId, Integer tipoPeriodoId, Integer tipoConsolidacaoDadoId, Boolean isByte) {
        String retorno = null;
        retorno = this.linhaValorEjb.getLinhaValorJS(linhaId, graficoId, this.mesRelatorio, tipoPeriodoId, tipoConsolidacaoDadoId, isByte);
        return retorno;
    }

    public String parseLegenda(Integer graficoId, Integer linhaId, String legenda) {
        String retorno = null;
        retorno = this.graficoLinhaEjb.parseLegenda(graficoId, linhaId, legenda);
        return retorno;
    }

    public String getMesRelatorio() {
        return this.mesRelatorio;
    }

    public void setMesRelatorio(String mesRelatorio) {
        this.mesRelatorio = mesRelatorio;
    }

    public Integer getDiasNoMes() {
        this.diasNoMes = this.graficoEjb.getDiasNoMes(this.mesRelatorio);
        return this.diasNoMes;
    }

    public List<DBSizeTabelaVO> getTabelaDBsize(Integer graficoId) {
        return this.linhaValorEjb.getTabelaDBsize(graficoId, this.mesRelatorio);
    }
}