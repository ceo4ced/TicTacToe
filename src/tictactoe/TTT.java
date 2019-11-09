package tictactoe;

import tictactoe.Gameboard.Cell;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;



public class TTT {

	public static void main(String[] args) throws InterruptedException {
		
		Gameboard board = new Gameboard();
		int run = 1;
		int dim = 0;
		int row = 0; 
		int col = 0;
		Scanner scan = new Scanner(System.in);
	
		for(int z = 0; z < run; z++) {
		
			runGame(board, dim, row, col, scan);
		
		}
		
	}

	public static void runGame(Gameboard board, int dim, int row, int col, Scanner scan) {
		
		do {
			if(board.turn<1) {
			System.out.println("Your Turn");
			String myLine = scan.nextLine();
			dim = Integer.parseInt(myLine.substring(0, 1));
			row = Integer.parseInt(myLine.substring(2,3));
			col = Integer.parseInt(myLine.substring(4, 5));
			System.out.println(myLine);
			board.setCell(dim, row, col);
		
			} else {
				dim = (int) (Math.random()*3.5);
				row = (int) (Math.random()*3.5);
				col = (int) (Math.random()*3.5);
				board.setCell(dim, row, col);	
				
				
				
			}
			
			System.out.println(board);


		} while (!board.checkWinner(board.getEnumCell()));
			
		
	}
	
	
	
	
	
}
