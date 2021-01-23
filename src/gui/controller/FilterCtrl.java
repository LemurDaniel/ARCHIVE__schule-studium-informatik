package gui.controller;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;

import org.controlsfx.control.CheckComboBox;

import fxControls.MinMaxTextField;
import gui.FensterManager;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import verwaltung.DB_Manager;
import verwaltung.entitaeten.Genre;
import verwaltung.verwaltungen.Filmverwaltung;

public class FilterCtrl {

    @FXML
    private TextField tf_titel;

    @FXML
    private HBox hb_bwt;
    
    @FXML
    private HBox hb_dauer;
    
    @FXML
    private HBox hb_genre;

    @FXML
    private TextField tf_tags;

    @FXML
    private Button btn_filter;
    
    @FXML
    private Button btn_reset;

    @FXML
    void initialize() { 	
    	tf_titel.setTextFormatter(new TextFormatter<>( (UnaryOperator<TextFormatter.Change>) change->{
    		int maxlen = Filmverwaltung.getMaxTitel();
				if(change.getControlNewText().length()>=maxlen) {
					int z = maxlen - (change.getControlNewText().length() - change.getText().length());
					change.setText( change.getText().substring(0, z) );
				}
				return change;		
		}));
 
    	
    	MinMaxTextField tf_bwtMin = new MinMaxTextField(0, 10, " Sterne");
    	MinMaxTextField tf_bwtMax = new MinMaxTextField(0, 10, " Sterne");
    	tf_bwtMin.setPromptText("Bewertung Minimum");
    	tf_bwtMax.setPromptText("Bewertung Maximum");
    	
    	tf_bwtMax.setMintf(tf_bwtMin);
    	tf_bwtMin.setMaxtf(tf_bwtMax);
    	
    	hb_bwt.getChildren().add(tf_bwtMin);
    	hb_bwt.getChildren().add(new Label("bis"));
    	hb_bwt.getChildren().add(tf_bwtMax);
    	
    	
    	MinMaxTextField tf_dauerMin = new MinMaxTextField(0, Filmverwaltung.getMaxDauer(), " Minuten");
    	MinMaxTextField tf_dauerMax = new MinMaxTextField(0, Filmverwaltung.getMaxDauer(), " Minuten");
    	tf_dauerMin.setPromptText("Laufzeit Minimum");
    	tf_dauerMax.setPromptText("Laufzeit Maximum");
    	
    	tf_dauerMax.setMintf(tf_dauerMin);
    	tf_dauerMin.setMaxtf(tf_dauerMax);
    	
    	hb_dauer.getChildren().add(tf_dauerMin);
    	hb_dauer.getChildren().add(new Label("bis"));
    	hb_dauer.getChildren().add(tf_dauerMax);
    	
    	
    	CheckComboBox<Genre> cb_genre = new CheckComboBox<>( FXCollections.observableArrayList(Filmverwaltung.getGenres()) );
    	cb_genre.setConverter( new StringConverter<Genre>() {

			@Override
			public String toString(Genre object) {
				return object.getGenre();
			}

			@Override
			public Genre fromString(String string) {
				// TODO Auto-generated method stub
				return null;
			}
		});
    	hb_genre.getChildren().add(cb_genre);
    	  	
    	btn_reset.setOnAction(ev->{
    		tf_titel.setText(null);
    		tf_bwtMax.setText(null);
    		tf_bwtMin.setText(null);
    		tf_dauerMax.setText(null);
    		tf_dauerMin.setText(null);
    		tf_tags.setText(null);
    	});
    }
}