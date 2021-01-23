package aufgabe1.mitFXML;
	
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;


public class Main extends Application {
	
	Button btn;
	Label lbl;
	int count = 0;
	
	@Override
	public void start(Stage primaryStage) throws Exception{

		btn = new Button("sag Hallo");
		btn.setOnAction(this::button);
		lbl = new Label("Reee");
		
		VBox vb = new VBox(10, btn, lbl);
		vb.setAlignment(Pos.CENTER);
		vb.setOnMouseMoved(this::mouse);
		
		Scene scene = new Scene(vb,400,400);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	private void mouse(MouseEvent e) {
		lbl.setText(String.format("(%.0f, %.0f)", e.getX(), e.getY()));
	}
	
	private void button(ActionEvent e) {
		btn.setText(++count+"");
		lbl.setText("sag Hallo "+count);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
