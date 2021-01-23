package fxControls;

import java.util.function.UnaryOperator;

import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

public class CustomTextField<T> extends TextField{
	
	protected T defVal;
	protected boolean formatText;
	private int maxlen;
	
	public CustomTextField(int maxlen) {
		super();
		this.maxlen = maxlen;
		defVal = null;
		setTextFormatter(new TextFormatter<>( getMaxLenFilter() ));
		
		focusedProperty().addListener((ob,ov,focus)->{
			formatText = focus;
			if(focus==false)
				pruefe();
		});
	}
	protected UnaryOperator<TextFormatter.Change> getMaxLenFilter(){
		return (UnaryOperator<TextFormatter.Change>) change ->{
			if(change.getControlNewText().length()>maxlen) {
				int z = maxlen - (change.getControlNewText().length() - change.getText().length());
				change.setText( change.getText().substring(0, z) );
			}
			return change;	
		};
	}
	
	public void setDefVal(T defVal) {
		this.defVal = defVal;
		setText( defVal==null? null:defVal.toString() );
		selectPositionCaret(getLength());
	}	
	
	protected void pruefe() {			
		if(getText()==null || getText().length()==0) {
			setText( defVal==null? null:defVal.toString() );
			selectPositionCaret(getLength());
		}	
	}

}
