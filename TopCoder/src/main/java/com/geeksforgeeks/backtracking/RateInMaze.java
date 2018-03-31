package com.geeksforgeeks.backtracking;

public class RateInMaze {

    int[][] maze = {
        {1, 0, 0, 0},
        {1, 1, 0, 1},
        {0, 1, 0, 0},
        {1, 1, 1, 1}
    };
    
    int[][] solution ={
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0}
        }; 
    
    public void solution(){
        if (!solutionUtil(0,0)){
            System.out.println("Solution does not exist");
        } else {
            for(int i =0 ; i < solution[0].length ; i++){
                for(int j = 0 ; j < solution.length ; j++){
                    System.out.print(solution[i][j]+" ");
                }
                System.out.println();
            }
        }
    }
    
    
    private boolean solutionUtil(int mazex, int mazey) {
        if (mazex == 3 && mazey==3){
            return true;
        } else {
            solution[mazex][mazey] = 1;
            // try forward
            if(validPosition(mazex, mazey+1)){
                if (solutionUtil(mazex, mazey+1)){
                    return true;
                }
            } else if (validPosition(mazex+1, mazey)){
                if (solutionUtil(mazex+1, mazey)){
                    return true;
                }                
            }
            solution[mazex][mazey] = 0;
        }
        return false;
    }


    private boolean validPosition(int mazex, int mazey) {
        return (mazex>=0&& mazey>=0)&&(mazex<=3 && mazey<=3)&&(maze[mazex][mazey]==1);
    }


    /**
     * @param args
     */
    public static void main(String[] args) {
        new RateInMaze().solution();

    }

}
