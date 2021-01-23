package aufgabe4;
/**
 * Sample Skeleton for 'STV.fxml' Controller Class
 */

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import student.Student;
import student.Studentendaten;
import student.Studentendaten.LadeFehler;

public class STVController{
	
	private static STVController instance;
	public static STVController instance() {
		return instance;
	}
	
	Stage addStudent;
    public void addStudent() {
    	addStudent = new Stage();
    	addStudent.setTitle("Neuen Studenten hinzufügen");
    	addStudent.setResizable(false);
        try {
        	addStudent.setScene(new Scene(FXMLLoader.load(getClass().getResource("STZ.fxml"))));
        }catch(Exception e) {
        	Alert a = new Alert(AlertType.ERROR);
        	a.setTitle(e.getClass().getSimpleName());
        	a.setContentText(e.getMessage());
        }
        addStudent.show();
    }
	public void closeMe() {
		addStudent.close();
	}
	

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="mNew"
    private MenuItem mNew; // Value injected by FXMLLoader
    
    @FXML // fx:id="table"
    private TableView<Student> table; // Value injected by FXMLLoader
    @FXML // fx:id="tMat"
    private TableColumn<Student, Number> tMat; // Value injected by FXMLLoader

    @FXML // fx:id="tName"
    private TableColumn<Student, String> tName; // Value injected by FXMLLoader

    @FXML // fx:id="tEcts"
    private TableColumn<Student, Number> tEcts; // Value injected by FXMLLoader

    @FXML // fx:id="tSem"
    private TableColumn<Student, Number> tSem; // Value injected by FXMLLoader

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
//        assert save != null : "fx:id=\"save\" was not injected: check your FXML file 'STV.fxml'.";
 //       assert mAdd != null : "fx:id=\"mAdd\" was not injected: check your FXML file 'STV.fxml'.";
        assert mNew != null : "fx:id=\"mNew\" was not injected: check your FXML file 'STV.fxml'.";
  //      assert mAddNew != null : "fx:id=\"mAddNew\" was not injected: check your FXML file 'STV.fxml'.";
//        assert mStat != null : "fx:id=\"mStat\" was not injected: check your FXML file 'STV.fxml'.";
        assert table != null : "fx:id=\"table\" was not injected: check your FXML file 'STV.fxml'.";
        assert tMat != null : "fx:id=\"tMat\" was not injected: check your FXML file 'STV.fxml'.";
        assert tName != null : "fx:id=\"tName\" was not injected: check your FXML file 'STV.fxml'.";
        assert tEcts != null : "fx:id=\"tEcts\" was not injected: check your FXML file 'STV.fxml'.";
        assert tSem != null : "fx:id=\"tSem\" was not injected: check your FXML file 'STV.fxml'.";
        instance = this;
        
        table.setItems(Studentendaten.instance().getList());
        tMat.setCellValueFactory(data->data.getValue().getMartrikelNr());
        tName.setCellValueFactory(data->data.getValue().getName());
        tEcts.setCellValueFactory(data->data.getValue().getEcts());
        tSem.setCellValueFactory(data->data.getValue().getSemester());
        Studentendaten.instance().setDefault();
        
    }
    
    public void delete() {
    	if(table.getSelectionModel().getSelectedIndex()==-1) {
    		Alert a = new Alert(AlertType.ERROR);
        	a.setTitle("Löschen");
        	a.setContentText("Es wurde kein Student ausgewählt");
        	a.show();
    	}else
    		Studentendaten.instance().remove(table.getSelectionModel().getSelectedIndex());
    }
    public void stat() {
    	String av = String.format("Semesterdurchschnitt: %.4f", Studentendaten.instance().semesterDurchschnitt());
    	String reg = "Anzahl regulär Studierender: "+Studentendaten.instance().regulaerStudierende();
    	Alert a = new Alert(AlertType.INFORMATION);
    	a.setTitle("Statistiken");
    	a.setContentText(av+"\n"+reg);
    	a.show();
    }
    public void clear() {
    	Studentendaten.instance().getList().clear();
    }
    public void setDefault() {
    	Studentendaten.instance().getList().clear();
    	Studentendaten.instance().setDefault();
    }
    
    public void save() {
    	File file = new FileChooser().showSaveDialog(null);
    	if(file==null)
    		return;
    	try {
    		Studentendaten.instance().saveToFile(file);
    	}catch(Exception e) {
    		Alert a = new Alert(AlertType.ERROR);
    		a.setTitle(e.getClass().getSimpleName());
    		a.setContentText(e.getMessage());
    		a.show();
    		return;
    	}
    	
		Alert a = new Alert(AlertType.INFORMATION);
		a.setTitle("Speichern");
		a.setContentText("Speichern erfolgreich");
		a.show();
    }
    
    public void load() {
    	File file = new FileChooser().showOpenDialog(null);
    	if(file==null)
    		return;
    	try {
    		Studentendaten.instance().loadFile(file);
    	}catch(Exception e) {
    		Alert a;
    		if(e.getClass().equals(LadeFehler.class) && ((LadeFehler)e).error == false)
    			a = new Alert(AlertType.INFORMATION);
    		else
    			a = new Alert(AlertType.ERROR);
    		a.setTitle(e.getClass().getSimpleName());
    		a.setContentText(e.getMessage());
    		a.show();
    		return;
    	}
    	
		Alert a = new Alert(AlertType.INFORMATION);
		a.setTitle("Laden");
		a.setContentText("Laden erfolgreich");
		a.show();
    }
}
