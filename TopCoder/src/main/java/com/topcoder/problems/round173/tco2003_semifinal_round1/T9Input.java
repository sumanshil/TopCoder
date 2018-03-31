package com.topcoder.problems.round173.tco2003_semifinal_round1;

import java.util.*;
import java.util.stream.Stream;

//https://community.topcoder.com/stat?c=problem_statement&pm=1966&rd=4706
public class T9Input {

    private static Map<String, String> codeMap = new HashMap<>();
    static {
        codeMap.put("2", "abc");
        codeMap.put("3", "def");
        codeMap.put("4", "ghi");
        codeMap.put("5", "jkl");
        codeMap.put("6", "mno");
        codeMap.put("7", "pqrs");
        codeMap.put("8", "tuv");
        codeMap.put("9", "wxyz");
    }


    private static Map<String, Set<String>> codeToWordMap = new HashMap<>();
    private static Map<String, String> wordToCodeMap = new HashMap<>();


    public  static String[] getKeypresses(String[] messages) {

        Arrays.stream(messages).map(e -> Arrays.asList(e.split("\\s+")))
                .flatMap(List::stream)
                .forEach(T9Input::mapToCode);
        for (Map.Entry<String, Set<String>> codeToMap : codeToWordMap.entrySet()) {
            String key = codeToMap.getKey();
            for (String str : codeToMap.getValue()){
                wordToCodeMap.put(str, key);
                key = key + "0";
            }
        }

        List<String> retVal = new LinkedList<>();
        StringBuilder stringBuilder = new StringBuilder();
        for (String input : messages) {
            StringTokenizer stringTokenizer = new StringTokenizer(input, " ", true );
            while (stringTokenizer.hasMoreTokens()){
                String token = stringTokenizer.nextToken();
                if (" ".equals(token)){
                    stringBuilder.append("#");
                } else {
                    stringBuilder.append(wordToCodeMap.get(token));
                }
            }
            retVal.add(stringBuilder.toString());
            stringBuilder = new StringBuilder();
        }
        return retVal.toArray(new String[0]);
    }

    private static void mapToCode(String s) {
        StringBuilder stringBuilder = new StringBuilder();
        for ( int i = 0 ; i < s.length() ; i++) {
            String key = s.charAt(i)+"";
            String optional = codeMap
                    .entrySet()
                    .stream()
                    .filter( e -> e.getValue().contains(key))
                    .map(e -> e.getKey()).findAny().get();

            stringBuilder.append(optional);
        }

        if (codeToWordMap.containsKey(stringBuilder.toString())){
            codeToWordMap.get(stringBuilder.toString()).add(s);
        } else {
            Set<String> result = new TreeSet<>();
            result.add(s);
            codeToWordMap.put(stringBuilder.toString(), result);
        }

    }

    public static void main(String[] args) {
        String[] messages = {"rcl b jawko s xn c e io tr snotqgn", ""};
        String[] result = getKeypresses(messages);
        Stream.of(result).forEach(System.out::println);
    }

}
