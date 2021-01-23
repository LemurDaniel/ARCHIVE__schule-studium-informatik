package test;

public class Sprache {
	
	public final static int ENGLISH = 0;
	public final static int GERMAN = 1;
	private static int lang = GERMAN;
	
	private Sprache() {}
	
	public static int lang() {
		return lang;
	}
	public static void setlang(int i) {
		switch(i) {
		case ENGLISH: lang=ENGLISH; break;
		case GERMAN: lang=GERMAN; break;
		default:
			lang=GERMAN;
		}
	}
}
