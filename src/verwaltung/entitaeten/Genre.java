package verwaltung.entitaeten;

import java.util.ArrayList;
import java.util.List;

public class Genre {
	private int id;
	private String genre;
	private List<Genre> subgenre;
	
	public Genre(int id, String genre) {
		this.id = id;
		this.genre = genre;
	}

	public int getId() {
		return id;
	}

	public String getGenre() {
		return genre;
	}
	
	public void addSubGenre(Genre subgenre) {
		if(this.subgenre==null)this.subgenre = new ArrayList<>();
		this.subgenre.add(subgenre);
	}
	public List<Genre> getSubGenre() {
		return subgenre;
	}
	
}
