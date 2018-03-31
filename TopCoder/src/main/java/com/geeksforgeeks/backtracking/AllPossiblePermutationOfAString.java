package com.geeksforgeeks.backtracking;

public class AllPossiblePermutationOfAString {

    public void permute(String str) {
        recursive("", str);
    }

    private void recursive(String current, String left) {
        if (left == null || left.length() == 0){
            System.out.println(current);
            return;
        }

        for (int i = 0 ; i < left.length() ; i++) {
            String s = current;

            s = s+ left.charAt(i);
            String l = "";

            for ( int k = 0 ; k < i ; k++){
                l = l + left.charAt(k);
            }

            for ( int k = i+1 ; k < left.length() ; k++){
                l = l + left.charAt(k);
            }
            recursive(s, l);
        }
    }

    public static void main(String[] args) {
        String str = "suman";
        new AllPossiblePermutationOfAString().permute(str);
    }

}
