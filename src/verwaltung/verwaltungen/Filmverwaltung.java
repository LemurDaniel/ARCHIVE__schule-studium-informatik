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
		getGenres();
		try(Connection con = getCon();){
			ResultSet rs = con.createStatement().executeQuery("select * from filme left join genre_filme on fid = filme.id order by filme.id");
			
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
			PreparedStatement ps = con.prepareStatement("Insert into filme(titel, dauer, erscheinungsjahr, bewertung, ersteller) values(?, ?, ?, ?, ?); Select SCOPE_IDENTITY()");
			ps.setString(	1, 	titel				);
			ps.setInt(		2, 	dauer				);
			ps.setInt(		3,	erscheinungsjahr	);
			ps.setFloat(	4, 	0.0f					);
			ps.setInt(		5,  Nutzer.getNutzer().getId()	);
			ResultSet rs = ps.executeQuery();
			rs.next();
			Film f = new Film(rs.getInt(1), Nutzer.getNutzer().getId(), titel, dauer, erscheinungsjahr, 0 );

			for(Genre g: genres) {
				ps = con.prepareStatement("Insert into genre_filme(gid, fid) values(?,?)");
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
			PreparedStatement ps = con.prepareStatement("Update filme set titel=?, dauer=?, erscheinungsjahr=? where id=?;");
			ps.setString(	1, 	titel				);
			ps.setInt(		2, 	dauer				);
			ps.setInt(		3,	erscheinungsjahr	);
			ps.setInt(		4,	film.getId()		);
			ps.executeUpdate();

			con.createStatement().executeUpdate("Delete from genre_filme where fid="+film.getId());		
			
			for(Genre g: genres) {
				ps = con.prepareStatement("Insert into genre_filme(gid, fid) values(?,?)");
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
