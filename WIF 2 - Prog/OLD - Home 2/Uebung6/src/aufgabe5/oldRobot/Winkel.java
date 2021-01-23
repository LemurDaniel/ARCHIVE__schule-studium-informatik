package aufgabe5.oldRobot;

public class Winkel {
		
		private static int count = 0;
		private int max, min, winkel, id;
		
		public Winkel(int grenze){
			this( Math.abs(grenze), -Math.abs(grenze));
		}
		
		public Winkel(int max, int min){
			this.max = max;
			this.min = min;
			winkel = (max+min)/2;
			id = ++count;
		}
		
		public void aendere(int betrag, String modus) throws Exception {
			if(modus.toUpperCase().equals("R")) 
				betrag += winkel;
			else if(!modus.toUpperCase().equals("A"))
				throw new Exception("Ungültiger Befehl: Absolut[A] | Relativ[R] --> Eingabe: "+modus);
			
			if(betrag > max || betrag < min)
				throw new Exception( String.format("Außerhalb des Winkelbereichs:  %d - %d --> Akuteller Winkel: %d", min, max, winkel));
			
			winkel = betrag;	
		}
	
		public int getId() {
			return id;
		}
		public int getWinkel() {
			return winkel;
		}
}