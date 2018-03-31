package com.topcoder.problems.round155;
//http://community.topcoder.com/stat?c=problem_statement&pm=1748&rd=4580
public class RedBlackTree 
{
    enum Color
    {
       RED, BLACK        
    }

    enum ROTATION
    {
        LL,
        RR,
        LR,
        RL        
    }
    ROTATION rotation = null;
    static class RBNode
    {
        int data;
        Color color;
        RBNode left;
        RBNode right;
        public RBNode(int data)
        {
            this.data = data;
        }
    }

    RBNode root = null;
    private int twist = 0;
    public void insert(int data)
    {
        RBNode newRoot = insertUtil(root, data);
        newRoot.color = Color.BLACK;
        root = newRoot;
    }
    
    public void inorder()
    {
        inorderUtil(this.root);
    }
    
    public void inorderUtil(RBNode root)
    {
        if (root != null)
        {
            inorderUtil(root.left);
            System.out.println("( "+root.data+ " : "+ root.color +" ) ");
            inorderUtil(root.right);
        }
    }
    
	private RBNode insertUtil(RBNode root,
	                          int data)
    {
        if (root == null)
        {
            root = new RBNode(data);
            root.color = Color.RED;
            return root;
        }
        if (data < root.data)
        {
            root.left = insertUtil(root.left, data);
        }
        else if (data > root.data)
        {
            root.right = insertUtil(root.right, data);
        }
        
        if(isTreeUnbalanced(root))
        {
            if (rotation == ROTATION.LL)
            {
                root = rotateLeft(root);
                twist++;
            }
            else if (rotation == ROTATION.RR)
            {
                root = rotateRight(root);
                twist++;
            }
            else if (rotation == ROTATION.RL)
            {
                root.right = rotateRightLeft(root);
                root = rotateRight(root);
                twist ++;
            }
            else if (rotation == ROTATION.LR)
            {
                root.left = rotateLeftRight(root);
                root = rotateLeft(root);
                twist ++;                
            }

        }
        
        return root;
    }

	private RBNode rotateLeftRight(RBNode root)
    {
        RBNode x = root.left;
        RBNode y = x.right;
        RBNode T2 = y.left;
        y.left = x;
        x.right  = T2;
        return y;
    }

    private RBNode rotateRightLeft(RBNode root)
    {
        RBNode z = root.right;
        RBNode y = z.left;
        RBNode T3 = y.right;
        y.right = z;
        z.left  = T3;
        return y;
    }

    public int getTwistCount()
	{
	    return twist;
	}

    private RBNode rotateRight(RBNode root)
    {
        RBNode r = root.right;
        RBNode rr = root.right.right;
        
        RBNode temp = r.left;
        r.left = root;
        root.right = temp;
        root.color = Color.BLACK;
        rr.color = Color.BLACK;
        return r;
    }


    private RBNode rotateLeft(RBNode root)
    {
        RBNode l = root.left;
        RBNode ll = root.left.left;
        
        RBNode temp = l.right;
        l.right = root;
        root.left = temp;
        root.color = Color.BLACK;
        if (ll != null)
            ll.color = Color.BLACK;
        return l;
    }


    private boolean isTreeUnbalanced(RBNode root)
    {        
        if  (root.left != null && 
             root.left.left != null && 
             root.left.color == Color.RED && 
             root.left.left.color == Color.RED)
        {
            this.rotation = ROTATION.LL;
            return true;
        }
        else if (root.right != null && 
                 root.right.right != null && 
                 root.right.color == Color.RED && 
                 root.right.right.color == Color.RED)
        {
            this.rotation = ROTATION.RR;
            return true;
        }
        else if (root.right != null && 
                 root.right.left != null && 
                 root.right.color == Color.RED && 
                 root.right.left.color == Color.RED)
        {
            this.rotation = ROTATION.RL;
            return true;
        }
        else if (root.left != null && 
                 root.left.right != null && 
                 root.left.color == Color.RED && 
                 root.left.right.color == Color.RED)
       {
           this.rotation = ROTATION.LR;
           return true;
       }
        return false;
    }

    public int numTwists(int[] keys)
    {
        for(int key : keys)
        {
            insert(key);
        }
        return getTwistCount();
    }

    public static void main(String[] args) 
	{
        int[] keys = { 6,8,10,12,4,2,18,14,16,19,7,15,9,17,13,5,11,3,1};
        RedBlackTree rbTree = new RedBlackTree();
		int result = rbTree.numTwists(keys);
		System.out.println(result);
		rbTree.inorder();
         
	}

}
