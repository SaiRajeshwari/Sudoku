import java.util.Set;

import org.jgrapht.*;
import org.jgrapht.graph.*;

public class GraphColoring {

	UndirectedGraph<Integer, DefaultEdge> adjGraph = new SimpleGraph<Integer, DefaultEdge>(
			DefaultEdge.class);

	int[][] board = new int[9][9];

	public void initializeBoard() {

		board[0][0] = 0;
		board[0][1] = 0;
		board[0][2] = 0;
		board[0][3] = 2;
		board[0][4] = 6;
		board[0][5] = 0;
		board[0][6] = 7;
		board[0][7] = 0;
		board[0][8] = 1;

		board[1][0] = 6;
		board[1][1] = 8;
		board[1][2] = 0;
		board[1][3] = 0;
		board[1][4] = 7;
		board[1][5] = 0;
		board[1][6] = 0;
		board[1][7] = 9;
		board[1][8] = 0;

		board[2][0] = 1;
		board[2][1] = 9;
		board[2][2] = 0;
		board[2][3] = 0;
		board[2][4] = 0;
		board[2][5] = 4;
		board[2][6] = 5;
		board[2][7] = 0;
		board[2][8] = 0;

		board[3][0] = 8;
		board[3][1] = 2;
		board[3][2] = 0;
		board[3][3] = 1;
		board[3][4] = 0;
		board[3][5] = 0;
		board[3][6] = 0;
		board[3][7] = 4;
		board[3][8] = 0;

		board[4][0] = 0;
		board[4][1] = 0;
		board[4][2] = 4;
		board[4][3] = 6;
		board[4][4] = 0;
		board[4][5] = 2;
		board[4][6] = 9;
		board[4][7] = 0;
		board[4][8] = 0;

		board[5][0] = 0;
		board[5][1] = 5;
		board[5][2] = 0;
		board[5][3] = 0;
		board[5][4] = 0;
		board[5][5] = 3;
		board[5][6] = 0;
		board[5][7] = 2;
		board[5][8] = 8;

		board[6][0] = 0;
		board[6][1] = 0;
		board[6][2] = 9;
		board[6][3] = 3;
		board[6][4] = 0;
		board[6][5] = 0;
		board[6][6] = 0;
		board[6][7] = 7;
		board[6][8] = 4;

		board[7][0] = 0;
		board[7][1] = 4;
		board[7][2] = 0;
		board[7][3] = 0;
		board[7][4] = 5;
		board[7][5] = 0;
		board[7][6] = 0;
		board[7][7] = 3;
		board[7][8] = 6;

		board[8][0] = 7;
		board[8][1] = 0;
		board[8][2] = 3;
		board[8][3] = 0;
		board[8][4] = 1;
		board[8][5] = 8;
		board[8][6] = 0;
		board[8][7] = 0;
		board[8][8] = 0;

		for (int i = 0; i < 81; i++) {
			adjGraph.addVertex(i);
		}

		for (int i = 0; i < 81; i++) {
			int k = (i / 9) * 9;
			for (int j = k; j < k + 9; j++) {
				if (i != j) {
					adjGraph.addEdge(i, j);
				}
			}
			for (int j = i % 9; j < 81; j += 9) {
				if (i != j) {
					adjGraph.addEdge(i, j);
				}
			}

			// for squares
			int startRow = ((i / 27) * 27) + (((i % 9) / 3) * 3);
			for (int j = startRow; j < startRow + 27; j += 9) {
				for (int p = j; p < j + 3; p++) {
					if (i != p) {
						adjGraph.addEdge(i, p);
					}

				}
			}

		}

	}

	public boolean isValidInput(int row, int col, int[][] board) {
		int chkForVertex = row * 9 + col;
		Set<DefaultEdge> st = adjGraph.edgesOf(chkForVertex);
		for (DefaultEdge e : st) {
			if (adjGraph.getEdgeSource(e) == chkForVertex) {
				if (board[(adjGraph.getEdgeTarget(e) / 9)][(adjGraph
						.getEdgeTarget(e)) % 9] == board[row][col]) {
					return false;
				}
			} else {
				if (board[(adjGraph.getEdgeSource(e) / 9)][(adjGraph
						.getEdgeSource(e)) % 9] == board[row][col]) {
					return false;
				}
			}
		}
		return true;
	}

	public boolean solver(int[][] board) {

		for (int row = 0; row < 9; row++) {
			for (int col = 0; col < 9; col++) {

				if (board[row][col] == 0) {
					for (int input = 1; input <= 9; input++) {
						board[row][col] = input;
						if (isValidInput(row, col, board) && solver(board)) {
							return true;
						} else {
							board[row][col] = 0;

						}

					}
					return false;

				}
			}
		}
		return true;
	}

	public void printBoard(int[][] board) {
		System.out.println("Board:");

		int k = 0, p = 0;
		for (int i = 0; i < 9; i++) {
			k++;
			System.out.println();
			for (int j = 0; j < 9; j++) {
				p++;
				System.out.print(board[i][j] == 0 ? "  " : " " + board[i][j]);
				if (p % 3 == 0) {
					System.out.print("|");
				}
			}
			if (k % 3 == 0) {
				System.out.print("\n---------------------");
			}

		}
		System.out.println();
	}

	public static void main(String[] args) {

		GraphColoring s = new GraphColoring();
		s.initializeBoard();
		s.printBoard(s.board);
		s.solver(s.board);
		s.printBoard(s.board);
	}

}
