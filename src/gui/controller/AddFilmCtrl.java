package gui.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import fxControls.IntegerMinMaxTextField;
import fxControls.StringTextField;
import gui.FensterManager;
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
import javafx.scene.control.ButtonBar.ButtonData;
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
import javafx.scene.layout.HBox;
import javafx.util.StringConverter;
import verwaltung.DB_Manager;
import verwaltung.entitaeten.Film;
import verwaltung.entitaeten.Genre;
import verwaltung.entitaeten.Person;
import verwaltung.entitaeten.Person.PersonMitRolle;
import verwaltung.entitaeten.Rolle;
import verwaltung.verwaltungen.Filmverwaltung;
import verwaltung.verwaltungen.Stapelverarbeitung;
import verwaltung.verwaltungen.unterverwaltungen.Personenverwaltung;


public class AddFilmCtrl {

	private Film film;
	private Personenverwaltung pvw;
	private Stapelverarbeitung<Film> stpv;

	private BiMap<Genre, BooleanProperty> checked_genre = HashBiMap.create();
	private List<Genre> selected;
	private boolean blocked;
	
	public void setFilm(Film film, Stapelverarbeitung<Film> stpv) throws SQLException{
		accordion.setExpandedPane(tp_allg);
	    tab_pane.getSelectionModel().select(tab_allg);
	    tab_pane.requestFocus();	

	    if(film==null) {
	    	film = new Film(-1, 0, null, 0, 0, 0);
	    	pvw = film.getPvw();
	    }else{
	    	pvw = film.getPvw();
			if(!pvw.isLoaded() || !film.getRvw().isLoaded()) {
				try(Connection con = DB_Manager.getCon()){
					pvw.loadIfnotLoaded(con);
					film.getRvw().loadIfnotLoaded(con);
				}
			}
		}
		
	    this.film = film;
	    this.stpv = stpv;
		setDisplay();
		setTable();
	}
	
	private void setTable() {
		tp_mit.setDisable(film.getId()==-1);
		table.setItems(pvw.getPersonenMitRollen());	
	}
	
	private void setDisplay() {
	    blocked = true;
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

	    /** Mitwirkende **/
	    @FXML
	    private TitledPane tp_mit;

	    @FXML
	    private TableView<PersonMitRolle> table;

	    @FXML
	    private TableColumn<PersonMitRolle, Boolean> t_confirm;

	    @FXML
	    private TableColumn<PersonMitRolle, Boolean> t_confirm1;

	    @FXML
	    private TableColumn<PersonMitRolle, Boolean> t_confirm2;

	    @FXML
	    private TableColumn<PersonMitRolle, String> t_vorname;

	    @FXML
	    private TableColumn<PersonMitRolle, String> t_name;

	    @FXML
	    private TableColumn<PersonMitRolle, Rolle> t_rolle;

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
    		else if(event.getSource()==btn_commitP)	commitPersonen();
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
        tf_jahr = new IntegerMinMaxTextField(Filmverwaltung.getMinJahr(), Filmverwaltung.getMaxJahr(), "");
        tf_dauer.setTailSupplier( ()->" Minuten "+Film.getGenaueZeit(tf_dauer.getValue())	);
        hb_dauer.getChildren().add(tf_dauer);
        hb_jahr.getChildren().add(tf_jahr);
             
        tf_titel.setPromptText("Filmtitel");
        tf_dauer.setPromptText("Laufzeit");
        tf_jahr.setPromptText("Erscheinungsjahr");
        
        tf_bewertung.setDisable(true);
        tf_genre.setEditable(false);
        ta_genre.setWrapText(true);
        
        /** Table Genre **/
        table_genre.setEditable(true);
        table_genre.setItems(FXCollections.observableArrayList(Filmverwaltung.getGenres()));
        table_genre.getSelectionModel().selectedItemProperty().addListener( (ob,ov,nv)->{
        	if(nv!=null)	ta_genre.setText(nv.getText());			//Wenn Genre in Tabelle ausgewählt(Nicht abgehakt), dann Infotext dazu anzeigen
        	else			ta_genre.setText(null);
        });
        
        Filmverwaltung.getGenres().forEach(genre->checked_genre.put(genre, new SimpleBooleanProperty(false)));
        t_check.setCellFactory(CheckBoxTableCell.forTableColumn(t_check));
        t_check.setCellValueFactory(data->checked_genre.get(data.getValue()));
        t_genre.setCellValueFactory(data->new SimpleStringProperty( data.getValue().getGenre() ));

        
        checked_genre.forEach((k,v)->v.addListener(this::aktualisierGenre));
        
        
        /** Table Mitwirkende **/
        table.setEditable(true);
        t_name.setCellValueFactory(		data->data.getValue().getPerson().getNameProperty()				);
        t_vorname.setCellValueFactory(	data->data.getValue().getPerson().getVornameProperty()			);
        t_rolle.setCellValueFactory(	data->data.getValue().getRolle().getObservable()				);
        t_confirm1.setCellValueFactory(	data->data.getValue().getUpdateProperty()						);
        t_confirm2.setCellValueFactory(	data->data.getValue().getDeleteProperty()						);
        
        t_name.setCellFactory(TextFieldTableCell.forTableColumn());
        t_vorname.setCellFactory(TextFieldTableCell.forTableColumn());
       // t_rolle.setCellFactory( ChoiceBoxTableCell.forTableColumn(Personenverwaltung.getRollen()) );
        t_confirm1.setCellFactory(CheckBoxTableCell.forTableColumn(t_confirm1));
        t_confirm2.setCellFactory(CheckBoxTableCell.forTableColumn(t_confirm2));
        t_confirm1.setEditable(false);
        
        //Konvertiere Rolle zu String für t_rolle spalte
        t_rolle.setCellFactory(ComboBoxTableCell.forTableColumn(Personenverwaltung.getRollen()));
        
        
        /**Changes**/
        t_vorname.setOnEditCommit(this::onEditCommit);
        t_name.setOnEditCommit(this::onEditCommit);
        t_rolle.setOnEditCommit(this::onEditCommit);      
        
        tf_dauer.textProperty().addListener(this::changeListener);
        tf_jahr.textProperty().addListener(this::changeListener);
        tf_titel.textProperty().addListener(this::changeListener);    
        
    }
    
    private void aktualisierGenre( ObservableValue<? extends Boolean> ob, Boolean ov, Boolean nv) {
    	if(blocked)	return;
    	backupfilm();
   
    	Genre g = checked_genre.inverse().get(ob);
    	if(nv.booleanValue()==true) {
    		selected.add(g);
    		film.addGenre(g);
    	}else {
    		selected.remove(g);
    		film.remove(g);
    	}
    	
    	t_check.setText(Filmverwaltung.getMaxGenre()-selected.size()+"");	// Anzeige der noch übrigen Anzahl abzuhakender Genres
    	if(selected.size()>Filmverwaltung.getMaxGenre()) {
    		checked_genre.get(selected.get(0)).set(false);
    		return;
    	}
    	
    	tf_genre.setText(null);
    	if(selected.size()==0)	return;
    	
    	tf_genre.setText(film.getGenreStringProperty().get());
    }
    
    private void backupfilm() {
    	System.out.println(film.getId());
    	if(film.getId()==-1) 
        	stpv.addEntitaet(film);
    	else	if(!film.hasBackup()) {
    		film.backup();
    		stpv.updateEntitaet(film);
    	}
    }
    
    private void changeListener( ObservableValue<? extends String> ob, String ov, String nv) {
    	if(blocked)	return;
    	
    	backupfilm();   	
		if(tf_titel.getText()!=null)	film.setTitel( tf_titel.getText() );
		if(tf_dauer.getValue()!=null)	film.setDauer( tf_dauer.getValue() );
		if(tf_jahr.getValue()!=null)	film.setErscheinungsjahr(tf_jahr.getValue());
    }
    
    private void onEditCommit( CellEditEvent<PersonMitRolle, ?> data ) {
     	PersonMitRolle pmr = data.getRowValue();
       	pmr.getPerson().backup();
     	
       	if(data.getTableColumn()==t_rolle){
       		if(pmr.getPerson().existiert((Rolle) data.getNewValue())) {
       			data.consume();
       			data.getTableView().refresh();
       			return;
       		}
       		pmr.setRolle((Rolle) data.getNewValue());
       	}
       	
    	if(pmr.getPerson().getId()!=-1 && pmr.getUpdateProperty().get()==false)	{
        	pvw.updateEntitaet(pmr.getPerson());
        	pmr.getUpdateProperty().set(true);
    	}
       	
       	if(data.getTableColumn()==t_rolle)	return;
    	
    	int maxlen = 0;
    	if(data.getTableColumn()==t_vorname) 		maxlen = Personenverwaltung.getMaxVorname();
    	else if(data.getTableColumn()==t_name) 		maxlen = Personenverwaltung.getMaxName();

    	String val = data.getNewValue().toString();
    	if(val.length()>maxlen) 					val = val.substring(0, maxlen);
    	
     	if(data.getTableColumn()==t_vorname) 		pmr.getPerson().setVorname(val);
    	else if(data.getTableColumn()==t_name)		pmr.getPerson().setName(val);
     		
    }

    private void addPerson() {
    	PersonMitRolle pmr = new Person(-1, "Neue Person", "Neue Person", Personenverwaltung.getRollen().get(0) ).getPersonenMitRolle().get(0);
    	pvw.addEntitaet(pmr.getPerson());
    	pmr.getUpdateProperty().set(true);
    }
    
//    private void commitFilm() throws Exception {
//  	   	  	
//    	try(Connection con = DB_Manager.getCon()){
//    		stpv.save(con);
//    		stpv.getFehlerlog().forEach(f->System.out.println(f.getMessage()));
//    	}catch(Exception e) {
//    		throw e;
//    	}  	
//    	
//		tf_titel.setDefaultValue(film.getTitel());
//		tf_dauer.setDefaultValue(film.getDauer());
//		tf_jahr.setDefaultValue(film.getErscheinungsjahr()	);
//		if(film.getId()!=-1)	tp_mit.setDisable(false);
//    }
    
    private void commitPersonen() throws SQLException {
    	
    	pvw.getPersonenMitRollen().filtered(pmr->pmr.getDeleteProperty().get()==true).forEach(pmr-> pvw.removeEntitaet(pmr.getPerson()));
    	
		try(Connection con = DB_Manager.getCon()){
    		pvw.save(con);
    		pvw.getFehlerlog().forEach(p->System.out.println(p.getMessage()));
    		pvw.reset();
		}
    }
    
    
    private void openDetail() {
//    	fvw.reset();
//    	pvw.reset();
    	try {
			FensterManager.setDialog( FensterManager.getDetail(film, stpv) );
		} catch (SQLException e) {
			Alert a2 = new Alert(AlertType.ERROR);
			a2.setTitle(e.getClass().getSimpleName());
			a2.setContentText(e.getMessage());
			a2.show();
			e.printStackTrace();
		}
    }


}
