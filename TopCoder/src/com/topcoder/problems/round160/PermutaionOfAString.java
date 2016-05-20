package com.topcoder.problems.round160;

/**
 * Created by sshil on 5/7/2016.
 */
public class PermutaionOfAString {

    public void permute(String str){
        recursiveUtil("", str);
    }

    private void recursiveUtil(String original, String remaining) {
        if (remaining.length() == 0){
            System.out.println(original);
            return;
        }

        for ( int i = 0 ; i < remaining.length() ; i++){
            String a = original;
            String b = "";
            a = a + remaining.charAt(i);
            if (i > 0){
                b = remaining.substring(0, i);
            }
            if (i < remaining.length()-1){
                b = b+remaining.substring(i+1, remaining.length());
            }
            recursiveUtil(a, b);
        }
    }

    public static void main(String[] args) {
        new PermutaionOfAString().permute("abc");
    }
}
