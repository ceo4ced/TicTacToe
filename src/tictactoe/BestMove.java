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
	private Gameboard g;
	private ProbabilityTable table;
	private Cell player;

	public BestMove(Gameboard game, ProbabilityTable t) {
		g = game;
		table = t;
		if (game.turn % 2 == 0) {
			player = Cell.X;
		} else {
			player = Cell.O;
		}
	}

	public String findBestMove() {
		String bestMove = null;
		int bestVal = 0;
		for (int dim = 0; dim < 4; dim++) {
			for (int row = 0; row < 4; row++) {
				for (int col = 0; col < 4; col++) {
					if (g.getCell(dim, row, col) == Cell.E) {
						Gameboard fake = g;
						fake.setCell(dim, row, col);
						int placeholder = minimax(fake, 4, true);
						if (placeholder > bestVal) {
							bestMove = dim + "." + row + "." + col;
						}
					}
				}
			}
		}
		// if current move is better than bestMove
		// bestMove = current move
		return bestMove;
	}

	public int minimax(Gameboard board, int depth, boolean isMaximizingPlayer) {
		if (board.winner != "N") {
			if (isMaximizingPlayer) {
				return 1;
			} else {
				return -1;
			}
			// return valOfBoard
		}

		if (depth == 4) {
			return 0;
		}
		if (isMaximizingPlayer) {
			int bestVal = Integer.MIN_VALUE;
			// for each move in board :
			for (int dim = 0; dim < 4; dim++) {
				for (int row = 0; row < 4; row++) {
					for (int col = 0; col < 4; col++) {
						if (board.getCell(dim, row, col) == Cell.E) {
							Gameboard fake = board;
							fake.setCell(dim, row, col);
							int value = minimax(fake, depth + 1, false);
							bestVal = max(bestVal, value);
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
						if (board.getCell(dim, row, col) == Cell.E) {
							Gameboard fake = board;
							fake.setCell(dim, row, col);
							int value = minimax(fake, depth + 1, true);
							bestVal = min(bestVal, value);
							return bestVal;
						}
					}
				}
			}
		}
		return 0;
	}

	private boolean findThree() {

		return true;
	}

	public int count2DRow(int dim, int row) {
		int count = 0;
		for (int col = 0; col < 4; col++) {
			if (g.getCell(dim, row, col) == player) {
				count++;
			}
		}
		return count;
	}

	public int count2DCol(int dim, int col) {
		int count = 0;
		for (int row = 0; col < 4; row++) { // for col
			if (g.getCell(dim, row, col) == player) {
				count++;
			}
		}
		return count;
	}

	public int count2DDim(int row, int col) {
		int count = 0;
		for (int dim = 0; dim < 4; dim++) {// for col
			if (g.getCell(dim, row, col) == player) {
				count++;
			}
		}
		return count;
	}

	public int count2DDiagnolA(int dim) {
		int count = 0;
		for (int row = 0; row < 4; row++) { // for dim
			if (g.getCell(dim, row, row) == player) {
				count++;
			}
		}
		return count;
	}

	public int count2DDiagnolB(int dim) {
		int count = 0;
		for (int row = 0; row < 4; row++) { // for dim
			if (g.getCell(dim, row, 3 - row) == player) {
				count++;
			}
		}
		return count;
	}

	public int count3DRowDiagnolA(int row) {
		int count = 0;
		for (int dim = 0; dim < 4; dim++) { // for dim
			if (g.getCell(dim, row, dim) == player) {
				count++;
			}
		}
		return count;
	}

	public int count3DRowDiagnolB(int row) {
		int count = 0;
		for (int dim = 0; dim < 4; dim++) { // for dim
			if (g.getCell(dim, row, 3 - dim) == player) {
				count++;
			}
		}
		return count;
	}

	public int count3DColDiagnolA(int col) {
		int count = 0;
		for (int row = 0; row < 4; row++) { // for dim
			if (g.getCell(row, row, col) == player) {
				count++;
			}
		}
		return count;
	}

	public int count3DColDiagnolB(int col) {
		int count = 0;
		for (int row = 0; row < 4; row++) { // for dim
			if (g.getCell(row, 3 - row, col) == player) {
				count++;
			}
		}
		return count;
	}

	public int count3DDiagnolA() {
		int count = 0;
		for (int row = 0; row < 4; row++) { // for dim
			if (g.getCell(row, row, row) == player) {
				count++;
			}
		}
		return count;
	}

	public int count3DDiagnolB() {
		int count = 0;
		for (int row = 0; row < 4; row++) { // for dim
			if (g.getCell(row, row, 3 - row) == player) {
				count++;
			}
		}
		return count;
	}

	public int count3DDiagnolC() {
		int count = 0;
		for (int row = 0; row < 4; row++) { // for dim
			if (g.getCell(row, 3 - row, 3 - row) == player) {
				count++;
			}
		}
		return count;
	}

	public int count3DDiagnolD() {
		int count = 0;
		for (int row = 0; row < 4; row++) { // for dim
			if (g.getCell(row, 3 - row, row) == player) {
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
