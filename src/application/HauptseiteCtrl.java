package application;

import java.awt.Window;
import java.io.IOException;

/**
 * Sample Skeleton for 'Hauptseite.fxml' Controller Class
 */

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.sun.glass.events.WindowEvent;

import bla.Film;
import bla.Filmverwaltung;
import bla.Nutzer;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;

public class HauptseiteCtrl {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;
    
    private Stage hauptseite;
    private Stage anmeldeseite;
    private Stage detail;
    private DetailCtrl detailCtrl;
    public void setStage(Stage hauptseite) {
    	this.hauptseite = hauptseite;
    }
    
    private void openLogin() {
    	if(anmeldeseite == null)anmeldeseite = new Stage();
    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("Anmeldeseite.fxml"));
    		loader.load();
    		((AnmeldeseiteCtrl)loader.getController()).setController(this);
			anmeldeseite.setScene(new Scene(loader.getRoot()));
			anmeldeseite.setTitle("Filmdatenbank - Anmelden");
			anmeldeseite.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	anmeldeseite.setOnCloseRequest(ev->{
    		hauptseite.fireEvent(ev);
    		ev.consume();
    	});
    }
    public void openHaupt() {
    	anmeldeseite.close();
    	anmeldeseite = null;
    	hauptseite.show();
    }

    @FXML // fx:id="table"
    private TableView<Film> table; // Value injected by FXMLLoader

    @FXML // fx:id="t_Titel"
    private TableColumn<Film, String> t_Titel; // Value injected by FXMLLoader

    @FXML // fx:id="t_genre"
    private TableColumn<Film, String> t_genre; // Value injected by FXMLLoader

    @FXML // fx:id="t_bewertung"
    private TableColumn<Film, Number> t_bewertung; // Value injected by FXMLLoader

    @FXML // fx:id="t_dauer"
    private TableColumn<Film, Number>t_dauer; // Value injected by FXMLLoader

    @FXML // fx:id="t_jahr"
    private TableColumn<Film, Number> t_jahr; // Value injected by FXMLLoader

    @FXML // fx:id="btn_add"
    private Button btn_add; // Value injected by FXMLLoader

    @FXML // fx:id="btn_update"
    private Button btn_update; // Value injected by FXMLLoader

    @FXML // fx:id="btn_detail"
    private Button btn_detail; // Value injected by FXMLLoader

    @FXML
    void add(ActionEvent event) {
    	System.out.println("Test");
    }

    @FXML
    void detail(ActionEvent event) {
    	Film f = table.getSelectionModel().getSelectedItem();
    	if(f==null) {
    		Alert a = new Alert(AlertType.ERROR);
    		a.setTitle("Detailansicht - Fehler");
    		a.setContentText("Es wurde kein Film ausgewählt");
    		a.show();
    		return;
    	}

    	if(detail==null) {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("Detail.fxml"));	
    		try {
				loader.load();
			} catch (IOException e) {
				e.printStackTrace();
			}
    		detailCtrl = loader.getController();
    	    detail = new Stage();
    	    detail.setScene(new Scene(loader.getRoot()));
    	    detail.setResizable(false);
    	    detail.setTitle("Filmdatenbank - Detailansicht");	
    	}
    	try {
			detailCtrl.setFilm(table.getSelectionModel().getSelectedItem());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		detail.show();
    }

    @FXML
    void update(ActionEvent event) {
        try {
			Filmverwaltung.instance().test();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert table != null : "fx:id=\"table\" was not injected: check your FXML file 'Hauptseite.fxml'.";
        assert t_Titel != null : "fx:id=\"t_Titel\" was not injected: check your FXML file 'Hauptseite.fxml'.";
        assert t_genre != null : "fx:id=\"t_genre\" was not injected: check your FXML file 'Hauptseite.fxml'.";
        assert t_bewertung != null : "fx:id=\"t_bewertung\" was not injected: check your FXML file 'Hauptseite.fxml'.";
        assert t_dauer != null : "fx:id=\"t_dauer\" was not injected: check your FXML file 'Hauptseite.fxml'.";
        assert t_jahr != null : "fx:id=\"t_jahr\" was not injected: check your FXML file 'Hauptseite.fxml'.";
        assert btn_add != null : "fx:id=\"btn_add\" was not injected: check your FXML file 'Hauptseite.fxml'.";
        assert btn_update != null : "fx:id=\"btn_update\" was not injected: check your FXML file 'Hauptseite.fxml'.";
        assert btn_detail != null : "fx:id=\"btn_detail\" was not injected: check your FXML file 'Hauptseite.fxml'.";
        
        openLogin();
        
        table.setEditable(true);
        table.setItems(Filmverwaltung.instance().getList());
        t_Titel.setCellValueFactory(data->data.getValue().getTitel());
        t_genre.setCellValueFactory(data->data.getValue().getGenre());
        t_bewertung.setCellValueFactory(data->data.getValue().getBewertung());
        t_dauer.setCellValueFactory(data->data.getValue().getDauer());
        t_jahr.setCellValueFactory(data->data.getValue().getErscheinungsjahr());
        t_Titel.setCellFactory(TextFieldTableCell.forTableColumn());
        
        table.getSelectionModel().selectedItemProperty().addListener((ob, oldV, newV) ->{
        	System.out.println(table.getSelectionModel().getSelectedItem());
        });
    }
}
