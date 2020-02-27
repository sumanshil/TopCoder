package com.leetcode.String;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.topcoder.problems.round13.Array;

//https://leetcode.com/problems/word-break-ii/
public class WordBreak2 {

    public List<String> wordBreak(String s, List<String> wordDict) {
        Map<Integer, List<String>> dpMap = new HashMap<>();
        dpMap.put(0, new LinkedList<>());

        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;

        Map<Integer, List<String>> wordDictMap = new HashMap<>();

        for (String word : wordDict) {
            if (wordDictMap.containsKey(word.length())) {
                wordDictMap.get(word.length()).add(word);
            } else {
                List<String> list = new LinkedList<>();
                list.add(word);
                wordDictMap.put(word.length(), list);
            }
        }

        for (int i = 1 ; i <= s.length() ; i++) {
            List<String> newList = new LinkedList<>();
            for(Map.Entry<Integer, List<String>> entry : wordDictMap.entrySet()) {
                if (entry.getKey() <= i && dp[i - entry.getKey()]) {
                    int suffixLength = entry.getKey();
                    int lastIndex = i - suffixLength;
                    String subStr = s.substring(lastIndex, lastIndex + suffixLength);
                    if (dp[lastIndex] && wordDictMap.containsKey(subStr.length()) && wordDictMap.get(subStr.length())
                            .contains(subStr)) {
                        dp[i] = true;
                        if (dpMap.containsKey(lastIndex)) {
                            List<String> list = dpMap.get(lastIndex);
                            if (list.isEmpty()) {
                                newList.add(subStr);
                            } else {
                                for (String list2 : list) {
                                    newList.add(list2 + " " + subStr);
                                }
                            }
                        }
                    }
                }
            }
            dpMap.put(i, newList);
        }
        return dpMap.get(s.length());
    }
//public List<String> wordBreak(String s, List<String> wordDict) {
//    if(s == null || s.length() == 0 || wordDict == null || wordDict.size() == 0)
//        return new ArrayList<>();
//    int len = s.length();
//    List<String> res = new ArrayList<>();
//    boolean[] memo = new boolean[len];
//    Arrays.fill(memo, true);
//    dfs(s, wordDict, res, new StringBuilder(), memo, 0);
//    return res;
//}
//    private void dfs(String s, List<String> wordDict, List<String> res, StringBuilder sb, boolean[] memo, int index){
//        int len = s.length();
//        // base case
//        if(index == len){
//            res.add(sb.toString());
//            return;
//        }
//        // 一开始 memo里面全是true, 如果遇到false说明这条路不需要找了，往下找肯定找不到
//        // 因为之前其他分支已经找过了
//        // start with true, if we find a false which means this branch will not give you a solution
//        // then, we dont have to search this branch next time
//        if(!memo[index]) return;
//
//        // dfs recursion
//        int resultSize = res.size();
//        int sbLen = sb.length();
//        for(int i = index; i < len; i++){
//            String str = s.substring(index, i+1);
//            if(wordDict.contains(str)){
//                if(sbLen == 0) sb.append(str);
//                else sb.append(" " + str);
//                dfs(s, wordDict, res, sb, memo, i+1);
//                sb.setLength(sbLen);
//            }
//        }// 如果找了一圈没发现size改变，说明没有找到,这个时候把它设置为false
//        //if after a dfs, the result size not changed, that means this branch will not give us a solution, just mark it as false, next loop will nevet go thru this branch
//        if(resultSize == res.size()){
//            memo[index] = false;
//        }
//    }
    public static void main(String[] args) {

        String s = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        List<String> wordDict = Arrays.asList("a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa");
        //String s = "leetcode";
        //List<String> wordDict = Arrays.asList("leet", "code");
        List<String> res = new WordBreak2().wordBreak(s, wordDict);
        System.out.println(res);
    }

}
