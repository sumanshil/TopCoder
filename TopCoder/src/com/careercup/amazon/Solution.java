package com.careercup.amazon;

import java.io.*;
public class Solution {
    public static void main(String args[] ) throws Exception {
        int[][] matrix = 
        {
        {4,3},
        {1,2,3,4},
        {5,6,7,8},
        {9,10,11,12}
        };
        
        int row = matrix[0][1];
        int col = matrix[0][0];
        
        for(int i = 0 ; i < matrix[1].length ; i++)
        {
        	int j = 1;
        	int k = i;
        	while(j <= row && j> 0 && k> 0)
        	{
        		System.out.print(matrix[j][k]);
        		j--;
        		k--;
        	}
        	System.out.println();
        }
        
        
    }
}