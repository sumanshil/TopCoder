package com.geeksforgeeks.strings;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by sshil on 6/9/2016.
 */
//http://www.geeksforgeeks.org/print-ways-break-string-bracket-form/
public class PrintAllWaysToBreakAStringInBracketForm {

    static class GroupInfo{
        int index;
        String str;
        public GroupInfo(int index, String str){
            this.index = index;
            this.str  = str;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public String getStr() {
            return str;
        }

        public void setStr(String str) {
            this.str = str;
        }
    }
    private List<String> list = new LinkedList<>();
    public void print(String str){
        printRecursive(str);
    }

    private void printRecursive(String str) {
        if (str == null|| str.trim().length() == 0){
            list.stream().forEach(System.out::print);
            System.out.println();
            return;
        }
        for ( int i = 1 ; i <= str.length() ; i++) {
            String partOne = str.substring(0, i);
            list.add("(" + partOne + ")");
            if ( i <= str.length()) {
                String rest = str.substring(i);
                printRecursive(rest);
            } else {
                printRecursive(null);
            }
            list.remove(list.size()-1);
        }
    }


    public static void main(String[] args) {
        String str = "abcd";
        new PrintAllWaysToBreakAStringInBracketForm().print(str);
    }
}
