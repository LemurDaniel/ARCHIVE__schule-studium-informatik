package fxControls;

import java.util.function.Supplier;
import java.util.function.UnaryOperator;

import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

public class MinMaxTextField extends CustomTextField<Integer>{
	
	private int min, max;
	private Supplier<Integer> minSupplier, maxSupplier;
	
	private boolean tailAdded;
	private String tail;
	private Supplier<String> tailSupplier;
	private boolean formatText;
	
	public MinMaxTextField(int min, int max) {
		this(min, max, "");
	}
	
	public MinMaxTextField(int min, int max, String schwanz) {
		super(9);	//Integer Limit: 2 147 483 647 -> 10 Zeichen lang -> 9 ist safe
		this.min = min;
		this.max = max;
		this.tail = schwanz;
		tailAdded = false;

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
			return super.getMaxLenFilter().apply(change);
    	}));
	}
	
	public void setMinSupplier(Supplier<Integer> minSupplier) {
		this.minSupplier = minSupplier;
	}
	public void setMaxSupplier(Supplier<Integer> maxSupplier) {
		this.maxSupplier = maxSupplier;
	}
	public void setTailSupplier(Supplier<String> tailSupplier) {
		this.tailSupplier = tailSupplier;
	}
	public void setDefVal(Integer defVal) {
		if(defVal!=null) {
			if(defVal>max)		defVal=max;
			else if(defVal<min)	defVal=min;
		}
		this.defVal = defVal;
		pruefe();
	}
	
	private void trimSchwanz() {
		if(!tailAdded)	return;
		setText( getText().substring(0, getLength()-tail.length()));
		tailAdded = false;
	}
	private void setTextToVal() {		
		if(value==null) {
			setText(null);
			return;
		}
		boolean temp = formatText;
		formatText = false;
		
		if(tailSupplier!=null)	tail=tailSupplier.get();
		setText(value+tail);
		tailAdded = true;
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
		Integer min = null, max = null;
		
		if(minSupplier!=null) min = minSupplier.get();		
		if(maxSupplier!=null) max = maxSupplier.get();	
		
		if(min==null) 	min=this.min;
		if(max==null)	max=this.max;
		
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
