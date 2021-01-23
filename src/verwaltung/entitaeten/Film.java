package verwaltung.entitaeten;

import javafx.beans.property.ReadOnlyFloatProperty;
import javafx.beans.property.ReadOnlyFloatWrapper;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import verwaltung.verwaltungen.Personenverwaltung;
import verwaltung.verwaltungen.Rezensionenverwaltung;

public class Film {
	
	private int id, erstellerId;
	private Genre genre;
	private ReadOnlyStringWrapper titel, dauer_string;
	private ReadOnlyIntegerWrapper dauer, erscheinungsjahr;
	private ReadOnlyFloatWrapper bewertung;
	
	private Personenverwaltung pvw;
	private Rezensionenverwaltung rvw;
	
	public Film(int id, int erstellerId, String titel, Genre genre, int dauer, int erscheinungsjahr, float bewertung) {
		this.id = id;
		this.erstellerId = erstellerId;
		this.titel = new ReadOnlyStringWrapper(titel);
		this.genre = genre;
		this.dauer = new ReadOnlyIntegerWrapper(dauer);
		this.dauer_string = new ReadOnlyStringWrapper(dauer+" Minuten");
		this.erscheinungsjahr = new ReadOnlyIntegerWrapper(erscheinungsjahr);
		this.bewertung = new ReadOnlyFloatWrapper(bewertung);
	}
	
	
	public int getId() {
		return id;
	}
	public int getErstellerId() {
		return erstellerId;
	}
	public String getTitel() {
		return titel.get();
	}
	public int getDauer() {
		return dauer.get();
	}
	public int getErscheinungsjahr() {
		return erscheinungsjahr.get();
	}
	public float getBewertung() {
		return bewertung.get();
	}
	
	
	
	
	public ReadOnlyStringWrapper getTitelProperty() {
		return titel;
	}
	public ReadOnlyIntegerProperty getErscheinungsjahrProperty() {
		return erscheinungsjahr.getReadOnlyProperty();
	}
	public ReadOnlyFloatProperty getBewertungProperty() {
		return bewertung.getReadOnlyProperty();
	}
	public ReadOnlyStringProperty getDauerProperty() {
		return dauer_string.getReadOnlyProperty();
	}
	public Genre getGenre() {
		return genre;
	}
	
	public Rezensionenverwaltung getRvw() {
		if(rvw == null)
			rvw = new Rezensionenverwaltung(this);
		return rvw;
	}
	public Personenverwaltung getPvw() {
		if(pvw == null)
			pvw = new Personenverwaltung(this);
		return pvw;
	}
	
	
	public void setBewertung(float bewertung) {
		this.bewertung.set(bewertung);
	}
}
