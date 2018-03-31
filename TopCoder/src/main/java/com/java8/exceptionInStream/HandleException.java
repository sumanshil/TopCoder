package com.java8.exceptionInStream;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by sshil on 9/20/16.
 */
//https://dzone.com/articles/exceptional-exception-handling-in-jdk-8-streams?edition=213322&utm_source=Daily%20Digest&utm_medium=email&utm_campaign=dd%202016-09-20
public class HandleException {


    public Map<String, List<String>> parseQuery(String query) {
        if (query == null){
            return new HashMap();
        }
//        return query == null ? null : Arrays.stream(query.split("[&]"))
//                .collect(Collectors.groupingBy( key -> splitKeys(key, 0),
//                        Collectors.mapping(key -> splitKeys(key, 1), Collectors.toList())));
        return  Arrays.stream(query.split("[&]")).map(this::splitKeyValue)
                .collect(Collectors.groupingBy(Map.Entry::getKey,
                        Collectors.mapping(Map.Entry::getValue, Collectors.toList())));

    }

    private String splitKeys(String key, int part) {
        try {
            return URLDecoder.decode(key.split("[=]")[part], System.getProperty("file.encoding"));
        } catch (UnsupportedEncodingException uee){
            return null;
        }

    }


    private AbstractMap.SimpleImmutableEntry<String, String> splitKeyValue(String key){
        String enc = System.getProperty("file.encoding");
        String[] parts = key.split("[&]");
        try {
            return new AbstractMap.SimpleImmutableEntry<>(
                    URLDecoder.decode(parts[0], enc),
                    URLDecoder.decode(parts[1], enc));
        }catch (UnsupportedEncodingException uee) {
            return null;
        }

    }
    public static void main(String[] args) {
        String str = "action=verify&key=10101&key=22222&key=10101";
        Map<String, List<String>> map = new HandleException().parseQuery(str);
        System.out.println(map);
    }
}
