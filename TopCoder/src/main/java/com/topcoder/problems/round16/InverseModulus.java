package com.topcoder.problems.round16;
//http://community.topcoder.com/stat?c=problem_statement&pm=112&rd=3015
public class InverseModulus {

    public int getMod(String[] al){
        int[] x = new int[al.length];
        int[] y = new int[al.length];
        
        for(int i = 0 ; i < al.length; i++){
            String str = al[i];
            String[] strArr = str.split(",");
            x[i] = Integer.parseInt(strArr[0]);
            y[i] = Integer.parseInt(strArr[1]);  
        }
        int max = Integer.MAX_VALUE;
        int n = al.length;
        int result = -1;
        for(int i = 0 ; i < max ; i++){
            boolean ok = false;
            for(int j = 0 ; j < n ; j++){
                if (i % x[j] == y[j]){
                    ok = true;
                } else {
                    ok = false;
                    break;
                }
            }
            
            if (ok){
                result =i ;
                break;
            }
        }
        
        return result;
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        int result = new InverseModulus().getMod(new String[]   {"19,18", "18,17", "17,16", "13,12"});
        System.out.println(result);
    }

}
