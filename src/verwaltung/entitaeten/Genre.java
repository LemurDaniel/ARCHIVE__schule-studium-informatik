package verwaltung.entitaeten;

import java.util.Comparator;

public class Genre implements Comparator<Genre> {
	
	private int id;
	private String genre, text;

	public Genre(int id, String genre, String text) {
		this.id = id;
		this.genre = genre;
		this.text = text;
	}

	public String getGenre() {
		return genre;
	}
	
	public String getText() {
		return text;
	}

	public int getId() {
		return id;
	}

	@Override
	public int compare(Genre o1, Genre o2) {
		return o1.genre.compareTo(o2.genre);
	}

}
