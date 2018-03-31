package com.careercup.amazon;

/**
 * Created by sshil on 12/10/2015.
 */
//http://www.careercup.com/question?id=5749172554694656

public class TreeTraversalInSpiralForm {

	static class TreeNode {
		private int data;
		private TreeNode left;
		private TreeNode right;

		public int getData() {
			return data;
		}

		public TreeNode(int data){
			this.data  = data;
		}

        public TreeNode setLeft(int data){
			this.left = new TreeNode(data);
			return this.left;
		}

		public TreeNode getRight() {
			return right;
		}

		public TreeNode getLeft() {
			return left;
		}

		public TreeNode setRight(int data){
			this.right = new TreeNode(data);
			return this.right;
		}

		public String toString(){
			return this.data + "";
		}
	}

	static class DoubleLinkedList{
		private DLNode start;
		private DLNode end;
		public DoubleLinkedList(){
			start = null;
			end = null;
		}

		public void add(TreeNode treeNode){
			add(new DLNode(treeNode));
		}


		public void add(DLNode data){
			if (start == null){
				start = data;
				end = start;
			} else {
				end.next = data;
				end.next.previous = end;
				end = end.next;
			}
		}

		public DLNode removeFromStart(){
			if (start != null) {
				DLNode retVal = start;
				start = start.getNext();
				return retVal;
			}
			return null;
		}

		public DLNode removeFromRear(){
			if (end != null){
				DLNode temp = end.getPrevious();
				end = end.getPrevious();
				return temp;
			}
			return null;
		}

	}

	static class DLNode {
		private TreeNode nodeValue;
		private DLNode next;
		private DLNode previous;
		public DLNode(){

		}
		public DLNode(TreeNode value){
			this.nodeValue = value;
		}

		public DLNode setNext(TreeNode value){
			DLNode newNode = new DLNode(value);
			this.next = newNode;
			this.next.previous = this;
			return this.next;
		}

		public DLNode getNext() {
			return next;
		}

		public void setNext(DLNode next) {
			this.next = next;
		}

		public TreeNode getData() {
			return nodeValue;
		}

		public void setData(TreeNode data) {
			this.nodeValue = data;
		}

		public DLNode getPrevious() {
			return previous;
		}

		public void setPrevious(DLNode previous) {
			this.previous = previous;
		}
	}


	static class BoundaryNode extends DLNode {
	}

	public void printSpiral(TreeNode root){
		DoubleLinkedList dl = new DoubleLinkedList();
		addTreeNodesInDL(dl, root);
		printFromBothTheEndsAlternatively(dl);
	}

	private void printFromBothTheEndsAlternatively(DoubleLinkedList dl) {
		DLNode start = dl.removeFromStart();
		DLNode end = dl.removeFromRear();
		while(true){
			if (start == end){
				break;
			}
			if (start instanceof BoundaryNode){
				start = dl.removeFromStart();
			}
			while( !(start instanceof BoundaryNode)) {
				System.out.print(start.getData().getData());
				start = dl.removeFromStart();
			}

			if (end instanceof BoundaryNode){
				end = dl.removeFromRear();
			}

			while (!(end instanceof BoundaryNode)){
				System.out.print(end.getData().getData()+" ");
				end = dl.removeFromRear();
			}
		}
	}

	private void addTreeNodesInDL(DoubleLinkedList start, TreeNode root) {
		start.add(new DLNode(root));
        start.add(new BoundaryNode());
		DLNode currentNode = start.start;
		boolean insertBoundary = false;
		while(true){
            if (currentNode == null){
				break;
			}
			if (insertBoundary){
				insertBoundary = false;
				start.add(new BoundaryNode());
			}
			if ( !(currentNode instanceof BoundaryNode) ) {
				TreeNode treeNode = currentNode.nodeValue;
				if (treeNode.getLeft() != null) {
					start.add(treeNode.getLeft());
				}
				if (treeNode.getRight() != null){
					start.add(treeNode.getRight());
				}
			} else {
				insertBoundary = true;
			}
			currentNode = currentNode.getNext();
		}
	}

	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		root.setLeft(2);
		root.setRight(3);
		root.getLeft().setLeft(4);
		root.getLeft().setRight(5);
		root.getRight().setLeft(6);
		root.getRight().setRight(7);
		root.getLeft().getLeft().setLeft(8);
		root.getLeft().getLeft().setRight(9);
		root.getLeft().getRight().setLeft(10);
		root.getLeft().getRight().setRight(11);
		root.getRight().getLeft().setLeft(12);
		root.getRight().getLeft().setRight(13);
		root.getRight().getRight().setLeft(14);
		root.getRight().getRight().setRight(15);
		new TreeTraversalInSpiralForm().printSpiral(root);
	}
}
