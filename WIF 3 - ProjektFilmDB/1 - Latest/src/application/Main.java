package application;
	


import gui.FensterManager;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {
	
	private static Application instance;
	public static Application getApplication() {
		return instance;
	}
	
	
	@Override
	public void start(Stage primaryStage) throws Exception{
			
		instance = this;	
		FensterManager.setPrimaryStage( FensterManager.getAnmelden() );
	}

	public static void main(String[] args) {
		launch(args);
	}
		
}
