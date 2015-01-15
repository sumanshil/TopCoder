package com.topcoder.problems.wed091013;

import java.util.ArrayList;
import java.util.List;

public class AllPermutationOfString {

    
    public List<String> getAllPermutation(String str){        
        List<String> permutations = new ArrayList<String>();
        permutationUtil("", str, permutations);
        return permutations;
    }
    private void permutationUtil(String string, String left,
            List<String> permutations) {
        if (left.length() == 0 || left == null){
            permutations.add(string);
            return;
        }
        
        for(int i = 0 ; i < left.length() ; i++){
            String n2 = string +left.charAt(i);
            String lefts="";
            int n = left.length();
            for(int j =0 ; j < i ; j++){
                lefts= lefts + left.charAt(j);
            }
            
            for(int j = i+1 ; j < n; j++){
                lefts = lefts + left.charAt(j);
            }
            permutationUtil(n2, lefts, permutations);
        }        
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        String s= "suman";
        List<String> permutations = new AllPermutationOfString().getAllPermutation(s);
        for(String str : permutations){
            System.out.println(str);
        }
    }

}
