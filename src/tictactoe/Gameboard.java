/* This class creates the gameboard, rules, and declares the winner.  
 * Declaring the winner was determined by creating 7 different methods,
 * each determining the winner in a particular directional type.
 * 
 * 
 * 
 * 
 * 
 */

package tictactoe;


public class Gameboard {

	public int turn;
	public String firstPlayer;
	public String winner = "N";

	public enum Cell {E, X, O};

	public Cell[][][] board;

	//	3D array (4x4x4) with default E 
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

	public Gameboard copy() {
		Gameboard copy = new Gameboard();
		for (int i = 0; i < this.board.length; i++) {
			for (int j = 0; j < this.board.length; j++) {
				for (int k = 0; k < this.board.length; k++) {

					copy.board[i][j][k] = this.getCell(i, j, k);

				}
			}
		}
		copy.turn = this.getTurn();
		copy.firstPlayer = this.getFirstPlayer();
		copy.winner = this.getWinner();

		return copy;
	}

	private String getWinner() {
		// TODO Auto-generated method stub
		return winner;
	}

	private String getFirstPlayer() {
		// TODO Auto-generated method stub
		return firstPlayer;
	}

	private int getTurn() {
		// TODO Auto-generated method stub
		return turn;
	}

	//	Prints out the array and replaces E with "-".  Also ends with a barrier of *'s
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

//		System.out.println("\n***********\n");
		return result;

	}

	//	gets board
	public Cell[][][] getBoard() {

		return board;

	}

	//	gets the particular cell
	public Cell getCell(int dim, int row, int col) {

		return board[dim][row][col];

	}

	//	Checks if cell is empty
	public boolean isEmpty(int dim, int row, int col) {

		if(getCell(dim, row, col)==Cell.E) {

			return true;
		}

		return false;
	}

	//	get X or O from Enum
	public Cell getEnumCell() {

		String player = getNextPlayer();

		Cell value;

		if(player.equals("X"))
			value = Cell.X;

		else
			value = Cell.O;

		return value;
	}
	public Cell getPrevCell() {
		String player = getNextPlayer();

		Cell value;

		if(player.equals("X"))
			value = Cell.O;

		else
			value = Cell.X;

		return value;
	}
	//	used to set the game board cell
	public void setCell(int dim, int row, int col ) {

		Cell value = getEnumCell();

		if(isEmpty( dim,  row, col)){

			board[dim][row][col]= value;

			turn++;
		}

		//		else System.out.println(value.toString() + " cannot play there. Choose another location for" + value.toString() + " .");


	}



	//	declares winner by running thru seven different winning possibilities **Not Efficient in CPU but efficient in mental energy
	public boolean checkWinner(Cell value) {
		if(turn >63) {
			//			System.out.println("Game is a draw.");
			return true;
		}

		if(check2DRowWinner(value) || check2DColWinner(value) || check2DDimWinner(value) || check2DDiagnolWinner(value) || check3DRowDiagnol(value) || check3DColDiagnol(value) || check3DDiagnolWinner(value)) {
			return true;
		}

		return false;

	}

	//	Checks all the 2D row spaces across one dimension
	public boolean check2DRowWinner(Cell play) {

		for(int dim = 0; dim < board.length; dim++) {// for dim
			for(int row = 0; row < board.length; row++) { //for row
				//				for(int col = 0; col < board.length; col++) {// for col

				if(board[dim][row][0] == board[dim][row][1] && board[dim][row][1]==board[dim][row][2] && board[dim][row][2]==board[dim][row][3] && board[dim][row][3].equals(play)) { 
					//						System.out.println("Row win by "+play.toString()+ " at dim=" + dim +" & row= " + row); 
					return true;
				}
			}
		}

		return false;
	}

	//	Checks all the 2D column spaces in one dimension
	public boolean check2DColWinner(Cell play) {
		for(int dim = 0; dim < board.length; dim++) { // for dim
			for(int col = 0; col < board.length; col++) { // for col
				if(board[dim][0][col] == board[dim][1][col] && board[dim][1][col]==board[dim][2][col] && board[dim][2][col]==board[dim][3][col] && board[dim][3][col].equals(play)) { 
					//					System.out.println("Column win by "+play.toString()+ " at dim=" + dim +" & col= " + col);
					return true;
				}
			}
		}

		return false;

	}

	//	Checks all the 2D dimension column spaces in one dimension
	public boolean check2DDimWinner(Cell play) {
		for(int row = 0; row < board.length; row++) {// for row
			for(int col = 0; col < board.length; col++) {// for col
				if(board[0][row][col] == board[1][row][col] && board[1][row][col]==board[2][row][col] && board[2][row][col]==board[3][row][col] && board[3][row][col].equals(play)) 
				{
					//					System.out.println("Dimensional win by "+play.toString()+ " at row=" + row +" & col= " + col);
					return true;
				}
			}
		}

		return false;
	}

	//	Checks all the 2D (up and down) diagnol spaces across multiple dimenions
	public boolean check2DDiagnolWinner(Cell play) {
		for(int dim = 0; dim < board.length; dim++) { // for dim
			if(board[dim][0][0] == board[dim][1][1] && board[dim][1][1]==board[dim][2][2] && board[dim][2][2]==board[dim][3][3] && board[dim][3][3].equals(play)||
					board[dim][0][3] == board[dim][1][2] && board[dim][1][2]==board[dim][2][1] && board[dim][2][1]==board[dim][3][0] && board[dim][3][0].equals(play)) { //for col & row || col & #-row
				//				System.out.println(" 3 Dimensional diagnol win by "+play.toString()+ " at dim=" + dim);
				return true;
			}
		}

		return false;

	}

	//	Checks all the 3D row diagnol spaces
	public boolean check3DRowDiagnol(Cell play) {
		for(int row = 0; row < board.length; row++) { // for row
			if(board[0][row][0] == board[1][row][1] && board[1][row][1] == board[2][row][2] && board[2][row][2] == board[3][row][3] && board[3][row][3].equals(play) ||
					board[0][row][3] == board[1][row][2] && board[1][row][2] == board[2][row][1] && board[2][row][1] == board[3][row][0] && board[3][row][0].equals(play)){ //for dim & col || dim & #-col
				//				System.out.println("3D diagnol row win by "+play.toString()+ " at row=" + row);
				return true;
			}
		}

		return false;
	}

	//	Checks all the 3D column diagnol spaces
	public boolean check3DColDiagnol(Cell play) {
		for(int col = 0; col < board.length; col++) { // for col
			if(board[0][0][col] == board[1][1][col] && board[1][1][col] == board[2][2][col] && board[2][2][col] == board[3][3][col] && board[3][3][col].equals(play) ||
					board[0][3][col] == board[1][2][col] && board[1][2][col] == board[2][1][col] && board[2][1][col] == board[3][0][col] && board[3][0][col].equals(play)){  // for dim & row || dim = #-row
				//				System.out.println("3D column diagnol win by "+play.toString()+ " at col=" + col);
				return true;
			}
		}

		return false;

	}
	//	Checks all the 3D column, row and dimension diagnol spaces
	public boolean check3DDiagnolWinner(Cell play) {
		// for dim & row & col || dim & #-row & col || dim & #-row & #-col || dim & row & #-col
		if(board[0][0][0] == board[1][1][1] && board[1][1][1]==board[2][2][2] && board[2][2][2]==board[3][3][3] && board[3][3][3].equals(play) ||
				board[0][0][3] == board[1][1][2] && board[1][1][2]==board[2][2][1] && board[2][2][1]==board[3][3][0] && board[3][3][0].equals(play) ||
				board[0][3][3] == board[1][2][2] && board[1][2][2]==board[2][1][1] && board[2][1][1]==board[3][0][0] && board[3][0][0].equals(play) ||
				board[0][3][0] == board[1][2][1] && board[1][2][1]==board[2][1][2] && board[2][1][2]==board[3][0][3] && board[3][0][3].equals(play)){
			//								System.out.println("3D Diagnol win by "+play.toString());
			return true;

		}

		return false;

	}

	//	Grabs next player
	public String getNextPlayer() {

		return turn % 2 == 0 ? "X":"O";
	}

	//	Use this method to determine if there's three in a row
	public void detectThree() {

		for(int dim = 0; dim < board.length; dim++) {// for dim
			for(int row = 0; row < board.length; row++) { //for row
				//				for(int col = 0; col < board.length; col++) {// for col

				if(board[dim][row][0] == board[dim][row][1] && board[dim][row][1]==board[dim][row][2] && board[dim][row][2]==board[dim][row][3]) { 
					//						System.out.println("Row win by "+play.toString()+ " at dim=" + dim +" & row= " + row); 

				}
			}
		}


	}


}



