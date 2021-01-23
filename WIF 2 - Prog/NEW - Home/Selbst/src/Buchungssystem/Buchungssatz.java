package Buchungssystem;

import java.util.Map;
import java.util.TreeMap;

import Buchungssystem.Fehler.BuchungsFehler;

public class Buchungssatz {
	
	private Map<Konto, Integer> soll, haben;
	
	public Buchungssatz(Buchung[] soll, Buchung[] haben) throws Exception{
		int sumSoll = 0, sumHaben = 0;
		this.soll = new TreeMap<>();
		this.haben = new TreeMap<>();

		for (int i = 0; i < soll.length; i++) {			
			this.soll.put(soll[i].konto, soll[i].betrag);
			sumSoll += soll[i].betrag;
		}
		
		for (int i = 0; i < haben.length; i++) { 
			if(this.soll.containsKey(haben[i].konto)) throw new BuchungsFehler("Das Konto "+haben[i].konto+" befindet sich auf der Haben und Sollseite");
			this.haben.put(haben[i].konto, haben[i].betrag);
			sumHaben += haben[i].betrag;
		}
		
		if(sumSoll != sumHaben)	throw new BuchungsFehler("Sollseite und Habenseite sind nicht ausgeglichen");
	}
	
	public void buche() {
		soll.forEach((k, i)->k.buche(i));
		haben.forEach((k, i)->k.buche(i));
	}
	
}
