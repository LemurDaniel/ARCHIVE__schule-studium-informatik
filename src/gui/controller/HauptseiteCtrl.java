package gui.controller;

import gui.FensterManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import verwaltung.entitaeten.Film;
import verwaltung.entitaeten.Nutzer;
import verwaltung.verwaltungen.Filmverwaltung;
import verwaltung.verwaltungen.Listenverwaltung;

public class HauptseiteCtrl {
	
	private long lastMouseClick = 0;
	private Filmverwaltung fvw = new Filmverwaltung();
	private Thread th;

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
    private Button btn_save;
    
    @FXML
    private Button btn_reset;
    
    @FXML
    private Button btn_liste;
    
    @FXML
    private Button btn_status;

    @FXML
    private Button btn_credits;
    
    @FXML
    private ImageView muell;
 

    @FXML
    void action(ActionEvent event) {
    	try {
    		if(event.getSource()==btn_add)				FensterManager.setDialog( FensterManager.getAddFilm(null, fvw) );
    		else if(event.getSource()==btn_update)		updateFilm();
    		else if(event.getSource()==btn_detail)		detail();
    		else if(event.getSource()==btn_abmelden) 	abmelden();
    		else if(event.getSource()==btn_filter)		FensterManager.setDialog( FensterManager.getFilter(fvw) );
    		else if(event.getSource()==btn_refresh)		Filmverwaltung.refreshAll();
    		else if(event.getSource()==btn_save)		speichern();
    		else if(event.getSource()==btn_reset)		fvw.reset();
    		else if(event.getSource()==btn_liste)		FensterManager.setSecondary(FensterManager.getListensicht());
    		else if(event.getSource()==btn_status)		FensterManager.showStatusmeldung();
    		else if(event.getSource()==btn_credits)		FensterManager.showCredits();
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
		table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        table.setItems(fvw.getObList());
        t_Titel.setCellValueFactory(	data->data.getValue().getTitelProperty());
        t_genre.setCellValueFactory(	data->data.getValue().getGenreStringProperty());
        t_bewertung.setCellValueFactory(data->data.getValue().getBwtStringProperty());
        t_dauer.setCellValueFactory(	data->data.getValue().getDauerStringProperty());
        t_jahr.setCellValueFactory(		data->data.getValue().getErscheinungsjahrProperty());
        
        table.setOnMouseClicked(this::onMouseClicked);
        table.setOnDragDetected(this::onDragDetected);
        muell.setOnDragOver(this::onDragOver);
        muell.setOnDragDropped(this::onDragDropped);
        
    }
	
	private void onMouseClicked(MouseEvent event) {
		long now = System.currentTimeMillis();
    	if(now-lastMouseClick<200 && table.getSelectionModel().getSelectedIndex()!=-1) {
    		btn_detail.fire();
    	}
    	lastMouseClick = now;
	}
	private void onDragDetected(MouseEvent event) {
		Dragboard db = table.startDragAndDrop(TransferMode.LINK);
    	Filmverwaltung.kopiereInDragbord(db, table.getSelectionModel().getSelectedItems());
	}
	private void onDragOver(DragEvent event) {
		if(event.getGestureSource()==table)	event.acceptTransferModes(TransferMode.ANY);
	}
	private void onDragDropped(DragEvent event) {
		// Löscht keine Filme. Entfernt nur neu erstellte nicht abgespeicherte Filme (id==-1)
		table.getSelectionModel().getSelectedItems().filtered(film->film.getId()==-1).forEach(film->fvw.removeEntitaet(film));
	}
    	
    private void detail() throws Exception {
    	Film film = table.getSelectionModel().getSelectedItem();
    	if(film == null)	throw new Exception("Es wurde kein Film ausgewählt");
		FensterManager.setDialog( FensterManager.getDetail(film, fvw) );
    }
    
    private void updateFilm() throws Exception {
    	Film film = table.getSelectionModel().getSelectedItem();
    	if(film == null)	throw new Exception("Es wurde kein Film ausgewählt");
    	FensterManager.setDialog( FensterManager.getAddFilm(film, fvw) );
    }

    private void abmelden()  {
    	Filmverwaltung.clearAll();
    	Listenverwaltung.instance().clear();
		FensterManager.reset();
    	Nutzer.getNutzer().abmelden();
		FensterManager.setPrimaryStage(FensterManager.getAnmelden());
	}
    
	private void speichern() throws Exception{
		if(th!=null && th.isAlive()) th.interrupt();
		else {
			th = new Thread(fvw);
			th.start();
		}
	}
}
