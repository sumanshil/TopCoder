package com.leetcode.linkedlist;

//https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/
public class RemoveElementFromDuplicateList {

    public ListNode deleteDuplicates(ListNode head) {

        ListNode headTemp = new ListNode(-1);
        int prevVal = -1;
        ListNode prvNode = headTemp;

        while (head != null ) {
           if (head.next != null && head.val == head.next.val) {
                while (head.next != null && head.val == head.next.val) {
                    head = head.next;
                }
                head = head.next;
           }
        }
        return headTemp.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(1);
        head.next.next = new ListNode(1);
        head.next.next.next = new ListNode(2);
        head.next.next.next.next = new ListNode(3);
        ListNode resultNode = new RemoveElementFromDuplicateList().deleteDuplicates(head);
        while (resultNode != null) {
            System.out.println(resultNode.val);
            resultNode = resultNode.next;
        }
    }
}
