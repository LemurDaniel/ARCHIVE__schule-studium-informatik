package Verwaltungen.verwaltungen;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.NoSuchElementException;

import Verwaltungen.Unterverwaltung;
import Verwaltungen.entitaeten.Film;
import Verwaltungen.entitaeten.Rezension;

public class Rezensionenverwaltung extends Unterverwaltung<Rezension> {

	public Rezensionenverwaltung(Film film) {
		super(film);
	}
	
	@Override
	public void load() throws SQLException {
		super.load();
		try(Connection con = getCon()){
			ResultSet rs = con.createStatement().executeQuery("Select rezensionen.id, titel, inhalt, name, verfasser, bewertung from rezensionen "
															+"inner join nutzer on verfasser = nutzer.id "
															+"where filmid ="+film.getId());
		while(rs.next())list.add(new Rezension(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(6)));	
		}
	}


	public boolean exists(int rid) {
		return list.stream().anyMatch(r->r.getId()==rid);
	}
	public Rezension getRezensionVonNutzer(int nid) {
		try {
		  return list.stream().filter(rz->rz.getVerfasserId()==nid).findFirst().get();
		}catch(NoSuchElementException ne) {
			return null;
		}
	}
	
	
	public void addRezension(String titel, String inhalt, int bewertung, int nid) throws Exception {

		check(titel, inhalt);
		try(Connection con = getCon()) {
			PreparedStatement ps = con.prepareStatement("Insert into rezensionen(titel, inhalt, bewertung, verfasser, filmid) values(?, ?, ?, ?, ?); "
														+"Update filme set bewertung = (Select AVG( CAST(bewertung AS decimal(3,1)) ) from rezensionen where filmid = ?) "
							  							+"where filme.id = ?;"
														+ "Select SCOPE_IDENTITY(), name, filme.bewertung from nutzer " 
														+ "join rezensionen on verfasser = nutzer.id "
														+ "join filme on filmid = filme.id "
														+ "where rezensionen.id = SCOPE_IDENTITY();");
			ps.setString(1, titel);
			ps.setString(2, inhalt);
			ps.setInt(3, bewertung);
			ps.setInt(4, nid);
			ps.setInt(5, film.getId());
			ps.setInt(6, film.getId());
			ps.setInt(7, film.getId());
			ResultSet rs = ps.executeQuery();
			rs.next();
			
			list.add(new Rezension(rs.getInt(1), titel, inhalt, rs.getString(2), nid, bewertung));
			film.setBewertung(rs.getFloat(3));
		}
	}
	
	public void updateRezension(String titel, String inhalt, int bewertung, int rid) throws Exception {
		Rezension rez = list.stream().filter(r->r.getId()==rid).findFirst().get();
		
		check(titel, inhalt);
		try(Connection con = getCon()) {
			PreparedStatement ps = con.prepareStatement("Update rezensionen set titel=?, inhalt=?, bewertung=? where id=?;"
														+"Update filme set bewertung = (Select AVG( CAST(bewertung AS decimal(3,1)) ) from rezensionen where filmid = ?) "
														+"where filme.id = ?; "
														+"Select filme.bewertung from filme where filme.id=?");
			ps.setString(1, titel);
			ps.setString(2, inhalt);
			ps.setInt(3, bewertung);
			ps.setInt(4, rez.getId());
			ps.setInt(5, film.getId());
			ps.setInt(6, film.getId());
			ps.setInt(7, film.getId());
			ResultSet rs = ps.executeQuery();
			rs.next();
			
			rez.setTitel(titel);
			rez.setInhalt(inhalt);
			rez.setBewertung(bewertung);
			film.setBewertung(rs.getFloat(1));
		}
	}
	
	public void check(String titel, String inhalt) throws Exception{
		if(titel.length()<10) throw new Exception("Der Titel muss mindestens 10 Zeichen lang sein.");
		if(inhalt.length()<20) throw new Exception("Der Inhalt muss mindestens 20 Zeichen lang sein.");
	}
	
}
