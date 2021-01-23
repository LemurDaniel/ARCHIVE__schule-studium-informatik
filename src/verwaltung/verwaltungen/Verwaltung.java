package verwaltung.verwaltungen;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import verwaltung.DB_Manager;
import verwaltung.entitaeten.Entitaet;

public abstract class Verwaltung <T extends Entitaet> extends DB_Manager{
	

	/** VAR */
	private ObservableList<T> liste;
	private List<T> list; 
	private ReadOnlyIntegerWrapper size;
	
	protected Verwaltung() {
		list = new ArrayList<T>();
		liste = FXCollections.observableArrayList();
		size = new ReadOnlyIntegerWrapper(0);
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
}
