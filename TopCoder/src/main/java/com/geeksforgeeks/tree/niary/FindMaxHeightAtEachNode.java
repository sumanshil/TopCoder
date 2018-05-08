package com.geeksforgeeks.tree.niary;

import java.util.LinkedList;
import java.util.List;

//https://www.geeksforgeeks.org/dynamic-programming-trees-set-2/
public class FindMaxHeightAtEachNode {

    int in[] = new int[12];
    int out[] = new int[12];

    public void process(List<List<Integer>> graph) {

        calculateInDepth(1, graph);
        calculateOutDepth(1, 1, graph);
        for (int i = 1 ; i <= 11 ; i++) {
            int max = Math.max(in[i], out[i]);
            System.out.println("Index "+i +" : "+max);
        }
    }

    private void calculateOutDepth(int index, int parent, List<List<Integer>> graph) {
        int max = 0;
        int secondMaxIn = 0;
        List<Integer> children = graph.get(index);
        for (Integer child : children) {
            if (in[child] > max) {
                secondMaxIn = max;
                max = in[child];
            } else if (in[child] > secondMaxIn) {
                secondMaxIn = in[child];
            }
        }

        for (Integer child : children) {
            if (child == parent)
                continue;

            if (max == in[child]) {
                out[child] = 1+ Math.max(out[index], 1 + secondMaxIn);
            } else {
                out[child] = 1+ Math.max(out[index], 1 + max);
            }
            calculateOutDepth(child, parent, graph);
        }
    }

    private int calculateInDepth(int index, List<List<Integer>> graph) {
        List<Integer> children = graph.get(index);
        if (children == null || children.size() == 0) {
            return 0;
        }

        int max = 0;
        for (Integer childIndex : children) {
            int inCurrent = calculateInDepth(childIndex, graph);
            max = Math.max(max, inCurrent);
        }
        in[index] =  max +1;
        return in[index];
    }

    public static void main(String[] args) {
        List<List<Integer>> graph = new LinkedList<>();
        for (int i = 0 ; i <=11 ; i++) {
            graph.add(new LinkedList<>());
        }
        graph.get(1).add(2);
        graph.get(1).add(3);
        graph.get(1).add(4);

        graph.get(2).add(5);
        graph.get(2).add(6);

        graph.get(3).add(7);
        graph.get(7).add(10);
        graph.get(7).add(11);

        graph.get(4).add(8);
        graph.get(4).add(9);

        new FindMaxHeightAtEachNode().process(graph);
        System.out.println(graph.get(0));

    }
}
