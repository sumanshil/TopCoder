package com.geeksforgeeks.tree;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

/**
 * Created by sshil on 12/14/2015.
 */
//http://www.geeksforgeeks.org/find-multiplication-of-sums-of-data-of-all-leaves-at-sane-levels/
public class FindMultiplicationOfDataOfTreeOfSameLevel {

	static class TreeNode {
		private int data;
		private TreeNode left;
		private TreeNode right;

		public TreeNode(int data) {
			this.data = data;
		}

		public TreeNode() {

		}

		public TreeNode getLeft() {
			return left;
		}

		public void setLeft(TreeNode left) {
			this.left = left;
		}

		public TreeNode getRight() {
			return right;
		}

		public void setRight(TreeNode right) {
			this.right = right;
		}

		public int getData() {
			return data;
		}

		public void setData(int data) {
			this.data = data;
		}
	}

	static class BoundaryNode extends TreeNode {

		public BoundaryNode(){
			super();
		}
		public BoundaryNode(int data) {
			super(data);
		}
	}

	public int calculateRecursive(TreeNode treeNode){
        int height = calculateHeight(treeNode);
		int multiplication = 1;

		for ( int i = 0 ; i < height ; i++) {
			int sum = getSumAtLevel(i, treeNode, 0);
			if (sum > 0) {
				multiplication *= sum;
			}
		}
		return multiplication;
	}

	private int getSumAtLevel(int targetLevel, TreeNode root, int currentLevel) {
		if (root == null){
			return  0;
		}
		if (currentLevel == targetLevel) {
			if (isLeaf(root)) {
				return root.getData();
			}
		}
		int lSum = getSumAtLevel(targetLevel, root.getLeft(), currentLevel+1);
		int rSum = getSumAtLevel(targetLevel, root.getRight(), currentLevel+1);
		return lSum + rSum;
	}

	private boolean isLeaf(TreeNode root) {
		return root.getLeft() == null && root.getRight() == null;
	}

	private int calculateHeight(TreeNode treeNode) {
		if (treeNode == null){
			return 0;
		}
		int lHeight = calculateHeight(treeNode.getLeft());
		int rHeight = calculateHeight(treeNode.getRight());
		return lHeight < rHeight ? rHeight+1 : lHeight+1;
	}

	public int calculate(TreeNode root) {
		Queue<TreeNode> list = new LinkedList<>();
		list.add(root);
		list.add(new BoundaryNode());
		int multiplication = 1;
		int sum = 0;
		while (true) {
			TreeNode node;
			try {
				node = list.remove();
			} catch (NoSuchElementException e){
				break;
			}
			if ( node != null ) {
				if (!(node instanceof BoundaryNode)) {
					if (node.getLeft() == null && node.getRight() == null) {
						sum += node.getData();
					}
					if (node.getLeft() != null){
						list.add(node.getLeft());
					}

					if (node.getRight() != null){
						list.add(node.getRight());
					}

				} else {
					if (sum > 0) {
						multiplication *= sum;
					}
					sum = 0;
					if (list.size() >= 1){
						list.add(new BoundaryNode());
					}
				}
			}
		}
		return multiplication;
	}
	public static void main(String[] args) {
		TreeNode root = new TreeNode(2);
		root.setLeft(new TreeNode(7));
		root.setRight(new TreeNode(5));

		root.getLeft().setLeft(new TreeNode(8));
		root.getLeft().setRight(new TreeNode(6));
		root.getLeft().getRight().setLeft(new TreeNode(1));
		root.getLeft().getRight().setRight(new TreeNode(11));

		root.getRight().setRight(new TreeNode(9));
		root.getRight().getRight().setLeft(new TreeNode(4));
		root.getRight().getRight().setRight(new TreeNode(10));

		int result = new FindMultiplicationOfDataOfTreeOfSameLevel().calculateRecursive(root);
		System.out.println(result);
	}
}
