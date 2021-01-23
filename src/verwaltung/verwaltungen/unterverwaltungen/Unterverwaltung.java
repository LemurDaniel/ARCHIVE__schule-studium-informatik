package verwaltung.verwaltungen.unterverwaltungen;

import java.sql.Connection;
import java.sql.SQLException;

import verwaltung.DB_Manager;
import verwaltung.entitaeten.Backup;
import verwaltung.entitaeten.EingabePruefung;
import verwaltung.entitaeten.Film;
import verwaltung.entitaeten.Id;
import verwaltung.verwaltungen.Verwaltung;

public abstract class Unterverwaltung<T extends Backup & EingabePruefung & Id> extends Verwaltung<T>{
	
	private boolean istGeladen;
	protected Film film;
	
	protected Unterverwaltung(Film film) {
		this.film = film;	
	}
	
	public void lade() throws SQLException{
		try(Connection con = DB_Manager.getCon()){
			lade(con);
		}
	}	
	public void lade(Connection con) throws SQLException {
		if(istGeladen) clear();
		istGeladen = true;
	}

	@Override
	public void clear() {
		super.clear();
		istGeladen = false;
	}
	public boolean isGeladen() {
		return istGeladen;
	}
}
