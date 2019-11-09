package tictactoe;

import tictactoe.Gameboard.Cell;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;



public class TTT {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		Gameboard board = new Gameboard();
		int run = 1;
		int dim = 0;
		int row = 0; 
		int col = 0;
		String player = null;
		Scanner scan = new Scanner(System.in);
	
		for(int z = 0; z < run; z++) {
		
			runGame(board, dim, row, col, scan, player);
		
		}
		
	}

	public static void runGame(Gameboard board, int dim, int row, int col, Scanner scan, String player) {
		
		do {
			if(board.turn<1) {
			System.out.println("Your Turn");
			String myLine = scan.nextLine();
			
			
			
			dim = Integer.parseInt(myLine.substring(0, 1));
			row = Integer.parseInt(myLine.substring(2,3));
			col = Integer.parseInt(myLine.substring(4, 5));
			player = (myLine.substring(6, 7).toUpperCase());
			board.firstPlayer = player;
//			System.out.println(myLine);
			board.setCell(dim, row, col, player);
			board.setCell(2, 2, 0, "O");
			} else {
				dim = (int) (Math.random()*3.5);
				row = (int) (Math.random()*3.5);
				col = (int) (Math.random()*3.5);
				
				board.setCell(dim, row, col, board.getNextPlayer());	
				
				
			}
			
			System.out.println(board);


		} while (!board.checkWinner(board.getEnumCell(player)));
			
		
	}
	
	public void runPvP(Gameboard board, int dim, int row, int col) {
		
		
		
		
	}
	
	public void runCvC(Gameboard board, int dim, int row, int col) {
		
		
		
		
	}
	
	public void runTest(Gameboard board, int dim, int row, int col) {
		
		
		
		
	}
	
}
