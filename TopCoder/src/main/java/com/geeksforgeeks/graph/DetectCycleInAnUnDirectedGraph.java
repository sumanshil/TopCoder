package com.geeksforgeeks.graph;

//http://www.geeksforgeeks.org/union-find/
public class DetectCycleInAnUnDirectedGraph {

    static class Graph {
        int V, E; // V -> no. of vertices, E -> no. of edges
        Edge edges[];

        public boolean isCycle() {
            int parent[] = new int[E];
            for ( int i = 0 ; i < E ; i++) {
                parent[i] = -1;
            }

            for ( int i = 0 ; i < E ; i++) {
                int src = this.edges[i].src;
                int dest = this.edges[i].dest;

                if (find(parent, src) == find(parent, dest)){
                    return true;
                }
                this.union(parent, src, dest);
            }
            return false;
        }

        static class Edge {
            int src, dest;
        }

        Graph(int v, int e) {
            V = v;
            E = e;
            for (int i = 0 ; i < e ; i++) {
                edges[i] = new Edge();
            }
        }

        public int find (int parent[], int x) {
            if (parent[x] == -1) {
                return x;
            }
            return find(parent, parent[x]);
        }

        public void union(int parent[], int x, int y) {
            int xSet = find(parent, x);
            int ySet = find(parent, y);
            parent[xSet] = ySet;
        }
    }


    public static void main(String[] args) {
        int G = 3, E = 3;
        Graph graph = new Graph(G, E);

        graph.edges[0].src = 0;
        graph.edges[0].dest = 1;

        graph.edges[1].src = 1;
        graph.edges[1].dest = 2;

        graph.edges[2].src = 0;
        graph.edges[2].dest = 2;

        graph.isCycle();

    }
}
