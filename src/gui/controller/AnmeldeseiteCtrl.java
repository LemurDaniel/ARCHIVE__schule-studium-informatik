package gui.controller;

import java.sql.SQLException;

import exceptions.LogInException;
import exceptions.RegisterException;
import fxControls.CustomTextField;
import gui.FensterManager;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import verwaltung.Nutzer;

public class AnmeldeseiteCtrl {

    @FXML
    private ComboBox<String> cbox;  
    
    @FXML
    private TextField tf_name;  

    @FXML
    private TextField tf_pwd;  

    @FXML
    private TextField tf_pwd2;  

    @FXML
    private Button btn;  
    
    @FXML
    void btnAction(ActionEvent event) {
    	int i = cbox.getSelectionModel().getSelectedIndex();
    	try{
    		if(i==0) Nutzer.anmeldenGast();
    		else if(i==1) Nutzer.anmeldenKonto(tf_name.getText().trim(), tf_pwd.getText());
    		else if(i==2) Nutzer.registrieren(tf_name.getText().trim(), tf_pwd.getText(), tf_pwd2.getText());
    		else if(i==3) Nutzer.anmeldenKonto("Daniel", "123456");
    		else if(i==4) Nutzer.anmeldenKonto("Unlimited", "123456");
    		
        	if(Nutzer.getNutzer().isAngemeldet())	FensterManager.setPrimaryStage( FensterManager.getHauptSeite() );
    	}catch(LogInException | RegisterException e) { 	
    		Alert a = new Alert(AlertType.ERROR);
    		a.setTitle(e.getTitle());
    		a.setContentText(e.getMessage());
    		a.show();
    		if(e.isTyp(LogInException.ALREADY_LOGGED_IN))	a.setOnCloseRequest(ev->trozdemAnmelden());
    		
    	} catch (SQLException e) {
    		Alert a = new Alert(AlertType.ERROR);
    		a.setTitle("Datenbank Fehler");
    		a.setContentText(e.getMessage());
    		a.show();
		}
    }


	@FXML
    void initialize() {

        cbox.setItems(FXCollections.observableArrayList());
        cbox.getItems().add("Gast");
        cbox.getItems().add("Nutzer");
        cbox.getItems().add("Registrieren");
        cbox.getItems().add("Daniel");
        cbox.getItems().add("Unlimited");

        cbox.getSelectionModel().selectedIndexProperty().addListener( (ob, oldV, newV) -> {      	
        	if(newV.intValue()==0) {
        		tf_name.setText("Gast");
        		tf_name.setEditable(false);
        		tf_pwd.setVisible(false);
                tf_pwd2.setVisible(false);
                btn.setText("anmelden");
        	}else
        	if(newV.intValue()==1) {
        		if(oldV.intValue()==0)tf_name.setText(null);
        		tf_name.setEditable(true);
        		tf_pwd.setVisible(true);
                tf_pwd2.setVisible(false);
                btn.setText("anmelden");
        	}
        	else
        	if(newV.intValue()==2) {
        		if(oldV.intValue()==0)tf_name.setText(null);
        		tf_name.setEditable(true);
        		tf_pwd.setVisible(true);
                tf_pwd2.setVisible(true);
                btn.setText("Registrieren");
        	}
        });        
        cbox.getSelectionModel().select(0);
     
        tf_pwd.setTextFormatter(	new TextFormatter<>(CustomTextField.getMaxLenFilter(Nutzer.getMaxPasswort())	));
        tf_pwd2.setTextFormatter(	new TextFormatter<>(CustomTextField.getMaxLenFilter(Nutzer.getMaxPasswort())	));
        tf_name.setTextFormatter(	new TextFormatter<>(CustomTextField.getMaxLenFilter(Nutzer.getMaxName())        ));

    }

	private void trozdemAnmelden() {
		//Abfrage wenn Typ = Already Logged in	
		Alert b = new Alert(AlertType.CONFIRMATION);
		b.setAlertType(AlertType.CONFIRMATION);
		b.setContentText("Möchten sie sich von den anderen Instanzen abmelden? ");
		b.show();
		b.setOnCloseRequest(evb->{
			if(b.getResult().getButtonData().equals(ButtonData.OK_DONE)) {
				try {
					Nutzer.getNutzer().vonAnderenInstanzenAbmelden("Daniel", "123456");
					if(Nutzer.getNutzer().isAngemeldet())	FensterManager.setPrimaryStage(FensterManager.getHauptSeite());
				} 
				catch (SQLException e1) {}
			}
		});	
	}
}
