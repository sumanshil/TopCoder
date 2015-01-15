package com.topcoder.problems.round17;

import java.util.StringTokenizer;

public class WordCalculatorTC {
    public String tens[] = { "","","TWENTY", "THIRTY", "FORTY", "FIFTY", "SIXTY", "SEVENTY", "EIGHTY", "NINETY" };
    public String ones[] = { "ZERO", "ONE", "TWO", "THREE", "FOUR", "FIVE", "SIX", "SEVEN", "EIGHT", "NINE","TEN", "ELEVEN", "TWELVE", "THIRTEEN", "FOURTEEN", "FIFTEEN", "SIXTEEN", "SEVENTEEN", "EIGHTEEN", "NINETEEN" };
    public final int MINUS = 0;
    public final int PLUS = 1;
    public final int TIMES = 2;
    
    private int value(String s){
        StringTokenizer st = new StringTokenizer(s, " -");
        int sum = 0;
        int sign = 1;
        while(st.hasMoreTokens()){
            String s1 = st.nextToken();
            if ("NEGATIVE".equals(s1)){
                sign = -1;
            } else {
                for(int i = 0 ; i < tens.length ; i++){
                    if (tens[i].equals(s1)){
                        sum += (10*i);
                    }
                }
                
                for(int i = 0 ; i < ones.length ; i++){
                    if (ones[i].equals(s1)){
                        sum += i;
                    }
                }
            }
        }
        return sign * sum;
    }
    
    private String convert(int a){
        String s = "";
        boolean g = false;
        if (a == 0) return "ZERO";
        
        if (a < 0){
            a = -a;
            s = "NEGATIVE ";    
        }
        if (a > 1000){
            s += ones[a/1000]+" THOUSAND ";
            a = a%1000;
            g = true;
        }
        if (a > 100){
            s += ones[a/100]+" HUNDRED ";
            a = a%100;
            g = true;
        }
        if ( a> 0){
            if (g){
                s += "AND ";                
            }
            g = false;
            if (a >= 20){
                s += tens[a/10];
                a = a%10;
                if (a > 0){
                    s += "-"+ones[a];
                }
            } else {
                s+=ones[a];
            }
        }
        return s;
    }
    
    
    public String evaluate(String s){
        int op=0;
        int p = 0;
        if (s.indexOf("MINUS")> 0){
            op = MINUS;
            p = s.indexOf("MINUS");
        } else if (s.indexOf("PLUS") > 0){
            op = PLUS;
            p = s.indexOf("PLUS");
        } else if (s.indexOf("TIMES") > 0){
            op = TIMES;
            p = s.indexOf("TIMES");
        }
        String a = s.substring(0, p-1);
        String b = s.substring(p+5);
        int d = value(a);
        int e = value(b);
        switch(op){
            case PLUS :
                d = d+e;
                break;
            case MINUS :
                d = d-e;
                break;
            case TIMES :
                d = d*e;
                break;
        }
        return convert(d);
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        String str =    "NEGATIVE SIXTEEN TIMES SEVENTY-EIGHT";
        String result = new WordCalculatorTC().evaluate(str);
        System.out.println(result);

    }

}
