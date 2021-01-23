package verwaltung.entitaeten;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Person implements Backup, EingabePruefung{
	
	private Person backup;
	
	public int id;
	public StringProperty vorname;
	public StringProperty name;
	private List<PersonMitRolle> pmrlist;
	
	public Person(int id, String vorname, String name){
		this(id, vorname, name, null);
	}
	
	public Person(int id, String vorname, String name, Rolle rolle){
		this.id = id;
		this.vorname = new SimpleStringProperty(vorname);
		this.name = new SimpleStringProperty(name);
		pmrlist = new ArrayList<>();
		this.addRolle(rolle);
	}
	
	public int getId() {
		return id;
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

	public void setId(int id) {
		this.id = id;
	}
	public void setVorname(String vorname) {
		this.vorname.set(vorname);
	}
	public void setName(String name) {
		this.name.set(name);
	}
	
	
	public void addRolle(Rolle rolle) {
		if(rolle==null)	return;
		pmrlist.add(new PersonMitRolle(rolle, this));
	}
	
	public void removePMR(PersonMitRolle pmr) {
		if(pmr==null)return;
		pmrlist.remove(pmr);
		pmr.per = null;
	}	
	public void addPMR(PersonMitRolle pmr) {
		if(pmr==null || existiert(pmr.getRolle()))	return;
		pmrlist.add(pmr);
		pmr.per = this;
	}
	public boolean existiert(Rolle rolle) {
		return pmrlist.stream().anyMatch(pmr->pmr.getRolle()==rolle);
	}
	
	public List<PersonMitRolle> getPersonenMitRolle(){
		return new ArrayList<>(pmrlist);
	}
	public int size() {
		return pmrlist.size();
	}
	
	
	
	@Override
	public void backup() {
		if(backup!=null)	return;
		
		backup = new Person(getId(), vorname.get(), name.get());
		backup.pmrlist = new ArrayList<>(pmrlist);
		backup.pmrlist.forEach(pmr->pmr.backup());
	}
	@Override
	public void backupReset() {
		if(backup==null)	return;
		
		id = backup.id;
		vorname.set(backup.getVorname());
		name.set(backup.getName());
		pmrlist = backup.pmrlist;
		pmrlist.forEach(pmr->pmr.backupReset());
		
		backup = null;
	}
	@Override
	public void deleteBackup() {
		backup = null;	
	}
	@Override
	public boolean hasBackup() {
		return backup!=null;
	}
	
	
	
	
	
	@Override
	public String toString() {
		return "Person [vorname=" + vorname + ", name=" + name + ", getId()=" + getId() + "]";
	}






	//Für table Einträge
	public class PersonMitRolle implements Backup{
		
		private PersonMitRolle backup;
		
		private Person per;
		private Rolle rolle, initialRolle;

		private BooleanProperty update, delete;
		
		public PersonMitRolle(Rolle rolle, Person per) {
			this.per = per;
			this.rolle = rolle;
			this.initialRolle = rolle;
			update = new SimpleBooleanProperty(false);
			delete = new SimpleBooleanProperty(false);
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
			return initialRolle;
		}
		public void resetInitialRolle() {
			initialRolle = rolle;
		}
	
		public BooleanProperty getDeleteProperty() {
			return delete;
		}
		public BooleanProperty getUpdateProperty() {
			return update;
		}

		
		@Override
		public void backup() {
			if(backup!=null)	return;
			
			backup = new PersonMitRolle(rolle, per);
			backup.initialRolle = initialRolle;
			backup.delete.set(delete.get());
			backup.update.set(update.get());
		}
		@Override
		public void backupReset() {
			if(backup==null)	return;
			
			rolle = backup.rolle;
			initialRolle = backup.initialRolle;
			per = backup.per;
			delete.set(backup.delete.get());
			update.set(backup.update.get());
			
			backup = null;
		}
		@Override
		public void deleteBackup() {
			backup = null;
		}
		@Override
		public boolean hasBackup() {
			return backup!=null;
		}

	
	}





	@Override
	public void checkEingaben() throws Exception {

	}

}
