package verwaltung.entitaeten;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Person extends Entitaet{
	
	public StringProperty vorname;
	public StringProperty name;
	public List<Rolle> rolle;   
	
	public Person(int id, String vorname, String name){
		this(id, vorname, name, null);
	}
	
	public Person(int id, String vorname, String name, Rolle rolle){
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
	
	
	public void addRolle(Rolle rolle) {
		if(rolle==null)return;
		this.rolle.add(rolle);
	}
	public void removeRolle(Rolle rolle) {
		if(rolle==null)return;
		this.rolle.remove(rolle);
	}
	public void addRollen(List<Rolle> rollen) {
		if(rollen==null)return;
		rollen.forEach(r->rolle.add(r));
	}

	public List<Rolle> getRollen() {
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
		private Rolle rolle, initialRolle;
		private boolean initialRolleAbschalten;
		
		public PersonMitRolle(Rolle rolle, Person per) {
			this.per = per;
			this.rolle = rolle;
			this.initialRolle = rolle;
		}
		
		public Rolle getRolle() {
			return rolle;
		}
		public void setRolle(Rolle rolle) {
			this.rolle = rolle;
		}
		
		public Person getPerson() {
			return per;
		}
		public Rolle getinitialRolle() {
			if(!initialRolleAbschalten) return initialRolle;
			else return rolle;
		}
		public void setDavorAbschalten(boolean initialRolleAbschalten) {
			this.initialRolleAbschalten = initialRolleAbschalten;
		}
	}
	

}
