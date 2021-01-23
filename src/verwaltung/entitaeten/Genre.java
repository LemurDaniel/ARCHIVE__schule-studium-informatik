package verwaltung.entitaeten;


public class Genre { 
	
	private static Genre kurzfilm;
	
	private int id;
	private String genre, text;

	public Genre(int id, String genre, String text) {
		this.id = id;
		this.genre = genre;
		this.text = text;
		if(genre.toLowerCase().equals("kurzfilm")) kurzfilm = this;
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

	public static Genre getKurzfilm() {
		return kurzfilm;
	}
}
