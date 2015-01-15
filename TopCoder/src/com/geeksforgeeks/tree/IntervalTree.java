package com.geeksforgeeks.tree;
//http://www.geeksforgeeks.org/interval-tree/
public class IntervalTree
{
	static class Interval
	{
		int low;
		int high;
		public Interval(int low, int high)
		{
			this.low = low;
			this.high = high;
		}
	}
	
	
    static class IntervalTreeNode
    {
    	public IntervalTreeNode(Interval interval)
		{
			this.i = interval;
			this.max = i.high;
		}
		Interval i;
    	int max;
    	IntervalTreeNode left;
    	IntervalTreeNode right;
    }
    
    public IntervalTreeNode insert(IntervalTreeNode root,
    		                       Interval interval)
    {
    	if (root == null)
    	{
    		root = new IntervalTreeNode(interval);
    		return root;
    	}
    	
    	if (interval.low < root.i.low)
    	{
    		root.left = insert(root.left, interval);
    	}
    	else
    	{
    		root.right = insert(root.right, interval);
    	}
    	
    	if (root.max < interval.high)
    	{
    		root.max = interval.high;
    	}
    	return root;
    }
    
    public boolean doSearchOverlap(IntervalTreeNode root, Interval interval)
    {
    	if (searchOverlap(root.i, interval))
    	{
    		return true;
    	}
    	
    	if (root.left != null
    		&& root.left.max >= interval.low)
    	{
    		return doSearchOverlap(root.left, interval);
    	}
    	return doSearchOverlap(root.right, interval);
    }
    
	private boolean searchOverlap(Interval interval1, Interval interval2)
	{
		return (interval1.low <= interval2.high && interval2.low <= interval1.high);
	}

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub

	}

}
