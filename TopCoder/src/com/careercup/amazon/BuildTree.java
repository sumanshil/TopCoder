package com.careercup.amazon;

import java.util.ArrayList;
import java.util.List;

public class BuildTree {
    private static class TestTree {
    	static class TreeNode
    	{
    		public TreeNode(int j) {
    			this.i = j;
    		}
    		int i;
    		TreeNode left;
    		TreeNode right;
    		
    	}

    	int[] elements;
    	List<Integer> inorder;
        TestTree(int[] treeNodes){
        	elements= treeNodes;
        	inorder = new ArrayList<Integer>();
        	buildTree();
        }
        TreeNode root ;
        void buildTree()
        {        
        	if (elements.length > 0)
        	{
        		root = buildTreeRecursive(0);
        	}
        }
        
        private TreeNode buildTreeRecursive( int i) {
			if (i >= elements.length || elements[i] == -1)
			{
				return null;
			}
			TreeNode root = new TreeNode(elements[i]);
			root.left = buildTreeRecursive( 2*i+1);
			root.right = buildTreeRecursive( 2*i+2);
			return root;
		}
		/**
        * Returns the inOrder Traversal of the Tree elements.
        */
        
        int[] inOrderTraversal() {
        	inOrderTraversalRecursive(this.root);
        	Integer[] result =  (Integer[])inorder.toArray(new Integer[0]);
        	int[] r = new int[result.length];
        	for(int i = 0 ; i < result.length; i++)
        	{
        		r[i] = result[i];
        	}
        	return r;
        }

		private void inOrderTraversalRecursive(TreeNode root) {
            if ( root != null)
            {
            	inOrderTraversalRecursive(root.left);
            	inorder.add(root.i);
            	inOrderTraversalRecursive(root.right);
            }			
		}
    }
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] arr = {1,3,-1,7,-1,-1,-1,-1,-1};
		TestTree tree = new TestTree(arr);
        int[] r = tree.inOrderTraversal();
        for(int i : r)
        {
        	System.out.println(i);
        }
	}

}
