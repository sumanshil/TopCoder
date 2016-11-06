package com.topcoder.problems.round171;

import java.util.Arrays;

/**
 * Created by SumanChandra on 11/6/2016.
 */
//https://community.topcoder.com/stat?c=problem_statement&pm=1944&rd=4660
public class RPG {
    static class Dice {
        private int numberOfTimes;
        private int diceSize;
        public Dice(int numberOfTimes, int diceSize){
            this.numberOfTimes = numberOfTimes;
            this.diceSize = diceSize;
        }

        public int getNumberOfTimes() {
            return numberOfTimes;
        }

        public void setNumberOfTimes(int numberOfTimes) {
            this.numberOfTimes = numberOfTimes;
        }

        public int getDiceSize() {
            return diceSize;
        }

        public void setDiceSize(int diceSize) {
            this.diceSize = diceSize;
        }
    }

    public static Dice createDice(String str){
        String[] arr = str.split("d");
        return new Dice(Integer.parseInt(arr[0]), Integer.parseInt(arr[1]));
    }

    int[] dieRolls(String[] dice){
        int min1 = Arrays.stream(dice).map(RPG::createDice).mapToInt(Dice::getNumberOfTimes).sum();
        int min2 = Arrays.stream(dice)
                .map(RPG::createDice)
                .mapToInt(Dice::getNumberOfTimes)
                .reduce(0, (a,b) -> a+b);
        int max1 = Arrays.stream(dice)
                .map(RPG::createDice)
                .mapToInt(d -> d.numberOfTimes*d.diceSize)
                .reduce(0, (a,b)-> a+b);
        int avg = (min1+max1)/2;
        return new int[]{min1, max1, avg};
    }

    public static void main(String[] args) {
        String[] str = {"1d2","2d2","4d2","6d2","8d2"};
        int[] result = new RPG().dieRolls(str);
        Arrays.stream(result).forEach(System.out::println);
    }
}
