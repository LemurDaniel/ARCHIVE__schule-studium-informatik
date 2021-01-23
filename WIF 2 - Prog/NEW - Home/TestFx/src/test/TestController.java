package test;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class TestController {

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	
	@FXML // fx:id="lblPW"
	private Label lblPW; // Value injected by FXMLLoader

	@FXML // fx:id="tfPW"
	private TextField tfPW; // Value injected by FXMLLoader

	@FXML // fx:id="btnCancel"
	private Button btnCancel; // Value injected by FXMLLoader

	@FXML // fx:id="btnOK"
	private Button btnOK; // Value injected by FXMLLoader
	
	@FXML
	void action(ActionEvent event) {
		if(event.getSource().equals(btnOK))	tfPW.setText("");
		else tfPW.setText("");
	}
	
	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert lblPW != null : "fx:id=\"lblPW\" was not injected: check your FXML file 'Test.fxml'.";
		assert tfPW != null : "fx:id=\"tfPW\" was not injected: check your FXML file 'Test.fxml'.";
		assert btnCancel != null : "fx:id=\"btnCancel\" was not injected: check your FXML file 'Test.fxml'.";
		assert btnOK != null : "fx:id=\"btnOK\" was not injected: check your FXML file 'Test.fxml'.";

	}

}
