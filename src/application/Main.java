package application;
	
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fxControls.FloatMinMaxTextField;
import fxControls.IntegerMinMaxTextField;
import gui.FensterManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) throws Exception{
		FensterManager.setPrimaryStage( FensterManager.getAnmelden() );
		//FensterManager.getInstance().getHauptSeite().show();

		
	}

	public static void main(String[] args) {
		launch(args);
	}
	
}
