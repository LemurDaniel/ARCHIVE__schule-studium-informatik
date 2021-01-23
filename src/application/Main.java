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
import verwaltung.DB_Manager;
import verwaltung.Nutzer;
import verwaltung.entitaeten.Film;
import verwaltung.verwaltungen.Filmverwaltung;


public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) throws Exception{
		FensterManager.setPrimaryStage( FensterManager.getAnmelden() );
		//FensterManager.getInstance().getHauptSeite().show();

//		Nutzer.getNutzer().anmeldenGast();
//		Filmverwaltung fvw = new Filmverwaltung();
//		fvw.test();
//		Film f = fvw.getList().get(0);
//		for(int i=0; i<1000; i++) {
//			Film ff = fvw.addFilm(f.getTitel(), f.getGenres(), f.getDauer(), f.getErscheinungsjahr(), null);
//			System.out.println(ff.toString()+ "-------------"+i);
//		}
	}

	public static void main(String[] args) {
		launch(args);
	}
	
}
