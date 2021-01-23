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
import verwaltung.entitaeten.Entitaet;

public abstract class Verwaltung <T extends Entitaet> extends DB_Manager{
	

	/** VAR */
	private List<T> remove, add;
	private Map<T, T> update;
	private List<T> list; 
	
	private ObservableList<T> liste;
	private ReadOnlyIntegerWrapper size;
	
	protected Verwaltung() {
		list = new ArrayList<T>();
		liste = FXCollections.observableArrayList();
		size = new ReadOnlyIntegerWrapper(0);
		
		remove = new ArrayList<>();
		add = new ArrayList<>();
		update = new HashMap<>();
	}
	
	public ObservableList<T> getObList(){
		return liste;
	}
	public ReadOnlyIntegerProperty getSize() {
		return size.getReadOnlyProperty();
	}
	
	public void addObj(T obj) {
		list.add(obj);
		liste.add(obj);
		size.set(list.size());
	}
	public void removeObj(T obj) {
		list.remove(obj);
		liste.remove(obj);
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
		if(remove.contains(entitaet))	return;
		remove.remove(entitaet);
		liste.remove(entitaet);
	}
	public void updateEntitaet(T alt, T neu) {
		if(alt==null)					alt = list.stream().filter(item->item.getId()==neu.getId()).findFirst().orElse(null);
		else if(!list.contains(alt))	alt=null;
		if(alt == null)	return;
		
		update.put(alt, neu);
	}
	
	public void save(Connection con) throws SQLException {
		for(T ent: add)				add(ent, con);
		for(T ent: update.keySet())	update(ent, update.get(ent), con);
		for(T ent: remove)			delete(ent, con);
		
		add.clear();
		update.clear();
		remove.clear();
		liste.clear();
		list.forEach(item->liste.add(item));
	}
	protected abstract void add(T ent, Connection con)					throws SQLException;
	protected abstract void update(T entAlt, T entNeu, Connection con)	throws SQLException;
	protected abstract void delete(T ent, Connection con)				throws SQLException;
}
