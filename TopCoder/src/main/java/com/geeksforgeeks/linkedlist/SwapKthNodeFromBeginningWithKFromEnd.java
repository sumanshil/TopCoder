package com.geeksforgeeks.linkedlist;
//http://www.geeksforgeeks.org/swap-kth-node-from-beginning-with-kth-node-from-end-in-a-linked-list/
public class SwapKthNodeFromBeginningWithKFromEnd {
// 1-> 5 -> 6 - > 7 -> 8 -> 9 -> 10
    public LinkedListNode swap(LinkedListNode start, int k){
        LinkedListNode ref1 = null;// points to the node just before kth node from start
        LinkedListNode ref2 = null;//points to the node just before kth node from end
        LinkedListNode ref3 = null;//points to the node just before k+1 th node from start
        
        LinkedListNode node1 = null;//refers to kth node from start
        LinkedListNode node2 = null;// refers to kth node from end;
        
        LinkedListNode temp = start;
        int i = 1;
        while(i<k-1){
            temp = temp.next;
            i++;
        }
        
        ref1 = temp;
        node1 = ref1.next;
        ref3 = node1.next;
        
        temp = start;
        LinkedListNode temp1 = temp;
        i = 0;
        while(i < k){
            temp1 = temp1.next;
            i++;
        }
        
        while(temp1.next != null){
            temp = temp.next;
            temp1 = temp1.next;            
        }
        
        ref2 = temp;
        node2 = ref2.next;
        
        
        ref2.next = node1;
        node1.next = node2.next;
        
        ref1.next = node2;
        node2.next = ref3;
        return start;
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        LinkedListNode head2 = new LinkedListNode(1);
        head2.next = new LinkedListNode(5);
        head2.next.next = new LinkedListNode(6);
        head2.next.next.next = new LinkedListNode(7);
        head2.next.next.next.next = new LinkedListNode(8);
        head2.next.next.next.next.next = new LinkedListNode(9);
        head2.next.next.next.next.next.next = new LinkedListNode(10);
        new SwapKthNodeFromBeginningWithKFromEnd().swap(head2, 3);

    }

}
