package gui.controller;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.ResourceBundle;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.ChoiceBoxTableCell;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyEvent;
import javafx.util.Callback;
import javafx.util.StringConverter;
import verwaltung.entitaeten.Film;
import verwaltung.entitaeten.Genre;
import verwaltung.entitaeten.Person;
import verwaltung.verwaltungen.Filmverwaltung;
import verwaltung.verwaltungen.Personenverwaltung;

public class AddFilmCtrl {

	private Film film;
	private Personenverwaltung pvw;
	
	private ObservableMap<Person, BooleanProperty> confirmedList;
	private ObservableList<Person> personen;

	public void setFilm(Film film) throws SQLException{
		this.film = film;
		if(film != null) {
			pvw = film.getPvw();
			pvw.loadIfnotLoaded();
			setDisplay();
		}else
			pvw = new Personenverwaltung(null);
		
		setTable();
	}
	
	private void setTable() {
		personen = FXCollections.observableArrayList();
		pvw.getList().forEach(per->personen.add(per.getCopy()));
		
		Map<Person, BooleanProperty> map = new HashMap<>();
		personen.forEach(per->map.put(per, new SimpleBooleanProperty(false)));
		confirmedList = FXCollections.observableMap(map);
		table.setItems(personen);
	}
	
	private void setDisplay() {
		tf_titel.setText(film.getTitel().get());
		tf_dauer.setText(film.getDauer().get()+" Minuten");
		tf_jahr.setText(film.getErscheinungsjahr().get()+"");
		tf_genre.setText(film.getGenre().get());
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

    @FXML // fx:id="tp_genre"
    private TitledPane tp_genre; // Value injected by FXMLLoader

    @FXML // fx:id="table_genre"
    private TreeTableView<Genre> table_genre; // Value injected by FXMLLoader

    @FXML // fx:id="t_genre"
    private TreeTableColumn<Genre, String> t_genre; // Value injected by FXMLLoader

    @FXML // fx:id="tp_mit"
    private TitledPane tp_mit; // Value injected by FXMLLoader

    @FXML // fx:id="table"
    private TableView<Person> table; // Value injected by FXMLLoader
    
    @FXML // fx:id="t_confirm"
    private TableColumn<Person, Boolean> t_confirm; // Value injected by FXMLLoader

    @FXML // fx:id="t_vorname"
    private TableColumn<Person, String> t_vorname; // Value injected by FXMLLoader

    @FXML // fx:id="t_name"
    private TableColumn<Person, String> t_name; // Value injected by FXMLLoader

    @FXML // fx:id="t_rolle"
    private TableColumn<Person, String> t_rolle; // Value injected by FXMLLoader
    
    @FXML
    void addP(ActionEvent event) {
    	Person per = new Person(-1, "Neue Person", "Neue Person", "Rolle");
    	personen.add(per);
    	confirmedList.put(per, new SimpleBooleanProperty(false));
    	System.out.println("---------------");
    	confirmedList.forEach((k, v)-> System.out.println(k+"   "+v.get()));
    	System.out.println("----------------------");
    }
    
    @FXML
    void commit(ActionEvent event) {
    	FilteredList<Person> fl = personen.filtered(per->confirmedList.get(per).get());
    	try {
			pvw.addOrUpdate( fl.subList(0, fl.size()) );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }


    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert accordion != null : "fx:id=\"accordion\" was not injected: check your FXML file 'AddFilm.fxml'.";
        assert tp_allg != null : "fx:id=\"tp_allg\" was not injected: check your FXML file 'AddFilm.fxml'.";
        assert tf_titel != null : "fx:id=\"tf_titel\" was not injected: check your FXML file 'AddFilm.fxml'.";
        assert tf_genre != null : "fx:id=\"tf_genre\" was not injected: check your FXML file 'AddFilm.fxml'.";
        assert tf_dauer != null : "fx:id=\"tf_dauer\" was not injected: check your FXML file 'AddFilm.fxml'.";
        assert tf_bewertung != null : "fx:id=\"tf_bewertung\" was not injected: check your FXML file 'AddFilm.fxml'.";
        assert tf_jahr != null : "fx:id=\"tf_jahr\" was not injected: check your FXML file 'AddFilm.fxml'.";
        assert tp_genre != null : "fx:id=\"tp_genre\" was not injected: check your FXML file 'AddFilm.fxml'.";
        assert table_genre != null : "fx:id=\"table_genre\" was not injected: check your FXML file 'AddFilm.fxml'.";
        assert t_genre != null : "fx:id=\"t_genre\" was not injected: check your FXML file 'AddFilm.fxml'.";
        assert tp_mit != null : "fx:id=\"tp_mit\" was not injected: check your FXML file 'AddFilm.fxml'.";
        assert table != null : "fx:id=\"table\" was not injected: check your FXML file 'AddFilm.fxml'.";
        assert t_vorname != null : "fx:id=\"t_vorname\" was not injected: check your FXML file 'AddFilm.fxml'.";
        assert t_name != null : "fx:id=\"t_name\" was not injected: check your FXML file 'AddFilm.fxml'.";
        assert t_rolle != null : "fx:id=\"t_rolle\" was not injected: check your FXML file 'AddFilm.fxml'.";

        accordion.setExpandedPane(tp_allg);
        
        tf_titel.addEventFilter(KeyEvent.KEY_TYPED, ev->{
        	if(tf_titel.getLength() >= Filmverwaltung.getMaxTitel()) ev.consume();
        });
        
        tf_jahr.addEventFilter(KeyEvent.KEY_TYPED, ev->{
        	if( !Character.isDigit(ev.getCharacter().charAt(0)) )	ev.consume();
        	if(tf_jahr.getLength() >= 4) ev.consume();
        });
        
        tf_dauer.addEventFilter(KeyEvent.KEY_TYPED, ev->{
        	if( !Character.isDigit(ev.getCharacter().charAt(0)) || tf_dauer.getLength() >= 3) {
        		ev.consume();
        		return;
        	}
        });     
        tf_dauer.focusedProperty().addListener((ob,ov,newVal)->{
        	if(newVal==false && tf_dauer.getLength()>0)
        		tf_dauer.setText(tf_dauer.getText()+" Minuten");
        	else
        		tf_dauer.setText(tf_dauer.getText().replaceAll("[^0-9]", ""));
        });
        
        //The Horse In Motion (1878)
        tf_jahr.focusedProperty().addListener((ob,ov,newVal)->{
        	if(newVal == true || tf_jahr.getLength()==0) return;
        	int jahr = Integer.parseInt(tf_jahr.getText());
        	if(jahr < 1878 || jahr > LocalDate.now().getYear())	{
        		tf_jahr.setText(null);
        	}
        });
        
        
        tf_bewertung.setDisable(true);
        tf_genre.setDisable(true);
        
        table_genre.setShowRoot(false);
        table_genre.setRoot(new TreeItem<Genre>(new Genre(-1, "")));
        t_genre.setText("Genre");
        
        Filmverwaltung.getGenres().stream().forEach( g->{
        	TreeItem<Genre> item = new TreeItem<>(g);
        	table_genre.getRoot().getChildren().add(item);  	
        	if(g.getSubGenre()!=null)
        		g.getSubGenre().stream().forEach( sg->item.getChildren().add(new TreeItem<Genre>(sg))	);
        });
        t_genre.setCellValueFactory(data-> new SimpleStringProperty(data.getValue().getValue().getGenre()) );
        
        table_genre.getSelectionModel().selectedItemProperty().addListener((ob, ov, newVal)->{
        	System.out.println(newVal);
        	if(newVal!=null) {
        		tf_genre.setText(newVal.getValue().getGenre());
        		t_genre.setText(newVal.getValue().getGenre());
        	}else {
        		t_genre.setText("Genre");
        		tf_genre.setText(null);
        	}
        });
        
       // ObservableList<SimpleBooleanProperty> confirmedList = FXCollections.observableArrayList();
        
        /** Mitwirkende **/
        table.setEditable(true);
        t_name.setCellValueFactory(data->data.getValue().getName());
        t_vorname.setCellValueFactory(data->data.getValue().getVorname());
        t_rolle.setCellValueFactory(data->data.getValue().getRolle());
        t_confirm.setCellValueFactory(data-> confirmedList.get(data.getValue()) );
        
        t_name.setCellFactory(TextFieldTableCell.forTableColumn());
        t_vorname.setCellFactory(TextFieldTableCell.forTableColumn());
       // t_rolle.setCellFactory( ChoiceBoxTableCell.forTableColumn(Personenverwaltung.getRollen()) );
        t_rolle.setCellFactory(ComboBoxTableCell.forTableColumn(Personenverwaltung.getRollen()));
        t_confirm.setCellFactory(CheckBoxTableCell.forTableColumn(t_confirm));

    }
    
    
}
