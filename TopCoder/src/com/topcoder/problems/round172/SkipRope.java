package com.topcoder.problems.round172;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sshil on 7/2/17.
 */
//https://community.topcoder.com/stat?c=problem_statement&pm=1968&rd=4665
public class SkipRope {

    public int[] partners(int[] candidates, int height) {
        int[] selectedHeights = new int[2];
        int[] diff = new int[2];

        selectedHeights[0] = candidates[0];
        selectedHeights[1] = candidates[1];

        diff[0] = Math.abs(candidates[0] - height);
        diff[1] = Math.abs(candidates[1] - height);

        for ( int i = 2 ; i < candidates.length ; i++) {
            int newDiff = Math.abs(candidates[i] - height);
            if (newDiff < diff[0]) {
                diff[0] = newDiff;
                selectedHeights[0] = candidates[i];
            } else if (newDiff < diff[1]){
                diff[1] = newDiff;
                selectedHeights[1] = candidates[i];
            } else if (newDiff == diff[0] && candidates[i] > selectedHeights[0]){
                diff[0] = newDiff;
                selectedHeights[0] = candidates[i];
            } else if (newDiff == diff[1] && candidates[i] > selectedHeights[1]){
                diff[1] = newDiff;
                selectedHeights[1] = candidates[i];
            }
        }
        return selectedHeights;
    }

    public static void main(String[] args) {
//        int[] candidates = {134, 79, 164, 86, 131, 78, 99, 150, 105, 163, 150, 110, 90, 137, 127, 130, 121,
//                93, 97, 131, 170, 137, 171, 153, 137, 138, 92, 103, 149, 110, 156};
//        int height = 82;
//        int[] result = new SkipRope().partners(candidates, height);
//
//        for ( int i : result){
//            System.out.println(i);
//        }

        List<String> list = new ArrayList<>();
        list.add("apple");
        list.add("orange");
        list.add("banana");
        for ( int i = 0 ; i < list.size() ; i++){
            String str = list.get(i);
            if (str.equals("banana")){
                list.remove(str);
            }
        }

        list.stream().forEach(System.out::println);
    }
}
