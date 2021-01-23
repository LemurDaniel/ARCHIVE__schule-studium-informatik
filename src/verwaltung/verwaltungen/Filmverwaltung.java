package verwaltung.verwaltungen;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import verwaltung.Verwaltung;
import verwaltung.entitaeten.Film;
import verwaltung.entitaeten.Genre;

public class Filmverwaltung extends Verwaltung<Film>{
	
	private static Filmverwaltung instance;		
	public static Filmverwaltung instance() {
		if(instance == null) instance = new Filmverwaltung();
		return instance;
	}
	private static Map<Integer, Genre> gMap;
	private static List<Genre> genre;
	
	public static List<Genre> getGenres(){
		if(genre == null) {
			gMap = new HashMap<>();
			try(Connection con = getCon();){
				ResultSet rs = con.createStatement().executeQuery("Select id, genre from genre");		
				while(rs.next())
					gMap.put(rs.getInt("id"), new Genre(rs.getInt("id"), rs.getString("genre")) );
				
				rs = con.createStatement().executeQuery("Select sgid, gid from genre_subgenre order by sgid");
				rs.next();
				
				ArrayList<Integer> sgidList = new ArrayList<>();
				int lastSgid = rs.getInt("sgid"), sgid, gid;
				sgidList.add(lastSgid);
				do{
					sgid = rs.getInt("sgid");
					gid = rs.getInt("gid");
					if(lastSgid != sgid) {
						sgidList.add(sgid);
						lastSgid = sgid;
					}
					gMap.get(gid).addSubGenre( gMap.get(sgid) );
				}while(rs.next()); 

				genre = new ArrayList<>();
				gMap.values().forEach(g->{
					if(!sgidList.stream().anyMatch(id->id==g.getId()))
						genre.add(g);
				});
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return genre;
	}
	
	private Filmverwaltung() {
		getGenres();
	}
	
	public void test() throws SQLException{
		try(Connection con = getCon();){
			ResultSet rs = con.createStatement().executeQuery("Select * from filme");
			while(rs.next()) {
				list.add( new Film(rs.getInt("id"), rs.getString("titel"), gMap.get(rs.getInt("genre")), rs.getInt("dauer"), rs.getInt("erscheinungsjahr"), rs.getFloat("bewertung")) );
			}
		}
	}
	
	public static int getMaxTitel() {
		return maxSize.get("filmTitel");
	}
}