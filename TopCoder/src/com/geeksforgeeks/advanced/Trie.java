package com.geeksforgeeks.advanced;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Trie {
    protected TrieNode root;
    private BinaryHeap binaryHeap;
    
    public Trie(BinaryHeap heap){
    	this.root = new TrieNode('X');
    	this.binaryHeap = heap;
    }
    
    public Trie(){
    	this.root = new TrieNode('X');
    }
    public void addWord(String word){
    	System.out.println("Inserting " + word);
    	TreeNode node = this.root.addChildren(word);
    	//((TrieNode)node).printContent(node);
    	//System.out.println();
    	this.binaryHeap.add(node);
    	
    }
    
    public static void main(String[] args) throws IOException{
    	List<String> list = new ArrayList<String>();
    	list.add("suman");
    	list.add("suman");
    	list.add("deepak");
    	list.add("abhi");
    	list.add("deepak");
    	list.add("abhi");
    	list.add("deepak");
    	list.add("suman");
    	list.add("suman");
    	list.add("khelo");
    	list.add("khelo");
    	list.add("khelo");
    	list.add("khelo");
    	list.add("khelo");
    	list.add("zzzz");
    	list.add("aaa");
//    	BinaryHeap heap = new BinaryHeap(4);
//    	Trie trie = new Trie(heap);
//    	for(String str : list){
//    		trie.addWord(str);
//    	}
//    	heap.printContent();
    	getFileContent();
    }
    
    public static void getFileContent() throws IOException{
       	BinaryHeap heap = new BinaryHeap(4);
    	Trie trie = new Trie(heap);
 
    	String filePath = "C:\\Users\\607166161\\desktop\\Performance testing.txt";
    	FileInputStream fis = new FileInputStream(new File(filePath));
    	BufferedReader buffer = new BufferedReader(new InputStreamReader(fis));
    	while(buffer.readLine() != null){
    		String line = buffer.readLine();
    		String[] str = line.split("\\s+");
    		for(int i = 0 ; i < str.length ; i++){
    			trie.addWord(str[i]);
    		}
    	}
    	heap.printContent();
    }
    
}
