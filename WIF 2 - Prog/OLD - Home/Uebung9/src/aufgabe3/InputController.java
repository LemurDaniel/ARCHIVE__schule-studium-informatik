package aufgabe3;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;


public class InputController {

	private Roboter robot;
	private Stage thisStage;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="tfBez"
    private TextField tfBez; // Value injected by FXMLLoader

    @FXML // fx:id="tfMin"
    private TextField tfMin; // Value injected by FXMLLoader

    @FXML // fx:id="tfMax"
    private TextField tfMax; // Value injected by FXMLLoader

    @FXML // fx:id="tfWink"
    private TextField tfWink; // Value injected by FXMLLoader

    @FXML // fx:id="tfMinor"
    private TextField tfMinor; // Value injected by FXMLLoader

    @FXML // fx:id="tfMajor"
    private TextField tfMajor; // Value injected by FXMLLoader

    @FXML // fx:id="btnKreiere"
    private Button btnKreiere; // Value injected by FXMLLoader

    @FXML
    private Label lblFehler;
    
    @FXML
    private Slider slider;
    
    @FXML
    void create(ActionEvent event) {
		Integer max, min, winkel, major, minor;
    	try {
    		if(tfBez.getText().length()==0) throw new Exception();
    		if(tfMajor.getText().length()==0) throw new Exception();
    		major = Integer.parseInt(tfMajor.getText());
    		if(tfMinor.getText().length()==0) throw new Exception();
    		minor = Integer.parseInt(tfMinor.getText());
    		if(tfMax.getText().length()==0) throw new Exception();
    		max = Integer.parseInt(tfMax.getText());
    		if(tfMin.getText().length()==0) throw new Exception();
    		min = Integer.parseInt(tfMin.getText());
    		if(tfWink.getText().length()==0) throw new Exception();
    		winkel = Integer.parseInt(tfWink.getText());
    	}catch(Exception e) {
    		lblFehler.setText("Es sind noch nicht alle Felder ausgefüllt");
    		return;
    	}
    	
    	try {     		
    		robot.hinzufuegen(tfBez.getText(), max, min, winkel, major, minor);
    		thisStage.close();
    	}catch(Exception e) {
    		lblFehler.setText(e.getMessage());
    	}
    }
    
    @FXML
    void istZahl(KeyEvent event) {
    	resetlbl();
    	if(!Character.isDigit(event.getCharacter().charAt(0)) && !event.getCharacter().equals("-") && !event.getCode().equals(KeyCode.BACK_SPACE)) {
    		if(!event.getCode().equals(KeyCode.UNDEFINED) && !Character.isDigit(event.getCharacter().charAt(0)))
    			lblFehler.setText("Es sind nur Zahlen erlaubt");
    		event.consume();
    		return;
    	}

    	if(!event.getSource().getClass().equals(TextField.class))
    		return;
    	
    	TextField t = (TextField)event.getSource();
    	String unalteredText = t.getText();
    	
    	if(event.getCharacter().equals("-")) {
    		t.setText(-Integer.parseInt(t.getText())+"");	
    	}
    	else if(event.getCode().equals(KeyCode.BACK_SPACE)) {
    		if(unalteredText.length()==0)return;
    		else {
    			t.setText(unalteredText.substring(0, unalteredText.length()-1));
    			if(t.getText().equals("-")) t.setText("");
    		}
    	}else	
    		t.setText(t.getText()+event.getCharacter());
    	event.consume();
    		
    	try {
        	Integer major = tfMajor.getText().length()==0? null:Integer.parseInt(tfMajor.getText());
        	if(major != null) {
        		if(major <= 0) throw new Exception("Major-Ticking-Space muss größer als 0 sein"); 
        		slider.setMajorTickUnit(major);
        		slider.setShowTickLabels(true);
        	}else
        		slider.setShowTickLabels(false);
        	
        	Integer minor = tfMinor.getText().length()==0? null:Integer.parseInt(tfMinor.getText());
        	if(minor != null) {
        		if(minor <= 0) throw new Exception("Minor-Ticking-Space muss größer als 0 sein");
        		slider.setMinorTickCount(minor);
        		slider.setShowTickMarks(true);
        	}else 
        		slider.setShowTickMarks(false);
        	
        	Integer max = tfMax.getText().length()==0? null:Integer.parseInt(tfMax.getText()),
        			min = tfMin.getText().length()==0? null:Integer.parseInt(tfMin.getText());
        	if(max != null && min != null) {
        		if(max < min) throw new Exception("Maximalwert darf nicht kleiner als der Minimalwert sein");
        		if(max == min) throw new Exception("Maximalwert darf nicht gleich dem Minimalwert sein");
        		if(max != null)	slider.setMax(max);
        		if(min != null) slider.setMin(min);
       		}
        		
       		Integer winkel = tfWink.getText().length()==0? null:Integer.parseInt(tfWink.getText());
       		if(winkel != null) {
        		if(max != null)
        			if(winkel > max) throw new Exception("Winkel darf nicht größer als der Maximalwert sein");
        		if(min != null)
        			if(winkel < min) throw new Exception("Winkel darf nicht kleiner als Minimalwert sein");
        		slider.setValue(winkel);
       		}
       	}catch(Exception e) {
        	lblFehler.setText(e.getMessage());
        	t.setText(unalteredText);
        }
    }

    
  
    
    @FXML
    void istOk() {
    	lblFehler.setText(null);
    	try {
    		robot.checkBezeichnung(tfBez.getText());
    	}catch(Exception e) {
    		tfBez.setText(null);
    		lblFehler.setText(e.getMessage());
    	}
    }
    
    @FXML
    void resetlbl() {
    	lblFehler.setText(null);
    }
    
    void set(Roboter r, Stage stage) {
    	this.robot = r;
    	this.thisStage = stage;
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert tfBez != null : "fx:id=\"tfBez\" was not injected: check your FXML file 'Input.fxml'.";
        assert tfMin != null : "fx:id=\"tfMin\" was not injected: check your FXML file 'Input.fxml'.";
        assert tfMax != null : "fx:id=\"tfMax\" was not injected: check your FXML file 'Input.fxml'.";
        assert tfWink != null : "fx:id=\"tfWink\" was not injected: check your FXML file 'Input.fxml'.";
        assert tfMinor != null : "fx:id=\"tfMinor\" was not injected: check your FXML file 'Input.fxml'.";
        assert tfMajor != null : "fx:id=\"tfMajor\" was not injected: check your FXML file 'Input.fxml'.";
        assert btnKreiere != null : "fx:id=\"btnKreiere\" was not injected: check your FXML file 'Input.fxml'.";
        tfBez.focusedProperty().addListener(c->istOk());
        
//        tfMajor.addEventHandler(KeyEvent.KEY_PRESSED, e->unalteredText = ((TextField)e.getSource()).getText());
//        tfMinor.addEventHandler(KeyEvent.KEY_PRESSED, e->unalteredText = ((TextField)e.getSource()).getText());
//        tfMax.addEventHandler(KeyEvent.KEY_PRESSED, e->unalteredText = ((TextField)e.getSource()).getText());
//        tfMin.addEventHandler(KeyEvent.KEY_PRESSED, e->unalteredText = ((TextField)e.getSource()).getText());
//        tfWink.addEventHandler(KeyEvent.KEY_PRESSED, e->unalteredText = ((TextField)e.getSource()).getText());
    }
}
