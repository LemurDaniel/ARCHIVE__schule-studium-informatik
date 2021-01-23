package fxControls;

import java.util.function.Supplier;
import java.util.function.UnaryOperator;

import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

public class MinMaxTextField extends CustomTextField<Integer>{
	
	private Integer value;
	private int min, max;
	private Supplier<Integer> minSupplier, maxSupplier;
	
	private String tail;
	private Supplier<String> tailSupplier;
	
	public MinMaxTextField(int min, int max) {
		this(min, max, "");
	}
	
	public MinMaxTextField(int min, int max, String tail) {
		super(9);	//Integer Limit: 2 147 483 647 -> 10 Zeichen lang -> 9 ist safe
		this.min = min;
		this.max = max;
		this.tail = tail;

		focusedProperty().addListener((ob,ov,focus)->{
			if(focus==true)	setText( value+"" );	
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
	@Override
	public void setDefVal(Integer defVal) {
		if(defVal!=null) {
			if(defVal>max)		defVal=max;
			else if(defVal<min)	defVal=min;
		}
		this.defVal = defVal;
		value = defVal;
		setTextToVal();
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
		selectPositionCaret(getLength());
		
		formatText = temp;
	}
	
	@Override
	protected void pruefe() {
		
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
		
		if(value > max)			value = max;
		else if(value < min)	value = min;
		
		setTextToVal();
	}

	
	public Integer getValue() {
		return value;
	}
}
