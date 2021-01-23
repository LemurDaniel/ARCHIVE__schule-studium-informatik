package application;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class Main2 extends Application {
	Label meldung = new Label();
	Button b1 = new Button("OK"), b2 = new Button("Cancel");
	TextField tfpw = new TextField();
	
	@Override
	public void start(Stage stage1) throws Exception {
		b1.setOnAction(e -> b1_task(e));
		b2.setOnAction(this::b1_task);
		b2.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				tfpw.setText(event.getSceneX()+"");
			}
			
		});
		b1.setDefaultButton(true);
		
		FlowPane h1 = new FlowPane(Orientation.HORIZONTAL, 10, 10, new Label("Enter your password:"), tfpw);
		FlowPane h2 = new FlowPane(Orientation.HORIZONTAL, 10, 10, b2, b1);h2.setAlignment(Pos.CENTER);
		FlowPane v1 = new FlowPane(Orientation.VERTICAL, 10, 10, h1, h2, meldung);
		v1.setPadding(new Insets(10));
		
		Scene scene1 = new Scene(v1, 300, 120);
		
		stage1.setScene(scene1);
		stage1.setTitle("Beispiel");
		stage1.show();
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
