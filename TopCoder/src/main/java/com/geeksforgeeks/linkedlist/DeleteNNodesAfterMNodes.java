package com.geeksforgeeks.linkedlist;

public class DeleteNNodesAfterMNodes {

    public LinkedListNode deleteNodes(LinkedListNode start, int n , int m){
        LinkedListNode temp = start;
        
        while(temp != null){
            int x = m;
            int y = n;
            while(x>1){
                if (temp == null)
                    break;
                temp = temp.next;
                x--;
            }
            
            while(y>0 && temp != null){
                if(temp.next == null)
                    break;
                temp.next = temp.next.next ;                   
                y--;
            }
            if (temp != null)
                temp = temp.next;
        }
        return start;
    }
    
    public void print(LinkedListNode start){
        LinkedListNode temp = start;
        while(temp != null){
            System.out.print(temp.data+" => ");
            temp = temp.next;
        }
        System.out.println();
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
        new DeleteNNodesAfterMNodes().print(head2);
        LinkedListNode start = new DeleteNNodesAfterMNodes().deleteNodes(head2, 1, 3);
        new DeleteNNodesAfterMNodes().print(start);
    }

}
