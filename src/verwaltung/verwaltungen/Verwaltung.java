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
	protected List<T> list; 
	
	@SuppressWarnings("serial")
	protected Verwaltung() {
			list = new ArrayList<T>() {
				@Override
				public boolean add(T obj) {
					super.add(obj);
					liste.add(obj);
					return true;
				}
				
				@Override
				public void clear() {
					super.clear();
					liste.clear();
				}
			};
			liste = FXCollections.observableArrayList(list);
	}
	
	public ObservableList<T> getList(){
		//return FXCollections.unmodifiableObservableList(list);
		return liste;
	}

	
}
