package com.geeksforgeeks.linkedlist;

public class ReverseALinkedList {

	public static LinkedListNode reverseRecursive(LinkedListNode start){
		if (start.next == null){
			return start;
		}
		LinkedListNode a = start;
		LinkedListNode b = start.next;
		start.next = null;
		LinkedListNode nodeStart = reverseRecursive(b);
		b.next = a;
		return nodeStart;
	}
	public static LinkedListNode reverse(LinkedListNode start){
		LinkedListNode head = start;
		LinkedListNode next = start.next;
		LinkedListNode loop = null;
		
		while(next != null){
			head.next = loop;
			loop = head;
			head = next;
			next = next.next;
		}
		head.next = loop;
		return head;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LinkedListNode node = new LinkedListNode(2);
		node.next = new LinkedListNode(3);
		node.next.next = new LinkedListNode(4);
		node.next.next.next = new LinkedListNode(5);
		node.next.next.next.next = new LinkedListNode(6);
        LinkedListNode l1 = reverseRecursive(node);
        while(l1 != null){
        	System.out.println(l1.data);
        	l1 = l1.next;
        }
	}

}
