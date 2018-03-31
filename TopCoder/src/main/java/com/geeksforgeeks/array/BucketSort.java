package com.geeksforgeeks.array;

import java.util.ArrayList;
import java.util.List;

public class BucketSort {
    
	static class Node
	{
		float f;
		Node  next;
		Node(float f)
		{
			this.f = f;
		}
		
		
		float[] getValues()
		{
			Node temp = this;
			List<Float> list = new ArrayList<Float>();
			while(temp != null)
			{
				list.add(temp.f);
				temp = temp.next;
				
			}
			Float[] arr  = (Float[])list.toArray(new Float[0]);
			float[] arr1 = new float[arr.length]; 
			for(int k = 0 ; k < arr.length ; k++)
			{
				arr1[k] = arr[k];
			}
			return arr1;
		}
		
	}
	
	public void sort(float[] arr)
	{
		int n = arr.length;
		Node[] buckets = new Node[n];
		for( int i = 0 ; i < n ; i++)
		{
			int key = (int)(10 * arr[i]);
			if (buckets[key] == null)
			{
				buckets[key] = new Node(arr[i]);
			}
			else
			{
				Node node = buckets[key];
				int count = 0;
				boolean inserted = false;
				Node prevNode = null;
				while(node != null)
				{
					if (node.f > arr[i]);
					{
						Node temp = new Node(arr[i]);
						temp.next = node;
						if (count == 0)
						{
							buckets[key] = temp;
						}
						inserted = true;
					}
					prevNode = node;
					node = node.next;
				}
				if (!inserted)
				{
					prevNode.next = new Node(arr[i]);
				}
			}
		}
		
		List<Float> list= new ArrayList<Float>();
		for(int i = 0 ; i < buckets.length ; i++)
		{
			if (buckets[i] != null)
			{
				float[] arr1 = buckets[i].getValues();
				for(int k = 0 ; k < arr1.length ; k++)
				{
					list.add(arr1[k]);
				}
			}
		}
		
		for(Float f : list)
		{
			System.out.println(f);
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub

	}

}
