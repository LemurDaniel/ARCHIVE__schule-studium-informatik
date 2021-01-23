package gui.controller;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.UnaryOperator;

import fxControls.CustomTextField;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.stage.Modality;
import verwaltung.DB_Manager;
import verwaltung.Nutzer;
import verwaltung.Nutzer.Rechte;
import verwaltung.entitaeten.Film;
import verwaltung.entitaeten.Genre;
import verwaltung.entitaeten.Person;
import verwaltung.entitaeten.Rezension;
import verwaltung.entitaeten.Rolle;
import verwaltung.verwaltungen.Filmverwaltung;
import verwaltung.verwaltungen.Stapelverarbeitung;
import verwaltung.verwaltungen.unterverwaltungen.Personenverwaltung;
import verwaltung.verwaltungen.unterverwaltungen.Rezensionenverwaltung;

public class DetailCtrl {

	private Film film;
	private Stapelverarbeitung<Film> stpv;
	private int nid= Nutzer.getNutzer().getId();
	private Rechte rechte = Nutzer.getNutzer().getRechte();
	
	private Map<Genre, BooleanProperty> checked_genre = new HashMap<>();
	
	private Rezension angezeigt;
	private Rezensionenverwaltung rvw;
	private Personenverwaltung pvw;
	
	private long lastMouseClick;

	public void setFilm(Film film, Stapelverarbeitung<Film> stpv) throws SQLException {
        accordion.setExpandedPane(tp_allg);
        tab_pane.getSelectionModel().select(tab_allg);
        tab_pane.requestFocus();
        aktualisiereNutzer();
        
		rvw = film.getRvw();
		pvw = film.getPvw();
		if(film.getId()!=-1 && (!rvw.isGeladen() || !pvw.isGeladen())) {
			try(Connection con = DB_Manager.getCon()){
				rvw.lade(con);
				pvw.lade(con);
			}
		}
		
        table.setItems(pvw.getObList());
        table1.setItems(rvw.getObList());
		
        tf_titel.setText(film.getTitel());
        tf_genre.setText(film.getGenreStringProperty().get());
        tf_bewertung.setText(film.getBwtStringProperty().get());
        tf_dauer.setText(film.getDauerStringProperty().get());
        tf_jahr.setText(film.getErscheinungsjahr()+""); 
        
        checked_genre.forEach((k, v)->v.set(false));
        film.getGenres().forEach(g->checked_genre.get(g).set(true));
        table_genre.getSelectionModel().clearSelection();
        t_check.setText(film.getGenresAnzahl()+"");
        t_genre.setText("Genre ("+checked_genre.size()+")");
        
        tp_mit.setDisable( pvw.getList().size()==0 );
        tp_rez.setDisable( rvw.getList().size()==0 );
       
		cb_r.setDisable(true);
		setRezension();			//Wenn Film gewechselt display aktualisieren 
		// Wenn keine Rechte zum schreiben einer Review und keine bereits geschreiben vorhanden
        if(!rechte.isReviewWrite() && rvw.getRezensionVonNutzer(nid)==null)		cb_r.getSelectionModel().select(0);
        else																	cb_r.getSelectionModel().select(1);
        
        this.film = film;
        this.stpv = stpv;
	}

	public void aktualisiereNutzer() {
		nid = Nutzer.getNutzer().getId();
		rechte = Nutzer.getNutzer().getRechte();
		int temp = cb_r.getSelectionModel().getSelectedIndex();
    	cb_r.getItems().set(1, "Nutzer - "+Nutzer.getNutzer().getName());
    	cb_r.getSelectionModel().select(temp);
    	tp_rezd.setDisable( !rechte.isReviewRead() );
//    });
	}
    /** Allgemein **/
    @FXML
    private Accordion accordion;  
	
    @FXML
    private TitledPane tp_allg;  
    
    @FXML
    private Button btn_mod;
    
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
    private TableView<Person> table;  

    @FXML
    private TableColumn<Person, String> t_vorname;  

    @FXML
    private TableColumn<Person, String> t_name;  

    @FXML
    private TableColumn<Person, Rolle> t_rolle;  
    
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
    private FlowPane fp_rezTitel;
    private StringTextField tf_rtitel;
    
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
                  
          if(angezeigt.getId()!=-1) {
        	  a.setTitle("Rezension - Update");
        	  a.setContentText("Rezension erfolgreich upgedatet");   
        	  angezeigt.setBewertung((int)s_bwt.getValue());
              angezeigt.setInhalt(ta_rtext.getText());
              angezeigt.setTitel(tf_rtitel.getText());
        	  rvw.updateEntitaet(angezeigt);
          }else {
        	  a.setTitle("Rezension - Erstellen");
        	  a.setContentText("Rezension erfolgreich Erstellt");
              angezeigt.backup();
              angezeigt.setBewertung((int)s_bwt.getValue());
              angezeigt.setInhalt(ta_rtext.getText());
              angezeigt.setTitel(tf_rtitel.getText());
        	  rvw.addEntitaet(angezeigt);
          }
          
         try(Connection con = DB_Manager.getCon()){
        	 rvw.save(con);
         }catch(Exception e) {
        	 Alert err = new Alert(AlertType.ERROR);
        	 err.setContentText(e.getMessage());
        	 err.initOwner(FensterManager.getDialog());
        	 err.initModality(Modality.APPLICATION_MODAL);
        	 err.show();
        	 return;
          }

        setEdit(false);
        tf_bewertung.setText(film.getBwtStringProperty().get());
        tp_rez.setDisable(false);
        System.out.println(rvw.existiert(Nutzer.getNutzer().getId()));
        setRezension();
        a.show();
    }
    
    @FXML
    void initialize() {
        
        // Wenn nicht review Write -> keine eigene Review auswählbar
        cb_r.disabledProperty().addListener((ob, ov, nv)->{
        	if(!rechte.isReviewWrite()  && !nv && rvw.getRezensionVonNutzer(nid)==null) {
        		cb_r.setDisable(true);
        	}
        });      
        // Wenn nicht review Read -> Rez-Detail disabled
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
        	if(nv!=null)	ta_genre.setText(nv.getText());
        	else			ta_genre.setText(null);
        });
        
        t_check.setCellFactory(CheckBoxTableCell.forTableColumn(t_check));
        
      	Filmverwaltung.getGenres().forEach(g->checked_genre.put(g, new SimpleBooleanProperty(false)));
        t_genre.setCellValueFactory(data-> new SimpleStringProperty(data.getValue().getGenre()));
        t_check.setCellValueFactory(data-> checked_genre.get(data.getValue()));
        
        /** Mitwirkende  Tabelle **/       
        t_name.setCellValueFactory(		data->data.getValue().getNameProperty()				);
        t_vorname.setCellValueFactory(	data->data.getValue().getVornameProperty()			);
        t_rolle.setCellValueFactory(	data->data.getValue().getRolle().getObservable()	);     
        
        /** Reze Detail **/
        cb_r.setItems(FXCollections.observableArrayList());
        cb_r.getItems().add("Rezensions-Verfasser"); // Choice Box 0 -> Ausgewählte Rezension
        cb_r.getItems().add("Nutzer - "+Nutzer.getNutzer().getName());		//Choice Box 1 -> Nutzer Rezension
        cb_r.getSelectionModel().selectedIndexProperty().addListener((ob, ov, nv)->setRezension()); 
        
        
        tf_rtitel = new StringTextField(Rezensionenverwaltung.getMaxTitel());
        fp_rezTitel.getChildren().add(tf_rtitel);
        tf_rtitel.setPromptText("Titel hier einfügen");
        s_bwt.setMax(10);
        s_bwt.setMin(0);
        s_bwt.setMajorTickUnit(1);
        s_bwt.setMinorTickCount(0);
        s_bwt.setShowTickLabels(true);
        s_bwt.setShowTickMarks(true);
        s_bwt.setSnapToTicks(true);
  
        tbtn_r.setOnAction(ev->{
        	if(!tbtn_r.isSelected()) setAnzeige(); // Bei wechsel von Schreiben auf Lesen: Zurücksetzen ungespeicherter Änderungen
        	setEdit(tbtn_r.isSelected());
        });
  
        
        lbl_r.setText(Rezensionenverwaltung.getMaxInhalt()+"");
        ta_rtext.setWrapText(true);       
        ta_rtext.setPromptText("Rezension hier einfügen");
        ta_rtext.setTextFormatter(new TextFormatter<>( (UnaryOperator<Change>) change->{
        	change = CustomTextField.getMaxLenFilter(Rezensionenverwaltung.getMaxInhalt()).apply(change);		//Maximale TextArea-Länge einhalten
        	lbl_r.setText(Rezensionenverwaltung.getMaxInhalt()-change.getControlNewText().length()+"");			//Label mit noch verfügbaren Zeichen
        	return change;
        }));

        /** Tabelle mit Rezensionen **/ 
        t_ersteller.setCellValueFactory(data->	data.getValue().getVerfasserProperty());
        t_bwt.setCellValueFactory(		data->	data.getValue().getBewertungProperty());
        t_titel.setCellValueFactory(	data->	data.getValue().getTitelProperty());      
        
        table1.getSelectionModel().selectedItemProperty().addListener(this::rezTableListener);      
        table1.setOnMouseClicked(this::onMouseClicked);      
        btn_mod.setOnAction(this::openAddFilm);
    }
    private void rezTableListener(ObservableValue<? extends Rezension> ob, Rezension oldValue, Rezension newValue) {
    	// Wenn ausgewählter Rez == Nutzerrez dann nur eine Option anzeigen
    	Rezension r = rvw.getRezensionVonNutzer(nid);
    	if(newValue==null || r!=null && newValue.getId()==r.getId()) {
    		cb_r.setDisable(true);
    		cb_r.getSelectionModel().select(1);
    	}else {
    		int i = cb_r.getSelectionModel().getSelectedIndex();
        	cb_r.getItems().set(0, newValue.getVerfasser());	// Optionsname = verfasser name
        	cb_r.getSelectionModel().select(i);	// Bei änderungen wird select reseted -> merkt sich was selected wurde
        	cb_r.setDisable(false);
    	}   
    }
    
    private void onMouseClicked(MouseEvent event) {
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
    }
    
    private void openAddFilm(ActionEvent event) {
    	try {
			FensterManager.setDialog(FensterManager.getAddFilm(film, stpv));
		} catch (SQLException e) {
			Alert a = new Alert(AlertType.ERROR);
			a.setContentText(e.getMessage());
			a.show();
		}
    }
   
    // Zu anzeigende Rezension auswählen
    private void setRezension() {
    	if(cb_r.getSelectionModel().selectedIndexProperty().intValue()==1) {
    		// Wenn keine eigen vorhanden neue erstellen
    		angezeigt = rvw.getRezensionVonNutzer(nid);
    		if(angezeigt==null) angezeigt = new Rezension(-1, "", "", Nutzer.getNutzer().getName(), nid, 0, film);		
    	} else 
    		angezeigt = table1.getSelectionModel().getSelectedItem();
    		
    	// wenn weder eigene noch ausgewählte vorhanden disable Rez detail
    	if(angezeigt==null) {
    		tp_rezd.setDisable(true);
    		setEdit(false);
    		if(accordion.getExpandedPane().equals(tp_rezd)) accordion.setExpandedPane(tp_allg);
    		return;
    	}else
    		tp_rezd.setDisable(false);
    	
    	//Schreibrechte auf gewählte Rezension überprüfen
    	if( (angezeigt.getVerfasserId()==nid && rechte.isReviewWrite()) || rechte.isReviewWriteAll()) {
    		btn_r.setVisible(true);
    		tbtn_r.setVisible(true);
    		// Button anpassen
    		if(!rvw.existiert(angezeigt.getId()))	btn_r.setText("Erstelle Rezension");
        	else 									btn_r.setText("Update Rezension");
    	} else {
    		btn_r.setVisible(false);
    		tbtn_r.setVisible(false);
    		setEdit(false);
    	}
    	 	
    	setAnzeige();
    }
    
    // Rezension anzeigen
    private void setAnzeige() {
    	if(angezeigt == null) setRezension();
    	ta_rtext.setText(angezeigt.getInhalt());
    	tf_rtitel.setDefaultSupplier(()->angezeigt.getTitel());
    	s_bwt.setValue(angezeigt.getBewertung());
    	setEdit(false);
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
