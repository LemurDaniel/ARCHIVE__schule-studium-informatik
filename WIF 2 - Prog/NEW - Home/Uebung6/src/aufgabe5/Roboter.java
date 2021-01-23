package aufgabe5;


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


@SuppressWarnings({ "unused", "serial" })
public class Roboter implements Serializable{
	
	private ArrayList<Winkel> list = new ArrayList<>();
	
	private Roboter(ArrayList<Winkel> list) {
		this.list = list;
	}
	
	public Roboter(){
		list.add(new Winkel(135,      "Körper:Rotieren"));
		list.add(new Winkel(45,       "Schulter:Bewegen"));
		list.add(new Winkel(45,       "Arm:Bewegen"));
		list.add(new Winkel(45,       "Hand:Rotieren"));
		list.add(new Winkel(180, -90, "Hand:Bewegen"));
		list.add(new Winkel(0, -90,   "Finger:Schließen"));
		
		
//		list.add(new Winkel(90,       "Kopf:Drehen"));
//		list.add(new Winkel(45, 0, 0, "Kopf:Neigen"));
//		list.add(new Winkel(45,       "Bein:Bewegen"));
	}

	
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
			throw new Exception( String.format("Außerhalb des Winkelbereichs:  %d - %d --> Akuteller Winkel: %d", w.min, w.max, w.winkel));	
		
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
	
	
	public void save(JWindow jw) throws Exception{
		JFileChooser fc = new JFileChooser();
		if( fc.showSaveDialog(jw) != JFileChooser.APPROVE_OPTION )
			return;
		
		try( FileOutputStream fOut = new FileOutputStream(fc.getSelectedFile());
				ObjectOutputStream out = new ObjectOutputStream(fOut); ){
			out.writeInt(list.size()); 
			for(Winkel w:list) {
				out.writeObject(w);
			}
		}catch(Exception e) {
			e.printStackTrace();
			throw new Exception("Fehler beim Speichern: " + e.getMessage());
		}
	}
	
	public static Roboter load() throws Exception{
		JFileChooser fc = new JFileChooser();
		if( fc.showOpenDialog(null) != JFileChooser.APPROVE_OPTION )
			throw new Exception();
		
		ArrayList<Winkel> al = new ArrayList<>();
		
		try( FileInputStream fIn = new FileInputStream(fc.getSelectedFile());
				ObjectInputStream in = new ObjectInputStream(fIn)){
			for(int i=in.readInt(); i>0; i--) {
				al.add((Winkel)in.readObject());
			}
		}catch(Exception e) {
			e.printStackTrace();
			throw new Exception("Fehler beim Lesen");
		}
		return new Roboter(al);
	}
	
	
	
	
	
private class Winkel implements Serializable{

		int max, min, winkel;
		String bez;
		
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

	}
}
