package com.leetcode.linkedlist;

//https://leetcode.com/problems/partition-list/
public class PartitionList {

    public ListNode partition(ListNode head, int x) {
        ListNode more = new ListNode(0);
        ListNode moreHead = more;

        ListNode less = new ListNode(0);
        ListNode lessHead = less;

        while (head != null) {
            if (head.val < x) {
                less.next = head;
                less = head;
            } else {
                more.next = head;
                more = head;
            }
            head = head.next;
        }
        less.next = moreHead.next;
        more.next = null;
        return lessHead.next;
    }

    /*
    public ListNode partition(ListNode head, int x) {
        ListNode smallerNodesHead = null;
        ListNode smallerNodesHeadTemp = null;


        while (head != null && head.val < x) {
            if (smallerNodesHeadTemp == null) {
                smallerNodesHeadTemp = head;
                smallerNodesHead = smallerNodesHeadTemp;
                head = head.next;
                smallerNodesHeadTemp.next = null;
            } else {
                smallerNodesHeadTemp.next = head;
                head = head.next;
                smallerNodesHeadTemp = smallerNodesHeadTemp.next;
                smallerNodesHeadTemp.next = null;
            }
        }

        ListNode tempHead = head;

        while (tempHead != null) {
            if (tempHead.next != null && tempHead.next.val < x) {
                if (smallerNodesHeadTemp == null) {
                    smallerNodesHeadTemp = tempHead.next;
                    tempHead.next = tempHead.next.next;
                    smallerNodesHead = smallerNodesHeadTemp;
                } else {
                    smallerNodesHeadTemp.next = tempHead.next;
                    tempHead.next = tempHead.next.next;
                    smallerNodesHeadTemp = smallerNodesHeadTemp.next;
                }
                smallerNodesHeadTemp.next = null;
            } else if (tempHead.next != null && tempHead.next.val >= x) {
                tempHead = tempHead.next;
            } else {
                tempHead = tempHead.next;
            }
        }

        if (smallerNodesHeadTemp != null) {
            smallerNodesHeadTemp.next = head;
        } else {
            smallerNodesHead = head;
        }
        return smallerNodesHead;
    } */

    public static void main(String[] args) {
        /*
        ListNode head = new ListNode(1);
        head.next = new ListNode(4);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(2);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(2);
        */
        ListNode head = new ListNode(3);
        head.next = new ListNode(1);
        head.next.next = new ListNode(2);

        ListNode result = new PartitionList().partition(head, 3);
        while (result != null) {
            System.out.println(result.val);
            result = result.next;
        }
    }
}
