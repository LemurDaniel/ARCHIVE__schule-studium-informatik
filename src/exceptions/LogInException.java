package exceptions;

@SuppressWarnings("serial")
public class LogInException extends CustomException {
	
	public final static int NO_USER_LOGGED_IN = 0;
	public final static int ALREADY_LOGGED_IN = 1;
	public final static int NO_USER = 2;
	public final static int WRONG_PASSWORD = 3;
	public final static int OTHER = 4;
	
	String name;
	
	public LogInException(String message, int type) {
		super("Login Fehler", message, type);
	}
}
	
	

