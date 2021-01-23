package gui.controller;

import java.util.function.UnaryOperator;

import org.controlsfx.control.CheckComboBox;

import fxControls.MinMaxTextField;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.HBox;
import javafx.util.StringConverter;
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
    private HBox hb_jahr;
    
    @FXML
    private HBox hb_genre;
    
    @FXML
    private TextField tf_tags;

    @FXML
    private Button btn_filter;
    
    @FXML
    private Button btn_reset;
    
    private MinMaxTextField tf_bwtMin, tf_bwtMax;
    private MinMaxTextField tf_dauerMin, tf_dauerMax;
    private MinMaxTextField tf_jahrMin, tf_jahrMax;
    private CheckComboBox<Genre> cb_genre;

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
 
    	
    	tf_bwtMin = new MinMaxTextField(0, 10, " Sterne");
    	tf_bwtMax = new MinMaxTextField(0, 10, " Sterne");
    	tf_bwtMin.setPromptText("Bewertung Minimum");
    	tf_bwtMax.setPromptText("Bewertung Maximum");
    	
    	tf_bwtMax.setMintf(tf_bwtMin);
    	tf_bwtMin.setMaxtf(tf_bwtMax);
    	
    	hb_bwt.getChildren().add(tf_bwtMin);
    	hb_bwt.getChildren().add(new Label("bis"));
    	hb_bwt.getChildren().add(tf_bwtMax);
    	
    	
    	tf_dauerMin = new MinMaxTextField(0, Filmverwaltung.getMaxDauer(), " Minuten");
    	tf_dauerMax = new MinMaxTextField(0, Filmverwaltung.getMaxDauer(), " Minuten");
    	tf_dauerMin.setPromptText("Laufzeit Minimum");
    	tf_dauerMax.setPromptText("Laufzeit Maximum");
    	
    	tf_dauerMax.setMintf(tf_dauerMin);
    	tf_dauerMin.setMaxtf(tf_dauerMax);
    	
    	hb_dauer.getChildren().add(tf_dauerMin);
    	hb_dauer.getChildren().add(new Label("bis"));
    	hb_dauer.getChildren().add(tf_dauerMax);
    	
    	
    	tf_jahrMin = new MinMaxTextField(Filmverwaltung.getMinJahr(), Filmverwaltung.getMaxJahr(), "");
    	tf_jahrMax = new MinMaxTextField(Filmverwaltung.getMinJahr(), Filmverwaltung.getMaxJahr(), "");
    	tf_jahrMin.setPromptText("Jahr Minimum");
    	tf_jahrMax.setPromptText("Jahr Maximum");
    	
    	tf_jahrMax.setMintf(tf_jahrMin);
    	tf_jahrMin.setMaxtf(tf_jahrMax);
    	
    	hb_jahr.getChildren().add(tf_jahrMin);
    	hb_jahr.getChildren().add(new Label("bis"));
    	hb_jahr.getChildren().add(tf_jahrMax);
    	
    	
    	cb_genre = new CheckComboBox<>( FXCollections.observableArrayList(Filmverwaltung.getGenres()) );
    	cb_genre.setConverter( new StringConverter<Genre>() {
			@Override
			public String toString(Genre object) {
				return object.getGenre();
			}
			@Override
			public Genre fromString(String string) {return null;}
		});
    	hb_genre.getChildren().add(cb_genre);
    	  	
    	btn_reset.setOnAction(ev->{
    		tf_titel.setText(null);
    		tf_bwtMax.setText(null);
    		tf_bwtMin.setText(null);
    		tf_dauerMax.setText(null);
    		tf_dauerMin.setText(null);
    		tf_jahrMax.setText(null);
        	tf_jahrMin.setText(null);
    		tf_tags.setText(null);
    		cb_genre.getCheckModel().clearChecks();
    	});
    }
}