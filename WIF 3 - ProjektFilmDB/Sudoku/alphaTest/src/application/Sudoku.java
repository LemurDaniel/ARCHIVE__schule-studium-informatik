package application;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.layout.RowConstraints;

public class Sudoku {
	
	public static final int HARDMODE = 17;
	public static final int MEDIUM = HARDMODE*2;
	public static final int EASY = MEDIUM*2;
	public static final int BABY = 80;
	
	private Integer grid[][];
	private Integer solved[][];
	private List<Integer> leftPos;	
	
	public Sudoku() {
		leftPos = new ArrayList<>();
		solved = new Integer[9][9];
	}
	
	public void generateNew(int mode) {
		if(mode<17) mode = 17;
		grid= new Integer[9][9];
		leftPos.clear();
		for(int i=0; i<81; i++) leftPos.add(i);
		generate();
		trim(mode);
	}
	
	
	private void generate() {
		int cycles = 0;
		while(leftPos.size()>0) {
			cycles++;
			System.out.println("Cycles: "+cycles);
						
			int random = (int)(Math.random()*leftPos.size());
			int randPos = leftPos.get(random);
			
			int randZahl;
			int count = 0;
			do 	{
				randZahl = ((int)(Math.random()*9))+1;
				if(count++>20) {
					removeAround(randPos);
					count =0;
				}
			}
			while(contains(randZahl, randPos/9, randPos%9));
			
			Integer i = leftPos.remove(random);
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
		
		for(int i=0; i<9; i++) {
			for(int j=0; j<9; j++) {
				System.out.printf(" %5s  ", grid[i][j]+"");
			}
			System.out.println();
		}
		System.out.println();
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
	
	
	private boolean removeAround(int pos) {
		int col = pos%9;
		int row = pos/9;
		
		int i = (int)(Math.random()*3);
		if(i==0) 		col = (int)(Math.random()*9);
		else if(i==1)	row = (int)(Math.random()*9);
		else if(i==2) {
			if(row<3)		row=0;
			else if(row<6) 	row=3; 
			else 			row=6;
			
			if(col<3)		col=0;
			else if(col<6) 	col=3; 
			else 			col=6;
			
			col = ((int)(Math.random()*3))+col;
			row = ((int)(Math.random()*3))+row;
		}
		System.out.println("remove: "+row+"  "+col);
		if(grid[row][col] == null)	return false;
		grid[row][col] = null;
		leftPos.add(row*9+col);
		return true;
	}
	
	
	
	
	public Integer getValueAt(int row, int col) {
		return grid[row][col];
	}
	public boolean setValueAt(Integer zahl, int row, int col) {
		grid[row][col] = zahl;
		if(solved[row][col]!=zahl)	System.out.println("Wroooooooong!!!!");
		return solved[row][col]==zahl;
	}
}
