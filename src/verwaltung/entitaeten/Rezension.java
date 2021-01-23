package verwaltung.entitaeten;

import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import verwaltung.verwaltungen.unterverwaltungen.Rezensionenverwaltung;

public class Rezension implements Backup, EingabePruefung, Id{
	
	private Rezension backup;
	private int tempid;
	
	private int id, verfasserId;
	private String inhalt;
	private ReadOnlyStringWrapper titel, verfasser;
	private ReadOnlyIntegerWrapper bewertung;
	
	private Film film;
	
	public Rezension(int id, String titel, String inhalt, String verfasser, int verfasserId, int bewertung, Film film) {
		this.id = id;
		this.verfasserId = verfasserId;
		this.titel = new ReadOnlyStringWrapper(titel);
		this.inhalt = inhalt;
		this.verfasser = new ReadOnlyStringWrapper(verfasser);
		this.bewertung = new ReadOnlyIntegerWrapper();
		this.film = film;
		setBewertung(bewertung);
	}
	public int getId() {
		return id;
	}
	public String getTitel() {
		return titel.get();
	}
	public String getInhalt() {
		return inhalt;
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
	public ReadOnlyStringProperty getVerfasserProperty() {
		return verfasser.getReadOnlyProperty();
	}
	public ReadOnlyIntegerProperty getBewertungProperty() {
		return bewertung.getReadOnlyProperty();
	}
	public int getVerfasserId() {
		return verfasserId;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public void setTitel(String titel) {
		this.titel.set(titel);
	}
	public void setInhalt(String inhalt) {
		this.inhalt = inhalt;
	}
	public void setBewertung(int bewertung) {
		if(bewertung>10) bewertung = 10;
		else if(bewertung<0) bewertung = 0;
		this.bewertung.set(bewertung);;
	}

	
	@Override
	public void setTempId(int id) {
		tempid = id;
	}
	@Override
	public void commitId() {
		id = tempid;
	}
	
	@Override
	public void backup() {
		if(backup!=null)	return;
		
		backup = new Rezension(getId(), titel.get(), inhalt, verfasser.get(), verfasserId, bewertung.get(), film);
	}

	@Override
	public void backupReset() {
		if(backup==null)	return;
		
		id = backup.id;
		inhalt = backup.inhalt;
		titel.set(backup.getTitel());
		bewertung.set(backup.getBewertung());
		
		backup = null;
	}

	@Override
	public void deleteBackup() {
		backup = null;
	}
	@Override
	public boolean hasBackup() {
		return backup!=null;
	}

	
	@Override
	public void checkEingaben() throws Exception {	
		StringBuilder sb = new StringBuilder();
		if(titel.length().intValue()> Rezensionenverwaltung.getMaxTitel())	sb.append("\n  zu lang");
		if(titel.length().intValue()< Rezensionenverwaltung.getMinTitel())	sb.append("\n  Der Rezensionstitel ist zu kurz min."+Rezensionenverwaltung.getMinTitel());
		if(inhalt.length()> Rezensionenverwaltung.getMaxInhalt())			sb.append("\n--inhalt zu lang");
		if(inhalt.length()< Rezensionenverwaltung.getMinInhalt())			sb.append("\n  Der Rezensionsinhalt ist zu lang min."+Rezensionenverwaltung.getMinInhalt());
		if(sb.length()>0)
			throw new Exception("\nFehler Rezension '"+titel.get()+"' zu Film: '"+film.getTitel()+"' "+sb.toString());
	}
}
