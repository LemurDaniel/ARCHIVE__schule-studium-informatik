package aufgabe1;

import java.util.Iterator;

public class Artikel{
	
	private static int nr = 0;
	
	private int artk_nr, menge;
	private String bezeichnung;
	private double preis;
	
	public Artikel(String bezeichnung, String sPreis, String sMenge) throws Exception{
		if(bezeichnung.length() < 3) throw new Exception("Die Bezeichnung muss mindestens 3-stellig");
		this.bezeichnung = bezeichnung;
		
		try {
			preis = Double.parseDouble(sPreis);
		}catch(NumberFormatException nfe) {
			throw new NumberFormatException(sPreis +" ist nicht lesbar");
		}
		if(preis < 0) throw new Exception("Der preis muss größer als Null sein");
		
		try {
			menge = Integer.parseInt(sMenge);
		}catch(NumberFormatException nfe) {
			throw new NumberFormatException(sMenge +" ist nicht lesbar");
		}
		if(menge < 0) throw new Exception("Die Menge muss größer als Null sein");
		
		this.artk_nr = ++nr;
	}
	
	public Artikel(String bezeichnung, double preis,int menge) throws Exception{
		if(bezeichnung.length() < 3) throw new Exception("Die Bezeichnung muss mindestens 3-stellig");
		if(preis < 0) throw new Exception("Der preis muss größer als Null sein");
		if(menge < 0) throw new Exception("Die Menge muss größer als Null sein");
		
		this.bezeichnung = bezeichnung;
		this.preis = preis;
		this.menge = menge;
		this.artk_nr = ++nr;
	}

	public void setBezeichnung(String bezeichnung) throws Exception{
		if(bezeichnung.length() < 3) throw new Exception("Die Bezeichnung muss mindestens 3-stellig");
		this.bezeichnung = bezeichnung;
	}
	public void setPreis(String sPreis) throws Exception{
		double preis;
		try {
			preis = Double.parseDouble(sPreis);
		}catch(NumberFormatException nfe) {
			throw new NumberFormatException(sPreis +" ist nicht lesbar");
		}
		if(preis < 0) throw new Exception("Der preis muss größer als Null sein");
		this.preis = preis;
	}
	public void setMenge(String sMenge) throws Exception{
		int menge;
		try {
			menge = Integer.parseInt(sMenge);
		}catch(NumberFormatException nfe) {
			throw new NumberFormatException(sMenge +" ist nicht lesbar");
		}
		if(menge < 0) throw new Exception("Die Menge muss größer als Null sein");
		this.menge = menge;
	}
	
	public String getBezeichnung() {
		return bezeichnung;
	}
	public int getMenge() {
		return menge;
	}
	public double getPreis() {
		return preis;
	}
	
	public String toString() {
		String s = String.format("Artikel:  %-4d   %-20s   %7.2f€   %7d Stück", artk_nr, bezeichnung, preis, menge);
		//System.out.println(s);
		return s;
	}

	
	public static class TestArtikelListe implements Iterable<Artikel>, Iterator<Artikel>{
		
		private Artikel[] artks;
		private int count = 0;
		
		public TestArtikelListe() {
			String[] s = {"Fisch", "Apfel", "Banane", "Pudding", "Yoghurt", "Kirschen", "Erdnüsse", "Haslenüsse", "Wasser", "Cola", "Fanta", "Kirschsaft", "Sprudel", "Hackfleisch", "Schnitzel", "Käse", "Wurst"};
			artks = new Artikel[s.length];
			for(int i=0; i<s.length; i++){
				try {
					double p = Math.random()*21+5;
					int m = ((int)(Math.random()*151))+50;
					artks[i] = new Artikel(s[i], p, m);
				}catch(Exception e) {}
			}
		}

		public Artikel[] getArtikel() {
			return artks;
		}

		@Override
		public Iterator<Artikel> iterator() {
			count = 0;
			return (Iterator<Artikel>) this;
		}


		@Override
		public boolean hasNext() {
			return artks.length > count;
		}


		@Override
		public Artikel next() {
			return artks[count++];
		};

		}

}
