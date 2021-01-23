package gui;

import java.io.IOException;
import java.sql.SQLException;

import gui.controller.AddFilmCtrl;
import gui.controller.AnmeldeseiteCtrl;
import gui.controller.DetailCtrl;
import gui.controller.HauptseiteCtrl;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import verwaltung.DB_Manager;
import verwaltung.Nutzer;
import verwaltung.entitaeten.Film;

public class FensterManager {

	private static FensterManager instance;
	public static FensterManager getInstance() {
		if(instance==null) instance = new FensterManager();
		return instance;
	}
	
	private static Stage primaryStage;
	private static Stage dialog;
	
	/** Primary **/
	private  static Stage anmelden;
	@SuppressWarnings("unused")
	private  static AnmeldeseiteCtrl anmeldenCtrl;	
	
	private  static Stage hauptseite;
	@SuppressWarnings("unused")
	private  static HauptseiteCtrl hauptseiteCtrl;	
	
	
	/** Other **/
	private  static Stage detail;
	private  static DetailCtrl detailCtrl;
	
	private  static Stage addFilm;
	private  static AddFilmCtrl addFilmCtrl;
		
	private  static Stage filter;
	private  static AddFilmCtrl filterCtrl;
	
	static {
		
		Nutzer.getNutzer().angemeldetProperty().addListener((ob, ov, angemeldet)->{
			try {
				if(angemeldet) 
					setPrimaryStage(getHauptSeite());
				else {
					reset();
					setPrimaryStage(getAnmelden());
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		});
	}
	
	public static Stage getDetail(Film film) throws SQLException{
		if(detail==null) {
			detail = new Stage();
			detail.setTitle("Filmdatenbank - Detailansicht");
			detail.setResizable(false);
			FXMLLoader loader = getLoader("fxml/Detail.fxml");
    		detailCtrl = loader.getController();
    		detail.setScene(new Scene(loader.getRoot()));
		}
		detailCtrl.setFilm(film);
		return detail;
	}
	
	public static Stage getAddFilm(Film film) throws SQLException{
		if(film != null) {
			if( !(Nutzer.getNutzer().getRechte().isUpdate() && film.getErstellerId()==Nutzer.getNutzer().getId() || Nutzer.getNutzer().getRechte().isUpdateAll())	)
				throw new SQLException("Keine Berechtigung");
		}else if(film == null) {
			
		}
		
		if(addFilm==null) {
			addFilm = new Stage();
			addFilm.setTitle("Filmdatenbank - Detailansicht");
			addFilm.setResizable(false);
			FXMLLoader loader = getLoader("fxml/AddFilm.fxml");
			addFilmCtrl = loader.getController();
    		addFilm.setScene(new Scene(loader.getRoot()));
		}
		addFilmCtrl.setFilm(film);
		return addFilm;
	}
	
	public static Stage getAnmelden() {
		if(anmelden==null) {
			anmelden = new Stage();
			anmelden.setTitle("Filmdatenbank - Anmelden");
			FXMLLoader loader = getLoader("fxml/Anmeldeseite.fxml");
			anmeldenCtrl = loader.getController();
			anmelden.setScene(new Scene(loader.getRoot()));
		}
		return anmelden;
	}
	
	public static Stage getHauptSeite() {
		if(hauptseite==null) {
			hauptseite = new Stage();
			hauptseite.setTitle("Filmdatenbank");
			FXMLLoader loader = getLoader("fxml/Hauptseite.fxml");
			hauptseiteCtrl = loader.getController();
			hauptseite.setScene(new Scene(loader.getRoot()));
		}
		return hauptseite;
	}
	
	private static FXMLLoader getLoader(String source) {
		FXMLLoader loader = new FXMLLoader(FensterManager.class.getResource(source));
		try {
			loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return loader;
	}
	
	public static Stage getFilter() {
		if(filter==null) {
			filter = new Stage(StageStyle.DECORATED);
			filter.setTitle("Filmdatenbank - Filter");
			FXMLLoader loader = getLoader("fxml/Filter.fxml");
			filter.setScene(new Scene(loader.getRoot()));
		}
		return filter;
	}
	
	public static void setPrimaryStage(Stage stage) {
		if(primaryStage!=null)	primaryStage.close();
		primaryStage = stage;
		primaryStage.requestFocus();
		primaryStage.show();
		
		primaryStage.setOnCloseRequest(ev->{
			ev.consume();
			
			Alert a = new Alert(AlertType.CONFIRMATION);
			a.setTitle("Anwendung Beenden");
			a.setContentText("Möchten sie die Anwendung wirklich beenden");
			a.show();
			a.setOnCloseRequest(ev2->{
				if(a.getResult().getButtonData().equals(ButtonData.OK_DONE)){
					DB_Manager.InstanzAbmelden();
					reset();
					System.exit(0);
				}
			});
		});
	}
	
	public static void setDialog(Stage stage) {
		if(dialog!=null && dialog!=stage)	dialog.close();
		dialog = stage;
		dialog.requestFocus();
		dialog.show();
	}
	
	public static void reset() {
		if(primaryStage!=null)		primaryStage.close();
		if(dialog!=null)			dialog.close();
		detail = null;
		addFilm = null;
	}
}
