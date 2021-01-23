package aufgabe3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Start extends Application{

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		FXMLLoader lo = new FXMLLoader(getClass().getResource("KommandoKonsole.fxml"));
		primaryStage.setScene( new Scene((Pane)lo.load()));
		((KommandoKonsoleController)lo.getController()).setThis(primaryStage);
		primaryStage.setResizable(false);
		primaryStage.setOnCloseRequest(hl->{
			Alert a = new Alert(AlertType.CONFIRMATION);
			a.setTitle("Beenden");
			a.setContentText("Möchten sie wirklich schon beenden");
			a.setOnCloseRequest(vl1->{
				if(a.getResult()==null)
					return;
				if(a.getResult().getButtonData().equals(ButtonData.OK_DONE))
					System.exit(0);
			});
			a.show();
			hl.consume();
		});
		primaryStage.show();
	}

}
