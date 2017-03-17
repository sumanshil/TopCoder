package com.geeksforgeeks.tree;

import static org.hamcrest.CoreMatchers.instanceOf;

/**
 * Created by sshil on 1/27/2016.
 */
public class BinaryTreeNode {
	private int data;
	private BinaryTreeNode left;
	private BinaryTreeNode right;

	public BinaryTreeNode(int data){
		this.data = data;
	}

	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}

	public BinaryTreeNode getLeft() {
		return left;
	}

	public void setLeft(BinaryTreeNode left) {
		this.left = left;
	}

	public BinaryTreeNode getRight() {
		return right;
	}

	public void setRight(BinaryTreeNode right) {
		this.right = right;
	}

	public BinaryTreeNode insertInLeft(int data){
		this.left = new BinaryTreeNode(data);
		return this.left;
	}

	public BinaryTreeNode insertInRight(int data){
		this.right = new BinaryTreeNode(data);
		return this.right;
	}

	public String toString(){
		return data+"";
	}
	
	@Override
	public int hashCode(){
		return data;
	}
	
	@Override
	public boolean equals(Object other){
		if (other == null){
			return false;
		}
		if (other instanceof BinaryTreeNode){
			return false;
		}
		return this.getData() == ((BinaryTreeNode)other).data;
	}
	
}
