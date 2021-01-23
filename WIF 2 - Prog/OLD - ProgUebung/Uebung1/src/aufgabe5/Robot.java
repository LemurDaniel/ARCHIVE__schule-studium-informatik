package aufgabe5;

import Bla.Einlesen;

public class Robot {
	
	//
	public static class Est{
		char[] n1;
		int[] i1;
		
		Est(char[] n1, int... i1){
			this.n1 = n1;
			this.i1 = i1;
		}
	}
	
	public  static final Est KRP = new Est( new char[]{'K', 'R'}, -135, 135);
	public  static final Est SHT = new Est( new char[]{'S', 'B'}, -45, 45) ;
	public  static final Est ARM = new Est( new char[]{'A', 'B'}, -45, 45);
	public  static final Est HND = new Est( new char[]{'H', 'R', 'B'}, -90, 180, -45, 45);
	public  static final Est FNG = new Est( new char[]{'F', 'S'}, 0, 180);
	public  static final Est[] ALL = new Est[] { KRP, SHT, ARM, HND, FNG };
	
	//
	private class Teil{
		
		//
		public class Winkel {
			char name;
			private int max, min, winkel;
			
			Winkel(char name, int min, int max){
				this.name = name;
				this.min = min;
				this.max = max;
				winkel = 0;
			}
			
			public String change(char modus, int wert) {
				
				int w, w2;
				if (modus == 'A') 
					w = winkel+wert;
				else if (modus == 'R') 
					w = ( (winkel*wert)/100 ) * (wert >= 0 ? 1:-1);
				else
					return "Die Winkeloperation '" + modus + "' ist nicht bekannt";
				
				if (w > max || w < min )
					return String.format("Winkelbereich: %d bis %d, Aktueller Winkel: %d, Neuer Winkel %d liegt außerhalb des Winkelbereichs", min, max, winkel, w);
				
				w2 = winkel;
				winkel = w;
				return   String.format("Winkelbereich: %d bis %d, Alter Winkel: %d, Neuer Winkel %d wurde gesetzt", min, max, w2, winkel);
			}
		}
		
		
		//
		private char name;
		private Winkel[] winkel;
		
		
		Teil(Est est){
			name = est.n1[0];
			winkel = new Winkel[est.n1.length-1];
			
			for(int i=0, i2=0; i<winkel.length; i++, i2+=2) {
				winkel[i] = new Winkel(est.n1[i+1], est.i1[i2], est.i1[i2+1]);
			}
			
		}
					
	}
	
	
	//
	private Teil[] teile;
	private String name;
	
	Robot(String name, Est... ests){
		this.name = name;
		
		String t = "";
		String t2 = "";
		for(int i=0; i<ests.length; i++) {
			
			boolean test = false;
			for(int i2=0; i2<t.length(); i2++) {
				if (ests[i].n1[0] == t.charAt(i2)) {
					test = true;
				}
			}
			
			if(!test) {
				t += ests[i].n1[0];
				t2 += i + "|";
			}	
		}
		
		String t3[] = t2.split("\\|");
		
		teile = new Teil[t3.length];
		for(int i=0; i<teile.length; i++) {
			teile[i] = new Teil( ests[Integer.valueOf(t3[i])] );
		}
		
	}
	
	public void alleEingaben() {
		String b;
		for(Teil t:teile) {
			for(int i=0; i<t.winkel.length; i++) {
				b = t.name +"" + t.winkel[i].name + "A";
				System.out.println(" *** " + b + " *** ");
				System.out.println("	" + eingabe(b, 1) + "\n");
				
				b = t.name +"" + t.winkel[i].name + "R";
				System.out.println(" *** " + b + " *** ");
				System.out.println("	" + eingabe(b, 1) + "\n");
			}
		}
		
	}
	
	public String eingabe(String b, int w) {
		
		int kommando = 0;

		if(b.length() != 3)
			 return "ungültige Eingabe";
			
		b.toUpperCase();
		int objekt = -1;
		int winkel = -1;
		for(int i=0; i<teile.length; i++) {
			

			if( teile[i].name == b.charAt(0) ) {
				objekt = i;			
				
				for(int i2=0; i2<teile[i].winkel.length; i2++) {
					if ( teile[i].winkel[i2].name == b.charAt(1) ) {
						winkel = i2;
						break;
					}
					kommando+=2;
				}
				break;
			}
			kommando += teile[i].winkel.length*2;
		};
			
		
		if(objekt == -1)
			return "Objekt existiert nicht";
		else if(winkel == -1)
			return "Die Operation '" + b.charAt(1) + "' steht für Teil '" + b.charAt(0) + "' nicht zur verfügung!";
		else {
			w = (w==0 ? Einlesen.liesInt("Winkel> "):w) ;
			kommando += (b.charAt(2)=='A' ? 1:2);
			System.out.println("<>Kommando " + kommando + " wird ausgeführt<>");
			return teile[objekt].winkel[winkel].change(b.charAt(2), w);
		}

	}
	
	public void operate() {
		System.out.println("");
		System.out.println(" ***	*** ");
		System.out.println("Roboter: " + name);
		System.out.println();
		
		String b = Einlesen.liesString("Befehl> ");
		System.out.println("\n	" + eingabe(b, 0));
	}
	
}

