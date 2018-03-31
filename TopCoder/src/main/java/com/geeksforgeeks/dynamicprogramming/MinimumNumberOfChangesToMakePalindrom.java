package com.geeksforgeeks.dynamicprogramming;

/**
 * Created by sshil on 8/10/2016.
 */
public class MinimumNumberOfChangesToMakePalindrom {

    public void find(String str, int max){
        int result = recursive(str, 0, str.length()-1, max);
        System.out.println(result <= max);
    }

    private int recursive(String str, int start, int end, int max) {
        if (start > end ){
            return 0;
        }
        // base case 1
        if (start+1 == end){
            if (str.charAt(start) == str.charAt(end)){
                // they are same. lets check if we have chance to change both the values. In that case we will make
                // both of them as 9
                if (max >= 2){
                    return 2;
                } else {
                    // no need to change
                    return 0;
                }
            } else {
                // they are not same. If we have only one chance. we will replace the smaller one with the greater value
                if (max == 1) {
                    return 1;
                } else {
                    // we have to change both to make the string palindrom
                    return 2;
                }
            }
        }

        if (start == end){
            if (max >= 1){
                //we can make it 9;
                return 1;
            } else {
                return 0;
            }
        }

        char c1 = str.charAt(start);
        char c2 = str.charAt(end);
        int resultForRest;
        if (c1 == c2){
            resultForRest = recursive(str, start+1, end-1, max);
            if (max - resultForRest >= 2){
                resultForRest += 2;
            }
        } else {
            // they are not same. we have to change atleast one.
            resultForRest = recursive(str, start+1, end-1, max-1);
            if (resultForRest - max >= 2){
                // we have room for changing both. make both as 9
                resultForRest +=2;
            } else {
                resultForRest = resultForRest+1;
            }
        }
        return resultForRest;
    }

    public static void main(String[] args) {
        String str = "129";
        int maxChange = 1;
        new MinimumNumberOfChangesToMakePalindrom().find(str, maxChange);

        str = "1292";
        maxChange = 1;
        new MinimumNumberOfChangesToMakePalindrom().find(str, maxChange);

        str = "01292";
        maxChange = 2;
        new MinimumNumberOfChangesToMakePalindrom().find(str, maxChange);

        str = "01292";
        maxChange = 1;
        new MinimumNumberOfChangesToMakePalindrom().find(str, maxChange);

        str = "000129";
        maxChange = 3;
        new MinimumNumberOfChangesToMakePalindrom().find(str, maxChange);

        str = "000129";
        maxChange = 2;
        new MinimumNumberOfChangesToMakePalindrom().find(str, maxChange);

        str = "000129";
        maxChange = 4;
        new MinimumNumberOfChangesToMakePalindrom().find(str, maxChange);

    }
}
