package exceptions;

@SuppressWarnings("serial")
public class RegisterException extends CustomException{
	
	public final static int ILLEGAL_NAME = 0;
	public final static int ILLEGAL_PASSWORD = 1;
	public final static int NON_MATCHING_PASSWORDS = 2;
	public final static int NAME_EXISTS = 3;
	public final static int OTHER = 4;
	

	public RegisterException(String message, int type) {
		super("Registrations Fehler", message, type);
	}
}
