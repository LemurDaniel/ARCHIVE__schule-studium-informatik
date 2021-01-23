package application;
	
import Verwaltungen.DB_Manager;
import gui.FensterManager;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) throws Exception{
		
		DB_Manager.InstanzAnmelden();
		FensterManager.getInstance().setPrimaryStage( FensterManager.getInstance().showAnmelden() );
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
