package verwaltung.entitaeten;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import verwaltung.verwaltungen.unterverwaltungen.Personenverwaltung;
import verwaltung.verwaltungen.unterverwaltungen.Rezensionenverwaltung;


public class Film extends Entitaet {
	
	private int erstellerId;
	private List<Genre> genres;
	private ReadOnlyStringWrapper titel, dauer_string, genre_string, bwt_string;
	private ReadOnlyIntegerWrapper erscheinungsjahr;
	private int dauer;
	private float bewertung;
	
	private Personenverwaltung pvw;
	private Rezensionenverwaltung rvw;
//	public Film(int id, int erstellerId, String titel, int dauer, int erscheinungsjahr, float bewertung) {
//		this(id, erstellerId, titel, null, dauer, erscheinungsjahr, bewertung);
//	}
	
	public Film(int id, int erstellerId, String titel, int dauer, int erscheinungsjahr, float bewertung) {
		super(id);
		this.erstellerId = erstellerId;
		this.titel = new ReadOnlyStringWrapper(titel);
		this.dauer = dauer;		
		this.erscheinungsjahr = new ReadOnlyIntegerWrapper(erscheinungsjahr);		
		this.bewertung = bewertung;
		
		bwt_string = new ReadOnlyStringWrapper();
		setBewertung(bewertung);
		
		dauer_string = new ReadOnlyStringWrapper();
		setDauer(dauer);
		
		genre_string = new ReadOnlyStringWrapper("");
		genres = new ArrayList<>();
		rvw = new Rezensionenverwaltung(this);
		pvw = new Personenverwaltung(this);
	}
	
	
	public int getErstellerId() {
		return erstellerId;
	}
	public String getTitel() {
		return titel.get();
	}
	public int getDauer() {
		return dauer;
	}
	public int getErscheinungsjahr() {
		return erscheinungsjahr.get();
	}

	
	
	
	
	public ReadOnlyStringWrapper getTitelProperty() {
		return titel;
	}
	public ReadOnlyIntegerProperty getErscheinungsjahrProperty() {
		return erscheinungsjahr.getReadOnlyProperty();
	}
	public ReadOnlyStringProperty getDauerStringProperty() {
		return dauer_string.getReadOnlyProperty();
	}
	public ReadOnlyStringProperty getGenreStringProperty() {
		return genre_string.getReadOnlyProperty();
	}
	public ReadOnlyStringProperty getBwtStringProperty() {
		return bwt_string.getReadOnlyProperty();
	}
	
	public List<Genre> getGenres() {
		return new ArrayList<>(genres);
	}
	
	public Rezensionenverwaltung getRvw() {
		return rvw;
	}
	public Personenverwaltung getPvw() {
		return pvw;
	}
	
	
	public void setBewertung(float bewertung) {
		this.bewertung = bewertung;
		bwt_string.set(bewertung+" Sterne");
	}
	public void setTitel(String titel) {
		this.titel.set(titel);
	}
	public void setDauer(int dauer) {
		this.dauer = dauer;
		dauer_string.set(dauer+" Minuten "+getGenaueZeit(dauer));
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
	
	
	public static String getGenaueZeit(int min) {
		int d = min/60/24;
		int h =  min/60%24;
		min = min%60;
		String zeit = "";
		if(d>0 || h>0)	zeit = "("+ (d>0? " "+d+"d ":" ")+h+"h "+min+"min )";
		return zeit;
	}
}
