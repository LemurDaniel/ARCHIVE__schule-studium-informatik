package bla;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Personenverwaltung extends Verwaltung<Person>{

	private static Personenverwaltung instance;
	private static ObservableList<String> rollen;
	public static ObservableList<String> getRollen() {
		if(rollen == null) {
			rollen = FXCollections.observableArrayList();
			try(Connection con = getCon();){
				ResultSet rs = con.createStatement().executeQuery("Select rolle from rollen");
				while(rs.next()) rollen.add(rs.getString(1));
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return FXCollections.unmodifiableObservableList(rollen);
	}
	
	private int filmId;
	public Personenverwaltung() {}
	
	public void setFilmId(int filmId) {
		this.filmId = filmId;
	}
	
	public void load() throws Exception {
		list.clear();
		try(Connection con = getCon()){
			ResultSet rs = con.createStatement().executeQuery("Select pid, vorname, name, rolle from personen "
															+"inner join filme_personen_rollen on pid = personen.id "
															+"inner join rollen on rid = rollen.id "
															+"where fid="+filmId);
			while(rs.next())list.add(new Person(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
		}
	}
	

}
