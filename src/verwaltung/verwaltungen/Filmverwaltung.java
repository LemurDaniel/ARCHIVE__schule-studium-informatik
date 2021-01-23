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

import gui.FensterManager;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.Dragboard;
import javafx.scene.paint.Color;
import verwaltung.DB_Manager;
import verwaltung.Nutzer;
import verwaltung.entitaeten.Film;
import verwaltung.entitaeten.Genre;

public class Filmverwaltung extends Verwaltung<Film>{
	
	public static final DataFormat dfFilm = new DataFormat("filme_ids");
	
	private static Map<Integer, Genre> genreMap;
	public static void ladeGerne(Connection con) throws SQLException {
		genreMap = new HashMap<>();
		try(Statement st = con.createStatement();
				ResultSet rs = st.executeQuery("Select id, genre, text from genre")){
			while(rs.next()) 
				genreMap.put(rs.getInt(1), new Genre(rs.getInt(1), rs.getString(2), rs.getString(3)));
		}
	}
	public static List<Genre> getGenres(){
		return new ArrayList<>(genreMap.values());
	}
	
	
	/** **/
	private static Map<Integer, Film> geladeneFilme = new HashMap<>();
	private static Map<Film, Integer> referenziert = new HashMap<>();
	private static List<Filmverwaltung> fvws = new ArrayList<>();
	
	public Filmverwaltung() {
		fvws.add(this);
	}
	
	
	public void generiereFilme(ResultSet rs) throws SQLException {	
		long milli = System.currentTimeMillis();
		
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
			current.addGenre( genreMap.get(rs.getInt("gid")) );	
		}
		
		System.out.println(System.currentTimeMillis()-milli+"milli");
	}
	
	public void test() throws SQLException{
		filter(null, 10f, 0f, null, null, null, null, null, true, null, 10);	
	}
	
	@Override
	protected void onAdd(Film f, Connection con) throws Exception {
		super.onAdd(f, con);
		
		int id;	
		String sql = "Insert into film(titel, dauer, erscheinungsjahr, bewertung, ersteller) values(?, ?, ?, ?, ?); Select SCOPE_IDENTITY()";
		
		try(PreparedStatement ps = con.prepareStatement(sql)){			
			
			ps.setString(	1, 	f.getTitel()				);
			ps.setInt(		2, 	f.getDauer()				);
			ps.setInt(		3,	f.getErscheinungsjahr()		);
			ps.setFloat(	4, 	0							);
			ps.setInt(		5,  Nutzer.getNutzer().getId()	);
			
			try(ResultSet rs = ps.executeQuery()) {
				rs.next();
				id = rs.getInt(1);
				f.setTempId(id);
			}
		}		
		updateGenres(con, f.getGenres(), id);
	}
	
	@Override
	protected void onUpdate(Film f, Connection con) throws Exception {
		super.onUpdate(f, con);
		
		String sql = "Update film set titel=?, dauer=?, erscheinungsjahr=? where id=?;";
		
		try(PreparedStatement ps = con.prepareStatement(sql)){
			
			con.setAutoCommit(false);
			ps.setString(	1, 	f.getTitel()				);
			ps.setInt(		2, 	f.getDauer()				);
			ps.setInt(		3,	f.getErscheinungsjahr()		);
			ps.setInt(		4,	f.getId()					);
			ps.executeUpdate();

			updateGenres(con, f.getGenres(), f.getId());
		}
	
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
				}
			}			
		}
	}
	
	
	@Override
	protected void onAddSucess(Film f, Connection con) throws SQLException, InterruptedException{
		super.onAddSucess(f, con);
	//	super.log.add(String.format("'%-"+getMaxTitel()+"s' wurde erfolgreich hinzugefügt", f.getTitel()));
		FensterManager.logErreignis(String.format("Der Film '%s' wurde erfolgreich hinzugefügt", f.getTitel()));	
		updatePvw(f, con);
	}
	@Override
	protected void onUpdateSucess(Film f, Connection con) throws SQLException, InterruptedException{
		if(f.hasBackup()) FensterManager.logErreignis(String.format("Der Film '%s' wurde erfolgreich geändert", f.getTitel()));
		super.onUpdateSucess(f, con);
		updatePvw(f, con);
	}

	private void updatePvw(Film f, Connection con) throws SQLException, InterruptedException{
		if(!f.getPvw().hatAuftraege()) return;
		FensterManager.logErreignis(String.format("Die Mitwirkenden zum Film '%s' werden aktualisiert", f.getTitel()));
		f.getPvw().save(con);
		FensterManager.logErreignis(String.format("Die Aktualisierung der Mitwirkenden zum Film '%s' wurde abgeschlossen", f.getTitel()));
	}
		
	@Override
	public void save(Connection con) throws SQLException, InterruptedException {
		super.save(con);
		getObList().filtered(f->f.getPvw().hatAuftraege()).forEach(super::updateEntitaet);
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
		
		try(Connection con = DB_Manager.getCon()){
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
				for(String s:tags)	ps.setString(++count, "%"+s+"%");
				try(ResultSet rs = ps.executeQuery()){
					clear();
					generiereFilme(rs);
				}
			}
		}
		if(getObList().size()>0)FensterManager.logErreignis("\nEs wurden "+getObList().size()+" Filmeinträge mit entsprechenden Kriterien gefunden", Color.GREEN);
		else FensterManager.logErreignis("\nEs existieren keine Filmeinträge mit entsprechenden Kriterien", Color.RED);
	}
	
	
	@Override
	public void addObj(Film f) {
		if(super.list.contains(f)) return;
		super.addObj(f);
		if(!referenziert.containsKey(f)) {
			geladeneFilme.put(f.getId(), f);
			referenziert.put(f, 1);
		}else {
			int i = referenziert.get(f)+1;
			referenziert.put(f, i);
		}
	}
	@Override
	public void removeObj(Film f) {
		if(!super.list.contains(f))	return;
		super.removeObj(f);
		if(!referenziert.containsKey(f))	return;
		int i = referenziert.get(f)-1;
		if(i==0) {
			geladeneFilme.remove(f.getId());
			referenziert.remove(f);
		}else
			referenziert.put(f, i);
	}
	@Override
	public void clear() {
		getList().forEach(this::removeObj);;
//		referenziert.forEach((k,v)->System.out.println(k+"  "+v));
//		geladeneFilme.forEach((k,v)->System.out.println(k+"  "+v));
	}
	
	
	public static void clearAll() {
		geladeneFilme.clear();
		referenziert.clear();
		fvws.forEach(fvw->fvw.clear());
	}
	
	public static void refreshAll() throws SQLException {
		if(geladeneFilme.size()==0)	return;
		
		StringBuilder sb = new StringBuilder();
		sb.append("Select * from Film join genre_film on fid=id where id in ( ");
		
		int count = 0, size = geladeneFilme.size();
		for(Integer id: geladeneFilme.keySet())		sb.append( id+ (++count==size ? ") ":", ") );
	
		sb.append("order by id");
		
		System.out.println(sb.toString());
		try(Connection con = DB_Manager.getCon();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sb.toString())){
			
			Film current = new Film(-1, 0, "", 0, 0, 0);
			int idNow;
			while(rs.next()) {
				idNow = rs.getInt("id");
				if(current.getId()!=idNow) 	current = geladeneFilme.get(idNow).aktualisiere(rs.getString("titel"), rs.getInt("dauer"), rs.getInt("erscheinungsjahr"), rs.getFloat("bewertung"));
				current.addGenre( genreMap.get(rs.getInt("gid")) );	
			}
		}
		//fvws.forEach(fvw->fvw.reset());
		FensterManager.logErreignis("\nEs wurden "+geladeneFilme.size()+" Filme aktualisiert", Color.GREEN);
	}	
	
	
	public static void kopiereInDragbord(Dragboard db, List<Film> filme) {
   	 	List<Integer> ids = new ArrayList<>();
   	 	filme.forEach(film->ids.add(film.getId()));
   	 	ClipboardContent content = new ClipboardContent();
   	 	content.put(dfFilm, ids);
   	 	db.setContent(content);
	}
	public static List<Film> kopiereAusDragbord(Dragboard db) {		
   	 	@SuppressWarnings("unchecked")
		List<Integer> ids = (List<Integer>) db.getContent(dfFilm);
   	 	List<Film> filme = new ArrayList<>();
   	 	ids.forEach(id->filme.add(geladeneFilme.get(id)));
   	 	return filme;
	}
	
	
	
	
	public static boolean existiertGlobal(Film f) {
		return geladeneFilme.get(f.getId())==f;
	}	
	public static Film getFilmById(int id) {
		return geladeneFilme.get(id);
	}
	
	
	public static int getMinTitel() {
		return DB_Manager.get("FilmTitelMin");
	}
	public static int getMaxTitel() {
		return DB_Manager.get("FilmTitelMax");
	}
	public static int getMinDauer() {
		return DB_Manager.get("FilmDauerMin");
	}
	public static int getMaxDauer() {
		return DB_Manager.get("FilmDauerMax");
	}
	public static int getMinJahr() {
		return DB_Manager.get("JahrMin");
	}
	public static int getMaxJahr() {
		return DB_Manager.get("JahrMax");
	}
	public static int getMinGenre() {
		return DB_Manager.get("GenreMin");
	}
	public static int getMaxGenre() {
		return DB_Manager.get("GenreMax");
	}
	public static int getMinErgebnisse() {
		return DB_Manager.get("ErgebnisseMin");
	}
	public static int getMaxErgebnisse() {
		return DB_Manager.get("ErgebnisseMax");
	}


}
