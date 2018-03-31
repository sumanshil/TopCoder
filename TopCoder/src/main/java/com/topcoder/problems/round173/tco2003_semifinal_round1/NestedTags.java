package com.topcoder.problems.round173.tco2003_semifinal_round1;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class NestedTags {

    public void find (String input, String[] queries) throws IOException {
        Map<String, String> map = new HashMap<>();
        Stack<String> stack = new Stack<>();
        BufferedReader bufferedReader
                = new BufferedReader(
                        new InputStreamReader(
                                new ByteArrayInputStream(input.getBytes())));
        String str ;
        while((str = bufferedReader.readLine()) != null){
            if (str.trim().startsWith("</")){
                String content = str.trim().substring(2, str.trim().length()-1);
                if (stack.size() > 0 && stack.peek().equals(content)) {
                    stack.pop();
                } else {
                    throw new RuntimeException("Invalid input");
                }
            } else {
                String content = str.trim().substring(1, str.trim().length()-1);
                String[] arr = content.split("\\s+");
                String tagName = arr[0];
                stack.push(tagName);
                for ( int i = 1 ; i < arr.length ; i++) {
                    String st = arr[1];
                    String[] arr1 = st.split("=");
                    prepareReplyMap(map, stack, arr1[0], arr1[1]);
                }
            }
        }
        Predicate<String> predicate = (s) -> map.containsKey(s);

        List<String> list = Arrays.stream(queries)
              .filter(predicate).map(s -> s +"->"+map.get(s))
              .collect(Collectors.toList());
        list.stream().forEach(System.out::println);
    }

    private void prepareReplyMap(Map<String, String> map, Stack<String> stack, String attributeName,
                                 String attributeValue) {
        String key = stack.stream().collect(Collectors.joining("."));
        key = key+"~"+attributeName;
        map.put(key, attributeValue);
    }

    public static void main(String[] args) throws IOException {
        String input = "<tag1 name=suman>\n"+
                       "    <tag2 name=shil>\n" +
                       "        <tag3 name=Deepz>\n"+
                       "        </tag3>\n"+
                       "    </tag2>\n"+
                       "</tag1>\n";
        new NestedTags().find(input,new String[] {"tag1~name", "tag1.tag2~name", "tag1.tag2.tag3~name"});

    }
}
