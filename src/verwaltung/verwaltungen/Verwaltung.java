package verwaltung.verwaltungen;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import verwaltung.DB_Manager;
import verwaltung.entitaeten.Backup;
import verwaltung.entitaeten.Entitaet;

public abstract class Verwaltung <T extends Entitaet & Backup> extends DB_Manager{
	

	/** VAR */
	private List<T> delete, add, update;
	private List<T> list; 
	
	private ObservableList<T> liste;
	private ReadOnlyIntegerWrapper size;
	
	protected Verwaltung() {
		list = new ArrayList<T>();
		liste = FXCollections.observableArrayList();
		size = new ReadOnlyIntegerWrapper(0);
		
		delete = new ArrayList<>();
		add = new ArrayList<>();
		update = new ArrayList<>();
	}
	
	public ObservableList<T> getObList(){
		return liste;
	}
	public ReadOnlyIntegerProperty getSize() {
		return size.getReadOnlyProperty();
	}
	
	public void addObj(T obj) {
		list.add(obj);
		if(!liste.contains(obj))	liste.add(obj);
		size.set(list.size());
	}
	public void removeObj(T obj) {
		list.remove(obj);
		if(liste.contains(obj))		liste.remove(obj);
		size.set(list.size());
	}
	public void clear() {
		list.clear();
		liste.clear();
		size.set(list.size());
	}
	public List<T> getList() {
		return new ArrayList<>(list);
	}
	
	
	public void addEntitaet(T entitaet) {
		if(add.contains(entitaet))	return;
		add.add(entitaet);
		liste.add(entitaet);
	}
	public void removeEntitaet(T entitaet) {
		if(delete.contains(entitaet) || !liste.contains(entitaet))	return;
		delete.remove(entitaet);
		liste.remove(entitaet);
	}
	public void updateEntitaet(T entitaet) {
		if(entitaet==null  || !list.contains(entitaet))	return;
		update.add(entitaet);
	}
	
	public void save(Connection con) throws SQLException {
		try {
			for(T ent: add)	{
				try {
					add(ent, con);
				}catch(SQLException e) {
					System.out.println(e.getMessage());
				}
			}
			for(T ent: update) {
				try {
					update(ent, con);
					ent.deleteBackup();
				}catch(SQLException e) {
					ent.reset();
					System.out.println(e.getMessage());
				}
			}
			for(T ent: delete) {
				try {
					delete(ent, con);
				}catch(SQLException e) {
					System.out.println(e.getMessage());
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
		add.clear();
		delete.clear();
		if(update.size()>0) {
			update.forEach(up->up.reset());
		}
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
