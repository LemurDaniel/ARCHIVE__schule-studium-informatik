package aufgabe4;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.stage.Stage;

public class Start extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Scene sc = new Scene(FXMLLoader.load(getClass().getResource("STV.fxml")));

		primaryStage.setScene(sc);
		primaryStage.setResizable(false);
		primaryStage.setTitle("Studentenverwaltung");
		primaryStage.show();
		
		primaryStage.setOnCloseRequest(eh->{
			eh.consume();
			Alert a = new Alert(AlertType.CONFIRMATION);
			a.setTitle("Beenden");
			a.setContentText("Möchten sie wirklich schon beenden?");
			a.show();
			a.setOnCloseRequest(lv->{
				if(a.getResult().getButtonData().equals(ButtonData.OK_DONE))
					System.exit(0);
			});
		});
	}

}
