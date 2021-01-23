package gui.controller;

import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

import exceptions.LogInException;
import exceptions.RegisterException;
import gui.FensterManager;
import gui.fxControls.CustomTextField;
import javafx.beans.value.ObservableValue;
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

import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import verwaltung.Nutzer;

public class AnmeldeseiteCtrl {
	
	@FXML
	private VBox vb;
	
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
    
    private String name = "";
    
    @FXML
    void btnAction(ActionEvent event) {
    	int i = cbox.getSelectionModel().getSelectedIndex();
    	try{
    		if(i==0) Nutzer.anmeldenGast();
    		else if(i==1) Nutzer.anmeldenKonto(tf_name.getText().trim(), tf_pwd.getText());
    		else if(i==2) Nutzer.registrieren(tf_name.getText().trim(), tf_pwd.getText(), tf_pwd2.getText());
    		else if(i==3) Nutzer.anmeldenKonto("Daniel", "123456");
    		else if(i==4) Nutzer.anmeldenKonto("Alle Rechte", "]TrPCLPJ2T''XH.)Pn^l'{L{JT9\\tEwO=<b%%F/Jq7T:pi2Z9g");
    		
    	}catch(NullPointerException np) {
    		Alert a = new Alert(AlertType.ERROR);
    		a.setContentText("Bitte geben sie etwas ein bevor sie sich anmelden");
    		a.show();
    	}catch(LogInException | RegisterException e) { 	
    		Alert a = new Alert(AlertType.ERROR);
    		a.setTitle(e.getTitle());
    		a.setContentText(e.getMessage());
    		a.show();
    		if(e.isTyp(LogInException.ALREADY_LOGGED_IN))	a.setOnCloseRequest(ev->trozdemAnmelden());
    		
    		FensterManager.logErreignis(e);
    	} catch (SQLException e) {
    		Alert a = new Alert(AlertType.ERROR);
    		a.setTitle("Datenbank Fehler");
    		a.setContentText(e.getMessage());
    		a.show();
    		FensterManager.logErreignis(e);
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
        
        cbox.getSelectionModel().selectedIndexProperty().addListener(this::auswahl);    
        cbox.getSelectionModel().select(0);
     
        tf_pwd.setTextFormatter(	new TextFormatter<>(CustomTextField.getMaxLenFilter(Nutzer.getMaxPasswort())	));
        tf_pwd2.setTextFormatter(	new TextFormatter<>(CustomTextField.getMaxLenFilter(Nutzer.getMaxPasswort())	));
        tf_name.setTextFormatter(	new TextFormatter<>(CustomTextField.getMaxLenFilter(Nutzer.getMaxName())        ));
    }

	private void auswahl(ObservableValue<? extends Number> ob, Number oldV, Number newV) {  	
		if(oldV.intValue()==1 || oldV.intValue()==2) name = tf_name.getText()==null? "":tf_name.getText();
		
		if(newV.intValue()==0) {
	        tf_name.setText("Gast");
	        tf_name.setEditable(false);
	        vb.getChildren().remove(tf_pwd);
	        vb.getChildren().remove(tf_pwd2);
	        btn.setText("anmelden");
	        FensterManager.getAnmelden().setHeight(200);
	        }
		else if(newV.intValue()==1) {
			tf_name.setText(name);
	        tf_name.setEditable(true);
	        if(!vb.getChildren().contains(tf_pwd)) vb.getChildren().add(2, tf_pwd);
	        vb.getChildren().remove(tf_pwd2);
	        btn.setText("anmelden");
	        FensterManager.getAnmelden().setHeight(240);
	        }
	    else if(newV.intValue()==2) {
	    	tf_name.setText(name);
	        tf_name.setEditable(true);
	        if(!vb.getChildren().contains(tf_pwd)) 	vb.getChildren().add(2, tf_pwd);
	        if(!vb.getChildren().contains(tf_pwd2)) vb.getChildren().add(3, tf_pwd2);
	        btn.setText("Registrieren");
	        FensterManager.getAnmelden().setHeight(280);
	    	}
	    else if(newV.intValue()==3){
	        name = tf_name.getText()==null? "":tf_name.getText();
	        tf_name.setText("Daniel");
	        tf_name.setEditable(true);
	        vb.getChildren().remove(tf_pwd);
	        vb.getChildren().remove(tf_pwd2);
	        btn.setText("anmelden");
	        FensterManager.getAnmelden().setHeight(200);
	    	} 
	    else if(newV.intValue()==4){
	        tf_name.setText("Alle Rechte");
	        tf_name.setEditable(true);
	        vb.getChildren().remove(tf_pwd);
	        vb.getChildren().remove(tf_pwd2);
	        btn.setText("anmelden");
	        FensterManager.getAnmelden().setHeight(200);
	     	} 	   
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
					Nutzer.getNutzer().vonAnderenInstanzenAbmelden(tf_name.getText().trim(), tf_pwd.getText());
				} 
				catch (SQLException e1) {}
			}
		});	
	}
}
