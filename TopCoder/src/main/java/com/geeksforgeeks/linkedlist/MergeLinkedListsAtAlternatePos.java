package com.geeksforgeeks.linkedlist;

public class MergeLinkedListsAtAlternatePos {
    private static LinkedListNode head1 = new LinkedListNode(1);
    private static LinkedListNode head2 = new LinkedListNode(4);
    
    static {
//        head1.next = new LinkedListNode(7);
//        head1.next.next = new LinkedListNode(17);
//        head1.next.next.next = new LinkedListNode(13);
//        head1.next.next.next.next = new LinkedListNode(11);
//    
//        
//        head2.next = new LinkedListNode(10);
//        head2.next.next = new LinkedListNode(2);
//        head2.next.next.next = new LinkedListNode(4);
//        head2.next.next.next.next = new LinkedListNode(6);
        
        head1.next = new LinkedListNode(2);
        head1.next.next = new LinkedListNode(3);
           
        head2.next = new LinkedListNode(5);
        head2.next.next = new LinkedListNode(6);
        head2.next.next.next = new LinkedListNode(7);
        head2.next.next.next.next = new LinkedListNode(8);
        
    }
    public static void print(LinkedListNode head1){
        LinkedListNode temp = head1;
        while(temp != null){
            System.out.print(" "+temp.data);
            temp = temp.next;
        }
        System.out.println();
    }
    public static void merge(LinkedListNode l1 , LinkedListNode l2 ){
        if (l1.next == null){
            if (l2 != null){
                l1.next = l2;
                LinkedListNode temp = l2;
                head2 = l2.next;
                temp.next = null;
            }
            return;
        } else if (l2 == null){
            return;
        } else {
            LinkedListNode temp = l1.next;
            LinkedListNode temp1 = l2.next;
            
            l1.next = l2;
            l2.next = temp;
            head2 = temp1; 
            l2 = temp1;
        }
        System.out.print("HEAD1 ");
        print(head1);
        
        System.out.print("HEAD2 ");
        print(head2);
        
        merge(l1.next.next, l2);
    }
    
    /**
     * @param args
     */
    public static void main(String[] args) {
        merge(head1, head2);
        print(head1);
        print(head2);

    }

}
