package com.topcoder.problems.wed091013;
//http://community.topcoder.com/stat?c=problem_statement&pm=84&rd=3006
public class Dice {

    
    public int[] getScores(int iNumberOfPlayers, int[] dieRolls){
        int[] result = new int[iNumberOfPlayers];
        int prevDiceNumber = 0;
        int repetitionCount = 0;
        int value = 0;
        int currentPlayerIndex = 0;
        for(int i = 0 ; i< dieRolls.length ; i++){
            int dieNumber = dieRolls[i];
            if (prevDiceNumber != 0){
                if (prevDiceNumber == dieNumber){
                    repetitionCount++;
                    if (repetitionCount== 2){
                        value = getNumber(dieNumber)+dieNumber;  
                    } else if (repetitionCount > 2){
                        value = (repetitionCount-1)*dieNumber;
                    }
                } else {
                    value = getNumber(dieNumber);
                    repetitionCount = 1;
                }
            } else {
                value = getNumber(dieNumber);
                repetitionCount = 1;
            }
            result[currentPlayerIndex] += value;
            currentPlayerIndex = getNextPlayer(dieNumber, currentPlayerIndex, iNumberOfPlayers, prevDiceNumber);
            prevDiceNumber = dieNumber;
        }
        return result;
    }
    
    private int getNextPlayer(int dieNumber, int currentPlayerIndex, int iNumberOfPlayers, int prevDiceNumber) {
        int retVal = 0;
        if (prevDiceNumber == dieNumber){
            return currentPlayerIndex;
        } else if (dieNumber%2 != 0){
            retVal = currentPlayerIndex+1;
            if (retVal ==iNumberOfPlayers){
                retVal = 0;
            }
        } else if (dieNumber%2 == 0){
            retVal = currentPlayerIndex-1;
            if (retVal <0){
                retVal = iNumberOfPlayers-1;
            }
        }
        return retVal;
    }

    private int getNumber(int dieNumber) {
        int retVal = 0;
        switch (dieNumber) {
        case 1:
            retVal = 1;
            break;
        case 2:
            retVal = 1;
            break;
        case 3:
            retVal = 2;
            break;
        case 4:
            retVal = 2;
            break;
        case 5:
            retVal = 3;
            break;
        case 6:
            retVal = 3;
            break;            
        default:
            break;
        }
        return retVal;
    }

    public static void main(String[] args){
        int numberOfPlayers = 3;
        int[] dieRolls = {1, 2, 3, 4, 5, 6, 1, 2, 3, 4};
        int[] result = new Dice().getScores(numberOfPlayers, dieRolls);
        for(int i = 0 ; i< result.length ; i++){
            System.out.println(result[i]);
        }
    }
}
