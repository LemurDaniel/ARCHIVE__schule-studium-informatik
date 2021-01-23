package gui.fxControls;

public class StringTextField extends CustomTextField<String>{

	private String last;
	
	public StringTextField(int maxlen) {
		super(maxlen);
	}

	@Override
	protected void pruefe() {	
		if(last!=null && getText()!=null && getText().equals(last)) return;
		
		if(getText()==null || getText().length()==0) {
			String defaultValue = getDefaultValue();
			setText( defaultValue==null? null:defaultValue );
			selectPositionCaret(getLength());	
		}
		last = getText();
		if(valueChanged!=null) valueChanged.accept(this, getText());
	}

}
