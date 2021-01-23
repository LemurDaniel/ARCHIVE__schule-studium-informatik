package fxControls;

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
	protected Integer parseText(String text) throws NumberFormatException{
		return Integer.parseInt(text);
	}

	
}
