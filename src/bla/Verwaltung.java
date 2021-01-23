package bla;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Verwaltung <T> extends DB_Manager{
	

	/** VAR */
	//protected ObservableList<T> list = FXCollections.observableArrayList();
	protected List<T> list; 
	private ObservableList<T> liste;
	
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
