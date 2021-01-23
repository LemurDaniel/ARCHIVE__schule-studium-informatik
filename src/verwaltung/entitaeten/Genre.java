package verwaltung.entitaeten;


public class Genre extends Entitaet {
	
	private String genre, text;

	public Genre(int id, String genre, String text) {
		super(id);
		this.genre = genre;
		this.text = text;
	}

	public String getGenre() {
		return genre;
	}
	
	public String getText() {
		return text;
	}

	
	
}
