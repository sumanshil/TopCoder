package com.geeksforgeeks.linkedlist;

public class SwapPairwiseNodes
{

	public LinkedListNode swap(LinkedListNode start)
	{
	    if ( start == null )
	    {
	    	return null;
	    }
	    else if ( start.next == null )
	    {
	    	return start;
	    }
	    LinkedListNode a = start;
	    LinkedListNode b = start.next;
	    LinkedListNode ret = swap (b.next);
	    b.next = a;
	    a.next = ret;
	    return b;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
	    LinkedListNode start = new LinkedListNode(1);
	    start.next = new LinkedListNode(2);
	    start.next.next = new LinkedListNode(3);
	    start.next.next.next = new LinkedListNode(4);
	    start.next.next.next.next = new LinkedListNode(5);
	    start.next.next.next.next.next = new LinkedListNode(6);
	    start.next.next.next.next.next.next = new LinkedListNode(7);
        LinkedListNode l = new SwapPairwiseNodes().swap(start);
        while(l!= null)
        {
        	System.out.println(l.data);
        	l = l.next;
        }
	}

}
