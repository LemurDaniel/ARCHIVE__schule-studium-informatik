package application;

import java.net.URL;
import java.util.ResourceBundle;

import Buchungssystem.Buchung;
import Buchungssystem.Buchungssatz;
import Buchungssystem.KontenSystem;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;

public class BuchungInputController {

	private Buchung[] soll, haben;
	private KontenSystem k;
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea sollTA;

    @FXML
    private Label sollLBL;

    @FXML
    private Label habenLBL;

    @FXML
    private TextArea habenTA;

    @FXML
    private Button btn;

    @FXML
    void initialize() {
        assert sollTA != null : "fx:id=\"sollTA\" was not injected: check your FXML file 'BuchungInput.fxml'.";
        assert sollLBL != null : "fx:id=\"sollLBL\" was not injected: check your FXML file 'BuchungInput.fxml'.";
        assert habenLBL != null : "fx:id=\"habenLBL\" was not injected: check your FXML file 'BuchungInput.fxml'.";
        assert habenTA != null : "fx:id=\"habenTA\" was not injected: check your FXML file 'BuchungInput.fxml'.";
        assert btn != null : "fx:id=\"btn\" was not injected: check your FXML file 'BuchungInput.fxml'.";

         k = new KontenSystem();
    k.test();
    }
    
    private Buchung[] getBuchung(TextArea ta) throws Exception{
      	String[] text = ta.getText().split("\\s+");
      	if(text.length%2 != 0) throw new Exception();
    	Buchung[] buchungen = new Buchung[text.length/2];    	
    	for(int i=0, i2=0; i2<text.length; i++, i2+=2) 
    		buchungen[i] = k.getBuchung(text[i2], Integer.parseInt(text[i2+1]),  ta.equals(sollTA)? Buchung.SOLL:Buchung.HABEN);
    	return buchungen;
    }
    
    @FXML
    private void setBuchung(KeyEvent event) {
    	Buchung[] b;
    	try {
    		b = getBuchung((TextArea)event.getSource());
    	}catch(Exception e) {
    		return;
    	}
    	
    	int sum = 0;
    	for (int i = 0; i < b.length; i++) 
			sum += b[i].betrag;
    	
    	if(event.getSource().equals(sollTA)) {
    		sollLBL.setText("Sum:  "+sum);
    		soll = b;
    	}
    	else {
    		habenLBL.setText("Sum:  "+sum);
    		haben = b;
    	}
    	
    	btn.setDisable(soll == null || haben == null);
    }
    
    
    @FXML
    void test() {
    	try {
    		Buchungssatz bs = new Buchungssatz(soll, haben);
    		bs.buche();
    	}catch(Exception e) {
    		Alert a = new Alert(AlertType.ERROR);
        	a.setTitle(e.getClass().getSimpleName());
        	a.setContentText(e.getMessage());
        	a.show();
        	e.printStackTrace();
    	}
    }
    
    
}
