package bla;

public class Unterverwaltung<T> extends Verwaltung<T>{
	
	private boolean isLoaded;
	protected Film film;
	
	public Unterverwaltung(Film film) {
		this.film = film;	
	}
	
	protected void load() throws Exception {
		isLoaded = true;
	}
	public void loadIfnotLoaded() throws Exception {
		if(!isLoaded) load();
	}
	protected void clear() {
		isLoaded = false;
	}
	public boolean isLoaded() {
		return isLoaded;
	}
}
