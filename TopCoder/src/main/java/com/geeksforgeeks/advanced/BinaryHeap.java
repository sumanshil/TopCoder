package com.geeksforgeeks.advanced;

public class BinaryHeap {

	private TreeNode[] binaryHeapNodes ;
	private int maxSize;
	private int currentIndex; 
	public BinaryHeap(int size){
		this.maxSize = size;
		binaryHeapNodes = new BinaryHeapNode[size+1];
		currentIndex = 1;
	}
	
	public void add(TreeNode node){
		if (node.getPeer() == null){
			if (currentIndex <= maxSize){
				binaryHeapNodes[currentIndex] = new BinaryHeapNode(node, currentIndex);
				upheap(currentIndex++);
			} else {
				if (binaryHeapNodes[1].getPeer().compareTo(node)< 0){
					binaryHeapNodes[1] = node;
					downHeap(1);
				}
			}
		} else {
			// we already have a binary heap node for this trie node. so just down heap it as count has changed
			int index = (Integer)node.getPeer().getQualifier();
			downHeap(index);
		}
	}
	
	private void downHeap(int index) {
		int leftIndex = 2*index;
		int rightIndex = 2*index + 1;
		int minIndex = index;
		if (leftIndex  < currentIndex && leftIndex <= maxSize){
			if (binaryHeapNodes[index].compareTo(binaryHeapNodes[leftIndex]) > 0)
			minIndex = leftIndex;
			if (rightIndex  < currentIndex && rightIndex <= maxSize){
				if (binaryHeapNodes[minIndex].compareTo(binaryHeapNodes[rightIndex]) >0){
					minIndex = rightIndex;
				}
			}
			
			if (index != minIndex){
				swap(index, minIndex);
				downHeap(minIndex);
			}
		}
		
	}

	private void upheap(int index) {
	    int parentIndex = index/2;
	    if(binaryHeapNodes[parentIndex] != null){
	    	if (binaryHeapNodes[parentIndex].getPeer().compareTo( binaryHeapNodes[index].getPeer())>0){
	    		swap(index, parentIndex);
	    	}
	    	upheap(parentIndex);
	    }
	    
	}

	private void swap(int index, int parentIndex) {
		TreeNode temp = binaryHeapNodes[index];
		binaryHeapNodes[index] = binaryHeapNodes[parentIndex];
		binaryHeapNodes[parentIndex] = temp;
		binaryHeapNodes[parentIndex].setQualifier(parentIndex);
		binaryHeapNodes[index].setQualifier(index);
	}

	public void printContent(){
		for(int i = 1 ; i <= maxSize ; i++){
			System.out.println(binaryHeapNodes[i].getPeer().print()+"  "+ binaryHeapNodes[i].getPeer().getQualifier());
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
