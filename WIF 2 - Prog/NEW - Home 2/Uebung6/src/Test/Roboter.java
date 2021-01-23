package Test;


import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JFileChooser;

@SuppressWarnings("serial")
public class Roboter implements Serializable{
	
	private String name;
	private ArrayList<Winkel> list = new ArrayList<>();
	private boolean fehlerhaft = false;
	private static int nameMax = 20, listMax = 36, bezMax = 32, fatalCounterMax = 300;
	private static char stringSeperator = '.';
	private static char varSeperator = '|';
	private static char actionSeperator = ':';
	
	// Konstruktor
	private Roboter(InputStreamReader in) throws Exception{
		StringBuffer sb = new StringBuffer();
		int c, counter = 0, deadPartsCount = 0;
		stringSeperator = (char)in.read();
		varSeperator = (char)in.read();
		actionSeperator = (char) in.read();
		
		if( stringSeperator == varSeperator || stringSeperator == actionSeperator || varSeperator == actionSeperator)
			throw new FatalerLadeFehler("Die Datei kann nicht gelesen werden");
		
		// RoboterName
		while(true) {
			c = in.read();		
			if( c == -1 || c == stringSeperator )
				break;
			if( sb.length() > nameMax ) {
				fehlerhaft = true;
				break;
			}
			sb.append( (char)c );
			
			// Stop bei zu großen Datenmengen
			if( ++counter > fatalCounterMax )
				throw new FatalerLadeFehler("Die Datei kann nicht gelesen werden");
		}
		name = checkName(sb.toString());
		sb = new StringBuffer();
		counter = 0;
		
		// Stringbeschreibung für Winkel
		while(true) {
			c = in.read();			
			if( c == -1 )
				break;
			
			// Eine Winkelbeschreibung endet
			if( c == stringSeperator ) {
				if( list.size() >= listMax ) {
					fehlerhaft = true;
					break;
				}
				// Winkel adden
				try {
					list.add( new Winkel(sb.toString()) );
				}catch( DateiFehler df) {
					fehlerhaft = true;
					deadPartsCount++;
				}
				// Leere StringBuffer für neue Winkelbeschreibung
				sb = new StringBuffer();
				counter= 0;
			}else
				sb.append( (char) c );
			
			if( ++counter > fatalCounterMax )
				throw new FatalerLadeFehler("Die Datei kann nicht gelesen werden");
		}
		
		if(fehlerhaft)
			throw new RoboterLadeFehler("Fehlerhafte Datei: "+deadPartsCount+" Teil"+(deadPartsCount==1?"":"e")+" beschädigt", this);
	}
	
	public Roboter(String name) throws Exception{
		this.name = checkName(name);
		list.add(new Winkel(135,      "Körper:Rotieren",	45, 15));
		list.add(new Winkel(45,       "Schulter:Bewegen",	45, 5));
		list.add(new Winkel(45,       "Arm:Bewegen",		45, 5));
		list.add(new Winkel(45,       "Hand:Rotieren",		45, 5));
		list.add(new Winkel(180, -90, "Hand:Bewegen", 		45, 15));
		list.add(new Winkel(0, -90,   "Finger:Schließen", 	45, 5));
		
		
//		list.add(new Winkel(90,       "Kopf:Drehen", 		45, 15));
//		list.add(new Winkel(45, 0, 0, "Kopf:Neigen",		45, 5));
//		list.add(new Winkel(45,       "Bein:Bewegen", 		45, 5));
		
		while(list.size() > listMax && !list.isEmpty()) {
			list.remove(list.size()-1);
		}
	}

	
	// Methoden
	public void bewege(int element, String sbetrag, boolean modeR) throws Exception{	
		try {
			bewege(element, Integer.parseInt(sbetrag), modeR);		
		}catch(NumberFormatException nfe) {
			throw new NumberFormatException( "'"+sbetrag+"' ist keine gültige Integer-Zahl");
		}	
	}
	
	public void bewege(int element, int betrag, boolean modeR) throws Exception{	
		Winkel w;
		try {
			w = list.get(element);
		}catch(IndexOutOfBoundsException ioobe) {
			throw new IndexOutOfBoundsException("Es wurde kein gültiges Körpeteil ausgewählt");
		}
		
		if(modeR) 
			betrag += w.winkel;		
		if(betrag > w.max || betrag < w.min)
			throw new Exception( String.format("Außerhalb des Winkelbereichs"));	
		
		w.winkel = betrag;;
	}
	
	public void fillDLM(DefaultListModel<String> dlm) {
		dlm.clear();
		list.forEach( a -> dlm.addElement(a.bez) );
	}
	
	public void loesche(int id) throws Exception {
		if(list.isEmpty())	throw new Exception("Keine Körperteile vorhanden");
		list.remove(id);
	}
	
	public void hinzufuegen(String bez, int max, int min, int winkel, int majorT, int minorT) throws Exception{
		if (list.size() >= listMax) throw new Exception("Zu viele Körperteile");
		list.add( new Winkel(max, min, winkel, bez, majorT, minorT));
	}
	
	public String checkBezeichnung(String bez) throws Exception{
		if(bez.length() > bezMax)
			throw new Exception("Die Bezeichnung ist zu lang");
		
		boolean doppelpunkt = false;
		for(int i=0; i<bez.length(); i++) {
			//Erlaubte Zeichen
			if( bez.charAt(i) == stringSeperator || bez.charAt(i) == varSeperator )
				throw new Exception("Das Zeichen '"+bez.charAt(i)+"' ist nicht erlaubt");
			
			if( bez.charAt(i) == actionSeperator ) {
				if ( doppelpunkt )
					throw new Exception("Bitte nur ein " + actionSeperator + " verwenden");
				doppelpunkt = true;
			}	
		}
		if(!doppelpunkt)
			throw new Exception("Bitte ein "+ actionSeperator + " verwenden");
		
		// Bereits vorhanden?
		for(int i=0; i<list.size(); i++) {
			if( bez.equals(list.get(i).bez) )
				throw new Exception("Bezeichnung bereits verwendet");
		}
		return bez;
	}
	
	public String checkName(String name) throws Exception{
		if (name.length() > nameMax)	throw new Exception("Der Name ist zu lang");
		if (name.length() <= 0) throw new Exception("Der Robotername muss mindestens ein Zeichen beinhalten"); 		
		for(int i=0; i<name.length(); i++) {
			char c = name.charAt(i);
			if( c==varSeperator || c==stringSeperator) {
				throw new Exception("Das Zeichen '"+c+"' ist nicht zugelassen");
			}
		}
		return name;
	}
	
	
	// get Methoden
	public String getBez(int id) {
		return list.get(id).bez;
	}
	public int getMax(int id) {
		return list.get(id).max;
	}
	public int getMin(int id) {
		return list.get(id).min;
	}
	public int getWinkel(int id) {
		return list.get(id).winkel;
	}
	public int getMajorT(int id) {
		return list.get(id).majorT;
	}
	public int getMinorT(int id) {
		return list.get(id).minorT;
	}
	public void setText(int id, String bez) throws Exception{
		checkBezeichnung(bez);
		list.get(id).bez = bez;
	}
	public String getName() {
		return name;
	}
	public int getNameMax() {
		return nameMax;
	}
	public int getListMax() {
		return listMax;
	}
	public void setName(String name) throws Exception{
		this.name = checkName(name);
	}
	public int length() {
		return list.size();
	}
	public static char getStringSeperator() {
		return stringSeperator;
	}
	public static char getVarSeperator() {
		return varSeperator;
	}
	public static char getActionSeperator() {
		return actionSeperator;
	}
	
	
	// save - load
	public void save(JDialog jw) throws Exception{
		JFileChooser fc = new JFileChooser();
		if( fc.showSaveDialog(jw) != JFileChooser.APPROVE_OPTION )
			return;
		
		try(PrintWriter out = new PrintWriter(fc.getSelectedFile())){
			String s = ""+stringSeperator + varSeperator + actionSeperator + name + stringSeperator;
			for(Winkel w:list) {
				s += w.toString() + stringSeperator;
			}
			out.write(s);
		}catch(Exception e) {
			//e.printStackTrace();
			throw new Exception("Fehler beim Speichern: " + e.getMessage());
		}
	}
	
	public static Roboter load() throws Exception{
		JFileChooser fc = new JFileChooser();
		if( fc.showOpenDialog(null) != JFileChooser.APPROVE_OPTION )
			throw new Exception();

		try( FileInputStream fIn = new FileInputStream(fc.getSelectedFile());
				InputStreamReader in = new InputStreamReader(fIn)){
			return new Roboter(in);
		}catch(RoboterLadeFehler rlf) {
			throw rlf;
		}catch(Exception e) {
			//e.printStackTrace();
			throw new FatalerLadeFehler("Fataler Lesefehler: " + e.getMessage());
		}
	}
	
	
	
	
	
private class Winkel implements Serializable{

		int max, min, winkel, minorT, majorT;
		String bez;
		
		public Winkel(String ss) throws Exception {
			try {
				String s[] =  ss.split("\\"+varSeperator);
				bez = checkBezeichnung(s[0]);	
				max = Integer.parseInt(s[1]);
				min = Integer.parseInt(s[2]);
				winkel = Integer.parseInt(s[3]);
				majorT = Integer.parseInt(s[4]);
				minorT = Integer.parseInt(s[5]);			
				checkWinkel();
			}catch(Exception e) {
				throw new DateiFehler();
			}
		}
		
		public Winkel(int grenze, String bez, int majorT, int minorT) throws Exception{
			this( Math.abs(grenze), -Math.abs(grenze), bez, majorT, minorT);
		}
		
		public Winkel(int max, int min, String bez, int majorT, int minorT) throws Exception{
			this(max, min, (max+min)/2, bez, majorT, minorT);
		}
		
		public Winkel(int max, int min, int winkel, String bez, int majorT, int minorT) throws Exception{		
			this.max = max;
			this.min = min;
			this.winkel = winkel;
			this.bez = 		checkBezeichnung(bez);
			this.majorT = majorT;
			this.minorT = minorT;
			checkWinkel();
		}
		
		private void checkWinkel() throws Exception {
			if(max <= min)
				throw new Exception("Maximum kleiner als Minimum");
			if(winkel > max || winkel < min)
				throw new Exception("Winkel außerhalb des Winkelbereichs");
			if(majorT < 1 || minorT < 1)
				throw new Exception("Tick Spaces müssen größer als 0 sein");
		}
		
		public String toString() {		
			return bez + varSeperator + max  + varSeperator + min + varSeperator + winkel + varSeperator + majorT + varSeperator + minorT ;
		}

	}


	private static class DateiFehler extends Exception{}
	public static class RoboterLadeFehler extends Exception{
		private Roboter r;
		RoboterLadeFehler(String message, Roboter r){
			super(message);
			this.r = r;
		}
		public Roboter getRoboter() {
			return r;
		}
	}
	public static class FatalerLadeFehler extends Exception{
		FatalerLadeFehler(String message){
			super(message);
		}
	}
}
