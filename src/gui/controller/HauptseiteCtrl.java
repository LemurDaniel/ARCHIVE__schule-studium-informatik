package gui.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalTime;

import gui.FensterManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import verwaltung.Nutzer;
import verwaltung.entitaeten.Film;
import verwaltung.verwaltungen.Filmverwaltung;

public class HauptseiteCtrl {
	
	private long lastMouseClick = 0;
	private Stage filter;

    @FXML
    private Button blabla;
	
	
    @FXML
    private TableView<Film> table; 

    @FXML
    private TableColumn<Film, String> t_Titel; 

    @FXML
    private TableColumn<Film, String> t_genre; 

    @FXML
    private TableColumn<Film, Number> t_bewertung; 

    @FXML
    private TableColumn<Film, String>t_dauer; 

    @FXML
    private TableColumn<Film, Number> t_jahr; 

    @FXML
    private Button btn_add; 

    @FXML
    private Button btn_update; 

    @FXML
    private Button btn_detail; 
    
    @FXML
    private Button btn_abmelden; 

    @FXML
    void action(ActionEvent event) {
    	try {
    		if(event.getSource()==btn_add)				addFilm();
    		else if(event.getSource()==btn_update)		updateFilm();
    		else if(event.getSource()==btn_detail)		detail();
    		else if(event.getSource()==btn_abmelden) 	Nutzer.getNutzer().abmelden();
    		else if(event.getSource()==blabla)			filter();
    	}catch(Exception e) {
    		Alert a = new Alert(AlertType.ERROR);
    		a.setTitle(e.getClass().getSimpleName());
    		a.setContentText(e.getMessage());
    		a.show();
    		e.printStackTrace();
    	}
    }

    @FXML
    void initialize() {  
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
					FensterManager.setDialog(FensterManager.getDetail(table.getSelectionModel().getSelectedItem()));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
        	lastMouseClick = now;
        });

//		filter = FensterManager.getFilter();
//		filter.focusedProperty().addListener((ob,ov,focus)->{
//			if(focus==false) filter.close();
//		});
//    	blabla.addEventHandler(MouseEvent.MOUSE_ENTERED, ev->{
//    		Bounds d = blabla.localToScreen(blabla.getBoundsInLocal());
//    		filter.setX( d.getMinX());
//    		filter.setY( d.getMinY());
//    		filter.show();
//    	});

    }
    
    private void detail() throws Exception {
    	Film film = table.getSelectionModel().getSelectedItem();
    	if(film == null)	
    		throw new Exception("Es wurde kein Film ausgewählt");
		FensterManager.setDialog( FensterManager.getDetail(film) );
    }

    private void updateFilm() throws Exception {
    	Film film = table.getSelectionModel().getSelectedItem();
    	if(film == null)	
    		throw new Exception("Es wurde kein Film ausgewählt");
    	FensterManager.setDialog( FensterManager.getAddFilm(film) );
    }
    
    private void addFilm() throws SQLException, IOException {
    	FensterManager.setDialog( FensterManager.getAddFilm(null) );
    }
    
    private void filter() {
    	FensterManager.setDialog( FensterManager.getFilter() );
    }
}
