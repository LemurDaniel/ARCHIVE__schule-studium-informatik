package fxControls;

public class StringTextField extends CustomTextField<String>{

	public StringTextField(int maxlen) {
		super(maxlen);
	}

	@Override
	protected void pruefe() {		
		if(getText()==null || getText().length()==0) {
			setText( defaultValue==null? null:defaultValue.toString() );
			selectPositionCaret(getLength());
		}
	}

	@Override
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
		setText( defaultValue==null? null:defaultValue.toString() );
		selectPositionCaret(getLength());	
	}

}
