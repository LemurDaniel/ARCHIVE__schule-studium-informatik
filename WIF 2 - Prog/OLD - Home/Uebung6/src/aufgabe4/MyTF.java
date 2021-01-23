package aufgabe4;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import javax.swing.JTextField;


public class MyTF extends JTextField {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;




	public Integer intZahl() throws KonvertError {
		try {
			return new Integer(getText());
		}catch(NumberFormatException nfe) {
			throw new KonvertError( "'"+getText()+"' lässt sich in keinen Integer konvertieren");
		}
	}
	
	public Double doubleZahl() throws KonvertError {
		try {
			return new Double(getText());
		}catch(NumberFormatException nfe) {
			throw new KonvertError( "'"+getText()+"' lässt sich in keinen Double konvertieren");
		}
	}
	
	public LocalDate datum() throws KonvertError{
		try {
			return LocalDate.parse(getText());
		}catch(DateTimeParseException dtpe) {
			throw new KonvertError( "'"+getText()+"' lässt sich in kein Datum konvertieren");
		}
	}
	
	
	
	
	public static class KonvertError extends Exception{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		KonvertError(String message){
			super(message);
		}
	}
}
