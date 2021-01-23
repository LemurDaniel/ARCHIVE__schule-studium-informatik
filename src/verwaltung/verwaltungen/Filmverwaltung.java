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

public class Filmverwaltung extends Verwaltung<Film>{
	
	public static List<Genre> getGenres(){
		List<Genre> g = new ArrayList<>();
		genre.forEach((k, v)->g.add(v));
		return g;
	}
	
	
	/** **/
	private static Map<Integer, Film> geladeneFilme = new HashMap<>();
	private static Map<Film, Integer> referenziert = new HashMap<>();
	
	
	public void generiereFilme(ResultSet rs) throws SQLException {	
		int lastId = -1, idNow;
		Film current = null;
		while(rs.next()) {
			idNow = rs.getInt("id");
			if(lastId!=idNow) {
				if(geladeneFilme.containsKey(idNow))	current = geladeneFilme.get(idNow).aktualisiere(rs.getString("titel"), rs.getInt("dauer"), rs.getInt("erscheinungsjahr"), rs.getFloat("bewertung"));
				else 									current = new Film(idNow, rs.getInt("ersteller"), rs.getString("titel"), rs.getInt("dauer"), rs.getInt("erscheinungsjahr"), rs.getFloat("bewertung"));
				addObj(current);
				lastId = idNow;
			}
			current.addGenre( genre.get(rs.getInt("gid")) );	
		}
	}
	
	public void test() throws SQLException{
	//	filter(null, 10f, 9f, null, null, null, null, null, true, null, 10);
		String sql = "select Top (10) * from film left join genre_film on fid = film.id order by film.id";
		try(Connection con = getCon();
				Statement st = con.createStatement();
					ResultSet rs = con.createStatement().executeQuery(sql)){
				generiereFilme(rs);
			}		
	}
	
	@Override
	protected void add(Film f, Connection con) throws SQLException {
				
		String sql = "Insert into film(titel, dauer, erscheinungsjahr, bewertung, ersteller) values(?, ?, ?, ?, ?); Select SCOPE_IDENTITY()";
		
		try(PreparedStatement ps = con.prepareStatement(sql)){			
			con.setAutoCommit(false);	
			
			ps.setString(	1, 	f.getTitel()				);
			ps.setInt(		2, 	f.getDauer()				);
			ps.setInt(		3,	f.getErscheinungsjahr()		);
			ps.setFloat(	4, 	0							);
			ps.setInt(		5,  Nutzer.getNutzer().getId()	);
			
			try(ResultSet rs = ps.executeQuery()) {
				rs.next();
				f.setId(rs.getInt(1));
			}

			updateGenres(con, f.getGenres(), f.getId());
			con.commit();
			con.setAutoCommit(true);
		}		
		addObj(f);	
	}
	
	@Override
	protected void update(Film alt, Film neu, Connection con) throws SQLException {
	
		String sql = "Update film set titel=?, dauer=?, erscheinungsjahr=? where id=?;";
		
		try(PreparedStatement ps = con.prepareStatement(sql)){
			
			con.setAutoCommit(false);
			ps.setString(	1, 	neu.getTitel()				);
			ps.setInt(		2, 	neu.getDauer()				);
			ps.setInt(		3,	neu.getErscheinungsjahr()		);
			ps.setInt(		4,	alt.getId()					);
			ps.executeUpdate();

			updateGenres(con, neu.getGenres(), alt.getId());
			con.commit();
			con.setAutoCommit(true);
		}
		alt.setTitel(neu.getTitel());
		alt.setDauer(neu.getDauer());
		alt.setErscheinungsjahr(neu.getErscheinungsjahr());
		alt.clearGenre();
		neu.getGenres().forEach(g->alt.addGenre(g));		
	}
	@Override
	protected void delete(Film f, Connection con) {
		// f
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
				sb.append("(Select fid from genre_film where gid in ( ");
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
				System.out.println(con.getAutoCommit());
				System.out.println(ps.getConnection());
				try(ResultSet rs = ps.executeQuery()){
					clear();
					generiereFilme(rs);
				}
			}
		}
	}
	
	
	@Override
	public void addObj(Film f) {
		if(super.getList().contains(f)) return;
		super.addObj(f);
		if(!geladeneFilme.containsKey(f.getId())) {
			geladeneFilme.put(f.getId(), f);
			referenziert.put(f, 1);
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
		getList().forEach(this::removeObj);
//		referenziert.forEach((k,v)->System.out.println(k+"  "+v));
//		geladeneFilme.forEach((k,v)->System.out.println(k+"  "+v));
	}
	public void refresh() {
		
	}
	
	public static void refreshAll() throws SQLException {
		StringBuilder sb = new StringBuilder();
		sb.append("Select * from Film join genre_film on fid=id where id in ( ");
		
		int count = 0, size = geladeneFilme.size();
		for(Integer id: geladeneFilme.keySet())		sb.append( id+ (++count==size ? ") ":", ") );
	
		sb.append("order by id");
		
		System.out.println(sb.toString());
		try(Connection con = getCon();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sb.toString())){
			
			Film current = new Film(-1, 0, "", 0, 0, 0);
			int idNow;
			while(rs.next()) {
				idNow = rs.getInt("id");
				if(current.getId()!=idNow) 	current = geladeneFilme.get(idNow).aktualisiere(rs.getString("titel"), rs.getInt("dauer"), rs.getInt("erscheinungsjahr"), rs.getFloat("bewertung"));
				current.addGenre( genre.get(rs.getInt("gid")) );	
			}
		}
	}	
	
	
	public static Film getFilmById(int id) {
		return geladeneFilme.get(id);
	}
	
	public static int getMaxTitel() {
		return maxSize.get("FilmTitelMax");
	}
	public static int getMaxDauer() {
		return maxSize.get("FilmDauerMax");
	}
	public static int getMinJahr() {
		return maxSize.get("JahrMin");
	}
	public static int getMaxJahr() {
		return maxSize.get("JahrMax");
	}
	public static int getMaxGenre() {
		return maxSize.get("GenreMax");
	}
	public static int getMinErgebnisse() {
		return maxSize.get("ErgebnisseMin");
	}
	public static int getMaxErgebnisse() {
		return maxSize.get("ErgebnisseMax");
	}
}
