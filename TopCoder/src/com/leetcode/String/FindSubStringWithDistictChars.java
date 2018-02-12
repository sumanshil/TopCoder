package com.leetcode.String;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FindSubStringWithDistictChars {
    // METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED
    public List<String> subStringsKDist(String inputStr, int num)
    {
        Set<String> retVal = new HashSet<>();

        if (inputStr == null || num == 0) {
            return Collections.emptyList();
        }
        if (num == 1) {
            List<String> retList = new ArrayList<>();
            for ( int i = 0 ; i < inputStr.length() ; i++) {
                String str = inputStr.charAt(i) + "";
                retVal.add(str);
            }
            retList.addAll(retVal);
        }
        Map<String,Integer> map = new HashMap<>();
        int currentWindowLength = 1;
        int currentRunningIndex = 1;
        map.put(inputStr.charAt(0)+"", 0);

        while (currentRunningIndex < inputStr.length()) {
            char c = inputStr.charAt(currentRunningIndex);
            String str = c + "";
            if ( map.containsKey(str)) {
                int lastIndex = map.get(str);
                if (currentRunningIndex - lastIndex <= currentWindowLength) {
                    currentWindowLength = (currentRunningIndex - lastIndex);
                    map.put(str, currentRunningIndex);
                } else {
                    currentWindowLength++;
                }
            } else {
                currentWindowLength++;
            }
            if (currentWindowLength == num) {
                int startIndex =  currentRunningIndex - (currentWindowLength - 1);
                String subStr = inputStr.substring(startIndex, currentRunningIndex+1);
                retVal.add(subStr);
                currentWindowLength--;
            }
            map.put(str, currentRunningIndex);
            currentRunningIndex++;
        }
        List<String> retList = new ArrayList<>();
        retList.addAll(retVal);
        return retList;
        // WRITE YOUR CODE HERE
    }

    public static void main(String[] args) {
        String str = "awaglknagawunagwkwagl";
        int k = 1;
        List<String> result = new FindSubStringWithDistictChars().subStringsKDist(str, k);
        for (String str1 : result) {
            System.out.println(str1);
        }
    }
}
