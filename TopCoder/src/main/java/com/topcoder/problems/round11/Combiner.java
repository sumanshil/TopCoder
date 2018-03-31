package com.topcoder.problems.round11;

public class Combiner {

    private String  combine(String param0, String param1){
        String s1;
        String s2;
        String s3=null;
        if (param0.length() > param1.length()){
            s1 = param0.substring(0, param1.length());
            s2 = param1;
            s3 = param0.substring(param1.length());
        } else if (param1.length() > param0.length()){
            s1 = param0;
            s2 = param1.substring(0, param0.length());
            s3 = param1.substring(param0.length());
        } else {
            s1 = param0;
            s2 = param1;
            s3 = null;
        }
        String combined = combineUtil(s1,s2,0);
        if (s3 != null){
            return combined+s3;
        } else {
            return combined;
        }
        
    }
    private String combineUtil(String s1, String s2, int index) {
        if (index==s1.length()){
            return "";
        }
        char c1 = s1.charAt(index);
        char c2 = s2.charAt(index);
        String ret = combineUtil(s1, s2, index+1);
        return ""+c1+c2+ret;
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        String s1= "aa";
        String s2 = "bb";
        String result = new Combiner().combine("asdfgbasdf", "brrac");
        System.out.println(result);
    }

}