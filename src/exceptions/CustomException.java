package exceptions;

@SuppressWarnings("serial")
abstract class CustomException extends Exception {
	
	protected int message_type;
	protected String message;
	protected String title;
	
	public CustomException() {
		
	}
	
	public String getTitle() {
		return title;
	}
	@Override
	public String getMessage() {
		return message;
	}
}
