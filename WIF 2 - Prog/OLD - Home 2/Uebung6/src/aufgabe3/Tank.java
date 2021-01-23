package aufgabe3;

public class Tank {
	
	private int max, ist;
	
	public Tank(String sMax) throws Exception{
		max = Integer.parseInt(sMax);
		
		if( max <= 0)
			throw new TankException("Ungültige Tankkapazität: '"+ max+"'");
	}
	
	public void aenderFuellstand(String sMenge) throws Exception {
		int menge = Integer.parseInt(sMenge);
		
		if(ist+menge > max) {
			ist = max;
			throw new TankException("Tank überlaufen: "+(ist+menge-max)+ " Liter zu viel");
		}else if(ist+menge < 0) {
			ist = 0;
			throw new TankException("Zu wenig Flüssigkeit: "+ -(menge+ist)+ " Liter zu wenig");
		}
		ist += menge;
	}
	
	public int getMax() {
		return max;
	}
	
	public int getIst() {
		return ist;
	}
	
	
	
	public static class TankException extends Exception{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		TankException(String message){
			super(message);
		}
	}

}
