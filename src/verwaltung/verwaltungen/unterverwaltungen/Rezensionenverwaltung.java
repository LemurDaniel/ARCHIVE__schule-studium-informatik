package verwaltung.verwaltungen.unterverwaltungen;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

import verwaltung.Nutzer;
import verwaltung.entitaeten.Film;
import verwaltung.entitaeten.Rezension;

public class Rezensionenverwaltung extends Unterverwaltung<Rezension> {

	public Rezensionenverwaltung(Film film) {
		super(film);
	}
	
	@Override
	public void load(Connection con) throws SQLException {
		super.load(con);
		String sql = "Select rezension.id, titel, inhalt, name, verfasser, bewertung from rezension "
						+"inner join nutzer on verfasser = nutzer.id "
						+"where filmid ="+film.getId();
		
		try(Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(sql);){
			while(rs.next())
				list.add(new Rezension(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(6)));	
		}
	}

	
	public boolean exists(int rid) {
		return list.stream().anyMatch(r->r.getId()==rid);
	}
	public Rezension getRezensionVonNutzer(int nid) {
		  return list.stream().filter(rz->rz.getVerfasserId()==nid).findFirst().orElse(null);
	}
	
	
	public void addRezension(String titel, String inhalt, int bewertung, int nid) throws Exception {
		if(list.stream().anyMatch(rez->rez.getVerfasserId()==nid))
			throw new Exception("Es existiert bereits eine Rezension mit dieser BenutzerId");

		check(titel, inhalt);
		
		String sql = "Insert into rezension(titel, inhalt, bewertung, verfasser, filmid) values(?, ?, ?, ?, ?); "
					+ "Select SCOPE_IDENTITY()";
		
		try(Connection con = getCon();
				PreparedStatement ps = con.prepareStatement(sql);){
			ps.setString(1, titel);
			ps.setString(2, inhalt);
			ps.setInt(3, bewertung);
			ps.setInt(4, nid);
			ps.setInt(5, film.getId());
			
			try(ResultSet rs = ps.executeQuery();){	
				rs.next();
				list.add(new Rezension(rs.getInt(1), titel, inhalt, Nutzer.getNutzer().getName(), nid, bewertung));
			}
			updateFilmBewertung(con);
		}
	}
	
	public void updateRezension(String titel, String inhalt, int bewertung, int rid) throws Exception {
		Optional<Rezension> opt = list.stream().filter(r->r.getId()==rid).findFirst();
		if(!opt.isPresent())
			throw new Exception("Diese Rezension existiert nicht");
		Rezension rez = opt.get();
		check(titel, inhalt);
			
		String sql = "Update rezension set titel=?, inhalt=?, bewertung=? where id=?;";
	
		try(Connection con = getCon();
				PreparedStatement ps = con.prepareStatement(sql);){
			ps.setString(1, titel);
			ps.setString(2, inhalt);
			ps.setInt(3, bewertung);
			ps.setInt(4, rez.getId());
			ps.executeUpdate();
			
			rez.setTitel(titel);
			rez.setInhalt(inhalt);
			rez.setBewertung(bewertung);
			
			updateFilmBewertung(con);
		}
	}
	
	private void updateFilmBewertung(Connection con) throws SQLException{
		System.out.println("test");
		String sql = "Update film set bewertung = (Select AVG( CAST(bewertung AS decimal(3,1)) ) from rezension where filmid = ?)"
					+ "where film.id = ?; "
					+ "Select film.bewertung from film where film.id=?";
		try(PreparedStatement ps = con.prepareStatement(sql);){
			ps.setInt(1	, film.getId());
			ps.setInt(2, film.getId());
			ps.setInt(3, film.getId());
			try(ResultSet rs = ps.executeQuery()){
				rs.next();
				film.setBewertung(rs.getFloat(1));
			}
		}
	}
	
	public void check(String titel, String inhalt) throws Exception{
		if(titel.length()<10) throw new Exception("Der Titel muss mindestens 10 Zeichen lang sein.");
		//if(inhalt.length()<20) throw new Exception("Der Inhalt muss mindestens 20 Zeichen lang sein.");
	}
	
	public static int getMaxTitel() {
		return maxSize.get("RezTitel");
	}
	public static int getMaxInhalt() {
		return maxSize.get("RezInhalt");
	}
}
