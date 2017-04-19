package br.com.paxtecnologia.pma.relatorio;

import br.com.paxtecnologia.pma.relatorio.ejb.ClientesEjb;
import br.com.paxtecnologia.pma.relatorio.vo.ClienteVO;
import br.com.paxtecnologia.pma.relatorio.vo.MesRelatorioVO;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

@ManagedBean(name="clientesBean")
@SessionScoped
public class ClientesBean {
    @EJB
    private ClientesEjb clientesEjb;
    private Integer idCliente;
    private String mesRelatorio;
    private List<ClienteVO> listaClientes;
    private List<MesRelatorioVO> listaMes;
    private Boolean update = false;

    public Boolean getUpdate() {
        return this.update;
    }

    public void setUpdate(Boolean update) {
        this.update = update;
    }

    public List<ClienteVO> getListaClientes() {
        if (this.listaClientes == null) {
            this.listaClientes = this.clientesEjb.getListaClientes();
        }
        return this.listaClientes;
    }

    public void updateListaMes(ValueChangeEvent e) {
        Object newValue = e.getNewValue();
        this.setListaMes(this.generateListaMes((Integer)newValue));
    }

    private List<MesRelatorioVO> generateListaMes(Integer idCliente) {
        this.setUpdate(false);
        this.listaMes = this.clientesEjb.getListaMes(idCliente);
        this.setUpdate(true);
        return this.listaMes;
    }

    public List<MesRelatorioVO> getListaMes() {
        return this.listaMes;
    }

    public Integer getIdCliente() {
        return this.idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String getMesRelatorio() {
        return this.mesRelatorio;
    }

    public void setMesRelatorio(String mesRelatorio) {
        this.mesRelatorio = mesRelatorio;
    }

    public void setListaClientes(List<ClienteVO> listaClientes) {
        this.listaClientes = listaClientes;
    }

    public void setListaMes(List<MesRelatorioVO> listaMes) {
        this.listaMes = listaMes;
    }

    public void mesChanged(ValueChangeEvent e) {
    }
}