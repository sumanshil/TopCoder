package com.geeksforgeeks.tree.niary;



//http://www.geeksforgeeks.org/diameter-n-ary-tree/
public class DiameterOfANiaryTree<T> {

	public void findDiameter (NiaryTreeNode<T> root){
		int diameter = recursiveDiameter(root);
		System.out.println(diameter);
	}
	
	
	private int recursiveDiameter(NiaryTreeNode<T> root) {
		if (root == null) {
			return 0;
		}
		if (root.isLeaf()){
			return 1;
		}
		int firstMaxDepth = 0;
		int secondMaxDepth = 0;
		
		for ( int i = 0 ; i < root.getChildren().length ; i++) {
			int diameter = depth(root.getChildAtIndex(i));
			if (diameter > firstMaxDepth){
				secondMaxDepth = firstMaxDepth;
				firstMaxDepth = diameter;				
			} else if (diameter <= firstMaxDepth && diameter >= secondMaxDepth) {
				secondMaxDepth = diameter;
			}
		}		
		int totalDepth = firstMaxDepth + secondMaxDepth+1;
		
		int maxDiameter = Integer.MIN_VALUE;
		for ( int i = 0 ; i < root.getChildren().length ; i++) {
			int diameter = recursiveDiameter(root.getChildAtIndex(i));
			if (diameter > maxDiameter){
				maxDiameter = diameter;
			}
		}		
		return Math.max(maxDiameter, totalDepth);
	}


	private int depth(NiaryTreeNode<T> root) {
		if (root == null) {
			return 0;
		}
		if (root.isLeaf()){
			return 1;
		}
		int max = Integer.MIN_VALUE;
		for ( int i = 0 ; i < root.getChildren().length ; i++) {
			int d = depth(root.getChildAtIndex(i));
			max = Integer.max(max, d);
		}
		return max + 1;
	}

	
	public static void main(String[] args){
		NiaryTreeNode<String> root = new NiaryTreeNode<String>("A", 3);
		root.createChildNode(new int[]{0}, 3, "B");
		root.createChildNode(new int[]{1}, 3, "C");
		root.createChildNode(new int[]{2}, 2, "D");
		
		root.createChildNode(new int[]{0, 0}, 0, "E");
		root.createChildNode(new int[]{0, 1}, 0, "F");
		root.createChildNode(new int[]{0, 2}, 2, "G");

		root.createChildNode(new int[]{0, 2, 0}, 1, "H");
		root.createChildNode(new int[]{0, 2, 1}, 0, "I");

		root.createChildNode(new int[]{0, 2, 0, 0}, 0, "J");

		root.createChildNode(new int[]{2, 0}, 1, "K");
		root.createChildNode(new int[]{2, 1}, 1, "L");
		root.createChildNode(new int[]{2, 0, 0}, 0, "M");
		
		root.printRootToLeaf();
		new DiameterOfANiaryTree<String>().findDiameter(root);
	}
}
