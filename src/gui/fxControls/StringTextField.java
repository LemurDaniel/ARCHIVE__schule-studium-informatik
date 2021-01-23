package gui.fxControls;

public class StringTextField extends CustomTextField<String>{

	public StringTextField(int maxlen) {
		super(maxlen);
	}

	@Override
	protected void pruefe() {			
		if(getText()==null || getText().length()==0) {
			String defaultValue = getDefaultValue();
			setText( defaultValue==null? null:defaultValue.toString() );
			selectPositionCaret(getLength());	
		}
	}

}
