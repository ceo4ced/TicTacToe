package tictactoe;

import tictactoe.Gameboard.Cell;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;



public class TTT {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		Gameboard board = new Gameboard();
		int dim, row, col;
		String player = null;
		// NEED TO ADD IN PUT VARIABLES FOR PLAYING
		
		
//		System.out.println("Your Turn");
		Scanner scan = new Scanner(System.in);
	
		
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
			} else {
				dim = (int) (Math.random()*3.5);
				row = (int) (Math.random()*3.5);
				col = (int) (Math.random()*3.5);
				
				board.setCell(dim, row, col, board.getNextPlayer(board.turn));	
				
				
			}
			
			System.out.println(board);


		} while (!board.checkWinner(board.getEnumCell(player)));
		
		
		
		
		
	}

}
