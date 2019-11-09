package tictactoe;

import java.util.Scanner;



public class TTT {

	public static void main(String[] args) throws InterruptedException {
		
		ProbabilityTable table = new ProbabilityTable();
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
			if(z>49) {
				table.fillPQ();
			}
			runGame(board, table, dim, row, col);
			
		}
		
		table.printTable(run);
		
//		board = new Gameboard();
//		runPlayerVsAI( board,  scan,  dim,  row,  col);
		scan.close();
		
	}

	public static void runGame(Gameboard board, ProbabilityTable table, int dim, int row, int col) {
		
		String parsePQ;
		
		do {
			
			if(table.pQ.peek() != null) {
				
			parsePQ = table.pQ.poll();
			
			dim =  Integer.parseInt(parsePQ.substring(7, 8));
			row =  Integer.parseInt(parsePQ.substring(9, 10));
			col =  Integer.parseInt(parsePQ.substring(11, 12));
			
			
			} else {
				
			dim = (int) (Math.random()*3.4);
			row = (int) (Math.random()*3.4);
			col = (int) (Math.random()*3.4);
			
			}
			
			board.setCell(dim, row, col);	
			
			System.out.println(board);


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
