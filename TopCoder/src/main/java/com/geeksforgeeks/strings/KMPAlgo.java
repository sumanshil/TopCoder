package com.geeksforgeeks.strings;

public class KMPAlgo {

    public void find(String input, String pattern){
        int[] prefixArray = preparePrefixArray(pattern);

        int strIndex = 0;
        int patternIndex = 0;
        int currentStartIndex = 0;

        while (strIndex < input.length()){
            if (patternIndex == pattern.length()){
                System.out.println("Pattern found between "+ currentStartIndex+ ":" + (currentStartIndex+patternIndex));
                boolean found = false;
                for ( int i = patternIndex-1 ; i > 0; i--){
                    if (prefixArray[i] == 1) {
                        currentStartIndex = currentStartIndex + i;
                        found = true;
                        break;
                    }
                }
                if (!found){
                    currentStartIndex++;
                }
                strIndex = currentStartIndex;
                patternIndex = 0;

            }

            if (input.charAt(strIndex) == pattern.charAt(patternIndex)){
                strIndex++;
                patternIndex++;
            } else {
                //patternIndex = 0;
                boolean found = false;
                if (prefixArray[patternIndex] < prefixArray.length){
                    for ( int i = patternIndex ; i > 0; i--){
                        if (prefixArray[i] == 1) {
                            currentStartIndex = currentStartIndex + i;
                            found = true;
                            break;
                        }
                    }
                }
                if (!found){
                    currentStartIndex++;
                }
                strIndex = currentStartIndex;
                patternIndex = 0;
            }
        }
        if (patternIndex == pattern.length()) {
            System.out.println("Pattern found between " + currentStartIndex + ":" + (currentStartIndex + patternIndex));
        }
    }

    private int[] preparePrefixArray(String pattern) {
        int[] arr = new int[pattern.length()];

        int count = 1;
        int firstIndex = 0;
        int secondIndex = 0;
        int currenIndex = 0;
        arr[0] = 1;
        for (int i = 1 ; i < pattern.length() ; i++){
            if (pattern.charAt(i) == pattern.charAt(0)){
                secondIndex = i;
                break;
            } else {
                count++;
                arr[i] = count;
                currenIndex++;
            }
        }
        count = 1;
        if (currenIndex < pattern.length()-1) {
            while (secondIndex < pattern.length()) {
                if (pattern.charAt(firstIndex) == pattern.charAt(secondIndex)) {
                    firstIndex++;
                    arr[secondIndex++] = count++;
                } else {
                    firstIndex = 0;
                    secondIndex++;
                    count = 1;
                }
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        String input = "AABAACAADAABAABA";
        String pattern = "AABA";
        new KMPAlgo().find(input, pattern);
    }
}
