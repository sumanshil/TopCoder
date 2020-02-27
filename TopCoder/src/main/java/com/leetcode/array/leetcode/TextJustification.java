package com.leetcode.array.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//https://leetcode.com/problems/text-justification/
public class TextJustification {
    public List<String> fullJustify(String[] words, int maxWidth) {
        int startIndex = 0;
        List<String> results= new ArrayList<>();
        while(true) {
            List<Integer>  blockSizes = getBlocks(startIndex, words, maxWidth);
            StringBuilder stringBuilder = new StringBuilder();
            for (Integer gapSize : blockSizes) {
                String spaces = getSpaces(gapSize);
                stringBuilder.append(words[startIndex++]);
                stringBuilder.append(spaces);
            }
            if (stringBuilder.toString().length() > 0 ) {
                results.add(stringBuilder.toString());
            }

            if (startIndex == words.length) {
                break;
            }
        }

        return results;
    }

    private List<Integer> getBlocks(int startIndex, String[] words, int maxWidth) {

        if (startIndex == words.length) {
            List<Integer> list = new ArrayList<>();
            //list.add(maxWidth - words[startIndex].length());
            return list;
        }

        int currentLetterCount = 0;
        int wordCount = 0;
        while (currentLetterCount + wordCount < maxWidth + 1 && startIndex < words.length) {
            currentLetterCount += words[startIndex++].length();
            wordCount++;
        }

        if (currentLetterCount + wordCount >= maxWidth + 1) {
            startIndex--;
            if (startIndex < words.length) {
                wordCount -= 1;
                currentLetterCount -= words[startIndex].length();
            }
        }

        boolean hasWordInLastPosition = true;
        boolean isLastSentence = false;
        if (wordCount == 1 || startIndex == words.length) {
            hasWordInLastPosition = false;
            if (startIndex == words.length) {
                isLastSentence = true;
            }
        }


        int gapCount = wordCount;
        if (hasWordInLastPosition) {
            gapCount -= 1;
        }


        int uniformGaps = (maxWidth - currentLetterCount)/gapCount;
        int remainingGaps = (maxWidth - currentLetterCount)%gapCount;
        List<Integer> list = new ArrayList<>();
        if (isLastSentence) {
            int remaining = maxWidth - currentLetterCount;

            for (int i = 0 ; i < gapCount-1 ; i++) {
                list.add(1);
                remaining--;
            }
            list.add(remaining);
        } else {
            for (int i = 0; i < gapCount; i++) {
                if (remainingGaps > 0) {
                    remainingGaps--;
                    list.add(uniformGaps + 1);
                } else {
                    list.add(uniformGaps);
                }
            }
        }
        if (hasWordInLastPosition) {
            list.add(0);
        }
        return list;
     }

    private String getSpaces(Integer gapSize) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0 ; i < gapSize ; i++) {
            stringBuilder.append(" ");
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        //String[] words = {"This", "is", "an", "example", "of", "text", "justification."};
        String[] words = {"What","must","be","acknowledgment","shall","be"};
        int maxWidth = 16;
        List<String> list = new TextJustification().fullJustify(words, maxWidth);
        for (String str : list) {
            System.out.println(str.length());
            System.out.println(str);
        }
    }
}
