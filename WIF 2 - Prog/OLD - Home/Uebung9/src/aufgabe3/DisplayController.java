package aufgabe3;



import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;

import javafx.scene.control.TextField;

import javafx.scene.control.Slider;

import javafx.scene.control.Label;

public class DisplayController {
	
	private ChangeListener<Number> listen;
	
	@FXML
	private TextField tfBez;
	@FXML
	private TextField tfWink;
	@FXML
	private Slider slider;
	@FXML
	private Label lblFehler;

	public void set(Roboter r, int id) {
		if(listen != null)
			slider.valueProperty().removeListener(listen);
		
		slider.setMajorTickUnit(r.getMajorT(id));
		slider.setMin(r.getMinorT(id));
		slider.setShowTickLabels(true);
		slider.setShowTickMarks(true);
		
		tfBez.setText(r.getBez(id));
		tfWink.setText(r.getWinkel(id)+"");
		slider.setMax(r.getMax(id));
		slider.setMin(r.getMin(id));
		slider.setValue(r.getWinkel(id));

		r.getIntProp(id).addListener(new InvalidationListener() {		
			@Override
			public void invalidated(Observable observable) {
				slider.setValue(r.getWinkel(id));
				tfWink.setText(r.getWinkel(id)+"");
				observable.toString();
			}
		});		
		
		listen = (ob, oV, nV) -> {
				lblFehler.setText(null);
				try {
					r.bewege(id, (int)slider.getValue(), false);
				}catch(Exception e) {
					lblFehler.setText(e.getMessage());
				}
		};
		slider.valueProperty().addListener(listen);
	}
}
