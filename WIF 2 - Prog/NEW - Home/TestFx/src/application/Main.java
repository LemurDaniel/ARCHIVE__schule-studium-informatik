package application;
	
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Main extends Application {
	Label meldung;
	Button b1, b2;
	TextField tfpw;
	
	@Override
	public void start(Stage stage) throws Exception {
		tfpw = new TextField();
		HBox h1 = new HBox(10,new Label("Enter your password:"), tfpw);
		
		
		b1 = new Button("OK");
		b1.setOnAction(e -> b1_task(e));
		b2 = new Button("Cancel");
		b2.setOnAction(this::b1_task);
		
		HBox h2 = new HBox(10, b1, b2);
		h2.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
		
		meldung = new Label();	
		VBox v1 = new VBox(10,h1, h2, meldung);
		v1.setPadding(new Insets(10));
		
		Scene scene = new Scene(v1);
		
		stage.setScene(scene);
		stage.setTitle("Beispiel");
		stage.show();
	}
	
	private void b1_task(ActionEvent e) {
		if(e.getSource().equals(b1)) meldung.setText(tfpw.getText());
		else if(e.getSource().equals(b2)) meldung.setText(null);
		tfpw.clear();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}