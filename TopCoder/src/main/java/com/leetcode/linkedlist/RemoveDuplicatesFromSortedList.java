package com.leetcode.linkedlist;

//https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/
public class RemoveDuplicatesFromSortedList {

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode headNode = new ListNode(Integer.MAX_VALUE);
        ListNode currentTemp = headNode;

        int prevValue = -1;

        while (head != null) {
            if (head.next != null) {
                if (head.val == head.next.val || prevValue == head.val) {
                    // move head by 1 pointer as the node is duplicate
                    prevValue = head.val;
                    ListNode temp = head;
                    head = head.next;
                    temp.next = null;
                } else {
                    // append this node to the result
                    currentTemp.next = head;
                    prevValue = head.val;
                    head = head.next;
                    currentTemp = currentTemp.next;
                }
            } else {
                // last node, check if it is duplicate
                if (prevValue == head.val) {
                    currentTemp.next = null;
                } else {
                    currentTemp.next = head;
                }
                head = head.next;
            }
        }
        return headNode.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(1);
        head.next.next = new ListNode(1);
        head.next.next.next = new ListNode(2);
        head.next.next.next.next = new ListNode(3);
        //head.next.next.next.next.next = new ListNode(4);
        //head.next.next.next.next.next.next = new ListNode(5);
        ListNode result = new RemoveDuplicatesFromSortedList().deleteDuplicates(head);
        while (result != null) {
            System.out.println(result.val);
            result = result.next;
        }
    }
}
