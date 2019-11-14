package tictactoe;

import tictactoe.Gameboard.Cell;

/*
 * 
findBestMove() function will use minimax() and return the best move the maximizer can make




Pseudocode for findBestMove():

function findBestMove(board):
    bestMove = NULL
    for each move in board :
        if current move is better than bestMove
            bestMove = current move
    return bestMove




Pseudocode for MiniMax():

function minimax(board, depth, isMaximizingPlayer):

    if current board state is a terminal state :
        return value of the board

    if isMaximizingPlayer :
        bestVal = -INFINITY 
        for each move in board :
            value = minimax(board, depth+1, false)
            bestVal = max( bestVal, value) 
        return bestVal

    else :
        bestVal = +INFINITY 
        for each move in board :
            value = minimax(board, depth+1, true)
            bestVal = min( bestVal, value) 
        return bestVal
 */

public class BestMove {
	private Gameboard game;
	private ProbabilityTable table;
	private int depth;

	public BestMove(Gameboard g, ProbabilityTable t, int d) {
		game = g.copy();
		depth = d;
		table = t;

	}

	public String findBestMove() {
		String bestMove = null;
		int bestVal = 0;
		/*
		 * if (table.pQ.peek() != null) { for (probCell parsePQ : table.pQ) { int dim =
		 * Integer.parseInt(parsePQ.locale.substring(0, 1)); int row =
		 * Integer.parseInt(parsePQ.locale.substring(2, 3)); int col =
		 * Integer.parseInt(parsePQ.locale.substring(4, 5)); Gameboard test =
		 * game.copy(); test.setCell(dim, row, col);
		 * test.checkWinner(test.getEnumCell()); int placeholder = maxMove(test, 0); //
		 * try minimax if (placeholder > bestVal) { // if there are any spots that will
		 * lead to a win update the values bestVal = placeholder; bestMove = dim + "." +
		 * row + "." + col; } } } /*else {
		 */
		for (int dim = 0; dim < 4; dim++) {
			for (int row = 0; row < 4; row++) {
				for (int col = 0; col < 4; col++) {
					if (game.getCell(dim, row, col) == Cell.E) { // if this spot hasn't been played
						String special = specialCase(dim, row, col);
						if (special != null) {
							return special;
						}
						
						// make a copy of the board and test each move on that copy of the board
						Gameboard fake = game.copy();
						fake.setCell(dim, row, col);
						fake.checkWinner(fake.getPrevCell());
						int placeholder = maxMove(fake, 0); // try minimax
						if (placeholder > bestVal) { // if there are any spots that will lead to a win update the values
							bestVal = placeholder;
							bestMove = dim + "." + row + "." + col;
						}
					}
				}
			}
		}
		// this returns the best move or null if no move will lead to winning

		if (bestMove != null) {
			System.out.println("minimax works");
		}
		return bestMove;
	}

	public String specialCase(int dim, int row, int col) {
		// see if this spot leads to a win
		if (findThree(dim, row, col, game.getEnumCell())) {
			// if so play it
			return (dim + "." + row + "." + col);
		} else if (findThree(dim, row, col, game.getPrevCell())) {
			// otherwise block any opponents chances of winning
			return (dim + "." + row + "." + col);
		}
		
		// see if this spot leads to more than one 3 in a row
		if (findTwo(dim, row,col, game.getEnumCell()) > 1) {
			// if so play it
			return (dim + "." + row + "." + col);
		} else if (findTwo(dim, row, col, game.getPrevCell()) > 1) {
			// otherwise prevent any opponents from taking a prime spot
			return (dim + "." + row + "." + col);
		}
		return null;
	}
	public int minimax(Gameboard board, int depth, boolean isMaximizingPlayer) {
		if (board.winner != "N") { // if someone has won
			if (isMaximizingPlayer) { // if it's the maximizing player, then return 1
				return this.depth - depth;
			} else {
				return depth - this.depth;
			}
		}

		if (depth == this.depth) { // if the board doesn't reach a terminating state within the moves needed
			return 0;
		}
		if (isMaximizingPlayer) {
			int bestVal = Integer.MIN_VALUE;
			// for each move in board :
			for (int dim = 0; dim < 4; dim++) {
				for (int row = 0; row < 4; row++) {
					for (int col = 0; col < 4; col++) {
						if (board.getCell(dim, row, col) == Cell.E) { // if this spot hasn't been played
							// make a copy of the board and test each move on that copy of the board
							if (findThree(dim, row, col, board.getEnumCell())) {
								return this.depth - depth;
							}
							Gameboard fake = board.copy();
							fake.setCell(dim, row, col);
							fake.checkWinner(fake.getPrevCell());
							int value = minimax(fake, depth + 1, false); // call minimax
							bestVal = max(bestVal, value); // choose best value for maximizing player
							return bestVal;
						}
					}
				}
			}

		} else {
			int bestVal = Integer.MAX_VALUE;
			// for each move in board :
			for (int dim = 0; dim < 4; dim++) {
				for (int row = 0; row < 4; row++) {
					for (int col = 0; col < 4; col++) {
						if (board.getCell(dim, row, col) == Cell.E) { // if this spot hasn't been played
							// make a copy of the board and test each move on that copy of the board
							if (findThree(dim, row, col, board.getEnumCell())) {
								return depth - this.depth;
							}
							Gameboard fake = board.copy(); // IS THIS COPYING THE BOARD AT EACH DIM,ROW,COL ITERATION?
							fake.setCell(dim, row, col);
							fake.checkWinner(fake.getPrevCell());
							int value = minimax(fake, depth + 1, true); // call the minimax
							bestVal = min(bestVal, value); // choose best value for minimizing player
							return bestVal;
						}
					}
				}
			}
		}
		return 0;
	}

	public int miniMove(Gameboard board, int depth) { // gets lowest value from moves
		if (depth > this.depth) {
			return 0;
		}

		int bestVal = Integer.MAX_VALUE;
		if (table.pQ.peek() != null) {
			for (probCell parsePQ : table.pQ) {
				int dim = Integer.parseInt(parsePQ.locale.substring(0, 1));
				int row = Integer.parseInt(parsePQ.locale.substring(2, 3));
				int col = Integer.parseInt(parsePQ.locale.substring(4, 5));
				Gameboard test = board.copy();
				test.setCell(dim, row, col);
				test.checkWinner(test.getPrevCell());
				if (test.winner != "N") {
					return depth - this.depth;
				}
				int value = maxMove(test, depth + 1);
				bestVal = min(bestVal, value);
				return bestVal;
			}
		} /*
		 * else { for (int dim = 0; dim < 4; dim++) { for (int row = 0; row < 4; row++)
		 * { for (int col = 0; col < 4; col++) { if (board.getCell(dim, row, col) ==
		 * Cell.E) { Gameboard test = board.copy(); test.setCell(dim, row, col);
		 * test.checkWinner(test.getEnumCell()); if (test.winner != "N") { return -1; }
		 * int value = maxMove(test, depth + 1); bestVal = min(bestVal, value); return
		 * bestVal; } } } } }
		 */
		return bestVal;
	}

	public int maxMove(Gameboard board, int depth) { // gets lowest value from moves
		if (depth > this.depth) {
			return 0;
		}

		int bestVal = Integer.MIN_VALUE;

		if (table.pQ.peek() != null) {
			for (probCell parsePQ : table.pQ) {
				int dim = Integer.parseInt(parsePQ.locale.substring(0, 1));
				int row = Integer.parseInt(parsePQ.locale.substring(2, 3));
				int col = Integer.parseInt(parsePQ.locale.substring(4, 5));
				Gameboard test = board.copy();
				test.setCell(dim, row, col);
				test.checkWinner(test.getPrevCell());
				if (test.winner != "N") {
					return this.depth - depth;
				}
				int value = miniMove(test, depth + 1);
				bestVal = max(bestVal, value);
				return bestVal;
			}
		} /*
		 * else { for (int dim = 0; dim < 4; dim++) { for (int row = 0; row < 4; row++)
		 * { for (int col = 0; col < 4; col++) { if (board.getCell(dim, row, col) ==
		 * Cell.E) { Gameboard test = board.copy(); test.setCell(dim, row, col);
		 * test.checkWinner(test.getEnumCell()); if (test.winner != "N") { return 1; }
		 * int value = miniMove(test, depth + 1); bestVal = max(bestVal, value); return
		 * bestVal; } } } } }
		 */
		return bestVal;
	}

	public int getBoardValue(Gameboard board, Cell player) {
		int value = 0;
		for (int dim = 0; dim < 4; dim++) {
			for (int row = 0; row < 4; row++) {
				for (int col = 0; col < 4; col++) {
					if (board.getCell(dim, row, col) == Cell.E) {
						value += 0;
					} else if (board.getCell(dim, row, col) == player) {
						value += table.getVal(dim, row, col);
					} else {
						value -= table.getVal(dim, row, col);
					}
				}
			}
		}
		return value;
	}

	private Cell getOpponent(Cell player) {
		if (player == Cell.O) {
			return Cell.X;
		}
		return Cell.O;
	}

	private boolean findThree(int dim, int row, int col, Cell player) {
		if (count2DRow(dim, row, player) == 3) {
			return true;
		} else if (count2DCol(dim, col, player) == 3) {
			return true;
		} else if (count2DDim(row, col, player) == 3) {
			return true;
		} else if (count2DDiagnolA(dim, player) == 3) {
			return true;
		} else if (count2DDiagnolB(dim, player) == 3) {
			return true;
		} else if (count3DRowDiagnolA(row, player) == 3) {
			return true;
		} else if (count3DRowDiagnolB(row, player) == 3) {
			return true;
		} else if (count3DRowDiagnolA(row, player) == 3) {
			return true;
		} else if (count3DColDiagnolA(col, player) == 3) {
			return true;
		} else if (count3DColDiagnolA(col, player) == 3) {
			return true;
		} else if (count3DDiagnolA(player) == 3) {
			return true;
		} else if (count3DDiagnolB(player) == 3) {
			return true;
		} else if (count3DDiagnolC(player) == 3) {
			return true;
		} else if (count3DDiagnolD(player) == 3) {
			return true;
		}
		return false;
	}

	private int findTwo(int dim, int row, int col, Cell player) {
		int count = 0;
		if (count2DRow(dim, row, player) == 2) {
			count++;
		} 
		if (count2DCol(dim, col, player) == 2) {
			count++;
		} 
		if (count2DDim(row, col, player) == 2) {
			count++;
		} 
		if (count2DDiagnolA(dim, player) == 2) {
			count++;
		} 
		if (count2DDiagnolB(dim, player) == 2) {
			count++;
		} 
		if (count3DRowDiagnolA(row, player) == 2) {
			count++;
		} 
		if (count3DRowDiagnolB(row, player) == 2) {
			count++;
		} 
		if (count3DRowDiagnolA(row, player) == 2) {
			count++;
		} 
		if (count3DColDiagnolA(col, player) == 2) {
			count++;
		} 
		if (count3DColDiagnolA(col, player) == 2) {
			count++;
		} 
		if (count3DDiagnolA(player) == 2) {
			count++;
		} 
		if (count3DDiagnolB(player) == 2) {
			count++;
		} 
		if (count3DDiagnolC(player) == 2) {
			count++;
		} 
		if (count3DDiagnolD(player) == 2) {
			count++;
		}
		return count;
	}

	public int count2DRow(int dim, int row, Cell player) {
		int count = 0;
		for (int col = 0; col < 4; col++) {
			if (game.getCell(dim, row, col) == getOpponent(player)) {
				return -1;
			} else if (game.getCell(dim, row, col) == player) {
				count++;
			}
		}
		return count;
	}

	public int count2DCol(int dim, int col, Cell player) {
		int count = 0;
		for (int row = 0; row < 4; row++) {
			if (game.getCell(dim, row, col) == getOpponent(player)) {
				return -1;
			} else if (game.getCell(dim, row, col) == player) {
				count++;
			}
		}
		return count;
	}

	public int count2DDim(int row, int col, Cell player) {
		int count = 0;
		for (int dim = 0; dim < 4; dim++) {
			if (game.getCell(dim, row, col) == getOpponent(player)) {
				return -1;
			} else if (game.getCell(dim, row, col) == player) {
				count++;
			}
		}
		return count;
	}

	public int count2DDiagnolA(int dim, Cell player) {
		int count = 0;
		for (int row = 0; row < 4; row++) {
			if (game.getCell(dim, row, row) == getOpponent(player)) {
				return -1;
			} else if (game.getCell(dim, row, row) == player) {
				count++;
			}
		}
		return count;
	}

	public int count2DDiagnolB(int dim, Cell player) {
		int count = 0;
		for (int row = 0; row < 4; row++) {
			if (game.getCell(dim, row, 3 - row) == getOpponent(player)) {
				return -1;
			} else if (game.getCell(dim, row, 3 - row) == player) {
				count++;
			}
		}
		return count;
	}

	public int count3DRowDiagnolA(int row, Cell player) {
		int count = 0;
		for (int dim = 0; dim < 4; dim++) {
			if (game.getCell(dim, row, dim) == getOpponent(player)) {
				return -1;
			} else if (game.getCell(dim, row, dim) == player) {
				count++;
			}
		}
		return count;
	}

	public int count3DRowDiagnolB(int row, Cell player) {
		int count = 0;
		for (int dim = 0; dim < 4; dim++) {
			if (game.getCell(dim, row, 3 - dim) == getOpponent(player)) {
				return -1;
			} else if (game.getCell(dim, row, 3 - dim) == player) {
				count++;
			}
		}
		return count;
	}

	public int count3DColDiagnolA(int col, Cell player) {
		int count = 0;
		for (int row = 0; row < 4; row++) {
			if (game.getCell(row, row, col) == getOpponent(player)) {
				return -1;
			} else if (game.getCell(row, row, col) == player) {
				count++;
			}
		}
		return count;
	}

	public int count3DColDiagnolB(int col, Cell player) {
		int count = 0;
		for (int row = 0; row < 4; row++) {
			if (game.getCell(row, 3 - row, col) == getOpponent(player)) {
				return -1;
			} else if (game.getCell(row, 3 - row, col) == player) {
				count++;
			}
		}
		return count;
	}

	public int count3DDiagnolA(Cell player) {
		int count = 0;
		for (int row = 0; row < 4; row++) {
			if (game.getCell(row, row, row) == getOpponent(player)) {
				return -1;
			} else if (game.getCell(row, row, row) == player) {
				count++;
			}
		}
		return count;
	}

	public int count3DDiagnolB(Cell player) {
		int count = 0;
		for (int row = 0; row < 4; row++) {
			if (game.getCell(row, row, 3 - row) == getOpponent(player)) {
				return -1;
			} else if (game.getCell(row, row, 3 - row) == player) {
				count++;
			}
		}
		return count;
	}

	public int count3DDiagnolC(Cell player) {
		int count = 0;
		for (int row = 0; row < 4; row++) {
			if (game.getCell(row, 3 - row, 3 - row) == getOpponent(player)) {
				return -1;
			} else if (game.getCell(row, 3 - row, 3 - row) == player) {
				count++;
			}
		}
		return count;
	}

	public int count3DDiagnolD(Cell player) {
		int count = 0;
		for (int row = 0; row < 4; row++) {
			if (game.getCell(row, 3 - row, row) == getOpponent(player)) {
				return -1;
			} else if (game.getCell(row, 3 - row, row) == player) {
				count++;
			}
		}
		return count;
	}

	private static int min(int a, int b) {
		if (a < b) {
			return a;
		} else {
			return b;
		}
	}

	private static int max(int a, int b) {
		if (a > b) {
			return a;
		} else {
			return b;
		}
	}
}
