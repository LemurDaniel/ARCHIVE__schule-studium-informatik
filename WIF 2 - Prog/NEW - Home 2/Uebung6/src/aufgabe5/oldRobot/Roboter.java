package aufgabe5.oldRobot;

import java.util.HashMap;

public class Roboter {
	
	HashMap<String, Winkel> list = new HashMap<>();
	
	Roboter(){
		list.put("KR", new Winkel(135));
		list.put("SB", new Winkel(45));
		list.put("AB", new Winkel(45));
		list.put("HR", new Winkel(45));
		list.put("HB", new Winkel(-90, 180));
		list.put("FS", new Winkel(0, 180));
	}
	
	
	public Winkel bewege(String befehl, int betrag) throws Exception{
		if(befehl.length() != 3) 
			throw new Exception("Ungültiger Befehl --> "+befehl);
		
		String sub = befehl.substring(0, 2).toUpperCase();
		if( !list.containsKey(sub) )
			throw new Exception("Ungültiger Befehl: Winkel nicht vorhanden");
		
		Winkel w = list.get(befehl.substring(0, 2));
		w.aendere(betrag, befehl.charAt(2)+"");
		return w;
	}
	
	
}
