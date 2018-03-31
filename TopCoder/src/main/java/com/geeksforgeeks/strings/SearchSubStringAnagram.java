package com.geeksforgeeks.strings;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sshil on 5/24/2016.
 */
public class SearchSubStringAnagram {
    static class PatternState {
        private Map<String, Integer> currentItems = new HashMap<>();
        private Map<String, Integer> expectedItems = new HashMap<>();
        private int unwantedCharacterCount = 0;
        private int mismatchedValidCharacterCount = 0;

        public Map<String, Integer> getCurrentItems() {
            return currentItems;
        }

        public void setCurrentItems(Map<String, Integer> currentItems) {
            this.currentItems = currentItems;
        }

        public Map<String, Integer> getExpectedItems() {
            return expectedItems;
        }

        public void setExpectedItems(Map<String, Integer> expectedItems) {
            this.expectedItems = expectedItems;
        }

        public int getUnwantedCharacterCount() {
            return unwantedCharacterCount;
        }

        public void setUnwantedCharacterCount(int unwantedCharacterCount) {
            this.unwantedCharacterCount = unwantedCharacterCount;
        }

        public int getMismatchedValidCharacterCount() {
            return mismatchedValidCharacterCount;
        }

        public void setMismatchedValidCharacterCount(int mismatchedValidCharacterCount) {
            this.mismatchedValidCharacterCount = mismatchedValidCharacterCount;
        }

        public void incrementMismatchedValidCharacterCount() {
            this.mismatchedValidCharacterCount++;
        }
        public void insertNewCharacter(char c){
            String key = c +"";
            if (this.expectedItems.containsKey(key)){
                int currentCount = this.currentItems.get(key);
                int newCount = currentCount+1;
                if (this.getExpectedItems().get(key)== newCount){

                }
            }
        }
    }

    public void count(String mainString, String pattern){
        PatternState patternState = new PatternState();
        for ( int i = 0 ; i < pattern.length() ; i++){
            String key = pattern.charAt(i)+"";
            if (patternState.getExpectedItems().containsKey(key)){
                patternState.getExpectedItems().put(key, 1);
            } else {
                int count = patternState.getExpectedItems().get(key);
                patternState.getExpectedItems().put(key, count+1);
            }
            patternState.incrementMismatchedValidCharacterCount();
        }



    }

    public static void main(String[] args) {

    }
}
