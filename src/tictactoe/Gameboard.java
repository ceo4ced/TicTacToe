package tictactoe;

public class Gameboard {
	
	public enum Cell {E, X, O};
	
	public Cell[][][] board;
	
	public Gameboard() {
		
		board = new Cell[4][4][4];
		
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				for (int k = 0; k < board.length; k++) {
					
					board[i][j][k] = Cell.E;
					
					
				}
			}
		}
		
		
	}
	
	public String toString() {
		
		String result = "";
		
		for(Cell[][] dim : board) {
			for(Cell[] row : dim) {
				for(Cell col : row) {
					if(col== Cell.E)
						result += "-";
					else
						result += col;	
				}
				result += "\n";
			}
			result += "\n";
		}
		
		System.out.println("\n***********\n");
		return result;
		
	}
	

	
	public Cell[][][] getBoard() {
		
		return board;
		
	}

	public Cell getCell(int dim, int row, int col) {
		
		return board[dim][row][col];
		
	}
	
	public boolean isEmpty(int dim, int row, int col) {
		
		if(getCell(dim, row, col)==Cell.E) {
			
			return true;
		}
		
		return false;
	}
	
	public void setCell(int dim, int row, int col, Cell value) {
		
		
		if(isEmpty( dim,  row, col)){
			
			board[dim][row][col]= value;
		}
		else System.out.println("Can't play there. Choose another location.");
	}
	
	
	public String gameResults() {
		
		if() {  // Four X's in a row
			return "X won!";
		}
		else if() {  // Four O's in a row
			return "O won!";
		}
		else if() {  // All 64 spots are filled
			
			return "Draw!";
		}
		
		return null;
	}

	
	
	//  eeee
	//  eeee
	//  eeee
	//  eeee

	//  eeee
	//  eeee
	//  eeee
	//  eeee

	//  eeee
	//  eeee
	//  eeee
	//  eeee
	
	//  eeee
	//  eeee
	//  eeee
	//  eeee

}
