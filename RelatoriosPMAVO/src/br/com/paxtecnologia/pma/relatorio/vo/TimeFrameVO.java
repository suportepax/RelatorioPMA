package br.com.paxtecnologia.pma.relatorio.vo;

import java.util.Comparator;

public class TimeFrameVO implements Comparator<TimeFrameVO> {
	private String data;
	private Double valor;

	public String getData() {
		return this.data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Double getValor() {
		return this.valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public int compare(TimeFrameVO positive, TimeFrameVO negative) {
		String[] partsp = positive.getData().split("/");
		String[] partsn = negative.getData().split("/");

		Integer pos = Integer.valueOf(Integer.parseInt(partsp[1] + partsp[0].replaceAll("/", "")));
		Integer neg = Integer.valueOf(Integer.parseInt(partsn[1] + partsn[0].replaceAll("/", "")));

		return pos.intValue() - neg.intValue();
	}
}