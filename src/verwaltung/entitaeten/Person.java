package verwaltung.entitaeten;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Person {
	
	private int id;
	public StringProperty vorname;
	public StringProperty name;
	public List<String> rolle;   
	
	public Person(int id, String vorname, String name){
		this(id, vorname, name, null);
	}
	
	public Person(int id, String vorname, String name, String rolle){
		this.id = id;
		this.vorname = new SimpleStringProperty(vorname);
		this.name = new SimpleStringProperty(name);
		this.rolle = new ArrayList<>();
		this.addRolle(rolle);
	}
	
//	public Person(int id, String vorname, String name, List<String> rolle){
//		this.id = id;
//		this.vorname = new SimpleStringProperty(vorname);
//		this.name = new SimpleStringProperty(name);
//		this.rolle = rolle;
//	}
	
	public String getVorname() {
		return vorname.get();
	}

	public String getName() {
		return name.get();
	}
	public int getId() {
		return id;
	}
	
	public StringProperty getVornameProperty() {
		return vorname;
	}
	public StringProperty getNameProperty() {
		return name;
	}
	
	public void addRolle(String rolle) {
		if(rolle==null)return;
		this.rolle.add(rolle);
	}
	public void removeRolle(String rolle) {
		this.rolle.remove(rolle);
	}
	public void addRollen(List<String> rollen) {
		if(rollen==null)return;
		rollen.forEach(r->rolle.add(r));
	}
	
	public Person getCopy() {
		Person p = new Person(id,vorname.get(),name.get());
		p.addRollen(rolle);
		return p;
	}

	public List<String> getRollen() {
		return rolle;
	}
	
	public List<PersonMitRolle> getPersonenMitRolle(){
		List<PersonMitRolle> l = new ArrayList<>();
		rolle.forEach(r->{
			l.add(new PersonMitRolle(r, this));
		});
		return l;
	}
	
	public class PersonMitRolle {
		private Person per;
		private StringProperty rolle;
		
		public PersonMitRolle(String r, Person per) {
			this.per = per;
			rolle = new SimpleStringProperty(r);
		}
		
		public StringProperty getRolle() {
			return rolle;
		}
		
		public Person getPerson() {
			return per;
		}
	}
	

}
