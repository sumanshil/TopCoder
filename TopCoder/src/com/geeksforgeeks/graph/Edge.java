package com.geeksforgeeks.graph;

/**
 * Created by sshil on 5/5/2016.
 */
public class Edge<T extends Object> {
    private T source;
    private T destination;
    private int weight;

    public Edge(T source, T destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Edge(T source, T destination){
        this(source, destination, 0);
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
        return "Source ===> "
            +this.source.toString()+" ====> "
            +this.destination.toString()+"  weight "+this.getWeight();
    }

    @Override
    public boolean equals(Object o){
        return o != null
            && ((Edge)o).getSource().equals(this.getSource())
            && ((Edge)o).getDestination().equals(this.getDestination());
    }

    @Override
    public int hashCode(){
        return this.getSource().hashCode()*17 + this.getDestination().hashCode();
    }
}
