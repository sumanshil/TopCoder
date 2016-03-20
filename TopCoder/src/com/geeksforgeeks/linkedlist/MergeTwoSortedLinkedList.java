package com.geeksforgeeks.linkedlist;

/**
 * Created by sshil on 3/19/2016.
 */
public class MergeTwoSortedLinkedList {
	static class Node {
		int data;
		Node next;
		public Node(int data){
			this.data = data;
		}
	}

	public Node reverse(Node start1, Node start2) {
		Node list1 = start1;
		Node list2 = start2;
		Node reversedListHead = null;
		while (list1 != null && list2 != null) {
			if (list1.data < list2.data){
				Node selectedNode = list1;
				list1 = list1.next;
				selectedNode.next = null;
				reversedListHead = insertInReversedList(reversedListHead, selectedNode);
			} else if (list2.data < list1.data) {
				Node selectedNode = list2;
				list2 = list2.next;
				selectedNode.next = null;
				reversedListHead = insertInReversedList(reversedListHead, selectedNode);
			}
		}

		if (list1 != null){
			while (list1 != null){
				Node selectedNode = list1;
				list1 = list1.next;
				selectedNode.next = null;
				reversedListHead = insertInReversedList(reversedListHead, selectedNode);

			}
		}
		if (list2 != null){
			while (list2 != null){
				Node selectedNode = list2;
				list2 = list2.next;
				selectedNode.next = null;
				reversedListHead = insertInReversedList(reversedListHead, selectedNode);
			}
		}
		return reversedListHead;
	}

	private Node insertInReversedList(Node reversedListHead, Node selectedNode) {
		if (reversedListHead == null){
			reversedListHead = selectedNode;
			return reversedListHead;
		} else {
			selectedNode.next = reversedListHead;
			return selectedNode;
		}
	}


	public static void main(String[] args) {
		Node start = new Node(5);
		start.next = new Node(10);
		start.next.next = new Node(15);
		start.next.next.next = new Node(40);

		Node start1 = new Node(2);
		start1.next = new Node(3);
		start1.next.next = new Node(20);

		Node reversed = new MergeTwoSortedLinkedList().reverse(start1, start);
		while(reversed != null){
			System.out.println(reversed.data);
			reversed = reversed.next;
		}
	}
}
