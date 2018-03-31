package com.topcoder.problems.round169;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by sshil on 11/30/2015.
 */
//https://community.topcoder.com/stat?c=round_overview&rd=4650
//https://community.topcoder.com/stat?c=problem_statement&pm=1957&rd=4650
public class GoldMine {
    public int[] getAllocation(String[] mm /*mines*/, int m2 /*miners*/)
    {
        int mmlen=mm.length;
        int c=0, count=0, i=0, j=0, k=0, l=0, x=0, y=0, z=0;
        char ch;
        String s; StringTokenizer st; Iterator it;
        int[] rv = new int[mmlen];

        int[][] pb;
        pb = new int[mmlen][7];

        int[][] v;
        v = new int[mmlen][7];

        for (i = 0; i < mmlen; i++)
        {
            st = new StringTokenizer(mm[i], " ,");
            for (j = 0; j < 7; j++)
                pb[i][j] = Integer.parseInt("1"+st.nextToken())-1000;
        }

        for (i = 0; i < mmlen; i++)
            for (j = 0; j < 7; j++)
                for (k = 0; k < 7; k++)
                {
                    if (j < k) {
                        v[i][j] += z = pb[i][k] * j * 60;
                    }// println ("mine " + i + " workers " + j + " ore " + k + " profit " + z);}
                    else if (j == k) {
                        v[i][j] += z = pb[i][k] * j * 50;
                    }//println ("mine " + i + " workers " + j + " ore " + k + " profit " + z);}
                    else {
                        v[i][j] += z = pb[i][k] * (k * 50 + (k-j) * 20);
                    }//println ("mine " + i + " workers " + j + " ore " + k + " profit " + z);}
                }

         /*for (i = 0; i < mmlen; i++)
         {
           for (j = 0; j < 6; j++)
             print (" " + v[i][j]);
           println ("");
         }*/

        for (i = 0; i < m2; i++)
        {
            int bestIncrease = -9999999;
            int bestMine = 0;

            for (j = 0; j < mmlen; j++)
            {
                if (rv[j] == 6) continue;
                int increase = v[j][rv[j]+1] - v[j][rv[j]];
                if (increase > bestIncrease)
                {
                    bestIncrease = increase;
                    bestMine = j;
                }
            }
            rv[bestMine]++;
        }

        return rv;
    }

    private List<Integer> finalListOfMiners = null;


    private List<Integer> currentListOfMiners = new LinkedList<>();
    private int maxRevenue = Integer.MIN_VALUE;

    public int[] getAllocation1(String[] mines, int miners){
        int mineSize = mines.length;
        int maxMineSize = mines[0].split(",").length;
        findRecursive(mines,
                      maxMineSize,
                      mineSize,
                      miners);
        Integer[] result = finalListOfMiners.toArray(new Integer[0]);
        int[] finalResult = new int[result.length];
        for ( int i = 0 ; i < result.length ; i++){
            finalResult[i] = result[i];
        }
        return  finalResult;
    }

    private void findRecursive(String[] mines,
                               int maxMineSize,
                               int remianingMineSize,
                               int  remainingMiners) {
        if (remianingMineSize == 0){
            // base condition
            calculateAndFindMaxRevenue(mines);
            return;
        }
        if (remianingMineSize == 1) {
            currentListOfMiners.add(remainingMiners);
            findRecursive(mines,
                          maxMineSize,
                          remianingMineSize - 1,
                          0);
            currentListOfMiners.remove(currentListOfMiners.size() - 1);

        } else {
            for (int i = 0; i <= remainingMiners && i < maxMineSize; i++) {
                currentListOfMiners.add(i);
                findRecursive(mines,
                              maxMineSize,
                              remianingMineSize - 1,
                              remainingMiners - i);
                currentListOfMiners.remove(currentListOfMiners.size() - 1);
            }
        }

    }

    private void calculateAndFindMaxRevenue(String[] mines) {

        int index = 0;
        int totalRevenue = 0;
        for (String mine : mines){
            int result = 0;
            String[] probabilities = mine.split(",");
            int minersAllocated = currentListOfMiners.get(index);
            int depositIndex = 0;
            for (String strProbability : probabilities) {
                float probablity = Float.parseFloat(strProbability.trim());
                if (probablity > 0) {
                    int revenueExpected = calculateRevenue(minersAllocated,
                                                           depositIndex);
                    result += ((probablity/100))*revenueExpected;
                }
                depositIndex++;
            }
            totalRevenue += result;
            index++;
        }
        if (totalRevenue > maxRevenue){
            maxRevenue = totalRevenue;
            finalListOfMiners = new LinkedList<>(currentListOfMiners);
        } else if (totalRevenue == maxRevenue){
            checkIfCurrentListIsFinalList();
        }
    }

    private int calculateRevenue(int minersAllocated, int numberOfDeposits) {
        if (minersAllocated < numberOfDeposits) {
            return 60*minersAllocated;
        } else if (minersAllocated == numberOfDeposits){
            return  50*minersAllocated;
        } else {
            return (50*numberOfDeposits)-((minersAllocated-numberOfDeposits)
                *20);
        }
    }

    private void checkIfCurrentListIsFinalList() {
        int index = 0;
        boolean isCurrentEligible = false;
        for ( Integer current : currentListOfMiners){
            if (current > finalListOfMiners.get(index)){
                isCurrentEligible = true;
                break;
            }
            index++;
        }
        if (isCurrentEligible){
            finalListOfMiners = new LinkedList<>(currentListOfMiners);
        }
    }

    public static void main(String[] args){
        String[] mines ={"000, 030, 030, 040, 000, 000, 000",
                         "020, 020, 020, 010, 010, 010, 010"};
        int miners = 4;
        int[] result = new GoldMine().getAllocation(mines, miners);
        for ( int i = 0 ; i < result.length ; i++){
            System.out.println(result[i]);
        }
    }
}
