package com.geeksforgeeks.strings;

import java.util.*;
import java.util.stream.Collectors;


/**
 * Created by sshil on 6/21/17.
 */
//http://www.geeksforgeeks.org/decode-string-recursively-encoded-count-followed-substring/
public class DecodeAStringRecursivelyEncodedAsCount {

    public void testUsingStack(String str){
        Stack<String> stringStack = new Stack<>();
        for ( int i = 0 ; i < str.length() ; i++) {

            if (str.charAt(i) == ']'){
                List<String> stringArrayList = new ArrayList<>();
                String strTemp = stringStack.pop();
                do {
                    stringArrayList.add(strTemp);
                    strTemp = stringStack.pop();
                } while ( !strTemp.equals("["));

                Collections.reverse(stringArrayList);
                String st = stringArrayList.stream().collect(Collectors.joining());
                int count = Integer.parseInt(stringStack.pop());

                StringBuilder stringBuilder = new StringBuilder();
                for ( int j = 0 ; j < count ; j++ ){
                    stringBuilder.append(st);
                }
                stringStack.push(stringBuilder.toString());
            } else {
                stringStack.push(str.charAt(i)+"");
            }
        }

        StringBuilder stringBuilder = new StringBuilder();
        while ( !stringStack.isEmpty() ){
            stringBuilder.append(stringStack.pop());
        }
        System.out.println(stringBuilder.toString());
    }

    public void test (String str) {
        StringTokenizer stringTokenizer = new StringTokenizer(str, "[]", true);
//        while (stringTokenizer.hasMoreTokens()){
//            String str1 = stringTokenizer.nextToken();
//            System.out.println(str1);
//        }

        String res = findRecursive(stringTokenizer);
        System.out.println(res);
    }

    private String findRecursive(StringTokenizer stringTokenizer) {
        List<String> retVal = new LinkedList<>();
        String resStr = null;
        while (stringTokenizer.hasMoreTokens()){
            String token = stringTokenizer.nextToken();
            if (token.equals("[")){
                String retString = findRecursive(stringTokenizer);
                retVal.add(retString);
            } else if (token.equals("]")){
                String res = decode(retVal);
                resStr = res;
            } else {
                if (token.charAt(token.length()-1) >= '0' && token.charAt(token.length()-1) <= '9'){
                    String prefixStr = token.substring(0, token.length()-1);
                    String suffixStr = token.substring(token.length()-1);
                    retVal.add(prefixStr);
                    retVal.add(suffixStr);
                } else {
                    retVal.add(token);
                }
            }
        }
        return resStr != null ? resStr : decode(retVal);
    }

    private String decode(List<String> retVal) {
        StringBuilder stringBuilder = new StringBuilder();
        Iterator<String> iterator = retVal.iterator();
        while (iterator.hasNext()) {
            String str = iterator.next();
            if (isANumber(str)){
                int number = Integer.parseInt(str);
                String nextStr = iterator.next();
                for ( int j = 0 ; j < number ; j++){
                    stringBuilder.append(nextStr);
                }
            } else {
                stringBuilder.append(str);
            }
        }
        return stringBuilder.toString();
    }

    private boolean isANumber(String str) {
        try {
            Integer.parseInt(str);
        } catch (Exception e){
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        String str = "3[b2[ca]]";
        new DecodeAStringRecursivelyEncodedAsCount().testUsingStack(str);
    }
}
