package verwaltung.verwaltungen;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import verwaltung.Verwaltung;
import verwaltung.entitaeten.Film;

public class Filmverwaltung extends Verwaltung<Film>{
	
	private static Filmverwaltung instance;	
	
	public static Filmverwaltung instance() {
		if(instance == null) instance = new Filmverwaltung();
		return instance;
	}
	
	private Filmverwaltung() {}
	
	public void test() throws SQLException{
		try(Connection con = getCon();){
			ResultSet rs = con.createStatement().executeQuery("Select * from filme");
			while(rs.next()) {
				list.add(new Film(rs));
			}
		}
	}
}
