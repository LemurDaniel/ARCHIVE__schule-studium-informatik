package fxControls;

import java.util.function.UnaryOperator;

import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

public class MinMaxTextField extends TextField{
	
	private int min, max;
	private MinMaxTextField mintf, maxtf;
	private Integer value;
	private String schwanz;
	private boolean formatText;
	
	public MinMaxTextField(int min, int max, String schwanz) {
		super("");
		this.min = min;
		this.max = max;
		this.schwanz = schwanz;
		value = null;	
		
		focusedProperty().addListener((ob,ov,focus)->{
			formatText = focus;
			if(focus==false)
				pruefe();
			else if(getText()!=null)
				setText( getText().replaceFirst(schwanz, ""));		
		});
		
		setTextFormatter(new TextFormatter<>( (UnaryOperator<TextFormatter.Change>) change->{
			if(formatText)
				change.setText( change.getText().replaceAll("[^0-9]",	""));
    		return change;
    	}));
	}
	
	public void setMintf(MinMaxTextField mintf) {
		this.mintf = mintf;
	}
	public void setMaxtf(MinMaxTextField maxtf) {
		this.maxtf = maxtf;
	}
	
	
	private void pruefe() {

		if(getText()==null || getText().length()==0) {
			value = null;
			return;
		}
			
		value = Integer.parseInt( getText().substring(0,  getText().length()>10? 10:getText().length()) );
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
