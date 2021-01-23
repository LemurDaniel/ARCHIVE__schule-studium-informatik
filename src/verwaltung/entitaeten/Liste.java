package verwaltung.entitaeten;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.collections.ObservableList;
import verwaltung.verwaltungen.Filmverwaltung;
import verwaltung.verwaltungen.Verwaltung;

public class Liste extends Verwaltung<Film> implements Backup{
	
	private int id;
	private String name;
	private Filmverwaltung filme;
	
	public Liste(int id, String name) {
		this.id = id;
		this.name = name;
		filme = new Filmverwaltung();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public void addFilme(ResultSet rs) throws SQLException {
		filme.generiereFilme(rs);
	}
	public void addFilm(Film film) {
		filme.addObj(film);
	}
	public void removeFilm(Film film) {
		filme.removeObj(film);
	}
	public void clear() {
		filme.clear();
	}
	
	public ObservableList<Film> getObList() {
		return filme.getObList();
	}	
	public Filmverwaltung getFvw() {
		return filme;
	}


	@Override
	public void makeBackup() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void deleteBackup() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public boolean hasBackup() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	protected void add(Film ent, Connection con) throws SQLException {
		// TODO Auto-generated method stub
		
	}


	@Override
	protected void update(Film ent, Connection con) throws SQLException {
		// TODO Auto-generated method stub
		
	}


	@Override
	protected void delete(Film ent, Connection con) throws SQLException {
		// TODO Auto-generated method stub
		
	}



}
