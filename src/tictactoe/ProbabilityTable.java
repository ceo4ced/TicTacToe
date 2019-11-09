package tictactoe;

import java.text.DecimalFormat;

import tictactoe.Gameboard.Cell;

/*
 * What the Probability table needs:
- list out all the squares: 4x4x4
- list out numbers corresponding to these squares - utility values for each squares (3 sig digits)
	- each time there is a win, the winner's squares are noted - even if they did not contribute to the 4 in a row
	- these numbers will be indicated in decimal < 1 --> out of total number of wins
	- i.e. square 28 was used in 10 wins out of total 15 wins --> will have 0.6667 next to square 28
- after more iterations of running the game, the probability table should change to indicate that:
	- 8 squares in the middle have highest frequency of being in a winning game
	- 8 corner squares have second highest frequency of being in a winning game
	- etc. 
- show three seperate tables after 100 trials, 500 trials, and 2000 trials
- stores all the values for winner board after each game trial, print at 100 trials,... 




Psuedocode for probabilityTable():


function probabilityTable():

For (int wins = 0; wins < 100, wins++){
	check the board for wins;
	win is recorded on the table
		out of total games played (should return a decimal number)
print table after 100 wins


For (int wins = 0; wins < 500, wins++){
	check the board for wins;
	win is recorded on the table
		out of total games played (should return a decimal number)
print table after 500 wins


For (int wins = 0; wins < 2000, wins++){
	check the board for wins;
	win is recorded on the table
		out of total games played (should return a decimal number)
print table after 2000 wins
 */
public class ProbabilityTable {
	public double[][][] table;
	private static DecimalFormat df3 = new DecimalFormat("##.###");

	public ProbabilityTable() {
		table = new double[4][4][4];
	}

	public void printTable(int trials) {
		for (int dim = 0; dim < 4; dim++) {
			for (int row = 0; row < 4; row++) {
				for (int col = 0; col < 4; col++) {
					System.out.print(df3.format(table[dim][row][col]/trials) + " ");
				}
				System.out.println();
			}
			System.out.println();
		}
	}

	public void recordResult(Gameboard g) {
		// find winning side
		Cell winner;
		if (g.turn <= 63) {
			if ((g.turn - 1) % 2 == 0) {
				winner = Cell.X;
			} else {
				winner = Cell.O;
			}
			for (int dim = 0; dim < 4; dim++) {
				for (int row = 0; row < 4; row++) {
					for (int col = 0; col < 4; col++) {
						if (g.getCell(dim, row, col) == winner) {
							table[dim][row][col] += 1;
						} /*else {
							table[dim][row][col] -= 1;
						}*/
					}
				}
			}
		}
		System.out.println("recorded");
	}
}
