package br.com.paxtecnologia.pma.relatorio;

import br.com.paxtecnologia.pma.relatorio.ejb.RelatorioEjb;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

@ViewScoped
@ManagedBean(name="capaBean")
public class CapaBean {
    @EJB
    private RelatorioEjb relatorioEjb;
    @ManagedProperty(value="#{relatorioBean.relatorioId}")
    private Integer relatorioId;
    @ManagedProperty(value="#{relatorioBean.mesRelatorio}")
    private String mesRelatorio;
    private String tituloRelatorio;
    private String subtituloRelatorio;
    private String dataCriacao;
    private String textoVersao = "Vers\u00e3o 10.0";
    SimpleDateFormat dateFormat;
    private String nomeCliente;
    private String mesEAno;
    private String logoCliente;

    public String getLogoCliente() {
        if (this.logoCliente == null) {
            this.logoCliente = this.relatorioEjb.getLogoStr(this.relatorioId);
        }
        return this.logoCliente;
    }

    public String getNomeCliente() {
        if (this.nomeCliente == null) {
            this.nomeCliente = this.relatorioEjb.getClienteDisplayName(this.relatorioId);
        }
        return this.nomeCliente;
    }

    public String getMesEAno() {
        if (this.mesEAno == null) {
            this.dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date dataRelatorio = this.dateFormat.parse(this.mesRelatorio);
                this.dateFormat = new SimpleDateFormat("MMMM 'de' yyyy");
                this.mesEAno = this.dateFormat.format(dataRelatorio);
            }
            catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return this.mesEAno;
    }

    public String getTituloRelatorio() {
        this.tituloRelatorio = this.relatorioEjb.getTituloRelatorio(this.relatorioId);
        return this.tituloRelatorio;
    }

    public String getSubtituloRelatorio() {
        if (this.subtituloRelatorio == null) {
            this.dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date dataRelatorio = this.dateFormat.parse(this.mesRelatorio);
                this.dateFormat = new SimpleDateFormat("MMMM / yyyy");
                this.subtituloRelatorio = "- " + this.dateFormat.format(dataRelatorio) + " -";
            }
            catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return this.subtituloRelatorio;
    }

    public String getDataCriacao() {
        if (this.dataCriacao == null) {
            Date dataAtual = new Date();
            this.dateFormat = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy");
            this.dataCriacao = "Data de cria\u00e7\u00e3o: " + this.dateFormat.format(dataAtual);
        }
        return this.dataCriacao;
    }

    public String getTextoVersao() {
        return this.textoVersao;
    }

    public Integer getRelatorioId() {
        return this.relatorioId;
    }

    public void setRelatorioId(Integer relatorioId) {
        this.relatorioId = relatorioId;
    }

    public String getMesRelatorio() {
        return this.mesRelatorio;
    }

    public void setMesRelatorio(String mesRelatorio) {
        this.mesRelatorio = mesRelatorio;
    }
}