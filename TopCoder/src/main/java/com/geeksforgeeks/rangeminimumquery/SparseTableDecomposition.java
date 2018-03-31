package com.geeksforgeeks.rangeminimumquery;
//https://www.youtube.com/watch?v=c5O7E_PDO4U
//http://www.geeksforgeeks.org/range-minimum-query-for-static-array/
//https://www.topcoder.com/community/data-science/data-science-tutorials/range-minimum-query-and-lowest-common-ancestor/
public class SparseTableDecomposition {
    
	private int[][] sparse = null;
    
	public void preprocess (int[] arr) {
		int n = arr.length;
		sparse = new int[n][new Double(Math.log(n)).intValue()+1];
		for ( int i = 0 ; i < n ; i ++) {
			sparse[i][0] = i;
		}

		for ( int j = 1; (1 << j) < n ; j++ ) {
			for ( int i = 0 ; ( i + (1<<(j-1)) ) < n ; i++) {
				int row =  i + (1<<(j-1));
				if ( arr[sparse[i][j-1]] < arr[ sparse[row][j-1]]){
					sparse[i][j] = sparse[i][j-1];
				} else {
					sparse[i][j] = sparse[i+ (1 << (j-1))][j-1];						
				}
			}
		}
	}
	
    private int query (int[] arr, int l, int r) {
    	int j = new Double(Math.log((r - l )+1)).intValue();
    	if ( arr[sparse[l][j-1]] < arr[sparse[l+ (1 << (j-1))][j-1]] ) {
    		return arr[sparse[l][j-1]];
    	} else {
    		return arr[sparse[l+ (1 << (j-1))][j-1]];
    	}
    }
	
	
	public static void main(String[] args) {
		int[] arr = {7, 2, 3, 0, 5, 10, 3, 12, 18};
		SparseTableDecomposition sparseTableDecomposition = new SparseTableDecomposition();
		sparseTableDecomposition.preprocess(arr);
		int small = sparseTableDecomposition.query(arr, 0, 4);
		System.out.println(small);
	}

}
