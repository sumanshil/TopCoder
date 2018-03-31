package com.topcoder.problems;


import java.util.HashMap;
import java.util.Map;

import com.geeksforgeeks.advanced.TreeNode;
import com.geeksforgeeks.advanced.Trie;
import com.geeksforgeeks.advanced.TrieNode;

//http://community.topcoder.com/stat?c=problem_statement&pm=56&rd=3001
public class LetterSort extends Trie {
    static class LetterSortedTrieNode extends TrieNode{
    	private String content;
    	public LetterSortedTrieNode(char c){
    		super(c);
    	}
    	public String getContent(){
    		return this.content;
    	}
    	
    	public void setContent(String str){
    		this.content = str;
    	}
    	
    	public String print(){
    		StringBuffer sb = new StringBuffer();
    		printContentX(this, sb);
    		return sb.toString();
    	}
    	public void printContentX(TreeNode node, StringBuffer sb){
    		if (node == null || ((TrieNode)node).getC() == 'X'){
    			return ;			
    		}
    		String c = ((LetterSortedTrieNode)node).content;
    		printContentX(node.getParent(), sb);
    		sb.append(c);
    	}
    	
    }

    private static Map<Integer, LetterSortedTrieNode> map = new HashMap<Integer,LetterSortedTrieNode>();
    
    static class LetterSortedTrie extends Trie{
    	//private LetterSortedTrieNode root = new LetterSortedTrieNode('X');
    	public void addWord(String word){
    		TreeNode parent = this.root;
    		
    		for(int i = 0 ; i < word.length() ; i++){
    			TreeNode[] children = parent.getChildren();
    			char c = word.charAt(i);
    			int index = word.charAt(i)-97;
    			if (map.get(index)==null){
    				children[index] = new LetterSortedTrieNode(word.charAt(i));
    				map.put(index, (LetterSortedTrieNode)children[index]);
    				children[index].setParent(parent);
    				((LetterSortedTrieNode)children[index]).content = ""+word.charAt(i);
    				parent = children[index];    				
    			} else {
    				String str = (String)map.get(index).getContent();
    				str += word.charAt(i);
    				map.get(index).setContent(str);
    			}
    		}
    		
    		System.out.println(parent.print());
    	}
    }


	public static String doSort(String param){
		LetterSortedTrie trie = new LetterSortedTrie();
		trie.addWord(param);
		return null;
	}
	
	public static String doSort1(String s)
	{
		String sorted = "";
		for(int i = 0; i < s.length(); i++){
			if(sorted.indexOf(s.charAt(i)) == -1){
				for(int j = 0; j < s.length(); j++){
					if(s.charAt(j) == s.charAt(i)){
						sorted += s.charAt(j);
					}//if
				}//for
			}//if
		}//for
	    return sorted;
	}//method doSort()	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String str = "TopCoder";
		//doSort(str.toLowerCase());
		doSort1(str);

	}

}
