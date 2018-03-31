package com.geeksforgeeks.tree;

/**
 * Created by sshil on 1/25/2016.
 */
//http://www.geeksforgeeks.org/segment-tree-set-1-sum-of-given-range/
public class SegmentTree {
	static class SegmentTreeNode{
		int data;
		SegmentTreeNode left;
		SegmentTreeNode right;
		int lowRange;
		int highRange;

		public SegmentTreeNode(int data, int low, int high) {
			this.data = data;
			this.lowRange = low;
			this.highRange = high;
		}

	}

	public void printInOrder(SegmentTreeNode segmentTreeNode){
		if(segmentTreeNode != null){
			printInOrder(segmentTreeNode.left);
			System.out.println(segmentTreeNode.data);
			printInOrder(segmentTreeNode.right);
		}

	}

	private SegmentTreeNode root = null;
	public void buildTree(int[] arr) {
		SegmentTreeNode root = buildRecursive(arr, 0, arr.length-1);
		//this.root =
		printInOrder(root);
	}



	private SegmentTreeNode buildRecursive(int[] arr, int start, int end) {
		if (start == end){
			return new SegmentTreeNode(arr[start], start, end);
		}

		int mid = (start+end)/2;
		SegmentTreeNode leftNode = buildRecursive(arr, start, mid);
		SegmentTreeNode rightNode = buildRecursive(arr, mid+1, end);

		SegmentTreeNode root = new SegmentTreeNode(leftNode.data+rightNode.data, start, end);
		root.left = leftNode;
		root.right = rightNode;
		return root;
	}

	public static void main(String[] args) {
		int[] arr = {1, 3, 5, 7, 9, 11};
		new SegmentTree().buildTree(arr);
	}
}
