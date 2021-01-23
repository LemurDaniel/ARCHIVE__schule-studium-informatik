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
	private List<Genre> genres;
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
		genres = new ArrayList<>();
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
	public List<Genre> getGenres() {
		return genres;
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
		genres.add(genre);
		genres.sort((o1,o2)->o1.compare(o1, o2));
		
		StringBuilder sb = new StringBuilder(genres.get(0).getGenre());
    	for(int i=1; i<genres.size(); i++)
    		sb.append( ", "+genres.get(i).getGenre() );
    	genre_string.set(sb.toString());
	}

	public void clearGenre() {
		if(genres==null)
			return;
		genres.clear();
		genre_string.set("");
	}
}
