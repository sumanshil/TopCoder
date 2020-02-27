package com.leetcode.tree;

import com.leetcode.array.leetcode.RotateList;

//https://leetcode.com/problems/remove-duplicates-from-sorted-list/
public class RemoveElementFromSortedList {

    public RotateList.ListNode deleteDuplicates(RotateList.ListNode head) {

        RotateList.ListNode start = head;
        while (head.next != null) {
            if (head.val == head.next.val ) {
                head.next = head.next.next;
            } else {
                head = head.next;
            }
        }
        return start;
    }

    public void print(RotateList.ListNode head) {
        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }
    }

    public static void main(String[] args) {
        RotateList.ListNode head = new RotateList.ListNode(1);
        head.next = new RotateList.ListNode(1);
        head.next.next = new RotateList.ListNode(2);
        head.next.next.next = new RotateList.ListNode(3);
        head.next.next.next.next = new RotateList.ListNode(3);

        new RemoveElementFromSortedList().deleteDuplicates(head);
        new RemoveElementFromSortedList().print(head);
    }
}
