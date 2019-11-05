package tictactoe;

import tictactoe.Gameboard.Cell;

public class TTT {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Gameboard board = new Gameboard();
		
		// NEED TO ADD IN PUT VARIABLES FOR PLAYING
		System.out.println(board);
		board.setCell(0, 1, 1, Cell.X);
		System.out.println(board);
		board.setCell(0, 1, 1, Cell.X);
		System.out.println(board);
	}

}
