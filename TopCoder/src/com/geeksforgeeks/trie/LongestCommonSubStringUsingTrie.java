package com.geeksforgeeks.trie;

public class LongestCommonSubStringUsingTrie extends BaseTrie
{
    private int maxLength = 0;
    
    public int findLongestCommonSubString(String str1,
                                           String str2)
    {
        BaseTrie trie = BaseTrie.getInstance();
        str1 = str1.toLowerCase();
        for(int i = 0 ; i < str1.length() ; i++)
        {
            trie.beginInsert();
            String str = str1.substring(i, str1.length());
            for ( int j = 0 ; j < str.length() ; j++)
            {
                char c = str.charAt(j);
                trie.insert(c, null);
            }
            trie.finishInsert();
        }
        
        str2 = str2.toLowerCase();
        for (int i = 0 ; i < str2.length() ; i++ )
        {
            trie.beginInsert();
            String str = str2.substring(i, str2.length());
            for ( int j = 0 ; j < str.length() ; j++)
            {
                char c = str.charAt(j);
                final int k = j;
                trie.insert(c, new BaseTrie.NodeInsertCallback(){
                    @Override
                    public void inspect(TrieNode node)
                    {                        
                        if (!node.isNew())
                        {
                            if ( k+1 > LongestCommonSubStringUsingTrie.this.maxLength  )
                            {
                                LongestCommonSubStringUsingTrie.this.maxLength  = k+1;
                            }
                        }
                    }
                    
                });
            }
            trie.finishInsert();            
        }
        return maxLength;
    }
    
    public static void main(String[] args)
    {
        String str1 = "GeeksForGeeks";
        String str2 = "abForc";
        int result = new LongestCommonSubStringUsingTrie()
                     .findLongestCommonSubString(str1, str2);
        System.out.println(result);
    }
}
