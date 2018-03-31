package com.geeksforgeeks.tree.niary;

import java.util.LinkedList;

public class NiaryTreeNode<T extends Object> {
	private T data;
	private NiaryTreeNode<T>[] children;
	private int height;
	
	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	@SuppressWarnings("unchecked")
	public NiaryTreeNode(T data,int numberOfChildren){
		this.data = data;
		this.children = new NiaryTreeNode[numberOfChildren];
	}

	public T getData() {
		return this.data;
	}
	
	public void addChildAtIndex(NiaryTreeNode<T> child, int index){
		this.children[index] = child;
	}
	
	public NiaryTreeNode<T> getChildAtIndex(int index){
		return this.children[index];
	}
	
	private void addChildAtIndex (NiaryTreeNode<T> child, int[] indexArr) {
		NiaryTreeNode<T> temp = this;
		for (int i = 0 ; i < indexArr.length-1 ; i++ ) {
			temp = temp.getChildAtIndex(indexArr[i]);
			if (temp == null){
				throw new RuntimeException("Invalid child path");
			}
		}
		if (temp != null){
			temp.addChildAtIndex(child, indexArr[indexArr.length-1]);
		}
	}
	
	public NiaryTreeNode<T>[] getChildren(){
		return this.children;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void createChildNode(int[] path, int childrenArrSize, T data){
		try {
			NiaryTreeNode<T> newNode = new NiaryTreeNode(data, childrenArrSize);
			addChildAtIndex(newNode, path);
		} catch(RuntimeException e) {
			e.printStackTrace();
			System.out.println("Failed to create as child already exist");
			return;
		}
	}
		
	public void printRootToLeaf(){
		printRecursive(this, new LinkedList<NiaryTreeNode<T>>());
	}

	private void printRecursive(NiaryTreeNode<T> node, LinkedList<NiaryTreeNode<T>> linkedList) {
		if ( node.isLeaf()) {
			linkedList.add(node);
			print(linkedList);
			linkedList.removeLast();
			System.out.println("======");
			return;
		}
		
		linkedList.add(node);
		for ( int i = 0 ; i < node.getChildren().length ; i++) {
			if (node.getChildAtIndex(i) != null){
			    NiaryTreeNode<T> childNode = node.getChildAtIndex(i);
			    printRecursive(childNode, linkedList);
			}
		}
		linkedList.removeLast();		
	}

	private void print(LinkedList<NiaryTreeNode<T>> linkedList) {
		linkedList.stream().map(e -> e.getData()).forEach(System.out::print);
	}
	
	public boolean isLeaf() {
		for ( int i = 0 ; i < this.children.length ; i++){
			if (this.children[i] != null) {
				return false;
			}
		}
		return true;
		
		
	}
}
