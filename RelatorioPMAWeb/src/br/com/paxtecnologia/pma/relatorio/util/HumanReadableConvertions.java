package br.com.paxtecnologia.pma.relatorio.util;

public class HumanReadableConvertions {

	private static int hoursInday = 24;
	private static int minutesInHour = 60;
	private static int secondsInMinute = 60;

	public static String humanRedable(Double bytes) {
		String[] dictionary = { "bytes", "KB", "MB", "GB", "TB", "PB", "EB", "ZB", "YB" };
		int index = 0;
		for (index = 0; index < dictionary.length; index++) {
			if (bytes < 1024)
				break;
			bytes /= 1024;
		}

		return String.format("%." + 2 + "f", bytes) + " " + dictionary[index];
	}

	public static String humanReadableTime(Integer seconds) {
		int minutes = seconds / secondsInMinute;
		seconds -= minutes * secondsInMinute;

		int hours = minutes / minutesInHour;
		minutes -= hours * minutesInHour;

		int days = hours / hoursInday;
		hours -= days * hoursInday;

		return days + " " + plural("dia", days) + ", " + hours + " " + plural("hora", hours) + ", " + minutes + " "
				+ plural("minuto", minutes) + " e " + seconds + " " + plural("segundo", seconds);
	}

	private static String plural(String s, Integer qtd) {
		if (qtd > 1) {
			s += 's';
		}			
		return s;
	}

}
