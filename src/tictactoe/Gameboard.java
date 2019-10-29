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
		
		for(int i=0;i<board.length;i++) {  //i = dimension j = row k = column
			for(int j=0;j<board.length;i++) {
				if(check2DRowWinner(i,j))	// Across Row Winner
					return "Winner!";
			}
			
			// Along Col Winner
			for(int k=0;k<board.length;k++) {
				if(check2DColWinner( i, k)) 
					return "Winner!";			
				}
			
			// Diagnol Winner
			if(check2DDiagnolWinner(i))   // All 64 spots are filled
				return "Winner!";
			
			
		}
		
		return null;
	}

	
	public boolean check2DRowWinner(int i, int j) {
		if(board[i][j][0] == board[i][j][1] && board[i][j][1]==board[i][j][2] && board[i][j][2]==board[i][j][3]) 
			return true;
		return false;
	}
	
	public boolean check2DColWinner(int i, int k) {
		if(board[i][0][k] == board[i][1][k] && board[i][1][k]==board[i][2][k] && board[i][2][k]==board[i][3][k]) 
			return true;
		return false;
	}
	
	public boolean check2DDiagnolWinner(int i) {
		if(board[i][0][0] == board[i][1][1] && board[i][1][1]==board[i][2][2] && board[i][2][2]==board[i][3][3] ||
				board[i][0][3] == board[i][1][2] && board[i][1][2]==board[i][2][1] && board[i][2][1]==board[i][3][0])
			return true;
		return false;
	}
	
//	public boolean check3DRowWinner(int i, int j, int k) {
//		if(board[i][j][k]==board[i][j][k] && board[i][j][k] == board[i][j][k] && board[i][j][k]==board[i][j][k])
//			return true;
//		return false;
//	}
	
	public boolean check3DColWinner(int i, int j, int k) {
		if(board[i][j][k]==board[i][j][k] && board[i][j][k] == board[i][j][k] && board[i][j][k]==board[i][j][k])
			return true;
		return false;
	}
	
	public boolean check3DDiagnolWinner(int i, int j, int k) {
		if(board[0][0][0] == board[1][1][1] && board[1][1][1]==board[2][2][2] && board[2][2][2]==board[3][3][3] ||
				board[0][0][3] == board[1][1][2] && board[1][1][2]==board[2][2][1] && board[2][2][1]==board[3][3][0] ||
					board[0][3][3] == board[1][2][2] && board[1][2][2]==board[2][1][1] && board[2][1][1]==board[3][0][0] ||
							board[0][3][0] == board[1][2][1] && board[1][2][1]==board[2][1][2] && board[2][1][2]==board[3][0][3])
								return true;
		return false;
	}
	
	public boolean check3DRowDiagnol(int i, int j, int k) {
		if(board[0][j][0] == board[1][j][1] && board[1][j][1] == board[2][j][2] && board[2][j][2] == board[3][j][3] ||
				board[0][j][3] == board[1][j][2] && board[1][j][2] == board[2][j][1] && board[2][j][1] == board[3][j][0])
			return true;
		return false;
	}
	
	public boolean check3DColDiagnol(int i, int j, int k) {
		if(board[0][0][k] == board[1][1][k] && board[1][1][k] == board[2][2][k] && board[2][2][k] == board[3][3][k] ||
				board[0][3][k] == board[1][2][k] && board[1][2][k] == board[2][1][k] && board[2][1][k] == board[3][0][k])
			return true;
		return false;
	}	
}
