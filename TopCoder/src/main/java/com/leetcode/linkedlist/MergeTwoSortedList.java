package com.leetcode.linkedlist;

  class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
  }

public class MergeTwoSortedList {

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        ListNode head = new ListNode(-1);
        ListNode tempHead = head;

        while (l1 != null && l2 != null) {

            if (l1.val == l2.val) {
                tempHead.next = l1;
                l1 = l1.next;
                tempHead.next.next = l2;
                l2 = l2.next;
                tempHead = tempHead.next.next;
                tempHead.next = null;
            } else if (l1.val < l2.val) {
                tempHead.next = l1;
                l1 = l1.next;
                tempHead = tempHead.next;
            } else {
                tempHead.next = l2;
                l2 = l2.next;
                tempHead = tempHead.next;
            }
        }

        while (l1 != null) {
            tempHead.next = l1;
            l1 = l1.next;
        }

        while (l2 != null) {
            tempHead.next = l2;
            l2 = l2.next;
        }

        return head.next;
    }


    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(4);

        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(4);
        ListNode result = new MergeTwoSortedList().mergeTwoLists(l1, l2);
        while (result != null) {
            System.out.println(result.val);
            result = result.next;
        }
    }



}
