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
import verwaltung.DB_Manager;
import verwaltung.entitaeten.Film;
import verwaltung.entitaeten.Nutzer;

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
	
	static {
		
		Nutzer.getNutzer().angemeldetProperty().addListener((ob, ov, angemeldet)->{
			try {
				if(angemeldet) 
					setPrimaryStage(showHauptSeite());
				else {
					reset();
					setPrimaryStage(showAnmelden());
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		});
	}
	
	public static Stage showDetail(Film film) throws SQLException, IOException{
		if(detail==null) {
			detail = new Stage();
			detail.setTitle("Filmdatenbank - Detailansicht");
			detail.setResizable(false);
			FXMLLoader loader = new FXMLLoader(FensterManager.class.getResource("fxml/Detail.fxml"));
			loader.load();
    		detailCtrl = loader.getController();
    		detail.setScene(new Scene(loader.getRoot()));
		}
		detailCtrl.setFilm(film);
		if(!detail.isShowing())	detail.show();
		detail.requestFocus();
		return detail;
	}
	
	public static Stage showAddFilm(Film film) throws SQLException, IOException{
		if(film != null) {
			if( !(Nutzer.getNutzer().getRechte().isUpdate() && film.getErstellerId()==Nutzer.getNutzer().getId() || Nutzer.getNutzer().getRechte().isUpdateAll())	)
				throw new SQLException("Keine Berechtigung");
		}else if(film == null) {
			
		}
		
		if(addFilm==null) {
			addFilm = new Stage();
			addFilm.setTitle("Filmdatenbank - Detailansicht");
			addFilm.setResizable(false);
			FXMLLoader loader = new FXMLLoader(FensterManager.class.getResource("fxml/AddFilm.fxml"));
			loader.load();
			addFilmCtrl = loader.getController();
    		addFilm.setScene(new Scene(loader.getRoot()));
		}
		addFilmCtrl.setFilm(film);
		if(!addFilm.isShowing())	addFilm.show();
		addFilm.requestFocus();
		return addFilm;
	}
	
	public static Stage showAnmelden() throws SQLException, IOException {
		if(anmelden==null) {
			anmelden = new Stage();
			anmelden.setTitle("Filmdatenbank - Anmelden");
			FXMLLoader loader = new FXMLLoader(FensterManager.class.getResource("fxml/Anmeldeseite.fxml"));
			loader.load();
			anmeldenCtrl = loader.getController();
			anmelden.setScene(new Scene(loader.getRoot()));
		}
		if(!anmelden.isShowing()) anmelden.show();
		anmelden.requestFocus();
		return anmelden;
	}
	
	public static Stage showHauptSeite() throws SQLException, IOException {
		if(hauptseite==null) {
			hauptseite = new Stage();
			hauptseite.setTitle("Filmdatenbank");
			FXMLLoader loader = new FXMLLoader(FensterManager.class.getResource("fxml/Hauptseite.fxml"));
			loader.load();
			hauptseiteCtrl = loader.getController();
			hauptseite.setScene(new Scene(loader.getRoot()));
		}
		if(!hauptseite.isShowing()) hauptseite.show();
		hauptseite.requestFocus();
		return hauptseite;
	}
	
	public static void setPrimaryStage(Stage primaryStage) {
		if(FensterManager.primaryStage!=null)	FensterManager.primaryStage.close();
		FensterManager.primaryStage = primaryStage;
		
		FensterManager.primaryStage.setOnCloseRequest(ev->{
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
	
	public static void setDialog(Stage dialog) {
		if(FensterManager.dialog!=null && FensterManager.dialog!=dialog)	FensterManager.dialog.close();
		FensterManager.dialog = dialog;
	}
	
	public static void reset() {
		if(anmelden!=null)		anmelden.close();
		if(hauptseite!=null)	hauptseite.close();
		if(detail!=null)		detail.close();
	}
}
