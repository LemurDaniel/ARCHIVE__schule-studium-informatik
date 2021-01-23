package verwaltung.verwaltungen;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
import verwaltung.entitaeten.Liste;

public class Listenverwaltung extends Verwaltung<Liste>{
	
	private static Listenverwaltung instance;
	public static Listenverwaltung instance() {
		if(instance==null)instance = new Listenverwaltung();
		return instance;
	}

		
	public void ladeListen(Connection con) throws SQLException {	

		String sql = "Select id, name from liste where besitzer=? ";	
		
		try(PreparedStatement ps = con.prepareStatement(sql)){
			ps.setInt(1, Nutzer.getNutzer().getId());				
			try(ResultSet rs = ps.executeQuery()){
				while(rs.next()) {
					Liste li = new Liste(rs.getInt(1), rs.getString(2));
					fuelleListe(li, con);
					addObj(li);	
					FensterManager.logErreignis("Liste '"+li.getName()+"' erfolgreich geladen", Color.GREEN);
				}
			}
		}	
		FensterManager.logErreignis("Es wurden insgesamt "+getObList().size()+ (getObList().size()==1?" Liste":" Listen")+" geladen", Color.GREEN);
	}
		
	private void fuelleListe(Liste li, Connection con) throws SQLException {
		
		String	sql = "Select * from film "
				+ "join genre_film on genre_film.fid=film.id "
				+ "join liste_film on liste_film.fid=film.id "
				+ "where lid=? order by film.id ";
//		
		try(PreparedStatement ps = con.prepareStatement(sql)){
			ps.setInt(1, li.getId());
			try(ResultSet rs = ps.executeQuery()){
				li.addFilme(rs);
			}
		}
	}
	
	
	
	@Override
	protected void onAdd(Liste li, Connection con) throws Exception {
		super.onAdd(li, con);
		
		String sql = "Insert liste(name, besitzer) values( ?, ?); Select SCOPE_IDENTITY();";
		
		try(PreparedStatement ps = con.prepareStatement(sql)){
			ps.setString(1, li.getName()				);
			ps.setInt(2, 	Nutzer.getNutzer().getId()	);
			try(ResultSet rs = ps.executeQuery()){
				rs.next();
				li.setTempId(rs.getInt(1));
			}			
		}
	}	
	@Override
	protected void onUpdate(Liste li, Connection con) throws Exception {
		super.onUpdate(li, con);
		
		String sql = "Update liste set name=? where id=?";
		
		try(PreparedStatement ps = con.prepareStatement(sql)){
			ps.setString(1, li.getName()	);
			ps.setInt(2, 	li.getId()		);
			ps.executeUpdate();
		}
	}
	@Override
	protected void onDelete(Liste li, Connection con) throws Exception {
		super.onDelete(li, con);
		
		String sql = "Delete liste_film where lid=?; Delete liste where id=?";
		
		try(PreparedStatement ps = con.prepareStatement(sql)){
			ps.setInt(1, li.getId());
			ps.setInt(2, li.getId());
			ps.executeUpdate();
		}
	}
	
	
	@Override
	protected void onAddSucess(Liste li, Connection con) throws SQLException, InterruptedException{
		super.onAddSucess(li, con);
		FensterManager.logErreignis(String.format("Die Liste '%s' wurde erfolgreich erstellt", li.getName()));
		if(li.hatAuftraege())	super.updateEntitaet(li);
	}
	@Override
	protected void onUpdateSucess(Liste li, Connection con) throws SQLException, InterruptedException{
		if(li.hasBackup())	FensterManager.logErreignis(String.format("Der name der Liste '%s' wurde erfolgreich geändert", li.getName()));
		super.onUpdateSucess(li, con);
		if(!li.hatAuftraege())	return;
		FensterManager.logErreignis(String.format("Der Inhalt der Liste '%s' wird aktualisiert", li.getName()));
		li.save(con);
		FensterManager.logErreignis(String.format("Aktualisierung der Liste '%s' wurde beendet", li.getName()));
	}
	@Override
	protected void onDeleteSucess(Liste li, Connection con) throws SQLException, InterruptedException{
		super.onDeleteSucess(li, con);
		li.clear();
		FensterManager.logErreignis(String.format("Die Liste '%s' wurde erfolgreich gelöscht", li.getName()));
	}
	
	
	@Override
	public void save(Connection con) throws SQLException, InterruptedException{
		try{
			super.save(con);
		}catch(Exception e) {
			super.getObList().filtered(Liste::hatAuftraege).forEach(super::updateEntitaet);
			throw e;
		}
	}
	
	
	
	public static int getMaxName() {
		return DB_Manager.get("ListeNameMax");
	}
	public static int getMinName() {
		return DB_Manager.get("ListeNameMin");
	}
}