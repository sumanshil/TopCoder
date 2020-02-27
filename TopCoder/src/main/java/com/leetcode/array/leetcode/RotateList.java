package com.leetcode.array.leetcode;

//https://leetcode.com/problems/rotate-list/
public class RotateList {

    static public class ListNode {
       public int val;
       public ListNode next;
       public ListNode(int x) { val = x; }
    }

    public ListNode rotateRight(ListNode head, int k) {
        ListNode start = head;
        ListNode endNode = start;
        int count = 0;
        for (int i  = 0 ; i < k ; i++) {
            if (endNode == null) {
                break;
            }
            count++;
            endNode = endNode.next;
        }

        if (endNode == null) {
            endNode = head;
            int resCount = k % count;
            for (int i  = 0 ; i < resCount ; i++) {
                endNode = endNode.next;
            }

        }


        while (endNode.next != null) {
            endNode = endNode.next;
            start = start.next;
        }

        endNode.next = head;
        ListNode tempStart = start.next;
        start.next = null;
        return tempStart;

    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        node1.next = new ListNode(2);
        node1.next.next = new ListNode(3);
        node1.next.next.next = new ListNode(4);
        node1.next.next.next.next = new ListNode(5);

        /*
        ListNode node1 = new ListNode(0);
        node1.next = new ListNode(1);
        node1.next.next = new ListNode(2);

        /

        ListNode result = new RotateList().rotateRight(node1, 2);

        while (result != null) {
            System.out.println(result.val);
            result = result.next;
        }

         */
    }
}
