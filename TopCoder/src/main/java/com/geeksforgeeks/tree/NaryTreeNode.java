package com.geeksforgeeks.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;
import java.util.function.Predicate;

/**
 * Created by sshil on 6/16/2016.
 */
public class NaryTreeNode {
    private int data;
    private List<NaryTreeNode> children;

    public NaryTreeNode(int data){
        this.data = data;
        children = new ArrayList<>();
    }

    public void setChildren(List<NaryTreeNode> list) {
        this.children = list;
    }

    public void addChild(NaryTreeNode child){
        this.children.add(child);
    }

    public void bfs(){
        Queue<NaryTreeNode> queue = new LinkedList<>();
        queue.add(this);
        while (!queue.isEmpty()){
            NaryTreeNode node = queue.remove();
            System.out.print(node.data);
            node.children.stream().forEach(queue::add);
        }


    }

    public Optional<NaryTreeNode> getChild(Predicate<NaryTreeNode> predicate){
        Optional<NaryTreeNode> node = this.children.stream().filter(predicate).findAny();
        return node;
    }

    public static void main(String[] args) {
        NaryTreeNode root = new NaryTreeNode(1);
        List<NaryTreeNode> list = new ArrayList<>();
        list.add(new NaryTreeNode(2));
        list.add(new NaryTreeNode(3));
        list.add(new NaryTreeNode(4));

        root.setChildren(list);

        Optional<NaryTreeNode> child = root.getChild((NaryTreeNode node) -> node.data == 2);
        if (child.isPresent()){
            child.get().addChild(new NaryTreeNode(5));
        }

        child = root.getChild((NaryTreeNode node) -> node.data == 3);
        if (child.isPresent()){
            child.get().addChild(new NaryTreeNode(6));
        }

        child = root.getChild((NaryTreeNode node) -> node.data == 4);
        if (child.isPresent()){
            child.get().addChild(new NaryTreeNode(7));
        }

        root.bfs();
    }

}
