package Buchungssystem;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import Buchungssystem.Fehler.BuchungsFehler;


public class KontenSystem {
	

	int kontoNummerLaenge;
	//private List<Buchungssatz> list = new LinkedList<>();
	private Kontoklasse[] kontoKlassen;

	public KontenSystem() {
		try {
			setRahmen();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void test() {
		for (int i = 0; i < kontoKlassen.length; i++) {
			kontoKlassen[i].konten.forEach((k,v)->System.out.println(v.toString()));
		}
	}
	
	
	
	public Buchung getBuchung(String nummer, int betrag, int typ) throws Exception{
		int i = Integer.parseInt(nummer.charAt(0)+"");
		Buchung b = new Buchung();
		b.konto = kontoKlassen[i].find(nummer);
		b.betrag = betrag;
		b.typ = typ;
		if(b.konto==null) throw new BuchungsFehler("Das Konto mit der Nummer "+nummer+"konnte nicht gefuden werden");
		
		return b;
	}
	
	public Konto getKonto(String nummer) {
		int i = Integer.parseInt(nummer.charAt(0)+"");
		return kontoKlassen[i].find(nummer);
	}
	
	
	
	
	/** BLLLLLLLAAAAA */
	public void setRahmen() throws Exception{
		//File file = new FileChooser().showOpenDialog(null);
		File file = new File(getClass().getResource("Konten").toURI());
		///if(file==null) return;
		
		try (FileInputStream in = new FileInputStream(file);
				InputStreamReader read = new InputStreamReader(in)){
			readFile(read);
		}catch(Exception e) {
			throw e;
		}
	}
	
	private void readFile(InputStreamReader read)throws Exception{
		int typ = 0;
		List<Kontoklasse> list = new ArrayList<>();
		while(true){
			int i = read.read();
			char c = (char)i;
			if(c=='.') 
				typ++;
			else if(c=='#' || i==-1)
				break;
			else
				list.add(new Kontoklasse(Integer.parseInt(c+""), typ, this));
		}
		kontoKlassen = new Kontoklasse[list.size()];
		list.toArray(kontoKlassen);
		for (int i = 0; i < kontoKlassen.length; i++) {
			System.out.println(kontoKlassen[i]);
		}
		
		char c1 = (char)read.read();;
		kontoNummerLaenge = Integer.parseInt(c1+"");
		
		//Konten
		Konto last = null;
		StringBuilder nr = new StringBuilder();
		StringBuilder name = new StringBuilder();
		boolean nrB = true;
		boolean unter = false;
		
		while(true) {
			int i = read.read();
			char c = (char)i;
			
			if(i ==-1) break;
			else if(c=='+')
				unter = true;
			else if(c=='.') {
				Konto k = new Konto(nr.toString(), name.toString());

				nr = new StringBuilder();
				name = new StringBuilder();
				nrB = true;

				if(unter) {
					last.addKonto(k);
					unter = false;
				}else{
					kontoKlassen[k.getKlasse()].addKonto(k);
					last = k;
				}
						
			}
			else if(Character.isDigit(c) && nrB) 
				nr.append(c);
			else if(nr.length()!=0){
				nrB = false;
				name.append(c);
			}
		}
	}
}
