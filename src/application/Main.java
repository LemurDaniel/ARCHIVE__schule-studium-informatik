package application;
	
import gui.FensterManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) throws Exception{
		//FensterManager.setPrimaryStage( FensterManager.getAnmelden() );
		FensterManager.getInstance().getHauptSeite().show();
	}

	public static void main(String[] args) {
		launch(args);
	}
	
}
