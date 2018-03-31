package com.geeksforgeeks.array;

import java.util.ArrayList;
import java.util.List;

public class SortKSortedArray {

	static class BinaryHeap{
		int index;
		int size;
		int[] arr;
		public BinaryHeap(int size){
			arr = new int[size+1];
			this.size = size;
			index = 0;
		}
		
		public void add(int element){
			if (index < size){
				arr[++index] = element;
				upHeap(index);
//				if (index < size)
//					index++;
			}
		}
		
		public int remove(){
			int retVal =  arr[1];
			arr[1] = arr[index];
			index--;
			downHeap(1);
			return retVal;
		}
		
		private void downHeap(int index) {
            int leftIndex = 2*index ;
            int rightIndex = 2*index + 1;
            
            if (leftIndex < (this.index+1)){
            	int smallerIndex = index;
            	if (arr[smallerIndex] > arr[leftIndex]){
            		smallerIndex = leftIndex;
            	}
            	if (rightIndex < (this.index+1) && arr[smallerIndex] > arr[rightIndex]){
            		smallerIndex = rightIndex;
            	}
            	if(smallerIndex != index){
	            	swap (smallerIndex,index);
	            	downHeap(smallerIndex);
            	}
            }
		}

		public void upHeap(int index){
			int parentIndex = index /2;
			if(parentIndex != 0){
				if (arr[parentIndex] > arr[index]){
					swap(parentIndex, index);
					upHeap(parentIndex);
				}
			}
		}

		private void swap(int parentIndex, int index) {
			int temp = arr[parentIndex];
			arr[parentIndex] = arr[index];
			arr[index] = temp;			
		}
		
	}
	
	static class ArrayHeader{
		public List<Integer> header;
		public int currentIndex = 0;
		public ArrayHeader(List<Integer> ref, int index){
			this.header = ref;
			this.currentIndex = index;
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		int[] arr = {5,4,3}; 
//        SortKSortedArray.BinaryHeap heap = new SortKSortedArray.BinaryHeap(3);
//        for(int i = 0 ; i < arr.length ; i++){
//        	heap.add(arr[i]);        	
//        }
//        
//        for(int i = 0 ; i < 3; i++){
//        	System.out.println(heap.remove());
//        }
//        
        SortKSortedArray.BinaryHeap heap = new SortKSortedArray.BinaryHeap(3);
        int k = 3;
        int n = 4;
        ArrayHeader[] ref= new ArrayHeader[k];
        List<Integer> l1 = new ArrayList<Integer>();
        l1.add(2);
        l1.add(6);
        l1.add(12);
        l1.add(34);
        ref[0] = new ArrayHeader(l1, 0);
        
        List<Integer> l2 = new ArrayList<Integer>();
        l2.add(1);
        l2.add(9);
        l2.add(20);
        l2.add(1000);
        ref[1] = new ArrayHeader(l2, 0);
        
        List<Integer> l3 = new ArrayList<Integer>();
        l3.add(23);
        l3.add(34);
        l3.add(90);
        l3.add(2000);
        ref[2] = new ArrayHeader(l3, 0);
        
        for(int i = 0 ; i < 3; i++){
             heap.add(ref[i].header.get(0));
        }
        
        int[] arr = new int[n*k];
        int index = 0;
        for(int i = 0 ; i < n*k ; i++){
        	int min = heap.remove();
        	System.out.println(min);
        	arr[index++] = min;        	
        	for(int j = 0 ; j < k ; j++ ){
        		if (ref[j].currentIndex < ref[j].header.size() && min == ref[j].header.get(ref[j].currentIndex)){
        			if (ref[j].currentIndex == ref[j].header.size()-1){
        				heap.add(Integer.MAX_VALUE);
        			    (ref[j].currentIndex)++;
        			    break;
        			} else {	
        				heap.add(ref[j].header.get(++(ref[j].currentIndex)));
        				break;
        			}	
        		}
//        		else if (min == ref[j].header.get(ref[j].currentIndex) && (ref[j].currentIndex == ref[j].header.size() -1)){
//        			heap.add(ref[j].header.get(Integer.MAX_VALUE));
//        		}
        	}
        }        
	}

}
