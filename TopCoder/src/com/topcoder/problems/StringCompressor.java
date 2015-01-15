package com.topcoder.problems;
//http://community.topcoder.com/stat?c=problem_statement&pm=76&rd=3004
public class StringCompressor {

    public String compress(String param0){
        int count = 1;
        char prevChar = param0.charAt(0);
        StringBuffer result = new StringBuffer();
        for(int i = 1 ; i < param0.length(); i++){
            char c = param0.charAt(i);
            if (c == prevChar){
                count++;
            }else{
                if (count > 1){
                    result.append(count);
                    result.append(prevChar);
                    count = 1;
                } else{
                    result.append(prevChar);
                }
            }
            prevChar = c;
        }
        if (count>1){
            result.append(count);
            result.append(prevChar);            
        } else {
            result.append(prevChar);
        }
         return result.toString();
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        //String input= "AAAABBBBCDDDDDD";
        //String input = "SDFJaAAAAaaaass";
        //String input = "AAAAAAAAAAAf";
        //String input = "zxxxxxxxxxxxzz";
        //String input = "abcAbC";
       // String input = "aaAAdaddd";
        //String input = "abcdaacccc";
        String input  = "zxxxxxxxxxxxzz"        ;
        String result = new StringCompressor().compress(input);
        System.out.println(result);

    }

}
