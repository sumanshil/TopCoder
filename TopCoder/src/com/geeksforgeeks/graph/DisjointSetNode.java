package com.geeksforgeeks.graph;

import org.junit.Assert;

/**
 * Created by sshil on 5/10/2016.
 */
public class DisjointSetNode<T extends Comparable<T>> {
    private T data;
    private int rank;
    private DisjointSetNode parent;

    public DisjointSet<T> getSet() {
        return set;
    }

    public void setSet(DisjointSet<T> set) {
        this.set = set;
    }

    private DisjointSet<T> set;

    public DisjointSetNode(T data){
        this.data = data;
        setParent(this);
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public DisjointSetNode getParent() {
        return parent;
    }

    public void setParent(DisjointSetNode parent) {
        this.parent = parent;
    }

    public void compress(){
        if (this.parent != null
            && !isParentPointToCurrentNode()
            && !isParentPointToSameNode()){
            DisjointSetNode topMostNode = getTopMostNode();
            this.parent = topMostNode;
        }
    }

    private DisjointSetNode getTopMostNode() {
        DisjointSetNode currentParent = this.parent;
        while(true){
            if (currentParent != null
                && currentParent.parent != null
                && currentParent.parent == currentParent){
                return currentParent;
            } else if (currentParent.parent == null){
                break;
            }
            currentParent = currentParent.parent;
        }
        return currentParent;
    }

    private boolean isParentPointToSameNode() {
        Assert.assertTrue(this.parent != null);
        return this.parent.parent.equals(this.parent);

    }

    private boolean isParentPointToCurrentNode() {
        return this.parent.equals(this);
    }

}
