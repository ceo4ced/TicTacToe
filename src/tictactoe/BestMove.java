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

	public BestMove() {
		g = new Gameboard();
		table = new ProbabilityTable();
	}

	public String findBestMove(Gameboard board) {
		String bestMove = null;
		int bestVal = 0;
		for (int dim = 0; dim < 4; dim++) {
			for (int row = 0; row < 4; row++) {
				for (int col = 0; col < 4; col++) {
					if (board.getCell(dim, row, col) == Cell.E) {
						Gameboard fake = board;
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
		//   bestMove = current move
		return bestMove;
	}

	public int minimax(Gameboard board, int depth, boolean isMaximizingPlayer) {
		if (board.winner != "N") {
			if (isMaximizingPlayer) {
				return 1;
			} else {
				return -1;
			}
			//return valOfBoard 
		}
		
		if (depth == 4) {
			return 0;
		}
		if (isMaximizingPlayer) {
			int bestVal = Integer.MIN_VALUE; 
			//for each move in board :
			for (int dim = 0; dim < 4; dim++) {
				for (int row = 0; row < 4; row++) {
					for (int col = 0; col < 4; col++) {
						if (board.getCell(dim, row, col) == Cell.E) {
							Gameboard fake = board;
							fake.setCell(dim, row, col);
							int value = minimax(fake, depth+1, false);
							bestVal = max(bestVal, value); 
							return bestVal;
						}
					}
				}
			}

		} else {
			int bestVal = Integer.MAX_VALUE;
			//for each move in board :
			for (int dim = 0; dim < 4; dim++) {
				for (int row = 0; row < 4; row++) {
					for (int col = 0; col < 4; col++) {
						if (board.getCell(dim, row, col) == Cell.E) {
							Gameboard fake = board;
							fake.setCell(dim, row, col);
							int value = minimax(fake, depth+1, true);
							bestVal = min(bestVal, value); 
							return bestVal;
						}
					}
				}
			}
		}
		return 0;
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
