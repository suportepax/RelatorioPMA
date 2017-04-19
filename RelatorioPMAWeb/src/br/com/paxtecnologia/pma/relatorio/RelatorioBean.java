package br.com.paxtecnologia.pma.relatorio;

import br.com.paxtecnologia.pma.relatorio.ejb.RelatorioEjb;
import br.com.paxtecnologia.pma.relatorio.vo.MesRelatorioVO;
import br.com.paxtecnologia.pma.relatorio.vo2.RelatorioVO;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

@SessionScoped
@ManagedBean(name="relatorioBean")
public class RelatorioBean {
    @EJB
    private RelatorioEjb relatorioEjb;
    private Integer relatorioId;
    private Integer projetoJiraId;
    private String mesRelatorio;
    private List<RelatorioVO> listaRelatorios;
    private List<MesRelatorioVO> listaMes;
    private Boolean update = false;
    private String nome;
    private String tituloCapa;
    private String logoStr;
    private String clienteDisplayName;
    private Integer tipoRelatorioId;

    public Boolean getUpdate() {
        return this.update;
    }

    public void setUpdate(Boolean update) {
        this.update = update;
    }

    public List<RelatorioVO> getListaRelatoriosMenu() {
        if (this.listaRelatorios == null) {
            this.listaRelatorios = this.relatorioEjb.getListaRelatorioMenu();
        }
        return this.listaRelatorios;
    }

    public void updateListaMes(ValueChangeEvent e) {
        Object newValue = e.getNewValue();
        this.setListaMes(this.generateListaMes((Integer)newValue));
    }

    private List<MesRelatorioVO> generateListaMes(Integer relatorioId) {
        this.setUpdate(false);
        this.listaMes = this.relatorioEjb.getListaMes(relatorioId);
        this.setUpdate(true);
        return this.listaMes;
    }

    public List<MesRelatorioVO> getListaMes() {
        return this.listaMes;
    }

    public void setListaMes(List<MesRelatorioVO> listaMes) {
        this.listaMes = listaMes;
    }

    public String getMesRelatorio() {
        return this.mesRelatorio;
    }

    public void setMesRelatorio(String mesRelatorio) {
        this.mesRelatorio = mesRelatorio;
    }

    public Integer getProjetoJiraId() {
        this.projetoJiraId = this.relatorioEjb.getProjetoJiraIdByRelatorioId(this.getRelatorioId());
        return this.projetoJiraId;
    }

    public Integer getTipoRelatorioId() {
        this.tipoRelatorioId = this.relatorioEjb.getTipoRelatorioIdByRelatorioId(this.getRelatorioId());
        return this.tipoRelatorioId;
    }

    public void setProjetoJiraId(Integer projetoJiraId) {
        this.projetoJiraId = projetoJiraId;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public RelatorioVO getRelatorioById(Integer id, String mesRelatorio) {
        return this.relatorioEjb.getRelatorioById(id, mesRelatorio);
    }

    public String getTituloCapa() {
        return this.tituloCapa;
    }

    public void setTituloCapa(String tituloCapa) {
        this.tituloCapa = tituloCapa;
    }

    public String getLogoStr() {
        return this.logoStr;
    }

    public void setLogoStr(String logoStr) {
        this.logoStr = logoStr;
    }

    public String getClienteDisplayName() {
        return this.clienteDisplayName;
    }

    public void setClienteDisplayName(String clienteDisplayName) {
        this.clienteDisplayName = clienteDisplayName;
    }

    public Integer getRelatorioId() {
        return this.relatorioId;
    }

    public void setRelatorioId(Integer relatorioId) {
        this.relatorioId = relatorioId;
    }

    public void getTipoRelatorioId(String nome) {
        this.nome = nome;
    }
}