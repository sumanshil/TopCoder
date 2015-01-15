package com.topcoder.problems.round156;
//http://community.topcoder.com/stat?c=problem_statement&pm=1778&rd=4585
public class BombSweeper
{
    int rowLength = 0;
    int colLength = 0;
    private int[] x = {0, 0, -1, -1, -1, 1,  1, 1};
    private int[] y = {1,-1, -1,  0,  1, 1, -1, 0};
    private int[][] matrix = null;
    public double winPercentage(String[] board)
    {
        int[][] matrix = new int[board.length][board[0].length()];
        rowLength = board.length;
        colLength = board[0].length();
        int index = 0;
        for(String b : board)
        {
            for(int i = 0 ; i < b.length() ; i++)
            {
                if (b.charAt(i) == '.')
                {
                    matrix[index][i] = 0;
                }
                else
                {
                    matrix[index][i] = 1;
                }
            }
            index++;
        }
        this.matrix = matrix;
        double wins = 0;
        double loss = 0;
        
        for(int i = 0 ; i < rowLength ; i++)
        {
            for(int j = 0; j < colLength ; j++)
            {
                if (matrix[i][j] == 0
                    && isWin(i, j))
                {
                    wins++;
                }
                else if (matrix[i][j] == 1)
                {
                    loss++;
                }
                
            }
        }
        
        return (wins / (wins+loss))*100.0;
    }
    
    
    private boolean isWin(int row, int col)
    {
        for(int i = 0; i < 8 ; i++)
        {
            if (!isSafe(row+x[i], col +y[i]))
            {
                return false;
            }
        }
        return true;
    }
    
    
    private boolean isSafe(int r, int c)
    {
        return r < 0 || r >= rowLength || c <0 || c>= colLength || matrix[r][c] == 0;
    }
    public static void main(String[] args)
    {
        String[] board = {"B.......B..B...B.B.....B.B.B...B.......BB.", "BB...........BBBB........B...BBBBB........", "..B.B..B..B.B.B...............B.BB.B.B.B.B", ".......B..B..B......B....B....B...B..B...B", "BBBBB..B..BB..B...B..B.B......B......BB.B.", "B......B.BB..B....B..B......B.........B..B", "......B...B........BB...B.BB.......BB..BBB", "..B..B..BB.BB..B.....B..............B..BB.", ".B...B..B...BB.....B.....BB.........B.B...", "...B......B..B.B......BBB...B.B.....B.B.B.", "...B..........BB..B.B..........B.....B.BB.", "BB.B..B..B..B...B...B....B...BBB.....BB..B", "....B.....B...BBB..B......BBBB.......B....", "....B..B.B......B..B..BBBB...B....BB....B.", ".BB..BBBB.B...B....B....B.B..B.B...BB..B..", ".B........B.B..B.....BBB.B.BB.BBB...B.....", "....BB.......BB.....B.........B....B.BB...", "....BB......B....BB.........B.B....B.B.B..", "BB.BBBBBBBB........B......B..B..BB........", "B......BBB.BBB......B..B......B...........", "...B......BB.B..B..BBBB..B..B....BB....B..", "B....BBB.....B...B........BBB..BB.B...BB..", "....B...B..B.B....B...BB.B..BB..BB...B...B", "..B....B..............B.............B.....", "BBBB...B...........B...BB..B..........B.B.", ".........B.B.B.B.B..........B...B....B.BB.", ".BB..B.....B......B....BB....B.BB...B.B..B", "B...B..B.BB..B...B.B....B..BB..BB.BBB.B.B.", ".....BBBB.......B.......B..B......BB......", "B........B...B.B..BB...B...B..B.B......B..", ".....BBB....B..B.B..B..B....BB.B..B...BB..", "...BBBBB...B.B..B...BB..B......BBBB..B.B..", "...B...B.....BBBB.......B.B...B...BBB.BBB.", "....BB..B.B.B.B..BB......BB..B..........B.", ".B..B......B...B.BB..BB..B.BB.........BBB.", "........B..B...B...BBB...B.......B..B..B..", "..BB.B......B...BB...B..BB......B.B..B.B.B"};
        
        double result = new BombSweeper().winPercentage(board);
        System.out.println(result);
    }

}
