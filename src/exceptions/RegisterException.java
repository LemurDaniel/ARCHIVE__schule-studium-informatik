package exceptions;

@SuppressWarnings("serial")
public class RegisterException extends CustomException{
	
//	public final static int ILLEGAL_NAME = 0;
//	public final static int ILLEGAL_PASSWORD = 1;
//	public final static int NON_MATCHING_PASSWORDS = 2;
//	public final static int NAME_EXISTS = 3;
//	public final static int OTHER = 4;
	
	public final static Typ<RegisterException> ILLEGAL_NAME 			= new Typ<>(RegisterException.class, 0);
	public final static Typ<RegisterException> ILLEGAL_PASSWORD	 		= new Typ<>(RegisterException.class, 1);
	public final static Typ<RegisterException> NON_MATCHING_PASSWORDS 	= new Typ<>(RegisterException.class, 2);
	public final static Typ<RegisterException> NAME_EXISTS 				= new Typ<>(RegisterException.class, 3);
	public final static Typ<RegisterException> OTHER 					= new Typ<>(RegisterException.class, 4);
	
	public RegisterException(String message, Typ<RegisterException> type) {
		super("Registrations Fehler", message, type);
	}
}
