package verwaltung.verwaltungen;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import verwaltung.Nutzer;
import verwaltung.entitaeten.Film;
import verwaltung.entitaeten.Genre;
import verwaltung.entitaeten.Liste;

public class Filmverwaltung extends Verwaltung<Film>{
	
	public static List<Genre> getGenres(){
		List<Genre> g = new ArrayList<>();
		genre.forEach((k, v)->g.add(v));
		return g;
	}
	
	
	/** **/
	private static Map<Integer, Film> geladeneFilme = new HashMap<>();
	private static Map<Film, Integer> referenziert = new HashMap<>();
	private static List<Filmverwaltung> fvws = new ArrayList<>();
	
	
	public void generateFilm(ResultSet rs) throws SQLException {	
		int lastId = -1, idNow;
		Film current = null;
		while(rs.next()) {
			idNow = rs.getInt("id");
			if(lastId!=idNow) {
				if(geladeneFilme.containsKey(idNow))	
					current = geladeneFilme.get(idNow);
				else {
					current = new Film(idNow, rs.getInt("ersteller"), rs.getString("titel"), rs.getInt("dauer"), rs.getInt("erscheinungsjahr"), rs.getFloat("bewertung"));
				}
				addObj(current);
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
				generateFilm(rs);
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
			addObj(f);
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
	
	public void filter(String titel, Float bwtMax, Float bwtMin, Integer dauerMax, Integer dauerMin, Integer jahrMax,
			Integer jahrMin, List<Genre> genre, boolean and, List<String> tags, int anzahl) throws SQLException {
		
		System.out.println(titel);

		if(bwtMax==null)	bwtMax=10f;
		if(bwtMin==null)	bwtMin=0f;	else bwtMin-=0.01f;
		if(dauerMax==null)  dauerMax=getMaxDauer();
		if(dauerMin==null) 	dauerMin=0;
		if(jahrMax==null)	jahrMax=getMaxJahr();
		if(jahrMin==null)	jahrMin=getMinJahr();
		if(tags==null)		tags=new ArrayList<>();
		if(genre==null)		genre=new ArrayList<>();
		
		System.out.println(bwtMax);
		System.out.println(bwtMin);
		
		StringBuilder sb = new StringBuilder();

		//Genre ODER
		//Select id, ... from
		//	(Select Top (?) * from 
		//		(Select fid from genre_film where gid in ( ?,... ) group by fid  ) as fmg 
		//	join film on fmg.fid=film.id where ...) as filtered 
		//join genre_film on genre_film.fid=filtered.fid order by id
		
		//Genre UND
		//Select id, ... from
		//	(Select Top (?) * from 
		//		(Select fid from genre_film where gid in ( ?,... ) group by fid  Having count(gid)=?) as fmg 
		//	join film on fmg.fid=film.id where...) as filtered 
		//join genre_film on genre_film.fid=filtered.fid order by id
		
		//Kein Genre ausgewählt
		//Select id, ... from (Select Top (?) film.id as fid, * from film where ... ) as filtered 
		//join genre_film on genre_film.fid=filtered.fid order by id 
		
		sb.append("Select id, titel, dauer, erscheinungsjahr, bewertung, ersteller, gid from ");
		
		if(genre.size()!=0) {
			sb.append("(Select Top (?) * from ");
				sb.append("(Select fid from genre_film where gid in ( ");			// In( ?, ... ) kurz für multiple Or
				for(int i=0; i<genre.size(); i++) 
					sb.append("?" + (i==genre.size()-1 ? " ) group by fid ":", ") );
				if(and) sb.append("Having count(gid)=? ");
				sb.append(") as fmg ");
				sb.append("join film on fmg.fid=film.id ");
		}else
			sb.append("(Select Top (?) film.id as fid, * from film ");
		
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
		sb.append(") as filtered ");
		sb.append("join genre_film on genre_film.fid=filtered.fid ");
		sb.append("order by id ");
		
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
				ps.setFloat(++count, bwtMin);
				ps.setFloat(++count, bwtMax);
				ps.setInt(++count, dauerMin);
				ps.setInt(++count, dauerMax);
				ps.setInt(++count, jahrMin);
				ps.setInt(++count, jahrMax);
				for(String s:tags)
					ps.setString(++count, "%"+s+"%");
				
				try(ResultSet rs = ps.executeQuery()){
					clear();
					generateFilm(rs);
				}
			}
		}
	}
	
	
	@Override
	public void addObj(Film f) {
		super.addObj(f);
		if(!geladeneFilme.containsKey(f.getId())) {
			geladeneFilme.put(f.getId(), f);
			referenziert.put(f, 0);
		}else {
			int i = referenziert.get(f)+1;
			referenziert.put(f, i);
		}
	}
	@Override
	public void removeObj(Film f) {
		super.removeObj(f);
		int i = referenziert.get(f)-1;
		if(i==0) {
			geladeneFilme.remove(f.getId());
			referenziert.remove(f);
		}else
			referenziert.put(f, i);
	}
	@Override
	public void clear() {
		getList().forEach(f->{
			removeObj(f);
		});
	}
	
	public static void refreshAll(Connection connect) throws SQLException {
		Map<Filmverwaltung, List<Film>> fvwListen = new HashMap<>();
		for(Filmverwaltung fvw: fvws) {
			fvwListen.put(fvw, fvw.getList());
			fvw.clear();
		}

		for(Filmverwaltung fvw:fvws) {
			StringBuilder sb = new StringBuilder();
			sb.append("Select * from Film where id in ( ");
			
			List<Film> fliste = fvwListen.get(fvw);
			for(int i=0; i<fliste.size(); i++) 
				sb.append( fliste.get(i).getId() +(i==fliste.size()-1? ") ":", ") );
			try (Connection con = connect!=null? connect:getCon()){
				try(Statement st = con.createStatement()){
					try(ResultSet rs = st.executeQuery(sb.toString())){
						fvw.generateFilm(rs);
					}
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
	public static int getMinErgebnisse() {
		return maxSize.get("MinErgebnisse");
	}
	public static int getMaxErgebnisse() {
		return maxSize.get("MaxErgebnisse");
	}
}
