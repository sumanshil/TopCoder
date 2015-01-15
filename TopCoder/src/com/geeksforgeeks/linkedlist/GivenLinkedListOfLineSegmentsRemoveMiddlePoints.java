package com.geeksforgeeks.linkedlist;

//http://www.geeksforgeeks.org/given-linked-list-line-segments-remove-middle-points/
public class GivenLinkedListOfLineSegmentsRemoveMiddlePoints
{

	static class Node
	{
		int x;
		int y;
		Node next;
		Node(int x,
			 int y)
		{
			this.x = x;
			this.y = y;					
		}
		
	    boolean isHorizontal(Node n)
	    {
	    	return n.y == this.y;
	    }
	    
	    boolean isVertical(Node n)
	    {
	    	return n.x == this.x;
	    }
	    
	    public String toString()
	    {
	    	return this.x +" "+ this.y;
	    }
	}
	
	public void removeMiddlePoints(Node start)
	{
		Node first = start;
		Node second = first.next;
		boolean isHorizontal = false;
		boolean isVertical = false;
		while(second != null)
		{
			if (second.next == null)
			{
				break;
			}
			if (first.isHorizontal(second))
			{
				if ( (isHorizontal || (!isHorizontal&& !isVertical))
					  && second.next != null
					  && second.isHorizontal(second.next))
				{
					//delete second
					first.next = second.next;
					second = second.next;
					isHorizontal = true;
				}
				// turn
				else if (isHorizontal
						&& second.next != null
						&& second.isVertical(second.next))
				{
					first = second;
					second = first.next;
					isVertical = true;
					isHorizontal = false;
				}
				else if (isVertical)
				{
					first = second;
					second = second.next;
					isHorizontal = true;
					isVertical = false;
				}
				
			}
			else if (first.isVertical(second))
			{
				if ( (isVertical || (!isHorizontal&& !isVertical))
					  && second.next != null
					  && second.isVertical(second.next))
				{
					//delete second
					first.next = second.next;
					second = second.next;
					isVertical = true;
				}
				// turn
				else if (isVertical
						&& second.next != null
						&& second.isHorizontal(second.next))
				{
					first = second;
					second = second.next;
					isHorizontal = true;
					isVertical = false;
				}
				else if (isHorizontal)
				{
					first = second;
					second = second.next;
					isHorizontal = false;
					isVertical = true;
				}
			}
			else
			{
				System.out.println("Not in a straight line. Exiting..");
				break;
			}
		}
		print(start);
	}
	
	
	private void print(Node start)
	{
		Node current = start;
		while(current != null)
		{	
		  System.out.println(current);
		  current = current.next;
		}
	}


	public static void main(String[] args)
	{
		Node start = new Node(0,10);
		start.next = new Node(1,10);
		start.next.next = new Node(5,10);
		start.next.next.next = new Node(7,10);
		start.next.next.next.next = new Node(7,5);
		start.next.next.next.next.next = new Node(7,4);
		start.next.next.next.next.next.next = new Node(20,4);
		start.next.next.next.next.next.next.next = new Node(40,4);
		
//		Node start = new Node(2,3);
//		start.next = new Node(4,3);
//		start.next.next = new Node(6,3);
//		start.next.next.next = new Node(10,3);
//		start.next.next.next.next = new Node(12,3);
		
		new GivenLinkedListOfLineSegmentsRemoveMiddlePoints().removeMiddlePoints(start);
		

	}

}
