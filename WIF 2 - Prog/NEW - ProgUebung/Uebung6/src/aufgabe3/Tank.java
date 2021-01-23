package aufgabe3;

public class Tank {

	private int max, ist;

	Tank(int max){
		this.max = max;
	}
	
	public void fuellen(int menge) throws TankException{
		int delta = 0;
		if(menge+ist <= max)	ist += menge;
		else {
			delta = ist+menge-max;
			ist = max;
			throw new TankException("Überschuss: ",delta);
		}
			
	}

	public void leeren(int menge) throws TankException{
		int delta = 0;
		if(ist-menge >= 0)	ist -= menge;
		else {
			delta = -(ist-menge);
			ist = 0;
			throw new TankException("Fehlmenge: ", delta);
		}
	}
	
	
	
	
	public int getMax() {
		return max;
	}
	public int getIst() {
		return ist;
	}
}
