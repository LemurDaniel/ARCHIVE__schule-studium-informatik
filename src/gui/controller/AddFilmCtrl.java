package gui.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import fxControls.IntegerMinMaxTextField;
import fxControls.StringTextField;
import gui.FensterManager;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
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
import verwaltung.entitaeten.Liste;
import verwaltung.entitaeten.Person;
import verwaltung.entitaeten.Person.PersonMitRolle;
import verwaltung.entitaeten.Rolle;
import verwaltung.verwaltungen.Filmverwaltung;
import verwaltung.verwaltungen.unterverwaltungen.Personenverwaltung;

public class AddFilmCtrl {

	private Film film;
	private Personenverwaltung pvw;
	private Filmverwaltung fvw;
	private Liste filmliste;
	

	private BiMap<Genre, BooleanProperty> checked_genre = HashBiMap.create();
	private List<Genre> selected = new ArrayList<>();
	private boolean blocked;
	
	// 0 - update 1 - delete
	private Map<PersonMitRolle, BooleanProperty[]> confirmed = new HashMap<>();
	private ObservableList<PersonMitRolle> personen;
	private boolean[] changes = {false, false};
	
	public void setFvw(Filmverwaltung fvw) {
		this.fvw = fvw;
	}
	public void setFilmliste(Liste filmliste) {
		this.filmliste = filmliste;
	}
	
	public void setFilm(Film film) throws SQLException{
		accordion.setExpandedPane(tp_allg);
	    tab_pane.getSelectionModel().select(tab_allg);
	    tab_pane.requestFocus();
			
		if(film==null)	pvw = null;
		else {
			pvw = film.getPvw();
			if(!pvw.isLoaded() || !film.getRvw().isLoaded()) {
				try(Connection con = DB_Manager.getCon()){
					pvw.loadIfnotLoaded(con);
					film.getRvw().loadIfnotLoaded(con);
				}
			}
		}
		
		this.film = film;	
		setDisplay();
		setTable();
	}
	
	private void setTable() {
		if(pvw!=null)	personen = FXCollections.observableArrayList(pvw.getPersonenMitRollen());
		else			personen = FXCollections.observableArrayList();
		
		table.setItems(personen);
		confirmed.clear();		
		personen.forEach(per->	confirmed.put(per, new SimpleBooleanProperty[]{new SimpleBooleanProperty(), new SimpleBooleanProperty()})	);
		
		changes[1] = false;
	}
	
	private void setDisplay() {
		table_genre.getSelectionModel().clearSelection();

		blocked = true;
		selected.forEach(ge->checked_genre.get(ge).set(false));
		selected.clear();
		tf_genre.setText(null);
		blocked = false;
		
		if(film==null) {
			tf_titel.setDefaultValue(null);
			tf_dauer.setDefaultValue(null);
			tf_jahr.setDefaultValue(null);
			tf_bewertung.setText(null);
		}else {
			tf_titel.setDefaultValue(film.getTitel());
			tf_dauer.setDefaultValue(film.getDauer());
			tf_jahr.setDefaultValue(film.getErscheinungsjahr());
			tf_bewertung.setText(film.getBwtStringProperty().get());
			film.getGenres().forEach(g->checked_genre.get(g).set(true));
		}		
		changes[0] = false;
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
	    private Button btn_rel1;

	    @FXML
	    private Button btn_commit1;

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
    	if(event.getSource()==btn_rel1)			setDisplay();
    	else if(event.getSource()==btn_rel2) 	setTable();
    	else if(event.getSource()==btn_commit1)	commit();
    	else if(event.getSource()==btn_addP)	addPerson();
    	else if(event.getSource()==btn_detail)	detail();
    	
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

        /** Beim abhaken -> Genre Textfeld aktualisieren **/
        checked_genre.forEach((k,v)->v.addListener((ob,ov,nv)->{
        	if(blocked) return;
        	if(nv.booleanValue()==true) selected.add(checked_genre.inverse().get(ob));
        	else						selected.remove(checked_genre.inverse().get(ob));
        	
        	t_check.setText(Filmverwaltung.getMaxGenre()-selected.size()+"");	// Anzeige der noch übrigen Anzahl abzuhakender Genres
        	if(selected.size()>Filmverwaltung.getMaxGenre()) {
        		checked_genre.get(selected.get(0)).set(false);
        		return;
        	}
        	
        	tf_genre.setText(null);
        	if(selected.size()==0)	return;
        	
        	List<Genre> sorted = new ArrayList<>(selected);
        	sorted.sort((o1,o2)->o1.compare(o1, o2));
        	StringBuilder sb = new StringBuilder(sorted.get(0).getGenre());
        	for(int i=1; i<sorted.size(); i++)
        		sb.append( ", "+sorted.get(i).getGenre() );
        	tf_genre.setText(sb.toString());
        	tf_genre.selectPositionCaret(sb.toString().length());	//Bei zu viel Text verschiebt sich der Text sonst zu weit nach rechts
        }));
        
        
        /** Table Mitwirkende **/
        table.setEditable(true);
        t_name.setCellValueFactory(		data->data.getValue().getPerson().getNameProperty()		);
        t_vorname.setCellValueFactory(	data->data.getValue().getPerson().getVornameProperty()	);
        t_rolle.setCellValueFactory(	data->data.getValue().getRolle().getObservable()			);
        t_confirm1.setCellValueFactory(	data->confirmed.get(data.getValue())[0]			);
        t_confirm2.setCellValueFactory(	data->confirmed.get(data.getValue())[1]			);
        
        t_name.setCellFactory(TextFieldTableCell.forTableColumn());
        t_vorname.setCellFactory(TextFieldTableCell.forTableColumn());
       // t_rolle.setCellFactory( ChoiceBoxTableCell.forTableColumn(Personenverwaltung.getRollen()) );
        t_confirm1.setCellFactory(CheckBoxTableCell.forTableColumn(t_confirm1));
        t_confirm2.setCellFactory(CheckBoxTableCell.forTableColumn(t_confirm2));
        
        //Konvertiere Rolle zu String für t_rolle spalte
        StringConverter<Rolle> stconv = new StringConverter<Rolle>() {
			@Override
			public String toString(Rolle object) {
				return object.getRolle();
			}
			@Override
			public Rolle fromString(String string) {return null;}
		};
        t_rolle.setCellFactory(ComboBoxTableCell.forTableColumn(stconv, Personenverwaltung.getRollen()));
        
        
        /**Changes**/
        t_vorname.setOnEditCommit(	data->{
        	String val = data.getNewValue();
        	if(val.length()>Personenverwaltung.getMaxVorname())
        		val = val.substring(0, Personenverwaltung.getMaxVorname());	//Wenn Eingabe zu Lang abschneiden.
        	data.getRowValue().getPerson().setVorname(val);
        	confirmed.get(data.getRowValue())[0].set(true);
        	changes[1]=true;
        });
        t_name.setOnEditCommit(	data->{
        	String val = data.getNewValue();
        	if(val.length()>Personenverwaltung.getMaxName())
        		val = val.substring(0, Personenverwaltung.getMaxName());
        	data.getRowValue().getPerson().setName(val);
        	confirmed.get(data.getRowValue())[0].set(true);
        	changes[1]=true;
        });
        t_rolle.setOnEditCommit(	data->{
        	data.getRowValue().setRolle(data.getNewValue());
        	confirmed.get(data.getRowValue())[0].set(true);
        	changes[1]=true;
        });
        
        tf_bewertung.textProperty().addListener((ob,ov,nv)-> changes[0]=true);
        tf_dauer.textProperty().addListener(	(ob,ov,nv)-> changes[0]=true);
        tf_genre.textProperty().addListener(	(ob,ov,nv)-> changes[0]=true);
        tf_jahr.textProperty().addListener(		(ob,ov,nv)-> changes[0]=true);
        tf_titel.textProperty().addListener(	(ob,ov,nv)-> changes[0]=true);    
        
    }

    private void addPerson() {
    	PersonMitRolle per = new Person(-1, "Neue Person", "Neue Person", Personenverwaltung.getRollen().get(0)).getPersonenMitRolle().get(0);
    	personen.add(per);
    	confirmed.put(per, new SimpleBooleanProperty[]{new SimpleBooleanProperty(true), new SimpleBooleanProperty(false)});
    	changes[1] = true;
    }
    
    private void commit() {
    	System.out.println(changes[0]+"  "+changes[1]);
    	
		FilteredList<PersonMitRolle> update = personen.filtered(per->confirmed.get(per)[0].get());
    	FilteredList<PersonMitRolle> delete = personen.filtered(per->confirmed.get(per)[1].get());
    	if(!changes[0]&& !changes[1] && delete.size()==0)
    		return;
    	
    	try(Connection con = DB_Manager.getCon()) {
    		//Wenn Filmdaten geändert
    		if(changes[0]) {
            	checkEingaben();
    			if(film==null) {
    				film = fvw.addFilm(tf_titel.getText(), selected, tf_dauer.getValue(), tf_jahr.getValue(), con );
    				pvw = film.getPvw();
    				if(filmliste!=null) filmliste.addFilm(film);
    			}else		
    				fvw.updateFilm(tf_titel.getText(), selected, tf_dauer.getValue(),  tf_jahr.getValue(), film, con);
    		}
    		
    		if(changes[1])	pvw.addOrUpdate(update, con);
        	pvw.delete(delete, con);
    		
		} catch (Exception e) {
			Alert a = new Alert(AlertType.ERROR);
			a.setTitle(e.getClass().getSimpleName());
			a.setContentText(e.getMessage());
			a.show();
			e.printStackTrace();
			return;
		}
    	setDisplay();
    	setTable();
    }
    
    private void checkEingaben() throws Exception {
    	if(tf_titel.getLength() < 10)	throw new Exception("Titel zu kurz");
    	if(tf_jahr.getValue()==null)	throw new Exception("Geben sie ein gültiges jahr ein");
    	if(tf_dauer.getValue()==null)   throw new Exception("dauer");
    	if(selected.size()==0) 			throw new Exception("Kein Genre");
    }
    
    private void detail() {
    	if( film == null) {
    		Alert a = new Alert(AlertType.ERROR);
    		a.setTitle("Detailansicht öffnen");
    		a.setHeaderText("Dieser Film existiert nich niht in der Datenbank");
    		a.setContentText("Bitte Speichern sie vorher den Film ab");
    		a.show();
    		return;
    	}
    	
    	if( changes[0]==true || changes[1]==true ) {
    		Alert a = new Alert(AlertType.CONFIRMATION);
    		a.setTitle("Detailansicht öffnen");
    		a.setHeaderText("Es sind nicht gespeicherte Änderungen vorhanden");
    		a.setContentText("Nicht gespeicherte Änderungen gehen verloren. Möchten sie trotzdem zur Detailansicht wechseln?");
    		a.show();
    		a.setOnCloseRequest(ev->{
    			if(a.getResult().getButtonData().equals(ButtonData.OK_DONE)) 
    				openDetail();
    		});
    	}else
    		openDetail();
    }
    
    private void openDetail() {
    	try {
			FensterManager.setDialog( FensterManager.getDetail(film) );
		} catch (SQLException e) {
			Alert a2 = new Alert(AlertType.ERROR);
			a2.setTitle(e.getClass().getSimpleName());
			a2.setContentText(e.getMessage());
			a2.show();
			e.printStackTrace();
		}
    }


}
