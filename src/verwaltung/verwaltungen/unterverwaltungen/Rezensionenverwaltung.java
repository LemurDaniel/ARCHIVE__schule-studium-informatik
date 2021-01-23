package verwaltung.verwaltungen.unterverwaltungen;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLClientInfoException;
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
				addObj(new Rezension(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(6)));	
		}
	}

	
	public boolean existiert(int rid) {
		return getList().stream().anyMatch(r->r.getId()==rid);
	}
	public Rezension getRezensionVonNutzer(int nid) {
		  return getList().stream().filter(rz->rz.getVerfasserId()==nid).findFirst().orElse(null);
	}
	
	@Override
	protected void add(Rezension rez, Connection con) throws SQLException {
//		if(getList().stream().anyMatch(rez->rez.getVerfasserId()==nid))
//			throw new Exception("Es existiert bereits eine Rezension mit dieser BenutzerId");

		//check(rez.getTitel(), rez.getInhalt());
		
		String sql = "Insert into rezension(titel, inhalt, bewertung, verfasser, filmid) values(?, ?, ?, ?, ?); "
					+ "Select SCOPE_IDENTITY()";
		
		try(PreparedStatement ps = con.prepareStatement(sql)){
			ps.setString(1, rez.getTitel());
			ps.setString(2, rez.getInhalt());
			ps.setInt(3, 	rez.getBewertung());
			ps.setInt(4, 	rez.getVerfasserId());
			ps.setInt(5, 	film.getId());
			
			try(ResultSet rs = ps.executeQuery();){	
				rs.next();
				rez.setId(rs.getInt(1));
			}
		}
		updateFilmBewertung(con);			
		addObj(rez);
	}
	
	@Override
	protected void update(Rezension rez, Connection con) throws SQLException {
		
		String sql = "Update rezension set titel=?, inhalt=?, bewertung=? where id=?;";
	
		try(PreparedStatement ps = con.prepareStatement(sql);){
			ps.setString(1, rez.getTitel());
			ps.setString(2, rez.getInhalt());
			ps.setInt(3, 	rez.getBewertung());
			ps.setInt(4, 	rez.getId());
			ps.executeUpdate();
			updateFilmBewertung(con);
		}
	}
	
	@Override
	protected void delete(Rezension rez, Connection con) {
		throw new UnsupportedOperationException();
	}
	
	private void updateFilmBewertung(Connection con) throws SQLException{

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
		if(titel.length()<maxSize.get("RezTitelMin")) 	throw new Exception("Der Titel muss mindestens "+maxSize.get("RezTitelMin")+" Zeichen lang sein.");
		if(inhalt.length()<maxSize.get("RezInhaltMin"))	throw new Exception("Der Titel muss mindestens "+maxSize.get("RezInhaltMin")+" Zeichen lang sein.");
	}
	
	public static int getMaxTitel() {
		return maxSize.get("RezTitelMax");
	}
	public static int getMaxInhalt() {
		return maxSize.get("RezInhaltMax");
	}

	public static int getMinTitel() {
		return maxSize.get("RezTitelMin");
	}
	public static int getMinInhalt() {
		return maxSize.get("RezInhaltMin");
	}

}
