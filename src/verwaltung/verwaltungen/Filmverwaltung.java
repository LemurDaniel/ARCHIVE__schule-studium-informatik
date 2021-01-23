package verwaltung.verwaltungen;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import verwaltung.Nutzer;
import verwaltung.entitaeten.Film;
import verwaltung.entitaeten.Genre;

public class Filmverwaltung extends Verwaltung<Film>{
	
	private static Filmverwaltung instance;		
	public static Filmverwaltung instance() {
		if(instance == null) instance = new Filmverwaltung();
		return instance;
	}

	public static List<Genre> getGenres(){
		List<Genre> g = new ArrayList<>();
		genre.forEach((k, v)->g.add(v));
		return g;
	}
	
	private Filmverwaltung() {
		getGenres();
	}

	public void test() throws SQLException{
		String sql = "select * from film left join genre_film on fid = film.id order by film.id";
		try(Connection con = getCon();
				Statement st = con.createStatement();
					ResultSet rs = con.createStatement().executeQuery(sql)){
			
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
					current.addGenre( genre.get(rs.getInt("gid")) );	
			}
		}
	}
	
	public Film addFilm(String titel, List<Genre> genres, int dauer, int erscheinungsjahr, Connection connect) throws SQLException {
		
		String sql = "Insert into film(titel, dauer, erscheinungsjahr, bewertung, ersteller) values(?, ?, ?, ?, ?); Select SCOPE_IDENTITY()";
		
		try(Connection con = connect!=null ? connect:getCon();
				PreparedStatement ps = con.prepareStatement(sql)){
			
			con.setAutoCommit(false);
			ps.setString(	1, 	titel				);
			ps.setInt(		2, 	dauer				);
			ps.setInt(		3,	erscheinungsjahr	);
			ps.setFloat(	4, 	0					);
			ps.setInt(		5,  Nutzer.getNutzer().getId()	);
			
			Film f;
			try(ResultSet rs = ps.executeQuery()) {
				rs.next();
				f = new Film(rs.getInt(1), Nutzer.getNutzer().getId(), titel, dauer, erscheinungsjahr, 0 );
			}

			updateGenres(con, genres, f.getId());
			con.commit();
			list.add(f);
			return f;
		}
	}

	public void updateFilm(String titel, List<Genre> genres, int dauer, int erscheinungsjahr, Film film, Connection connect) throws SQLException {
	
		String sql = "Update film set titel=?, dauer=?, erscheinungsjahr=? where id=?;";
		
		try(Connection con = connect!=null ? connect:getCon();
				PreparedStatement ps = con.prepareStatement(sql)){
			
			con.setAutoCommit(false);
			ps.setString(	1, 	titel				);
			ps.setInt(		2, 	dauer				);
			ps.setInt(		3,	erscheinungsjahr	);
			ps.setInt(		4,	film.getId()		);
			ps.executeUpdate();

			updateGenres(con, genres, film.getId());
			con.commit();
		}
		film.setTitel(titel);
		film.setDauer(dauer);
		film.setErscheinungsjahr(erscheinungsjahr);
		film.clearGenre();
		genres.forEach(g->film.addGenre(g));		
	}
	
	private void updateGenres(Connection con, List<Genre> genres, int fid) throws SQLException{
		String sql1 = "Delete from genre_film where fid="+fid;
		String sql2 = "Insert into genre_film(gid, fid) values(?,?)";
		try(PreparedStatement ps1 = con.prepareStatement(sql1)){
			ps1.executeUpdate();
			for(Genre g: genres) {
				try(PreparedStatement ps2 = con.prepareStatement(sql2)){
					ps2.setInt(1, g.getId());
					ps2.setInt(2, fid);
					ps2.executeUpdate();
					System.out.println(g.getId()+"   "+fid);
				}
			}			
		}
	}
	
	public static int getMaxTitel() {
		return maxSize.get("FilmTitel");
	}

	public static int getMaxDauer() {
		return maxSize.get("FilmDauer");
	}
	public static int getMinJahr() {
		return maxSize.get("MinJahr");
	}
	public static int getMaxJahr() {
		return maxSize.get("MaxJahr");
	}
}
