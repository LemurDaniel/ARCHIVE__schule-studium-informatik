package Buchungssystem;

import java.util.Map;
import java.util.TreeMap;

public class Kontoklasse {
	
	public static final int AKTIV = 0;
	public static final int PASSIV = 1;
	public static final int ERTRAG = 2;
	public static final int AUFWAND = 3;
	public static final int ERGEBNIS = 4;
	
	private int typ;
	private int nummer;
	private KontenSystem ko;
	Map<String, Konto> konten = new TreeMap<>();
	
	Kontoklasse(int nummer, int typ, KontenSystem ko){
		this.nummer = nummer;
		this.typ = typ;
		this.ko = ko;
	}

	void addKonto(Konto k) {
		konten.put(k.getNummer(), k);
	}
	
	Konto find(String nummer) {
		if(konten.containsKey(nummer))
			return konten.get(nummer);
		else {
			if(konten.containsKey(nummer.substring(0, nummer.length()-1)+"0")) {
				return konten.get(nummer.substring(0, nummer.length()-1)+"0").getKonto(nummer);
			}else	return null;
		}
	}
	
	
	public String toString() {
		return typ + "  " + nummer;
	}
}
