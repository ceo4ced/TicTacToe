package tictactoe;

public class Gameboard {
	
	int turn;
	
	public enum Cell {E, X, O};
	
	public Cell[][][] board;
	
	public Gameboard() {
		
		turn = 0;
		
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
	
	public Cell getEnumCell(String play) {
		
		Cell value;
		
		if(play.equals("X"))
			value = Cell.X;
		
		else
			value = Cell.O;
		
		return value;
	}
	
	public void setCell(int dim, int row, int col, String play ) {
		
		turn++;
		
		Cell value = getEnumCell(play);
		
		if(isEmpty( dim,  row, col)){
			
			board[dim][row][col]= value;
			
		}
		else System.out.println(value.toString() + " cannot play there. Choose another location for" + value.toString() + " .");
	}
	
	public String checkWinner(Cell value) {
		
		if(check2DRowWinner() || check2DColWinner() || check2DDimWinner() || check2DDiagnolWinner() || check3DRowDiagnol() || check3DColDiagnol() || check3DDiagnolWinner())
			return value.toString() + " Won";
		
		return "Next player's turn";
	}
	
	public boolean checkWinner() {
		
		if(check2DRowWinner() || check2DColWinner() || check2DDimWinner() || check2DDiagnolWinner() || check3DRowDiagnol() || check3DColDiagnol() || check3DDiagnolWinner())
			return true;
		
		return false;
	}

	public boolean check2DRowWinner() {
		
		for(int dim = 0; dim < board.length; dim++) {// for dim
			for(int row = 0; row < board.length; row++) { //for row
//				for(int col = 0; col < board.length; col++) {// for col
		
					if(board[dim][row][0] == board[dim][row][1] && board[dim][row][1]==board[dim][row][2] && board[dim][row][2]==board[dim][row][3]) 
						return true;
//					}
				}
			}
		
		return false;
	}
	
	public boolean check2DColWinner() {
		for(int dim = 0; dim < board.length; dim++) { // for dim
			for(int col = 0; col < board.length; col++) { // for col
				// for row
		if(board[dim][0][col] == board[dim][1][col] && board[dim][1][col]==board[dim][2][col] && board[dim][2][col]==board[dim][3][col]) 
			return true;
			}
		}
		return false;
	}
	
	public boolean check2DDimWinner() {
		for(int row = 0; row < board.length; row++) {// for row
			for(int col = 0; col < board.length; col++) {// for col
				// for dim
		if(board[0][row][col] == board[1][row][col] && board[1][row][col]==board[2][row][col] && board[2][row][col]==board[3][row][col]) 
			return true;
			}
		}
		
		return false;
	}
	
	public boolean check2DDiagnolWinner() {
//		int n = board.length;
		for(int dim = 0; dim < board.length; dim++) { // for dim
			// for col & row || col & #-row
		if(board[dim][0][0] == board[dim][1][1] && board[dim][1][1]==board[dim][2][2] && board[dim][2][2]==board[dim][3][3] ||
				board[dim][0][3] == board[dim][1][2] && board[dim][1][2]==board[dim][2][1] && board[dim][2][1]==board[dim][3][0])
			return true;
		}
		
		return false;
	}
	
	public boolean check3DRowDiagnol() {
		for(int row = 0; row < board.length; row++) { // for row
			// for dim & col || dim & #-col
		if(board[0][row][0] == board[1][row][1] && board[1][row][1] == board[2][row][2] && board[2][row][2] == board[3][row][3] ||
				board[0][row][3] == board[1][row][2] && board[1][row][2] == board[2][row][1] && board[2][row][1] == board[3][row][0])
			return true;
		}
		
		return false;
	}
	
	public boolean check3DColDiagnol() {
		for(int col = 0; col < board.length; col++) { // for col
			// for dim & row || dim = #-row
		if(board[0][0][col] == board[1][1][col] && board[1][1][col] == board[2][2][col] && board[2][2][col] == board[3][3][col] ||
				board[0][3][col] == board[1][2][col] && board[1][2][col] == board[2][1][col] && board[2][1][col] == board[3][0][col])
			return true;
		}
		
		return false;
	}
	
	public boolean check3DDiagnolWinner() {
		// for dim & row & col || dim & #-row & col || dim & #-row & #-col || dim & row & #-col
		
		if(board[0][0][0] == board[1][1][1] && board[1][1][1]==board[2][2][2] && board[2][2][2]==board[3][3][3] ||
				board[0][0][3] == board[1][1][2] && board[1][1][2]==board[2][2][1] && board[2][2][1]==board[3][3][0] ||
					board[0][3][3] == board[1][2][2] && board[1][2][2]==board[2][1][1] && board[2][1][1]==board[3][0][0] ||
							board[0][3][0] == board[1][2][1] && board[1][2][1]==board[2][1][2] && board[2][1][2]==board[3][0][3])
								return true;
		return false;
	}
	
	public boolean checkWinner(int dim, int row, int col) {
		
		
		//check dim win
		
			// where am i?
			// is there 
		
		//check row win
		
		//check col win
		
		//check dim diagnol win
		
		//check row diagnol win
		
		//check col diagnol win
		
		 
		
		
		return false;
		
	}
	
	
}
