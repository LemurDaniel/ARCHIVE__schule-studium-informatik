package verwaltung.entitaeten;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Person {
	
	@SuppressWarnings("unused")
	private int id;
	public StringProperty vorname;
	public StringProperty name;
	public StringProperty rolle;
	
	public Person(int id, String vorname, String name, String rolle){
		this.id = id;
		this.vorname = new SimpleStringProperty(vorname);
		this.name = new SimpleStringProperty(name);
		this.rolle = new SimpleStringProperty(rolle);
	}
}
