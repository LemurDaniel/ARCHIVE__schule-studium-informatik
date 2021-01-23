package gui.controller;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.input.KeyEvent;
import verwaltung.DB_Manager;
import verwaltung.Nutzer;
import verwaltung.Nutzer.Rechte;
import verwaltung.entitaeten.Film;
import verwaltung.entitaeten.Genre;
import verwaltung.entitaeten.Person.PersonMitRolle;
import verwaltung.entitaeten.Rezension;
import verwaltung.verwaltungen.Filmverwaltung;
import verwaltung.verwaltungen.unterverwaltungen.Personenverwaltung;
import verwaltung.verwaltungen.unterverwaltungen.Rezensionenverwaltung;

public class DetailCtrl {

	private Film film;
	private int nid= Nutzer.getNutzer().getId();
	private Rechte rechte = Nutzer.getNutzer().getRechte();
	
	private Map<Genre, BooleanProperty> checked_genre = new HashMap<>();
	
	private Rezension displayed;
	private Rezensionenverwaltung rvw;
	private Personenverwaltung pvw;
	
	private long lastMouseClick;

	public void setFilm(Film film) throws SQLException {
        accordion.setExpandedPane(tp_allg);
        tab_pane.getSelectionModel().select(tab_allg);
        tab_pane.requestFocus();
        aktualisiereRechte();
        
		if(this.film!=null && this.film.equals(film))
			return;
        
		rvw = film.getRvw();
		pvw = film.getPvw();
		if(!rvw.isLoaded() || !pvw.isLoaded()) {
			try(Connection con = DB_Manager.getCon()){
				rvw.load(con);
				pvw.load(con);
			}
		}
		
        table.setItems(FXCollections.observableArrayList(pvw.getPersonenMitRollen()));
        table1.setItems(rvw.getList());
		
        tf_titel.setText(film.getTitel());
        tf_genre.setText(film.getGenreStringProperty().get());
        tf_bewertung.setText(film.getBewertung()+"");
        tf_dauer.setText(film.getDauer()+" Minuten");
        tf_jahr.setText(film.getErscheinungsjahr()+""); 
        
        if(this.film!=null)
        	this.film.getGenres().forEach(g->checked_genre.get(g).set(false));
        film.getGenres().forEach(g->checked_genre.get(g).set(true));
        table_genre.getSelectionModel().clearSelection();
        
        tp_mit.setDisable( pvw.getList().size()==0 );
        tp_rez.setDisable( rvw.getList().size()==0 );
       
        setEdit(false);
		cb_r.setDisable(true);
		displayRezension();			//Wenn Film gewechselt display aktualisieren 
		// Wenn keine Rechte zum schreiben einer Review und keine bereits geschreiben vorhanden
        if(!rechte.isReviewWrite() && rvw.getRezensionVonNutzer(nid)==null)
        	cb_r.getSelectionModel().select(0);
        else
        	cb_r.getSelectionModel().select(1);
        
        this.film = film;
	}

	private void aktualisiereRechte() {
		nid = Nutzer.getNutzer().getId();
		rechte = Nutzer.getNutzer().getRechte();
    	cb_r.getItems().set(1, "Nutzer - "+Nutzer.getNutzer().getName());
    	tp_rezd.setDisable( !rechte.isReviewRead() );
    	if(!rechte.isReviewWrite()) cb_r.getSelectionModel().select(0);
//    });
	}
    /** Allgemein **/
    @FXML
    private Accordion accordion;  
	
    @FXML
    private TitledPane tp_allg;  
    
    @FXML
    private Tab tab_allg;
    
    @FXML
    private TabPane tab_pane;

    @FXML 
    private TextField tf_titel;  

    @FXML
    private TextField tf_genre;  

    @FXML
    private TextField tf_dauer;  

    @FXML
    private TextField tf_bewertung;  

    @FXML
    private TextField tf_jahr;  
    
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
    private TableColumn<PersonMitRolle, String> t_vorname;  

    @FXML
    private TableColumn<PersonMitRolle, String> t_name;  

    @FXML
    private TableColumn<PersonMitRolle, String> t_rolle;  
    
    /** Rezension **/
    @FXML
    private TitledPane tp_rez;  

    @FXML
    private TableView<Rezension> table1;  

    @FXML
    private TableColumn<Rezension, String> t_ersteller;  

    @FXML
    private TableColumn<Rezension, Number> t_bwt;  

    @FXML
    private TableColumn<Rezension, String> t_titel;  
    
    /** Rezension Detail**/
    @FXML
    private TitledPane tp_rezd;  

    @FXML
    private ChoiceBox<String> cb_r;  

    @FXML
    private ToggleButton tbtn_r;

    @FXML
    private TextField tf_rtitel;
    
    @FXML
    private Slider s_bwt;

    @FXML
    private TextArea ta_rtext;

    @FXML 
    private Button btn_r;
    
    @FXML
    private Label lbl_r;
    
    @FXML
    void add_rez(ActionEvent event) {
          Alert a = new Alert(AlertType.INFORMATION);
          boolean exists = rvw.exists(displayed.getId());
          if(exists) {
        	  a.setTitle("Rezension - Update");
        	  a.setContentText("Rezension erfolgreich upgedatet");
          }else {
        	  a.setTitle("Rezension - Erstellen");
        	  a.setContentText("Rezension erfolgreich Erstellt");
          }
          
          try {
        	if(exists)	rvw.updateRezension(tf_rtitel.getText(), ta_rtext.getText(), (int)s_bwt.getValue(), displayed.getId());
        	else		rvw.addRezension(tf_rtitel.getText(), ta_rtext.getText(), (int)s_bwt.getValue(), nid);
        	displayRezension();
          	setEdit(false);
            tf_bewertung.setText(film.getBewertung()+"");
            tp_rez.setDisable(false);
          }catch(Exception e) {
        	  a.setAlertType(AlertType.ERROR);
        	  a.setContentText(e.getMessage());
          }
          a.show();
    }
    
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        
        // Wenn nicht review Write -> keine eigene Review auswählbar
        cb_r.disabledProperty().addListener((ob, ov, nv)->{
        	if(!rechte.isReviewWrite()  && !nv && rvw.getRezensionVonNutzer(nid)==null) {
        		cb_r.setDisable(true);
        	}
        });      
        // Wenn nicht review Read Rez Detail disabled
        tp_rezd.disabledProperty().addListener((ob, ov, nv)->{
        	if(!rechte.isReviewRead() && nv==false) tp_rezd.setDisable(true);
        });

        /** Allg **/
        
        tf_titel.setEditable(false);
        tf_bewertung.setEditable(false);
        tf_dauer.setEditable(false);
        tf_genre.setEditable(false);
        tf_jahr.setEditable(false);
        
        /** Genre **/
        table_genre.setEditable(false);
        table_genre.setItems(FXCollections.observableArrayList(Filmverwaltung.getGenres()));
        table_genre.getSelectionModel().selectedItemProperty().addListener( (ob,ov,nv)->{
        	if(nv!=null)
        		ta_genre.setText(nv.getText());
        	else
        		ta_genre.setText(null);
        });
        
        t_check.setCellFactory(CheckBoxTableCell.forTableColumn(t_check));
        
      	Filmverwaltung.getGenres().forEach(g->checked_genre.put(g, new SimpleBooleanProperty(false)));
        t_genre.setCellValueFactory(data-> new SimpleStringProperty(data.getValue().getGenre()));
        t_check.setCellValueFactory(data-> checked_genre.get(data.getValue()));
        
        /** Mitwirkende  **/
        
        t_name.setCellValueFactory(		data->data.getValue().getPerson().getNameProperty()		);
        t_vorname.setCellValueFactory(	data->data.getValue().getPerson().getVornameProperty()	);
        t_rolle.setCellValueFactory(	data->data.getValue().getRolle()				);     
        
        /** Reze Detail **/
        cb_r.setItems(FXCollections.observableArrayList());
        cb_r.getItems().add("Rezensions-Verfasser"); // Nur ein Platzhalter
        cb_r.getItems().add("Nutzer - "+Nutzer.getNutzer().getName());
        cb_r.getSelectionModel().selectedIndexProperty().addListener((ob, ov, nv)->displayRezension()); 
        

        ta_rtext.setWrapText(true);       
        ta_rtext.setPromptText("Rezension hier einfügen");
        tf_rtitel.setPromptText("Titel hier einfügen");
        s_bwt.setMax(10);
        s_bwt.setMin(0);
        s_bwt.setMajorTickUnit(1);
        s_bwt.setMinorTickCount(0);
        s_bwt.setShowTickLabels(true);
        s_bwt.setShowTickMarks(true);
        s_bwt.setSnapToTicks(true);
        
        lbl_r.setText(Rezensionenverwaltung.getMaxInhalt()+"");
  
        tbtn_r.setOnAction(ev->{
        	if(!tbtn_r.isSelected()) setDisplay(); // Bei wechsel von write auf read reset display
        	setEdit(tbtn_r.isSelected());
        });
  
        tf_rtitel.addEventFilter(KeyEvent.KEY_TYPED, ev->{
        	if(!tf_rtitel.isEditable()) return;
        	if(tf_rtitel.getLength() >= Rezensionenverwaltung.getMaxTitel()) ev.consume();
        });
        ta_rtext.addEventFilter(KeyEvent.KEY_TYPED, ev->{
        	if(!ta_rtext.isEditable()) return;
        	if(ta_rtext.getLength() >= Rezensionenverwaltung.getMaxInhalt()) ev.consume();
        	lbl_r.setText(Rezensionenverwaltung.getMaxInhalt()-ta_rtext.getLength()+"");
        });
        
        t_ersteller.setCellValueFactory(data->data.getValue().getVerfasser());
        t_bwt.setCellValueFactory(data->data.getValue().getBewertung());
        t_titel.setCellValueFactory(data->data.getValue().getTitel());      
        
        table1.getSelectionModel().selectedItemProperty().addListener((ob, ov, newValue)->{      	
        	// Wenn ausgewählter Rez == Nutzerrez dann nur eine Option anzeigen
        	Rezension r = rvw.getRezensionVonNutzer(nid);
        	if(newValue==null || r!=null && newValue.getId()==r.getId()) {
        		cb_r.setDisable(true);
        		cb_r.getSelectionModel().select(1);
        	}else {
        		int i = cb_r.getSelectionModel().getSelectedIndex();
            	cb_r.getItems().set(0, newValue.getVerfasser().get());	// Optionsname = verfasser name
            	cb_r.getSelectionModel().select(i);	// Bei änderungen wird select reseted -> merkt sich was selected wurde
            	cb_r.setDisable(false);
        	}   		
        });
        
        table1.setOnMouseClicked(ev->{
        	//Double Click
        	long now = System.currentTimeMillis();
        	if(now-lastMouseClick < 200 && table1.getSelectionModel().getSelectedIndex()!=-1) {
        		if(rechte.isReviewRead())	{
        			tp_rezd.setExpanded(true);
        			// Wenn eigene Rez ausgewähl, dann erste Option anzeigen
        			if(table1.getSelectionModel().getSelectedItem().getId()==rvw.getRezensionVonNutzer(nid).getId())
        				cb_r.getSelectionModel().select(1);
        			else
        				cb_r.getSelectionModel().select(0);
        		}
        	}
        	lastMouseClick = now;
        });
    }
   
    // Rezension anzeigen
    private void displayRezension() {
    	if(cb_r.getSelectionModel().selectedIndexProperty().intValue()==1) {
    		// Wenn keine eigen vorhanden , rohe erstellen
    		displayed = rvw.getRezensionVonNutzer(nid);
    		if(displayed==null) displayed = new Rezension(-1, "", "", "", nid, 0);		
    	} else 
    		displayed = table1.getSelectionModel().getSelectedItem();
    		
    	// wenn weder eigene noch ausgewählte vorhanden disable Rez detail
    	if(displayed==null) {
    		tp_rezd.setDisable(true);
    		if(accordion.getExpandedPane().equals(tp_rezd)) accordion.setExpandedPane(tp_allg);
    		return;
    	}else
    		tp_rezd.setDisable(false);
    	
    	if( (displayed.getVerfasserId()==nid && rechte.isReviewWrite()) || rechte.isReviewWriteAll()) {
    		btn_r.setVisible(true);
    		tbtn_r.setVisible(true);
    		// Button anpassen
    		if(!rvw.exists(displayed.getId()))	btn_r.setText("Erstelle Rezension");
        	else btn_r.setText("Update Rezension");
    	} else {
    		btn_r.setVisible(false);
    		tbtn_r.setVisible(false);
    		setEdit(false);
    	}
    	 	
    	setDisplay();
    }
    
    private void setDisplay() {
    	if(displayed == null) displayRezension();
    	ta_rtext.setText(displayed.getInhalt().get());
    	tf_rtitel.setText(displayed.getTitel().get());
    	s_bwt.setValue(displayed.getBewertung().get());
    	lbl_r.setText(Rezensionenverwaltung.getMaxInhalt()-ta_rtext.getLength()+"");
    }
   
    
    // Rezension Editierbarkeit
    private void setEdit(boolean edit) {
    	ta_rtext.setEditable(edit);
    	tf_rtitel.setEditable(edit);
    	btn_r.setDisable(!edit);
    	s_bwt.setDisable(!edit);
    	tbtn_r.setText(edit? "Editieren":"Lesen");
    	tbtn_r.setSelected(edit);
    }
}
