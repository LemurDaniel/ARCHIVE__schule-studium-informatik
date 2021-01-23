package fxControls;

import java.util.function.UnaryOperator;

import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;

abstract class CustomTextField<T> extends TextField{
	
	protected T defVal;
	protected boolean formatText;
	private int maxlen;
	
	public CustomTextField(int maxlen) {
		this.maxlen = maxlen;
		defVal = null;
		setTextFormatter(new TextFormatter<>(this::filter));
		
		focusedProperty().addListener((ob,ov,focus)->{
			formatText = focus;
			if(focus==false)
				pruefe();
		});
	}
	protected Change filter(Change change){
		if(!formatText) return change;
		
		if(change.getControlNewText().length()>maxlen) {
			int z = maxlen - (change.getControlNewText().length() - change.getText().length());
			change.setText( change.getText().substring(0, z) );
		}
		return change;	
	}
	
	abstract public void setDefVal(T defVal);	
	abstract protected void pruefe();

}
