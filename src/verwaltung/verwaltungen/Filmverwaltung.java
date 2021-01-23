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
		if(gMap == null) {
			gMap = new HashMap<>();
			genre = new ArrayList<>();
			try(Connection con = getCon();){
				ResultSet rs = con.createStatement().executeQuery("Select id, genre, text from genre");		
				while(rs.next()) {
					Genre g = new Genre(rs.getInt("id"), rs.getString("genre"), rs.getString("text"));
					gMap.put(g.getId(), g);
					genre.add(g);
				}				
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
		getGenres();
		try(Connection con = getCon();){
			ResultSet rs = con.createStatement().executeQuery("select * from film left join genre_film on fid = film.id order by film.id");
			
			int lastId = -1, idNow;
			Film current = null;
			while(rs.next()) {
				idNow = rs.getInt("id");
				if(lastId!=idNow) {
					current = new Film(idNow, rs.getInt("ersteller"), rs.getString("titel"), rs.getInt("dauer"), rs.getInt("erscheinungsjahr"), rs.getFloat("bewertung"));
					list.add(current);
					lastId = idNow;
				}
				if(rs.getObject("gid")!=null)
					current.addGenre( gMap.get(rs.getInt("gid")) );	
			}
		}
	}
	
	public Film addFilm(String titel, List<Genre> genres, int dauer, int erscheinungsjahr) throws SQLException {
		
		try(Connection con = getCon();){
			con.setAutoCommit(false);
			PreparedStatement ps = con.prepareStatement("Insert into film(titel, dauer, erscheinungsjahr, bewertung, ersteller) values(?, ?, ?, ?, ?); Select SCOPE_IDENTITY()");
			ps.setString(	1, 	titel				);
			ps.setInt(		2, 	dauer				);
			ps.setInt(		3,	erscheinungsjahr	);
			ps.setFloat(	4, 	0.0f					);
			ps.setInt(		5,  Nutzer.getNutzer().getId()	);
			ResultSet rs = ps.executeQuery();
			rs.next();
			Film f = new Film(rs.getInt(1), Nutzer.getNutzer().getId(), titel, dauer, erscheinungsjahr, 0 );

			for(Genre g: genres) {
				ps = con.prepareStatement("Insert into genre_film(gid, fid) values(?,?)");
				ps.setInt(1, g.getId());
				ps.setInt(2, f.getId());
				ps.executeUpdate();
				f.addGenre(g);
			}
			con.commit();
			list.add(f);
			return f;
		}
	}

	public void updateFilm(String titel, List<Genre> genres, int dauer, int erscheinungsjahr, Film film) throws SQLException {
	
		try(Connection con = getCon();){
			con.setAutoCommit(false);
			PreparedStatement ps = con.prepareStatement("Update film set titel=?, dauer=?, erscheinungsjahr=? where id=?;");
			ps.setString(	1, 	titel				);
			ps.setInt(		2, 	dauer				);
			ps.setInt(		3,	erscheinungsjahr	);
			ps.setInt(		4,	film.getId()		);
			ps.executeUpdate();

			con.createStatement().executeUpdate("Delete from genre_film where fid="+film.getId());		
			
			for(Genre g: genres) {
				ps = con.prepareStatement("Insert into genre_film(gid, fid) values(?,?)");
				ps.setInt(1, g.getId());
				ps.setInt(2, film.getId());
				ps.executeUpdate();
				System.out.println(g.getId()+ "    "+film.getId());
			}
			con.commit();
			
			film.setTitel(titel);
			film.setDauer(dauer);
			film.setErscheinungsjahr(erscheinungsjahr);
			film.clearGenre();
			genres.forEach(g->film.addGenre(g));
			
		}
	}
	
	
	public static int getMaxTitel() {
		return maxSize.get("filmTitel");
	}
}
