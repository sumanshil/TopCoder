package com.geeksforgeeks.advanced;

public class TrieNode implements TreeNode {

	private TrieNode[] children;
	private int count;
	protected char c;
    public char getC() {
		return c;
	}

	public void setC(char c) {
		this.c = c;
	}

	private TreeNode peerNode;
    private TreeNode parentNode;
	public TrieNode(){
		children = new TrieNode[26];
		this.count = 0;
	}

	public TrieNode(char c){
		children = new TrieNode[26];
		this.count = 0;
		this.c = c;
	}
	
	public TreeNode addChildren(Object child) {
		 String str = (String)child;
		 TreeNode[] children = this.children;
		 TreeNode parent = this;
         for(int i = 0 ; i < str.length()-1; i++ ){
        	 int value = str.charAt(i)-97;
        	 if(children[value] == null){
        		 children[value] = new TrieNode(str.charAt(i));
        		 children[value].setParent(parent);
        	 }
        	 parent = children[value];
        	 children = ((TrieNode)children[value]).children;
         }
         
         if (children[str.length()-1] == null){
        	 children[str.length()-1] = new TrieNode(str.charAt(str.length()-1));
        	 children[str.length()-1].setParent(parent);
         }
         ((TrieNode)children[str.length()-1]).count++;
         return children[str.length()-1];
	}

	public void removeChildren() {
		// TODO Auto-generated method stub

	}

	public TreeNode getChild(Object key) {
		// TODO Auto-generated method stub
		return null;
	}


	public Iterator iterator(){
		return new Iterator() {
			int childCount = 0;
			public TrieNode next() {
				return children[childCount++];
			}
			
			public boolean hasNext() {
				return childCount < 26;
			}
		};
	}
	
	public static void main(String[] args){
		
	}

	public void addPeer(TreeNode node) {
		this.peerNode = node;		
	}

	public Object getQualifier() {
		return count;
	}

	public TreeNode getPeer() {
       return  this.peerNode;
	}

	public int compareTo(TreeNode trieNode) {
		if ( this.count > (Integer)trieNode.getQualifier())
			return 1;
		else if ( this.count < (Integer)trieNode.getQualifier()){
			return -1;
		}
		return 0;
	}

	public String print(){
		StringBuffer sb = new StringBuffer();
		printContent(this, sb);
		return sb.toString();
	}
	public void printContent(TreeNode node, StringBuffer sb){
		if (node == null || ((TrieNode)node).c == 'X'){
			return ;			
		}
		char c = ((TrieNode)node).c;
		printContent(node.getParent(), sb);
		sb.append(c);
	}
	public void setQualifier(Object qualifier) {
		// TODO Auto-generated method stub
		
	}

	public void setParent(TreeNode parent) {
        this.parentNode = parent;	
	}

	public TreeNode getParent() {
		return parentNode;
	}
	
	public TreeNode[] getChildren(){
		return this.children;
	}
}
