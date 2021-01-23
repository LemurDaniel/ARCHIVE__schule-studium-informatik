package aufgabe2;

import java.math.BigDecimal;

import javax.swing.JTextField;

public class Rechner{
	
	
	
	public static double berechneDouble(JTextField arg1, JTextField arg2, String operation) throws Exception{
		double erg, num1, num2;
		try {
			num1 = Double.parseDouble(arg1.getText());
			num2 = Double.parseDouble(arg2.getText());
			
			if (num2 == 0 && operation == "/")
				throw new ArithmeticException("Division durch Null" );

			switch(operation) {
			case "+": erg = num1+num2; break;
			case "-": erg = num1-num2; break;
			case "*": erg = num1*num2; break;
			case "/": erg = num1/num2; break;
			default: erg=0;
			}
			
			if (Double.isInfinite(erg))
				throw new ArithmeticException(" ergebnis ist unendlich ");
				
			return erg;	
		}catch(NumberFormatException nfe) {
			throw new NumberFormatException(nfe.getMessage() + " ist keine gültiger Double-Wert");
		}	
		
	}
	
	
	
	public static int berechneInt(JTextField arg1, JTextField arg2, String operation) throws Exception{
		int erg, num1, num2;
		try {
			num1 = Integer.parseInt(arg1.getText());
			num2 = Integer.parseInt(arg2.getText());
			
			if (num2 == 0 && operation == "/")
				throw new ArithmeticException("Division durch Null" );

			switch(operation) {
			case "+": erg = num1+num2; break;
			case "-": erg = num1-num2; break;
			case "*": erg = num1*num2; break;
			case "/": erg = num1/num2; break;
			default: erg=0;
			}
			
			return erg;	
		}catch(NumberFormatException nfe) {
			throw new NumberFormatException(nfe.getMessage() + " ist keine gültiger Integer-Wert");
		}
	}
	
	
	public static Number berechne(String arg1, String arg2, String operation) throws Exception {
		BigDecimal num1, num2, erg=null;
		
		num2 = new BigDecimal(arg2);
		num1 = new BigDecimal(arg1);
		
		
		switch(operation) {
		case "+": erg = num1.add(num2); break;
		case "-": erg = num1.subtract(num2); break;
		case "*": erg = num1.multiply(num2); break;
		case "/": 	if (num2.equals(BigDecimal.ZERO))
						throw new Exception("Ergebnis nicht verwertbar (Division durch Null) ");
					erg = num1.divide(num2); 
					break;
		}
		if (Double.isInfinite(erg.doubleValue()))
			throw new ArithmeticException("Ergebnis ist unendlich");
			
		return erg;
	}
	
	
}
