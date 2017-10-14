package com.geeksforgeeks.graph;

//http://www.geeksforgeeks.org/union-find-algorithm-set-2-union-by-rank/
public class GraphUnionPathCompressionAndRaking {

    static class Graph {
        private int v;
        private int e;
        private Edge[] edges;
        private int[] ranking;

        Graph(int v, int e){
            this.v = v;
            this.e = e;
            edges = new Edge[e];
            ranking = new int[v];
            for ( int i = 0 ; i < e ; i++) {
                edges[i] = new Edge(0, 0);
            }
        }

        public void addEdge(int edge, int source, int destination) {
            edges[edge].src = source;
            edges[edge].destination = destination;
        }

        public Edge[] getEdges() {
            return edges;
        }

        public boolean isCyclePresent(){
            int[] parent = new int[this.e];
            for ( int i = 0 ; i < parent.length ; i++) {
                parent[i] = i;
            }
            for ( int i = 0 ; i < edges.length ; i++) {
                Edge edge = edges[i];
                int src = edge.src;
                int dest = edge.destination;
                int srcParent = this.find(parent, src);
                int destParent = this.find(parent, dest);
                if (srcParent == destParent ){
                    return  true;
                }
                this.union(parent, srcParent, destParent);
            }
            return false;
        }

        private int find(int[] parent, int src) {
            if (parent[src] == src) {
                return src;
            }
            return find(parent, parent[src]);
        }

        private void union(int[] parent, int srcParent, int destParent) {
            if (ranking[srcParent] > ranking[destParent]){
                parent[destParent] = srcParent;
            } else if (ranking[srcParent] < ranking[destParent]) {
                parent[srcParent] = destParent;
            } else {
                parent[destParent] = srcParent;
                ranking[srcParent]++;
            }
        }

        class Edge {
            int src;
            int destination;

            Edge(int src, int dest) {
                this.src = src;
                this.destination = dest;
            }

            public int getSrc() {
                return src;
            }

            public void setSrc(int src) {
                this.src = src;
            }

            public int getDestination() {
                return destination;
            }

            public void setDestination(int destination) {
                this.destination = destination;
            }
        }

    }

    public static void main(String[] args) {
        Graph graph = new Graph(3, 3);
        graph.addEdge(0, 0, 1);
        graph.addEdge(1,1,2);
        graph.addEdge(2, 0, 2);

        boolean result = graph.isCyclePresent();
        System.out.println(result);
    }

}
