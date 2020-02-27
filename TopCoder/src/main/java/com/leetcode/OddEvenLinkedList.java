package com.leetcode;

//https://mail.google.com/mail/u/0/#inbox
public class OddEvenLinkedList {

    public ListNode oddEvenList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode oddListHead = new ListNode(-1);

        ListNode oddListTail = oddListHead;

        ListNode evenListCurrent = head;

        while (true) {
            if (evenListCurrent.next == null) {
                evenListCurrent.next = oddListHead.next;
                break;
            } else {
                ListNode temp = evenListCurrent.next.next;

                ListNode nextNode = evenListCurrent.next;

                evenListCurrent.next = temp;

                nextNode.next = null;
                oddListTail.next = nextNode;

                oddListTail = oddListTail.next;
                if (evenListCurrent.next != null) {
                    evenListCurrent = evenListCurrent.next;
                } else {
                    evenListCurrent.next = oddListHead.next;
                    break;
                }
            }
        }
        return head;
    }

    public static void main(String[] args) {

        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        head.next.next.next.next.next.next = new ListNode(7);
        head.next.next.next.next.next.next.next = new ListNode(8);

        ListNode temp = new OddEvenLinkedList().oddEvenList(head);
        while (temp != null) {
            System.out.println(temp.val);
            temp = temp.next;
        }

    }
}
