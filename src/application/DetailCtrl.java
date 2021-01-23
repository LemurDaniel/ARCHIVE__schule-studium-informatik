package application;


import java.io.IOException;

/**
 * Sample Skeleton for 'Detail.fxml' Controller Class
 */

import bla.Film;
import bla.Nutzer;
import bla.Person;
import bla.Personenverwaltung;
import bla.Rezension;
import bla.Rezensionenverwaltung;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
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
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;

public class DetailCtrl {

	private static Stage detail;
	private Film film;
	private int nid = Nutzer.getNutzer().getId();
	private Personenverwaltung pvv = new Personenverwaltung();
	private Rezensionenverwaltung rvv = new Rezensionenverwaltung();
	
	public void setFilm(Film film) throws Exception {
		if(this.film!=null && this.film.getId()==film.getId())	return;
		
		this.film = film;
		pvv.setFilmId(film.getId());
		rvv.setFilmId(film.getId());
		pvv.load();
		rvv.load();
		if(rvv.getRezension(nid)==null)	btn_r.setText("Erstelle Rezension");
		else	btn_r.setText("Update Rezension");
		
    	displayRezension(rvv.getRezension(nid));
        cb_r.getSelectionModel().select(0);
        cb_r.setDisable(true);
		
        tf_titel.setText(film.getTitel().get());
        tf_genre.setText(film.getGenre().get());
        tf_bewertung.setText(film.getBewertung().get()+"");
        tf_dauer.setText(film.getDauer().get()+"");
        tf_jahr.setText(film.getErscheinungsjahr().get()+"");
	}
	
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
          if(rvv.exists(nid)) {
        	  a.setTitle("Rezension - Update");
        	  a.setContentText("Rezension erfolgreich upgedatet");
          }else {
        	  a.setTitle("Rezension - Erstellen");
        	  a.setContentText("Rezension erfolgreich Erstellt");
          }
          
          try {
          	rvv.addRezension(tf_rtitel.getText(), ta_rtext.getText(), (int)s_bwt.getValue(), nid);
          	btn_r.setText("Update Rezension");
          }catch(Exception e) {
        	  a = new Alert(AlertType.ERROR);
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
        
        accordion.setExpandedPane(tp_allg);
        
        /** Reze Detail **/
        cb_r.setItems(FXCollections.observableArrayList());
        cb_r.getItems().add(Nutzer.getNutzer().getName());
        cb_r.getItems().add("");
        cb_r.getSelectionModel().selectedIndexProperty().addListener((ob, ov, nv)->{
        	System.out.println(ov+"   "+nv);
        	if(nv.intValue()==0) {
        		btn_r.setVisible(true);
        		tbtn_r.setVisible(true);
        		displayRezension(rvv.getRezension(nid));
        	} else {
        		btn_r.setVisible(false);
        		tbtn_r.setVisible(false);
        		tbtn_r.setSelected(false);
        		displayRezension(table1.getSelectionModel().getSelectedItem());
        	}
        });
        ta_rtext.setEditable(false);
        ta_rtext.setPromptText("Text hier einfügen");
        tf_rtitel.setEditable(false);
        tf_rtitel.setPromptText("Titel hier einfügen");
        s_bwt.setDisable(true);
        s_bwt.setMax(10);
        s_bwt.setMin(0);
        s_bwt.setMajorTickUnit(2);
        s_bwt.setMinorTickCount(1);
        s_bwt.setShowTickLabels(true);
        s_bwt.setShowTickMarks(true);
        s_bwt.setSnapToTicks(true);
        tbtn_r.setOnAction(ev->{
        	ta_rtext.setEditable(tbtn_r.isSelected());
        	tf_rtitel.setEditable(tbtn_r.isSelected());
        	displayRezension(rvv.getRezension(nid));
        	btn_r.setDisable(!tbtn_r.isSelected());
        	s_bwt.setDisable(!tbtn_r.isSelected());
        	tbtn_r.setText(tbtn_r.isSelected()? "Editieren":"Lesen");
        });
        tbtn_r.setSelected(true);
        tbtn_r.fire();
        
        /**  **/
        
        table.setItems(pvv.getList());
        t_vorname.setCellValueFactory(data->data.getValue().vorname);
        t_name.setCellValueFactory(data->data.getValue().name);
        t_rolle.setCellValueFactory(data->data.getValue().rolle);     
        
        table1.setItems(rvv.getList());
        t_ersteller.setCellValueFactory(data->data.getValue().getVerfasser());
        t_bwt.setCellValueFactory(data->data.getValue().getBewertung());
        t_titel.setCellValueFactory(data->data.getValue().getTitel());      
        
        table1.getSelectionModel().selectedItemProperty().addListener((ob, ov, nv)->{      	
        	// Wenn ausgewählter Rez == Nutzerrez
        	Rezension r = rvv.getRezension(nid);
        	if(r==null || nv.getId()!=r.getId()) {
            	cb_r.getItems().remove(1);  
        		cb_r.getItems().add(nv.getVerfasser().get());
        		cb_r.getSelectionModel().select(0);
        		cb_r.setDisable(false);
        	}else 
        		cb_r.setDisable(true);
        	
        	if(cb_r.getSelectionModel().getSelectedIndex()==1) {
        		displayRezension(table1.getSelectionModel().getSelectedItem());
        	}
        });
    }
    
    private void displayRezension(Rezension r) {
    	if(r==null) {
    		ta_rtext.setText(null);
    		tf_rtitel.setText(null);
    		s_bwt.setValue(0);
    	}else {
    		ta_rtext.setText(r.getInhalt().get());
    		tf_rtitel.setText(r.getTitel().get());
    		s_bwt.setValue(r.getBewertung().get());
    	}
    }
}
