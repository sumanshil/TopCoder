package com.geeksforgeeks.tree;

/**
 * Created by sshil on 1/9/2016.
 */
//http://www.geeksforgeeks.org/locking-and-unlocking-of-resources-in-the-form-of-n-ary-tree/
public class NaryTreeLocking {

	private static final int MAX_CHILD_COUNT = 5;

	static class Node {
		private int data;
		private Node[] children;
		private Node parentPointer;
		private int lockCountOfDescendants;
		private boolean isLocked;

		public Node(int data){
			this.data = data;
			children  = new Node[MAX_CHILD_COUNT];
		}

		public void addChild(Node child) {
			for ( int i = 0 ; i < children.length ; i++) {
				if (children[i] == null){
					children[i] = child;
					child.parentPointer = this;
				}
			}
		}

		private void increaseLockCountForDescendants(){
			lockCountOfDescendants++;
		}

		private void decreaseLockCountForDescendants(){
			lockCountOfDescendants--;
		}

		public synchronized boolean isLocked(){
			return isLocked;
		}

		public synchronized boolean lock(){
			boolean isAnyOfParentLocked = checkIfAncestorLockedRecursively(this.parentPointer);
			if (isAnyOfParentLocked){
				return false;
			}
			if (lockCountOfDescendants > 0){
				return false;
			}
			isLocked = true;
			updateChildLockCountOfAncestorsRecursively(this.parentPointer);
			return true;
		}

		private boolean checkIfAncestorLockedRecursively(Node parentPointer) {
			if (parentPointer == null){
				return  false;
			}
			if (parentPointer.isLocked){
				return true;
			} else {
				return  checkIfAncestorLockedRecursively(parentPointer.parentPointer);
			}

		}

		private void updateChildLockCountOfAncestorsRecursively(Node parentPointer) {
			if (parentPointer == null) {
				return;
			}
			parentPointer.increaseLockCountForDescendants();
			updateChildLockCountOfAncestorsRecursively(parentPointer.parentPointer);
		}

		public boolean unlock(){
			synchronized (this) {
				if (isLocked) {
					isLocked = false;
					decreaseChildLockCountOfAncestorsRecursively(this.parentPointer);
					return true;
				}
			}
			return false;
		}

		private void decreaseChildLockCountOfAncestorsRecursively(Node parentPointer) {
			if (parentPointer == null){
				return;
			}
			parentPointer.decreaseLockCountForDescendants();
			decreaseChildLockCountOfAncestorsRecursively(parentPointer.parentPointer);
		}

		public int getData() {
			return data;
		}

		public void setData(int data) {
			this.data = data;
		}

		@Override
		public boolean equals(Object obj){
			if (!(obj instanceof  Node)){
				return  false;
			}
			Node n = (Node) obj;
			return n.data == this.data;
		}

		@Override
		public String toString(){
			return data+"";
		}
	}

     ////         1
	 //     2         3         4              5
//        6 7 8    9 10 11   12 13  14 15   16 17 18 19
	public static void main(String[] args) {
		Node n1 = new Node(1);
		Node n2 = new Node(2);
		Node n3 = new Node(3);
		Node n4 = new Node(4);
		Node n5 = new Node(5);
		Node n6 = new Node(6);
		Node n7 = new Node(7);
		Node n8 = new Node(8);
		Node n9 = new Node(9);
		Node n10 = new Node(10);
		Node n11 = new Node(11);
		Node n12 = new Node(12);
		Node n13 = new Node(13);
		Node n14 = new Node(14);
		Node n15 = new Node(15);
		Node n16 = new Node(16);
		Node n17 = new Node(17);
		Node n18 = new Node(18);
		Node n19 = new Node(19);

		n1.addChild(n2);
		n1.addChild(n3);
		n1.addChild(n4);
		n1.addChild(n5);

		n2.addChild(n6);
		n2.addChild(n7);
		n2.addChild(n8);

		n3.addChild(n9);
		n3.addChild(n10);
		n3.addChild(n11);

		n4.addChild(n12);
		n4.addChild(n13);
		n4.addChild(n14);
		n4.addChild(n15);

		n5.addChild(n16);
		n5.addChild(n17);
		n5.addChild(n18);
		n5.addChild(n19);

		System.out.println("Check if "+n1+" is locked "+n1.isLocked());
		System.out.println("Try locking "+n1.lock());
		System.out.println("Try locking "+n2+" should fail");
		System.out.println(n2.lock());
		System.out.println("Release lock for "+n1);
		System.out.println(n1.unlock());
		System.out.println("Try locking "+n2+" should pass");
		System.out.println(n2.lock());
		System.out.println("Try Unlocking "+n2+" should pass");
		System.out.println(n2.unlock());
		System.out.println("Try locking "+n1);
		System.out.println(n1.lock());
		System.out.println("Try locking "+n6+" should fail");
		System.out.println(n6.lock());
		System.out.println("Release lock for "+n1);
		System.out.println(n1.unlock());
		System.out.println("Try locking "+n6+" should pass");
		System.out.println(n6.lock());
		System.out.println("Try locking "+n1+" should fail");
		System.out.println(n1.lock());

	}
}
