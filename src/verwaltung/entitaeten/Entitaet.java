package verwaltung.entitaeten;

import java.util.Comparator;

public class Entitaet implements Comparator<Entitaet> {
	
	private int id;
	
	protected Entitaet(int id) {
		this.id = id;
	}

	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public int compare(Entitaet o1, Entitaet o2) {
		if(o1.getId()>o2.getId())
			return 1;
		else if(o1.getId()<o2.getId())
			return -1;
		return 0;
	}
}
