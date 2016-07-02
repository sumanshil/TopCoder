package com.topcoder.problems.crpf_round1;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by sshil on 6/10/2016.
 */
//https://community.topcoder.com/stat?c=problem_statement&pm=1935&rd=4656
public class RoomSummary {

    static class FinalResult{
        DecimalFormat decimalFormat = new DecimalFormat("#.##");

        private String name;
        private Double result ;
        private String strResult;

        public FinalResult(String name, Double result) {
            this.name = name;
            this.result = result;
            strResult = decimalFormat.format(result);
            if (strResult.indexOf('.') == -1) {
                strResult = strResult + ".00";
            } else {
                String decimal = strResult.substring(strResult.indexOf('.')+1);
                if (decimal.length() < 2){
                    decimal = decimal+"0";
                }
                String wholeString = strResult.substring(0, strResult.indexOf('.')+1)+decimal;
                strResult = wholeString;
            }
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Double getResult() {
            return result;
        }

        public void setResult(Double result) {
            this.result = result;
        }

        public String toString(){
            return this.getName()+" "+this.strResult;
        }
    }
    static class ResultHandler {
        private Map<String, Map<String, Double>> resultMap = new HashMap<>();
        private Map<String, List<Double>> challengeMap = new HashMap<>();

        public void handleSubmission(String submission){
            String[] arr = submission.split("\\s+");
            String challengerName = arr[0];
            String problemName = arr[1];
            String point = arr[2];
            Map<String, Double> result = resultMap.get(challengerName);
            if (result == null){
                result = new HashMap<>();
                result.put(problemName, Double.valueOf(point));
                resultMap.put(challengerName, result);
            } else {
                result.put(problemName, Double.valueOf(point));
            }

        }

        public void handleChallenge(String submission){
            String[] arr = submission.split("\\s+");
            String challengersName = arr[0];
            String challenged = arr[1];
            String problemName = arr[2];
            String result = arr[3];
            if ("unsuccessful".equals(result)){
                // challenger loses 50 points
                List<Double> list = challengeMap.get(challengersName);
                if (list == null){
                    list = new ArrayList<>();
                    challengeMap.put(challengersName,list);
                }
                list.add(-50.00);
            } else {
                List<Double> list = challengeMap.get(challengersName);
                if (list == null){
                    list = new ArrayList<>();
                    challengeMap.put(challengersName,list);
                }
                list.add(50.00);
                Map<String, Double> map = resultMap.get(challenged);
                map.remove(problemName);
            }
        }

        public void handleSystemTest(String result){
            String[] arr = result.split("\\s+");
            String name = arr[0];
            String challenge = arr[1];
            Map<String, Double> map = resultMap.get(name);
            map.remove(challenge);
        }

        public List<FinalResult> getResult(String[] handles){
            List<FinalResult> retVal = new ArrayList<>();
            for (String handle : handles) {
                Double result = 0.0;
                Map<String, Double> map = resultMap.get(handle);
                if (map != null) {
                    for (Map.Entry<String, Double> entry : map.entrySet()) {
                        result += entry.getValue();
                    }
                }
                List<Double> list = challengeMap.get(handle);
                if (list != null) {
                    for (Double d : list) {
                        result += d;
                    }
                }
                FinalResult finalResult = new FinalResult(handle, result);
                retVal.add(finalResult);
            }
            Collections.sort(retVal, Comparator.comparing(FinalResult::getResult).reversed().thenComparing(FinalResult::getName));
            return retVal;
        }
    }


    public String[] generate(String[] problems,
                             String[] handles,
                             String[] submissions,
                             String[] challenges,
                             String[] failed){
        ResultHandler resultHandler = new ResultHandler();
        Arrays.asList(submissions).stream().forEach(resultHandler::handleSubmission);
        Arrays.asList(challenges).stream().forEach(resultHandler::handleChallenge);
        Arrays.asList(failed).stream().forEach(resultHandler::handleSystemTest);
        List<String> result = new LinkedList<>();
        List<FinalResult> list = resultHandler.getResult(handles);
        list.stream().forEach(e->result.add(e.toString()));
        return result.toArray(new String[0]);
    }

    public static void main(String[] args) {
        String[] problems = {"Cher", "Ambrose", "Marc"};
        String[] handlers = {"Ripley", "Edvard"};
        String[] submissions = {
            "Ripley Cher 89.18", "Ripley Cher 69.71", "Edvard Marc 324.55", "Edvard Ambrose 79.79", "Ripley Cher 68.33", "Edvard Marc 322.80"
        };
        String[] challenges = { "Ripley Edvard Marc unsuccessful"};
        String[] failed = {
            "Edvard Ambrose", "Ripley Cher"
        };
        String[] result = new RoomSummary().generate(problems,handlers,submissions,challenges,failed);
        Arrays.asList(result).stream().forEach(System.out::println);
    }
}
