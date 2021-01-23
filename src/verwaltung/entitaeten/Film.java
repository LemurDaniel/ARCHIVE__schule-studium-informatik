package verwaltung.entitaeten;

import java.util.ArrayList;
import java.util.List;

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
	private List<Genre> genre;
	private ReadOnlyStringWrapper titel, dauer_string, genre_string;
	private ReadOnlyIntegerWrapper dauer, erscheinungsjahr;
	private ReadOnlyFloatWrapper bewertung;
	
	private Personenverwaltung pvw;
	private Rezensionenverwaltung rvw;
	
//	public Film(int id, int erstellerId, String titel, int dauer, int erscheinungsjahr, float bewertung) {
//		this(id, erstellerId, titel, null, dauer, erscheinungsjahr, bewertung);
//	}
	
	public Film(int id, int erstellerId, String titel, int dauer, int erscheinungsjahr, float bewertung) {
		this.id = id;
		this.erstellerId = erstellerId;
		this.titel = new ReadOnlyStringWrapper(titel);
		//this.genre = genre;
		this.dauer = new ReadOnlyIntegerWrapper(dauer);
		this.dauer_string = new ReadOnlyStringWrapper(dauer+" Minuten");
		this.erscheinungsjahr = new ReadOnlyIntegerWrapper(erscheinungsjahr);
		this.bewertung = new ReadOnlyFloatWrapper(bewertung);
		genre_string = new ReadOnlyStringWrapper("");
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
	public ReadOnlyStringProperty getGenreStringProperty() {
	return genre_string.getReadOnlyProperty();
}
	public List<Genre> getGenre() {
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
	public void setTitel(String titel) {
		this.titel.set(titel);
	}
	public void setDauer(int dauer) {
		this.dauer.set(dauer);
		this.dauer_string.set(dauer+" Minuten");
	}
	public void setErscheinungsjahr(int jahr) {
		this.erscheinungsjahr.set(jahr);
	}
	public void addGenre(Genre genre) {
		if(this.genre == null) this.genre = new ArrayList<>();
		this.genre.add(genre);

		if(genre_string.get().length()==0)
			genre_string.set(genre.getGenre());
		else
			genre_string.set(genre_string.get()+", "+genre.getGenre());
	}

	public void clearGenre() {
		if(genre==null)
			return;
		genre.clear();
		genre_string.set("");
	}
}
