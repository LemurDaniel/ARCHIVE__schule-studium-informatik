package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		primaryStage.setScene( new Scene( FXMLLoader.load(getClass().getResource("Test.fxml")) ));
		primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
