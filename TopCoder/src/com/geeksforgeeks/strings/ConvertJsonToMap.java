package com.geeksforgeeks.strings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConvertJsonToMap {

    public Map convert(String str){
     //   String trimmed = str.substring(1, str.length()-1);
        Object result = recursive(str);
        return (Map)result;
    }

    class Token {
        String str;
        int endIndex;
        public Token(String str, int endIndex) {
            this.str = str;
            this.endIndex = endIndex;
        }
    }
    private Object recursive(String input) {
        if (input == null || input.length() == 0){
            return null;
        }

        if (input.startsWith("{")){
            Map<String, Object> map = new HashMap<>();
            String trimmed = input.substring(1, input.length()-1);

            return map;
        } else if (input.startsWith("[")){
            List<Object> retVal = new ArrayList<>();
            String trimmed = input.substring(1, input.length()-1);
            String[] splitted = trimmed.split(",");
            for (String str : splitted){
                String[] arr2 = str.split(":");
                String key = arr2[0];
                String value = arr2[1];
                Object convertedValue = recursive(value);
                //map.put(key, convertedValue);
            }
        } else {
            return input;
        }
        return null;
    }



    public static void main(String[] args) {
        String str = "{\n" +
                "    \"name\":\"John\",\n" +
                "    \"age\":30,\n" +
                "    \"cars\": [\n" +
                "        { \"name\":\"Ford\", \"models\":[ \"Fiesta\", \"Focus\", \"Mustang\" ] },\n" +
                "        { \"name\":\"BMW\", \"models\":[ \"320\", \"X3\", \"X5\" ] },\n" +
                "        { \"name\":\"Fiat\", \"models\":[ \"500\", \"Panda\" ] }\n" +
                "    ]\n" +
                " }";
    }
}
