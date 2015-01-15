package com.topcoder.problems;

public class Bases {

    public static void main(String[] args){
//        String num1 = "104.4";
//        String num2 = "613.56";
//        int base = 7;
//        String num1 = "1D.8";
//        String num2 = "02.A";
//        int base = 16;
//        String num1 = "102.4";
//        String num2 = "63.23";
//        int base = 10;
//        String num1 = "1D.EE";
//        String num2 = "D11.A12";
//        int base = 15;

        String num1 = ".4";
        String num2 = ".4";
        int base = 8;

        String result = new Bases().adder(num1, num2, base);
        System.out.println(result);
        //System.out.println((int)'A');
    }
    public String adder(String num1, String num2, int base){
        String result = new String();
        String decimal1 = num1.substring(num1.indexOf('.'), num1.length());
        String decimal2 = num2.substring(num2.indexOf('.'), num2.length());
        if (decimal1.length() != decimal2.length()){
            if (decimal1.length() > decimal2.length()){
                int diff = decimal1.length() - decimal2.length();
                for(int i = 0 ; i < diff ; i++){
                    num2+='0';
                }
            } else if (decimal2.length() > decimal1.length()){
                int diff = decimal2.length() - decimal1.length();
                for(int i = 0 ; i < diff ; i++){
                    num1+='0';
                }
            }
        } 
        
        if (num1.length() != num2.length()){
            if (num1.length() >= num2.length()){
                for(int i = 0 ; i < (num1.length() - num2.length()) ; i++){
                    num2 = '0'+num2;
                }
            } else {
                for(int i = 0 ; i < (num2.length() - num1.length()) ; i++){
                    num1 = '0'+num1;
                }                
            }
        }
        {
            int carry = 0;
            for(int i = num1.length()-1 ; i >=0  ; i--){
                char c1 = num1.charAt(i);
                char c2 = num2.charAt(i);

                if (c1 != '.' && c2 != '.'){
                    int i1 = getInt(c1);
                    int i2 = getInt(c2);
                    
                    int r = 0;
                    if ((i1+i2+carry)>= base){
                        r =  (i1+i2+carry)-base;
                        carry = 1;
                    } else {
                        r = (i1+i2+carry);
                        carry = 0;
                    }
                    result = getChar(r, base)+result;
                } else {
                    result = '.'+result;
                }
            }
            if (carry > 0)
                result = carry + result;
        }
        
        return result;
    }

    private int getInt(char c) {
        if (c >='0' && c <='9')
            return c-'0';
        else if (c >='A' && c <='F')
            return (c-'A')+10;
        return 0;
    }
    
    private char getChar(int i, int base){
        if (base <= 10){
            return (char)(i+48);
        } else {
            if (i < 10){
                return (char)(i+48);
            } else {
                return (char)((i-10)+65);
            }
        }
    }
}
