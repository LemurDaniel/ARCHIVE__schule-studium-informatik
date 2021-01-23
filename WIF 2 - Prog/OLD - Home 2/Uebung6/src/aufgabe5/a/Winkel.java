package aufgabe5.a;


public class Winkel {
		
		private int max, min, winkel;
		private String bez;
		
		public Winkel(int grenze, String bez ){
			this( Math.abs(grenze), -Math.abs(grenze), bez);
		}
		
		public Winkel(int max, int min, String bez){
			this(max, min, (max+min)/2, bez);
		}
		
		public Winkel(int max, int min, int winkel, String bez){
			this.max = max;
			this.min = min;
			this.winkel = winkel;
			this.bez = bez;
		}
		
		public void aendere(int betrag, boolean modusR) throws Exception {
			if(modusR) 
				betrag += winkel;
			
			if(betrag > max || betrag < min)
				throw new Exception( String.format("Außerhalb des Winkelbereichs:  %d - %d --> Akuteller Winkel: %d", min, max, winkel));
			
			winkel = betrag;	
		}
	
		public int getWinkel() {
			return winkel;
		}
		public String getBez() {
			return bez;
		}
		public int getMax() {
			return max;
		}
		public int getMin() {
			return min;
		}

}