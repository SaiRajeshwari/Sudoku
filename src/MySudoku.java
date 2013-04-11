public class MySudoku{
	
	public void initializeBoard(int[][] board){
        /***Board:
            [0][0][0][2][6][0][7][0][1]
            [6][8][0][0][7][0][0][9][0]
            [1][9][0][0][0][4][5][0][0]
            [8][2][0][1][0][0][0][4][0]
            [0][0][4][6][0][2][9][0][0]
            [0][5][0][0][0][3][0][2][8]
            [0][0][9][3][0][0][0][7][4]
            [0][4][0][0][5][0][0][3][6]
            [7][0][3][0][1][8][0][0][0]
        ***/
		
        board[0][0] = 0;
        board[0][1] = 2;
        board[0][2] = 6;
        board[0][3] = 5;
        board[0][4] = 0;
        board[0][5] = 0;
        board[0][6] = 0;
        board[0][7] = 9;
        board[0][8] = 0;
       
        board[1][0] = 5;
        board[1][1] = 0;
        board[1][2] = 0;
        board[1][3] = 0;
        board[1][4] = 7;
        board[1][5] = 9;
        board[1][6] = 0;
        board[1][7] = 0;
        board[1][8] = 4;
       
        board[2][0] = 3;
        board[2][1] = 0;
        board[2][2] = 0;
        board[2][3] = 0;
        board[2][4] = 1;
        board[2][5] = 0;
        board[2][6] = 0;
        board[2][7] = 0;
        board[2][8] = 0;
       
        board[3][0] = 6;
        board[3][1] = 0;
        board[3][2] = 0;
        board[3][3] = 0;
        board[3][4] = 0;
        board[3][5] = 0;
        board[3][6] = 8;
        board[3][7] = 0;
        board[3][8] = 7;
       
        board[4][0] = 0;
        board[4][1] = 7;
        board[4][2] = 5;
        board[4][3] = 0;
        board[4][4] = 2;
        board[4][5] = 0;
        board[4][6] = 0;
        board[4][7] = 1;
        board[4][8] = 0;
       
        board[5][0] = 0;
        board[5][1] = 1;
        board[5][2] = 0;
        board[5][3] = 0;
        board[5][4] = 0;
        board[5][5] = 0;
        board[5][6] = 4;
        board[5][7] = 0;
        board[5][8] = 0;
       
        board[6][0] = 0;
        board[6][1] = 0;
        board[6][2] = 0;
        board[6][3] = 3;
        board[6][4] = 0;
        board[6][5] = 8;
        board[6][6] = 9;
        board[6][7] = 0;
        board[6][8] = 2;
       
        board[7][0] = 7;
        board[7][1] = 0;
        board[7][2] = 0;
        board[7][3] = 0;
        board[7][4] = 6;
        board[7][5] = 0;
        board[7][6] = 0;
        board[7][7] = 4;
        board[7][8] = 0;
       
        board[8][0] = 0;
        board[8][1] = 3;
        board[8][2] = 0;
        board[8][3] = 2;
        board[8][4] = 0;
        board[8][5] = 0;
        board[8][6] = 1;
        board[8][7] = 0;
        board[8][8] = 0;
    }
	
	public void printBoard(int[][] board){
        System.out.println("Board:");
        
        int k = 0, p = 0;
        for (int i=0;i<9;i++){
            k++;
            System.out.println();
            for (int j=0; j<9; j++){
                p++;
                System.out.print(" " + board[i][j]);
                if(p%3 == 0){
                    System.out.print("|");
                }
            }
            if(k%3 == 0){
                System.out.print("\n---------------------");
            }
            
        }
        System.out.println();
    }
	
	public boolean isValidRowInput(int row, int col, int[][] board)
	{
		for (int i = 0; i < 9; i++){
			if (i == col)
				continue;
			
			if (board[row][i] == board[row][col])
				return false;
		}
		return true;
	}
	
	public boolean isValidColInput(int row, int col, int[][] board)
	{
		for (int i = 0; i < 9; i++)
		{
			if (i == row)
				continue;

			if (board[i][col] == board[row][col])
				return false;
		}
		return true;
	}
	
	public boolean isValidSqrInput(int row, int col, int[][] board)
	{
		int r1, r2, c1, c2;
		if(row < 3){
			r1 = 0;
		}
		else if(row < 6){
			r1 = 3;
		}
		else{
			r1 = 6;
		}
		
		if(col < 3){
			c1 = 0;
		}
		else if(col < 6){
			c1 = 3;
		}
		else{
			c1 = 6;
		}
		
		
		r2 = r1+2;
		c2 = c1+2;
		
		for (int r = r1; r <= r2; r++)
		{
			for (int c = c1; c <= c2; c++)
			{
				if(r == row && c == col)
					continue;
				
				if (board[r][c] == board[row][col])
					return false;
			}
		}
		
		return true;
	}

	
	public boolean solver(int[][] board){
	     
        int flag = 0;
        for (int row = 0; row < 9; row++)
        {
       	 for (int col = 0; col < 9; col++)
       	 {
       		 if (board[row][col] == 0)
       		 {
       			 for (int input = 1; input <= 9; input++)
       			 {
       				 board[row][col] = input;
       				 if (isValidRowInput(row, col, board) && isValidColInput(row, col, board) && isValidSqrInput(row, col, board) && solver(board))
       				 {
       					 return true;
       				 }
       				 else{
           				 board[row][col] = 0;

       				 }
       				 
       			 }
       			 return false;

       		 }
       	 }
        }
        return true;
	}
	
	
	public static void main(String[] args) {
		int[][] board = new int [9][9];
		MySudoku s = new MySudoku();
		 s.initializeBoard(board);
         s.printBoard(board);
         s.solver(board);
         s.printBoard(board);
	}

}