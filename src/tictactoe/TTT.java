package tictactoe;


import java.util.Scanner;

public class TTT {

	public static void main(String[] args) throws InterruptedException {

		// create basic universal variables and new probability table
		ProbabilityTable table = new ProbabilityTable();
		Gameboard board;
		int run;
		int dim = 0;
		int row = 0;
		int col = 0;

		Scanner scan = new Scanner(System.in);

		// Takes in an int as input
		System.out.println("Please enter the number of trials to train AI?");

		// read the scanned line
		String myLine = scan.nextLine();

		String index[] = new String[3];
		int i = 0;

		for (String p : myLine.split("\\s")) {

			index[i] = p;
			i++;
		}

		int print1 = Integer.parseInt(index[0]);
		int print2 = Integer.parseInt(index[1]);
		run = Integer.parseInt(index[2]);

		//
		for (int z = 0; z < run; z++) {
			board = new Gameboard();
			// this is the Explore factor which decreases as z gets larger
			if (z > (run - z) * .5) {

				table.fillPQ();
			}
			double random = ((double) z+1)/run;
			runGame(board, table, dim, row, col, z, random);

			if ((z + 1) == print1 || (z + 1) == print2) {

				System.out.println("");
				System.out.println(z + 1 + " Games");
				table.printTable(z + 1);
				System.out.println("");
			}
		}

		System.out.println("");
		System.out.println(run + " Games");
		table.printTable(run);

		scan.close();

	}

	public static void runGame(Gameboard board, ProbabilityTable table, int dim, int row, int col, int z, double rando) {

		probCell parsePQ = null;
		String minimax = null;

		do {

			BestMove bm = new BestMove(board, table, 3);
			minimax = bm.findBestMove(); // will return null if no winning within 2 moves
			if (Math.random() < 1-rando) { // exploring randomly that's less likely with more runs
				dim = (int) (Math.random() * ((3 - 0) + 1)) + 0;
				row = (int) (Math.random() * ((3 - 0) + 1)) + 0;
				col = (int) (Math.random() * ((3 - 0) + 1)) + 0;
			} else {

				if (minimax != null && z > 400) {

					// CHECK IF SOMEONE COULD WIN BEFORE WE DETERMINE OUR MOVE
					// RUN 2 IN A WINNING ROW ALGORITHM
					// INSERT MINIMAX DETERMINATION HERE

					// parse minimax recommended location

					// UNCOMMENT THE CODE BELOW AND SET THE MINIMAX STRING VARIABLE
					dim = Integer.parseInt(minimax.substring(0, 1));
					row = Integer.parseInt(minimax.substring(2, 3));
					col = Integer.parseInt(minimax.substring(4, 5));

				} else if (table.pQ.peek() != null && z > 500) {

					parsePQ = table.pQ.poll();
					// System.out.println(parsePQ.locale.toString());

					dim = Integer.parseInt(parsePQ.locale.substring(0, 1));
					row = Integer.parseInt(parsePQ.locale.substring(2, 3));
					col = Integer.parseInt(parsePQ.locale.substring(4, 5));

				} else {

					dim = (int) (Math.random() * ((3 - 0) + 1)) + 0;
					row = (int) (Math.random() * ((3 - 0) + 1)) + 0;
					col = (int) (Math.random() * ((3 - 0) + 1)) + 0;
					// System.out.println(dim + "," + row + "," + col);
				}
			}
			board.setCell(dim, row, col);

			// System.out.println(board);

		} while (!board.checkWinner(board.getPrevCell()));

		table.recordResult(board);

	}

}
