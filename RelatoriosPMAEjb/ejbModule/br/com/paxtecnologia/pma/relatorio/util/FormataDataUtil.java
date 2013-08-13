package br.com.paxtecnologia.pma.relatorio.util;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class FormataDataUtil {
	
	public static Date formataAnoInicio(String mesRelatorio) {
		Date anoInicio = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar c = Calendar.getInstance();
			c.setTime(sdf.parse(mesRelatorio));
			c.add(Calendar.YEAR, -1);
			anoInicio = new Date(sdf.parse(sdf.format(c.getTime())).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return anoInicio;
	}
	
	public static Date formataDataInicio(String mesRelatorio) {
		Date dataInicio = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			dataInicio = new Date((sdf.parse(mesRelatorio).getTime()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dataInicio;
	}
	
	public static Date formataDataFim(String mesRelatorio) {
		Date dataFim = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar c = Calendar.getInstance();
			c.setTime(sdf.parse(mesRelatorio));
			c.add(Calendar.MONTH, 1);
			dataFim = new Date((sdf.parse(sdf.format(c.getTime())).getTime()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dataFim;
	}
	
	public static int diasNoMes(String mesRelatorio) {
		int dia = 0;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar c = Calendar.getInstance();
			c.setTime(sdf.parse(mesRelatorio));
			dia = c.getActualMaximum( Calendar.DAY_OF_MONTH );
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dia;
	}	
}
