package exceptions;

@SuppressWarnings("serial")
public abstract class CustomException extends Exception {
	
	protected String title;
	protected int type;
	
	public CustomException(String title, String message, int type) {
		super(message);
		this.title = title;
		this.type = type;
	}
	
	public String getTitle() {
		return title;
	}
	public int getType() {
		return type;
	}

}
