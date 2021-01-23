package application;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.layout.RowConstraints;

public class Sudoku {
	
	public static final Modus HARDMODE = 	new Modus("HARDMODE", 17);
	public static final Modus MEDIUM = 		new Modus("MEDIUM", 17*2);
	public static final Modus EASY = 		new Modus("EASY", 17*4);
	public static final Modus BABY = 		new Modus("BABY", 80);
	
	private Integer grid[][];
	private Integer solved[][];
	private List<Integer> leftPos, wrong;	
	private int cycles;
	
	public Sudoku() {
		leftPos = new ArrayList<>();
		wrong = new ArrayList<>();
		solved = new Integer[9][9];
		generateNew(MEDIUM);
	}
	
	public void generateNew(Modus mode) {
		int z = Math.max(17, mode.getValue());
		z = Math.min(81, mode.getValue());
		
		grid= new Integer[9][9];
		wrong.clear();
		leftPos.clear();
		for(int i=0; i<81; i++) leftPos.add(i);
		generate();
		trim(z);
	}
	
	
	private void generate() {
		cycles = 0;
		while(leftPos.size()>0) {
			cycles++;
						
			int random = (int)(Math.random()*leftPos.size());
			int randPos = leftPos.get(random);
			
			int randZahl = 0;
			int count = 0;
			do 	{
				if(count++>20) {
					removeAround(randZahl, getBlock(randZahl, randPos));
					count =0;
				}
				randZahl = ((int)(Math.random()*9))+1;
			}
			while(contains(randZahl, randPos/9, randPos%9));
			
			leftPos.remove(random);
			grid[randPos/9][randPos%9]		= Integer.valueOf(randZahl);
			solved[randPos/9][randPos%9]	= grid[randPos/9][randPos%9];
		}
	}		
	
	private void trim(int vorgabe) {
		vorgabe = 81 - vorgabe;
		while(leftPos.size()!= vorgabe) {
			int random = (int)(Math.random()*81);
			if(leftPos.contains(random)) continue;
			
			leftPos.add(random);
			grid[random/9][random%9] = null;
		}
		
		System.out.println("Generated in "+cycles+" Cycles");
		for(int i=0; i<9; i++) {
			for(int j=0; j<9; j++) {
				System.out.printf(" %5s  ",solved[i][j]+"");
			}
			System.out.println();
		}
	}
	
	
	private boolean contains(int zahl, int row, int col) {
		if(spalteContains(zahl, col))	return true;
		if(reiheContains(zahl, row))	return true;
		return grid3x3Contains(zahl, row, col);
	}
	
	private boolean spalteContains(int zahl, int col) {
		for(int i=0; i<grid.length; i++) {
			if(grid[i][col]!=null && grid[i][col]==zahl)	return true;
		}
		return false;
	}
	private boolean reiheContains(int zahl, int row) {
		for(int i=0; i<grid[row].length; i++) {
			if(grid[row][i]!=null && grid[row][i]==zahl)	return true;
		}
		return false;
	}	
	private boolean grid3x3Contains(int zahl, int row, int col) {
		if(row<3)		row=3;
		else if(row<6) 	row=6; 
		else 			row=9;
		
		if(col<3)		col=3;
		else if(col<6) 	col=6; 
		else 			col=9;
		
		for(int i=row-3; i<row; i++) {
			for(int j=col-3; j<col; j++) {
				if(grid[i][j]!=null && grid[i][j]==zahl)	return true;
			}
		}
		return false;
	}
	
	
	
	private int getBlock(int zahl, int pos) {
		int col = pos%9;
		int row = pos/9;
		
		for(int i=0; i<grid.length; i++) {
			if(grid[i][col]!=null && grid[i][col]==zahl)	return i*9+col;
		}
		
		for(int i=0; i<grid[row].length; i++) {
			if(grid[row][i]!=null && grid[row][i]==zahl)	return row*9+i;
		}
		
		if(row<3)		row=3;
		else if(row<6) 	row=6; 
		else 			row=9;
		
		if(col<3)		col=3;
		else if(col<6) 	col=6; 
		else 			col=9;
		
		for(int i=row-3; i<row; i++) {
			for(int j=col-3; j<col; j++) {
				if(grid[i][j]!=null && grid[i][j]==zahl)	return i*9+j;
			}
		}
		return 0;
	}

	
	private boolean removeAround(int zahl, int pos) {
		int col = pos%9;
		int row = pos/9;

		if(grid[row][col] == null)	return false;
		grid[row][col] = null;
		leftPos.add(row*9+col);
		return true;
	}
	
	
	
	
	public Integer getValueAt(int row, int col) {
		return grid[row][col];
	}
	public void setValueAt(Integer zahl, int row, int col) {
		grid[row][col] = zahl;
		Integer pos = row*9+col;
		leftPos.remove(pos);
		if(solved[row][col]!=zahl)	wrong.add(pos);
		else						wrong.remove(pos);	
	}
	
	
	
	public boolean pruefe() throws Exception{
		if(leftPos.size()!=0)	throw new Exception("Es sind nicht alle Felder ausgefüllt. ");
		if(wrong.size()!=0) {
			StringBuilder sb = new StringBuilder("Folgende Positionen sind Falsch: ");
			for(Integer i: wrong) {
				sb.append("\nReihe:  "+(i/9+1)+"  |  Spalte:  "+(i%9+1));
			}
			throw new Exception(sb.toString());
		}
		return true;
	}



	public static class Modus {
		private String name;
		private int value;
		
		private Modus(String name, int value) {
			this.name = name;
			this.value = value;
		}
		public int getValue() {
			return value;
		}
		@Override
		public String toString() {
			return name;
		}
	}



	public int getSolvedValueAt(int row, int col) {
		return solved[row][col];
	}
}
