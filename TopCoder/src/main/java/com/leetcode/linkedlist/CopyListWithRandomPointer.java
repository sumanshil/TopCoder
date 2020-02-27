package com.leetcode.linkedlist;

import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/problems/copy-list-with-random-pointer/
public class CopyListWithRandomPointer {
    static class Node {
        public int val;
        public Node next;
        public Node random;

        public Node() {}

        public Node(int _val,Node _next,Node _random) {
            val = _val;
            next = _next;
            random = _random;
        }
    };

    public Node copyRandomList(Node head) {
        Map<Node, Node> map = new HashMap<>();
        Node result = recursive(head, map);
        return result;
    }

    private Node recursive(Node head, Map<Node, Node> map) {
        if (head == null) {
            return null;
        }
        Node clonedNode = new Node(head.val, null, null);
        map.put(head, clonedNode);
        clonedNode.next = recursive(head.next, map);
        clonedNode.random = map.getOrDefault(head.random, null);
        return clonedNode;
    }
    /*
    public Node copyRandomList(Node head) {
        Node startOriginal = head;
        Node startCloned = null;

        Node currentOriginal = startOriginal;
        while (currentOriginal != null) {
            Node currentCloned = new Node();
            currentCloned.val = currentOriginal.val;
            if (startCloned == null) {
                startCloned = currentCloned;
            }

            Node originalNext = currentOriginal.next;
            currentCloned.next = originalNext;
            currentOriginal.next = currentCloned;
            currentOriginal = originalNext;
        }


        currentOriginal = startOriginal;
        while (currentOriginal != null) {
            Node nextOriginal = currentOriginal.next.next;
            if (currentOriginal.random != null) {
                currentOriginal.next.random = currentOriginal.random.next;
            }
            currentOriginal = nextOriginal;
        }

        currentOriginal = startOriginal;

        while (currentOriginal != null) {
            Node clonedNode = currentOriginal.next;
            currentOriginal.next = clonedNode.next;
            if (currentOriginal.next != null) {
                clonedNode.next = currentOriginal.next.next;
            }
            currentOriginal = currentOriginal.next;
        }

        return startCloned;
    }
    */

    public static void main(String[] args) {

        Node node1 = new Node();
        node1.val = 1;

        Node node2 = new Node();
        node2.val = 2;

        node1.next = node2;
        node1.random = node2;

        node2.random = node2;

        /*
        Node node1 = new Node();
        node1.val = -1;

        Node node2 = new Node();
        node2.val = 8;
        node1.next = node2;

        Node node3 = new Node();
        node3.val = 7;
        node2.next = node3;

        Node node4 = new Node();
        node4.val = -3;
        node3.next = node4;

        Node node5 = new Node();
        node5.val = 4;
        node4.next = node5;

        node1.random = node5;
        node2.random = node4;
        node3.random = null;
        node4.random = null;
        node5.random = node1;
        */
        Node result = new CopyListWithRandomPointer().copyRandomList(node1);
        System.out.println(result);
    }
}
