package com.geeksforgeeks.linkedlist;

/**
 * Created by sshil on 4/15/2016.
 */
//http://www.geeksforgeeks.org/rearrange-a-linked-list-such-that-all-even-and-odd-positioned-nodes-are-together/
public class ReArrangeLinkedListToMakeOddAndEvenTogether {
    static class Node {
        int data;
        Node next;
        Node(int data){
            this.data = data;
        }
    }

    public Node rearrange(Node startNode){
        int stepCount = 1;
        Node currentNode = startNode;
        while(true){
            Node nextNode = currentNode;
            boolean breakLoop = false;
            for ( int i = 0 ; i < stepCount ; i++) {
                nextNode = nextNode.next;
                if (nextNode == null){
                    breakLoop = true;
                    break;
                }
            }
            Node nodeToBeRemoved = nextNode.next;
            if (nodeToBeRemoved != null){
                nextNode.next = nodeToBeRemoved.next;
                nodeToBeRemoved.next = null;
            }else {
                break;
            }
            Node tempNode = currentNode.next;
            currentNode.next = nodeToBeRemoved;
            nodeToBeRemoved.next = tempNode;
            currentNode = currentNode.next;
            stepCount++;
            if (breakLoop){
                break;
            }
        }
        return startNode;
    }

    public static void main(String[] args) {
        Node node = new Node(10);
        node.next = new Node(22);
        node.next.next = new Node(30);
        node.next.next.next = new Node(43);
        node.next.next.next.next = new Node(56);
        node.next.next.next.next.next = new Node(70);
        Node startNode = new ReArrangeLinkedListToMakeOddAndEvenTogether().rearrange(node);
        while(startNode != null){
            System.out.print(startNode.data+" ");
            startNode = startNode.next;
        }
    }
}
