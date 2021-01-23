package verwaltung.verwaltungen;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import verwaltung.DB_Manager;
import verwaltung.entitaeten.Backup;
import verwaltung.entitaeten.EingabePruefung;
import verwaltung.entitaeten.Id;

public abstract class Verwaltung <T extends Backup & EingabePruefung & Id> extends Stapelverarbeitung<T>{
	

	/** VAR */
	protected Set<T> list; 	
	protected ObservableList<T> observablelist;
	
	protected Verwaltung() {
		list = new HashSet<>();
		observablelist = FXCollections.observableArrayList();
	}
	
	public ObservableList<T> getObList(){
		return observablelist;
	}
	public int size() {
		return list.size();
	}
	public List<T> getList() {
		return new ArrayList<>(list);
	}

	
	
	
	public void addObj(T obj) {
		list.add(obj);
		if(!observablelist.contains(obj)) 	observablelist.add(obj);
	}
	public void removeObj(T obj) {
		list.remove(obj);
		observablelist.remove(obj);
	}
	public void clear() {
		list.clear();
		observablelist.clear();
		super.clear();
	}
		
	
	
	
	@Override
	public boolean addEntitaet(T entitaet) {
		if(list.contains(entitaet))		  return false;
		if(!super.addEntitaet(entitaet))  return false;
		observablelist.add(0, entitaet);
		return true;
	}
	@Override
	public boolean removeEntitaet(T entitaet) {
		if(!super.removeEntitaet(entitaet))		return false;
		observablelist.remove(entitaet);
		return true;
	}
	@Override
	public boolean updateEntitaet(T entitaet) {
		if(!list.contains(entitaet))		return false;
		return super.updateEntitaet(entitaet);
	}
	
	
		
	@Override
	protected void onAdd(T ent, Connection con) throws Exception {
		ent.checkEingaben();
	}
	@Override
	protected void onUpdate(T ent, Connection con) throws Exception  {
		if(!ent.hasBackup())	return;
		ent.checkEingaben();
	}
	@Override
	protected void onDelete(T ent, Connection con) throws Exception  {}

	
	
	
	@Override
	protected void onAddSucess(T ent, Connection con) 	throws SQLException, InterruptedException{
		addObj(ent);
		ent.commitId();
	}
	@Override
	protected void onUpdateSucess(T ent, Connection con) throws SQLException, InterruptedException{
		ent.deleteBackup();
	}
	@Override
	protected void onDeleteSucess(T ent, Connection con) throws SQLException, InterruptedException{
		removeObj(ent);
	}

	
	@Override
	public void reset() {
		add.forEach(observablelist::remove);
		delete.forEach(observablelist::add);
		super.reset();
	}


}
