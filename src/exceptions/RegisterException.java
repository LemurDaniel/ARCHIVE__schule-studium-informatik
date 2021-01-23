package exceptions;

import test.Sprache;

public class RegisterException extends CustomException{
	
	public final static int ILLEGAL_NAME = 0;
	public final static int ILLEGAL_PASSWORD = 1;
	public final static int NON_MATCHING_PASSWORDS = 2;
	public final static int NAME_EXISTS = 3;
	public final static int OTHER = 4;
	
	String name;
	
	public RegisterException(int type) {
		super();
		this.name = name;
		
		switch(Sprache.lang()) {
		case Sprache.ENGLISH: title="RegisterException"; 
		case Sprache.GERMAN: title="Registrierungsfehler";
		}
	}
}
