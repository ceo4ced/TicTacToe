package tictactoe;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;



public class TTT {

	public static void main(String[] args) throws InterruptedException {
		
		ProbabilityTable table = new ProbabilityTable();
		Gameboard board;
		int run;
		int dim = 0;
		int row = 0; 
		int col = 0;
		boolean explore = true;
		
		Scanner scan = new Scanner(System.in);
	
		System.out.println("How many trials to train AI?");
		String myLine = scan.nextLine();
		run = Integer.parseInt(myLine);
		
		for(int z = 0; z < run; z++) {
			board = new Gameboard();
			if(z > (run-z)*.5) {
				
				table.fillPQ();
			}
			
			runGame(board, table, dim, row, col);
			
			if((z + 1)%500 == 0) {
				
				System.out.println("");
				System.out.println(z+1 + " Games");
				table.printTable(run);
				System.out.println("");
			}
		}
		
		System.out.println("");
		System.out.println(run + " Games");
		table.printTable(run);
		

		scan.close();
		
	}

	public static void runGame(Gameboard board, ProbabilityTable table, int dim, int row, int col) {
		
		probCell parsePQ = null;
		
		do {
			
			// change to explore function instead
			if(table.pQ.peek() != null) {
				
			parsePQ = table.pQ.poll();
//			System.out.println(parsePQ.locale.toString());
				 
			
			dim =  Integer.parseInt(parsePQ.locale.substring(0, 1));  
			row =  Integer.parseInt(parsePQ.locale.substring(2, 3));  
			col =  Integer.parseInt(parsePQ.locale.substring(4, 5));   
			
			} else {
	
			dim = (int) (Math.random()*((3 - 0) + 1)) + 0;
			row = (int) (Math.random()*((3 - 0) + 1)) + 0;
			col = (int) (Math.random()*((3 - 0) + 1)) + 0;
//			System.out.println(dim + "," + row + "," + col);
			}
			
			board.setCell(dim, row, col);	
			
//			System.out.println(board);


		} while (!board.checkWinner(board.getEnumCell()));
		
		table.recordResult(board);
			
		
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
