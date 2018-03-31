package com.topcoder.problems.round172;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by sshil on 6/25/17.
 */
public class PermutationOfAString {

    public void permute(String str){
        char[] charArr = new char[str.length()];
        boolean[] used = new boolean[26];
        Arrays.fill(charArr,' ');
        Set<String> alreadySeen = new HashSet<>();
        permuteRecursively( charArr, str, used, alreadySeen);

    }

    private void permuteRecursively( char[] charArr, String str, boolean[] used, Set<String> alreadySeen) {
        if (isFilled(charArr)){
            StringBuilder stringBuilder = new StringBuilder();
            for ( int i = 0 ; i < charArr.length ; i++){
                stringBuilder.append(charArr[i]);
            }
            if (alreadySeen.contains(stringBuilder.toString())){
                return;
            }
            System.out.println(stringBuilder.toString());
            alreadySeen.add(stringBuilder.toString());
            return;
        }
        for ( int i = 0 ; i < str.length(); i++){
            int index = str.charAt(i) - 'a';
            if (!used[index]){
                used[index] = true;
                for (int j = 0 ; j < charArr.length ; j++) {
                    if (charArr[j] == ' ') {
                        charArr[j] = str.charAt(i);
                        permuteRecursively( charArr, str, used, alreadySeen);
                        charArr[j] = ' ';
                    }
                }
                used[index] = false;
            }
        }
    }

    private boolean isFilled(char[] charArr) {
        for (int i = 0 ; i < charArr.length ; i++){
            if (charArr[i] == ' '){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        new PermutationOfAString().permute("abc");
    }
}
