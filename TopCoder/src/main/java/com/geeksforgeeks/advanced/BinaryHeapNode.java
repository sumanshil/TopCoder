/**
 * 
 */
package com.geeksforgeeks.advanced;

/**
 * @author 607166161
 *
 */
public class BinaryHeapNode implements TreeNode {

	private TreeNode trieNode;
	private int index;
	
	
	public BinaryHeapNode(TreeNode trieNode, int index){
		this.trieNode = trieNode;
		this.index = index;
		trieNode.addPeer(this);
	}
	/* (non-Javadoc)
	 * @see com.geeksforgeeks.advanced.TreeNode#addChildren(java.lang.Object)
	 */
	public TreeNode addChildren(Object child) {
		// TODO Auto-generated method stub
        return null;
	}

	/* (non-Javadoc)
	 * @see com.geeksforgeeks.advanced.TreeNode#removeChildren()
	 */
	public void removeChildren() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.geeksforgeeks.advanced.TreeNode#getChild(java.lang.Object)
	 */
	public TreeNode getChild(Object key) {
		// TODO Auto-generated method stub
		return null;
	}
	public void addPeer(TreeNode node) {
		this.trieNode = node;		
	}
	public Object getQualifier() {
		return this.index;
	}
	public TreeNode getPeer() {
        return this.trieNode;
	}
	public int compareTo(TreeNode object) {
         return this.getPeer().compareTo(object.getPeer());
	}
	public void setQualifier(Object qualifier) {
        this.index = (Integer)qualifier;		
	}
	public void setParent(TreeNode parent) {
		// TODO Auto-generated method stub
		
	}
	public TreeNode getParent() {
		// TODO Auto-generated method stub
		return null;
	}
	public String print() {
		return this.getPeer().print();
	}
	public TreeNode[] getChildren() {
		// TODO Auto-generated method stub
		return null;
	}

}
