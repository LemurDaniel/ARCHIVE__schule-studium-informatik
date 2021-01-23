package verwaltung.entitaeten;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import gui.FensterManager;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;
import javafx.util.StringConverter;
import verwaltung.DB_Manager;
import verwaltung.verwaltungen.Filmverwaltung;
import verwaltung.verwaltungen.Listenverwaltung;
import verwaltung.verwaltungen.Stapelverarbeitung;


public class Liste extends Stapelverarbeitung<Film> implements Backup, EingabePruefung, Id {
	
	private Liste backup;
	private int tempid;
	
	private int id;
	private ReadOnlyStringWrapper name;
	private Filmverwaltung filme;
	
	public Liste(int id, String name) {
		this.id = id;
		this.name = new ReadOnlyStringWrapper(name);
		filme = new Filmverwaltung();
	}
	
	public int getId() {
		return id;
	}
	public String getName() {
		return name.get();
	}
	public ReadOnlyStringProperty getNameProperty() {
		return name.getReadOnlyProperty();
	}
	public ReadOnlyIntegerProperty getSizeProperty() {
		return filme.getSizeProperty();
	}
	public Filmverwaltung getFvw() {
		return filme;
	}
	
	
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name.set(name);
	}
	
	public void addFilme(ResultSet rs) throws SQLException {
		filme.generiereFilme(rs);
	}
	
	public ObservableList<Film> getObList() {
		return filme.getObList();
	}	

	@Override
	public String toString() {
		return name.get();
	}
		
	@Override
	public void setTempId(int id) {
		tempid = id;
	}
	@Override
	public void commitId() {
		id = tempid;
	}
	
	
	@Override
	public void backup() {
		if(backup!=null)	return;
		backup = new Liste(id, name.get());
	}
	@Override
	public void deleteBackup() {
		backup = null;
	}
	@Override
	public boolean hasBackup() {
		return backup!=null;
	}
	@Override
	public void backupReset() {
		if(backup==null)	return;
		
		System.out.println("backup "+backup.id+backup.getName());
		id = backup.getId();
		name.set(backup.getName());
		
		backup = null;
	}
		
	
	@Override
	public void checkEingaben() throws Exception {
		StringBuilder sb = new StringBuilder();
		if(name.get() == null)												sb.append("\n--kein name");
		else{
			if(name.length().intValue() < 	Listenverwaltung.getMinName())	sb.append("\n--t na");
			if(name.length().intValue() >	Listenverwaltung.getMaxName())	sb.append("\n--t maa");
		}

		if(sb.length()>0)
			throw new Exception("Fehler Liste: '"+name.get()+"'"+sb.toString());
	}

	
	@Override
	public boolean addEntitaet(Film film) {
		if(filme.existiert(film) && !delete.contains(film))		return false;
		if(!super.addEntitaet(film))		return false;
		if(film.getId()==-1) 				filme.addEntitaet(film);
		else 								filme.getObList().add(film);
		Listenverwaltung.instance().updateEntitaet(this);
		return true;
	}
	
	//Neu angelegte Filme können nicht mit dragndrop entfernt werden
	// nicht via id, diese ist immer -1 !!
	@Override
	public boolean removeEntitaet(Film film) {
		if(!filme.existiert(film) && !add.contains(film))	return false;
		if(!super.removeEntitaet(film))		return false;
		if(film.getId()==-1)	filme.removeEntitaet(film);
		filme.getObList().remove(film);
		Listenverwaltung.instance().updateEntitaet(this);
		return true;
	}	
	@Override
	public boolean updateEntitaet(Film film) {
		if(!filme.updateEntitaet(film))	return false;
		Listenverwaltung.instance().updateEntitaet(this);
		return true;
	}
	
	
	
	@Override
	protected void onAdd(Film film, Connection con) throws Exception {
		if(!Filmverwaltung.existiertGlobal(film))	throw new Exception("Der Film konnte nicht zur liste hinzugefügt werden");
		
		String sql = "Insert into liste_film(lid, fid) values(?, ?)";
		
		try(PreparedStatement ps = con.prepareStatement(sql)){
			ps.setInt(1, id);
			ps.setInt(2, film.getId());
			ps.executeUpdate();
		}
	}
	@Override
	protected void onDelete(Film film, Connection con) throws Exception {
		
		String sql = "Delete liste_film where lid=? and fid=?";
		
		try(PreparedStatement ps = con.prepareStatement(sql)){
			ps.setInt(1, id);
			ps.setInt(2, film.getId());
			ps.executeUpdate();
		}
		
	}
	
	@Override
	protected void onAddSucess(Film film, Connection con) {
		filme.addObj(film);
		int max = Filmverwaltung.getMaxTitel();
		int max2 = Listenverwaltung.getMaxName();
	//	super.log.add(String.format("'%-"+max+"s' wurde erfolgreich zu Liste '%-"+max2+"s' hinzugefügt", film.getTitel(), name.get()));
		FensterManager.logErreignis(String.format("'%-"+max+"s' wurde erfolgreich zu Liste '%-"+max2+"s' hinzugefügt", film.getTitel(), name.get()));
	}
	@Override
	protected void onDeleteSucess(Film film, Connection con) {
		filme.removeObj(film);
		int max = Filmverwaltung.getMaxTitel();
		int max2 = Listenverwaltung.getMaxName();
		//super.log.add(String.format("'%-"+max+"s' wurde erfolgreich aus Liste '%-"+max2+"s' gelöscht", film.getTitel(), name.get()));
		FensterManager.logErreignis(String.format("'%-"+max+"s' wurde erfolgreich aus Liste '%-"+max2+"s' gelöscht", film.getTitel(), name.get()));
	}
	
	
	@Override
	protected void onUpdate(Film ent, Connection con) throws Exception {}
	@Override
	protected void onUpdateSucess(Film film, Connection con) {}
	
	
	@Override
	public void save(Connection con) throws SQLException{
		filme.save(con);
		super.save(con);
		filme.getLog().forEach(super.log::add);
		filme.getFehlerlog().forEach(super.fehlerlog::add);
	}
	@Override
	public void reset() {
		filme.reset();
		while(!add.empty())		filme.getObList().remove(add.pop());
		while(!delete.empty())	filme.getObList().add(delete.pop());
		super.reset();
	}
	@Override
	public void clear() {
		filme.clear();
		super.clear();
	}
	

	
}
