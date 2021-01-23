package gui;

import java.io.IOException;
import java.sql.SQLException;

import gui.controller.AddFilmCtrl;
import gui.controller.AnmeldeseiteCtrl;
import gui.controller.DetailCtrl;
import gui.controller.FilterCtrl;
import gui.controller.HauptseiteCtrl;
import gui.controller.ListensichtCtrl;
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
import verwaltung.entitaeten.Liste;
import verwaltung.verwaltungen.Filmverwaltung;
import verwaltung.verwaltungen.Stapelverarbeitung;

public class FensterManager {

	private FensterManager() {};
	
	private static Stage primaryStage;
	private static Stage secondary;
	private static Stage dialog;
	
	/** Stages **/
	private  static Stage anmelden;
	@SuppressWarnings("unused")
	private  static AnmeldeseiteCtrl anmeldenCtrl;	
	
	private  static Stage hauptseite;
	@SuppressWarnings("unused")
	private  static HauptseiteCtrl hauptseiteCtrl;	
	
	private static Stage listensicht;
	private static ListensichtCtrl listensichtCtrl;
	
	private  static Stage detail;
	private  static DetailCtrl detailCtrl;
	
	private  static Stage addFilm;
	private  static AddFilmCtrl addFilmCtrl;
		
	private  static Stage filter;
	private  static FilterCtrl filterCtrl;
	
	
	private static FXMLLoader getLoader(String source) {
		FXMLLoader loader = new FXMLLoader(FensterManager.class.getResource(source));
		try {
			loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return loader;
	}
	
	
	public static Stage getDetail(Film film, Stapelverarbeitung<Film> stpv) throws SQLException{
		if(detail==null) {
			detail = new Stage();
			detail.setTitle("Filmdatenbank - Detailansicht");
			detail.setResizable(false);
			FXMLLoader loader = getLoader("fxml/Detail.fxml");
    		detailCtrl = loader.getController();
    		detail.setScene(new Scene(loader.getRoot()));
		}
		detailCtrl.setFilm(film, stpv);
		return detail;
	}
	
	public static Stage getAddFilm(Film film, Stapelverarbeitung<Film> stpv) throws SQLException{
		if(film != null) {
			if( !(Nutzer.getNutzer().getRechte().isUpdate() && film.getErstellerId()==Nutzer.getNutzer().getId() || Nutzer.getNutzer().getRechte().isUpdateAll())	)
				throw new SQLException("Keine Berechtigung");
		}else if(film == null) {
			
		}
		
		if(addFilm==null) {
			addFilm = new Stage();
			addFilm.setResizable(false);
			addFilm.setTitle("Filmdatenbank - Detailansicht");
			FXMLLoader loader = getLoader("fxml/AddFilm.fxml");
			addFilmCtrl = loader.getController();
    		addFilm.setScene(new Scene(loader.getRoot()));
		}
		addFilmCtrl.setFilm(film, stpv);
		return addFilm;
	}
	
	public static Stage getAnmelden() {
		if(anmelden==null) {
			anmelden = new Stage();
			anmelden.setResizable(false);
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
			hauptseite.setResizable(false);
			hauptseite.setTitle("Filmdatenbank");
			FXMLLoader loader = getLoader("fxml/Hauptseite.fxml");
			hauptseiteCtrl = loader.getController();
			hauptseite.setScene(new Scene(loader.getRoot()));
		}
		return hauptseite;
	}
	
	public static Stage getFilter(Filmverwaltung fvw) {
		if(filter==null) {
			filter = new Stage();
			filter.setResizable(false);
			filter.setTitle("Filmdatenbank - Filter");
			FXMLLoader loader = getLoader("fxml/Filter.fxml");
			filterCtrl = loader.getController();
			filter.setScene(new Scene(loader.getRoot()));
		}
		filterCtrl.setFvw(fvw);
		return filter;
	}
	
	public static Stage getListensicht() {
		if(listensicht==null) {
			listensicht = new Stage();
			listensicht.setResizable(false);
			listensicht.setTitle("Filmdatenbank - Filter");
			FXMLLoader loader = getLoader("fxml/Listensicht.fxml");
			listensichtCtrl = loader.getController();
			listensicht.setScene(new Scene(loader.getRoot()));
		}
		return listensicht;
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
			a.setContentText("M�chten sie die Anwendung wirklich beenden");
			a.show();
			a.setOnCloseRequest(ev2->{
				if(a.getResult().getButtonData().equals(ButtonData.OK_DONE)){
					if(Nutzer.getNutzer().isAngemeldet()) Nutzer.getNutzer().abmelden();
					reset();
					System.exit(0);
				}
			});
		});
		
//		primaryStage.focusedProperty().addListener((ob,ov,focus)->{
//			if(focus && dialog!=null && dialog.isShowing())	dialog.close();
//		});
	}
	
	public static void setSecondary(Stage stage) {
		if(secondary!=null && secondary!=stage)	secondary.close();
		secondary = stage;
		secondary.requestFocus();
		secondary.show();
	}
	
	public static void setDialog(Stage stage) {
		if(dialog!=null && dialog!=stage)	dialog.close();
		dialog = stage;
		dialog.requestFocus();
		dialog.show();
	}
	
	public static Stage getPrimary() {
		return primaryStage;
	}
	public static Stage getSecondary() {
		return secondary;
	}
	public static Stage getDialog() {
		return dialog;
	}
	
	
	public static void reset() {
		if(primaryStage!=null)		primaryStage.close();
		if(secondary!=null)			secondary.close();
		if(dialog!=null)			dialog.close();
	}
}
