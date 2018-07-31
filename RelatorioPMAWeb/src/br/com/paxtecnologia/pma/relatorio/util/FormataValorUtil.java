package br.com.paxtecnologia.pma.relatorio.util;

import java.text.DecimalFormat;

public class FormataValorUtil {

	public static String converterDoubleString(double valor) {
		DecimalFormat fmt = new DecimalFormat("0.00");
		return fmt.format(valor).replace(",", ".");
	}

	public static double converterDoubleDoisDecimais(double valor) {
		DecimalFormat fmt = new DecimalFormat("0.00");
		String string = fmt.format(valor);
		return Double.parseDouble(string.replace(",", "."));
	}

	public static String humanReadableByteCount(long bytes, boolean si) {
		boolean negativo;

		if (bytes < 0) {
			negativo = true;
			bytes = bytes * -1;
		} else {
			negativo = false;
		}

		int unit = si ? 1024 : 1000;
		if (bytes < unit)
			return bytes + " B";
		int exp = (int) (Math.log(bytes) / Math.log(unit));
		String pre = (si ? "kMGTPE" : "KMGTPE").charAt(exp - 1) + (si ? "" : "i");

		if (negativo) {
			return String.format("-%.2f %sB", bytes / Math.pow(unit, exp), pre);
		} else {
			return String.format("%.2f %sB", bytes / Math.pow(unit, exp), pre);
		}

	}

}
