package aufgabe5.oldRobot;

import javapack.Einlesen;

public abstract class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Roboter r = new Roboter();
		
		do {
			String b = Einlesen.liesString("Befehl: ");
			int w = Einlesen.liesInt("Winkel: ");
			
			try {
				Winkel wl = r.bewege(b, w);
				System.out.println("KommandoNr: " +wl.getId());
				System.out.println("Neuer Winkel: "+wl.getWinkel());
			}catch(Exception e) {
				System.out.println();
				System.out.println("!!!! FEHLER !!!!");
				System.out.println(e.getMessage());
				System.out.println("!!!! FEHLER !!!!");
				System.out.println("");
			}
			System.out.println();
		}while(true);
	}

}
