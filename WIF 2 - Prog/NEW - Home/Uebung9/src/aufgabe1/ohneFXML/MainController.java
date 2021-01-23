package aufgabe1.ohneFXML;
/**
 * Sample Skeleton for 'main.fxml' Controller Class
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class MainController {

	private int count = 0;
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btn"
    private Button btn; // Value injected by FXMLLoader

    @FXML // fx:id="lbl"
    private Label lbl; // Value injected by FXMLLoader
    
    @FXML
    private Label test;

    @FXML
    void button(ActionEvent event) {
    	btn.setText(++count+"");
    	lbl.setText("sag Hallo "+count);
    }

    @FXML
    void mouse(MouseEvent event) {
    	lbl.setText(String.format("(%.0f, %.0f)", event.getX(), event.getY()));
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btn != null : "fx:id=\"btn\" was not injected: check your FXML file 'main.fxml'.";
        assert lbl != null : "fx:id=\"lbl\" was not injected: check your FXML file 'main.fxml'.";
        assert test != null : "fx:id=\"test\" was not injected: check your FXML file 'main.fxml'.";
    }
}
