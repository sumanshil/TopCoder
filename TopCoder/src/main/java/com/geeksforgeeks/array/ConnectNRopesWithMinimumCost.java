package com.geeksforgeeks.array;
//http://www.geeksforgeeks.org/connect-n-ropes-minimum-cost/
public class ConnectNRopesWithMinimumCost
{
	
	static class MinHeap
	{
		int size;
		int[] arr;
		int currentIndex=1;
		public MinHeap(int size)
		{
	        this.size = size+1;
	        arr = new int[this.size];
	        //this.currentIndex = 0;
		}
		
		
		public void insert(int element)
		{
			if (currentIndex >= this.size)
			{
				// throw Exception
			}
			arr[currentIndex++] = element;
			upHeap(currentIndex-1);
		}

		
		private void upHeap(int index) 
		{
			if (index == 1)
			{
				return;
			}
			
			int parentIndex = index/2;
			if (arr[parentIndex] > arr[index])
			{
				swap(parentIndex, index);
				upHeap(parentIndex);
			}						
		}
		
		
		public int delete()
		{
			if(currentIndex == 1)
			{
				// throw exception
			}
			swap(1, currentIndex-1);
			currentIndex--;
			downHeap(1);
			int retVal = this.arr[currentIndex];			
			return retVal;
		}

		
		private void downHeap(int index) 
		{
			if (index == currentIndex-1)
			{
				return;
			}
			int minIndex = index;
			int lIndex = index*2;
			int rIndex = index*2+1;
			if ((lIndex < currentIndex)
					&& (arr[lIndex] < arr[index]))
			{
				minIndex = lIndex;
				if ((rIndex < currentIndex) 
					&& (arr[rIndex] < arr[minIndex]))
				{
					minIndex = rIndex;
				}
			}
			if (index != minIndex)
			{
				swap(index, minIndex);
				downHeap(minIndex);
			}
		}

		public int size()
		{
			return currentIndex-1;
		}
		
		private void swap(int i, int j)
		{
			int temp = arr[i];
			arr[i] = arr[j];
			arr[j] = temp;						
		}		
	}
	
	
	public int findMinimumCost(int[] arr)
	{
		MinHeap heap = new MinHeap(arr.length);
		for(int i = 0 ; i < arr.length ; i++)
		{
			heap.insert(arr[i]);
		}
		
		int result = 0;
		while(heap.size()!=1)
		{
			int a = heap.delete();
			int b = heap.delete();
			result += (a+b);
			heap.insert(a+b);
		}
		return result;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		int[] arr = {4,3,2,6};
        int result =  new ConnectNRopesWithMinimumCost().findMinimumCost(arr);
        System.out.println(result);
	}

}
