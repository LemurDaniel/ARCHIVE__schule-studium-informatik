package fxControls;

public class StringTextField extends CustomTextField<String>{

	public StringTextField(int maxlen) {
		super(maxlen);
	}

	@Override
	protected void pruefe() {		
		if(getText()==null || getText().length()==0) {
			setText( defVal==null? null:defVal.toString() );
			selectPositionCaret(getLength());
		}
	}

	@Override
	public void setDefVal(String defVal) {
		this.defVal = defVal;
		setText( defVal==null? null:defVal.toString() );
		selectPositionCaret(getLength());	
	}

}
