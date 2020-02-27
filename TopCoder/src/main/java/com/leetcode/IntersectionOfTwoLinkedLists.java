package com.leetcode;

//https://leetcode.com/problems/intersection-of-two-linked-lists/
public class IntersectionOfTwoLinkedLists {

    public SwapNodesInPair.ListNode getIntersectionNode(SwapNodesInPair.ListNode headA, SwapNodesInPair.ListNode headB) {
        int countA = 0;
        int countB = 0;
        SwapNodesInPair.ListNode currentA = headA;
        SwapNodesInPair.ListNode currentB = headB;

        while(currentA != null) {
            countA++;
            currentA = currentA.next;
        }

        while (currentB != null) {
            countB++;
            currentB = currentB.next;
        }
        currentA = headA;
        currentB = headB;

        if (countA > countB) {
            int diff = countA - countB;
            while(diff > 0) {
                currentA = currentA.next;
                diff--;
            }
        } else if (countB > countA) {
            int diff = countB - countA;
            while(diff > 0) {
                currentB = currentB.next;
                diff--;
            }
        }

        while(currentA != null && currentB != null && currentA != currentB) {
            currentA = currentA.next;
            currentB = currentB.next;
        }

        return currentA;
    }

    public static void main(String[] args) {
        SwapNodesInPair.ListNode node4 = new SwapNodesInPair.ListNode(4);
        SwapNodesInPair.ListNode node1 = new SwapNodesInPair.ListNode(1);
        SwapNodesInPair.ListNode node8 = new SwapNodesInPair.ListNode(8);
        SwapNodesInPair.ListNode node44 = new SwapNodesInPair.ListNode(4);
        SwapNodesInPair.ListNode node5 = new SwapNodesInPair.ListNode(5);
        node4.next = node1;
        node1.next = node8;
        node8.next = node44;
        node44.next = node5;

        SwapNodesInPair.ListNode node15 = new SwapNodesInPair.ListNode(5);
        SwapNodesInPair.ListNode node10 = new SwapNodesInPair.ListNode(0);
        SwapNodesInPair.ListNode node11 = new SwapNodesInPair.ListNode(1);

        node15.next = node10;
        node10.next = node11;
        node11.next = node8;
        SwapNodesInPair.ListNode result = new IntersectionOfTwoLinkedLists().getIntersectionNode(node4, node15);
        System.out.println(result.val);

    }
}
