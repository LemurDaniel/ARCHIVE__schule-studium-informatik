package verwaltung.entitaeten;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableObjectValue;
import javafx.beans.value.ObservableValueBase;

public class Genre extends ObservableValueBase<Genre>{
	private int id;
	private ReadOnlyStringProperty genre;
	private List<Genre> subgenre;
	
	public Genre(int id, String genre) {
		this.id = id;
		this.genre = new SimpleStringProperty(genre);
	}

	public int getId() {
		return id;
	}

	public String getGenre() {
		return genre.get();
	}
	public ReadOnlyStringProperty getGenreProperty() {
		return genre;
	}
	
	public void addSubGenre(Genre subgenre) {
		if(this.subgenre==null)this.subgenre = new ArrayList<>();
		this.subgenre.add(subgenre);
	}
	public List<Genre> getSubGenre() {
		return subgenre;
	}

	@Override
	public Genre getValue() {
		return this;
	}
	
}
