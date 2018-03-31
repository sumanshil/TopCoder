package com.geeksforgeeks.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by sshil on 3/25/2016.
 */
// http://www.geeksforgeeks.org/find-all-possible-trees-with-given-inorder-traversal/
public class FindAllPossibleBinaryTreesWithGivenInOrderTraversal {

	public void print(int[] arr){
		List<List<Integer>> result = calculateRecursive(arr, arr.length-1);
		result.stream().forEach( e -> e.stream().forEach(System.out::print));
	}

	private List<List<Integer>> calculateRecursive(int[] arr, int index) {
		if ( index < 0){
			return new ArrayList<>();
		}

		int element = arr[index];
		List<List<Integer>>  arrays = calculateRecursive(arr, index-1);
		List<List<Integer>> retVal = new ArrayList<>();
		if (arrays.size() == 0){
			List<Integer> list = new ArrayList<>();
			list.add(element);
			retVal.add(list);
			return retVal;
		} else {
			arrays.stream().forEach(e -> retVal.addAll(generateArrays(e, element)));
			return retVal;
		}
	}

	private List<List<Integer>> generateArrays(List<Integer> originalList, int element) {
		List<List<Integer>> retVal = new ArrayList<>();
		Integer[] array = new Integer[originalList.size()+1];
		for ( int i = array.length -1 ; i>= 0 ; i--){
			array[i] = element;
			for (Integer integer : originalList){
				for ( int j = 0 ; j < array.length ; j++){
					if (array[j] == null){
						array[j] = new Integer(integer);
						break;
					} else if (j+1 < array.length && array[j+1] == null){
						array[j+1] = new Integer(integer);
						j++;
						break;
					}
				}
			}
			retVal.add(Arrays.asList(array));
			array = new Integer[originalList.size()+1];
		}
		return retVal;
	}


	public static void main(String[] args) {
		int[] arr = {4, 5, 7};
		new FindAllPossibleBinaryTreesWithGivenInOrderTraversal().print(arr);
	}
}
