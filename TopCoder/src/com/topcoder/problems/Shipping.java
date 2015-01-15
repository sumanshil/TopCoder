package com.topcoder.problems;

import java.util.ArrayList;
import java.util.List;

//http://community.topcoder.com/stat?c=problem_statement&pm=78&rd=3004
public class Shipping {

    private int[][] adjacency;
    private int[][] lengthMatrix;
    private int[][] costMatrix;
    private List<Integer> list = new ArrayList<Integer>();
    private int maxValue = Integer.MIN_VALUE;
    private boolean[] visited ;
    private int shipFrom;
    private int shipTo;
    private int weight;
    private int numOfWareHouses;
    public int mostExpensive(int numWarehouses, String[] legs, int shipFrom, int shipTo, int weight){
        int[][] adjacency = new int[numWarehouses][numWarehouses];
        int[][] lengthMatrix = new int[numWarehouses][numWarehouses];
        int[][] costMatrix = new int[numWarehouses][numWarehouses];
        visited = new boolean[numWarehouses];
        this.numOfWareHouses = numWarehouses;
        for(String leg : legs){
            String[] strs  = leg.split(" ");
            int x = Integer.parseInt(strs[0]);
            int y = Integer.parseInt(strs[1]);
            int length = Integer.parseInt(strs[2]);
            int cost = Integer.parseInt(strs[3]);
            
            adjacency[x][y] = 1;
            adjacency[y][x] = 1;
            
            lengthMatrix[x][y] = length;
            lengthMatrix[y][x] = length;
            
            costMatrix[x][y] = cost;
            costMatrix[y][x] = cost;
        }
    
        this.adjacency = adjacency;
        this.lengthMatrix = lengthMatrix;
        this.costMatrix = costMatrix;
        this.shipFrom = shipFrom;
        this.shipTo = shipTo;
        this.weight = weight;
        //mostExpensiveUtil(shipFrom);
        mostExpensiveUtil1(shipFrom, 0, shipTo);                
        return this.maxValue*weight;
    }
    
    private void mostExpensiveUtil1(int shipFrom, int d, int shipTo){
        if (visited[shipFrom])
            return;
        if (shipFrom == shipTo){
            if (d > maxValue){
                maxValue = d;
            }
            return ;
        }
        visited[shipFrom] = true;
        for(int i = 0 ; i < numOfWareHouses ; i++){
            if (adjacency[shipFrom][i] == 1){
                mostExpensiveUtil1(i, d+(lengthMatrix[shipFrom][i]*costMatrix[shipFrom][i]), shipTo);
            }
        }
        visited[shipFrom]=false;
    }
    
    private void mostExpensiveUtil(int shipFrom) {
        if (shipFrom == this.shipTo){
            int totalCostSoFar = getTotalCostSoFar();
            if (totalCostSoFar > maxValue){
                maxValue = totalCostSoFar;
            }
            return;
        }
        if (!visited[shipFrom]){            
            visited[shipFrom] = true;
            List<Integer> list = getAdjacents(shipFrom); 
            for(int i : list){
                if (!visited[i]){
                    System.out.println("Considering "+shipFrom+" "+i);                    
                    addCost(shipFrom, i);
                    mostExpensiveUtil(i);
                    this.list.remove(this.list.size()-1);
                }
            }
            visited[shipFrom] = false;
        }
    }
    private int getTotalCostSoFar() {
        int retVal = 0;
        for(Integer i : list){
            retVal += i;
        }
        return retVal;
    }

    private List<Integer> getAdjacents(int shipFrom) {
        List<Integer> retVal = new ArrayList<Integer>();
        
        int[] arr = adjacency[shipFrom];
        for(int i = 0 ; i < arr.length ; i++){
            if (arr[i] != 0)
                retVal.add(i);
        }
        return retVal;
    }

    private void addCost(int shipFrom, int shipTo) {
        int cost = costMatrix[shipFrom][shipTo];
        int length = lengthMatrix[shipFrom][shipTo];
        int totalCost = weight*cost*length;
        list.add(totalCost);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        int numberOfWarehouses = 5;
        String[] legs =  {"0 1 2 3", "1 2 4 6", "0 4 10 12", "4 2 3 3", "2 3 8 12", "3 4 4 5", "0 3 1 1"};
        int shipFrom=0;
        int shipTo=3;
        int weight=10;
        int result = new Shipping().mostExpensive(numberOfWarehouses, legs, shipFrom, shipTo, weight);
        System.out.println("Max value "+ result);
    }

}
