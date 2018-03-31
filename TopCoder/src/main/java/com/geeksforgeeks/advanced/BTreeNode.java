package com.geeksforgeeks.advanced;
//http://www.cs.utexas.edu/users/djimenez/utsa/cs3343/lecture17.html
public class BTreeNode {
    public int[] keys;
    public int t;//Minimum degree (defines the range for number of keys)
    public BTreeNode[] children;//aray of children
    public int n; // number of keys
    public boolean leaf;
	public int[] getKeys() {
		return keys;
	}
	public void setKeys(int[] keys) {
		this.keys = keys;
	}
	public int getT() {
		return t;
	}
	public void setT(int t) {
		this.t = t;
	}
	public BTreeNode[] getChildren() {
		return children;
	}
	public void setChildren(BTreeNode[] children) {
		this.children = children;
	}
	public int getN() {
		return n;
	}
	public void setN(int n) {
		this.n = n;
	}
	public boolean isLeaf() {
		return leaf;
	}
	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}
	public BTreeNode(int _t, boolean _leaf){
		this.t = _t;
		this.leaf = _leaf;
		n = 0;
		keys = new int[2*t - 1];
		children = new BTreeNode[2*t];
	}
	
	public BTreeNode search(int k){
		int i = 0;
		while(i < n && k> keys[i]){
		    i++;
		}
		if(keys[i] == k)
			return this;
		
		if (this.leaf == true)
			return null;
		BTreeNode retVal=  children[i].search(k);
		return retVal;
	}
	
	public void traverse(){
		// There are n keys and n+1 children, traverse through n keys
	    // and first n children		
		int i;
		for(i = 0 ; i < n ; i++){
			if(this.leaf = false){
				children[i].traverse();
			}
			System.out.print(" "+this.keys[i]);
		}
		 // Print the subtree rooted with last child
		if(this.leaf == false){
			children[i].traverse();
		}
	}
	
	// A utility function to split the child y of this node
	// Note that y must be full when this function is called
	public void splitChild(int i, BTreeNode y){
		// currently y has (2*t - 1) or ((t-1)+1+(t-1)) keys
		// Create a new node which is going to store the last (t-1) keys
	    // of y. y will contain the first (t-1) nodes. and the middle key will go to root;
		
		BTreeNode z = new BTreeNode(t, false);
		z.n = t-1;
		// copy last (t-1) keys from y to z
		for(int j = 0; j < t-1; j++ ){
			z.keys[j] = y.keys[j+t];
		}
		
		// copy last t children from y to z
		for(int j = 0 ; j < t ; j++){
			z.children[j] = y.children[j+t];
		}
		
		y.n = t-1;
		
		// Since this node is going to have a new child,
	    // create space of new child
		for(int j =n ; j>=i+1;j--){
		    children[j+1] = children[j];
		}
		// Link the new child to this node
		children[i+1] = z;
		
		// A key of y will move to this node. Find location of
	    // new key and move all greater keys one space ahead		
		
		for (int j = n-1; j >= i; j--)
	        keys[j+1] = keys[j];
		
		// Copy the middle key of y to this node
	    keys[i] = y.keys[t-1];
	    
	    // Increment count of keys in this node
	    n = n + 1;	    
	}
    public void insertNonFull(int k) {
        int i = n-1;
        if(this.leaf){
            // The following loop does two things
            // a) Finds the location of new key to be inserted
            // b) Moves all greater keys to one place ahead            
            while(i>=0 && this.keys[i]> k){
                this.keys[i+1] = this.keys[i];
                i--;
            }
            this.keys[i+1] = k;
            this.n = this.n+1;
        } else {
            // If this node is not leaf
            // Find the child which is going to have the new key
            while(i >=0 && this.keys[i] > k){
                i--;
            }
            if(children[i+1].n == ((2*t)-1)){
                // If the child is full, then split it
                splitChild(i+1, children[i+1]);
                // After split, the middle key of C[i] goes up and
                // C[i] is splitted into two.  See which of the two
                // is going to have the new key
                if (keys[i+1] < k)
                    i++;                
            }
            children[i+1].insertNonFull(k);
        }
    }
}
