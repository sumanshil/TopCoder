package com.geeksforgeeks.dynamicprogramming;

//http://www.geeksforgeeks.org/dice-throw-problem/
public class DiceThrow1 {

    public static void find (int numberOfSides, int numberOfDice, int sum) {
        // sum (6, 3, 8) = sum (6,2,7)+ sum(6,2,6) + ...+ sum(6,2,sum-numberOfSides)
        int rowLength = numberOfDice + 1;
        int colLength = sum + 1;
        int table[][] =new int[rowLength][colLength];
        for (int i = 1 ; i < colLength && i <= numberOfSides ; i++ ){
            table[1][i] = 1;
        }

        for (int currentNumberOfDice = 2 ; currentNumberOfDice < rowLength ; currentNumberOfDice++){

            for ( int currentSum = 1; currentSum < colLength ; currentSum++ ) {
                for (int currentSide = 1; currentSide <= numberOfSides && currentSide < currentSum; currentSide++) {
                    table[currentNumberOfDice][currentSum] += table[currentNumberOfDice-1][currentSum- currentSide];
                }
            }
        }
        System.out.println(table[rowLength-1][colLength-1]);

    }

    public static void main(String[] args) {
        DiceThrow1.find(6, 3, 8);
    }

}
