package verwaltung.verwaltungen.unterverwaltungen;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import verwaltung.Nutzer;
import verwaltung.entitaeten.Film;
import verwaltung.entitaeten.Liste;
import verwaltung.verwaltungen.Verwaltung;

public class Listenverwaltung extends Verwaltung<Liste>{
	
	private static Listenverwaltung instance;
	public static Listenverwaltung instance() {
		if(instance==null)instance = new Listenverwaltung();
		return instance;
	}
	private Listenverwaltung() {};
		
	public void listen(Connection con) throws SQLException {	
		String sql = "Select id, name from liste where besitzer=? ";	
		
		try(PreparedStatement ps = con.prepareStatement(sql)){
			ps.setInt(1, Nutzer.getNutzer().getId());				
			try(ResultSet rs = ps.executeQuery()){
				while(rs.next())	
					addObj(new Liste(rs.getInt(1), rs.getString(2)));
			}
		}
		fuelleListen(con);		
	}
		
	public void fuelleListen(Connection con) throws SQLException {
		String	sql = "Select * from film "
				+ "join film_genre on film_genre.fid=film.id "
				+ "join liste_film on liste_film.fid=film.id "
				+ "where lid=?";

		for(Liste li:getList()) {
			try(PreparedStatement ps = con.prepareStatement(sql)){
				ps.setInt(1, li.getId());
				try(ResultSet rs = ps.executeQuery()){
					li.clear();
					li.addFilme(rs);
				}
			}
		}
	}
	
	@Override
	protected void add(Liste ent, Connection con) throws SQLException {
		String sql = "Insert liste(name, besitzer) values( ?, ?); Select SCOPE_IDENTITY();";
		
		try(PreparedStatement ps = con.prepareStatement(sql)){
			ps.setString(1, ent.getName()				);
			ps.setInt(2, 	Nutzer.getNutzer().getId()	);
			try(ResultSet rs = ps.executeQuery()){
				rs.next();
				ent.setId(rs.getInt(1));
				addObj(ent);
			}			
		}
	}
	@Override
	protected void update(Liste li, Connection con) throws SQLException {
		
		String sql = "Update liste set name=? where lid=?";
		
		try(PreparedStatement ps = con.prepareStatement(sql)){
			ps.setString(1, li.getName()	);
			ps.setInt(2, 	li.getId()		);
			ps.executeUpdate();
		}
	}
	@Override
	protected void delete(Liste ent, Connection con) throws SQLException {
		con.setAutoCommit(false);
		entferneFilmeVonListe(ent, ent.getList(), con);
		
		String sql = "Delete liste_film where lid=?; Delete liste where lid=?";
		
		try(PreparedStatement ps = con.prepareStatement(sql)){
				ps.setInt(1, ent.getId());
				ps.setInt(2, ent.getId());
				ps.executeUpdate();
		}
		con.commit();
		con.setAutoCommit(true);
		removeObj(ent);	
	}
	
	public void filmeZuListenHinzufuegen(Map<Liste, List<Film>> li_filme, Connection con)  throws SQLException {
		for(Liste li: li_filme.keySet())
			filmeZuListeHinzufuegen(li, li_filme.get(li), con);
	}
	public void entferneFilmeVonListen(Map<Liste, List<Film>> li_filme, Connection con)  throws SQLException {
		for(Liste li: li_filme.keySet())
			entferneFilmeVonListe(li, li_filme.get(li), con);
	}
	
	public void filmeZuListeHinzufuegen(Liste li, List<Film> filme, Connection con)  throws SQLException {
		
		StringBuilder sb = new StringBuilder();
		sb.append("Insert into liste_film(fid, lid) values ");
		for(int i=0; i<filme.size(); i++)	sb.append(  "( ?, ?)"+(i==filme.size()-1? " ":", ") );
			
		try(PreparedStatement ps = con.prepareStatement(sb.toString())){
			int count=0;
			for(Film f: filme) {
				ps.setInt(++count, f.getId());
				ps.setInt(++count, li.getId());
			}
			ps.executeUpdate();
		}
		filme.forEach(f->li.addFilm(f));
	}
	
	private void entferneFilmeVonListe(Liste li, List<Film> filme, Connection con)  throws SQLException {	
		
		StringBuilder sb = new StringBuilder();
		sb.append("Delete liste_film where lid=? and fid in( ");
		for(int i=0; i<filme.size(); i++)	sb.append(  "?"+(i==filme.size()-1? ") ":", ") );
			
		try(PreparedStatement ps = con.prepareStatement(sb.toString())){
			int count=0;
			ps.setInt(++count, li.getId());
			for(Film f: filme) ps.setInt(++count, f.getId());
			ps.executeUpdate();
		}
		filme.forEach(f->li.removeFilm(f));
	}

	
	public void listeEntfernen(Liste li, Connection con) throws SQLException {		
		String sql = "Delete liste_film where lid=?; Delete liste where lid=?";
		
		try(PreparedStatement ps = con.prepareStatement(sql)){
				ps.setInt(1, li.getId());
				ps.setInt(2, li.getId());
				ps.executeUpdate();
			}
		removeObj(li);
		}
	
}