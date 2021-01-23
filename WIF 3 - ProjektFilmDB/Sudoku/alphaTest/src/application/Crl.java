package application;


import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

public class Crl {

    @FXML
    private VBox vb;
    
    @FXML
    private TilePane btn_overlay;
    
    @FXML
    private Pane pane;
    
    private Button[] arr = new Button[9];
    private TextField[][] tfArr = new TextField[9][9];
    private TextField current;
    
    @FXML
    void initialize() {
    	Platform.runLater( () -> vb.requestFocus() );

    	int offset = -1;
       for(int i=0; i<81; i++) {
    	   TextField tf = new TextField(); 	   
    	   tf.setEditable(false);
    	   tfArr[i/9][i%9] = tf;
    	   
    	   if(i%9==0) offset++;
    	   tf.setText( (i+offset)%9+1+"" );
       }
       
       
       //pane.toFront();
       	btn_overlay.setVisible(false);
   		HBox hb = null;
     	for(int i=0; i<arr.length; i++) {
     		if(i%3==0) {
     			if(hb!=null) btn_overlay.getChildren().add(hb);
     			hb = new HBox();
     		}
     		arr[i] = new Button();
     		hb.getChildren().add(arr[i]);   		
     		final int zahl = i+1;
     		arr[i].setOnAction(ev-> current.setText( zahl +""));
     		
     	}
   		btn_overlay.getChildren().add(hb);
       
   		btn_overlay.setOnMouseExited(ev->{
   			btn_overlay.setVisible(false);
   			vb.requestFocus();
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
   			
   		configOverly();
    }	
    
    
    private void configOverly() {
		  
    	for(int i=0; i<81; i++) {
    		int row = (i/9);
    		int col = (i%9);
    		
  //		val = vorgegebener Wert; -> Button Overlay nicht anzeigen -> nicht änderbar
//    		if(val!=null) {
//    			tfArr[row][col].setText(val+"");
//    			continue;
//    		}
    	
    		final int frow = row+1;
    		final int fcol = col+1;
    		
    		tfArr[row][col].setText(null);
    		tfArr[row][col].setOnMouseEntered(ev->{
    			current = tfArr[row][col]; 
    			System.out.println("Textfeld-Position:	Reihe: "+frow+"	Spalte: "+fcol);
    			Bounds b = current.localToScene(current.getBoundsInLocal());
    			btn_overlay.setLayoutX(b.getMinX());
    			btn_overlay.setLayoutY(b.getMinY());
    			btn_overlay.setVisible(true);
    		});
    		
    		tfArr[row][col].textProperty().addListener((ob,ov,nv)->{
    			String text = tfArr[row][col].getText();
    			if(text==null)	return;
    			// Setze bei Textveräderung den gegebenen Wert in SudokuKlasse ???
    		});
    	}
    }
    
    

    
}
