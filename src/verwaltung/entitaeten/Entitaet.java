package verwaltung.entitaeten;

import java.util.Comparator;

public class Entitaet implements Comparator<Genre> {
	
	private int id;
	
	protected Entitaet(int id) {
		this.id = id;
	}

	
	public int getId() {
		return id;
	}
	
	@Override
	public int compare(Genre o1, Genre o2) {
		if(o1.getId()>o2.getId())
			return 1;
		else if(o1.getId()<o2.getId())
			return -1;
		return 0;
	}
}
