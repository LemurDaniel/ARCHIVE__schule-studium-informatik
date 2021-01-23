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
import verwaltung.entitaeten.Entitaet;
import verwaltung.entitaeten.Person;
import verwaltung.entitaeten.Person.PersonMitRolle;

public abstract class Verwaltung <T extends Entitaet & Backup> extends DB_Manager{
	

	/** VAR */
	private Stack<T> delete, add, update;
	private List<T> deleteErr, addErr, updateErr;
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
		
		deleteErr = new ArrayList<>();
		addErr = new ArrayList<>();
		updateErr = new ArrayList<>();
	}
	
	public ObservableList<T> getObList(){
		return observablelist;
	}
	public ReadOnlyIntegerProperty getSize() {
		return size.getReadOnlyProperty();
	}
	
	public void addObj(T obj) {
		if(!list.contains(obj))				list.add(obj);
		if(!observablelist.contains(obj))	observablelist.add(obj);
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
	
	
	public void addEntitaet(T entitaet) {
		if(add.contains(entitaet))	return;
		add.add(entitaet);
		observablelist.add(entitaet);
	}
	public void removeEntitaet(T entitaet) {
		if(delete.contains(entitaet))	return;
		delete.add(entitaet);
		observablelist.remove(entitaet);
	}
	public void updateEntitaet(T entitaet) {
		if(entitaet==null  || !list.contains(entitaet))	return;
		update.add(entitaet);
	}
	
	public void save(Connection con) throws SQLException {
		deleteErr.clear();
		addErr.clear();
		updateErr.clear();
		try {
			con.setAutoCommit(false);
			while(!add.empty()) {
				T ent = add.pop();
				try {
					add(ent, con);
					con.commit();
				}catch(SQLException e) {
					System.out.println(e.getMessage());
					addErr.add(ent);
					con.rollback();
				}
			}
			while(!update.empty()) {
				T ent = update.pop();
				try {
					update(ent, con);
					con.commit();
					ent.deleteBackup();
				}catch(SQLException e) {
					ent.reset();
					System.out.println(e.getMessage());
					updateErr.add(ent);
					con.rollback();
				}
			}
			while(!delete.empty()) {
				T ent = delete.pop();
				if(!list.contains(ent)) continue;
				try {
					delete(ent, con);
					con.commit();
				}catch(SQLException e) {
					System.out.println(e.getMessage());
					deleteErr.add(ent);
					con.rollback();
				}
			}
		}finally {
			reset();
		}
	}
	
	public void save(Save<T> save) throws SQLException{
		try {
			save.save(add, delete, update, this);
		}finally {
			reset();
		}
	}
	
	public void reset() {
		add.forEach(	item->	observablelist.remove(item));
		delete.forEach(	item->	observablelist.remove(item));
		update.forEach(	item->	item.reset());

		add.clear();
		delete.clear();
		update.clear();
	}
	
	protected abstract void add(T ent, Connection con)					throws SQLException;
	protected abstract void update(T ent, Connection con)				throws SQLException;
	protected abstract void delete(T ent, Connection con)				throws SQLException;
	
	@FunctionalInterface
	public interface Save<T extends Entitaet & Backup>{
		public void save(List<T> add, List<T> remove, List<T> update, Verwaltung<T> vw) throws SQLException;
	}
}
