package verwaltung.entitaeten;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import gui.FensterManager;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.ObservableList;
import verwaltung.Stapelverarbeitung;
import verwaltung.entitaeten.interfaces.Backup;
import verwaltung.entitaeten.interfaces.EingabePruefung;
import verwaltung.entitaeten.interfaces.Id;
import verwaltung.verwaltungen.Filmverwaltung;
import verwaltung.verwaltungen.Listenverwaltung;


public class Liste extends Stapelverarbeitung<Film> implements Backup, EingabePruefung, Id {
	
	private Liste backup;
	private int tempid;
	
	private int id;
	private ReadOnlyStringWrapper name, groeﬂe;
	private Filmverwaltung filme;

	
	public Liste(int id, String name) {
		this.id = id;
		this.name = new ReadOnlyStringWrapper(name);
		filme = new Filmverwaltung();
		groeﬂe = new ReadOnlyStringWrapper("0");
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
	public ReadOnlyStringProperty getGroeﬂeProperty() {
		return groeﬂe.getReadOnlyProperty();
	}

	
	
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name.set(name);
	}
	
	public void addFilme(ResultSet rs) throws SQLException {
		filme.generiereFilme(rs);
		aktualisiereGroeﬂe();
	}
	
	public ObservableList<Film> getObList() {
		return filme.getObList();
	}	

	
	
	public void aktualisiereGroeﬂe() {
		String s = String.format("%-5d", filme.size());
		if(!super.add.empty() && !super.delete.empty())	s = s+String.format(" (+%s / -%s)" , super.add.size(), super.delete.size());
		else if(!super.delete.empty())					s = s+String.format(" (-%s)" , super.delete.size());
		else if(!super.add.empty())						s = s+String.format(" (+%s)" , super.add.size());
		groeﬂe.set(s);	
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
		
		id = backup.getId();
		name.set(backup.getName());
		
		backup = null;
	}
		
	
	
	
	
	
	@Override
	public void checkEingaben() throws Exception {
		StringBuilder sb = new StringBuilder();
		if(name.get() == null)												sb.append("\n  Die Liste besitzt keinen Name");
		else{
			if(name.length().intValue() < 	Listenverwaltung.getMinName())	sb.append("\n  Der Name der Liste ist zu kurz min."+Listenverwaltung.getMinName());
			if(name.length().intValue() >	Listenverwaltung.getMaxName())	sb.append("\n--t maa");
		}

		if(sb.length()>0)
			throw new Exception("Fehler Liste: '"+name.get()+"'"+sb.toString());
	}

	
	
	
	
	
	
	public void addEntitaeten(List<Film> filmListe) {
		filmListe.stream().filter(film->!filme.getObList().contains(film) && super.addEntitaet(film)).forEach(film->{
				if(film.getId()==-1) 				filme.addEntitaet(film);
				else 								filme.getObList().add(film);
		});
		Listenverwaltung.instance().updateEntitaet(this);
		aktualisiereGroeﬂe();
	}
	public void removeEntitaeten(List<Film> filmListe) {
		filmListe.stream().filter(film->filme.getObList().contains(film) && super.removeEntitaet(film)).forEach(filme.getObList()::remove);
		Listenverwaltung.instance().updateEntitaet(this);
		aktualisiereGroeﬂe();
	}
	
	
	@Override
	public boolean addEntitaet(Film film) {
		if(filme.getObList().contains(film) && !delete.contains(film))		return false;
		if(!super.addEntitaet(film))		return false;
		if(film.getId()==-1) 				filme.addEntitaet(film);
		else 								filme.getObList().add(film);
		Listenverwaltung.instance().updateEntitaet(this);
		aktualisiereGroeﬂe();
		return true;
	}
	
	
	//Neu angelegte Filme kˆnnen nicht mit dragndrop entfernt werden
	// nicht via id, diese ist immer -1 !!
	@Override
	public boolean removeEntitaet(Film film) {
		if(!filme.getObList().contains(film) && !add.contains(film))	return false;
		if(!super.removeEntitaet(film))		return false;
		filme.getObList().remove(film);
		Listenverwaltung.instance().updateEntitaet(this);
		aktualisiereGroeﬂe();
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
		if(!Filmverwaltung.existiertGlobal(film))	throw new Exception("Der Film '"+film.getTitel()+"' konnte nicht zur liste hinzugef¸gt werden");
		
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
		aktualisiereGroeﬂe();
		FensterManager.logErreignis(String.format("'%s' wurde erfolgreich zu Liste '%s' hinzugef¸gt", film.getTitel(), name.get()));
	}
	@Override
	protected void onDeleteSucess(Film film, Connection con) {
		filme.removeObj(film);
		aktualisiereGroeﬂe();
		FensterManager.logErreignis(String.format("'%s' wurde erfolgreich aus Liste '%s' gelˆscht", film.getTitel(), name.get()));
	}
	
	
	@Override
	protected void onUpdate(Film ent, Connection con) throws Exception {}
	@Override
	protected void onUpdateSucess(Film film, Connection con) {}
	
	
	
	
	@Override
	public void save(Connection con) throws SQLException, InterruptedException{
		FensterManager.logErreignis(String.format("Der Inhalt der Liste '%s' wird aktualisiert", name.get()));
		filme.save(con);
		super.save(con);
		FensterManager.logErreignis(String.format("Aktualisierung der Liste '%s' wurde beendet", name.get()));
	}
	@Override
	public void reset() {
		filme.reset();
		while(!add.empty())		filme.getObList().remove(add.pop());
		while(!delete.empty())	filme.getObList().add(delete.pop());
		aktualisiereGroeﬂe();
		super.reset();
	}
	@Override
	public void clear() {
		filme.clear();
		super.clear();
	}
	
	
	@Override
	public boolean hatAuftraege() {
		return super.hatAuftraege() || filme.hatAuftraege();
	}

	
}
