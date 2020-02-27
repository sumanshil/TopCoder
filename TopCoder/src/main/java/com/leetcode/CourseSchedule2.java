package com.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

import org.omg.CORBA.FREE_MEM;

public class CourseSchedule2 {

    Map<Integer, List<Integer>> graph = new HashMap<>();
    boolean[] visited, recStack;

    /**
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        graph = new HashMap<>();
        recStack = new boolean[numCourses];
        visited = new boolean[numCourses];
        int[] retVal = new int[numCourses];

        for (int i = 0 ; i < numCourses ; i++) {
            graph.put(i, new ArrayList<>());
        }

        for (int[] edge : prerequisites) {
            graph.get(edge[1]).add(edge[0]);
        }
        Stack<Integer> stack = new Stack<>();

        for (int i = 0 ; i < numCourses ; i++) {
            if (hasCycle(i, stack)) {
                return new int[]{};
            }
        }
        int i = 0;
        while (!stack.isEmpty()) {
            retVal[i++] = stack.pop();
        }
        return retVal;
    }

    private boolean hasCycle(int i, Stack<Integer> stack) {
        if (recStack[i]) {
            return true;
        }
        if (visited[i]) {
            return false;
        }

        recStack[i] = true;
        visited[i] = true;
        for (Integer index: graph.get(i)) {
            if (hasCycle(index, stack)) {
                return true;
            }
        }
        recStack[i] = false;
        stack.push(i);
        return false;
    }
    */
    /*
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Queue<Integer> queue = new LinkedList<>();
        int[] inorder = new int[numCourses];
        Map<Integer, List<Integer>> map = new HashMap<>();
        for ( int i = 0 ; i < numCourses ; i++) {
            map.put(i, new ArrayList<>());
        }
        for (int[] arr : prerequisites) {
            map.get(arr[1]).add(arr[0]);
            inorder[arr[0]]++;
        }

        int[] res = new int[numCourses];
        int index = 0;

        for (int i = 0 ; i < numCourses ; i++) {
            if (inorder[i] == 0) {
                res[index++] = i;
                queue.offer(i);
            }
        }

        while(!queue.isEmpty()) {
            int i = queue.remove();

            for (int q : map.get(i)) {
                inorder[q]--;
                if (inorder[q] == 0) {
                    res[index++] = q;
                    queue.offer(q);
                }
            }
        }
        if (index < numCourses) {
            return new int[]{};
        }
        return res;
    }
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        int[] inorder = new int[numCourses];

        for (int i = 0 ; i < numCourses ; i++) {
            graph.put(i, new LinkedList<>());
        }

        for (int[] arr : prerequisites) {
            graph.get(arr[1]).add(arr[0]);
            inorder[arr[0]]++;
        }

        int index = 0;
        int[] retVal = new int[numCourses];
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0 ; i < numCourses; i++) {
            if (inorder[i] == 0) {
                retVal[index++] = i;
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            int i = queue.remove();

            for (int g : graph.get(i)) {
                if (--inorder[g] == 0) {
                    retVal[index++] = g;
                    queue.offer(g);
                }
            }
        }
        if (index < numCourses) {
            return new int[]{};
        } else {
            return retVal;
        }
    }

    public static void main(String[] args) {
        int numOfCourses =2;
        int[][] arr = {{1, 0}, {0, 1}}; // complete 1 before completing 0
        int[] result = new CourseSchedule2().findOrder(numOfCourses, arr);
        for (int i = 0 ; i < result.length ; i++) {
            System.out.println(result[i]);
        }
    }
}
