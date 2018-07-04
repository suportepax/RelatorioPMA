package br.com.paxtecnologia.pma.relatorio.ejb;

import java.util.List;

import javax.ejb.Stateless;

import br.com.paxtecnologia.pma.relatorio.dao.VpnDAO;
import br.com.paxtecnologia.pma.relatorio.util.HumanReadableConvertions;
import br.com.paxtecnologia.pma.relatorio.vo2.EstatisticaGeralVO;
import br.com.paxtecnologia.pma.relatorio.vo2.KeyValue;

@Stateless
public class VpnEJB {
	
	private final VpnDAO vpnDAO = new VpnDAO();
	
	public List<KeyValue> acessoUsuariosDia() {
		return vpnDAO.acessoUsuariosDia();
	}
	
	public List<KeyValue> segundosConectadosTop10() {
		List<KeyValue> retorno = vpnDAO.segundosConectadosTop10();
		
		for (KeyValue e : retorno) {
			e.setValue(HumanReadableConvertions.humanReadableTime(Integer.parseInt(e.getValue())));
		}
		return retorno;
	}
	
	public List<KeyValue> downloadTop10() {
		List<KeyValue> retorno = vpnDAO.downloadTop10();
		for (KeyValue e : retorno) {
			e.setValue(HumanReadableConvertions.humanRedable(Double.parseDouble(e.getValue())));
		}
		return retorno;
	}
	
	public List<KeyValue> uploadTop10() {
		List<KeyValue> retorno = vpnDAO.uploadTop10();
		for (KeyValue e : retorno) {
			e.setValue(HumanReadableConvertions.humanRedable(Double.parseDouble(e.getValue())));
		}
		return retorno;
	}

	public List<EstatisticaGeralVO> estatisticaGeral() {
		List<EstatisticaGeralVO> retorno = vpnDAO.estatisticaGeral();
		for (EstatisticaGeralVO e : retorno) {
			e.setHrSegundos(HumanReadableConvertions.humanReadableTime(e.getSegundos()));
			e.setHrDownload(HumanReadableConvertions.humanRedable(e.getDownloadBytes()));
			e.setHrUpload(HumanReadableConvertions.humanRedable(e.getUploadBytes()));
		}		
		return retorno;		
	}

}
