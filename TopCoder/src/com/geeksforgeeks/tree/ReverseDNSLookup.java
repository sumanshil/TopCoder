package com.geeksforgeeks.tree;

import java.util.LinkedList;
import java.util.List;

public class ReverseDNSLookup
{
     static class TrieNode
     {
         private char c;
         private TrieNode[] children = null;
         private List<String> dns = new LinkedList<String>();
         public TrieNode(char c)
         {
             this.c = c;
             children = new TrieNode[11];
         }
         
         
         public void insert(String ip, String dns)
         {
             
             if (ip == null
                 || ip.length() == 0)
                 return;
             
             TrieNode nextNode = null;
             int index = getIndex(ip.charAt(0));
             if (children[index]==null)
             {
                 nextNode = new TrieNode(c);
                 children[index] = nextNode;
             }
             else
             {
                 nextNode = children[index];
             }
             nextNode.dns.add(dns);
             if (ip.length() > 1)
                 nextNode.insert(ip.substring(1), dns);
             else
                 nextNode.insert(null, dns);
                 
         }

         private int getIndex(char c)
         {
             int index = 10;
             if (c >= '0' && c<='9')
             {
                 index = c - '0';
             }
             return index;
         }
         public List<String> search(String ip)
         {
             if (ip == null
                   || ip.length() == 0)
             {
                 return this.dns;
             }
             
             int index = getIndex(ip.charAt(0));
             TrieNode node = children[index];
             if (node != null)
             {
                 if (ip.length() > 1)
                    return node.search(ip.substring(1));
                 else
                    return node.search(null);
             }
             else
             {
                 return null;
             }
         }         
     }
     
     
     
     public static void main(String[] args)
     {
         String[] ipAddresses = {"107.108.11.123", "107.109.123.255","74.125.200.106"};
         String[] URLS = {"www.samsung.com", "www.samsung.net","www.google.in"};
         TrieNode root = new TrieNode(' ');
         int index = 0;
         for(String ipAddress : ipAddresses)
         {
             root.insert(ipAddress, URLS[index++]);
         }
         List<String> result = root.search(ipAddresses[2]);
         for(String str : result)
         {
             System.out.println(str);   
         }
         
     }
}
