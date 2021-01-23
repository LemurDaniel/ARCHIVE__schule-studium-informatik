package fxControls;

import java.util.function.Supplier;

import javafx.scene.control.TextFormatter.Change;

abstract class MinMaxTextField<T extends Number> extends CustomTextField<T>{
	
	private T value;
	private T min, max;
	private Supplier<T> minSupplier, maxSupplier;
	
	private String tail;
	private Supplier<String> tailSupplier;
	
	protected MinMaxTextField(T min, T max, int length) {
		this(min, max, "", length);
	}
	
	protected MinMaxTextField(T min, T max, String tail, int length) {
		super(length);
		this.min = min;
		this.max = max;
		this.tail = tail;

		focusedProperty().addListener((ob,ov,focus)->{
			if(focus==true)	setText( value+"" );	
		});		
	}
	
	@Override
	protected Change filter(Change change) {
		if(!formatText) return change;
		if(change.getControlNewText().equals("-") || change.getControlNewText().length()==0)	return change;		
		change = super.filter(change);
		try {
			parseTextAufValue(change.getControlNewText());
		}catch(NumberFormatException nfe) {
			change.setText("");
		}
		return change;
	}
		
	
	public void setMinSupplier(Supplier<T> minSupplier) {
		this.minSupplier = minSupplier;
	}
	public void setMaxSupplier(Supplier<T> maxSupplier) {
		this.maxSupplier = maxSupplier;
	}
	public void setTailSupplier(Supplier<String> tailSupplier) {
		this.tailSupplier = tailSupplier;
	}
	
	
	
	private void setTextToValue() {		
		if(value==null) {
			setText(null);
			return;
		}
		boolean temp = formatText;
		formatText = false;
		
		if(tailSupplier!=null)	tail=tailSupplier.get();
		setText(value+tail);
		selectPositionCaret(getLength());
		
		formatText = temp;
	}
	
	
	
	@Override
	protected void pruefe() {	
		try {
			value = parseTextAufValue(getText());	
			if(value!=null)	value = pruefeMinMax(getMin(), getMax(), value);
		}catch(NumberFormatException nfe) {
			value = getDefaultValue();
		}
		setTextToValue();
	}	
	@Override
	public void setDefaultValue(T defaultValue) {
		defaultValue = defaultValue!=null ? pruefeMinMax(getMin(), getMax(), defaultValue) : defaultValue;
		super.setDefaultValue(defaultValue);
		value = defaultValue;
		setTextToValue();
	}
	@Override
	protected T getDefaultValue(){
		return pruefeMinMax(getMin(), getMax(), super.getDefaultValue());
	}
	
	
	
	protected T getMin() {
		if(minSupplier!=null && minSupplier.get()!=null) return minSupplier.get();	
		return min;
	}
	protected T getMax() {
		if(maxSupplier!=null && maxSupplier.get()!=null) return maxSupplier.get();	
		return max;
	}
	
	
	
	abstract protected T pruefeMinMax(T min, T max, T wert);
	abstract protected T parseTextAufValue(String text) throws NumberFormatException;

	
	
	public T getValue() {
		return value;
	}
}
