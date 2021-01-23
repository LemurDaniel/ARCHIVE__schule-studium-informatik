package verwaltung.entitaeten;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import verwaltung.verwaltungen.Filmverwaltung;

public class Liste extends Entitaet{
	
	private String name;
	private Filmverwaltung filme;
	
	public Liste(int id, String name) {
		super(id);
		this.name = name;
		filme = new Filmverwaltung();
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public void addFilme(ResultSet rs) throws SQLException {
		filme.generateFilm(rs);
	}
	public void addFilm(Film film) {
		filme.addObj(film);
	}
	public void removeFilm(Film film) {
		filme.removeObj(film);
	}
	
	public ObservableList<Film> getObList() {
		return filme.getObList();
	}
	public List<Film> getList() {
		return filme.getList();
	}


}
