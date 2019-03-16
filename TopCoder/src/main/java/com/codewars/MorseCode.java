package com.codewars;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class MorseCode {

    public static String get(String morseCode) {

        Map<String, String> map = new HashMap<>();
        map.put("....", "H");
        map.put(".", "E");
        map.put("-.--", "Y");
        map.put(".---", "J");
        map.put("..-", "U");
        map.put("-..", "D");

        return map.get(morseCode);
    }

    public static String transform(String str) {
        return Arrays.stream(str.split(" ")).map(MorseCode::get).collect(Collectors.joining(""));
    }

    public static void main(String[] args) {
        String str = ".... . -.--   .--- ..- -.. .";
        //String str = "   .   ";
        String result = Arrays.stream(str.trim().split("   ")).map(MorseCode::transform)
                .collect(Collectors.joining(" "));
        System.out.println(result);
        /*
        StringBuffer stringBuffer = new StringBuffer();
        StringBuffer result = new StringBuffer();
        int spaceCount = -1;
        for (int i = 0 ; i < str.length() ; ) {
            if (str.charAt(i) == ' ' && spaceCount == -1) {
                String code = stringBuffer.toString();
                String character = MorseCode.get(code);
                spaceCount = 0;

                while (str.charAt(i) == ' ') {
                    i++;
                    spaceCount++;
                }
                if (spaceCount == 1) {
                    result.append(character);
                } else if (spaceCount == 3) {
                    result.append(character).append(" ");
                }
                spaceCount = -1;
                stringBuffer = new StringBuffer();
                stringBuffer.append(str.charAt(i));
            } else {
                stringBuffer.append(str.charAt(i));
            }
            i++;
        }

        if (stringBuffer.length() > 0) {
            String code = stringBuffer.toString();
            String character = MorseCode.get(code);
            result.append(character);
        }
        //return result.toString();
        System.out.println(result.toString());
        */

    }
}
