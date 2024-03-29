package gui.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import gui.FensterManager;
import gui.fxControls.IntegerMinMaxTextField;
import gui.fxControls.StringTextField;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import verwaltung.DB_Manager;
import verwaltung.Stapelverarbeitung;
import verwaltung.entitaeten.Film;
import verwaltung.entitaeten.Genre;
import verwaltung.entitaeten.Nutzer;
import verwaltung.entitaeten.Person;
import verwaltung.entitaeten.Rolle;
import verwaltung.verwaltungen.Filmverwaltung;
import verwaltung.verwaltungen.unterverwaltungen.Personenverwaltung;


public class AddFilmCtrl {

	private Film film;
	private Personenverwaltung pvw;
	private Stapelverarbeitung<Film> stpv;

	private BiMap<Genre, BooleanProperty> checked_genre = HashBiMap.create();
	private List<Genre> selected;
	private boolean blocked, added;
		
	public void setFilm(Film film, Stapelverarbeitung<Film> stpv) throws SQLException{
		accordion.setExpandedPane(tp_allg);
	    tab_pane.getSelectionModel().select(tab_allg);
	    tab_pane.requestFocus();	

	    if(film==null) {
	    	film = new Film(-1, Nutzer.getNutzer().getId(), null, 120, 2000, 0);	   
	    	film.getPvw().setGeladen(true);
	    	film.getRvw().setGeladen(true);
	    }    
	    else if(film.getId()!=-1 && (!film.getPvw().isGeladen() || !film.getRvw().isGeladen())) {
			try(Connection con = DB_Manager.getCon()){
					film.getPvw().lade(con);
					film.getRvw().lade(con);			
			}
		}
	    pvw = film.getPvw();
		
	    this.film = film;
	    this.stpv = stpv;
	    added = false;
		setDisplay();
	}

	
	private void setDisplay() {		
	    blocked = true;
	    
		table.setItems(pvw.getObList());	
		table_genre.getSelectionModel().clearSelection();

		if(selected!=null)	selected.forEach(ge->checked_genre.get(ge).set(false));
		film.getGenres().forEach(g->checked_genre.get(g).set(true));
		selected = film.getGenres();
		tf_genre.setText(film.getGenreStringProperty().get());
		t_check.setText(Filmverwaltung.getMaxGenre()-selected.size()+"");
		
		tf_titel.setDefaultValue(film.getTitel());
		tf_dauer.setDefaultValue(film.getDauer());
		tf_jahr.setDefaultValue(film.getErscheinungsjahr()	);
		tf_bewertung.setText(film.getBwtStringProperty().get());
		
		blocked = false;
	}
	
	/** Variablen **/
	  	@FXML
	    private Accordion accordion;

	    @FXML
	    private TitledPane tp_allg;
	    
	    /** Allg */
	    @FXML
	    private Tab tab_allg;
	    
	    @FXML
	    private TabPane tab_pane;

	    @FXML
	    private Button btn_commitP;

	    @FXML
	    private Button btn_detail;

	    @FXML
	    private TextField tf_genre;

	    @FXML
	    private TextField tf_bewertung;
	    
	    @FXML
	    private HBox hb_titel;
	    @FXML
	    private HBox hb_dauer;
	    @FXML
	    private HBox hb_jahr;
	    private StringTextField tf_titel;
	    private IntegerMinMaxTextField tf_dauer;
	    private IntegerMinMaxTextField tf_jahr;

	    @FXML
	    private TableView<Genre> table_genre;

	    @FXML
	    private TableColumn<Genre, Boolean> t_check;

	    @FXML
	    private TableColumn<Genre, String> t_genre;

	    @FXML
	    private TextArea ta_genre;
	    
	    @FXML
	    private TextField tf_genre2;

	    /** Mitwirkende **/
	    @FXML
	    private TitledPane tp_mit;

	    @FXML
	    private TableView<Person> table;

	    @FXML
	    private TableColumn<Person, String> t_vorname;

	    @FXML
	    private TableColumn<Person, String> t_name;

	    @FXML
	    private TableColumn<Person, Rolle> t_rolle;
	    
	    @FXML
	    private TableColumn<Person, String> t_weiteres;
	    
	    @FXML
	    private ImageView muell;

	    @FXML
	    private Button btn_rel2;
	    
	    @FXML
	    private Button btn_addP;
	    

    @FXML
    void action(ActionEvent event) {
    	try {
    	//	if(event.getSource()==btn_rel1)			resetFilm();
    		if(event.getSource()==btn_rel2) 	pvw.reset();
    		//else if(event.getSource()==btn_commitF)	commitFilm();
    		else if(event.getSource()==btn_commitP)	throw new Exception("Out of Order");
    		else if(event.getSource()==btn_addP)	addPerson();
    		else if(event.getSource()==btn_detail)	openDetail();
    	}catch (Exception e) {
			e.printStackTrace();
		}
    }

	@FXML
    void initialize() {
        accordion.setExpandedPane(tp_allg);
        
        /** Allg **/
        tf_titel = new StringTextField(Filmverwaltung.getMaxTitel());
        hb_titel.getChildren().add(tf_titel);
        
        tf_dauer = new IntegerMinMaxTextField(0, Filmverwaltung.getMaxDauer());
        tf_jahr = new IntegerMinMaxTextField(Filmverwaltung.getMinJahr(), Filmverwaltung.getMaxJahr());
        tf_dauer.setTextFunction( (val)->film.getDauerStringProperty().get() );
        hb_dauer.getChildren().add(tf_dauer);
        hb_jahr.getChildren().add(tf_jahr);
             
        tf_titel.setPromptText("Filmtitel");
        tf_dauer.setPromptText("Laufzeit");
        tf_jahr.setPromptText("Erscheinungsjahr");
        
        tf_bewertung.setDisable(true);
        tf_genre.setEditable(false);
        
        /** Table Genre **/
        table_genre.setEditable(true);
        table_genre.setItems(FXCollections.observableArrayList(Filmverwaltung.getGenres()));
        table_genre.getSelectionModel().selectedItemProperty().addListener( (ob,ov,nv)->{
        	if(nv!=null)	{
        		ta_genre.setText(nv.getText());			
        		tf_genre2.setText(nv.getGenre());
        	}
        	else	ta_genre.setText(null);
        });
        
        Filmverwaltung.getGenres().forEach(genre->checked_genre.put(genre, new SimpleBooleanProperty(false)));
        t_check.setCellFactory(CheckBoxTableCell.forTableColumn(t_check));
        t_check.setCellValueFactory(data->checked_genre.get(data.getValue()));
        t_genre.setCellValueFactory(data->new SimpleStringProperty( data.getValue().getGenre() ));
        
        checked_genre.values().forEach(v->v.addListener(this::aktualisierGenre));

        tf_genre2.setEditable(false);  
        ta_genre.setEditable(false);
        ta_genre.setWrapText(true);
        
            
        /** Table Mitwirkende **/
        table.setEditable(true);
        t_name.setCellValueFactory(		data->data.getValue().getNameProperty()				);
        t_vorname.setCellValueFactory(	data->data.getValue().getVornameProperty()			);
        t_rolle.setCellValueFactory(	data->data.getValue().getRolle().getObservable()	);
        t_weiteres.setCellValueFactory( data->data.getValue().getWeiteresProperty()			);
        
        t_name.setCellFactory(TextFieldTableCell.forTableColumn());
        t_vorname.setCellFactory(TextFieldTableCell.forTableColumn());
        t_weiteres.setCellFactory(TextFieldTableCell.forTableColumn());
        t_rolle.setCellFactory(ComboBoxTableCell.forTableColumn(Personenverwaltung.getRollen()));
        
        
        /**Changes**/
        t_vorname.setOnEditCommit(this::onEditCommit);
        t_name.setOnEditCommit(this::onEditCommit);
        t_weiteres.setOnEditCommit(this::onEditCommit);
        t_rolle.setOnEditCommit(this::onEditCommit);      
        
        tf_dauer.setValueChanged(this::aktualisiereFilm);
        tf_jahr.setValueChanged(this::aktualisiereFilm);
        tf_titel.setValueChanged(this::aktualisiereFilm);   
        
        table.setOnDragDetected(this::onDragDetected);
        muell.setOnDragOver(this::onDragOver);
        muell.setOnDragDropped(this::onDragDropped);
    }
    
	private void onDragDetected(MouseEvent event) {
		ClipboardContent content = new ClipboardContent();
		content.put(ListensichtCtrl.NICHTS, "");
		table.startDragAndDrop(TransferMode.MOVE).setContent(content);
	}
	private void onDragOver(DragEvent event) {
		if(event.getGestureSource()==table)	event.acceptTransferModes(TransferMode.MOVE);
	}
	private void onDragDropped(DragEvent event) {
		try {
			table.getSelectionModel().getSelectedItems().forEach(pvw::removeEntitaet);
		}catch(NoSuchElementException e) {}
		if(film.getId()!=-1 && !added)	{
     		stpv.updateEntitaet(film);
     		added = true;
     	}    
	}
	
	
	
    private void aktualisierGenre( ObservableValue<? extends Boolean> ob, Boolean ov, Boolean nv) {
    	if(blocked)	return;
    	Genre g = checked_genre.inverse().get(ob);
    	if(g==Genre.getKurzfilm()) {
    		if(nv.booleanValue()==true && film.getDauer()>44) {
    			((BooleanProperty)ob).set(false);
    			return;
    		}
    		else if(nv.booleanValue()==false && film.getDauer()<45) {
    			((BooleanProperty)ob).set(true);
    			return;
    		}
    	}
    	
    	backupfilm();
   
    	if(nv.booleanValue()==true && selected.contains(g))	return;
    	if(nv.booleanValue()==true) {
    		selected.add(g);
    		film.addGenre(g);
    	}else {
    		selected.remove(g);
    		film.remove(g);
    	}
    	
    	t_check.setText(Filmverwaltung.getMaxGenre()-selected.size()+"");	// Anzeige der noch �brigen Anzahl abzuhakender Genres
    	if(selected.size()>Filmverwaltung.getMaxGenre()) {
    		checked_genre.get(selected.get(0)).set(false);
    		return;
    	}
    	
    	tf_genre.setText(null);
    	if(selected.size()==0)	return;
    	
    	tf_genre.setText(film.getGenreStringProperty().get());
    }
    
    private void backupfilm() {
    	if(!film.hasBackup() && film.getId()!=-1) {
    		film.backup();
    		stpv.updateEntitaet(film);
    	}
    	else if(film.getId()==-1 && added == false) {
    	   stpv.addEntitaet(film);
    	   added = true;
    	}
    }
    
    private void aktualisiereFilm(TextField tf, Object value) {
    	if(blocked || value == null)	return;

    	backupfilm();   	
		if(tf==tf_titel)		film.setTitel( value.toString() );
		else if(tf==tf_jahr)	film.setErscheinungsjahr((int)value);
		else if(tf==tf_dauer)	{
			film.setDauer( (int)value );
			if(film.getDauer()<=Filmverwaltung.getMaxShort())	checked_genre.get(Genre.getKurzfilm()).set(true);
			else												checked_genre.get(Genre.getKurzfilm()).set(false);
		}
    }
    
    private void onEditCommit( CellEditEvent<Person, ?> data ) {
    	Person per = data.getRowValue();	
    	
     	if(film.getId()!=-1 && added==false)	{
     		stpv.updateEntitaet(film);
     		added = true;
     	}
    	if(per.getId()!=-1 && !per.hasBackup() ){
    	   	per.backup();
        	pvw.updateEntitaet(per);
    	}
       	
       	if(data.getTableColumn()==t_rolle) {
       		per.setRolle((Rolle)data.getNewValue());
       		return;
       	}
    	
    	int maxlen = 0;
    	if(data.getTableColumn()==t_vorname) 		maxlen = Personenverwaltung.getMaxVorname();
    	if(data.getTableColumn()==t_weiteres) 		maxlen = Personenverwaltung.getMaxWeiteres();
    	else if(data.getTableColumn()==t_name) 		maxlen = Personenverwaltung.getMaxName();

    	String val = data.getNewValue().toString();
    	if(val.length()>maxlen) 					val = val.substring(0, maxlen);
    	
     	if(data.getTableColumn()==t_vorname) 		per.setVorname(val);
     	if(data.getTableColumn()==t_weiteres) 		per.setWeiteres(val);
    	else if(data.getTableColumn()==t_name)		per.setName(val);
     		
    }

    private void addPerson() {
    	Person per = new Person(-1, "Neue Person", "Neue Person", null, Personenverwaltung.getRollen().get(0));
    	pvw.addEntitaet(per);
    	if(added) return;
    	if(film.getId()!=-1)	stpv.updateEntitaet(film);
    	else					stpv.addEntitaet(film);
     	added = true;   
    }

    
    private void openDetail() {
    	try {
			FensterManager.setDialog( FensterManager.getDetail(film, stpv) );
		} catch (Exception e) {
			Alert a2 = new Alert(AlertType.ERROR);
			a2.setTitle("Fehler - Detailansicht");
			a2.setContentText(e.getMessage());
			a2.show();
			e.printStackTrace();
		}
    }


}
