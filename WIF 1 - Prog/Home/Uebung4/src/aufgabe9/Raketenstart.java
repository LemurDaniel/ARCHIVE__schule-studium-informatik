package aufgabe9;

import javapack.Einlesen;

public class Raketenstart {

	public static void main(String[] args) {

		boolean freigabe, raketenstart;
		int countdown;
		
		freigabe = Einlesen.liesBoolean("Freigabe> ");
		countdown = Einlesen.liesInt("Countdown> ");
		raketenstart = freigabe && countdown == 0;
		
		if(raketenstart) {
			System.out.println("LIFT OFF!!!");
		} else if (freigabe) {
			System.out.println("Start in t-" + countdown);
		} else {
			System.out.println("Erwarte Freigabe... ");
		}
		

	}

}
