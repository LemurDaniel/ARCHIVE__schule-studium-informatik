package verwaltung.verwaltungen.unterverwaltungen;

import java.sql.Connection;
import java.sql.SQLException;

import verwaltung.DB_Manager;
import verwaltung.entitaeten.Backup;
import verwaltung.entitaeten.EingabePruefung;
import verwaltung.entitaeten.Film;
import verwaltung.verwaltungen.Verwaltung;

public abstract class Unterverwaltung<T extends Backup & EingabePruefung> extends Verwaltung<T>{
	
	private boolean isLoaded;
	protected Film film;
	
	protected Unterverwaltung(Film film) {
		this.film = film;	
	}
	
	public void load() throws SQLException{
		try(Connection con = DB_Manager.getCon()){
			load(con);
		}
	}	
	public void loadIfnotLoaded() throws SQLException {
		if(!isLoaded) load();
	}
	public void load(Connection con) throws SQLException {
		if(isLoaded) clear();
		isLoaded = true;
	}
	public void loadIfnotLoaded(Connection con) throws SQLException {
		if(!isLoaded) load(con);
	}
	@Override
	public void clear() {
		super.clear();
		isLoaded = false;
	}
	public boolean isLoaded() {
		return isLoaded;
	}
}
