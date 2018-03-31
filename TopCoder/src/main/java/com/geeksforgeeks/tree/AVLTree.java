package com.geeksforgeeks.tree;

import java.util.Stack;

public class AVLTree {
       static class Node{
    	   int data;
    	   Node left;
    	   Node right;
    	   int height;
    	   public Node(int data){
    		   this.data = data;
    	   }
       }
       
       public Node insert(Node root, int key){
    	   if (root == null){
    		   root = new Node(key);
    		   return root;
    	   }
    	   if (key > root.data){
    		   root.right = insert(root.right, key);
    	   } else if (key < root.data){
    		   root.left = insert(root.left, key);
    	   }
    	   
    	   root.height = Math.max(getHeight(root.left), getHeight(root.right))+1;
    	   int balance = getBalance(root);
    	   if (balance > 1 && key > root.left.data){
    		   // LEFT RIGHT case
    		   root.left = leftRotate(root.left);
    		   return rightRotate(root);
    		   
    	   } else if (balance > 1 && key < root.left.data){
    		   return rightRotate(root);
    	   } else if (balance < -1 && key < root.right.data){
    		   root.right =  rightRotate(root.right);
    		   return leftRotate(root);
    	   } else if (balance < -1 && key >root.right.data){
    		   return leftRotate(root);
    	   }
    	   return root;
       }

	private Node rightRotate(Node X) {
         Node Y = X.left;
         Node Z = Y.right;
         Y.right = X;
         X.left = Z;
         X.height = Math.max(getHeight(X.left), getHeight(X.right))+1;
         Y.height = Math.max(getHeight(Y.left), getHeight(Y.right))+1;
         return Y;		
	}

	private Node leftRotate(Node node) {
		Node y = node.right;
		Node x = y.left;
		y.left = node;
		node.right = x;
		node.height = Math.max(getHeight(node.left),getHeight(node.right))+1;
		y.height  = Math.max(getHeight(y.left),getHeight(y.right))+1;
		return y;
	}

	private int getBalance(Node root) {
		if (root == null)
			return 0;
		return getHeight(root.left)- getHeight(root.right);
		
	}

	private int getHeight(Node node) {
		if (node == null)
			return 0;
		int lHeight = getHeight(node.left);
		int rHeight = getHeight(node.right);
		if (lHeight > rHeight){
			return lHeight+1;
		} else {
			return rHeight+1;
		}
	}
       
	public void preOrder(Node root){
		Stack<Node> stack = new Stack<Node>();
		stack.push(root);
		while(!stack.isEmpty()){
			Node node = stack.pop();
			if (node.right!=null){
				stack.push(node.right);
			}
			if (node.left != null){
				stack.push(node.left);
			}
			System.out.println(node.data);
		}
	}
	
	
	public Node delete(Node root, int key){
		if (root == null)
			return null;
		
		if (root.data <key){
			root.right = delete(root.right, key);			
		} else if (root.data > key){
			root.left  = delete (root.left, key);
		}
		
		if (root.data == key){
			return deleteNode(root);
		}
		root.height = Math.max(getHeight(root.left), getHeight(root.right))+1;
		int balance = getBalance(root);
		int leftBalance = getBalance(root.left);
		if (balance > 1 && leftBalance > 1 ){
			// left left case
			return rightRotate(root);
		}
		if (balance > 1 && leftBalance < -1){
			// left right case
			root.left = leftRotate(root);
			return rightRotate(root);
		}
		if (balance < -1 && leftBalance < -1){
			// right right case
			return leftRotate(root.right);			
		}
		
		if (balance < -1 && leftBalance > 1){
			//right left case
			root.right = rightRotate(root.right);
			return leftRotate(root);
		}
		return root;
	}
	
	private Node deleteNode(Node node){
		Node left = node.left;
		Node right = node.right;
		Node x  = null;
		if (left != null){
		    x = left.right;
		}
		Node y = right;
		while(y != null && y.left != null){
			y = y.left;
		}
		if (left != null)
	        left.right = right;
		if (y != null)
		    y.left = x;
		return left;
	}
	
	public static void main(String[] args){
		Node root = null;
		AVLTree avlTree = new AVLTree();
		root = avlTree.insert(root, 10);
		root = avlTree.insert(root, 20);
		root = avlTree.insert(root, 30);
		root = avlTree.insert(root, 40);
		root = avlTree.insert(root, 50);
		root = avlTree.insert(root, 25);
		avlTree.preOrder(root);
	}
       
       
}
