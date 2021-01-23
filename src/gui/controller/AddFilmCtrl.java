package gui.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gui.FensterManager;
import javafx.beans.InvalidationListener;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ListChangeListener.Change;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import verwaltung.entitaeten.Film;
import verwaltung.entitaeten.Genre;
import verwaltung.entitaeten.Nutzer;
import verwaltung.entitaeten.Person;
import verwaltung.entitaeten.Person.PersonMitRolle;
import verwaltung.verwaltungen.Filmverwaltung;
import verwaltung.verwaltungen.Personenverwaltung;

public class AddFilmCtrl {

	private Film film;
	private Personenverwaltung pvw;
	private Map<Genre, TreeItem<Genre>> genreMap;
	
	// 0 - update 1 - delete
	private ObservableMap<PersonMitRolle, BooleanProperty[]> confirmed;
	private ObservableList<PersonMitRolle> personen;
	private boolean[] changes = {false, false};
	
	public void setFilm(Film film) throws SQLException{
		System.out.println(film);
		if(this.film!=null && this.film.equals(film))	
			return;
			
		this.film = film;		
		if(film!=null) {
			pvw = film.getPvw();
			pvw.load();
		}
		setDisplay();
		setTable();
	}
	
	private void setTable() {
		changes[1] = false;
		
		if(pvw!=null)
			personen = FXCollections.observableArrayList(pvw.getPersonenMitRollen());
		else
			personen = FXCollections.observableArrayList();
		
		table.setItems(personen);
		Map<PersonMitRolle, BooleanProperty[]> map = new HashMap<>();		
		personen.forEach(per->{
			map.put(per, new SimpleBooleanProperty[]{new SimpleBooleanProperty(false), new SimpleBooleanProperty(false)});
		});
		confirmed = FXCollections.observableMap(map);
	}
	
	private void setDisplay() {
		changes[0] = false;
		table_genre.getSelectionModel().clearSelection();
		
		if(film == null) {
			tf_titel.setText(null);
			tf_dauer.setText(null);
			tf_jahr.setText(null);
			tf_genre.setText(null);
		}else {
			tf_titel.setText(film.getTitel());
			tf_dauer.setText(film.getDauer()+" Minuten");
			tf_jahr.setText(film.getErscheinungsjahr()+"");
			tf_genre.setText(film.getGenreStringProperty().get());
			
			if(film.getGenre()==null)	return;
			film.getGenre().forEach(g->{
				table_genre.getSelectionModel().select(genreMap.get(g));
			});
		}
	}
	
    @FXML // fx:id="accordion"
    private Accordion accordion; // Value injected by FXMLLoader

    @FXML // fx:id="tp_allg"
    private TitledPane tp_allg; // Value injected by FXMLLoader
    
    @FXML // fx:id="btn_rel1"
    private Button btn_rel1; // Value injected by FXMLLoader

    @FXML // fx:id="btn_commit1"
    private Button btn_commit1; // Value injected by FXMLLoader

    @FXML // fx:id="btn_detail"
    private Button btn_detail; // Value injected by FXMLLoader

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
    private TableView<PersonMitRolle> table; // Value injected by FXMLLoader
    
    @FXML // fx:id="t_confirm"
    private TableColumn<PersonMitRolle, Boolean> t_confirm; // Value injected by FXMLLoader
    
    @FXML // fx:id="t_confirm1"
    private TableColumn<PersonMitRolle, Boolean> t_confirm1; // Value injected by FXMLLoader

    @FXML // fx:id="t_confimr2"
    private TableColumn<PersonMitRolle, Boolean> t_confirm2; // Value injected by FXMLLoader

    @FXML // fx:id="t_vorname"
    private TableColumn<PersonMitRolle, String> t_vorname; // Value injected by FXMLLoader

    @FXML // fx:id="t_name"
    private TableColumn<PersonMitRolle, String> t_name; // Value injected by FXMLLoader

    @FXML // fx:id="t_rolle"
    private TableColumn<PersonMitRolle, String> t_rolle; // Value injected by FXMLLoader

    @FXML // fx:id="btn_addP"
    private Button btn_addP; // Value injected by FXMLLoader
    
    @FXML // fx:id="btn_rel2"
    private Button btn_rel2; // Value injected by FXMLLoader

    @FXML
    void action(ActionEvent event) {
    	if(event.getSource()==btn_rel1)			setDisplay();
    	else if(event.getSource()==btn_rel2) 	setTable();
    	else if(event.getSource()==btn_commit1)	commit();
    	else if(event.getSource()==btn_addP)	addPerson();
    	else if(event.getSource()==btn_detail)	detail();
    	
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
        assert table_genre != null : "fx:id=\"table_genre\" was not injected: check your FXML file 'AddFilm.fxml'.";
        assert t_genre != null : "fx:id=\"t_genre\" was not injected: check your FXML file 'AddFilm.fxml'.";
        assert btn_commit1 != null : "fx:id=\"btn_commit\" was not injected: check your FXML file 'AddFilm.fxml'.";
        assert btn_rel1 != null : "fx:id=\"btn_rel1\" was not injected: check your FXML file 'AddFilm.fxml'.";
        assert tp_mit != null : "fx:id=\"tp_mit\" was not injected: check your FXML file 'AddFilm.fxml'.";
        assert table != null : "fx:id=\"table\" was not injected: check your FXML file 'AddFilm.fxml'.";
        assert t_confirm != null : "fx:id=\"t_confirm\" was not injected: check your FXML file 'AddFilm.fxml'.";
        assert t_confirm1 != null : "fx:id=\"t_confirm1\" was not injected: check your FXML file 'AddFilm.fxml'.";
        assert t_confirm2 != null : "fx:id=\"t_confirm2\" was not injected: check your FXML file 'AddFilm.fxml'.";
        assert t_vorname != null : "fx:id=\"t_vorname\" was not injected: check your FXML file 'AddFilm.fxml'.";
        assert t_name != null : "fx:id=\"t_name\" was not injected: check your FXML file 'AddFilm.fxml'.";
        assert t_rolle != null : "fx:id=\"t_rolle\" was not injected: check your FXML file 'AddFilm.fxml'.";
        assert btn_addP != null : "fx:id=\"btn_addP\" was not injected: check your FXML file 'AddFilm.fxml'.";
        assert btn_rel2 != null : "fx:id=\"btn_rel2\" was not injected: check your FXML file 'AddFilm.fxml'.";

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
        tf_genre.setEditable(false);
        
        table_genre.setShowRoot(false);
        table_genre.setRoot(new TreeItem<Genre>(new Genre(-1, "")));
        table_genre.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        t_genre.setText("Genre");
        t_genre.setCellValueFactory(data-> new SimpleStringProperty(data.getValue().getValue().getGenre()) );       

        genreMap = new HashMap<>();
        for(Genre g: Filmverwaltung.getGenres()) {
            TreeItem<Genre> item = new TreeItem<>(g);
            table_genre.getRoot().getChildren().add(item);  
            genreMap.put(g, item);
            
            if(g.getSubGenre()!=null) {
            	for(Genre sg: g.getSubGenre()) {
            		TreeItem<Genre> itemChild = new TreeItem<>(sg);
                	item.getChildren().add( itemChild);
                	genreMap.put(sg, itemChild);
            	}
            }
        }

        table_genre.setOnMouseReleased(ev->checkGenre());
        table_genre.setOnKeyReleased(kev->{
        	if(kev.getCode().equals(KeyCode.SHIFT))
        		table_genre.getSelectionModel().clearSelection();
        });

        
        /** Mitwirkende **/
        table.setEditable(true);
        t_name.setCellValueFactory(		data->data.getValue().getPerson().getNameProperty()		);
        t_vorname.setCellValueFactory(	data->data.getValue().getPerson().getVornameProperty()	);
        t_rolle.setCellValueFactory(	data->data.getValue().getRolle()				);
        t_confirm1.setCellValueFactory(	data->confirmed.get(data.getValue())[0]			);
        t_confirm2.setCellValueFactory(	data->confirmed.get(data.getValue())[1]			);
        
        t_name.setCellFactory(TextFieldTableCell.forTableColumn());
        t_vorname.setCellFactory(TextFieldTableCell.forTableColumn());
       // t_rolle.setCellFactory( ChoiceBoxTableCell.forTableColumn(Personenverwaltung.getRollen()) );
        t_rolle.setCellFactory(ComboBoxTableCell.forTableColumn(Personenverwaltung.getRollen()));
        t_confirm1.setCellFactory(CheckBoxTableCell.forTableColumn(t_confirm1));
        t_confirm2.setCellFactory(CheckBoxTableCell.forTableColumn(t_confirm2));

        /**Changes**/
//        t_vorname.setOnEditCommit(	ev->changes[1]=true);
//        t_name.setOnEditCommit(		ev->changes[1]=true);
//        t_rolle.setOnEditCommit(	ev->changes[1]=true);
//        t_confirm1.setOnEditCommit(	ev->changes[1]=true);
//        t_confirm1.setOnEditCommit(	ev->changes[1]=true);
        
        tf_bewertung.textProperty().addListener((ob,ov,nv)-> changes[0]=true);
        tf_dauer.textProperty().addListener(	(ob,ov,nv)-> changes[0]=true);
        tf_genre.textProperty().addListener(	(ob,ov,nv)-> changes[0]=true);
        tf_jahr.textProperty().addListener(		(ob,ov,nv)-> changes[0]=true);
        tf_titel.textProperty().addListener(	(ob,ov,nv)-> changes[0]=true);    
    }
    
    private void addPerson() {
    	PersonMitRolle per = new Person(-1, "Neue Person", "Neue Person", Personenverwaltung.getRollen().get(0)).getPersonenMitRolle().get(0);
    	personen.add(per);
    	confirmed.put(per, new SimpleBooleanProperty[]{new SimpleBooleanProperty(false), new SimpleBooleanProperty(false)});
    	changes[1] = true;
    }
    
    private void commit() {
    	try {
        	checkEingaben();
    		//Wenn Fildaten ge�ndert
    		if(changes[0]) {
    			int dauer = Integer.parseInt(tf_dauer.getText().replaceAll("[^0-9]", ""));
    			int jahr = Integer.parseInt(tf_jahr.getText());
    			List<Genre> genres = new ArrayList<>();
    			table_genre.getSelectionModel().getSelectedItems().forEach(item-> genres.add(item.getValue()) );
    			
    			genres.forEach(g->System.out.println(g.getGenre()));
    			//Wenn kein Film vorhanden
    			if(film==null) {
    				film = Filmverwaltung.instance().addFilm(tf_titel.getText(), genres, dauer, jahr );
    				pvw = new Personenverwaltung(film);
    			}else		
    				Filmverwaltung.instance().updateFilm(tf_titel.getText(), genres, dauer, jahr, film);
    		}
    		
        	FilteredList<PersonMitRolle> update = personen.filtered(per->confirmed.get(per)[0].get());
        	FilteredList<PersonMitRolle> delete = personen.filtered(per->confirmed.get(per)[1].get());
			pvw.addOrUpdate(update.subList(0, update.size()));
			pvw.delete(delete.subList(0, delete.size()));
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
    	// TODO Auto-generated method stub
    	if(tf_titel.getLength() < 10)	throw new Exception("Titel zu kurz");
    	if(tf_jahr.getLength() != 4)	throw new Exception("Geben sie ein g�ltiges jahr ein");
    	if(tf_dauer.getLength() == 0)   throw new Exception("dauer");
    	if(table_genre.getSelectionModel().getSelectedItems().size()==0) throw new Exception("Kein Genre");
    }
    
    private void detail() {
    	if( changes[0]==true || changes[1]==true ) {
    		Alert a = new Alert(AlertType.CONFIRMATION);
    		a.setTitle("Detailansicht �ffnen");
    		a.setHeaderText("Es sind nicht gespeicherte �nderungen vorhanden");
    		a.setContentText("Nicht gespeicherte �nderungen gehen verloren. M�chten sie trotzdem zur Detailansicht wechseln?");
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
			FensterManager.setDialog( FensterManager.showDetail(film) );
		} catch (SQLException | IOException e) {
			Alert a2 = new Alert(AlertType.ERROR);
			a2.setTitle(e.getClass().getSimpleName());
			a2.setContentText(e.getMessage());
			a2.show();
			e.printStackTrace();
		}
    }
    
    //Deselect Parents and Generere String f�r Genre TextField
    private void checkGenre() {
    	List<TreeItem<Genre>> remove = new ArrayList<>();
    	table_genre.getSelectionModel().getSelectedItems().forEach(item->{
    		if(item.getParent()!=null)	remove.add(item.getParent());
    	});
    	
    	List<TreeItem<Genre>> list2 = new ArrayList<>();
    	table_genre.getSelectionModel().getSelectedItems().forEach(item->{
    		if(!remove.stream().anyMatch(rItem->rItem==item)) {
    			list2.add(item);
    			table_genre.getSelectionModel().select(item);
    		}
    	});

    	table_genre.getSelectionModel().clearSelection();
    	if(list2.size()==0) {
			tf_genre.setText(null);
			return;
		}
		tf_genre.setText(list2.get(0).getValue().getGenre());
		table_genre.getSelectionModel().select( list2.get(0) );
        for(int i=1; i<list2.size(); i++) {
        	tf_genre.setText( tf_genre.getText()+", "+list2.get(i).getValue().getGenre());
        	table_genre.getSelectionModel().select( list2.get(i) );
        }
    }
    	
    
    
}
