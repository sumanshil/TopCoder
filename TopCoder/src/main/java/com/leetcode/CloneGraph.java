package com.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

//https://leetcode.com/problems/clone-graph/
public class CloneGraph {
    static class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {}

        public Node(int _val,List<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    };

    Map<Node, Node> oldToNewMap = new HashMap<>();

    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }

        if (oldToNewMap.containsKey(node)) {
            return oldToNewMap.get(node);
        }

        Node clonedNode = new Node(node.val, new LinkedList<>());
        oldToNewMap.put(node, clonedNode);
        for (Node neighbor : node.neighbors) {
            clonedNode.neighbors.add(cloneGraph(neighbor));
        }

        return clonedNode;
    }

    public static void main(String[] args) {
        Node node1 = new Node(1, new LinkedList<>());
        Node node2 = new Node(2, new LinkedList<>());
        Node node3 = new Node(3, new LinkedList<>());
        Node node4 = new Node(4, new LinkedList<>());

        node2.neighbors.add(node1);
        node1.neighbors.add(node2);

        node2.neighbors.add(node3);
        node3.neighbors.add(node2);

        node3.neighbors.add(node4);
        node4.neighbors.add(node3);

        node1.neighbors.add(node4);
        node4.neighbors.add(node1);

        Node result = new CloneGraph().cloneGraph(node1);
        System.out.println(result);

    }
}

