package com.geeksforgeeks.tree;
//http://www.geeksforgeeks.org/find-closest-leaf-binary-tree/
import java.util.ArrayList;
import java.util.List;


public class FindClosestLeafNode
{
    static class Node
    {
        char c;
        Node left;
        Node right;
        
        Node(char c)
        {
            this.c = c;            
        }
        
        public int hashCode()
        {
            return c;
        }
        
        public boolean equals(Object o)
        {
            return c== ((Node)o).c;
        }
    }
        
    public int calculate(Node root,
                          Node keyNode)
    {
        List<Node> list = new ArrayList<Node>();
        getAllLeafNodes(root,
                        list);
        int minDistance = Integer.MAX_VALUE;
        for(Node node : list)
        {
            Node lowestCommonAncestor = findLCA(root, node, keyNode);
            int count1 = getDistance(lowestCommonAncestor, node, 0);
            int count2 = getDistance(lowestCommonAncestor, keyNode, 0);
            if (count1 + count2 < minDistance)
            {
                minDistance = (count1+count2);
            }
        }
        return minDistance;
    }
    
    private int getDistance(Node lowestCommonAncestor,
                            Node node,
                            int distance)
    {
        if (lowestCommonAncestor == null)
        {
            return 0;
        }
        
        if (lowestCommonAncestor == node)
        {
            return distance;
        }
        
        int lDistance = getDistance(lowestCommonAncestor.left,
                                    node,
                                    distance+1);
        int rDistance = getDistance(lowestCommonAncestor.right,
                                    node,
                                    distance+1);

        return lDistance+rDistance;
    }

    private Node findLCA(Node root,
                         Node node,
                         Node keyNode)
    {
        if (root == node)
        {
            return root;
        }
        else if (root == keyNode)
        {
            return root;
        }
        int lCount = getCount(root.left,node, keyNode);
        int rCount = getCount(root.right,node, keyNode);
        if (lCount == rCount)
        {
            return root;
        }
        else if (lCount > 0)
        {
            return findLCA(root.left, node, keyNode);
        }
        else if (rCount > 0)
        {
            return findLCA(root.right, node, keyNode);
        }
        else 
        {
            return null;
        }
    }

    private int getCount(Node root,
                         Node node,
                         Node keyNode)
    {
        if (root == null)
        {
            return 0;
        }
        else if (root == node
                 || root == keyNode)
        {
            return 1;
        }
        int lCount = getCount(root.left,
                              node,
                              keyNode);
        int rCount = getCount(root.right,
                              node,
                              keyNode);
        
        return lCount+rCount;
    }

    private void getAllLeafNodes(Node root, List<Node> list)
    {
        if (root == null)
        {
            return ;
        }
        else if (root.left == null
                && root.right == null)
        {
            list.add(root);
            return ;
        }
        getAllLeafNodes(root.left, list);
        getAllLeafNodes(root.right, list);
    }

    
    public int findClosestLeafNode(Node root, char key)
    {
        Node[] ancestors = new Node[100];
        int result = findClosestRecursiveUtil(root,
                                              key,
                                              ancestors,
                                              0);
        return result;
    }
    
    
    private int findClosestRecursiveUtil(Node root,
                                         char key,
                                         Node[] ancestors,
                                         int index)
    {
        if (root == null)
        {
            return Integer.MAX_VALUE;
        }
        if (root.c == key)
        {
            int res = findClosestDown(root);
            
            for(int i = index-1 ; i >=0 ; i--)
            {
                res = Math.min(res, index-i+findClosestDown(ancestors[i]));
            }
            return res;
        }
        ancestors[index] = root;
        return Math.min(findClosestRecursiveUtil(root.left, key, ancestors, index+1),
                        findClosestRecursiveUtil(root.right, key, ancestors, index+1));
    }

    private int findClosestDown(Node root)
    {
        if (root == null)
        {
            return Integer.MAX_VALUE;
        }
        if (root.left == null 
            && root.right == null)
        {
            return 0; 
        }
        return Math.min(findClosestDown(root.left),
                        findClosestDown(root.right))+1;
    }

    public static void main(String[] args)
    {
        Node root = new Node('A');
        
        root.left = new Node('B');
        root.right = new Node('C');
        
        root.right.left = new Node('E');
        root.right.right = new Node('F');
        
        root.right.left.left = new Node('G');
        root.right.right.right = new Node('H');
        
        root.right.left.left.left = new Node('I');
        root.right.left.left.right = new Node('J');

        root.right.right.right.left = new Node('K');
        int result = new FindClosestLeafNode().findClosestLeafNode(root, 'H');
        System.out.println(result);
    }

}
