package com.topcoder.problems;

import java.util.ArrayList;
import java.util.List;

public class StringParser {

    public ArrayList parse(String str){
        char c = str.charAt(0);
        parseUtil(c, str, 0);
        return al;
    }
    private String sb = new String();
    private ArrayList al = new ArrayList();
    private char  parseUtil(char delimiter, String str, int index) {
        if (index == str.length())
            return ' ';
        
        char c= str.charAt(index);
        char prevChar = parseUtil(delimiter, str, index+1);
        if (c != delimiter){
            sb = c+sb;
        } else if (c == delimiter && prevChar != delimiter && prevChar != ' '){
            al.add(sb);
            sb = new String();
        }
        return c;
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        //String s = ",This,is,,,a,test,";
        String s = "-1 HO 2?-3-A a";
        List<String> l = new StringParser().parse(s);
        for(String sq : l){
            System.out.println(sq);
        }
    }

}
