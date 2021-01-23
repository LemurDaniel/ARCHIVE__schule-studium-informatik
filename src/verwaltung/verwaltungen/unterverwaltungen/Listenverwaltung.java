package verwaltung.verwaltungen.unterverwaltungen;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import verwaltung.Nutzer;
import verwaltung.entitaeten.Film;
import verwaltung.entitaeten.Liste;
import verwaltung.verwaltungen.Filmverwaltung;
import verwaltung.verwaltungen.Verwaltung;

public class Listenverwaltung extends Verwaltung<Liste>{
	
	private static Listenverwaltung instance;
	public static Listenverwaltung instance() {
		if(instance==null)instance = new Listenverwaltung();
		return instance;
	}
	private Listenverwaltung() {};
	
	
	public void listen(Connection connect) throws SQLException {	
		try(Connection con = connect!=null? connect:getCon()){
			String sql = "Select id, name from liste where besitzer=? ";
			try(PreparedStatement ps = con.prepareStatement(sql)){
				ps.setInt(1, Nutzer.getNutzer().getId());
				try(ResultSet rs = ps.executeQuery()){
					while(rs.next())	list.add(new Liste(rs.getInt(1), rs.getString(2)));
				}
			}
			
			
			sql = "Select * from film "
					+ "join film_genre on film_genre.fid=film.id "
					+ "join liste_film on liste_film.fid=film.id "
					+ "where lid=?";
			for(Liste li:list) {
				try(PreparedStatement ps = con.prepareStatement(sql)){
					ps.setInt(1, li.getId());
					try(ResultSet rs = ps.executeQuery()){
						li.addFilme(rs);
					}
				}
			}
		}
	}
	
	public void addFilmeToListen(Map<Liste, List<Film>> li_filme, Connection con)  throws SQLException {
		for(Liste li: li_filme.keySet())
			addFilmeToListe(li, li_filme.get(li), con);
	}
	public void removeFilmeVonListen(Map<Liste, List<Film>> li_filme, Connection con)  throws SQLException {
		for(Liste li: li_filme.keySet())
			removeFilmeVonListe(li, li_filme.get(li), con);
	}
	
	public void addFilmeToListe(Liste li, List<Film> filme, Connection connect)  throws SQLException {
		
		try(Connection con = connect!=null? connect:getCon()){
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
	}
	
	public void removeFilmeVonListe(Liste li, List<Film> filme, Connection connect)  throws SQLException {	
		try(Connection con = connect!=null? connect:getCon()){
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
	}
	
	public void listenUmbennene(Map<Liste, String> li_name, Connection con) throws SQLException {
		for(Liste li: li_name.keySet()) {
			listeUmbenennen(li, li_name.get(li), con);
		}
	}
	
	public void listeUmbenennen(Liste li, String name, Connection connect) throws SQLException {
		try(Connection con = connect!=null? connect:getCon()){
			String sql = "Update liste set name=? where lid=?";
			try(PreparedStatement ps = con.prepareStatement(sql)){
				ps.setString(1, name);
				ps.setInt(2, li.getId());
				ps.executeUpdate();
			}
		li.setName(name);
		}
	}

	
}