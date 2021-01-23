package fxControls;

import java.util.function.UnaryOperator;

import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

public class MinMaxTextField extends TextField{
	
	private int min, max;
	private MinMaxTextField mintf, maxtf;
	private Integer value, defVal;
	private String schwanz;
	private boolean formatText;
	
	public MinMaxTextField(int min, int max, String schwanz) {
		super();
		this.min = min;
		this.max = max;
		this.schwanz = schwanz;
		value = null;
		defVal = null;
		
		focusedProperty().addListener((ob,ov,focus)->{
			formatText = focus;
			if(focus==false)
				pruefe();
			else if(getText()!=null)
				setText( getText().replaceFirst(schwanz, ""));		
		});
		
		setTextFormatter(new TextFormatter<>( (UnaryOperator<TextFormatter.Change>) change->{
			if(!formatText) return change;
			change.setText( change.getText().replaceAll("[^0-9]",	""));
			// Mögliche NumberFormatException wenn nach Integer geparster Text größer als 9 Zeichen ist.
			if(change.getControlNewText().length()>=9) {
				int z =  9 - (change.getControlNewText().length() - change.getText().length());
				change.setText( change.getText().substring(0, z) );
			}
    		return change;
    	}));
	}
	
	public void setMintf(MinMaxTextField mintf) {
		this.mintf = mintf;
	}
	public void setMaxtf(MinMaxTextField maxtf) {
		this.maxtf = maxtf;
	}
	public void setDefVal(Integer defVal) {
		if(defVal>max)		defVal=max;
		else if(defVal<min)	defVal=min;
		this.defVal = defVal;
		pruefe();
	}
	
	
	private void pruefe() {

		if(getText()==null || getText().length()==0) {
			value = defVal;
			if(value!=null) setText(defVal+schwanz);
			return;
		}
			
		value = Integer.parseInt( getText().replaceAll(schwanz, "") );
		int min = this.min;
		int max = this.max;
		
		if(mintf != null && mintf.getValue()!=null) 
			min = mintf.getValue();		
		if(maxtf != null && maxtf.getValue()!=null) 
			max = maxtf.getValue();
		
		
		if(value > max)
			value = max;
		else if(value < min)
			value = min;
		
		setText(value+schwanz);
	}
	
	public Integer getValue() {
		return value;
	}
}
