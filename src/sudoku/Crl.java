package sudoku;


import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import sudoku.Sudoku.Modus;

public class Crl {

    @FXML
    private VBox vb;
    
    @FXML
    private VBox btn_overlay;
    
    @FXML
    private Button btn_pruefe;

    @FXML
    private Button btn_new;

    @FXML
    private Button btn_solve;

    @FXML
    void action(ActionEvent event) {
    	try {
    		if(event.getSource()==btn_pruefe) 		pruefe();
    		else if(event.getSource()==btn_new)		neuesGrid();
    		else if(event.getSource()==btn_solve)	solve();
    	}catch(Exception e) {
    		Alert a = new Alert(AlertType.ERROR);
    		a.setContentText(e.getMessage());
    		a.initModality(Modality.APPLICATION_MODAL);
    		a.show();
    	}
    }
    
    private void pruefe() throws Exception {
		sudoku.pruefe();
		Alert a = new Alert(AlertType.INFORMATION);
		a.setTitle("Erfolg");
		a.setHeaderText("Herzlichen Glückwunsch");
		a.setContentText("Sie haben das Sudoku erfolgreich gelöst");
		a.setGraphic(new ImageView(getClass().getResource("success.png").toString()));
		a.show();
	}

    private Thread th;
	private void solve() {
		if(th!=null)	th.interrupt();
		th = new Thread(()->{
			for(int i=0; i<9; i++) {
				final int row = i;
				for(int j=0; j<9; j++) {
					if(Thread.interrupted())	return;
					final int col = j;
					if(tfArr[i][j].isVorgegeben())	continue;
					tfArr[i][j].setValue(null);
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						return;
					}
					Platform.runLater(()->{
						tfArr[row][col].setValue(sudoku.getSolvedValueAt(row, col));
					});
				}
			}
		});
		th.start();

	}

	private void neuesGrid() {
		Alert a = new Alert(AlertType.CONFIRMATION);
		ChoiceBox<Modus> choice = new ChoiceBox<>();
		choice.getItems().add(Sudoku.HARDMODE);
		choice.getItems().add(Sudoku.MEDIUM);
		choice.getItems().add(Sudoku.EASY);
		choice.getItems().add(Sudoku.BABY);
		choice.getSelectionModel().select(1);
		VBox vb = new VBox(choice);
		vb.setPadding(new Insets(10,0,10,20));
    	a.getDialogPane().setContent(vb);
    	a.setHeaderText("Wählen sie eine Schwierigkeitsstufe");
    	a.setTitle("Neues Sudoku Feld");
    	a.setGraphic(new ImageView(getClass().getResource("sudoku.png").toString()));
    	
    	a.setOnCloseRequest(ev->create(choice.getSelectionModel().getSelectedItem()));
    	a.initModality(Modality.APPLICATION_MODAL);
    	a.show();
	}
	private void create(Modus m) {
		if(m==null)	return;
		sudoku.generateNew(m);
    	for(int i=0; i<81; i++) tfArr[i/9][i%9].setVorgabe(sudoku.getValueAt(i/9, i%9));
	}
	
	
	private Sudoku sudoku = new Sudoku();
    private Button[] arr = new Button[9];
    private SudokuTextField[][] tfArr = new SudokuTextField[9][9];
    private SudokuTextField current;
    private int curRow, curCol;
    
    @FXML
    void initialize() {
    	
    	Platform.runLater( () -> vb.requestFocus() );

       for(int i=0; i<81; i++) {
   			int row = (i/9);
   			int col = (i%9);
    	   
   			SudokuTextField tf = new SudokuTextField(sudoku, row, col); 	   
    	   	tf.setEditable(false);
    	   	tfArr[row][col] = tf;
    	    	      		
   			tf.setOnMouseEntered(ev->{
   				if(tf.isVorgegeben()) return;
   				current = tfArr[row][col]; 
   				curRow = row;
   				curCol = col;
				Bounds b = tf.localToScene(tf.getBoundsInLocal());
				btn_overlay.setLayoutX(b.getMinX());
				btn_overlay.setLayoutY(b.getMinY());
				btn_overlay.setVisible(true);
				blend();
   			});
			
       }
       
       
       	btn_overlay.setVisible(false);
   		HBox hb = null;
     	for(int i=0; i<arr.length; i++) {
     		if(i%3==0) {
     			if(hb!=null) btn_overlay.getChildren().add(hb);
     			hb = new HBox();
     		}
     		arr[i] = new Button(i+1+"");
     		hb.getChildren().add(arr[i]);   	
     		arr[i].getStyleClass().add("miniButton");
     		arr[i].setOnAction(ev-> {
     			current.setValue( ((Button)ev.getSource()).getText() );
     			blend();
     		});
     	}
   		btn_overlay.getChildren().add(hb);
       
   		btn_overlay.setOnMouseExited(ev->{
   			btn_overlay.setVisible(false);
   			vb.requestFocus();
   			unblend();
   		});
   		
   		
   	    	
   		for(int j=0; j<9; j+=3) {
   			HBox hb1 = new HBox();
   			for(int i=0; i<9; i+=3) {
   				VBox vb1 = new VBox();
   	   			vb1.getStyleClass().add("grid3x3");
   	   			vb1.getChildren().add( new HBox(tfArr[j][i],  	tfArr[j][i+1],	 	tfArr[j][i+2]) );
   				vb1.getChildren().add( new HBox(tfArr[j+1][i],	tfArr[j+1][i+1],	tfArr[j+1][i+2]) );
   				vb1.getChildren().add( new HBox(tfArr[j+2][i], 	tfArr[j+2][i+1], 	tfArr[j+2][i+2]) );
   				hb1.getChildren().add(vb1);
   			}
   			vb.getChildren().add(hb1);
   		}
   	
//			for(int i3=0; i3<100; i3++) {
//				System.out.println(i3);
//				sudoku.generateNew(Sudoku.BABY);
//			}
   		create(Sudoku.MEDIUM);
    }	
 
    
    
    private void blend() {
    	unblend();
		Integer value = sudoku.getValueAt(curRow, curCol);
		for(int i2=0; i2<9; i2++) {
			if(curRow!=i2 && value!=null && sudoku.getValueAt(i2, curCol)==value)	tfArr[i2][curCol].markFehler();
			else if(!tfArr[i2][curCol].isVorgegeben()) 								tfArr[i2][curCol].mark();
		}
		for(int i2=0; i2<9; i2++) {
			if(curCol!=i2 && value!=null && sudoku.getValueAt(curRow, i2)==value)	tfArr[curRow][i2].markFehler();
			else if(!tfArr[curRow][i2].isVorgegeben())							tfArr[curRow][i2].mark();
		}
		
		int cLimit, rLimit;
		if(curCol<3) cLimit=3;
		else if(curCol<6) cLimit=6;
		else cLimit = 9;
		if(curRow<3) rLimit=3;
		else if(curRow<6) rLimit=6;
		else rLimit = 9;
		for(int i2=cLimit-3; i2<cLimit; i2++) {
			for(int i3=rLimit-3; i3<rLimit; i3++) {
				if( (curRow!=i3 || curCol!=i2) && value!=null && sudoku.getValueAt(i3, i2)==value)	tfArr[i3][i2].markFehler();
				else if(!tfArr[i3][i2].isVorgegeben())											tfArr[i3][i2].mark();
			}
		}
    }
    
    private void unblend() {
		for(int i2=0; i2<9; i2++) 	tfArr[i2][curCol].unmark();
		for(int i2=0; i2<9; i2++)  	tfArr[curRow][i2].unmark();
		
		int cLimit, rLimit;
		if(curCol<3) cLimit=3;
		else if(curCol<6) cLimit=6;
		else cLimit = 9;
		if(curRow<3) rLimit=3;
		else if(curRow<6) rLimit=6;
		else rLimit = 9;
		for(int i2=cLimit-3; i2<cLimit; i2++)
			for(int i3=rLimit-3; i3<rLimit; i3++) tfArr[i3][i2].unmark();
    }
    
}
