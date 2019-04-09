package com.leetcode.queue;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class MergeKSortedArray {

    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public ListNode mergeKLists(ListNode[] lists) {
        Queue<ListNode> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(a -> a.val));

        priorityQueue.addAll(Arrays.asList(lists));

        ListNode dummy = new ListNode(0);
        ListNode temp = dummy;

        while (!priorityQueue.isEmpty()) {
            ListNode current = priorityQueue.remove();
            dummy.next = current;
            if (current.next != null) {
                priorityQueue.add(current.next);
            }
            dummy = dummy.next;
            current.next = null;
            dummy.next = null;
        }

        return temp.next;
    }

    public static void main(String[] args) {
        ListNode node0 = new ListNode(1);
        node0.next = new ListNode(4);
        node0.next.next = new ListNode(5);

        ListNode node1 = new ListNode(1);
        node1.next = new ListNode(3);
        node1.next.next = new ListNode(4);

        ListNode node2 = new ListNode(2);
        node2.next = new ListNode(6);

        ListNode res = new MergeKSortedArray().mergeKLists(new ListNode[]{node0, node1, node2});

        while (res != null) {
            System.out.println(res.val);
            res = res.next;
        }
    }
}
