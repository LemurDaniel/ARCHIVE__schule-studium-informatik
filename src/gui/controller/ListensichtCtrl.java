package gui.controller;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import gui.FensterManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.util.StringConverter;
import verwaltung.DB_Manager;
import verwaltung.entitaeten.Film;
import verwaltung.entitaeten.Liste;
import verwaltung.verwaltungen.Filmverwaltung;
import verwaltung.verwaltungen.unterverwaltungen.Listenverwaltung;

public class ListensichtCtrl {

	private Listenverwaltung lvw = Listenverwaltung.instance();
	private Liste angezeigteListe;
	
	private boolean blocked = false;

	/** Erster Tap **/
    @FXML
    private TabPane tab_pane;

    @FXML
    private Tab tab_film;

    @FXML
    private ComboBox<Liste> cb;

    @FXML
    private TableView<Film> table_film;

    @FXML
    private TableColumn<Film, Boolean> tFilm_del;

    @FXML
    private TableColumn<Film, String> tFilm_titel;

    @FXML
    private TableColumn<Film, String> tFilm_bwt;

    @FXML
    private TableColumn<Film, String> tFilm_dauer;

    @FXML
    private Button btn_detail;

    @FXML
    private Button btn_update;

    @FXML
    private Button btn_add;
    
    @FXML
    private ImageView muelleimer;

    /** Tab Mit Listen **/
    @FXML
    private Tab tab_listen;

    @FXML
    private TableView<Liste> table_listen;

    @FXML
    private TableColumn<Liste, Boolean> tListe_Upd;

    @FXML
    private TableColumn<Liste, Boolean> tListe_Del;

    @FXML
    private TableColumn<Liste, String> tListe_name;

    @FXML
    private TableColumn<Liste, Number> tListe_size;

    @FXML
    private Button btn_rel;

    @FXML
    private Button btn_save;

    @FXML
    private Button btn_neu;
    
    @FXML
    public void action(ActionEvent event) {
    	try {
    		if(event.getSource()==btn_detail)		openDetail();
    		else if(event.getSource()==btn_update)	updateFilm();
    		else if(event.getSource()==btn_add)		addFilm();
    		else if(event.getSource()==btn_neu)		neuListe();
    		else if(event.getSource()==btn_rel)		zuruecksetzen();
    	}catch(Exception e) {
    		Alert a = new Alert(AlertType.ERROR);
    		a.setContentText(e.getMessage());
    		a.show();
    	}
    }
    private void zuruecksetzen() throws Exception{
		if(tab_film.isSelected()) {
			if(angezeigteListe==null)	throw new Exception("Es wurde keine Liste ausgewählt");
			angezeigteListe.reset();
		}
		else
			lvw.reset();
		
	//	aktualisiereAngezeigteListe(sl);
	}
	private void neuListe() {
		lvw.addEntitaet(new Liste(-1, "Neue Liste"));
	}
	private void addFilm() throws Exception{
		if(angezeigteListe==null)	throw new Exception("Es wurde keine Liste ausgewählt");
		FensterManager.setDialog(FensterManager.getAddFilm(null, angezeigteListe));
	}
	private void updateFilm() throws Exception{
		if(angezeigteListe==null || table_film.getSelectionModel().getSelectedItem()==null) throw new Exception("Es wurde kein Film ausgewählt");
		FensterManager.setDialog(FensterManager.getAddFilm(table_film.getSelectionModel().getSelectedItem(), angezeigteListe));
		
	}
	private void openDetail() throws Exception{
		if(angezeigteListe==null || table_film.getSelectionModel().getSelectedItem()==null) throw new Exception("Es wurde kein Film ausgewählt");
		FensterManager.setDialog(FensterManager.getDetail(table_film.getSelectionModel().getSelectedItem(), angezeigteListe));
	}


	@FXML
    void initialize() {
		table_listen.setEditable(true);
    	table_listen.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    	table_listen.getSelectionModel().selectedItemProperty().addListener((ob,ov,liste)->aktualisiereAngezeigteListe(table_listen.getSelectionModel()));
    	table_listen.setItems(lvw.getObList());
    	tListe_name.setCellValueFactory(data->data.getValue().getNameProperty());
    	tListe_name.setCellFactory(TextFieldTableCell.forTableColumn());
    	tListe_name.setOnEditCommit(this::onEditCommit);
    	tListe_size.setCellValueFactory(data->data.getValue().getSizeProperty());
    	
     	cb.getSelectionModel().selectedItemProperty().addListener((ob,ov,liste)->aktualisiereAngezeigteListe(cb.getSelectionModel()));
    	cb.setItems(lvw.getObList());
    	
    	table_film.setOnDragDetected(this::onDragDected);
    	table_film.setOnDragOver(this::onDragOverTable);
    	table_film.setOnDragDropped(this::zuListeHinzufuegen);  	
    	tFilm_titel.setCellValueFactory(data->data.getValue().getTitelProperty());
    	
    	muelleimer.setOnDragOver(this::onDragOverMuell);
    	muelleimer.setOnDragDropped(this::vonListeEntfernen);
    	
    	
    }
    
    private void aktualisiereAngezeigteListe(SelectionModel<Liste> sl) {
    	if(blocked)	return;
      	blocked = true;
    	angezeigteListe = sl.getSelectedItem();
      	if(angezeigteListe==null) {
    		table_film.setItems(null);
    		if(cb.getSelectionModel()!=sl) 				cb.getSelectionModel().clearSelection();
    		if(table_listen.getSelectionModel()!=sl) 	table_listen.getSelectionModel().clearSelection();
    	}else { 	
    		if(cb.getSelectionModel()!=sl) 				cb.getSelectionModel().select(sl.getSelectedItem());
    		if(table_listen.getSelectionModel()!=sl) 	table_listen.getSelectionModel().select(sl.getSelectedItem());
    		table_film.setItems(angezeigteListe.getObList());
    	}
    	blocked = false;
    }
   
    private void onDragDected(MouseEvent event) {   	
    	Dragboard db = table_film.startDragAndDrop(TransferMode.MOVE);
    	Filmverwaltung.kopiereInDragbord(db, table_film.getSelectionModel().getSelectedItems());
    } 
    private void onDragOverTable(DragEvent event) {
    	if(!event.getDragboard().hasContent(Filmverwaltung.df)) return;   
    	if(angezeigteListe!=null)	event.acceptTransferModes(TransferMode.LINK);
    }  	
    private void onDragOverMuell(DragEvent event) {
    	if(!event.getDragboard().hasContent(Filmverwaltung.df)) return;    		
    	if(event.getGestureSource()==table_film)	event.acceptTransferModes(TransferMode.MOVE);
    }   
    private void zuListeHinzufuegen(DragEvent event) {
    	Filmverwaltung.kopiereAusDragbord(event.getDragboard()).forEach(angezeigteListe::addEntitaet);
		event.setDropCompleted(true);
		event.consume();
    }
    private void vonListeEntfernen(DragEvent event) {
    	Filmverwaltung.kopiereAusDragbord(event.getDragboard()).forEach(angezeigteListe::removeEntitaet);
		event.setDropCompleted(true);
		event.consume();
    }
    
    private void onEditCommit(CellEditEvent<Liste, String> event) {
    	Liste li = event.getRowValue();
    	if(li.getId()!=-1 && !li.hasBackup()) {
    		li.backup();
    		lvw.addEntitaet(li);
    	}
    	li.setName(event.getNewValue());
    }
    
}
