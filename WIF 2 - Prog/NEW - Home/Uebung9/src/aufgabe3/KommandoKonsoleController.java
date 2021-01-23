package aufgabe3;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import aufgabe3.Roboter.Winkel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class KommandoKonsoleController {
	

	private Roboter robot;
	private Stage stageAdd;
	private Stage display;
	private DisplayController dispCtrl;
	private Stage thisStage;
	
	public void setThis(Stage thisStage) {
		this.thisStage = thisStage;
		thisStage.setTitle(robot.getName().get());
	}

	private StringProperty fehler = new SimpleStringProperty();
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;
    
    @FXML
    private Pane pane;
    
    @FXML
    private RadioButton rRelativ;
    
    @FXML
    private ListView<Winkel> list;
    
    @FXML
    private Label lblTeile;
    
    @FXML
    private TextField tfWinkel;
    
    @FXML // fx:id="mFileNew"
    private MenuItem mFileNew; // Value injected by FXMLLoader

    @FXML // fx:id="mFileOpen"
    private MenuItem mFileOpen; // Value injected by FXMLLoader

    @FXML // fx:id="mFileSave"
    private MenuItem mFileSave; // Value injected by FXMLLoader
    
    @FXML
    private MenuItem mFileDefault;
    
    @FXML
    private MenuItem mFileRename;

    @FXML // fx:id="btnDel"
    private Button btnDel; // Value injected by FXMLLoader

    @FXML // fx:id="btnAdd"
    private Button btnAdd; // Value injected by FXMLLoader

    @FXML // fx:id="btnCom"
    private Button btnCom; // Value injected by FXMLLoader

    @FXML // fx:id="lblFehler"
    private Label lblFehler; // Value injected by FXMLLoader

    
    
    
    @FXML
    void btn(ActionEvent event) {
    	lblFehler.setText(null);
    	try {
    		if(event.getSource().equals(btnDel)) {
    			robot.loesche(list.getSelectionModel().getSelectedIndex());
    			display(list.getSelectionModel().getSelectedIndex());
    		}else if(event.getSource().equals(btnAdd)) {
    			if(stageAdd==null) {
    				stageAdd = new Stage();
    				stageAdd.setTitle("Körperteil hinzufügen");
    				FXMLLoader lo = new FXMLLoader(getClass().getResource("Input.fxml"));
    				stageAdd.setScene(new Scene((Pane)lo.load()));
    				stageAdd.setResizable(false);
    				((InputController)lo.getController()).set(robot, stageAdd);
    			}
    			stageAdd.show();
    			stageAdd.requestFocus();
    			stageAdd.focusedProperty().addListener((ob, oldV, newV)->{
    				if(newV.booleanValue()==false)	stageAdd.close();
    			});
    		}else if(event.getSource().equals(btnCom)) {
    			robot.bewege(list.getSelectionModel().getSelectedIndex(), tfWinkel.getText(), rRelativ.isSelected());
    		}
    	}catch(Exception e) {
    		lblFehler.setText(e.getMessage());	
    	}
    }

    @FXML
    void file(ActionEvent event) {
    	lblFehler.setText(null);
    	if(event.getSource().equals(mFileNew)) {
    		Alert a = new Alert(AlertType.CONFIRMATION);
    		a.setTitle("Neu");
    		a.setContentText("Wenn sie auf OK drücken werden alle Teile gelöscht");
    		a.show();
    		a.setOnCloseRequest(lv->{
    			if(a.getResult().equals(ButtonType.OK)) {
    				robot.getList().clear();
    				robot.randName();
    			}
    		});
    	}else if(event.getSource().equals(mFileOpen)) {
    		FileChooser fc = new FileChooser();
    		File f = fc.showOpenDialog(thisStage);
    		if(f!=null) {
    			try {
    				robot.getList().clear();
    				robot.load(f);
    				pane.setVisible(true);
    			}catch(Exception e) {
    				Alert a = new Alert(AlertType.ERROR);
    				a.setTitle(e.getClass().getSimpleName());
    				a.setContentText(e.getMessage());
    				a.show();
    			}
    		}
    	}else if(event.getSource().equals(mFileSave)) {
    		FileChooser fc = new FileChooser();
    		File f = fc.showSaveDialog(thisStage);
    		if(f!=null) {
    			try {
    				robot.save(f);
    			}catch(Exception e) {
    				lblFehler.setText(e.getMessage());
    			}
    		}
    	}else if(event.getSource().equals(mFileRename))
    		inputName();
    	else
    		robot.setStandartTeile();
    }

	private void inputName() {
		TextInputDialog tid = new TextInputDialog();
		tid.setTitle("Robotername");
		tid.setResizable(false);
		tid.setContentText("Wie soll dein Roboter heißen? ");
		tid.show();	
		tid.setOnCloseRequest(vl->{
			if(tid.getResult()== null)
				return;
    		try {
    			robot.setName(tid.getResult());
    		}catch(Exception e) {
    			Alert a = new Alert(AlertType.ERROR);
    			a.setTitle(e.getClass().getSimpleName());
    			if(e.getClass().equals(NullPointerException.class))
    				a.setContentText("Keine gültige Eingabe");
    			else a.setContentText(e.getMessage());
    			tid.close();
    			a.setOnCloseRequest(v->inputName());
    			a.show();
    		}
    	});
	}
    
    private void display(int index) {
    	if(index == -1 || index > robot.getList().size()-1) {
    		if(display != null) {
    			display.close();
    			display = null;
    		}
    		return;
    	}
    	try {
    		if(display == null || dispCtrl == null) {
    			display = new Stage(StageStyle.UNDECORATED);
    			FXMLLoader lo = new FXMLLoader(getClass().getResource("Display.fxml"));
    			display.setScene(new Scene((Pane)lo.load()));
    			dispCtrl = lo.getController();
    			display.setResizable(false);
    		}
    	}catch(Exception e) {
    		fehler.set(e.getMessage());
    	}
    	dispCtrl.set(robot, index);
		display.setX(thisStage.getX()+thisStage.getWidth());
		display.setY(thisStage.getY()+thisStage.getHeight()/4);
		display.setTitle(robot.getName()+"  |  "+robot.getBez(index));
		display.show();
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert mFileNew != null : "fx:id=\"mFileNew\" was not injected: check your FXML file 'KommandoKonsole.fxml'.";
        assert mFileOpen != null : "fx:id=\"mFileOpen\" was not injected: check your FXML file 'KommandoKonsole.fxml'.";
        assert mFileSave != null : "fx:id=\"mFileSave\" was not injected: check your FXML file 'KommandoKonsole.fxml'.";
        assert btnDel != null : "fx:id=\"btnDel\" was not injected: check your FXML file 'KommandoKonsole.fxml'.";
        assert btnAdd != null : "fx:id=\"btnAdd\" was not injected: check your FXML file 'KommandoKonsole.fxml'.";
        assert btnCom != null : "fx:id=\"btnCom\" was not injected: check your FXML file 'KommandoKonsole.fxml'.";
        assert lblFehler != null : "fx:id=\"lblFehler\" was not injected: check your FXML file 'KommandoKonsole.fxml'.";
        assert pane != null : "fx:id=\"pane\" was not injected: check your FXML file 'KommandoKonsole.fxml'.";
       
//        try {
//        	robot = new Roboter("tes");
//        }catch (Exception e) {
//        	e.printStackTrace();
//        }
 
        list.setCellFactory(vl-> new ListCell<Winkel>(){
        	@Override
            protected void updateItem(Winkel winkel, boolean empty) {
                super.updateItem(winkel, empty);
                setText(empty ? null : winkel.getBez());
            }
        });
        list.getFocusModel().focusedIndexProperty().addListener( (o, ov, nv)-> display(nv.intValue()) );
		
        robot = new Roboter();
		robot.getList().addListener(new ListChangeListener<Winkel>() {
			@Override
			public void onChanged(Change<? extends Winkel> c) {
				lblTeile.setText(robot.getList().size()+"/"+Roboter.listMax+" Körperteile");
			}
		});
		list.setItems(robot.getList());
		robot.getName().addListener(ch->thisStage.setTitle(robot.getName().get()));
		lblTeile.setText(robot.getList().size()+"/"+Roboter.listMax+" Körperteile");
    
//		fehler.addListener(l->{
//			lblFehler.setText(fehler.get());
//			if(fehler.get()==null)
//				return;
//			new Thread(new Runnable() {
//				@Override
//				public void run() {
//					try {
//						Thread.sleep(1000);
//					}catch(Exception e){
//						e.printStackTrace();
//						System.exit(0);
//					}
//					fehler.set(null);
//					return;
//				}
//			}).start();
//		});
    }
}