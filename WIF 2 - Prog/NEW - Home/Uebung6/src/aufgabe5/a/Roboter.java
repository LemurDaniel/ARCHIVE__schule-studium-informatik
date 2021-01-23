package aufgabe5.a;

import java.util.ArrayList;

public class Roboter {
	
	private ArrayList<Winkel> list = new ArrayList<>();
	
	Roboter(){
		list.add(new Winkel(135,      "Körper:Rotieren"));
		list.add(new Winkel(45,       "Schulter:Bewegen"));
		list.add(new Winkel(45,       "Arm:Bewegen"));
		list.add(new Winkel(45,       "Hand:Rotieren"));
		list.add(new Winkel(180, -90, "Hand:Bewegen"));
		list.add(new Winkel(-90, 0,   "Finger:Schließen"));
		
		
		list.add(new Winkel(90,       "Kopf:Drehen"));
		list.add(new Winkel(45, 0, 0, "Kopf:Neigen"));
		list.add(new Winkel(45,       "Bein:Bewegen"));
	}

	
	public void bewege(int element, int betrag, boolean modeR) throws Exception{			
		list.get(element).aendere(betrag, modeR);
	}
	
	public Winkel[] getWinkel() {
		Winkel[] w = new Winkel[list.size()];
		return list.toArray(w);
	}	
}
