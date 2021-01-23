package verwaltung.entitaeten;

import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import verwaltung.entitaeten.interfaces.Backup;
import verwaltung.entitaeten.interfaces.EingabePruefung;
import verwaltung.entitaeten.interfaces.Id;
import verwaltung.verwaltungen.unterverwaltungen.Personenverwaltung;

public class Person implements Backup, EingabePruefung, Id{
	
	private Person backup;
	private int tempid;
	
	private int id;
	private ReadOnlyStringWrapper vorname, name, weiteres;
	private Rolle rolle;
	private boolean nameChanged;
	
//	public Person(int id, String vorname, String name,  String weiteres){
//		this(id, vorname, name, null);
//	}
	
	public Person(int id, String vorname, String name, String weiteres, Rolle rolle){
		this.id = id;
		this.vorname = new ReadOnlyStringWrapper(vorname);
		this.name = new ReadOnlyStringWrapper(name);
		this.weiteres = new ReadOnlyStringWrapper(weiteres);
		this.rolle = rolle;
		nameChanged = false;
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
	public String getWeiteres() {
		return weiteres.get();
	}
	public Rolle getRolle() {
		return rolle;
	}
	
	public ReadOnlyStringProperty getVornameProperty() {
		return vorname.getReadOnlyProperty();
	}
	public ReadOnlyStringProperty getNameProperty() {
		return name.getReadOnlyProperty();
	}
	public ReadOnlyStringProperty getWeiteresProperty() {
		return weiteres.getReadOnlyProperty();
	}
	
	public boolean isNameChanged() {
		return nameChanged;
	}

	
	
	public void setId(int id) {
		this.id = id;
	}
	public void setVorname(String vorname) {
		this.vorname.set(vorname.trim());
		nameChanged = true;
	}
	public void setName(String name) {
		this.name.set(name.trim());
		nameChanged = true;
	}
	public void setWeiteres(String weiteres) {
		if(weiteres.length()==0) weiteres = null;
		this.weiteres.set(weiteres);
	}
	public void setRolle(Rolle rolle) {
		this.rolle = rolle;
	}
	public void setNameChanged(boolean nameChanged) {
		this.nameChanged = nameChanged;
	}
	
	
	@Override
	public void setTempId(int id) {
		tempid = id;
	}
	@Override
	public void commitId() {
		id = tempid;
	}
	
	
	
	@Override
	public void backup() {
		if(backup!=null)	return;
		backup = new Person(getId(), vorname.get(), name.get(), weiteres.get(), rolle);
	}
	@Override
	public void backupReset() {
		if(backup==null)	return;
		
		id = backup.id;
		vorname.set(backup.getVorname());
		name.set(backup.getName());
		weiteres.set(backup.weiteres.get());
		rolle = backup.rolle;
		
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
	
	public Person getBackup() {
		return backup;
	}
	
	
	
	@Override
	public String toString() {
		return vorname.get()+" "+name.get();
	}



	@Override
	public void checkEingaben() throws Exception {
		StringBuilder sb = new StringBuilder();
		if(name.length().intValue() > Personenverwaltung.getMaxName())				sb.append("\n--jahr Titel");
		if(name.length().intValue() < Personenverwaltung.getMinName())				sb.append("\n  Der Name '"+name.get()+"' ist zu kurz min."+Personenverwaltung.getMinName());
		if(vorname.length().intValue() > Personenverwaltung.getMaxVorname())		sb.append("\n--jahr Titel");
		if(vorname.length().intValue() < Personenverwaltung.getMinVorname())	 	sb.append("\n  Der Vorname '"+vorname.get()+"' ist zu kurz min."+Personenverwaltung.getMinVorname());
		if(sb.length()>0)	throw new Exception("Fehler Person: '"+this+"' "+sb.toString());
	}

}
