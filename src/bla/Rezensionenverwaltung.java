package bla;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.NoSuchElementException;

public class Rezensionenverwaltung extends Verwaltung<Rezension> {
	
	
	private int filmId;
	public Rezensionenverwaltung() {}
	
	public void setFilmId(int filmId) {
		this.filmId = filmId;
	}
	
	public void load() throws Exception {
		list.clear();
		try(Connection con = getCon()){
			ResultSet rs = con.createStatement().executeQuery("Select rid, titel, inhalt, name, verfasser, bewertung from rezensionen "
															+"inner join rezensionen_filme on rid = id "
															+"inner join nutzer on verfasser = nutzer.id "
															+"where fid ="+filmId);
		while(rs.next())list.add(new Rezension(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(6)));	
		}
	}

	public Rezension getRezension(int nid) {
		try {
		  return list.stream().filter(rz->rz.getVerfasserId()==nid).findFirst().get();
		}catch(NoSuchElementException ne) {
			return null;
		}
	}
	public boolean exists(int nid) {
		return list.stream().anyMatch(r->r.getVerfasserId()==Nutzer.getNutzer().getId());
	}
	
	
	public void addRezension(String titel, String inhalt, int bewertung, int nid) throws Exception {
		if(exists(nid)) {
			updateRezension(titel, inhalt, bewertung, getRezension(nid));
			return;
		}
		
		try(Connection con = getCon()) {
			PreparedStatement ps = con.prepareStatement("Insert into rezensionen(titel, inhalt, bewertung, verfasser) values(?, ?, ?, ?); "
														+ "Insert into rezensionen_filme values(?,SCOPE_IDENTITY()); "
														+ "Select rezensionen.id, name from nutzer " 
														+ "join rezensionen on rezensionen.verfasser = nutzer.id");
			ps.setString(1, titel);
			ps.setString(2, inhalt);
			ps.setInt(3, bewertung);
			ps.setInt(4, nid);
			ps.setInt(5, filmId);
			ResultSet rs = ps.executeQuery();
			rs.next();
			list.add(new Rezension(rs.getInt(1), titel, inhalt, rs.getString(2), nid, bewertung));
		}
	}
	
	public void updateRezension(String titel, String inhalt, int bewertung, Rezension r) throws Exception {
		try(Connection con = getCon()) {
			PreparedStatement ps = con.prepareStatement("Update rezensionen set titel=?, inhalt=?, bewertung=? where id="+r.getId());
			ps.setString(1, titel);
			ps.setString(2, inhalt);
			ps.setInt(3, bewertung);
			ps.execute();
		}
	}
}
