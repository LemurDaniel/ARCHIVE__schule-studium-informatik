package verwaltung;

import java.sql.SQLException;

import verwaltung.entitaeten.Film;

public abstract class Unterverwaltung<T> extends Verwaltung<T>{
	
	private boolean isLoaded;
	protected Film film;
	
	public Unterverwaltung(Film film) {
		this.film = film;	
	}
	
	protected void load() throws SQLException {
		isLoaded = true;
	}
	public void loadIfnotLoaded() throws SQLException {
		if(!isLoaded) load();
	}
	protected void clear() {
		isLoaded = false;
	}
	public boolean isLoaded() {
		return isLoaded;
	}
}
