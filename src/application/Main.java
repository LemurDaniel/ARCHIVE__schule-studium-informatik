package application;
	
import gui.FensterManager;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) throws Exception{
	
		//FensterManager.setPrimaryStage( FensterManager.showAnmelden() );
		FensterManager.getInstance().showAddFilm(null);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
