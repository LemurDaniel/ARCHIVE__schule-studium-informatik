package verwaltung.entitaeten;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.util.StringConverter;
import verwaltung.DB_Manager;
import verwaltung.verwaltungen.Filmverwaltung;
import verwaltung.verwaltungen.Stapelverarbeitung;
import verwaltung.verwaltungen.unterverwaltungen.Listenverwaltung;


public class Liste extends Stapelverarbeitung<Film> implements Backup, EingabePruefung {
	
	private Liste backup;
	
	private int id;
	private StringProperty name;
	private Filmverwaltung filme;
	
	public Liste(int id, String name) {
		this.id = id;
		this.name = new SimpleStringProperty(name);
		filme = new Filmverwaltung();
	}
	
	public int getId() {
		return id;
	}
	public String getName() {
		return name.get();
	}
	public StringProperty getNameProperty() {
		return name;
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
		System.out.println(filme.existiert(film));
		if(filme.existiert(film) && !delete.contains(film))		return false;
//			if(delete.contains(film))	{
//				delete.remove(film);
//				filme.getObList().add(film);
//				
//				System.out.println("sssssssssssss");
//				System.out.println("add----------add");
//				add.forEach(a->System.out.println(a));
//				System.out.println("delete----------");
//				delete.forEach(a->System.out.println(a));
//			}
//			return false;
//		}
		if(!super.addEntitaet(film))		return false;
		if(film.getId()==-1) 				filme.addEntitaet(film);
		else 								filme.getObList().add(film);
		return true;
	}
	
	@Override
	public boolean removeEntitaet(Film film) {
		if(!filme.existiert(film) && !add.contains(film))		return false;
		if(!super.removeEntitaet(film))		return false;
		filme.getObList().remove(film);
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
	protected void onAddSucess(Film film) {
		filme.addObj(film);
	}
	@Override
	protected void onDeleteSucess(Film film) {
		filme.removeObj(film);
	}
	
	
	
	@Override
	public boolean updateEntitaet(Film film) {
		filme.addEntitaet(film);
		return true;
	}
	@Override
	protected void onUpdate(Film ent, Connection con) throws Exception {}
	@Override
	protected void onUpdateSucess(Film film) {}
	
	
	@Override
	public void save(Connection con) throws SQLException{
		filme.save(con);
		super.save(con);
	}
	@Override
	public void reset() {
		filme.reset();
		while(!add.empty())		filme.getObList().remove(add.pop());
		while(!delete.empty())	filme.getObList().add(delete.pop());
		super.reset();
	}
	
}
