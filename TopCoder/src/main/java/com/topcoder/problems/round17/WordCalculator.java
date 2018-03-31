package com.topcoder.problems.round17;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

//http://community.topcoder.com/stat?c=problem_statement&pm=123&rd=3017
public class WordCalculator {

    public String evaluate(String str){
        String[] strArr = str.split(" ");
        int i=0;
        int j=0;
        String operator= null;
        for(int k = 0 ; k < strArr.length ; k++){
            if (isOperator(strArr[k])){
                operator = strArr[k];
                i = k-1;
                j = k+1;
            }
        }
        int number1 = convertStringToNumber(strArr,0,i,0);
        int number2 = convertStringToNumber(strArr,j,strArr.length-1,j);
        char c = getOperator(operator);
        int r = applyOperator((number1),(number2), c);
        System.out.println(r);
        
        String result = convertNumberToString(r);
        return result;
    }
    
    private String convertNumberToString(int number) {
        int index = 10;
        int remainder = 0;
        int remainderNumber = 0;
        StringBuffer sb = new StringBuffer();
        boolean isNegative = false;
        if(number < 0 ){
            number = Math.abs(number);
            isNegative = true;
        }else if (number == 0){
            sb.insert(0, "ZERO");
        }
        while(number > 0){
            remainder = number % 10;
            number = number/10;
            sb = convertNumber(remainder, index, remainderNumber,sb);            
            remainderNumber+=remainder*(index/10);            
            index *=10;
        }
        if (isNegative){
            sb.insert(0, "NEGATIVE ");
        }
        return sb.toString();
    }

    private StringBuffer convertNumber(int digit1, int index, int digit2, StringBuffer sb){
        switch(digit1){
            case 1 :
                switch(index){
                    case 10 :
                        sb.append("ONE");
                    break;
                    case 100 :
                        switch(digit2){
                            case 0:
                                sb = new StringBuffer();
                                sb.append("TEN");
                            break;
                            case 1: 
                                sb = new StringBuffer();
                                sb.append("ELEVEN");
                            break;
                            case 2:
                                sb = new StringBuffer();
                                sb.append("TWELVE");
                            break;
                            case 3 :
                                sb = new StringBuffer();
                                sb.append("THIRTEEN");
                            break;
                            case 4 :
                                sb = new StringBuffer();
                                sb.append("FOURTEEN");
                            break;
                            case 5 :
                                sb = new StringBuffer();
                                sb.append("FIFTEEN");
                            break;
                            case 6 :
                                sb = new StringBuffer();
                                sb.append("SIXTEEN");
                            break;
                            case 7 :
                                sb = new StringBuffer();
                                sb.append("SEVENTEEN");
                            break;
                            case 8 :
                                sb = new StringBuffer();
                                sb.append("EIGHTTEEN");
                            break;
                            case 9 :
                                sb = new StringBuffer();
                                sb.append("NINETEEN");
                            break;                                
                        }
                    break;// case 100 
                    case 1000:
                            if (digit2 > 0)
                                sb.insert(0, "ONE HUNDRED AND ");
                                
                            else 
                                sb.insert(0, "ONE HUNDRED");
                    break;// case 1000
                    
                    case 10000:// case 10000
                            sb.insert(0, "ONE THOUSAND ");
                   break;
                        
                }
                break; // case 1
            case 2 :
                switch (index) {
                case 10:
                    sb.insert(0,"TWO");
                    break;
                case 100:
                    if (digit2 > 0)
                        sb.insert(0,"TWENTY-");
                    else 
                        sb.insert(0,"TWENTY");
                    break;
                case 1000:
                        if (digit2 > 0)
                            sb.insert(0,"TWO HUNDRED AND ");
                        else 
                            sb.insert(0,"TWO HUNDRED");
                    break;
                case 10000:
                    sb.insert(0,"TWO THOUSAND ");
                break;
                    
                default:
                    break;
                } 
                break;// case 2 
            case 0 :                
                break;// case 0
            case 3 :
                switch (index) {
                    case 10:
                        sb.insert(0,"THREE");
                        break;
                    case 100:
                        if (digit2 > 0)
                            sb.insert(0,"THIRTY-");
                        else 
                            sb.insert(0,"THIRTY");
                        break;
                    case 1000:
                        if (digit2 > 0)
                            sb.insert(0,"THREE HUNDRED AND ");
                        else
                            sb.insert(0,"THREE HUNDRED ");
                        break;
                    case 10000:
                        sb.insert(0,"THREE THOUSAND ");
                    break;
                        
                    default:
                        break;
                } 
                break;// case 3
                
            case 4 :
                switch (index) {
                    case 10:
                        sb.insert(0,"FOUR");
                        break;
                    case 100:
                        if (digit2 > 0)
                            sb.insert(0,"FORTY-");
                        else 
                            sb.insert(0,"FORTY");
                        break;
                    case 1000:
                        if (digit2 > 0)
                            sb.insert(0,"FOUR HUNDRED AND ");
                        else
                            sb.insert(0,"FOUR HUNDRED ");
                        break;
                    case 10000:
                        sb.insert(0,"FOUR THOUSAND ");
                    break;                        
                    default:
                        break;
                } 
                break;// case 4                

            case 5 :
                switch (index) {
                    case 10:
                        sb.insert(0,"FIVE");
                        break;
                    case 100:
                        if (digit2 > 0)
                            sb.insert(0,"FIFTY-");
                        else 
                            sb.insert(0,"FIFTY");
                        break;
                    case 1000:
                        if (digit2 > 0)
                            sb.insert(0,"FIVE HUNDRED AND ");
                        else
                            sb.insert(0,"FIVE HUNDRED ");
                        break;
                    case 10000:
                        sb.insert(0,"FIVE THOUSAND ");
                    break;                        
                    default:
                        break;
                } 
                break;// case 5               

            case 6 :
                switch (index) {
                    case 10:
                        sb.insert(0,"SIX");
                        break;
                    case 100:
                        if (digit2 > 0)
                            sb.insert(0,"SIXTY-");
                        else 
                            sb.insert(0,"SIXTY");
                        break;
                    case 1000:
                        if (digit2 > 0)
                            sb.insert(0,"SIX HUNDRED AND ");
                        else
                            sb.insert(0,"SIX HUNDRED ");
                        break;
                    case 10000:
                        sb.insert(0,"SIX THOUSAND ");
                    break;                        
                    default:
                        break;
                } 
                break;// case 6                

            case 7 :
                switch (index) {
                    case 10:
                        sb.insert(0,"SEVEN");
                        break;
                    case 100:
                        if (digit2 > 0)
                            sb.insert(0,"SEVENTY-");
                        else 
                            sb.insert(0,"SEVENTY");
                        break;
                    case 1000:
                        if (digit2 > 0)
                            sb.insert(0,"SEVEN HUNDRED AND ");
                        else
                            sb.insert(0,"SEVEN HUNDRED ");
                        break;
                    case 10000:
                        sb.insert(0,"SEVEN THOUSAND ");
                    break;                        
                    default:
                        break;
                } 
                break;// case 7                
                
            case 8 :
                switch (index) {
                    case 10:
                        sb.insert(0,"EIGHT");
                        break;
                    case 100:
                        if (digit2 > 0)
                            sb.insert(0,"EIGHTY-");
                        else 
                            sb.insert(0,"EIGHTY");
                        break;
                    case 1000:
                        if (digit2 > 0)
                            sb.insert(0,"EIGHT HUNDRED AND ");
                        else
                            sb.insert(0,"EIGHT HUNDRED ");
                        break;
                    case 10000:
                        sb.insert(0,"EIGHT THOUSAND ");
                    break;                        
                    default:
                        break;
                } 
                break;// case 8               

            case 9 :
                switch (index) {
                    case 10:
                        sb.insert(0,"NINE");
                        break;
                    case 100:
                        if (digit2 > 0)
                            sb.insert(0,"NINETY-");
                        else 
                            sb.insert(0,"NINETY");
                        break;
                    case 1000:
                        if (digit2 > 0)
                            sb.insert(0,"NINE HUNDRED AND ");
                        else
                            sb.insert(0,"NINE HUNDRED ");
                        break;
                    case 10000:
                        sb.insert(0,"NINE THOUSAND ");
                    break;                        
                    default:
                        break;
                } 
                break;// case 8               

        }
        return sb;        
    }
    private int convertStringToNumber(String[] strArr, int start, int end, int index) {
        if (index >= end+1){
            return 0;
        }
        int i = getNumber(strArr[index]);
        int j = convertStringToNumber(strArr, start, end, index+1);
        if (i == Integer.MIN_VALUE)
            return 0-j;
        else
            return i+j;
    }

    
    private int getNumber(String str) {
        if (str.indexOf('-')>0){
            String s1 = str.substring(0, str.indexOf('-'));
            String s2 = str.substring(str.indexOf('-')+1);
            int i = getNumber(s1);
            int i1 = getNumber(s2);
            return i+i1;
        } else if ("NEGATIVE".equals(str)){
            return Integer.MIN_VALUE;
        } else {
            if ("NINETY".equals(str)){
                return 90;
            } else if ("EIGHTY".equals(str)){
                return 80;
            } else if ("SEVENTY".equals(str)){
                return 70;
            } else if ("SIXTY".equals(str)){
                return 60;
            } else if ("FIFTY".equals(str)){
                return 50;
            } else if ("FORTY".equals(str)){
                return 40;
            } else if ("THIRTY".equals(str)){
                return 30;
            } else if ("TWENTY".equals(str)){
                return 20;
            } else if ("ONE".equals(str)){
                return 1;
            }else if ("TWO".equals(str)){
                return 2;
            }else if ("THREE".equals(str)){
                return 3;
            }else if ("FOUR".equals(str)){
                return 4;
            }else if ("FIVE".equals(str)){
                return 5;
            }else if ("SIX".equals(str)){
                return 6;
            }else if ("SEVEN".equals(str)){
                return 7;
            }else if ("EIGHT".equals(str)){
                return 8;
            }else if ("NINE".equals(str)){
                return 9;
            }else if ("TEN".equals(str)){
                return 10;
            }else if ("ELEVEN".equals(str)){
                return 11;
            }else if ("TWELVE".equals(str)){
                return 12;
            }else if ("THIRTEEN".equals(str)){
                return 13;
            }else if ("FOURTEEN".equals(str)){
                return 14;
            }else if ("FIFTEEN".equals(str)){
                return 15;
            }else if ("SIXTEEN".equals(str)){
                return 16;
            }else if ("SEVENTEEN".equals(str)){
                return 17;
            }else if ("EIGHTEEN".equals(str)){
                return 18;
            }else if ("NINETEEN".equals(str)){
                return 19;
            }
        }
        return 0;
    }

    private int applyOperator(int number1, int number2, char op){
        int result = 0;
        switch(op){
            case '+' :
                result = number1+number2;
                break;
            case '-' :
                result = number1 - number2;
                break;
            case '*' :
                result = number1 * number2;
                break;
        }
        return result;
    }
    
    private boolean isOperator(String string) {        
        return "PLUS".equals(string)||"MINUS".equals(string) || "TIMES".equals(string);
    }
    
    private char getOperator(String operator){
        if ("PLUS".equals(operator)){
            return '+';
        } else if ("MINUS".equals(operator)) {
            return '-';
        } else {
            return '*';
        }
    }
    /**
     * @param args
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException {
        //String str = "TWENTY-TWO TIMES TWO";
        //String str = "TWENTY-SIX MINUS SEVENTY-SEVEN";
        //String str = "NEGATIVE SIXTY PLUS FORTY-TWO";
        //String str = "NEGATIVE THIRTY-FOUR PLUS NEGATIVE THIRTY-FOUR";
        //String str = "ZERO PLUS NEGATIVE SEVENTY-TWO";
        //String str = "TWENTY-TWO TIMES TWO";
        //String str = "THIRTY-THREE PLUS NEGATIVE THIRTY-TWO";
        //String str =   "NINETY-TWO TIMES NEGATIVE FORTY-SEVEN";
        //String str = "NEGATIVE NINETY TIMES NEGATIVE EIGHTY";
        //String str = "NEGATIVE TWENTY-NINE MINUS FORTY-FOUR";
        //String str = "NEGATIVE NINETY-THREE TIMES SEVENTY-THREE";
        //String str = "ZERO TIMES SIXTY-FIVE";
        //String str = "TWENTY-NINE TIMES THIRTY-TWO";
        //String str = "SEVEN TIMES FIFTY-FOUR";
        //String str = "NEGATIVE EIGHTY-ONE TIMES TWENTY-EIGHT";
       // String str = "SEVENTY-EIGHT PLUS EIGHTY-TWO";
       // String str =    "NEGATIVE THIRTY-TWO PLUS NINETY-TWO"       ;
        //String str = "NEGATIVE SIXTY-ONE MINUS NEGATIVE THIRTY" ;
        //String str = "THIRTY-ONE PLUS NEGATIVE TWELVE";
       // String str = "NEGATIVE SEVENTY-FOUR TIMES NEGATIVE SIXTY-THREE";
        //String str = "NEGATIVE SEVEN PLUS SEVEN";
        //String str = "NEGATIVE FOUR MINUS EIGHT";
        //String str =    "SIXTY-FOUR TIMES TWELVE";
        //String str =    "SEVEN MINUS TWENTY-FOUR";
        //String str =    "FORTY-THREE TIMES TWO";
        //String str =    "NEGATIVE TWENTY-ONE MINUS NEGATIVE TWO"        ;
        
        //String str = "SEVENTY-ONE TIMES TWO";
        
//        String str =    "TWENTY TIMES FIFTY";
//        String result = new WordCalculator().evaluate(str);
//        System.out.println(result);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("C://Suman//TopCoder//Round17_2.txt")));
        String str1 = null;
        while((str1 = bufferedReader.readLine()) != null){
            if (str1.length() > 0 ){
                String[] strArr = str1.trim().split(",");
                String str2 = strArr[0].trim();
                String str3 = strArr[1].trim();
                String r = new WordCalculator().evaluate(str2);
                if (r!= null && !r.intern().equals(str3.trim().intern())){
                   System.out.println("Failed for input "+str2); 
                }else {
                    System.out.println("Passed for "+str1);
                }
            } else {
                
            }
        }
    }

}
