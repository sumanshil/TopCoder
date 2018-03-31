package com.geeksforgeeks.tree;

/**
 * Created by sshil on 2/7/2016.
 */
public class ThreadedBinaryTreeNode extends BinaryTreeNode {
	private boolean isRightThreaded;
	public ThreadedBinaryTreeNode(int data) {
		super(data);
	}

	public boolean isRightThreaded() {
		return isRightThreaded;
	}

	public void setRightThreaded(boolean rightThreaded) {
		isRightThreaded = rightThreaded;
	}
}
