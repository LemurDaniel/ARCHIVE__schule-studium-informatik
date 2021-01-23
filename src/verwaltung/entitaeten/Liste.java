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
	private List<Film> list;
	
	public Liste(int id, String name) {
		super(id);
		this.name = name;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public void addFilme(ResultSet rs) throws SQLException {
		Filmverwaltung.generateFilm(rs, list);
	}
	public void addFilm(Film film) {
		list.add(film);
	}
	public void removeFilm(Film film) {
		list.remove(film);
	}
	
	public ObservableList<Film> getFilme() {
		return FXCollections.observableArrayList(list);
	}
	
	public void bla() {
		System.out.println("name");
		list.forEach(a->System.out.println(a.toString()));
	}
}
