package verwaltung.verwaltungen.unterverwaltungen;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

import gui.FensterManager;
import verwaltung.DB_Manager;
import verwaltung.entitaeten.Film;
import verwaltung.entitaeten.Rezension;

public class Rezensionenverwaltung extends Unterverwaltung<Rezension> {

	public Rezensionenverwaltung(Film film) {
		super(film);
	}
	
	@Override
	public void lade(Connection con) throws SQLException {
		super.lade(con);
		String sql = "Select titel, inhalt, name, verfasser, bewertung from rezension "
						+"inner join nutzer on verfasser = nutzer.id "
						+"where filmid ="+film.getId();
		
		try(Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(sql);){
			while(rs.next())
				addObj(new Rezension(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), film));	
		}
		
		try(Statement st = con.createStatement()){
			try(ResultSet rs = st.executeQuery("Select bewertung from film where id="+film.getId())){
				rs.next();
				film.setBewertung(rs.getFloat(1));
			}
		}
	}
	public Optional<Rezension> getRezensionVonNutzer(int nid) {
		  return super.list.stream().filter(rz->rz.getVerfasserId()==nid).findFirst();
	}
	
	
	@Override
	protected void onAdd(Rezension rez, Connection con) throws Exception {
		super.onAdd(rez, con);
		
		String sql = "Insert into rezension(titel, inhalt, bewertung, verfasser, filmid) values(?, ?, ?, ?, ?); ";
		
		try(PreparedStatement ps = con.prepareStatement(sql)){
			ps.setString(1, rez.getTitel());
			ps.setString(2, rez.getInhalt());
			ps.setInt(3, 	rez.getBewertung());
			ps.setInt(4, 	rez.getVerfasserId());
			ps.setInt(5, 	film.getId());
			ps.executeUpdate();
		}
	}
	
	@Override
	protected void onUpdate(Rezension rez, Connection con) throws Exception {
		super.onUpdate(rez, con);
		
		String sql = "Update rezension set titel=?, inhalt=?, bewertung=? where verfasser=? and filmid=?;";
		
		try(PreparedStatement ps = con.prepareStatement(sql);){
			ps.setString(1, rez.getTitel());
			ps.setString(2, rez.getInhalt());
			ps.setInt(3, 	rez.getBewertung());
			ps.setInt(4, 	rez.getVerfasserId());
			ps.setInt(5, 	film.getId());
			ps.executeUpdate();
		}
	}
	
	@Override
	protected void onDelete(Rezension rez, Connection con) throws Exception {
		super.onAdd(rez, con);
		
		String sql = "Delete rezension where verfasser=? and filmid=?";
		
		try(PreparedStatement ps = con.prepareStatement(sql)){
			ps.setInt(1, 	rez.getVerfasserId());
			ps.setInt(2, 	film.getId());
			ps.executeUpdate();
		}
	}
	
	@Override
	protected void onAddSucess(Rezension rez, Connection con) 		throws SQLException, InterruptedException{
		super.onAddSucess(rez, con);
		FensterManager.logErreignis(String.format("\nDie Rezension '%s' mit %d Sternen wurde erfolgreich zum Film '%s' hinzugefügt", rez.getTitel(), rez.getBewertung(), film.getTitel()));
		lade(con);
		FensterManager.logErreignis(String.format("Die Filmbewertung wurde auf %s geändert", film.getBwtStringProperty().get()));
		// Filmbewertung wird über Datenbank Trigger angepasst.
	}
	@Override
	protected void onUpdateSucess(Rezension rez, Connection con) 	throws SQLException, InterruptedException{
		super.onUpdateSucess(rez, con);
		FensterManager.logErreignis(String.format("\nDie Rezension '%s' zum Film '%s' wurde erfolgreich auf ´%d Sterne geändert", rez.getTitel(), film.getTitel(), rez.getBewertung()));
		lade(con);
		FensterManager.logErreignis(String.format("Die Filmbewertung wurde auf %s geändert", film.getBwtStringProperty().get()));
	}
	
	
	public static int getMaxTitel() {
		return DB_Manager.get("RezTitelMax");
	}
	public static int getMaxInhalt() {
		return DB_Manager.get("RezInhaltMax");
	}

	public static int getMinTitel() {
		return DB_Manager.get("RezTitelMin");
	}
	public static int getMinInhalt() {
		return DB_Manager.get("RezInhaltMin");
	}

}
