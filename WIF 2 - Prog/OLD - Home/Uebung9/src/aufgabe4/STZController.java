package aufgabe4;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import student.Student;
import student.Studentendaten;

public class STZController {

	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField tfMat;

    @FXML
    private TextField tfNam;

    @FXML
    private TextField tfEcts;

    @FXML
    private ComboBox<Integer> cSem;

    @FXML
    private Button btnErs;

    @FXML
    void initialize() {
        assert tfMat != null : "fx:id=\"tfMat\" was not injected: check your FXML file 'STZ.fxml'.";
        assert tfNam != null : "fx:id=\"tfNam\" was not injected: check your FXML file 'STZ.fxml'.";
        assert tfEcts != null : "fx:id=\"tfEcts\" was not injected: check your FXML file 'STZ.fxml'.";
        assert cSem != null : "fx:id=\"cSem\" was not injected: check your FXML file 'STZ.fxml'.";
        assert btnErs != null : "fx:id=\"btnErs\" was not injected: check your FXML file 'STZ.fxml'.";

        ObservableList<Integer> list = FXCollections.observableArrayList();
        for(int i=1; i<=Studentendaten.MAX_SEMESTER; i++)
        	list.addAll(i);
        cSem.setItems(list);
       cSem.getSelectionModel().selectedItemProperty().addListener(l->checkEingaben());
    }
    
    public void checkMat(KeyEvent e) {
    	if(!Character.isDigit(e.getCharacter().charAt(0)) || tfMat.getLength() == Studentendaten.MATRIKELNUMMER_LAENGE) {
    		e.consume();
    		return;
    	}
    	if(tfMat.getLength()==Studentendaten.MATRIKELNUMMER_LAENGE-1) {
    		if(Studentendaten.instance().getList().stream().filter(st->st.getMartrikelNr().get()==Integer.parseInt(tfMat.getText()+e.getCharacter())).count()>0) {
    			e.consume();
    			Alert a = new Alert(AlertType.ERROR);
    			a.setTitle("Matrikelnummer bereits vorhanden");
    			a.setContentText("Die Martrikelnummer "+tfMat.getText()+e.getCharacter()+" ist bereits vergeben");
    			a.show();
    		}
    	}
    }
    
    public void checkECTS(KeyEvent e) {
    	if(!Character.isDigit(e.getCharacter().charAt(0)) || tfEcts.getLength() == (Studentendaten.MAX_ECTS+"").length()) {
    		e.consume();
    		return;
    	}
    	int ects = Integer.parseInt(tfEcts.getText()+e.getCharacter());
    	if(ects > Studentendaten.MAX_ECTS) {
    		e.consume();
    		Alert a = new Alert(AlertType.ERROR);
			a.setTitle("Zu viele ECTS");
			a.setContentText("Der Wert "+ects+" überschreitet die Maximale Anzahl an ECTS: "+Studentendaten.MAX_ECTS);
			a.show();
    	}
    }
    
    public void checkName(KeyEvent event) {
    	try {
    		Studentendaten.instance().checkName(tfNam.getText()+event.getCharacter());
    	}catch(Exception e) {
    		event.consume();
    		Alert a = new Alert(AlertType.ERROR);
			a.setTitle(e.getClass().getSimpleName());
			a.setContentText(e.getMessage());
			a.show();
    	}
    }
    
    public void checkEingaben() {
    	boolean test = true;
    	if(tfMat.getText().length()!=Studentendaten.MATRIKELNUMMER_LAENGE) test = false;
    	if(tfEcts.getText().length() == 0) test = false;
    	if(tfNam.getText().length()==0) test = false;
    	if(cSem.getSelectionModel().isEmpty()) test = false;
    	btnErs.setDisable(!test);
    }
    
    public void create() {
    	try {
    		Studentendaten.instance().add(new Student(Integer.parseInt(tfMat.getText()), Integer.parseInt(tfEcts.getText()), cSem.getSelectionModel().getSelectedItem(), tfNam.getText()));
    		STVController.instance().closeMe();
    	}catch(Exception e) {
    		Alert a = new Alert(AlertType.ERROR);
			a.setTitle(e.getClass().getSimpleName());
			a.setContentText(e.getMessage());
			a.show();
    	}
    }
}
