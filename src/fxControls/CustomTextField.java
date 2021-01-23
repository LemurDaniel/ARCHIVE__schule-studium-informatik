package fxControls;

import java.util.function.UnaryOperator;

import com.google.common.base.Supplier;

import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;

public abstract class CustomTextField<T> extends TextField{
	
	private T defaultValue;
	private Supplier<T> defaultSupplier;
	
	protected boolean formatText;
	private int maxlen;
	
	
	
	public CustomTextField(int maxlen) {
		this.maxlen = maxlen;
		defaultValue = null;
		defaultSupplier = null;
		
		setTextFormatter(new TextFormatter<>(this::filter));
		
		focusedProperty().addListener((ob,ov,focus)->{
			formatText = focus;
			if(focus==false)	pruefe();
		});
	}
	
	
	protected Change filter(Change change){
		if(!formatText) return change;	
		getMaxLenFilter(maxlen).apply(change);
		return change;	
	}
	
	
	public void setDefaultSupplier(Supplier<T> defaultSupplier) {
		this.defaultSupplier = defaultSupplier;
		pruefe();
	}
	public void setDefaultValue(T defaultValue) {
		this.defaultValue = defaultValue;
	};	
	protected T getDefaultValue() {
		if(defaultSupplier!=null)	return defaultSupplier.get();
		return defaultValue;
	}
	
	
	abstract protected void pruefe();
	
	
	
	
	
	
	
	
	public static UnaryOperator<Change> getMaxLenFilter(int len) {
		return (UnaryOperator<Change>) change->{
			if(change.getControlNewText().length()>len) {
				int z = len - (change.getControlNewText().length() - change.getText().length());
				change.setText( change.getText().substring(0, z) );
			}
			return change;
		};
	}

}
