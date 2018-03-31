package com.topcoder.problems.arista;
//http://www.geeksforgeeks.org/arista-network-interview-set-1/
//Question 1: Given a sorted doubly link list and two numbers C and K. You need to decrease the info of node
//with data K by C and insert the new node formed at its correct position such that the list remains sorted.
public class DoublyLinkedList {
     static class Node{
    	 int data;
    	 Node next;
    	 Node prev;    	 
    	 public Node(int data){
    		 this.data = data;
    	 }
     }
     
     
     public void change(Node start, int k , int c){
    	 int length = getLength(start);
    	 int[] arr = new int[length];
    	 Node temp = start;
    	 int index = 0;
    	 while(temp != null){
    		 arr[index] = temp.data;
    		 index++;
    		 temp = temp.next;
    	 }
    	 
    	 index = getIndex(0, arr.length, k, arr);
         if (index != -1){
        	 changeValueAtNode(index, k-c, start);
        	 Node n = deleteNode(start, index);
        	 if (n != null){
        		 int i = getInsertionIndex(0, arr.length, arr, n.data);
        		 insertAtNode(i, n,  start);
        	 }
         }
     }
     
     
     private void insertAtNode(int index, Node n, Node start) {
		int i = 0;
		while(start != null){
	         if (i == index){
	        	 Node temp = start.prev;
	        	 temp.next = start;
	        	 start.prev = n;
	        	 
	        	 temp.next = n;
	        	 n.prev = temp;
	        	 break;
	         }
	         start = start.next;
	         i++;
		}
		
	}


	private int getInsertionIndex(int start, int end, int[] arr, int key) {
        if (start > end)
        	return -1;
        
        int mid = (start+end)/2;
        if (mid == 0 && key < arr[mid])
        	return mid;
        
        if (arr[mid-1] < key && key < arr[mid])
        	return mid;
        
        if (arr[mid] > key){
        	return getInsertionIndex(start, mid-1, arr, key);
        } else {
        	return getInsertionIndex(mid+1, end, arr, key);
        }        
	}


	private Node deleteNode(Node start, int index) {
	   	int temp = 0;
		while(start != null){
			if (temp == index){
				return start;
			}
			temp++;
			index++;
		}
		return null;
	}


	private void changeValueAtNode(int index, int value, Node start) {
    	int temp = 0;
		while(start != null){
			if (temp == index){
				start.data = value;
				break;
			}
			temp++;
			index++;
		}
		
	}


	private int getIndex(int start, int end, int key, int[] arr) {
		if (start > end)
			return -1;
		
		int mid = (start + end)/2;
		if (arr[mid] == key)
			return mid;
		
		
		if(arr[mid] > key){
			return getIndex(start, mid-1, key, arr);
		} else {
			return getIndex(mid+1, end, key, arr);
		}
	}


	private int getLength(Node start) {
		int length = 0;
		while(start != null){
			length++;
			start = start.next;
		}
		return length;
	}


	public static void main(String[] args){
    	 Node start = new Node (2);
    	 
    	 start.next = new Node(4);
    	 start.next.prev = start;
    	 
    	 start.next.next = new Node(6);
    	 start.next.next.prev = start.next;
    	 
    	 start.next.next.next = new Node(8);
    	 start.next.next.next.prev = start.next.next;

    	 start.next.next.next.next = new Node(10);
    	 start.next.next.next.next.prev = start.next.next.next;

     }
}
