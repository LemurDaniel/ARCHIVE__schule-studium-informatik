package verwaltung.verwaltungen.unterverwaltungen;

import java.sql.Connection;
import java.sql.SQLException;

import verwaltung.DB_Manager;
import verwaltung.entitaeten.Film;
import verwaltung.entitaeten.Person;

public class VerwaltungenWrapper extends DB_Manager{
	
	private Personenverwaltung pvw;
	private Rezensionenverwaltung rvw;
	
	public VerwaltungenWrapper(Film film) {
		pvw = new Personenverwaltung(film);
		rvw = new Rezensionenverwaltung(film);
	}
	
	public Personenverwaltung getPvw() {
		return pvw;
	}
	public Rezensionenverwaltung getRvw() {
		return rvw;
	}
	
	public void loadIfnotLoaded() throws SQLException {
		if(pvw.isLoaded() && rvw.isLoaded())
			return;
		
		try(Connection con = getCon()){
			pvw.loadIfnotLoaded(con);
			rvw.loadIfnotLoaded(con);
		}
	}

}
