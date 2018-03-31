package com.geeksforgeeks.graph;

import java.util.LinkedList;
import java.util.Queue;

//http://www.geeksforgeeks.org/snake-ladder-problem-2/
public class SnakeAndLadderProblem {

    class QEntry {
        int dist; // distance
        int v; // vertex number
    }

    public void find (int moves[], int n) {
        Queue<QEntry> queue = new LinkedList<>();
        QEntry qEntry = new QEntry();
        qEntry.dist = 0;
        qEntry.v = 1;
        boolean visited[] = new boolean[n];
        visited[0] = true;
        queue.add(qEntry);
        while (!queue.isEmpty()) {
            qEntry = queue.remove();
            int vertex = qEntry.v;
            if (vertex == n) {
                break;
            }

            for (int i = vertex + 1 ; i < vertex + 6 ; i++) {
                if (!visited[i]) {
                    visited[i] = true;
                    QEntry newQEntry = new QEntry();
                    newQEntry.dist = qEntry.dist + 1;
                    if (moves[i] == -1) {
                        newQEntry.v = i;
                    } else {
                        newQEntry.v = moves[i];
                    }
                    queue.add(newQEntry);
                }
            }

        }


    }
    public static void main(String[] args) {

    }

}
