package bla;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Optional;

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

	public boolean rezensionExists(int nid) {
		return list.stream().anyMatch(rz->rz.getVerfasserId()==nid);
	}	
	public Optional<Rezension> getRezension(int nid) {
		return list.stream().filter(rz->rz.getVerfasserId()==nid).findFirst();
	}
}
