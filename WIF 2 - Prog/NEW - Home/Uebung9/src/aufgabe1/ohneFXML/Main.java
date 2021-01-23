package aufgabe1.ohneFXML;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class Main extends Application {
	
	Button btn;
	Label lbl;
	int count = 0;
	
	@Override
	public void start(Stage primaryStage) throws Exception{


		
		Scene scene = new Scene((Pane)FXMLLoader.load(getClass().getResource("main.fxml")));
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
