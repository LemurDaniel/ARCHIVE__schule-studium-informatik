package fxControls;

import java.util.function.Supplier;
import java.util.function.UnaryOperator;

import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

public class MinMaxTextField extends TextField{
	
	private int min, max;
	private MinMaxTextField mintf, maxtf;
	private Integer value, defVal;
	
	private boolean schwanzAdded;
	private String schwanz;
	private Supplier<String> setSchwanzF;
	private boolean formatText;
	
	public MinMaxTextField(int min, int max) {
		this(min, max, "");
	}
	
	public MinMaxTextField(int min, int max, String schwanz) {
		super();
		this.min = min;
		this.max = max;
		this.schwanz = schwanz;
		schwanzAdded = false;
		value = null;
		defVal = null;
		
		focusedProperty().addListener((ob,ov,focus)->{
			formatText = focus;
			if(focus==false)
				pruefe();
			else if(getText()!=null)
				trimSchwanz();		
		});
		
		setTextFormatter(new TextFormatter<>( (UnaryOperator<TextFormatter.Change>) change->{
			if(!formatText) return change;
			change.setText( change.getText().replaceAll("[^0-9]",	""));
			// M�gliche NumberFormatException wenn nach Integer geparster Text gr��er als 9 Zeichen ist.
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
	public void setSchwanzF(Supplier<String> f) {
		this.setSchwanzF = f;
	}
	
	private void trimSchwanz() {
		if(!schwanzAdded)	return;
		setText( getText().substring(0, getLength()-schwanz.length()));
		schwanzAdded = false;
	}
	private void setTextToVal() {		
		if(value==null) {
			setText(null);
			return;
		}
		boolean temp = formatText;
		formatText = false;
		
		if(setSchwanzF!=null)	schwanz=setSchwanzF.get();
		setText(value+schwanz);
		schwanzAdded = true;
		selectPositionCaret(getLength());
		
		formatText = temp;
	}
	
	
	private void pruefe() {
		
		trimSchwanz();
		if(getText()==null || getText().length()==0) {
			value = defVal;
			setTextToVal();
			return;
		}
			
		value = Integer.parseInt( getText() );
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
		
		setTextToVal();
	}
	
	public Integer getValue() {
		return value;
	}
}
