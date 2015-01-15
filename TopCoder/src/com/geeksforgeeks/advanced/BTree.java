package com.geeksforgeeks.advanced;

public class BTree {
    BTreeNode root;// Pointer to root node
    int t;  // Minimum degree
    
    public BTree(int _t){
    	this.root = null;
    	this.t = _t;
    }
    
    public void insert(int k){
    	if (root == null){
    		root = new BTreeNode(this.t, true);
    		root.n = 1;
    		root.keys[0] = k;
    	} else if (root.n == ((2* this.t)-1)) {
    		BTreeNode newNode = new BTreeNode(this.t, false);
    	    newNode.children[0] = root;
    	    newNode.splitChild(0, root);
       	    // New root has two children now.  Decide which of the
            // two children is going to have new key
    	    int i = 0 ;
    	    if (newNode.keys[0] < k){
    	        i++;
    	    }
    	    newNode.children[i].insertNonFull(k);
    	    root = newNode;
    	} else {
    	    root.insertNonFull(k);
    	}
    }
}
