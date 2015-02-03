package com.geeksforgeeks.trie;

public class BaseTrie
{
    static interface NodeInsertCallback
    {
        public void inspect(TrieNode node);       
    }
    
    public static BaseTrie getInstance()
    {
        return new BaseTrie();
    }
    
    static class TrieNode
    {
        char data;
        TrieNode[] children = null;
        private boolean isNew = true;
        public TrieNode(char c)
        {
            this(c, true);
        }

        public TrieNode(char c, boolean isNew)
        {
            this.data = c;
            children = new TrieNode[26];
            this.isNew = isNew;
        }
        
        public boolean isNew()
        {
            return isNew;
        }
        
        public TrieNode insert(char c, NodeInsertCallback callback)
        {
            int index = c - 'a';
            if ( children[index] == null )
            {
                children[index] = new TrieNode(c);
            }
            TrieNode child = children[index];
            if ( callback != null )
            {
                callback.inspect(child);
            }
            child.isNew = false;
            return child;
        }
    }
    
    public void beginInsert()
    {
        mCurrent = mRoot;
    }
    
    public void insert(char c, NodeInsertCallback callback)
    {
        if ( mCurrent == null )
            throw new RuntimeException("Call beginInsert() before inserting set of characters."
                    + "Call finishInsert() after inserting all the characters");
        TrieNode newNode = mCurrent.insert(c, callback);
        mCurrent = newNode;
        
    }
    
    public void finishInsert()
    {
        mCurrent = null;
    }
    
    private final TrieNode mRoot = new TrieNode(' ');
    private TrieNode mCurrent = null;
}
