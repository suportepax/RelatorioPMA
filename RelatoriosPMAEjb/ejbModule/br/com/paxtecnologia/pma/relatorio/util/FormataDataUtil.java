package br.com.paxtecnologia.pma.relatorio.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class FormataDataUtil {
	public static java.sql.Date formataAnoInicio(String mesRelatorio) {
		java.sql.Date anoInicio = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar c = Calendar.getInstance();
			c.setTime(sdf.parse(mesRelatorio));
			c.add(2, -11);
			anoInicio = new java.sql.Date(sdf.parse(sdf.format(c.getTime())).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return anoInicio;
	}

	public static java.sql.Date formataDataInicio(String mesRelatorio) {
		java.sql.Date dataInicio = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			dataInicio = new java.sql.Date(sdf.parse(mesRelatorio).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dataInicio;
	}

	public static java.sql.Date formataDataFim(String mesRelatorio) {
		java.sql.Date dataFim = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar c = Calendar.getInstance();
			c.setTime(sdf.parse(mesRelatorio));

			dataFim = new java.sql.Date(sdf.parse(sdf.format(c.getTime())).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dataFim;
	}

	public static java.sql.Date formataDataFim_dia(String mesRelatorio) {
		java.sql.Date dataFim = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar c = Calendar.getInstance();
			c.setTime(sdf.parse(mesRelatorio));
			c.add(2, 1);
			dataFim = new java.sql.Date(sdf.parse(sdf.format(c.getTime())).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dataFim;
	}

	public static Integer diasNoMes(String mesRelatorio) {
		Integer dia = Integer.valueOf(0);
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar c = Calendar.getInstance();
			c.setTime(sdf.parse(mesRelatorio));
			dia = Integer.valueOf(c.getActualMaximum(5));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dia;
	}

	public static Integer getCampoMesRelatorio(String campo, String mesRelatorio) {
		String ano = mesRelatorio.split("-")[0];
		String mes = mesRelatorio.split("-")[1];
		String dia = mesRelatorio.split("-")[2];

		if (campo.equals("dia"))
			return Integer.valueOf(Integer.parseInt(dia));
		if (campo.equals("mes"))
			return Integer.valueOf(Integer.parseInt(mes));
		if (campo.equals("ano")) {
			return Integer.valueOf(Integer.parseInt(ano));
		}
		return null;
	}
}
