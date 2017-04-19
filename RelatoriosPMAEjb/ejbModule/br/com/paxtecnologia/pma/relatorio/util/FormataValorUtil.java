package br.com.paxtecnologia.pma.relatorio.util;

import java.text.DecimalFormat;

public class FormataValorUtil {
	public static String converterDoubleString(double valor) {
		DecimalFormat fmt = new DecimalFormat("0.00");
		String string = fmt.format(valor);

		String[] part = string.split("[,]");
		String retorno = part[0] + "." + part[1];
		return retorno;
	}

	public static double converterDoubleDoisDecimais(double valor) {
		DecimalFormat fmt = new DecimalFormat("0.00");
		String string = fmt.format(valor);
		String[] part = string.split("[,]");
		String string2 = part[0] + "." + part[1];
		double retorno = Double.parseDouble(string2);
		return retorno;
	}

	public static String humanReadableByteCount(long bytes, boolean si) {
		boolean negativo;
		if (bytes < 0L) {
			negativo = true;
			bytes *= -1L;
		} else {
			negativo = false;
		}

		int unit = si ? 1024 : 1000;
		if (bytes < unit)
			return bytes + " B";
		int exp = (int) (Math.log(bytes) / Math.log(unit));
		String pre = (si ? "kMGTPE" : "KMGTPE").charAt(exp - 1) + (si ? "" : "i");

		if (negativo) {
			return String.format("-%.2f %sB", new Object[] { Double.valueOf(bytes / Math.pow(unit, exp)), pre });
		}
		return String.format("%.2f %sB", new Object[] { Double.valueOf(bytes / Math.pow(unit, exp)), pre });
	}
}
