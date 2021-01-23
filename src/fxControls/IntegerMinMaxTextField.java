package fxControls;

import java.util.function.UnaryOperator;

import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;

public class IntegerMinMaxTextField extends MinMaxTextField<Integer>{

	public IntegerMinMaxTextField(Integer min, Integer max) {
		this(min, max, "");
	}
	
	public IntegerMinMaxTextField(Integer min, Integer max, String tail) {
		super(min, max, tail, 9);
	}
	
	@Override
	protected Integer pruefeMinMax(Integer min, Integer max, Integer wert) {
		if(wert<min)	return min;
		if(wert>max)	return max;
		return wert;
	}
	@Override
	protected Integer parseTextAufValue(String text) throws NumberFormatException{
		System.out.println(text);
		return Integer.parseInt(text);
	}

	
}
