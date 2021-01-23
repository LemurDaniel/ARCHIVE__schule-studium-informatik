package fxControls;

public class FloatMinMaxTextField extends MinMaxTextField<Float> {

	private int decimals;
	
	public FloatMinMaxTextField(Float min, Float max) {
		this(min, max, "");
	}
	public FloatMinMaxTextField(Float min, Float max, String tail) {
		this(min, max, tail, 1);
	}
	public FloatMinMaxTextField(Float min, Float max, String tail, int decimals) {
		super(min, max, tail, 60);
		this.decimals = decimals;
	}
	
	@Override
	protected Float pruefeMinMax(Float min, Float max, Float wert) {
		if(wert<min)	return min;
		if(wert>max)	return max;
		return wert;
	}
	@Override
	protected Float parseTextAufValue(String text) throws NumberFormatException{
		float val = Float.parseFloat(text);
		return (int)(val*(10*decimals)) / (10f*decimals);
	}

}
