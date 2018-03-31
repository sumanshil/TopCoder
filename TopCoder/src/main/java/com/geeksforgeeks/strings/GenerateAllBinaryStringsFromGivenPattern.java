package com.geeksforgeeks.strings;

/**
 * Created by sshil on 6/16/2016.
 */
//http://www.geeksforgeeks.org/generate-all-binary-strings-from-given-pattern/
public class GenerateAllBinaryStringsFromGivenPattern {

    public void generate(String binaryString){
        recursive("", binaryString);
    }

    private void recursive(String currentString, String remaining) {
        if (remaining == null || remaining.length() == 0){
            System.out.println(currentString);
            return;
        }

        String a = currentString;
        String b = "";
        char c = remaining.charAt(0);
        if (remaining.length() > 1){
            b = remaining.substring(1);
        }

        if (c == '?'){
            recursive(a+"0", b);
            recursive(a+"1", b);
        } else {
            recursive(a+c, b);
        }
    }


    public static void main(String[] args) {
        new GenerateAllBinaryStringsFromGivenPattern().generate("1??0?101");
    }
}
