package com.geeksforgeeks.graph;

/**
 * Created by sshil on 5/5/2016.
 */
public class Edge<T extends Object> {
    private T source;
    private T destination;

    public Edge(T source, T destination){
        this.source = source;
        this.destination = destination;
    }

    public T getSource() {
        return source;
    }

    public void setSource(T source) {
        this.source = source;
    }

    public T getDestination() {
        return destination;
    }

    public void setDestination(T destination) {
        this.destination = destination;
    }

    public String toString(){
        return "Source ===> "+this.source.toString()+" ====> " +this.destination.toString();
    }
}
