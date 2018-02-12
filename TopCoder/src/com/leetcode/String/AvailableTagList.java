package com.leetcode.String;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AvailableTagList {

    List<Integer> subSequenceTags(List<String> targetList,
                                  List<String> availableTagList)
    {
        if (targetList == null || availableTagList == null) {
            return Arrays.asList(0);
        }
        if (targetList.size() == 0 || availableTagList.size() == 0) {
            return Arrays.asList(0);
        }
        if (targetList.size() == 1) {
            int i = 0;
            for (i= 0 ; i < availableTagList.size() ; i++) {
                if (availableTagList.get(i).equals(targetList.get(0))) {
                    return Arrays.asList(i, i);
                }
            }
        }
        int minLength = Integer.MAX_VALUE;
        int start = -1;
        int end = -1;
        for ( int i = 0 ; i < availableTagList.size() ; i++) {
            String available = availableTagList.get(i);
            if (targetList.contains(available)) {
                Set<String> alreadySeen = new HashSet<>();
                alreadySeen.add(available);
                int count = targetList.size() - 1;
                for ( int j = i + 1; j < availableTagList.size() ; j++) {
                    String str1 = availableTagList.get(j);
                    if (alreadySeen.contains(str1)) {
                        continue;
                    }
                    if (targetList.contains(str1)) {
                        count--;
                    }
                    alreadySeen.add(available);
                    if (count == 0 && (j-i)< minLength ) {
                        minLength = j - i;
                        start = i;
                        end = j;
                        break;
                    }
                }
            }
        }
        if (start != -1) {
            return Arrays.asList(start, end);
        } else {
            return Arrays.asList(0);
        }
    }

    public static void main(String[] args) {
        List<String> list = Arrays.asList(new String[] {"in"});
        List<String> list1
                = Arrays.asList(new String[] {"the","spain", "that", "the", "rain", "in", "spain", "stays", "forecast", "in", "the"});
        List<Integer> result = new AvailableTagList().subSequenceTags(list, list1);
        for (Integer i : result) {
            System.out.println(i);
        }

    }

}
