package verwaltung.verwaltungen.unterverwaltungen;

import java.sql.Connection;
import java.sql.SQLException;

import verwaltung.entitaeten.Entitaet;
import verwaltung.entitaeten.Film;
import verwaltung.verwaltungen.Verwaltung;

public abstract class Unterverwaltung<T extends Entitaet> extends Verwaltung<T>{
	
	private boolean isLoaded;
	protected Film film;
	
	protected Unterverwaltung(Film film) {
		this.film = film;	
	}
	
	public void load() throws SQLException{
		try(Connection con = getCon()){
			load(con);
		}
	}	
	void load(Connection con) throws SQLException {
		if(isLoaded) list.clear();
		isLoaded = true;
	}
	void loadIfnotLoaded(Connection con) throws SQLException {
		if(!isLoaded) load(con);
	}
	void clear() {
		list.clear();
		isLoaded = false;
	}
	boolean isLoaded() {
		return isLoaded;
	}
}