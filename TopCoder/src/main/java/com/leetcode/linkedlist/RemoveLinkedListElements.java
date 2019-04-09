package com.leetcode.linkedlist;

public class RemoveLinkedListElements {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    /*
    public ListNode removeElements(ListNode head, int val) {
        ListNode prevNode = null;
        ListNode tempFirstNode = null;

        while (head != null) {
            if (head.val == val) {
                if (prevNode != null) {
                    prevNode.next = head.next;
                }
                head = head.next;
            }
            if (tempFirstNode == null && (head != null && head.val != val)) {
                tempFirstNode = head;
            }
            prevNode = head;
            if (head == null) {
                break;
            }
            head = head.next;
        }
        return tempFirstNode;
    }
    */
    public ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return null;
        }

        if (head.val == val) {
            return removeElements(head.next, val);
        }

        ListNode prev = head;
        ListNode current = head.next;

        while (current != null) {
            if (current.val == val) {
                prev.next = current.next;
            } else {
                prev = current;
            }
            current = current.next;
        }

        return head;
    }

    public static void main(String[] args) {
        ListNode node = new ListNode(1);
        node.next = new ListNode(2);
        node.next.next = new ListNode(6);
        node.next.next.next = new ListNode(3);
        node.next.next.next.next = new ListNode(4);
        node.next.next.next.next.next = new ListNode(5);
        node.next.next.next.next.next.next = new ListNode(6);
        /*
        ListNode node = new ListNode(1);
        node.next = new ListNode(1);
        */

        ListNode res = new RemoveLinkedListElements().removeElements(node, 6);
        while (res != null) {
            System.out.println(res.val);
            res = res.next;
        }
    }

}
