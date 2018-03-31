package com.topcoder.geeksforgeeks;

/**
 * Created by sshil on 11/30/2015.
 */
//http://www.geeksforgeeks.org/merge-two-sorted-linked-lists-such-that-merged-list-is-in-reverse-order/
public class MergeTwoSortedListInReverseOrder {
    static class Node {
        private int data;
        private Node next;

        public Node(int data){
            this.data = data;
            this.next = null;
        }
        public  Node setNext(int data){
            return this.next = new Node(data);
        }
    }

    public Node reverse(Node list1, Node list2){
        Node newList = null;
        while (list1 != null && list2 != null) {
            if (list1.data < list2.data){
                Node temp = list1 ;
                list1 = list1.next;
                temp.next = newList;
                newList = temp;
            } else {
                Node temp = list2 ;
                list2 = list2.next;
                temp.next = newList;
                newList = temp;
            }
        }

        while(list1 != null){
            Node temp = list1 ;
            list1 = list1.next;
            temp.next = newList;
            newList = temp;
        }
        while(list2 != null){
            Node temp = list2 ;
            list2 = list2.next;
            temp.next = newList;
            newList = temp;
        }
        return newList;
    }



    public static void main(String[] args){
        Node list1 = new Node(5);
        list1.setNext(10).setNext(15).setNext(40);
        Node list2 = new Node(2);
        list2.setNext(3).setNext(20);
        Node reversed = new MergeTwoSortedListInReverseOrder().reverse(list1,
                                                                   list2);
        while (reversed != null){
            System.out.println(reversed.data);
            reversed = reversed.next;
        }
    }
}
