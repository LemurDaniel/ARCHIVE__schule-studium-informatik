package Buchungssystem;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Konto implements Comparable<Konto>{
	
	private Map<String, Konto> unterkonto;
	
	private String name, nummer;
	private Integer  betrag;
	private List<Integer> buchungen;
	
	Konto(String nummer, String name){
		this.nummer = nummer;
		this.name = name;
		buchungen = new ArrayList<>();
		betrag = 0;
	}
	
	public void buche(int betrag) {
		buchungen.add(betrag);
		what();
	}
	
	
	public int getKlasse() {
		return Integer.parseInt(nummer.charAt(0)+"");
	}
	
	public void addKonto(Konto k) {
		if(unterkonto==null)unterkonto= new TreeMap<>();
		unterkonto.put(k.nummer, k);
	}
	
	public String toString() {
		String text= nummer + "   " + name;
		if(unterkonto!=null) {
			for (Konto k : unterkonto.values()) {
				text+="\n  -> "+k.toString();
			}
		}
		return text;
	}
	
	Konto getKonto(String nummer) {
		if(unterkonto == null) return null;
		return unterkonto.get(nummer);
	}
	
	public String getNummer() {
		return nummer;
	}
	public String getName() {
		return name;
	}


	@Override
	public int compareTo(Konto o) {
		int i1 = Integer.parseInt(this.nummer);
		int i2 = Integer.parseInt(o.getNummer());
		if(i1>i2)return 1;
		else if(i1==i2) return 0;
		else return -1;
	}
	
	
	public void what() {
		String text = "";
		String space = "  |  ";
		text = String.format("    %10s", "SOLL")+space+String.format("    %10s\n", "HABEN");
		if(betrag >= 0) 
			text += String.format("AB  %10d", betrag)+space+"\n";
		else
			text += String.format("    %10s", "")+space+String.format("AB  %10d\n", betrag);
		
		Iterator<Integer> sIT = buchungen.iterator();
		while(sIT.hasNext()) {
			int i = sIT.next();
			text += String.format("    %10s", i>0? i:"")+space;
			text += String.format("    %10s\n", i<0? -i:"");
		}
		System.out.println("---------------------");
		System.out.println("Konto:   "+nummer);
		System.out.println(text);
	}
}
