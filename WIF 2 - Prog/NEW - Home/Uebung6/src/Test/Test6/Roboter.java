package Test.Test6;


import java.awt.Component;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JWindow;

import aufgabe6.KC;

@SuppressWarnings({ "unused", "serial" })
public class Roboter implements Serializable{
	
	private ArrayList<Winkel> list = new ArrayList<>();
	
	private Roboter(ObjectInputStream in) throws Exception{
		String ss[] = ((String) in.readObject()).split("\\.");
		
		for(String s: ss) {
			list.add( new Winkel(s) );
		}
	}
	
	public Roboter(){
		list.add(new Winkel(135,      "K�rper:Rotieren"));
		list.add(new Winkel(45,       "Schulter:Bewegen"));
		list.add(new Winkel(45,       "Arm:Bewegen"));
		list.add(new Winkel(45,       "Hand:Rotieren"));
		list.add(new Winkel(180, -90, "Hand:Bewegen"));
		list.add(new Winkel(0, -90,   "Finger:Schlie�en"));
		
		
		list.add(new Winkel(90,       "Kopf:Drehen"));
		list.add(new Winkel(45, 0, 0, "Kopf:Neigen"));
		list.add(new Winkel(45,       "Bein:Bewegen"));
	}

	
	public void bewege(int element, String sbetrag, boolean modeR) throws Exception{	
		try {
			bewege(element, Integer.parseInt(sbetrag), modeR);		
		}catch(NumberFormatException nfe) {
			throw new NumberFormatException( "'"+sbetrag+"' ist keine g�ltige Integer-Zahl");
		}	
	}
	
	public void bewege(int element, int betrag, boolean modeR) throws Exception{	
		Winkel w;
		try {
			w = list.get(element);
		}catch(IndexOutOfBoundsException ioobe) {
			throw new IndexOutOfBoundsException("Es wurde kein g�ltiges K�rpeteil ausgew�hlt");
		}
		
		if(modeR) 
			betrag += w.winkel;		
		if(betrag > w.max || betrag < w.min)
			throw new Exception( String.format("Au�erhalb des Winkelbereichs:  %d - %d --> Akuteller Winkel: %d", w.min, w.max, w.winkel));	
		
		w.winkel = betrag;;
	}
	
	public void fillDLM(DefaultListModel<String> dlm) {
		dlm.clear();
		list.forEach( a -> dlm.addElement(a.bez) );
	}
	public void hinzufuegen(String bez, int max, int min, int winkel){
		list.add( new Winkel(max, min, winkel, bez));
	}
	public void loesche(int id) throws Exception {
		list.remove(id);
	}
	
	
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
	public int length() {
		return list.size();
	}
	public boolean getH() {
		return false;
	}
	
	public void save(JWindow jw) throws Exception{
		JFileChooser fc = new JFileChooser();
		if( fc.showSaveDialog(jw) != JFileChooser.APPROVE_OPTION )
			return;
		
		try( FileOutputStream fOut = new FileOutputStream(fc.getSelectedFile());
				ObjectOutputStream out = new ObjectOutputStream(fOut); ){
			String s = "";
			for(Winkel w:list) {
				s += w.toString() + ".";
			}
			out.writeObject(s);
		}catch(Exception e) {
			e.printStackTrace();
			throw new Exception("Fehler beim Speichern: " + e.getMessage());
		}
	}
	
	public static Roboter load() throws Exception{
		JFileChooser fc = new JFileChooser();
		if( fc.showOpenDialog(null) != JFileChooser.APPROVE_OPTION )
			throw new Exception();
		
		try( FileInputStream fIn = new FileInputStream(fc.getSelectedFile());
				ObjectInputStream in = new ObjectInputStream(fIn)){
			return new Roboter(in);
		}catch(Exception e) {
			e.printStackTrace();
			throw new Exception("Fehler beim Lesen");
		}
	}
	
	
	
	
	
private class Winkel implements Serializable{

		int max, min, winkel;
		String bez;
		
		public Winkel(String ss) throws Exception {
			String s[] =  ss.split("\\|");

			bez = s[0];
			max = Integer.parseInt(s[1]);
			
			if ( s.length == 4) {
				min = Integer.parseInt(s[2]);
				winkel = Integer.parseInt(s[3]);
			}else {
				min = -max;
				winkel = Integer.parseInt(s[2]);
			}
		}
		
		public Winkel(int grenze, String bez ){
			this( Math.abs(grenze), -Math.abs(grenze), bez);
		}
		
		public Winkel(int max, int min, String bez){
			this(max, min, (max+min)/2, bez);
		}
		
		public Winkel(int max, int min, int winkel, String bez){
			this.max = max;
			this.min = min;
			this.winkel = winkel;
			this.bez = bez;
		}
		
		public String toString() {		
			if ( max == -min) 
				return bez + "|" + max  + "|" + winkel;
			else
				return bez + "|" + max + "|" + min + "|" + winkel;
		}

	}
}
