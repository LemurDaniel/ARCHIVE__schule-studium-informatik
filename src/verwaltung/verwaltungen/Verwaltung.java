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
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import verwaltung.DB_Manager;
import verwaltung.entitaeten.Backup;
import verwaltung.entitaeten.EingabePruefung;

public abstract class Verwaltung <T extends Backup & EingabePruefung> extends Stapelverarbeitung<T>{
	

	/** VAR */
	private Set<T> list; 	
	private ObservableList<T> observablelist;
	private ReadOnlyIntegerWrapper size;
	
	protected Verwaltung() {
		list = new HashSet<>();
		observablelist = FXCollections.observableArrayList();
		size = new ReadOnlyIntegerWrapper(0);
		observablelist.addListener( (ListChangeListener<T>)change-> size.set(observablelist.size() ));
	}
	
	public ObservableList<T> getObList(){
		return observablelist;
	}
	public ReadOnlyIntegerProperty getSizeProperty() {
		return size.getReadOnlyProperty();
	}
	public boolean existiert(T entitaet) {
		return list.contains(entitaet);
	}

	public void addObj(T obj) {
		list.add(obj);
	//	if(!observablelist.contains(obj)) 	observablelist.add(obj);
	}
	public void removeObj(T obj) {
		list.remove(obj);
	//	observablelist.remove(obj);
	}
	public void clear() {
		list.clear();
		observablelist.clear();
		reset();
	}
		
	public List<T> getList() {
		return new ArrayList<>(list);
	}
	public List<Exception> getFehlerlog(){
		return fehlerlog;
	}
	
	public boolean addEntitaet(T entitaet) {
		System.out.println("Performance Test");
		long nano = System.nanoTime();
		long mili = System.currentTimeMillis();
//		System.out.println(String.format("nano: %,d", nano));
//		System.out.println(String.format("milli: %,d", mili));
		boolean t = addEntitaetTest(entitaet);
		nano = System.nanoTime()-nano;
		mili = System.currentTimeMillis()-mili;
		System.out.println(String.format("nano: %,d", nano));
		System.out.println(String.format("milli: %,d", mili));
		return t;
	}
	
	//@Override
	public boolean addEntitaetTest(T entitaet) {
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
		add(ent, con);
	}
	@Override
	protected void onUpdate(T ent, Connection con) throws Exception  {
		if(!ent.hasBackup())	return;
		ent.checkEingaben();
		update(ent, con);
	}
	@Override
	protected void onDelete(T ent, Connection con) throws Exception  {
		delete(ent, con);
	}
	
	
	@Override
	protected void onAddSucess(T ent) {
		addObj(ent);
	}
	@Override
	protected void onUpdateSucess(T ent) {
		ent.deleteBackup();
	}
	@Override
	protected void onDeleteSucess(T ent) {
		removeObj(ent);
	}

	
	@Override
	public void reset() {
		add.forEach(observablelist::remove);
		delete.forEach(observablelist::add);
		super.reset();
	}
	
	protected abstract void add(T ent, Connection con)					throws SQLException;
	protected abstract void update(T ent, Connection con)				throws SQLException;
	protected abstract void delete(T ent, Connection con)				throws SQLException;
	

}
