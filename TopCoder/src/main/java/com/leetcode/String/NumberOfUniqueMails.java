package com.leetcode.String;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class NumberOfUniqueMails {
    public int numUniqueEmails(String[] emails) {
        Set<String> set = Arrays.stream(emails).map(NumberOfUniqueMails::processEmail).collect(Collectors.toSet());
        return set.size();
    }

    private static String processEmail(String s) {
        String[] arr = s.split("@");
        String finalEmail = arr[0];
        if (arr[0].contains("+")) {
            int index = arr[0].indexOf('+');
            finalEmail = arr[0].substring(0, index);
        }
        finalEmail = finalEmail.replaceAll("\\.", "");
        return finalEmail+"@"+arr[1];
    }

    public static void main(String[] args) {
        String[] str = {"test.email+alex@leetcode.com","test.e.mail+bob.cathy@leetcode.com","testemail+david@lee.tcode.com"};
        int result = new NumberOfUniqueMails().numUniqueEmails(str);
        System.out.println(result);
    }

}
