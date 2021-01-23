package gui.controller;


import java.sql.SQLException;

import Verwaltungen.entitaeten.Film;
import Verwaltungen.entitaeten.Nutzer;
import Verwaltungen.entitaeten.Person;
import Verwaltungen.entitaeten.Rezension;
import Verwaltungen.entitaeten.Nutzer.Rechte;
import Verwaltungen.verwaltungen.Personenverwaltung;
import Verwaltungen.verwaltungen.Rezensionenverwaltung;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleButton;

public class DetailCtrl {

	private Film film;
	private int nid= Nutzer.getNutzer().getId();
	private Rechte rechte = Nutzer.getNutzer().getRechte();
	
	private Rezension displayed;
	private Personenverwaltung pvw;
	private Rezensionenverwaltung rvw;
	
	private long lastMouseClick;
	
	public void setFilm(Film film) throws SQLException {
		this.film = film;
		pvw = film.getPvw();
		rvw = film.getRvw();
		rvw.loadIfnotLoaded();
		pvw.loadIfnotLoaded();

        table.setItems(pvw.getList());
        table1.setItems(rvw.getList());
		
        tf_titel.setText(film.getTitel().get());
        tf_genre.setText(film.getGenre().get());
        tf_bewertung.setText(film.getBewertung().get()+"");
        tf_dauer.setText(film.getDauer().get()+"");
        tf_jahr.setText(film.getErscheinungsjahr().get()+""); 
        
        accordion.setExpandedPane(tp_allg);
        
		cb_r.setDisable(true);
		// Wenn keine Rechte zum schreiben einer Review und keine bereits geschreiben vorhanden
        if(!rechte.isReviewWrite() && rvw.getRezensionVonNutzer(nid)==null)
        	cb_r.getSelectionModel().select(0);
        else
        	cb_r.getSelectionModel().select(1);
	}
	
    /** Allgemein **/
    @FXML // fx:id="accordion"
    private Accordion accordion; // Value injected by FXMLLoader
	
    @FXML // fx:id="tp_allg"
    private TitledPane tp_allg; // Value injected by FXMLLoader

    @FXML // fx:id="tf_titel"
    private TextField tf_titel; // Value injected by FXMLLoader

    @FXML // fx:id="tf_genre"
    private TextField tf_genre; // Value injected by FXMLLoader

    @FXML // fx:id="tf_dauer"
    private TextField tf_dauer; // Value injected by FXMLLoader

    @FXML // fx:id="tf_bewertung"
    private TextField tf_bewertung; // Value injected by FXMLLoader

    @FXML // fx:id="tf_jahr"
    private TextField tf_jahr; // Value injected by FXMLLoader

    /** Mitwirkende **/
    @FXML // fx:id="tp_mit"
    private TitledPane tp_mit; // Value injected by FXMLLoader

    @FXML // fx:id="table"
    private TableView<Person> table; // Value injected by FXMLLoader

    @FXML // fx:id="t_vorname"
    private TableColumn<Person, String> t_vorname; // Value injected by FXMLLoader

    @FXML // fx:id="t_name"
    private TableColumn<Person, String> t_name; // Value injected by FXMLLoader

    @FXML // fx:id="t_rolle"
    private TableColumn<Person, String> t_rolle; // Value injected by FXMLLoader
    
    /** Rezension **/
    @FXML // fx:id="tp_rez"
    private TitledPane tp_rez; // Value injected by FXMLLoader

    @FXML // fx:id="table1"
    private TableView<Rezension> table1; // Value injected by FXMLLoader

    @FXML // fx:id="t_ersteller"
    private TableColumn<Rezension, String> t_ersteller; // Value injected by FXMLLoader

    @FXML // fx:id="t_bwt"
    private TableColumn<Rezension, Number> t_bwt; // Value injected by FXMLLoader

    @FXML // fx:id="t_titel"
    private TableColumn<Rezension, String> t_titel; // Value injected by FXMLLoader
    
    /** Rezension Detail**/
    @FXML // fx:id="tp_rezd"
    private TitledPane tp_rezd; // Value injected by FXMLLoader

    @FXML // fx:id="cb_r"
    private ChoiceBox<String> cb_r; // Value injected by FXMLLoader

    @FXML // fx:id="tbtn_r"
    private ToggleButton tbtn_r; // Value injected by FXMLLoader

    @FXML // fx:id="tf_rtile"
    private TextField tf_rtitel; // Value injected by FXMLLoader
    
    @FXML // fx:id="s_bwt"
    private Slider s_bwt; // Value injected by FXMLLoader

    @FXML // fx:id="ta_rtext"
    private TextArea ta_rtext; // Value injected by FXMLLoader

    @FXML // fx:id="btn_r"
    private Button btn_r; // Value injected by 
    
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
          	setEdit(false);
            tf_bewertung.setText(film.getBewertung().get()+"");
          }catch(Exception e) {
        	  a.setAlertType(AlertType.ERROR);
        	  a.setContentText(e.getMessage());
          }
          a.show();
    }
    
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert accordion != null : "fx:id=\"accordion\" was not injected: check your FXML file 'Detail.fxml'.";
        assert tp_allg != null : "fx:id=\"tp_allg\" was not injected: check your FXML file 'Detail.fxml'.";
        assert tf_titel != null : "fx:id=\"tf_titel\" was not injected: check your FXML file 'Detail.fxml'.";
        assert tf_genre != null : "fx:id=\"tf_genre\" was not injected: check your FXML file 'Detail.fxml'.";
        assert tf_dauer != null : "fx:id=\"tf_dauer\" was not injected: check your FXML file 'Detail.fxml'.";
        assert tf_bewertung != null : "fx:id=\"tf_bewertung\" was not injected: check your FXML file 'Detail.fxml'.";
        assert tf_jahr != null : "fx:id=\"tf_jahr\" was not injected: check your FXML file 'Detail.fxml'.";
        assert tp_mit != null : "fx:id=\"tp_mit\" was not injected: check your FXML file 'Detail.fxml'.";
        assert table != null : "fx:id=\"table\" was not injected: check your FXML file 'Detail.fxml'.";
        assert t_vorname != null : "fx:id=\"t_vorname\" was not injected: check your FXML file 'Detail.fxml'.";
        assert t_name != null : "fx:id=\"t_name\" was not injected: check your FXML file 'Detail.fxml'.";
        assert t_rolle != null : "fx:id=\"t_rolle\" was not injected: check your FXML file 'Detail.fxml'.";
        assert table1 != null : "fx:id=\"table1\" was not injected: check your FXML file 'Detail.fxml'.";
        assert t_ersteller != null : "fx:id=\"t_ersteller\" was not injected: check your FXML file 'Detail.fxml'.";
        assert t_bwt != null : "fx:id=\"t_bwt\" was not injected: check your FXML file 'Detail.fxml'.";
        assert t_titel != null : "fx:id=\"t_titel\" was not injected: check your FXML file 'Detail.fxml'.";
        assert tp_rezd != null : "fx:id=\"tp_rezd\" was not injected: check your FXML file 'Detail.fxml'.";
        assert cb_r != null : "fx:id=\"cb_r\" was not injected: check your FXML file 'Detail.fxml'.";
        assert tbtn_r != null : "fx:id=\"tbtn_r\" was not injected: check your FXML file 'Detail.fxml'.";
        assert tf_rtitel != null : "fx:id=\"tf_rtile\" was not injected: check your FXML file 'Detail.fxml'.";
        assert s_bwt != null : "fx:id=\"s_bwt\" was not injected: check your FXML file 'Detail.fxml'.";
        assert ta_rtext != null : "fx:id=\"ta_rtext\" was not injected: check your FXML file 'Detail.fxml'.";
        assert btn_r != null : "fx:id=\"btn_r\" was not injected: check your FXML file 'Detail.fxml'.";
       
        /** Rechte **/
        if(!rechte.isReviewRead()) tp_rezd.setDisable(true);
        
        Nutzer.getNutzer().angemeldetProperty().addListener((ob, ov, nv)->{
        	nid = Nutzer.getNutzer().getId();
        	cb_r.getItems().set(1, "Nutzer - "+Nutzer.getNutzer().getName());
        	if(!rechte.isReviewRead()) 	tp_rezd.setDisable(true);
        	if(!rechte.isReviewWrite()) cb_r.getSelectionModel().select(0);

        });
        
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

        /** Rechte **/
        
        /** Reze Detail **/
        cb_r.setItems(FXCollections.observableArrayList());
        cb_r.getItems().add("Rezensions-Verfasser"); // Nur ein Platzhalter
        cb_r.getItems().add("Nutzer - "+Nutzer.getNutzer().getName());
        cb_r.getSelectionModel().selectedIndexProperty().addListener((ob, ov, nv)->displayRezension()); 
        
        ta_rtext.setPromptText("Rezension hier einfügen");
        tf_rtitel.setPromptText("Titel hier einfügen");
        s_bwt.setMax(10);
        s_bwt.setMin(0);
        s_bwt.setMajorTickUnit(1);
        s_bwt.setMinorTickCount(0);
        s_bwt.setShowTickLabels(true);
        s_bwt.setShowTickMarks(true);
        s_bwt.setSnapToTicks(true);
  
        tbtn_r.setOnAction(ev->{
        	if(!tbtn_r.isSelected()) setDisplay(); // Bei wechsel von write auf read reset display
        	setEdit(tbtn_r.isSelected());
        });
  
        /**  **/
        
        t_vorname.setCellValueFactory(data->data.getValue().vorname);
        t_name.setCellValueFactory(data->data.getValue().name);
        t_rolle.setCellValueFactory(data->data.getValue().rolle);     
        
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
        			// Wenn eigene Rez ausgewähl, dann nur eine Option anzeigen
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
