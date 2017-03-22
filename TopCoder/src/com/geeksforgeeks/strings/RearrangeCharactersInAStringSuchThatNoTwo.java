package com.geeksforgeeks.strings;

import java.util.HashMap;
import java.util.Map;

//http://www.geeksforgeeks.org/rearrange-characters-string-no-two-adjacent/
public class RearrangeCharactersInAStringSuchThatNoTwo {

	static class Member {
		private String s;
		private int frequnecy;
		
		public Member(String s, int frequency){
			this.s = s;
			this.frequnecy = frequency;
		}
	}
	
	static class MaxHeap {
		private Member[] array;
		private int maxSize;
		private int currentSize = -1;
		private Member previous;
		
		public MaxHeap( int n) {
			array = new Member[n];
			this.maxSize = n;
		}
		
		public void insert(Member m){
			this.currentSize++;
			upHeap(m, this.currentSize);
		}
				
		public Member remove(){
			Member retVal = this.array[0];
			swap(0, this.currentSize);
			this.currentSize--;
			downHeap(0);
			if (this.previous != null) {
				insert(this.previous);
			}
			if (retVal.frequnecy > 1){
				this.previous = retVal;
			}  else {
				this.previous = null;
			}
			retVal.frequnecy--;
			return retVal;
		}

		private void downHeap(int currentIndex) {
			if (currentIndex >= this.currentSize){
				return;
			}
			
			int left = currentIndex + 1;
			int right = currentIndex + 2;
			int largestIndex = currentIndex;
			if ( (left < currentSize) && array[currentIndex].frequnecy < array[left].frequnecy) {
				largestIndex = left;
			}
			
			if ( (right < currentSize) && array[largestIndex].frequnecy < array[right].frequnecy) {
				largestIndex = right;
			}
			if (currentIndex != largestIndex) {
				swap(currentIndex, largestIndex);
				downHeap(largestIndex);
			}
		}

		public boolean isEmpty(){
			return this.currentSize < 0;
		}
		
		private void upHeap(Member m, int currentIndex) {
			if (currentIndex == 0) {
				array[currentIndex] = m;
				return;
			}
			
			int parent = currentIndex / 2 ;
			if (array[parent].frequnecy < m.frequnecy) {
				swap(parent, currentIndex);
				upHeap(m, parent);
			} else {
				array[currentIndex] = m;
			}
		}
		
		private void swap(int parent, int currentIndex) {
			Member temp = array[parent];
			array[parent] = array[currentIndex];
			array[currentIndex] = temp;
		}		
	}
	
	public static void main(String[] args) {
		String str = "aaabc";
		Map<String, Integer> map = new HashMap<>();
		for ( int i = 0 ; i < str.length() ; i++ ) {
			String s = str.charAt(i)+"";
			if (!map.containsKey(s)){
				map.put(s, 1);
			} else {
				int count = map.get(s);
				map.put(s, count + 1);
			}
		}
		MaxHeap rearrange = new  RearrangeCharactersInAStringSuchThatNoTwo.MaxHeap(map.entrySet().size());
		
		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			rearrange.insert(new Member(entry.getKey(), entry.getValue()));
		}
		
		while (!rearrange.isEmpty()){
			System.out.println(rearrange.remove().s);
		}
	}
}
