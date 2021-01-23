package verwaltung.verwaltungen;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import verwaltung.Nutzer;
import verwaltung.entitaeten.Film;
import verwaltung.entitaeten.Genre;
import verwaltung.entitaeten.Liste;

public class Filmverwaltung extends Verwaltung<Film>{
	
	private static Filmverwaltung instance;		
	public static Filmverwaltung instance() {
		if(instance == null) instance = new Filmverwaltung();
		return instance;
	}
	private Filmverwaltung() {	}
	
	public static List<Genre> getGenres(){
		List<Genre> g = new ArrayList<>();
		genre.forEach((k, v)->g.add(v));
		return g;
	}
	
	
	/** **/
	
	
	
	
	
	public static void generateFilm(ResultSet rs, List<Film> fliste) throws SQLException {		
		int lastId = -1, idNow;
		Film current = null;
		while(rs.next()) {
			idNow = rs.getInt("id");
			if(lastId!=idNow) {
				current = new Film(idNow, rs.getInt("ersteller"), rs.getString("titel"), rs.getInt("dauer"), rs.getInt("erscheinungsjahr"), rs.getFloat("bewertung"));
				fliste.add(current);
				lastId = idNow;
			}
			if(rs.getObject("gid")!=null)
				current.addGenre( genre.get(rs.getInt("gid")) );	
		}
	}
	
	public void test() throws SQLException{
		String sql = "select * from film left join genre_film on fid = film.id order by film.id";
		try(Connection con = getCon();
				Statement st = con.createStatement();
					ResultSet rs = con.createStatement().executeQuery(sql)){
				generateFilm(rs, list);
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
			genres.forEach(g->f.addGenre(g));
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
	
	public void filter(String titel, Integer bwtMax, Integer bwtMin, Integer dauerMax, Integer dauerMin, Integer jahrMax,
			Integer jahrMin, List<Genre> genre, boolean and, List<String> tags, int anzahl) throws SQLException {
		
		System.out.println(titel);

		if(bwtMax==null)	bwtMax=10;
		if(bwtMin==null)	bwtMin=0;
		if(dauerMax==null)  dauerMax=getMaxDauer();
		if(dauerMin==null) 	dauerMin=0;
		if(jahrMax==null)	jahrMax=getMaxJahr();
		if(jahrMin==null)	jahrMin=getMinJahr();
		if(tags==null)		tags=new ArrayList<>();
		if(genre==null)		genre=new ArrayList<>();
		
		StringBuilder sb = new StringBuilder();

//		sb.append("Select * from film join "
//					+"(Select fid from (Select * from genre_film where gid in(75, 72)) as g "
//					+"group by fid Having count(g.gid)=2) as fmg "
//				+ "on fmg.fid = film.id ");
		
		sb.append("Select Top (?) * from film ");
		
		if(genre.size()!=0) {		
			sb.append("join ");
				sb.append("(Select fid from (Select * from genre_film where gid in ( ");			// In( ?, ... ) kurz für multiple Or
				for(int i=0; i<genre.size(); i++) 
					sb.append("?" + (i==genre.size()-1 ? ")) as g group by fid ":", ") );
				if(and) sb.append("Having count(g.gid)=? ");
				sb.append(") as fmg ");
			sb.append("on fmg.fid = film.id ");
		}
		
		sb.append("join genre_film on film.id=genre_film.fid ");
		sb.append("where ");
		
		if(titel!=null)	sb.append("titel Like ? and ");
		sb.append("bewertung between ? and ? ");
		sb.append("and dauer between ? and ? ");
		sb.append("and erscheinungsjahr between ? and ? ");
		
		if(tags.size()!=0) {
			sb.append("and ( ");
			for(int i=0; i<tags.size(); i++) 
				sb.append("titel Like ? "+ (i==tags.size()-1 ? ") ":"or ") );
		}
		sb.append("order by film.id ");
		
		System.out.println(sb.toString());
		
		try(Connection con = getCon()){
			try(PreparedStatement ps = con.prepareStatement(sb.toString())){
				int count=0;
				ps.setInt(++count, anzahl);
				if(genre.size()!=0) {
					for(Genre g:genre)	ps.setInt(++count, g.getId());
					if(and)	ps.setInt(++count, genre.size());
				}
				
				if(titel!=null) ps.setString(++count, titel+"%");
				ps.setInt(++count, bwtMin);
				ps.setInt(++count, bwtMax);
				ps.setInt(++count, dauerMin);
				ps.setInt(++count, dauerMax);
				ps.setInt(++count, jahrMin);
				ps.setInt(++count, jahrMax);
				for(String s:tags)
					ps.setString(++count, "%"+s+"%");
				
				try(ResultSet rs = ps.executeQuery()){
					list.clear();
					generateFilm(rs, list);
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
	public static int getMaxGenre() {
		return maxSize.get("Genre");
	}
}
