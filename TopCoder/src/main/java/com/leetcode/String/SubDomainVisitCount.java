package com.leetcode.String;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//https://leetcode.com/problems/subdomain-visit-count/description/
public class SubDomainVisitCount {

    interface UpdateMap<K,V> {
        void update(K key, V val);
    }

    public List<String> subdomainVisits(String[] cpdomains) {
        Map<String, Integer> visitMap = new HashMap<>();
        UpdateMap<String,Integer> consumer = (key, visit) -> {
            if (visitMap.containsKey(key)) {
                visitMap.put(key, visitMap.get(key) + visit);
            } else {
                visitMap.put(key, visit);
            }
        };

        for (String cpDomain : cpdomains) {
            String[] arr = cpDomain.split(" ");
            int visitCount = Integer.parseInt(arr[0]);
            String domain = arr[1];

            int index = domain.indexOf(".", 0);
            //visitMap.put(domain, visitCount);
            consumer.update(domain, visitCount);

            int firstIndex;
            while (index != -1) {
                String secondSegmant = domain.substring(index+1);
                consumer.update(secondSegmant,visitCount);
                firstIndex = index + 1;
                index = domain.indexOf(".", firstIndex);
            }
        }

        return visitMap.entrySet().stream().map(SubDomainVisitCount::mapToString).collect(Collectors.toList());
    }

    private static String mapToString(Map.Entry<String,Integer> stringIntegerEntry) {
        return stringIntegerEntry.getValue()+" "+stringIntegerEntry.getKey();
    }


    public static void main(String[] args) {
        String[] arr= {"900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org"};
        List<String> list = new SubDomainVisitCount().subdomainVisits(arr);
        list.stream().forEach(System.out::println);
    }
}
