package com.geeksforgeeks.tree;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//http://www.geeksforgeeks.org/check-binary-tree-contains-duplicate-subtrees-size-2/
public class CheckBinaryTreeContainsSubStringSerialized {

	private Map<String, Boolean> map 
		= new HashMap<String, Boolean>();
	
	public void find (BinaryTreeNode root) {
		buildMapRecursively(root);
		
		List<String> list = map.entrySet().stream()
		.filter(e -> e.getValue()== true)
		.map(e -> e.getKey()).collect(Collectors.toList());
		
		if (list.size() > 0){
			System.out.println("Duplicate sub tree exists");
		} else {
			System.out.println("Duplicate sub tree does not exist");
		}
	}
	
	private String buildMapRecursively(BinaryTreeNode root) {
		if (root == null) {
			return "";
		}
		
		String leftStr = buildMapRecursively(root.getLeft());
		String rightStr = buildMapRecursively(root.getRight());
		String finalStr = leftStr+root.getData()+rightStr;
		if (finalStr.length() > 0 && map.get(finalStr) == null){
			map.put(finalStr, false);			
		} else if (finalStr.length()> 0) {
			map.put(finalStr, true);
		}
		return finalStr;
	}

	public static void main(String[] args){
		BinaryTreeNode root = new BinaryTreeNode(1);
		root.insertInLeft(2);		
		root.insertInRight(3);
		
		root.getLeft().insertInLeft(4);
		root.getLeft().insertInRight(5);
		
		root.getRight().insertInRight(2);		
		root.getRight().getRight().insertInLeft(4);
		root.getRight().getRight().insertInRight(6);
		
		new CheckIfBinaryTreeContainsSubTree().find(root);	
	}
}
