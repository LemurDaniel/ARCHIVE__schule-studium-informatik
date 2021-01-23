package gui.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalTime;

import gui.FensterManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import verwaltung.Nutzer;
import verwaltung.entitaeten.Film;
import verwaltung.verwaltungen.Filmverwaltung;

public class HauptseiteCtrl {
	
	private long lastMouseClick = 0;

    @FXML // fx:id="table"
    private TableView<Film> table; // Value injected by FXMLLoader

    @FXML // fx:id="t_Titel"
    private TableColumn<Film, String> t_Titel; // Value injected by FXMLLoader

    @FXML // fx:id="t_genre"
    private TableColumn<Film, String> t_genre; // Value injected by FXMLLoader

    @FXML // fx:id="t_bewertung"
    private TableColumn<Film, Number> t_bewertung; // Value injected by FXMLLoader

    @FXML // fx:id="t_dauer"
    private TableColumn<Film, String>t_dauer; // Value injected by FXMLLoader

    @FXML // fx:id="t_jahr"
    private TableColumn<Film, Number> t_jahr; // Value injected by FXMLLoader

    @FXML // fx:id="btn_add"
    private Button btn_add; // Value injected by FXMLLoader

    @FXML // fx:id="btn_update"
    private Button btn_update; // Value injected by FXMLLoader

    @FXML // fx:id="btn_detail"
    private Button btn_detail; // Value injected by FXMLLoader
    
    @FXML // fx:id="btn"
    private Button btn_abmelden; // Value injected by FXMLLoader

    @FXML
    void action(ActionEvent event) {
    	try {
    		if(event.getSource()==btn_add)				addFilm();
    		else if(event.getSource()==btn_update)		updateFilm();
    		else if(event.getSource()==btn_detail)		detail();
    		else if(event.getSource()==btn_abmelden) 	Nutzer.getNutzer().abmelden();
    	}catch(Exception e) {
    		Alert a = new Alert(AlertType.ERROR);
    		a.setTitle(e.getClass().getSimpleName());
    		a.setContentText(e.getMessage());
    		a.show();
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
        
        table.setItems(Filmverwaltung.instance().getList());
        t_Titel.setCellValueFactory(	data->data.getValue().getTitelProperty());
        t_genre.setCellValueFactory(	data->data.getValue().getGenreStringProperty());
        t_bewertung.setCellValueFactory(data->data.getValue().getBewertungProperty());
        t_dauer.setCellValueFactory(	data->data.getValue().getDauerProperty());
        t_jahr.setCellValueFactory(		data->data.getValue().getErscheinungsjahrProperty());
        

        
        table.getSelectionModel().selectedItemProperty().addListener((ob, oldV, newV) ->{
        	System.out.println(table.getSelectionModel().getSelectedItem());
        });
        table.setOnMouseClicked(ev->{
        	long now = System.currentTimeMillis();
        	if(now-lastMouseClick<200 && table.getSelectionModel().getSelectedIndex()!=-1) {
        		try {
					FensterManager.setDialog(FensterManager.showDetail(table.getSelectionModel().getSelectedItem()));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
        	lastMouseClick = now;
        });
        try {
			Filmverwaltung.instance().test();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    private void detail() throws Exception {
    	Film film = table.getSelectionModel().getSelectedItem();
    	if(film == null)	
    		throw new Exception("Es wurde kein Film ausgewählt");
		FensterManager.setDialog( FensterManager.showDetail(film) );
    }

    private void updateFilm() throws Exception {
    	Film film = table.getSelectionModel().getSelectedItem();
    	if(film == null)	
    		throw new Exception("Es wurde kein Film ausgewählt");
    	FensterManager.setDialog( FensterManager.showAddFilm(film) );
    }
    
    private void addFilm() throws SQLException, IOException {
    	FensterManager.setDialog( FensterManager.showAddFilm(null) );
    }
}
