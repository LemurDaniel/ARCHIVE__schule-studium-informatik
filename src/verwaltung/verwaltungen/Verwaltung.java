package verwaltung.verwaltungen;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import verwaltung.DB_Manager;
import verwaltung.entitaeten.Backup;
import verwaltung.entitaeten.EingabePruefung;
import verwaltung.entitaeten.Entitaet;
import verwaltung.entitaeten.Person;
import verwaltung.entitaeten.Person.PersonMitRolle;

public abstract class Verwaltung <T extends Backup & EingabePruefung> extends DB_Manager{
	

	/** VAR */
	private Stack<T> delete, add, update;
	private List<T> deleteErr, addErr, updateErr;
	
	private List<Exception>	fehlerlog;
	
	private List<T> list; 
	
	private ObservableList<T> observablelist;
	private ReadOnlyIntegerWrapper size;
	
	protected Verwaltung() {
		list = new ArrayList<T>();
		observablelist = FXCollections.observableArrayList();
		size = new ReadOnlyIntegerWrapper(0);
		
		delete = new Stack<>();
		add = new Stack<>();
		update = new  Stack<>();
		
		deleteErr	= new ArrayList<>();
		addErr		= new ArrayList<>();
		updateErr	= new ArrayList<>();
		fehlerlog   = new ArrayList<>();
	}
	
	public ObservableList<T> getObList(){
		return observablelist;
	}
	public ReadOnlyIntegerProperty getSize() {
		return size.getReadOnlyProperty();
	}
	
	public void addObj(T obj) {
		if(!list.contains(obj))				list.add(obj);
		if(!observablelist.contains(obj)) 	observablelist.add(obj);
		size.set(list.size());
	}
	public void removeObj(T obj) {
		list.remove(obj);
		observablelist.remove(obj);
		size.set(list.size());
	}
	public void clear() {
		list.clear();
		observablelist.clear();
		size.set(list.size());
	}
	public List<T> getList() {
		return new ArrayList<>(list);
	}
	public List<Exception> getFehlerlog(){
		return fehlerlog;
	}
	
	
	public void addEntitaet(T entitaet) {
		if(add.contains(entitaet))	return;
		add.push(entitaet);
		
		//Verschiebt alle Element, sodass dass neu Element ganz oben steht
		T temp = null;
		if(observablelist.size()>0) {
			for(int i=0;i<observablelist.size(); i++) {
				temp = observablelist.get(i);
				observablelist.set(i, entitaet);
				entitaet = temp;
			}
		}
		observablelist.add(entitaet);
	}
	public void removeEntitaet(T entitaet) {
		if(delete.contains(entitaet))	return;
		delete.push(entitaet);
		observablelist.remove(entitaet);
	}
	public void updateEntitaet(T entitaet) {
		if(entitaet==null  || !list.contains(entitaet))	return;
		update.push(entitaet);
	}
	
	
	public boolean hatAuftraege() {
		return add.size()!=0 || update.size()!=0 || delete.size()!=0;
	}
	
	
	
	
	public void save(Connection con) throws SQLException {
		if(!hatAuftraege())		return;
		
		fehlerlog.clear();
		
		try {
			con.setAutoCommit(false);
			while(!add.empty()) onAdd(add.pop(), con);
			while(!update.empty()) {
				T ent = update.pop();
				if(!ent.hasBackup())	continue;
				onUpdate(ent, con);
			}
			while(!delete.empty()) {
				T ent = update.pop();
				if(!list.contains(ent))	continue;
				onDelete(ent, con);
			}
		}finally {
			deleteErr.forEach(delete::push);
			updateErr.forEach(update::push);
			addErr.forEach(add::push);
			
			deleteErr.clear();
			updateErr.clear();
			addErr.clear();
		}
	}
	
	protected void onAdd(T ent, Connection con) throws SQLException {
		try {
			ent.checkEingaben();
			add(ent, con);
			con.commit();
			addObj(ent);
		}catch (SQLException e1) {
			con.rollback();
			addErr.add(ent);
			fehlerlog.add(e1);
		}catch(Exception e) {
			e.printStackTrace();
			addErr.add(ent);
			fehlerlog.add(e);
		}
	}
	protected void onUpdate(T ent, Connection con) throws SQLException  {
		try {
			ent.checkEingaben();
			update(ent, con);
			con.commit();
			ent.deleteBackup();
		}catch (SQLException e1) {
			con.rollback();
			updateErr.add(ent);
			fehlerlog.add(e1);
		}catch(Exception e) {
			updateErr.add(ent);
			fehlerlog.add(e);
		}
	}
	protected void onDelete(T ent, Connection con) throws SQLException  {
		try {
			delete(ent, con);
			con.commit();
		}catch (SQLException e1) {
			con.rollback();
			deleteErr.add(ent);
			fehlerlog.add(e1);
		}catch(Exception e) {
			deleteErr.add(ent);
			fehlerlog.add(e);
		}
	}
	
	

	
	public void reset() {
		update.forEach(	item->	item.reset());
		add.clear();
		delete.clear();
		update.clear();
		
		observablelist.clear();
		list.forEach(observablelist::add);	
	}
	
	protected abstract void add(T ent, Connection con)					throws SQLException;
	protected abstract void update(T ent, Connection con)				throws SQLException;
	protected abstract void delete(T ent, Connection con)				throws SQLException;
	

}
