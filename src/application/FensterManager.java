package application;

import bla.Film;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FensterManager {

	private static FensterManager instance;
	public static FensterManager getInstance() {
		if(instance==null) instance = new FensterManager();
		return instance;
	}
	
	private Stage detail;
	private DetailCtrl detailCtrl;
	
	private Stage anmelden;
	private AnmeldeseiteCtrl anmeldenCtrl;
	
	
	public Stage showDetail(Film film) throws Exception {
		if(detail==null) {
			detail = new Stage();
			detail.setTitle("Filmdatenbank - Detailansicht");
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Detail.fxml"));
			loader.load();
    		detailCtrl = loader.getController();
    		detail.setScene(new Scene(loader.getRoot()));
		}
		detailCtrl.setFilm(film);
		if(!detail.isShowing())	detail.show();
		detail.requestFocus();
		return detail;
	}
	
	public Stage showAnmelden() throws Exception {
		if(anmelden==null) {
			anmelden = new Stage();
			anmelden.setTitle("Filmdatenbank - Anmelden");
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Anmelden.fxml"));
			loader.load();
			anmeldenCtrl = loader.getController();
			anmelden.setScene(new Scene(loader.getRoot()));
		}
		if(!anmelden.isShowing())	 anmelden.show();
		anmelden.requestFocus();
		return anmelden;
	}
	
}
