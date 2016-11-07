package com.topcoder.problems.round171;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by SumanChandra on 11/6/2016.
 */
//https://community.topcoder.com/stat?c=problem_statement&pm=1942&rd=4660
public class TextEditor {

    public String[] twoColumn(String[] text, int width){
        List<String> result = new ArrayList<>();
        String intermediate = Arrays.stream(text).map(String::trim).collect(Collectors.joining(" "));
        System.out.println(intermediate);
        String[] arr = intermediate.split("\\s+");
        for ( int i = 0 ; i < arr.length-1 ;) {
            int length = 0;
            List<String> ll= new ArrayList<>();
            ll.add(arr[i]);
            length = arr[i].length();
            int j;
            for (j = i+1 ;(length+(ll.size()-1)) < width && j < arr.length; j++){
                ll.add(arr[j]);
                length += arr[j].length();
                i = j;
            }
            if ((length + (ll.size() -1 )) > width){
                ll.remove(ll.size()-1);
                i = j-1;
            }
            result.add(ll.stream().collect(Collectors.joining(" ")));
        }
        result.stream().forEach(System.out::println);
        int mid =(result.size()/2)+1;
        int tempMid = mid;
        int start = 0;
        List<String> finalResult = new ArrayList<>();
        while (start < tempMid){
            finalResult.add(result.get(start++));
            if (mid < result.size()){
                finalResult.add(result.get(mid++));
            }

        }
        return finalResult.toArray(new String[0]);
    }

    public static void main(String[] args) {
        String[] str = {"Such a preposterous use of !punctuation! !!!","Who would write ... something like this ???"};
        String[] result = new TextEditor().twoColumn(str, 17);
        Arrays.stream(result).forEach(System.out::println);
    }
}
