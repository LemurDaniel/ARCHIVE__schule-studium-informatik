package application;
	
import bla.DB_Manager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;


public class Main extends Application {
	
	static {
		DB_Manager.InstanzAnmelden();
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception{
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Hauptseite.fxml"));
		loader.load();
		((HauptseiteCtrl)loader.getController()).setStage(primaryStage);
		
		primaryStage.setScene(new Scene(loader.getRoot()));
		primaryStage.setTitle("Filmdatenbank");
		primaryStage.setOnCloseRequest(wev->{
			wev.consume();
			Alert a = new Alert(AlertType.CONFIRMATION);
			a.setTitle("Beenden");
			a.setContentText("Möchten sie wirklich schon beenden?");
			a.show();
			a.setOnCloseRequest(dev->{
				if(a.getResult().getButtonData().equals(ButtonData.OK_DONE)) {
					DB_Manager.InstanzAbmelden();
					System.exit(0);
				}
					
			});
		});
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
