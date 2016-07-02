package com.geeksforgeeks.tree;

/**
 * Created by sshil on 6/18/2016.
 */
//http://www.geeksforgeeks.org/find-largest-subtree-having-identical-left-and-right-subtrees/
public class FindTheLargestSubTreeWithIdenticalLeftAndRightSubTree {
    class TreeIdentityInfo{
        private boolean isIdentical;
        private int size;

        public TreeIdentityInfo(boolean isIdentical, int size){
            this.isIdentical = isIdentical;
            this.size = size;
        }

        public boolean isIdentical() {
            return isIdentical;
        }

        public void setIdentical(boolean identical) {
            isIdentical = identical;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }
    }

    public void find(BinaryTreeNode root){
        recursiveUtil(root, root.getLeft(), root.getRight());
        System.out.println(maxSize);
    }

    private int maxSize = Integer.MIN_VALUE;
    private TreeIdentityInfo recursiveUtil(BinaryTreeNode root, BinaryTreeNode left, BinaryTreeNode right) {
        if (left == null && right != null || left != null && right == null){
            return new TreeIdentityInfo(false, 0);
        }
        if (left == null && right == null){
            // leaf node
            return new TreeIdentityInfo(true, 0);
        }

        if (left.getData() == right.getData()) {
            // same
            TreeIdentityInfo leftInfo = recursiveUtil(root, left.getLeft(), right.getLeft());
            TreeIdentityInfo rightInfo = recursiveUtil(root, left.getRight(), right.getRight());
            if (leftInfo.isIdentical() && rightInfo.isIdentical()){
                int newSize = leftInfo.getSize() + rightInfo.getSize() + 1;
                if (newSize > maxSize){
                    maxSize = newSize;
                }
                return new TreeIdentityInfo(true, newSize);
            }
        } else {
            recursiveUtil(left,left.getLeft(), left.getRight());
            recursiveUtil(right, right.getLeft(), right.getLeft());
        }
        return null;
    }


    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(50);
        root.insertInLeft(10);
        root.insertInRight(60);

        root.getLeft().insertInLeft(5);
        root.getLeft().insertInRight(20);

        root.getRight().insertInLeft(70);
        root.getRight().insertInRight(70);

        root.getRight().getLeft().insertInLeft(65);
        root.getRight().getLeft().insertInRight(80);

        root.getRight().getRight().insertInLeft(65);
        root.getRight().getRight().insertInRight(80);
        new FindTheLargestSubTreeWithIdenticalLeftAndRightSubTree().find(root);
    }
}
