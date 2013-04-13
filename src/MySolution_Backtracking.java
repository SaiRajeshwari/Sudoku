import java.lang.reflect.Array;
import java.util.ArrayList;

public class MySolution_Backtracking {

	ArrayList[][] workBoard = new ArrayList[9][9];
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

	}

	public void printBoard() {
		System.out.println("\nBoard : ");
		int p = 0, q = 0;
		for (int i = 0; i < board.length; i++) {
			System.out.println();
			for (int j = 0; j < board[0].length; j++) {
				System.out.print(" " + board[i][j]);
				q++;
				if (q % 3 == 0) {
					System.out.print(" |");
				}
			}
			p++;
			if (p % 3 == 0) {
				System.out.print("\n--------------------------");
			}
		}
	}

	public void initializeWorkBoard() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {

				ArrayList<Integer> cell = new ArrayList<Integer>();
				for (int k = 1; k <= 9; k++) {
					cell.add(k);
				}
				if (board[i][j] != 0) {
					cell.clear();
					cell.add(board[i][j]);
				}

				workBoard[i][j] = cell;
			}
		}
	}

	public void printWorkBoard() {
		System.out.println("\nWork Board :");
		int p = 0, q = 0;
		for (int i = 0; i < 9; i++) {
			System.out.println();
			for (int j = 0; j < 9; j++) {
				System.out.print(" " + workBoard[i][j]);
				q++;
				if (q % 3 == 0) {
					System.out.print(" |");
				}
			}
			p++;
			if (p % 3 == 0) {
				System.out
						.print("\n--------------------------------------------");
			}
		}
	}

	public void removeByColumn() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {

				ArrayList currCell = workBoard[i][j];
				for (int k = 0; k < 9; k++) {
					if (currCell.contains(board[k][j]) && i != k) {
						currCell.remove(new Integer(board[k][j]));
					}

					if (workBoard[k][j].size() == 1
							&& currCell.contains(workBoard[k][j].get(0))
							&& i != k) {
						currCell.remove(workBoard[k][j].get(0));
					}

				}

			}

		}

	}

	public void removeByRow() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {

				ArrayList currCell = workBoard[i][j];
				for (int k = 0; k < 9; k++) {
					if (currCell.contains(board[i][k]) && j != k) {
						currCell.remove(new Integer(board[i][k]));
					}

					if (workBoard[i][k].size() == 1
							&& currCell.contains(workBoard[i][k].get(0))
							&& j != k) {
						currCell.remove(workBoard[i][k].get(0));
					}
				}
			}
		}
	}


	
	public void removeBySquare() {
		ArrayList<Integer> numsInSquare;
		for (int i = 0; i < 9; i += 3) {
			for (int j = 0; j < 9; j += 3) {
				
				numsInSquare = new ArrayList<Integer>();
				
				for (int k = 0 + i; k < 3 + i; k++) {
					for (int l = 0 + j; l < 3 + j; l++) {
						numsInSquare.add(new Integer(board[k][l]));
						if (workBoard[k][l].size() == 1) {
							numsInSquare.add((Integer) workBoard[k][l].get(0));
						}
					}
				}
				for (int k = 0 + i; k < 3 + i; k++) {
					for (int l = 0 + j; l < 3 + j; l++) {
						
						ArrayList<Integer> currCell = workBoard[k][l];
						for (int p = 0; p < numsInSquare.size(); p++) {
							if (currCell.contains(numsInSquare.get(p))
									&& currCell.size() > 1) {
								currCell.remove(new Integer(numsInSquare.get(p)));
							}
						}
					}
				}

			}
		}
		
	}

	
	
	public static void main(String[] args) {
		MySolution_Backtracking s = new MySolution_Backtracking();

		s.initializeBoard();
		s.printBoard();
		s.initializeWorkBoard();

		while (true) {
			int flag = 0;
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					if (s.workBoard[i][j].size() != 1) {
						flag = 1;
					}
				}
			}

			if (flag == 0) {
				break;
			}

			s.removeByRow();
			s.removeByColumn();
			s.removeBySquare();
			System.out.println("4");


		}
		
		s.printWorkBoard();

	}

}
