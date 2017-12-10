package com.geeksforgeeks.strings;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//http://www.geeksforgeeks.org/split-string-substrings-using-delimiter/
public class SplitStringIntoSubStrings {

    public void find (String str, char dCh) {
        StringBuilder stringBuilder = new StringBuilder();
        List<String> splitStrings = new ArrayList<>();

        for (int i = 0 ; i < str.length() ; i++) {
            if (str.charAt(i) == dCh) {
                if (stringBuilder.toString().length() > 0){
                    splitStrings.add(stringBuilder.toString());
                }
                stringBuilder = new StringBuilder();
            } else {
                stringBuilder.append(str.charAt(i));
            }
        }

        if (stringBuilder.toString().length() > 0) {
            splitStrings.add(stringBuilder.toString());
        }
        String result = splitStrings.stream().collect(Collectors.joining("->"));
        System.out.println(result);
    }

    public static void main(String[] args) {
        String str = "##ayush##jauhari####";
        char dCh = '#';
        new SplitStringIntoSubStrings().find(str, dCh);
    }

}
