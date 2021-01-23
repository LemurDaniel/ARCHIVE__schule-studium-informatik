package gui.fxControls;

import java.util.function.BiConsumer;
import java.util.function.UnaryOperator;

import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;

public abstract class CustomTextField<T> extends TextField{
	
	private T defaultValue;
	private int maxlen;
	
	protected boolean formatText;
	protected BiConsumer<CustomTextField<T>, T> valueChanged;
	
	public CustomTextField(int maxlen) {
		this.maxlen = maxlen;
		defaultValue = null;
	
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
	
	
	public void setDefaultValue(T defaultValue) {
		this.defaultValue = defaultValue;
		setText(null);
		pruefe();
	};	
	protected T getDefaultValue() {
		return defaultValue;
	}
		
	public void setValueChanged(BiConsumer<CustomTextField<T>, T> valueChanged) {
		this.valueChanged = valueChanged;
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
