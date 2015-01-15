package com.topcoder.problems.round15;
//http://community.topcoder.com/stat?c=problem_statement&pm=109&rd=3014
public class MagicNumber {
    public int getMagicNumber(String strName){
        String array = strName.toLowerCase();
        int sum = 0;
        for(int i = 0 ; i < array.length() ; i++){
            char c= array.charAt(i);
            if (c!= ' ')
                sum+=((c-'a')+1);
        }
        int result;
        while(true){
            result = getSum(sum);
            if (result < 10){
                break;
            }else {
                sum = result;
            }
        }
        return result;
    }
    private int getSum(int sum) {
        if (sum==0){
            return 0;
        }
        int remainder = sum % 10;
        int number = sum / 10;
        
        int r = getSum(number);
        return r+remainder;
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        String strName = "aaaaaaaaaa";
        int result = new MagicNumber().getMagicNumber(strName);
        System.out.println(result);
    }

}
