package com.topcoder.problems.wed091013;
//http://community.topcoder.com/stat?c=problem_statement&pm=88&rd=3008
public class Profit {

    public int maxProfit(int cost, int[] param){
       int result =  maxProfitUtil(cost, param, 0, param.length -1);
       return result;
    }
    private int maxProfitUtil(int cost, int[] param, int index, int n) {
        int max = Integer.MIN_VALUE;
        int[] current = new int[param.length];
        if ((param[0]-cost) < 0){
            current[0] = 0;
            max = 0;
        } else {
            current[0] = param[0]-cost;
            max = (param[0]-cost);
        }
        for(int i = 1 ; i < param.length ; i++){
            if ((param[i]-cost)+current[i-1]> max){
                max = (param[i]-cost)+current[i-1];
                current[i] = (param[i]-cost)+current[i-1];
            } else if ((param[i]-cost)+current[i-1] <0){
                current[i] = 0;    
            } else {
                current[i] = (param[i]-cost)+current[i-1];
            }
        }
        return max;
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
//        int cost = 10;
//        int[] arr = {30,20,10,38};
//        int cost = 30;
//        int[] arr =  {10, 10, 10, 100, 100};
        int cost = 100;
        int[] arr =  {101, 101, 102, 300, 350, 100, 103};

        int result = new Profit().maxProfit(cost, arr);
        System.out.println(result);
    }

}
