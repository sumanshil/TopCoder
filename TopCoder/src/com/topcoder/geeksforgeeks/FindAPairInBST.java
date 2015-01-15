package com.topcoder.geeksforgeeks;

import java.util.Stack;

import com.topcoder.geeksforgeeks.BinarySearchTree.TreeNode;

public class FindAPairInBST {
	public void findPairRecursive(TreeNode root1, TreeNode root2, int target){
		if (root1 == null || root2 == null ){
			return;
		}
			
		int min = root1.data;
		int max = root2.data;
		if (min+max == target){
			System.out.println(min +" "+max);
			return;
		} else if ((min + max) > target){
			findPairRecursive(root1.left, root2, target);
		} else if ((min + max) < target){
			findPairRecursive(root1, root2.right, target);
		}
		
	}
	
	public void findPair(TreeNode root, int target){
		Stack<TreeNode> s1 = new Stack<TreeNode>();
		Stack<TreeNode> s2 = new Stack<TreeNode>();
		boolean done1 = false, done2 = false;
		TreeNode curr1 = root;
		TreeNode curr2 = root;
		int val1 = 0, val2 = 0;
		while(true){
			
			while(!done1){
				if (curr1 != null){
					s1.push(curr1);
					curr1 = curr1.left;
				} else {
					if (s1.isEmpty()){
						done1 = true;
					}else{
						TreeNode temp = s1.pop();
						val1 = temp.data;
						curr1 = temp.right;
						done1 = true;
					}
				}
			}

			while(!done2){
				if (curr2 != null){
					s2.push(curr2);
					curr2 = curr2.right;
				} else {
					if (s2.isEmpty()){
						done2 = true;
					}else{
						TreeNode temp = s2.pop();
						val2 = temp.data;
						curr2 = temp.left;
						done2 = true;
					}
				}
			}
			
			if (val1 + val2 == target){
				System.out.println("Sum found " + val1 +" "+val2);
				break;
			}
			
			if ((val1 + val2) < target){
				done1 = false;
			}
			
			if ((val1 + val2) > target){
				done2 = false;
			}
			
			if ((curr1 == null &&(s1.isEmpty())) || (curr2 == null && s2.isEmpty())){
				System.out.println("No pair found");
				break;
			}
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        bst.insert(6);
        bst.insert(-13);
        bst.insert(14);
        bst.insert(-8);
        bst.insert(15);
        bst.insert(13);
        bst.insert(7);
        new FindAPairInBST().findPair(bst.getRoot(), 5);
        new FindAPairInBST().findPairRecursive(bst.getRoot(), bst.getRoot(), 5);
	}

}
