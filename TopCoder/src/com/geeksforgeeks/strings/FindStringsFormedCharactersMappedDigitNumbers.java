package com.geeksforgeeks.strings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by sshil on 6/21/2016.
 */
//http://www.geeksforgeeks.org/find-strings-formed-characters-mapped-digits-number/
public class FindStringsFormedCharactersMappedDigitNumbers {
    static class Info {
        private Map<String, Integer> characterToIndexMap = new HashMap<>();
        private Map<Integer, Set<String>> indexToCharSet = new HashMap<>();

        public void insert(String key, int index ) {
            characterToIndexMap.put(key, index);
            Set<String> set = indexToCharSet.get(index);
            if (set == null){
                set = new HashSet<>();
            }
            set.add(key);
            indexToCharSet.put(index, set);
        }

        public Set<String> getListOfString(String key){
            int index = characterToIndexMap.get(key);
            return indexToCharSet.get(index);
        }

        public Set<String> getListOfStringFromIndex(int index){
            return indexToCharSet.get(index);
        }

    }

    public void print(String str, Info info){
        printRecursive(str, info, 0);
    }

    private Map<String, String> map = new HashMap<>();
    private Map<String, String> invertedMap = new HashMap<>();
    private List<String> list = new ArrayList<>();
    private void printRecursive(String str,
                                Info info,
                                int index) {
        if (index == str.length()){
            list.stream().forEach(System.out::print);
            System.out.println();
            return;
        }
        String key = str.charAt(index)+"";
        if (map.containsKey(key)){
            String converted = map.get(key);
            list.add(converted);
            printRecursive(str, info, index+1);
            list.remove(list.size()-1);
        } else {
            Set<String> set = info.getListOfStringFromIndex(Integer.parseInt(key));
            for (String strSet : set){
                map.put(key, strSet);
                invertedMap.put(strSet, key);
                list.add(strSet);
                printRecursive(str, info, index+1);
                String s = list.get(list.size()-1);
                list.remove(list.size()-1);
                if (!list.contains(s)){
                    String indexKey = invertedMap.get(s);
                    map.remove(indexKey);
                    invertedMap.remove(s);
                }
            }
        }
    }

    public static void main(String[] args) {
        Info info = new Info();
        char c = 'A';
        int index = 0;
        int keyIndex = 1;
        while (c <= 'Z'){
            if (index == 3){
                index = 0;
                keyIndex++;
                continue;
            } else {
                info.insert(c+"", keyIndex);
                index ++;
            }
            c = (char)(c + 1);
        }
        new FindStringsFormedCharactersMappedDigitNumbers().print("121", info);
    }
}
