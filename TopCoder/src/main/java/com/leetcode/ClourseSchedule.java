package com.leetcode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class ClourseSchedule {

    static class CourseNode {
        int index;
        boolean visited;

        public CourseNode(int i) {
            this.index = i;
        }
    }

    Map<Integer, CourseNode> map = new HashMap<>();
    Map<Integer, List<CourseNode>> dependencyMap = new HashMap<>();

    public boolean canFinish(int numCourses, int[][] prerequisites) {

        for (int i = 0 ; i < numCourses ; i++) {
            map.put(i, new CourseNode(i));
        }
        for (int i= 0 ; i < prerequisites.length ; i ++) {
            int course1 = prerequisites[i][0];
            int course2 = prerequisites[i][1];

            if (dependencyMap.containsKey(course1)) {
                dependencyMap.get(course1).add(map.get(course2));
            } else {
                List<CourseNode> list = new LinkedList<>();
                list.add(map.get(course2));
                dependencyMap.put(course1, list);
            }
        }

        for (int i= 0 ; i < numCourses ; i++) {
            boolean[] visited = new boolean[numCourses];
            boolean retVal = dfs(i, visited);
            if (!retVal) {
                return false;
            }
        }
        return true;
    }

    private boolean dfs(int i, boolean[] visited) {
        if (!dependencyMap.containsKey(i)) {
            return true;
        }
        if (visited[i]) {
            return false;
        }

        visited[i] = true;

        for (CourseNode courseNode : dependencyMap.get(i)) {
            if (!dfs(courseNode.index, visited)) {
                return false;
            }
        }
        visited[i] =false;
        return true;
    }

    public static void main(String[] args) {
        int numberOfCourses = 2;
        int[][] matrix = {
                {1, 0},
                {0, 1}
        };
        boolean result = new ClourseSchedule().canFinish(2, matrix);
        System.out.println(result);
    }
}
