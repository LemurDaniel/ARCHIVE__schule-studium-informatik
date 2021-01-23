package bla;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Verwaltung <T> extends DB_Manager{
	

	/** VAR */
	protected ObservableList<T> list = FXCollections.observableArrayList();
	
	protected Verwaltung(){}
	public ObservableList<T> getList(){
		return FXCollections.unmodifiableObservableList(list);
	}
	
}
