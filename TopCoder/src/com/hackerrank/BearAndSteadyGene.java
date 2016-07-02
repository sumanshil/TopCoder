package com.hackerrank;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sshil on 6/7/2016.
 */
//https://www.hackerrank.com/challenges/bear-and-steady-gene
public class BearAndSteadyGene {
    static class State {
        private Map<String, Integer> totalCountMap = new HashMap<>();
        private Map<String, Integer> expectedCountToBeReduced = new HashMap<>();
        private Map<String, Integer> currentCountToBeReduced = new HashMap<>();
        private int totalCount = 0;
        private boolean searchModeOn = false;
        public State(){

        }

        public void characterFound(char c){
            String key = c +"";
            if (expectedCountToBeReduced.containsKey(key)){
                if (searchModeOn){

                }
            }
        }
    }
}
