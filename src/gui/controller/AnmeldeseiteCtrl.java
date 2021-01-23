package gui.controller;

import java.sql.SQLException;
import java.util.function.UnaryOperator;

import exceptions.LogInException;
import exceptions.RegisterException;
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
    		
        	if(Nutzer.getNutzer().isAngemeldet())
        		FensterManager.setPrimaryStage( FensterManager.getHauptSeite() );
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
    							if(Nutzer.getNutzer().isAngemeldet())
    								FensterManager.setPrimaryStage(FensterManager.getHauptSeite());
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
     
        UnaryOperator<TextFormatter.Change> checkText = change->{
        	int maxlen = Nutzer.getMaxPasswort();
			if(change.getControlNewText().length()>=maxlen) {
				int z = maxlen - (change.getControlNewText().length() - change.getText().length());
				change.setText( change.getText().substring(0, z) );
			}
			return change;	
        };
        
        tf_pwd.setTextFormatter(new TextFormatter<>(checkText));
        tf_pwd2.setTextFormatter(new TextFormatter<>(checkText));
        tf_name.setTextFormatter(new TextFormatter<>( (UnaryOperator<TextFormatter.Change>) change-> {
        	int maxlen = Nutzer.getMaxName();
			if(change.getControlNewText().length()>=maxlen) {
				int z = maxlen - (change.getControlNewText().length() - change.getText().length());
				change.setText( change.getText().substring(0, z) );
			}
			return change;	
        }));
    }

}
