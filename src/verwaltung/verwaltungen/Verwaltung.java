package verwaltung.verwaltungen;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import verwaltung.DB_Manager;
import verwaltung.entitaeten.Entitaet;

public abstract class Verwaltung <T extends Entitaet> extends DB_Manager{
	

	/** VAR */
	private ObservableList<T> liste;
	private List<T> list; 
	
	@SuppressWarnings("serial")
	protected Verwaltung() {
			list = new ArrayList<T>();
			liste = FXCollections.observableArrayList(list);
	}
	
	public ObservableList<T> getObList(){
		//return FXCollections.unmodifiableObservableList(list);
		return liste;
	}
	
	public void addObj(T obj) {
		list.add(obj);
		liste.add(obj);
	}
	public void removeObj(T obj) {
		list.remove(obj);
		liste.remove(obj);
	}
	public void clear() {
		list.clear();
		liste.clear();
	}
	public List<T> getList() {
		return new ArrayList<>(list);
	}
}
