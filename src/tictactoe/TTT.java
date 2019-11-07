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
			player = (myLine.substring(6, 7).toUpperCase());
	
			

		
		
			System.out.println(myLine);
			board.setCell(dim, row, col, player);
//			board.setCell(0, 0, 0, "X");
			board.setCell(1, 1, 0, "X");
			board.setCell(2, 2, 0, "X");
			board.setCell(3, 3, 0, "X");
			System.out.println(board);


		} while (!board.checkWinner(board.getEnumCell(player)));
		
		System.out.println(player + " won the game!");
		
		
		
	}

}
