package com.topcoder.problems;
//http://community.topcoder.com/stat?c=problem_statement&pm=62&rd=3003

//Examples:
//    (1) decimal="0.2500000000": return "0.25(0)"
//    (2) decimal="0.8333333333": return "0.8(3)"
//    (3) decimal="0.0323232323": return "0.0(32)" (Because the 10th digit is "3" and
//    the repeating part starts with "3")
public class Repeating {
    
    public static String findRepeating(String decimal){
        int length = 12;
        for(int i = 2 ; i < length -1 ; i++){
            int temp = i;
            for(int j = i +1 ; j < length; j++){
                boolean matches = true;
                temp = i;
                for(int k = j ; k < length ; k++){
                    
                    if (decimal.charAt(temp) == decimal.charAt(k)){
                        temp++;
                    } else {
                         matches = false;
                         break;
                    }
                }
                if (matches){
                    return decimal.substring(0, i)+"("+decimal.substring(i,j)+")";
                }
            }
        }
        return decimal;
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
         //String input = "0.2500000000";
         //String input = "0.8333333333";
         //String input = "0.0323232323";
        //String input = "0.0714285714";
        //String input = "0.1176470588";
        String input = "0.1588235290";
         String result = Repeating.findRepeating(input);
         System.out.println(result);

    }

}
