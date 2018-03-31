package com.topcoder.problems.crpf_charity_challenge_1;


import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by sshil on 10/26/16.
 */
//https://community.topcoder.com/stat?c=problem_statement&pm=1934&rd=4656
public class AustrianLotto {

    public 	int[] evaluate(String drawing, String[] picks) {
        Set<String> drawingSet = Arrays.stream(drawing.split("\\s+")).
            collect(Collectors.toSet());

        int[] result = new int[7];
        Arrays.stream(picks).
                map(e -> getMatchingResult(e, drawingSet)).
                forEach(i -> result[i]= result[i]+1);
        return result;
    }

    private int getMatchingResult(String e, Set<String> drawingSet) {
        List<String> list = Arrays.stream(e.split("\\s+")).
                             filter(drawingSet::contains).
                             collect(Collectors.toList());
        return list.size();
    }

    public static void main(String[] args) {
        String drawing = "42 26 33 2 13 14";
        String[] picks = {"13 4 36 42 26 12"};
        int[] result = new AustrianLotto().evaluate(drawing, picks);
        Arrays.stream(result).forEach(System.out::println);
    }
}
