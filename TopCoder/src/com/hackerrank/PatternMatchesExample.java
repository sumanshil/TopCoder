package com.hackerrank;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by sshil on 4/9/2016.
 */
public class PatternMatchesExample {
    public static void main(String[] args) {

//        String text    = "6208579823432423423";
//          //  "This is the text to be searched " +
//          //      "for occurrences of the pattern.";
//
//        String pattern = "^.{1,13}$";
//        //String pattern = "^.{1,30}$";
//        //String pattern = "0|(^\\d{1,8}\\.\\d{2}$)";
//        boolean matches = Pattern.matches(pattern, text);
//        Pattern pat = Pattern.compile("\\b\\w+@XYZ\\.com\\b");
//
//        Matcher mat = pat.matcher("t@XYZ.com\n" + "a@XYZ.com\n"
//                                      + "n@XYZ.com");

        Pattern pat = Pattern.compile("\\b\\w+\\$\\{\\w+abc\\.\\w+}\\b");

        Matcher mat = pat.matcher("$abc.er");

        while (mat.find())
            System.out.println("Match: " + mat.group());
    }
}
