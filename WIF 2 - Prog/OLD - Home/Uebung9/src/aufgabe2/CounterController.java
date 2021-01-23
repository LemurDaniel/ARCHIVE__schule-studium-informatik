package aufgabe2;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class CounterController {
	
	private int count;
	private String[] images = {"duke.gif", "snooze.gif", "fight.gif"};
	private String path = "file:\\G:\\Nicht verschl\u00FCsselt\\WIF - Studium\\Studium\\WIF 2\\Prog 2\\weiteres\\";
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;
	
	@FXML
	private Button btnCount;
	@FXML
	private Label lblCount;
	@FXML
	private Pane paneIMG;
	@FXML
	private ImageView image;

		@FXML
		void action() {
			lblCount.setText("Zählerstand: "+ ++count);
			image.setImage(new Image(path+ images[count%images.length] ));
		}
	
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        count = 0;
        image.setImage(new Image(path+ images[0] ));
    }
}
