package verwaltung.verwaltungen;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import verwaltung.Verwaltung;
import verwaltung.entitaeten.Film;
import verwaltung.entitaeten.Genre;
import verwaltung.entitaeten.Nutzer;

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
				list.add( new Film(rs.getInt("id"), rs.getInt("ersteller"), rs.getString("titel"), gMap.get(rs.getInt("genre")), rs.getInt("dauer"), rs.getInt("erscheinungsjahr"), rs.getFloat("bewertung")) );
			}
		}
	}
	
	public Film addFilm(String titel, Genre genre, int dauer, int erscheinungsjahr) throws SQLException {
		
		try(Connection con = getCon();){
			PreparedStatement ps = con.prepareStatement("Insert into filme(titel, genre, dauer, erscheinungsjahr, bewertung, ersteller) values(?, ?, ?, ?, ?, ?); Select SCOPE_IDENTITY()");
			ps.setString(	1, 	titel				);
			ps.setInt(		2, 	genre.getId()		);
			ps.setInt(		3, 	dauer				);
			ps.setInt(		4,	erscheinungsjahr	);
			ps.setFloat(	5, 	0					);
			ps.setInt(		6,  Nutzer.getNutzer().getId()	);
			ResultSet rs = ps.executeQuery();
			rs.next();
			Film f = new Film(rs.getInt(1), Nutzer.getNutzer().getId(), titel, genre, dauer, erscheinungsjahr, 0 );
			list.add(f);
			return f;
		}
	}

	public void updateFilm(String titel, Genre genre, int dauer, int erscheinungsjahr, Film film) throws SQLException {
	
		try(Connection con = getCon();){
			PreparedStatement ps = con.prepareStatement("Update filme set titel=?, genre=?, dauer=?, erscheinungsjahr=? where id=?;");
			ps.setString(	1, 	titel				);
			ps.setInt(		2, 	genre.getId()		);
			ps.setInt(		3, 	dauer				);
			ps.setInt(		4,	erscheinungsjahr	);
			ps.setInt(		5,	film.getId()		);
			ps.executeUpdate();

			film.setTitel(titel);
			film.setGenre(genre);
			film.setDauer(dauer);
			film.setErscheinungsjahr(erscheinungsjahr);
		}
	}
	
	
	public static int getMaxTitel() {
		return maxSize.get("filmTitel");
	}
}
