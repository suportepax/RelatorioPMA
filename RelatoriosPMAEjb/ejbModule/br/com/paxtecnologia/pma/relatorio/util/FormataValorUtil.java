package br.com.paxtecnologia.pma.relatorio.util;

import java.text.DecimalFormat;

public class FormataValorUtil {

	public static String converterDoubleString(double valor) {  
	    DecimalFormat fmt = new DecimalFormat("0.00");      
	    String string = fmt.format(valor);  
	    String[] part = string.split("[,]");  
	    String retorno = part[0]+"."+part[1];  
	    return retorno;  
	}  
	  
	public static double converterDoubleDoisDecimais(double valor) {  
	    DecimalFormat fmt = new DecimalFormat("0.00");        
	    String string = fmt.format(valor);  
	    String[] part = string.split("[,]");  
	    String string2 = part[0]+"."+part[1];  
	    double retorno = Double.parseDouble(string2);  
	    return retorno;  
	} 
	
}
