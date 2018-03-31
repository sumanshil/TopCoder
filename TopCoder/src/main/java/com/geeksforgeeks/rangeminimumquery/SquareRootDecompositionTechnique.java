package com.geeksforgeeks.rangeminimumquery;
//http://www.geeksforgeeks.org/sqrt-square-root-decomposition-technique-set-1-introduction/
public class SquareRootDecompositionTechnique {

	private int SQRSIZE = 100;
	private int[] arr = null; //original array
	private int blockSize;
	private int[] blocks = new int[SQRSIZE];
	private int blockIndex = 0;
	
	public void update ( int index, int value) {
		int blockIndex = index / blockSize;
		int sum = blocks[blockIndex];
		int newSum = sum - arr[index] + value;
		arr[index] = value;
		blocks[blockIndex] = newSum;
	}
	
	public int query (int l, int r) {
		int sum = 0;
		while ( l < r && l % blockSize != 0 && l > 0 ) {
			sum += arr[l];
			l++;
		}
		
		while (l + blockSize <= r){
			sum += blocks[l/blockSize];
			l =l + blockSize;
		}
		
		while ( l <= r) {
			sum += arr[l];
			l++;
		}
		return sum;
	}
	
	public void preprocess (int[] arr) {
		this.arr = new int[arr.length];
		blockSize = new Double(Math.sqrt(arr.length)).intValue();
		int block_index = -1;
		for ( int i = 0 ; i < arr.length ; i++) {
			this.arr[i] = arr[i];
			if ( i % blockSize == 0) {
				block_index++;
			}
			blocks[block_index] += arr[i];
		}
		this.blockIndex = block_index;
	}
	
	public void printBlocks(){
		for ( int i = 0 ; i <= this.blockIndex ; i++ ) {
			System.out.println(blocks[i]);
		}
	}
	
	public static void main(String[] args) {
	//	System.out.println(Math.sqrt(10));
		int[] input = {1, 5, 2, 4, 6, 1, 3, 5, 7, 10};
		SquareRootDecompositionTechnique instance = new SquareRootDecompositionTechnique();
		instance.preprocess(input);
		instance.printBlocks();
		System.out.println(instance.query(3, 8));
		System.out.println(instance.query(1, 6));
		instance.update(8, 0);
		System.out.println(instance.query(8, 8));

	}

}
