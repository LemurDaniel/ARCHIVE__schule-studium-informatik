package verwaltung;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public abstract class Verwaltung <T> extends DB_Manager{
	

	/** VAR */
	//protected ObservableList<T> list = FXCollections.observableArrayList();
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
