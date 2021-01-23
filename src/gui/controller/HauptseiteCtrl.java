package gui.controller;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import gui.FensterManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import verwaltung.Nutzer;
import verwaltung.entitaeten.Film;
import verwaltung.verwaltungen.Filmverwaltung;

public class HauptseiteCtrl {
	
	public static final DataFormat df = new DataFormat("filme_ids");
	
	private long lastMouseClick = 0;
	private Filmverwaltung fvw = new Filmverwaltung();

    @FXML
    private TableView<Film> table; 

    @FXML
    private TableColumn<Film, String> t_Titel; 

    @FXML
    private TableColumn<Film, String> t_genre; 

    @FXML
    private TableColumn<Film, String> t_bewertung; 

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
    private Button btn_filter;
    
    @FXML
    private Button btn_refresh;

    @FXML
    void action(ActionEvent event) {
    	try {
    		if(event.getSource()==btn_add)				FensterManager.setDialog( FensterManager.getAddFilm(null, fvw, null) );
    		else if(event.getSource()==btn_update)		updateFilm();
    		else if(event.getSource()==btn_detail)		detail();
    		else if(event.getSource()==btn_abmelden) 	abmelden();
    		else if(event.getSource()==btn_filter)		FensterManager.setDialog( FensterManager.getFilter(fvw) );
    		else if(event.getSource()==btn_refresh)		Filmverwaltung.refreshAll();
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
        table.setItems(fvw.getObList());
        t_Titel.setCellValueFactory(	data->data.getValue().getTitelProperty());
        t_genre.setCellValueFactory(	data->data.getValue().getGenreStringProperty());
        t_bewertung.setCellValueFactory(data->data.getValue().getBwtStringProperty());
        t_dauer.setCellValueFactory(	data->data.getValue().getDauerStringProperty());
        t_jahr.setCellValueFactory(		data->data.getValue().getErscheinungsjahrProperty());
        
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

   
        try {
			fvw.test();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
        
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

        table.setOnDragDetected(ev->{
        	Dragboard db = table.startDragAndDrop(TransferMode.LINK);

        	 List<Integer> ids = new ArrayList<>();
        	 table.getSelectionModel().getSelectedItems().forEach(f->ids.add(f.getId()));
        	 ClipboardContent content = new ClipboardContent();
        	 content.put(df, ids);
        	db.setContent(content);
        });
        
    }
    
    private void detail() throws Exception {
    	Film film = table.getSelectionModel().getSelectedItem();
    	if(film == null)	throw new Exception("Es wurde kein Film ausgewählt");
		FensterManager.setDialog( FensterManager.getDetail(film) );
    }

    private void updateFilm() throws Exception {
    	Film film = table.getSelectionModel().getSelectedItem();
    	if(film == null)	throw new Exception("Es wurde kein Film ausgewählt");
    	FensterManager.setDialog( FensterManager.getAddFilm(film, fvw, null) );
    }

    private void abmelden()  {
		FensterManager.reset();
    	Nutzer.getNutzer().abmelden();
		FensterManager.setPrimaryStage(FensterManager.getAnmelden());
	}
}
