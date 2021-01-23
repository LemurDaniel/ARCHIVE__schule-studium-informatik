package application;
	
import java.util.ArrayList;
import java.util.List;

import gui.FensterManager;
import javafx.application.Application;
import javafx.stage.Stage;
import verwaltung.DB_Manager;


public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) throws Exception{
		
		FensterManager.setPrimaryStage( FensterManager.getAnmelden() );
		//FensterManager.getHauptSeite().show();

		//FensterManager.setSecondary(FensterManager.getListensicht());
//		Nutzer.getNutzer().anmeldenGast();

	}

	public static void main(String[] args) {
		launch(args);
	}
	
}
