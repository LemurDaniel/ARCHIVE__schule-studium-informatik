package exceptions;

@SuppressWarnings("serial")
public class LogInException extends CustomException {
	
	//public final static int NO_USER_LOGGED_IN = 0;
//	public final static int ALREADY_LOGGED_IN = 1;
//	public final static int NO_USER = 2;
//	public final static int WRONG_PASSWORD = 3;
	
	public final static Typ<LogInException> ALREADY_LOGGED_IN	= new Typ<>(LogInException.class, 0);
	public final static Typ<LogInException> NO_USER 			= new Typ<>(LogInException.class, 1);
	public final static Typ<LogInException> WRONG_PASSWORD 		= new Typ<>(LogInException.class, 2);
	public final static Typ<LogInException> OTHER 				= new Typ<>(LogInException.class, 3);
	
	String name;
	
	public LogInException(String message, Typ<LogInException> typ) {
		super(getTitel(typ), message, typ);
	}
	
	private static String getTitel(Typ<LogInException> typ) {
		String titel = "Login Fehler - ";
		if(typ.isTyp(ALREADY_LOGGED_IN))	return titel+"Bereits angemeldett";
		if(typ.isTyp(NO_USER)) 				return titel+"Kein Konto gefunden";
		if(typ.isTyp(WRONG_PASSWORD))		return titel+"Falsches Passwort";
											return titel+"Unbekannter Fehler";
		
	}

}
	
	

