package com.geeksforgeeks.graph;

/**
 * Created by sshil on 5/20/2016.
 */
//https://www.youtube.com/watch?v=LwJdNfdLF9s&index=7&list=PLrmLmBdmIlpu2f2g8ltqaaCZiq6GJvl1j
public class FloydWarshallAlgorithm {
    static int[][] distanceMatrix = null;
    static int[][] pathMatrix = null;

    public static void main(String[] args) {
        Vertex<Integer> vertex0 = new Vertex<>(0);
        Vertex<Integer> vertex1 = new Vertex<>(1);
        Vertex<Integer> vertex2 = new Vertex<>(2);
        Vertex<Integer> vertex3 = new Vertex<>(3);
        vertex0.addNeighborDirected(vertex1,3);
        vertex0.addNeighborDirected(vertex3,15);
        vertex1.addNeighborDirected(vertex2, -2);
        vertex2.addNeighborDirected(vertex3, 2);
        vertex3.addNeighborDirected(vertex0, 1);
        vertex0.addNeighborDirected(vertex2, 6);
        Graph<Integer> graph = new Graph<>();
        graph.addVertex(vertex0);
        graph.addVertex(vertex1);
        graph.addVertex(vertex2);
        graph.addVertex(vertex3);

        new FloydWarshallAlgorithm().execute(graph);
    }

    private static void populateMatrix(Vertex<Integer> vertex){
        int source = vertex.getData();
        distanceMatrix[source][source] = 0;
        vertex.getNeighbors().stream().forEach(e -> {
            int destination = e.getData();
            distanceMatrix[source][destination] = vertex.getNeighhborDistance(e);
            pathMatrix[source][destination] = source;
        });
    }
    private void execute(Graph<Integer> graph) {
        distanceMatrix = new int[graph.size()][graph.size()];
        for (int i = 0 ; i < distanceMatrix.length; i++){
            for (int j = 0 ; j < distanceMatrix[0].length ; j++) {
                distanceMatrix[i][j] = Integer.MAX_VALUE;
            }
        }
        pathMatrix = new int[graph.size()][graph.size()];
        for (int i = 0 ; i < pathMatrix.length ; i++){
            for (int j = 0 ; j < pathMatrix.length ; j++){
                pathMatrix[i][j] = -1;
            }
        }
        printDistanceMatrix();
        printPathMatrix();
        graph.getNodes().stream().forEach(FloydWarshallAlgorithm::populateMatrix);
        printDistanceMatrix();
        printPathMatrix();
        for (Vertex<Integer> mid : graph.getNodes()) {
            for (Vertex<Integer> source : graph.getNodes()) {
                for (Vertex<Integer> destination : graph.getNodes()) {
                    System.out.println("Consider route via "+mid.getData()+ " source "+source+" destination "+destination);
                    if (hasRoute(source, mid) && hasRoute(mid, destination)) {
                        int distance = distanceMatrix[source.getData()][mid.getData()] + distanceMatrix[mid.getData()][destination.getData()];
                        if ( distance
                            < distanceMatrix[source.getData()][destination.getData()]){
                            System.out.println("Found a route via "+mid);
                            distanceMatrix[source.getData()][destination.getData()] = distance;
                            pathMatrix[source.getData()][destination.getData()] = pathMatrix[mid.getData()][destination.getData()];
                            printDistanceMatrix();
                            printPathMatrix();
                        }
                    } else {
                        System.out.println("Could not inOrder a route");
                    }
                }
            }
        }
    }

    private void printPathMatrix() {
        for ( int i = 0 ; i < pathMatrix.length ; i++){
            for ( int j=0 ; j < pathMatrix[0].length ; j++){
                System.out.print(pathMatrix[i][j]+" ");
            }
            System.out.println();
        }

    }

    private void printDistanceMatrix() {
        for ( int i = 0 ; i < distanceMatrix.length ; i++){
            for ( int j=0 ; j < distanceMatrix[0].length ; j++){
                System.out.print(distanceMatrix[i][j]+" ");
            }
            System.out.println();
        }

    }

    private boolean hasRoute(Vertex<Integer> source, Vertex<Integer> mid) {
        return distanceMatrix[source.getData()][mid.getData()] != Integer.MAX_VALUE;
    }
}
