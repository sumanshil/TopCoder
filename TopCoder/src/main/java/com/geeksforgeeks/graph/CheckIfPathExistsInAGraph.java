package com.geeksforgeeks.graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by sshil on 3/28/17.
 */
//http://www.geeksforgeeks.org/find-if-there-is-a-path-between-two-vertices-in-a-given-graph/
public class CheckIfPathExistsInAGraph {

    public void find (DetectCycleInaGraphUsingColor.GraphVertex source,
                      DetectCycleInaGraphUsingColor.GraphVertex dest,
                      int graphSize) {
        boolean[] visited = new boolean[graphSize];
        Queue<DetectCycleInaGraphUsingColor.GraphVertex> queue = new LinkedList<>();
        queue.add(source);
        while (!queue.isEmpty()) {
            DetectCycleInaGraphUsingColor.GraphVertex current = queue.remove();
            if (current == dest){
                System.out.println("Destination found");
                break;
            }
            visited[current.data] = true;
            for (DetectCycleInaGraphUsingColor.GraphVertex neighbor : current.neighbor) {
                if (!visited[neighbor.data]){
                    queue.add(neighbor);
                }
            }
        }
        System.out.println("There is no path");
    }

    public static void main(String[] args) {
        DetectCycleInaGraphUsingColor.GraphVertex vertex0 = new DetectCycleInaGraphUsingColor.GraphVertex(0);
        DetectCycleInaGraphUsingColor.GraphVertex vertex1 = new DetectCycleInaGraphUsingColor.GraphVertex(1);
        DetectCycleInaGraphUsingColor.GraphVertex vertex2 = new DetectCycleInaGraphUsingColor.GraphVertex(2);
        DetectCycleInaGraphUsingColor.GraphVertex vertex3 = new DetectCycleInaGraphUsingColor.GraphVertex(3);

        vertex0.addNeighbor(vertex1);
        vertex0.addNeighbor(vertex2);
        vertex2.addNeighbor(vertex0);
        vertex1.addNeighbor(vertex2);
        vertex2.addNeighbor(vertex3);
        vertex3.addNeighbor(vertex3);
        CheckIfPathExistsInAGraph checkIfPathExistsInAGraph = new CheckIfPathExistsInAGraph();
        checkIfPathExistsInAGraph.find(vertex3, vertex1, 4);
    }
}
