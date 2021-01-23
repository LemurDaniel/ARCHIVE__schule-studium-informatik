package verwaltung.verwaltungen;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import verwaltung.Unterverwaltung;
import verwaltung.entitaeten.Film;
import verwaltung.entitaeten.Person;

public class Personenverwaltung extends Unterverwaltung<Person>{

	private static Map<Integer, String> rollen;
	public static ObservableMap<Integer, String> getRollen() {
		if(rollen == null) {
			rollen = new HashMap<>();
			try(Connection con = getCon();){
				ResultSet rs = con.createStatement().executeQuery("Select id, rolle from rollen");
				while(rs.next()) rollen.put(rs.getInt(1), rs.getString(2));
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return FXCollections.observableMap(rollen);
	}
	
	
	
	public Personenverwaltung(Film film) {
		super(film);
	}
	
	@Override
	public void load() throws SQLException {
		super.load();
		try(Connection con = getCon()){
			ResultSet rs = con.createStatement().executeQuery("Select pid, vorname, name, rolle from personen "
															+"inner join filme_personen_rollen on pid = personen.id "
															+"inner join rollen on rid = rollen.id "
															+"where fid="+film.getId());
			while(rs.next())list.add(new Person(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
		}
	}
	

}
