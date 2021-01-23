package aufgabe2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Start extends Application{

	@Override
	public void start(Stage stage)throws Exception{
		
		stage.setTitle("Multi Purpose Counter - javaFX");
		stage.setScene( new Scene( (Pane)FXMLLoader.load(getClass().getResource("Counter.fxml")) ) );
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
