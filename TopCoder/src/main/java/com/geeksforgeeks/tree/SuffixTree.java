package com.geeksforgeeks.tree;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by sshil on 1/24/2016.
 */
//http://www.geeksforgeeks.org/pattern-searching-set-8-suffix-tree-introduction/
public class SuffixTree {

	static class SuffixTreeNode {
		private String data;

		public SuffixTreeNode(String data){
			this.data = data;
		}

		private Set<SuffixTreeNode> childList = new HashSet<>();

		@Override
		public int hashCode(){
			return data.hashCode();
		}

		@Override
		public boolean equals(Object object){
			SuffixTreeNode suffixTreeNode = (SuffixTreeNode)object;
			return suffixTreeNode.data.equals(this.data);
		}

		private void insert(String str) {
			if (childList.size() == 0){
				// we might have to split the node. if the first character matches
				if (!this.data.isEmpty()) {
					char c1 = this.data.charAt(0);
					char c2 = str.charAt(0);
					if ( c1 == c2 ) {
						// we have to split
						this.data = c1+"";
						insertUtil(this, data.length()> 1 ? data.substring(1): null, str.length() > 1? str.substring(1): null);
					} else {
						// TODO throw exception. how did it come here?
					}
				} else {
					this.childList.add(new SuffixTreeNode(str));
				}

			} else {
				for (SuffixTreeNode node : this.childList) {
					if (node.data.charAt(0) == str.charAt(0)){
						node.insert(str);
					}
				}
			}
		}

		private void insertUtil(SuffixTreeNode suffixTreeNode, String string1, String string2) {
			if (string1 == null && string2 == null) {

			}
		}
	}

	public static void main(String[] args) {

	}
}
