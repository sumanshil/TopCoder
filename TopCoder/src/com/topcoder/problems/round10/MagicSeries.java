package com.topcoder.problems.round10;
//http://community.topcoder.com/stat?c=problem_statement&pm=94&rd=3010
public class MagicSeries {

    public int lookup(int k){
        int count = 0;
        int result = 0;
        for(int i = 0 ;; i ++){
            if (i%2 == 0 || i%3 == 0 || i%5==0){
                count++;
            }
            if (count == k){
                result = i;
                break;
            }
        }
        return result;
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new MagicSeries().lookup(20));
    }

}
