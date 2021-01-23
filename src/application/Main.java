package application;
	
import java.util.ArrayList;
import java.util.List;

import gui.FensterManager;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) throws Exception{
		FensterManager.setPrimaryStage( FensterManager.getAnmelden() );
		//FensterManager.getInstance().getHauptSeite().show();

		//FensterManager.setSecondary(FensterManager.getListensicht());
//		Nutzer.getNutzer().anmeldenGast();

		
		List<Integer> li = new ArrayList<>();
		li.add(new Integer(1));
		li.add(new Integer(2));
		for(Integer i:li) {
			li.remove(i);
		}
//		}
	}

	public static void main(String[] args) {
		launch(args);
	}
	
}
