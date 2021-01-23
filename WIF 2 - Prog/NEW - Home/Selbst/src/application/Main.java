package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception{
		
		FXMLLoader lo = new FXMLLoader(getClass().getResource("BuchungInput.fxml"));
		primaryStage.setScene(new Scene((Pane)lo.load()));
		
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
