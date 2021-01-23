package gui.controller;

import java.awt.datatransfer.Clipboard;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import gui.FensterManager;
import javafx.beans.property.BooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
import javafx.scene.input.DataFormat;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import verwaltung.entitaeten.Film;
import verwaltung.entitaeten.Liste;
import verwaltung.verwaltungen.Filmverwaltung;
import verwaltung.verwaltungen.Listenverwaltung;

public class ListensichtCtrl {

	private final static DataFormat NICHTS = new DataFormat("nichts");
	
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
    private ImageView muelleimer_filme;

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
    private ImageView muelleimer_listen;
    
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
		}else 
			lvw.reset();		
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
    	table_listen.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    	table_listen.getSelectionModel().selectedItemProperty().addListener((ob,ov,liste)->aktualisiereAngezeigteListe(table_listen.getSelectionModel()));
    	table_listen.setItems(lvw.getObList());
    	tListe_name.setCellValueFactory(data->data.getValue().getNameProperty());
    	tListe_name.setCellFactory(TextFieldTableCell.forTableColumn());
    	tListe_name.setOnEditCommit(this::onEditCommit);
    	tListe_size.setCellValueFactory(data->data.getValue().getSizeProperty());
    	
     	cb.getSelectionModel().selectedItemProperty().addListener((ob,ov,liste)->aktualisiereAngezeigteListe(cb.getSelectionModel()));
    	cb.setItems(lvw.getObList());
    	
    	table_film.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    	table_film.setOnDragDetected(this::onDragDected);
      	table_listen.setOnDragDetected(this::onDragDected);
    	table_film.setOnDragOver(this::onDragOverTable);
    	table_film.setOnDragDropped(this::zuListeHinzufuegen);  	
    	tFilm_titel.setCellValueFactory(data->data.getValue().getTitelProperty());
    	//tFilm_dauer.setCellValueFactory(data->data.getValue().getDauerStringProperty());
    	tFilm_bwt.setCellValueFactory(data->data.getValue().getBwtStringProperty());
    	
    	muelleimer_filme.setOnDragOver(this::onDragOverMuell);
    	muelleimer_filme.setOnDragDropped(this::onDragDroppedMuell);
    	muelleimer_listen.setOnDragOver(this::onDragOverMuell);
    	muelleimer_listen.setOnDragDropped(this::onDragDroppedMuell);
    	
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
		ClipboardContent content = new ClipboardContent();
		content.put(NICHTS, "");
    	if(event.getSource()==table_film) 			table_film.startDragAndDrop(TransferMode.MOVE).setContent(content);
    	else if(event.getSource()==table_listen) 	table_listen.startDragAndDrop(TransferMode.MOVE).setContent(content);

    } 
    private void onDragOverTable(DragEvent event) {
    	if(!event.getDragboard().hasContent(Filmverwaltung.dfFilm)) return;   
    	if(angezeigteListe!=null)	event.acceptTransferModes(TransferMode.LINK);
    }  	
    private void onDragOverMuell(DragEvent event) {    		
    	if(event.getGestureSource()==table_film && event.getTarget()==muelleimer_filme )			event.acceptTransferModes(TransferMode.MOVE);
    	else if(event.getGestureSource()==table_listen && event.getTarget()==muelleimer_listen )	event.acceptTransferModes(TransferMode.MOVE);
    }   
    private void onDragDroppedMuell(DragEvent event) {
    	if(event.getGestureTarget()==muelleimer_filme) 			table_film.getSelectionModel().getSelectedItems().forEach(angezeigteListe::removeEntitaet);
    	else if(event.getGestureTarget()==muelleimer_listen)	table_listen.getSelectionModel().getSelectedItems().forEach(lvw::removeEntitaet);
		event.setDropCompleted(true);
		event.consume();	
    }
    private void zuListeHinzufuegen(DragEvent event) {
    	Filmverwaltung.kopiereAusDragbord(event.getDragboard()).forEach(angezeigteListe::addEntitaet);
		event.setDropCompleted(true);
		event.consume();
    }
    
    private void onEditCommit(CellEditEvent<Liste, String> event) {
    	String value = event.getNewValue();
    	value = value.length()>Listenverwaltung.getMaxName() ? value.substring(0, Listenverwaltung.getMaxName()) : value;
    
    	Liste li = event.getRowValue();
    	if(li.getId()!=-1 && !li.hasBackup()) {
    		li.backup();
    		lvw.updateEntitaet(li);
        	li.setName(value);
    	}
    }
    
}
