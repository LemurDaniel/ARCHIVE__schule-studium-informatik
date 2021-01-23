package gui.controller;

import java.util.List;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.TransferMode;
import verwaltung.entitaeten.Film;
import verwaltung.entitaeten.Liste;
import verwaltung.verwaltungen.Filmverwaltung;

public class ListensichtCtrl {

	private Map<Liste, List<Film>> addFilme, delFilme, anzeige_Filme;
	private Map<Liste, String> umbennenen, anzeige_Name;

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
    private TableColumn<Liste, String> tListe_size;

    @FXML
    private Button btn_rel;

    @FXML
    private Button btn_save;

    @FXML
    private Button btn_neu;

    @FXML
    void initialize() {
    	table_film.setItems(FXCollections.observableArrayList());
    	table_film.setOnDragOver(ev->{
    		if(ev.getDragboard().hasContent(HauptseiteCtrl.df))
    			ev.acceptTransferModes(TransferMode.LINK);
    		ev.consume();
    	});
    	table_film.setOnDragDropped(ev->{
    		@SuppressWarnings("unchecked")
			List<Integer> ids =  (List<Integer>)ev.getDragboard().getContent(HauptseiteCtrl.df);
    		for(Integer id: ids) {
    			Film f = Filmverwaltung.getFilmById(id);
    			System.out.println(f);
    			if(f!=null && !table_film.getItems().contains(f))
    				table_film.getItems().add(f);
    		}
    		ev.setDropCompleted(true);
    		ev.consume();
    	});
    	
    	tFilm_titel.setCellValueFactory(data->data.getValue().getTitelProperty());

    }
}
