package bla;

import java.sql.Connection;
import java.sql.ResultSet;

public class Filmverwaltung extends Verwaltung<Film>{
	
	private static Filmverwaltung instance;	
	
	public static Filmverwaltung instance() {
		if(instance == null) instance = new Filmverwaltung();
		return instance;
	}
	
	private Filmverwaltung() {}
	
	public void test() throws Exception{
		try(Connection con = getCon();){
			ResultSet rs = con.createStatement().executeQuery("Select * from filme");
			while(rs.next()) {
				list.add(new Film(rs));
			}
		}
	}
}
