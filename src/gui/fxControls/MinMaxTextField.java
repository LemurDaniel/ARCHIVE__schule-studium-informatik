package gui.fxControls;

import java.util.function.Function;
import java.util.function.Supplier;

import javafx.scene.control.TextFormatter.Change;

abstract class MinMaxTextField<T extends Number> extends CustomTextField<T>{
	
	private T value;
	private T min, max;
	private Supplier<T> minSupplier, maxSupplier;
	
	private String tail;	
	private Function<T, String> textFunction;
	
	protected MinMaxTextField(T min, T max, int length) {
		this(min, max, "", length);
	}
	
	protected MinMaxTextField(T min, T max, String tail, int length) {
		super(length);
		this.min = min;
		this.max = max;
		this.tail = tail==null? "":tail;
	
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
			parseText(change.getControlNewText());
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
	public void setTextFunction(Function<T, String> function) {
		textFunction = function;
	}

	
	private void setTextToValue() {		
		if(value==null) {
			setText("");
			return;
		}
		boolean temp = formatText;
		formatText = false;
		
		if(textFunction==null)	setText(value+tail);
		else					setText(textFunction.apply(value));
		selectPositionCaret(getLength());
		
		formatText = temp;
	}	
	
	@Override
	protected void pruefe() {
		T newV;
		try {
			newV = parseText(getText());	
			if(newV!=null)	newV = pruefeMinMax(getMin(), getMax(), newV);
		}catch(NumberFormatException nfe) {
			newV = getDefaultValue();
		}
		if( (value==null && newV!=value) || (value!=null && !value.equals(newV)) ) {
			value = newV;
			if(valueChanged!=null) valueChanged.accept(this, value);
		}
		setTextToValue();
	}	
	
		
	@Override
	public void setDefaultValue(T defaultValue) {
		defaultValue = defaultValue!=null ? pruefeMinMax(getMin(), getMax(), defaultValue) : defaultValue;
		super.setDefaultValue(defaultValue);
	}
	@Override
	protected T getDefaultValue(){
		T defaultValue = super.getDefaultValue();
		return defaultValue==null? null:pruefeMinMax(getMin(), getMax(), defaultValue);
	}
	
	
	
	protected T getMin() {
		T min = minSupplier==null? null:minSupplier.get();
		return min==null? this.min:min;
	}
	protected T getMax() {
		T max = maxSupplier==null? null:maxSupplier.get();
		return max==null? this.max:max;
	}
	public T getValue() {
		return value;
	}
	
	
	
	abstract protected T pruefeMinMax(T min, T max, T wert);
	abstract protected T parseText(String text) throws NumberFormatException;
}
