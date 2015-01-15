package com.topcoder.problems.wed091013;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

//Single Round Match 8 > Round 1 > Room 3 > Sord
//http://community.topcoder.com/stat?c=problem_statement&pm=87&rd=3007
public class AlienTakeover {

    static class Task{
        String name;
        int time;
        int end;
        boolean completed = false;
        String[] tasks ;
        public Task(String name, int time, String[] tasks){
            this.name = name;
            this.time = time;
            this.tasks = tasks;
        }
        
        public int hashCode(){
            return name.hashCode();
        }
        
        public boolean equals(Object o){
            if (((Task)o).name.equals(name)){
                return true;
            }
            
            return false;
        }
        public String toString(){
            StringBuffer sb = new StringBuffer();
            sb.append("Name "+name+"\n");
            sb.append("time "+time+"\n");
            sb.append("end  "+end+"\n");
            sb.append("Dependencies \n");
            if (tasks != null){
                for(String task : tasks){
                    sb.append(task+"\n");
                }
            }
            return sb.toString();
        }
    }

    public int takeOverTime(String[] param){
        List<Task> tasks  = new ArrayList<Task>();
        Map<String, Task> map = new HashMap<String, Task>();
        for(String str : param){
            String[] strList = str.split(" ");
            String name = strList[0];
            
            int time = Integer.parseInt(strList[1]);
            String[] list = null;
            if (strList.length == 3){
                String dependencies = strList[2];
                if (dependencies != null){
                    list = dependencies.split(",");
                }
            }
            Task task = new Task(name, time, list);
            tasks.add(task);
            map.put(name, task);
        }
        
        int count = tasks.size();
        Set<String> completedSet = new HashSet<String>();
        int totalTime = 0;
        int prevCount = count;
        while(count>0){
            int maxLength = Integer.MIN_VALUE;
            Set<String> foundTasks = new HashSet<String>();
            for(Task task : tasks){
                if (task.completed)
                    continue;
                String[] dependencies = task.tasks;
                if (dependencies == null){
                    if (task.time > maxLength){
                        maxLength = task.time;
                        totalTime = maxLength;
                    } 
                    task.completed = true;
                    task.end =task.time;
                    foundTasks.add(task.name);
                    System.out.println("Selected task "+task);
                    count--;
                } else {
                    boolean execute = true;
                    for(String dependency : dependencies){
                        if (!completedSet.contains(dependency)){
                            execute = false;
                        }
                    }
                    if (execute){
                        for(String dependency : dependencies) {
                            if (maxLength < map.get(dependency).end){
                                maxLength = map.get(dependency).end;
                            }                            
                        }                        
                        if (task.time+ maxLength > totalTime){
                            totalTime = (task.time+ maxLength);
                        }
                        task.end = task.time+ maxLength;
                        task.completed = true;
                        foundTasks.add(task.name);
                        System.out.println("Selected task "+task);
                        count--;
                    }
                }
            }
            System.out.println("Completed tasks"+foundTasks);
            System.out.println("Total time"+totalTime);
            if (prevCount == count){
                return -1;
            } else {
                prevCount = count;
            }
            completedSet.addAll(foundTasks);
        }
        return totalTime;
    }
    public static void main(String[] args){
//        String[] tasks = {"KILLPEOPLE 50",
//                "BUILDHOUSES 10 LAND",
//                "LAND 2 KILLPEOPLE",
//                "CLEANUPBODIES 5 KILLPEOPLE,LAND",
//                "PLANTNEWLAWN 10 CLEANUPBODIES,LAND"};

        //String[] tasks= {"A 3", "B 1 A,C", "C 2 A", "D 10 A,B", "E 2 D", "F 12"};
        //String[] tasks = {"A 1 C", "B 2 C", "C 2 D,E", "D 1", "E 2 A", "F 12 A"};
        //String[] tasks = {"A 2 E,F", "B 3", "C 6 B,G", "D 1 A", "E 9 G,H", "F 2 C", "G 8", "H 7"};
        //String[] tasks =    {"TESTER 4 ANOTHER", "ANOTHER 2 TA,TB,TC", "TA 1", "TB 1", "TC 1", "YOR 8 ANOTHER", "AL 1 TESTER"};
        String[] tasks = {"A 50 B", "B 50 C", "C 50 D", "D 50"};
        int result = new AlienTakeover().takeOverTime(tasks);
        System.out.println(result);
    }
}
