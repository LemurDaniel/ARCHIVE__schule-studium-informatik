package verwaltung.entitaeten;


public class Genre {
	
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


}
