package com.topcoder.problems;
//http://community.topcoder.com/stat?c=problem_statement&pm=61&rd=3003
public class ModEncoder {

    public static String encode(String input){
        StringBuffer retVal = new StringBuffer();
        int length = input.length();
        int requiredSpaces = (5 - (length % 5));
        for(int i = 0 ; i < requiredSpaces ; i++){
            input+=(" ");
        }
        StringBuffer sb = new StringBuffer();
        for(int i = 0 ; i < input.length(); i++){
            if (' '  == input.charAt(i)){
                sb.append('~');
            } else {
               sb.append(input.charAt(i));
            }
        }
        
        for(int i = 0 ; i < 5 ; i++){
            int index = i;
            while(index < sb.length()){
                retVal.append(sb.charAt(index));
                index +=5;
            }
        }
        return retVal.toString()+'$';
        
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        //String message = "THIS CODED MESSAGE";
        //String message = "Let us try a extraordinarily long message0123456789";
        String message = "HoW1 aRe You";
        String result = ModEncoder.encode(message);
        System.out.println(result);
    }

}
