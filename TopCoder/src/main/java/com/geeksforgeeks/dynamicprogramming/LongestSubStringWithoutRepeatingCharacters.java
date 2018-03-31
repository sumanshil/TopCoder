package com.geeksforgeeks.dynamicprogramming;

public class LongestSubStringWithoutRepeatingCharacters {

    public int count(String str){
        boolean[] arr = new boolean[256];
        int maxLength = Integer.MIN_VALUE;
        int start=-1;
        int end=-1;
        for(int i = 0 ; i < str.length() ; i++){
            int j = i;
            int count = 0;
            while(j< str.length()){
                char c= str.charAt(j);
                if (arr[c] == false){
                    count++;
                    arr[c] = true;
                } else {
                    if (count>= maxLength){
                        maxLength = count;
                        start = i;
                        end = j;
                    }
                    break;
                }
                j++;
            }
            for(int k = 0 ; k < arr.length ; k++){
                arr[k] = false;
            }
        }
        System.out.println(str.substring(start, end));
        return maxLength;
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
       // String str ="ABDEFGABEF";
        String str = "GEEKSFORGEEKS";
        int result = new LongestSubStringWithoutRepeatingCharacters().count(str);
        System.out.println(result);

    }

}
