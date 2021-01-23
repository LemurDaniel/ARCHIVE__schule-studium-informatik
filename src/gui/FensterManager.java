package gui;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;


import application.Main;
import gui.controller.AddFilmCtrl;
import gui.controller.AnmeldeseiteCtrl;
import gui.controller.DetailCtrl;
import gui.controller.FilterCtrl;
import gui.controller.HauptseiteCtrl;
import gui.controller.ListensichtCtrl;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Modality;
import javafx.stage.Stage;
import verwaltung.Nutzer;
import verwaltung.entitaeten.Film;
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
	
	private static Alert statusmeldung, credits;
	private static TextFlow statusTA;
	
	
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
			listensicht.setTitle("Filmdatenbank - Listensicht");
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
		primaryStage.focusedProperty().addListener((ob,ov,nv)->{
			if(nv && dialog!=null)	dialog.close();
		});
		
		
		primaryStage.setOnCloseRequest(ev->{
			ev.consume();
			
			Alert a = new Alert(AlertType.CONFIRMATION);
			a.setTitle("Anwendung Beenden");
			a.setContentText("Möchten sie die Anwendung wirklich beenden");
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
		secondary.focusedProperty().addListener((ob,ov,nv)->{
			if(nv && dialog!=null)	dialog.close();
		});
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
		if(credits!=null)			credits.close();
		if(statusmeldung!=null)		statusTA.getChildren().clear();
		detail = null;
	}
	
	
	
	public static void logErreignis(Exception e) {
		logErreignis(e.getMessage(), Color.RED);
	}
	public static void logErreignis(String text) {
		logErreignis(text, Color.GREEN);
	}
	
	public static void logErreignis(String text, Color color) {
		Text t = new Text(text+"\n");
		t.setFill(color);
		
		if(Platform.isFxApplicationThread()) {
			if(statusmeldung==null)	showStatusmeldung();
			statusTA.getChildren().add(t);	
			if(statusTA.getChildren().size()>=1000)	statusTA.getChildren().remove(0);
		}else {
			Platform.runLater(()->{
				if(statusmeldung==null)	showStatusmeldung();
				statusTA.getChildren().add(t);	
				if(statusTA.getChildren().size()>=1000)	statusTA.getChildren().remove(0);
			});	
		}
	}

	public static void showStatusmeldung() {
		if(statusmeldung==null) {
			statusmeldung = new Alert(AlertType.INFORMATION);
			statusmeldung.setTitle("Statusmeldungen");
			statusmeldung.setHeaderText("Aufgetretene Datenbankereignisse: ");
			ImageView img = new ImageView(FensterManager.class.getResource("images/database.png").toString());
			img.setFitWidth(50);
			img.setPreserveRatio(true);
			statusmeldung.setGraphic(img);
			statusmeldung.setResizable(true);
		
			statusTA = new TextFlow();
			ScrollPane sp = new ScrollPane(statusTA);
			sp.vvalueProperty().bind(statusTA.heightProperty());
			
			HBox hb = new HBox(sp);
			HBox.setHgrow(sp, Priority.ALWAYS);
			statusmeldung.getDialogPane().setContent(hb);
			statusmeldung.getDialogPane().getStylesheets().add(FensterManager.class.getResource("css/status.css").toString());
			statusmeldung.getDialogPane().setPrefHeight(380);
			statusmeldung.getDialogPane().setPrefWidth(900);
			statusmeldung.initModality(Modality.WINDOW_MODAL);
			
			statusmeldung.getDialogPane().setOnMouseEntered(ev->sp.vvalueProperty().unbind());
			statusmeldung.getDialogPane().setOnMouseExited(ev->sp.vvalueProperty().bind(statusTA.heightProperty()));
		}
		else if(statusmeldung.isShowing()) {
			statusmeldung.close();
			return;
		}

		statusmeldung.setX(0);
		statusmeldung.setY(0);
		statusmeldung.show();
		statusmeldung.setX(0);
		statusmeldung.setY(0);
	}
	
	public static void showCredits() {
		if(credits==null) {
			VBox vb = new VBox();
			vb.setStyle("-fx-font-size: 20px");
			
			try(BufferedReader r = new BufferedReader(new FileReader(FensterManager.class.getResource("images/credit").getFile()))){
				r.lines().forEach(string->{
					String[] sarr = string.split(",");
				
					Hyperlink hp = new Hyperlink();
					hp.setText(sarr[1]);
					hp.setOnAction(ev->{
						Main.getApplication().getHostServices().showDocument("https://www.flaticon.com/authors/"+sarr[1]);
					});
							
					ImageView img = new ImageView(FensterManager.class.getResource("images/"+sarr[0]+".png").toString());
					img.setFitHeight(30);
					img.setPreserveRatio(true);
					img.setOnMouseClicked(ev->{
						Main.getApplication().getHostServices().showDocument(sarr[2]);
					});
				
					HBox hb = new HBox(img, new Label(" Icon made by "), hp, new Label(" from www.flaticon.com"));
					hb.setAlignment(Pos.CENTER_LEFT);
				
					vb.getChildren().add(hb);
				});
			} catch (IOException e) {
			}
			
			credits = new Alert(AlertType.INFORMATION);
			credits.setTitle("Credits");
			credits.setHeaderText(null);
			credits.setGraphic(null);
			credits.getDialogPane().setContent(vb);
			credits.initModality(Modality.APPLICATION_MODAL);
		}
		credits.show();
	}

}
