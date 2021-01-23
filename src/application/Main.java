package application;
	
import gui.FensterManager;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) throws Exception{
		FensterManager.setPrimaryStage( FensterManager.getAnmelden() );
		//FensterManager.getInstance().getHauptSeite().show();

		FensterManager.setSecondary(FensterManager.getListensicht());
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
