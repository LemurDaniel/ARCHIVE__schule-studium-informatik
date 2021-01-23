package gui.controller;

import java.util.ArrayList;
import java.util.List;

import org.controlsfx.control.CheckComboBox;

import fxControls.FloatMinMaxTextField;
import fxControls.IntegerMinMaxTextField;
import fxControls.StringTextField;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.util.StringConverter;
import verwaltung.entitaeten.Genre;
import verwaltung.verwaltungen.Filmverwaltung;

public class FilterCtrl {

	private Filmverwaltung fvw;
	public void setFvw(Filmverwaltung fvw) {
		this.fvw = fvw;
	}
	
    @FXML
    private HBox hb_titel;

    @FXML
    private HBox hb_bwt;
    
    @FXML
    private HBox hb_dauer;
    
    @FXML
    private HBox hb_jahr;
    
    @FXML
    private HBox hb_genre;
    
    @FXML
    private HBox hb_anzahl;
    
    @FXML
    private TextField tf_tags;

    @FXML
    private Button btn_filter;
    
    @FXML
    private Button btn_reset;
    
    private StringTextField tf_titel;
    private FloatMinMaxTextField tf_bwtMin, tf_bwtMax;
    private IntegerMinMaxTextField tf_dauerMin, tf_dauerMax;
    private IntegerMinMaxTextField tf_jahrMin, tf_jahrMax;
    private IntegerMinMaxTextField tf_anzahl;
    private CheckBox cb_genreVerkuepfung;  
    private CheckComboBox<Genre> cb_genre;

    @FXML
    void initialize() { 	
    	tf_titel = new StringTextField(Filmverwaltung.getMaxTitel());
    	hb_titel.getChildren().add(tf_titel);
    	tf_titel.setPromptText("Filmtitel");
    	tf_titel.getStyleClass().add("tf_lang");
    	
    	tf_bwtMin = new FloatMinMaxTextField(0f, 10f, " Sterne");
    	tf_bwtMax = new FloatMinMaxTextField(0f, 10f, " Sterne");
    	tf_bwtMin.setPromptText("Bewertung Minimum");
    	tf_bwtMax.setPromptText("Bewertung Maximum");
    	tf_bwtMin.getStyleClass().add("tf_kurz");
    	tf_bwtMax.getStyleClass().add("tf_kurz");
    	
    	tf_bwtMax.setMinSupplier( ()->tf_bwtMin.getValue() );
    	tf_bwtMin.setMaxSupplier( ()->tf_bwtMax.getValue() );
    	
    	hb_bwt.getChildren().add(tf_bwtMin);
    	hb_bwt.getChildren().add(new Label("bis"));
    	hb_bwt.getChildren().add(tf_bwtMax);
    	
    	
    	
    	tf_dauerMin = new IntegerMinMaxTextField(0, Filmverwaltung.getMaxDauer(), " Minuten");
    	tf_dauerMax = new IntegerMinMaxTextField(0, Filmverwaltung.getMaxDauer(), " Minuten");
    	tf_dauerMin.setPromptText("Laufzeit Minimum");
    	tf_dauerMax.setPromptText("Laufzeit Maximum");
    	tf_dauerMin.getStyleClass().add("tf_kurz");
    	tf_dauerMax.getStyleClass().add("tf_kurz");
    	
    	tf_dauerMax.setMinSupplier( ()->tf_dauerMin.getValue() );
    	tf_dauerMin.setMaxSupplier( ()->tf_dauerMax.getValue() );
    	
    	hb_dauer.getChildren().add(tf_dauerMin);
    	hb_dauer.getChildren().add(new Label("bis"));
    	hb_dauer.getChildren().add(tf_dauerMax);
    	
    	
    	
    	tf_jahrMin = new IntegerMinMaxTextField(Filmverwaltung.getMinJahr(), Filmverwaltung.getMaxJahr(), "");
    	tf_jahrMax = new IntegerMinMaxTextField(Filmverwaltung.getMinJahr(), Filmverwaltung.getMaxJahr(), "");
    	tf_jahrMin.setPromptText("Jahr Minimum");
    	tf_jahrMax.setPromptText("Jahr Maximum");
    	tf_jahrMin.getStyleClass().add("tf_kurz");
    	tf_jahrMax.getStyleClass().add("tf_kurz");
    	
    	tf_jahrMax.setMinSupplier( ()->tf_jahrMin.getValue() );
    	tf_jahrMin.setMaxSupplier( ()->tf_jahrMax.getValue() );
    	
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
    	  	
    	
    	cb_genreVerkuepfung = new CheckBox();
    	cb_genreVerkuepfung.selectedProperty().addListener((ob,ov,checked)->{
    		if(checked)
    			cb_genreVerkuepfung.setText("Genre:  UND");
    		else
    			cb_genreVerkuepfung.setText("Genre:  ODER");
    	});
    	cb_genreVerkuepfung.setSelected(true);
    	
    	
    	
    	tf_anzahl = new IntegerMinMaxTextField(Filmverwaltung.getMinErgebnisse(), Filmverwaltung.getMaxErgebnisse(), " Ergebnisse");
    	tf_anzahl.setDefaultValue(Filmverwaltung.getMinErgebnisse());
    	tf_anzahl.getStyleClass().add("tf_kurz");
    	hb_anzahl.getChildren().add(tf_anzahl);
    	hb_anzahl.getChildren().add(new Label());
    	hb_anzahl.getChildren().add(cb_genreVerkuepfung);
    	
    	btn_reset.setOnAction(this::reset);
    	btn_filter.setOnAction(this::filter);
    }

    private void reset(ActionEvent event) {
    	tf_titel.setText(null);
		tf_bwtMax.setText(null);
		tf_bwtMin.setText(null);
		tf_dauerMax.setText(null);
		tf_dauerMin.setText(null);
		tf_jahrMax.setText(null);
    	tf_jahrMin.setText(null);
		tf_tags.setText(null);
		cb_genre.getCheckModel().clearChecks();
		cb_genreVerkuepfung.setSelected(true);
    }
    
    private void filter(ActionEvent event) {
		List<String> tags = new ArrayList<>();
		
    	if(tf_tags.getText()!=null && tf_tags.getText().length()!=0) {
    		String[] sarr = tf_tags.getText().split(",");	
    		for(int i=0; i<sarr.length; i++) {
    			sarr[i] = sarr[i].trim();
    			if(sarr[i].length()!=0)	tags.add(sarr[i]);
    		}
    	}
    	if(tf_titel.getText()!=null)	tf_titel.setText(tf_titel.getText().trim());
    	
    	try {
    		fvw.filter(tf_titel.getText(), tf_bwtMax.getValue(), tf_bwtMin.getValue(), tf_dauerMax.getValue(), tf_dauerMin.getValue(), 
    					tf_jahrMax.getValue(), tf_jahrMin.getValue(), cb_genre.getCheckModel().getCheckedItems(), cb_genreVerkuepfung.isSelected(), tags, tf_anzahl.getValue());
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }
}