package tictactoe;

import tictactoe.Gameboard.Cell;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;



public class TTT {

	public static void main(String[] args) throws InterruptedException {
		
		Gameboard board;
		int run;
		int dim = 0;
		int row = 0; 
		int col = 0;
		Scanner scan = new Scanner(System.in);
	
		System.out.println("How many trials to train AI?");
		String myLine = scan.nextLine();
		run = Integer.parseInt(myLine);
		
		for(int z = 0; z < run; z++) {
			board = new Gameboard();
			runGame(board, dim, row, col);
		
		}
		
//		board = new Gameboard();
//		runPlayerVsAI( board,  scan,  dim,  row,  col);
		
	}

	public static void runGame(Gameboard board, int dim, int row, int col) {
		
		do {

			dim = (int) (Math.random()*3.5);
			row = (int) (Math.random()*3.5);
			col = (int) (Math.random()*3.5);
			board.setCell(dim, row, col);	
			
			System.out.println(board);


		} while (!board.checkWinner(board.getEnumCell()));
			
		
	}
	

	
	public static void runPlayerVsAI(Gameboard board, Scanner scan, int dim, int row, int col) {
		
		System.out.println("Shall we play a game?");
		System.out.println("Yes or No");
		String myLine = scan.nextLine();
		if(myLine =="yes") {
			System.out.println("Which player am I?");
			myLine = scan.nextLine();
			if(myLine == "X") {
				
			}
		dim = Integer.parseInt(myLine.substring(0, 1));
		row = Integer.parseInt(myLine.substring(2,3));
		col = Integer.parseInt(myLine.substring(4, 5));
		board.setCell(dim, row, col);
		} else {}
		
		do {
			System.out.println("Player X's turn");
			myLine = scan.nextLine();
			dim = Integer.parseInt(myLine.substring(0, 1));
			row = Integer.parseInt(myLine.substring(2,3));
			col = Integer.parseInt(myLine.substring(4, 5));
			board.setCell(dim, row, col);
			System.out.println(board);
			
			System.out.println("Player O's turn");
			myLine = scan.nextLine();
			dim = Integer.parseInt(myLine.substring(0, 1));
			row = Integer.parseInt(myLine.substring(2,3));
			col = Integer.parseInt(myLine.substring(4, 5));
			board.setCell(dim, row, col);
			System.out.println(board);
			
		}while (!board.checkWinner(board.getEnumCell()));
		
	}
	
	
}
