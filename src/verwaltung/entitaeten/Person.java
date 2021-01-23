package verwaltung.entitaeten;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Person extends Entitaet{
	
	public StringProperty vorname;
	public StringProperty name;
	public List<String> rolle;   
	
	public Person(int id, String vorname, String name){
		this(id, vorname, name, null);
	}
	
	public Person(int id, String vorname, String name, String rolle){
		super(id);
		this.vorname = new SimpleStringProperty(vorname);
		this.name = new SimpleStringProperty(name);
		this.rolle = new ArrayList<>();
		this.addRolle(rolle);
	}
	
	public String getVorname() {
		return vorname.get();
	}

	public String getName() {
		return name.get();
	}
	
	public StringProperty getVornameProperty() {
		return vorname;
	}
	public StringProperty getNameProperty() {
		return name;
	}

	public void setVorname(String vorname) {
		this.vorname.set(vorname);
	}
	public void setName(String name) {
		this.name.set(name);
	}
	
	
	public void addRolle(String rolle) {
		if(rolle==null)return;
		this.rolle.add(rolle);
	}
	public void removeRolle(String rolle) {
		if(rolle==null)return;
		this.rolle.remove(rolle);
	}
	public void addRollen(List<String> rollen) {
		if(rollen==null)return;
		rollen.forEach(r->rolle.add(r));
	}

	public List<String> getRollen() {
		return new ArrayList<>(this.rolle);
	}
	
	
	
	public Person getCopy() {
		Person p = new Person(getId(),vorname.get(),name.get());
		p.addRollen(getRollen());
		return p;
	}
	
	public List<PersonMitRolle> getPersonenMitRolle(){
		List<PersonMitRolle> list = new ArrayList<>();
		rolle.forEach(r->{
			list.add(new PersonMitRolle(r, this));
		});
		return list;
	}
	
	//Für table Einträge
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
		public void setRolle(String rolle) {
			this.rolle.set(rolle);
		}
		
		public Person getPerson() {
			return per;
		}
	}
	

}
