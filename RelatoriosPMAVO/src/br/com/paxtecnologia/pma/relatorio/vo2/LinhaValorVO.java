package br.com.paxtecnologia.pma.relatorio.vo2;

import java.util.Comparator;

public class LinhaValorVO implements Comparator<LinhaValorVO> {
	private String data;
	private Double valor;
	private Integer mes;
	private Integer dia;
	private Integer hora;

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

	public Integer getMes() {
		return this.mes;
	}

	public void setMes(Integer mes) {
		this.mes = mes;
	}

	public Integer getDia() {
		return this.dia;
	}

	public void setDia(Integer dia) {
		this.dia = dia;
	}

	public Integer getHora() {
		return this.hora;
	}

	public void setHora(Integer hora) {
		this.hora = hora;
	}

	public int compare(LinhaValorVO positive, LinhaValorVO negative) {
		String[] partsp = positive.getData().split("/");
		String[] partsn = negative.getData().split("/");

		Integer pos = Integer.valueOf(Integer.parseInt(partsp[1] + partsp[0].replaceAll("/", "")));
		Integer neg = Integer.valueOf(Integer.parseInt(partsn[1] + partsn[0].replaceAll("/", "")));

		return pos.intValue() - neg.intValue();
	}
}