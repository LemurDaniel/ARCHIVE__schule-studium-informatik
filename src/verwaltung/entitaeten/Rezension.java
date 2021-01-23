package verwaltung.entitaeten;

import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.ReadOnlyStringWrapper;

public class Rezension extends Entitaet{
	
	private int verfasserId;
	private ReadOnlyStringWrapper titel, inhalt, verfasser;
	private ReadOnlyIntegerWrapper bewertung;
	
	public Rezension(int id, String titel, String inhalt, String verfasser, int verfasserId, int bewertung) {
		super(id);
		this.verfasserId = verfasserId;
		this.titel = new ReadOnlyStringWrapper(titel);
		this.inhalt = new ReadOnlyStringWrapper(inhalt);
		this.verfasser = new ReadOnlyStringWrapper(verfasser);
		this.bewertung = new ReadOnlyIntegerWrapper();
		setBewertung(bewertung);
	}
	
	public String getTitel() {
		return titel.get();
	}
	public String getInhalt() {
		return inhalt.get();
	}
	public String getVerfasser() {
		return verfasser.get();
	}
	public int getBewertung() {
		return bewertung.get();
	}
	
	
	public ReadOnlyStringProperty getTitelProperty() {
		return titel.getReadOnlyProperty();
	}
	public ReadOnlyStringProperty getInhaltProperty() {
		return inhalt.getReadOnlyProperty();
	}
	public ReadOnlyStringProperty getVerfasserProperty() {
		return verfasser.getReadOnlyProperty();
	}
	public ReadOnlyIntegerProperty getBewertungProperty() {
		return bewertung.getReadOnlyProperty();
	}
	public int getVerfasserId() {
		return verfasserId;
	}
	
	
	public void setTitel(String titel) {
		this.titel.set(titel);
	}
	public void setInhalt(String inhalt) {
		this.inhalt.set(inhalt);
	}
	public void setBewertung(int bewertung) {
		if(bewertung>10) bewertung = 10;
		else if(bewertung<0) bewertung = 0;
		this.bewertung.set(bewertung);;
	}
}
