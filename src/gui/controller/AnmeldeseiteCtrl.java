package gui.controller;

import java.sql.SQLException;

import exceptions.LogInException;
import exceptions.RegisterException;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
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
    		else if(i==1) Nutzer.anmeldenKonto(tf_name.getText(), tf_pwd.getText());
    		else if(i==2) Nutzer.registrieren(tf_name.getText(), tf_pwd.getText(), tf_pwd2.getText());
    		else if(i==3) Nutzer.anmeldenKonto("Daniel", "123456");
    		else if(i==4) Nutzer.anmeldenKonto("Unlimited", "rfgh");
    	
    	}catch(LogInException | RegisterException e) {
    	
    		Alert a = new Alert(AlertType.ERROR);
    		a.setTitle(e.getTitle());
    		a.setContentText(e.getMessage());
    		a.show();
    		
    		a.setOnCloseRequest(eva->{
    			//Abfrage wenn Typ = Already Logged in
    			if( e.isTyp(LogInException.ALREADY_LOGGED_IN) ){
    				
    				Alert b = new Alert(AlertType.CONFIRMATION);
    				b.setAlertType(AlertType.CONFIRMATION);
    				b.setContentText("Möchten sie sich von den anderen Instanzen abmelden? ");
    				b.show();
    				b.setOnCloseRequest(evb->{
    					if(a.getResult().getButtonData().equals(ButtonData.OK_DONE)) {
    						try {
    							Nutzer.getNutzer().vonAnderenInstanzenAbmelden();
    						} catch (SQLException e1) {}
    					}
    				});
    			}
    		});
    	} catch (SQLException e) {
    		Alert a = new Alert(AlertType.ERROR);
    		a.setTitle("Datenbank Fehler");
    		a.setContentText(e.getMessage());
    		a.show();
		}
    }
    

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert cbox != null : "fx:id=\"cbox\" was not injected: check your FXML file 'Anmeldeseite.fxml'.";
        assert tf_name != null : "fx:id=\"tf_name\" was not injected: check your FXML file 'Anmeldeseite.fxml'.";
        assert tf_pwd != null : "fx:id=\"tf_pwd\" was not injected: check your FXML file 'Anmeldeseite.fxml'.";
        assert tf_pwd2 != null : "fx:id=\"tf_pwd2\" was not injected: check your FXML file 'Anmeldeseite.fxml'.";
        assert btn != null : "fx:id=\"btn\" was not injected: check your FXML file 'Anmeldeseite.fxml'.";
        


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
     
        tf_name.addEventFilter(KeyEvent.KEY_TYPED, this::typed);
        tf_pwd.addEventFilter(KeyEvent.KEY_TYPED, this::typed);
        tf_pwd2.addEventFilter(KeyEvent.KEY_TYPED, this::typed);
    }
    
    void typed(KeyEvent event) {
    	if(event.getSource().equals(tf_name)) {
			if(tf_name.getLength() >= Nutzer.getMaxName())	 event.consume();
    	} else {
			if( ((TextField)event.getSource()).getLength() >= Nutzer.getMaxPasswort() ) event.consume();
    	}
    }
    
}
