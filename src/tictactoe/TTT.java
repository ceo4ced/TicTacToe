package tictactoe;

import tictactoe.Gameboard.Cell;
import java.util.Scanner;



public class TTT {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Gameboard board = new Gameboard();
		
		// NEED TO ADD IN PUT VARIABLES FOR PLAYING
		
		
//		System.out.println("Your Turn");
		Scanner scan = new Scanner(System.in);
		
		String player;
		
		do {
			System.out.println("Your Turn");
			String myLine = scan.nextLine();
			
	
			
			int dim = Integer.parseInt(myLine.substring(0, 1));
			int row = Integer.parseInt(myLine.substring(2,3));
			int col = Integer.parseInt(myLine.substring(4, 5));
			player = myLine.substring(6, 7);
	
			
//		System.out.println(dim);
//		System.out.println(row);
//		System.out.println(col);
//		System.out.println(player);
		
		
			System.out.println(myLine);
			board.setCell(dim, row, col, player);
			System.out.println(board);

	
		} while (board.checkWinner());
		
		System.out.println(player + " won the game!");
		
		
		
	}

}
