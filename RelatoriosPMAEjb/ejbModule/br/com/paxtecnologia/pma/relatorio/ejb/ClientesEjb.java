package br.com.paxtecnologia.pma.relatorio.ejb;

import br.com.paxtecnologia.pma.relatorio.dao.ClienteDAO;
import br.com.paxtecnologia.pma.relatorio.vo.ClienteVO;
import br.com.paxtecnologia.pma.relatorio.vo.MesRelatorioVO;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;

@Stateless
public class ClientesEjb {
	private ClienteDAO clienteDAO = new ClienteDAO();
	private List<ClienteVO> listaClientes;
	private List<MesRelatorioVO> listaMesRelatorio;
	private String logoCliente;
	private String nomeCliente;
	private Map<String, Integer> controleIdCliente = new HashMap<String, Integer>();

	public List<ClienteVO> getListaClientes() {
		if (this.listaClientes == null) {
			this.listaClientes = this.clienteDAO.getListaClientes();
		}
		return this.listaClientes;
	}

	public List<MesRelatorioVO> getListaMes(Integer idCliente) {
		if ((this.listaMesRelatorio == null) || (this.controleIdCliente.get("getListaMes") != idCliente)) {
			this.controleIdCliente.put("getListaMes", idCliente);
			this.listaMesRelatorio = this.clienteDAO.getListaMes(idCliente);
		}
		return this.listaMesRelatorio;
	}

	public String getLogoCliente(Integer idCliente) {
		if ((this.logoCliente == null) || (this.controleIdCliente.get("getLogoCliente") != idCliente)) {
			this.controleIdCliente.put("getLogoCliente", idCliente);
			this.logoCliente = this.clienteDAO.getLogoCliente(idCliente);
		}
		return this.logoCliente;
	}

	public String getNomeCliente(Integer idCliente) {
		if ((this.nomeCliente == null) || (this.controleIdCliente.get("getNomeCliente") != idCliente)) {
			this.controleIdCliente.put("getNomeCliente", idCliente);
			this.nomeCliente = this.clienteDAO.getNomeCliente(idCliente);
		}
		return this.nomeCliente;
	}
}