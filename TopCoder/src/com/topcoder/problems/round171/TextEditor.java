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
        int prevI = -1;
        for ( int i = 0 ; i < arr.length ;) {
            int length = 0;

            List<String> ll= new ArrayList<>();
            ll.add(arr[i]);
            length = arr[i].length();
            int j;
            boolean added = false;
            for (j = i+1 ;(length+(ll.size()-1)) < width && j < arr.length; j++){
                ll.add(arr[j]);
                length += arr[j].length();
                i = j;
                added = true;
            }
            if (added && i == arr.length-1){
                i = i+1;
            }
            if ((length + (ll.size() -1 )) > width){
                ll.remove(ll.size()-1);
                i = j-1;
            } else {
                i = i +1;
            }
            result.add(ll.stream().collect(Collectors.joining(" ")));
            if (!added && i == arr.length-1){
                break;
            }
        }
        result.stream().forEach(System.out::println);
        int mid = 0;
        if (result.size() % 2 == 0){
            mid = (result.size()/2);
        } else {
            mid = (result.size() / 2) + 1;
        }
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
        String[] str = {" I WONDER by my troth, what thou,",
                "and I Did, till we lovd? were we",
                "not weand till then? But suckd on",
                "countrey pleasures, childishly? Or",
                "snorted we in the seaven sleepers",
                "den? Twas so; But this, all",
                "pleasures fancies bee. If ever any",
                "beauty I did see, Which I desird,",
                "and got, twas but a dreame of",
                "thee. And now good morrow to our",
                "waking soules, Which watch not one",
                "another out of feare; For love, all",
                "love of other sights controules,",
                "And makes one little roome, an",
                "every where. Let seadiscoverers to",
                "new worlds have gone, Let Maps to",
                "other, worlds on worlds have",
                "showne, Let us possesse one world,",
                "each hath one, and is one. My face",
                "in thine eye, thine in mine",
                "appeares, And true plaine hearts",
                "doe in the faces rest, Where can we",
                "finde two better hemispheares",
                "Without sharpe North, without",
                "declining West? What ever dyes, was",
                "not mixt equally; If our two loves",
                "be one, or, thou and I Love so",
                "alike, that none doe slacken, none",
                "can die.",
                "John Donne"};
        String[] result = new TextEditor().twoColumn(str, 45);
        System.out.println("====");
        Arrays.stream(result).forEach(System.out::println);
    }
}
