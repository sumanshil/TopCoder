package com.geeksforgeeks.array;

public class InterpretaionOfAnArrayOfDigits {
     static class Node{
    	 String dataString;
    	 Node left;
    	 Node right;
    	 public Node(String str){
    		 this.dataString = str;
    	 }
    	 
    	 public String toString(){
    		 return this.dataString;
    	 }
     }
     
  // For simplicity I am taking it as string array. Char Array will save space
     private static final String[] alphabet = {"", "a", "b", "c", "d", "e",
         "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r",
         "s", "t", "u", "v", "w", "x", "v", "z"};
     
     //"1,2,1"
     public static Node createTree(int data, String parentString, int[] arr){
         if (data > 26){
        	 return null;
         }
         
         String dataString = parentString + alphabet[data];
         
         Node node = new Node(dataString);
         if (arr.length != 0 ){
        	 data = arr[0];
        	 int[] newArr = copyOfRange(1, arr);
        	 node.left = createTree(data, dataString, newArr);
        	 
        	 if (arr.length > 1){
        		 data = arr[0]*10 + arr[1];
        		 newArr = copyOfRange(2, arr);
        		 node.right = createTree(data, dataString, newArr);
        	 }
         }
         return node;
     }
     
	private static int[] copyOfRange(int index, int[] arr) {
        int length = arr.length - index;
        int[] newArr = new int[length];       
        int newIndex = 0;
        for(int i = index; i <arr.length ; i++){
        	newArr[newIndex++] = arr[i];
        }
		return newArr;
	}
	
	public static void printAllLeafs(Node n){
		if (n == null){
			return;
		}
		
		if (n.left == null && n.right == null){
			System.out.println(n.dataString);
			return;
		}
		printAllLeafs(n.left);
		printAllLeafs(n.right);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//int[] arr = {1,2,1};
		//int[] arr = {1, 2, 2, 1};
		int[] arr = {1, 1, 3, 4};
		Node node = createTree(0, "", arr);
        printAllLeafs(node);
	}

}
