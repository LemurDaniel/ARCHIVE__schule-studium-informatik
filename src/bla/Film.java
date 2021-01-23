package bla;

import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.beans.property.ReadOnlyFloatProperty;
import javafx.beans.property.ReadOnlyFloatWrapper;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.ReadOnlyStringWrapper;

public class Film {
	
	private int id;
	private ReadOnlyStringWrapper titel, genre;
	private ReadOnlyIntegerWrapper dauer, erscheinungsjahr;
	private ReadOnlyFloatWrapper bewertung;
	
	private Personenverwaltung pvw;
	private Rezensionenverwaltung rvw;
	
	public Film(int id, String titel, String genre, int dauer, int erscheinungsjahr, float bewertung) {
		this.id = id;
		this.titel = new ReadOnlyStringWrapper(titel);
		this.genre = new ReadOnlyStringWrapper(genre);
		this.dauer = new ReadOnlyIntegerWrapper(dauer);
		this.erscheinungsjahr = new ReadOnlyIntegerWrapper(erscheinungsjahr);
		this.bewertung = new ReadOnlyFloatWrapper(bewertung);
	}
	
	public Film(ResultSet rs) throws SQLException {
		this(rs.getInt("id"), rs.getString("titel"), rs.getString("genre"), rs.getInt("dauer"), rs.getInt("erscheinungsjahr"), rs.getFloat("bewertung"));
	}
	
	public int getId() {
		return id;
	}
	public ReadOnlyStringWrapper getTitel() {
		return titel;
	}
	public ReadOnlyStringProperty getGenre() {
		return genre.getReadOnlyProperty();
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
