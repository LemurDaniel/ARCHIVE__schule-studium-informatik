package sudoku;

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
	
}
