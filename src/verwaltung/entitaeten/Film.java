package verwaltung.entitaeten;

import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.beans.property.ReadOnlyFloatProperty;
import javafx.beans.property.ReadOnlyFloatWrapper;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import verwaltung.verwaltungen.Personenverwaltung;
import verwaltung.verwaltungen.Rezensionenverwaltung;

public class Film {
	
	private int id;
	private Genre genre;
	private ReadOnlyStringWrapper titel, genre_prop, dauer_string;
	private ReadOnlyIntegerWrapper dauer, erscheinungsjahr;
	private ReadOnlyFloatWrapper bewertung;
	
	private Personenverwaltung pvw;
	private Rezensionenverwaltung rvw;
	
	public Film(int id, String titel, Genre genre, int dauer, int erscheinungsjahr, float bewertung) {
		this.id = id;
		this.titel = new ReadOnlyStringWrapper(titel);
		this.genre = genre;
		this.genre_prop = new ReadOnlyStringWrapper(genre.getGenre());
		this.dauer = new ReadOnlyIntegerWrapper(dauer);
		this.dauer_string = new ReadOnlyStringWrapper(dauer+" Minuten");
		this.erscheinungsjahr = new ReadOnlyIntegerWrapper(erscheinungsjahr);
		this.bewertung = new ReadOnlyFloatWrapper(bewertung);
	}
	
	
	public int getId() {
		return id;
	}
	public ReadOnlyStringWrapper getTitel() {
		return titel;
	}
	public ReadOnlyStringProperty getGenre() {
		return genre_prop.getReadOnlyProperty();
	}
	public ReadOnlyIntegerProperty getDauer() {
		return dauer.getReadOnlyProperty();
	}
	public ReadOnlyIntegerProperty getErscheinungsjahr() {
		return erscheinungsjahr.getReadOnlyProperty();
	}
	public ReadOnlyFloatProperty getBewertung() {
		return bewertung.getReadOnlyProperty();
	}
	public ReadOnlyStringProperty getDauerString() {
		return dauer_string.getReadOnlyProperty();
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
