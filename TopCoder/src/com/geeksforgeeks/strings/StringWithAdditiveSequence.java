package com.geeksforgeeks.strings;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by sshil on 10/16/16.
 */
//http://www.geeksforgeeks.org/string-with-additive-sequence/
public class StringWithAdditiveSequence {

//    public static void main(String[] args) {
//
//    }

    private List<Integer> list = new LinkedList<>();
    public void find(String str){
        boolean result = recursive(0, str);
        System.out.println(result);
    }

    private boolean recursive(int index, String str) {
        if (index >= str.length()){
            return false;
        }

        if (list.size() >= 2) {
            List<Integer> newList = new ArrayList<>(list);
            boolean result = check(newList, str, index);
            return result;
        } else {
            for ( int len = 1 ; index+len < str.length() ; len++){
                String subStr = str.substring(index, index+len);
                int number = Integer.parseInt(subStr);
                list.add(number);
                boolean res = recursive(index+len, str);
                if (res){
                    return true;
                } else {
                   list.remove(list.size()-1);
                }

            }
        }
        return false;
    }

    private boolean check(List<Integer> newList, String str, int index) {
        if (index >= str.length()){
            return true;
        }
        int number1 = newList.get(newList.size() - 1);
        int number2 = newList.get(newList.size() - 2);
        int result = number1 + number2;
        String strResult = result + "";
        int length = strResult.length();
        if (index + length <= str.length()) {
            String next = str.substring(index, index + length);
            if (next.equals(strResult)) {
                newList.add(Integer.parseInt(next));
                return check(newList, str, index + length);
            } else {
                return false;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        String str = "12345678";
        new StringWithAdditiveSequence().find(str);
    }
}
