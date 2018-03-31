package com.geeksforgeeks.backtracking;

public class AllPermutationOfAString {

    public void permute(char[] arr, int index){
        if (index == arr.length-1){
            for(int i = 0 ; i < arr.length ; i++){
                System.out.print(arr[i]+" ");
            }
            System.out.println();
        } else {
            for(int i = index ; i < arr.length ; i++){
                char c = arr[i]; arr[i]=arr[index]; arr[index]=c;
                permute(arr, index+1);
                char d = arr[i]; arr[i]=arr[index]; arr[index]=d;
            }
        }
    }
    
    /**
     * @param args
     */
    public static void main(String[] args) {
        char[] c = {'a','b','c'};
        new AllPermutationOfAString().permute(c, 0);
    }

}
