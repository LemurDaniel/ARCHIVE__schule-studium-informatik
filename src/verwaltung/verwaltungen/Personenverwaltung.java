package verwaltung.verwaltungen;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import verwaltung.Unterverwaltung;
import verwaltung.entitaeten.Film;
import verwaltung.entitaeten.Person;

public class Personenverwaltung extends Unterverwaltung<Person>{

	public static Map<String, Integer> rollen;
	public static ObservableList<String> getRollen() {
		if(rollen == null) {
			rollen = new HashMap<>();
			try(Connection con = getCon();){
				ResultSet rs = con.createStatement().executeQuery("Select id, rolle from rollen");
				while(rs.next()) rollen.put(rs.getString(2), rs.getInt(1));
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

		ObservableList<String> oblist = FXCollections.observableArrayList();
		rollen.forEach( (k, v)-> oblist.add(k) );
		return oblist;
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
	
	
	public void addOrUpdate() {
		
	}
}
