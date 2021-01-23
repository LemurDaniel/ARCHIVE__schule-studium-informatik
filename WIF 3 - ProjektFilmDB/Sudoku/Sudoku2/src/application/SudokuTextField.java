package application;

import javafx.scene.control.TextField;
import javafx.scene.effect.BlendMode;

public class SudokuTextField extends TextField{

	private Sudoku sudoku;
	private int row, col;
	private boolean vorgabe;
	
	public SudokuTextField(Sudoku sudoku, int row, int col) {
		this.sudoku = sudoku;
		this.row = row;
		this.col = col;
	}
	
	public void setValue(String val) {
		if(val==null)	{
			setText(null);
			return;
		}
		setValue(Integer.parseInt(val));
	}
	public void setValue(int val) {
		setText(val+"");
		sudoku.setValueAt(val, row, col);
	}
	
	
	public void setVorgabe(Integer val) {
		if(val==null) {
			vorgabe=false;
			setText(null);
			setBlendMode(null);
		}
		else {
			vorgabe=true;
			setText(val+"");
			setBlendMode(BlendMode.DARKEN);
		}
	}
	
	public boolean isVorgegeben() {
		return vorgabe;
	}

	public void unmark() {
		setBlendMode( isVorgegeben()? BlendMode.DARKEN:null );
		getStyleClass().remove("mark");
		getStyleClass().remove("markF");
		getStyleClass().remove("markF2");
	}

	public void mark() {
		setBlendMode( null );
		getStyleClass().add( "mark");
	}
	
	public void markFehler() {
		setBlendMode( isVorgegeben()? BlendMode.DARKEN:null );
		getStyleClass().add( isVorgegeben()? "markF2":"markF"   );
	}
	
}
