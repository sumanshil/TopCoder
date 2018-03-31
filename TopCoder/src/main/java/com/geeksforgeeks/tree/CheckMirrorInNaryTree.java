package com.geeksforgeeks.tree;

import java.util.LinkedList;
import java.util.Queue;

//http://www.geeksforgeeks.org/check-mirror-n-ary-tree/
public class CheckMirrorInNaryTree {

    static class TreeNode {
        int data;
        TreeNode[] children;
        public TreeNode (int data) {
            this.data = data;
            this.children = new TreeNode[10];
            if (data != Integer.MAX_VALUE) {
                for ( int i = 0 ; i < this.children.length ; i++) {
                    this.children[i] = new EmtpyNode();
                }
            }
        }

        public void addChild(int index, int data){
            if (children[index] == null) {
                children[index] = new TreeNode(data);
            } else {
                children[index].data = data;
            }
        }

        @Override
        public boolean equals(Object node){
            if ( node == null) {
                return false;
            }
            if (!(node instanceof  TreeNode)) return false;
            return ((TreeNode)node).data == this.data;
        }
    }

    static class EmtpyNode extends TreeNode {

        public EmtpyNode() {
            super(Integer.MAX_VALUE);
        }
    }

    public void checkMirror(TreeNode node1, TreeNode node2) {
        Queue<TreeNode> queue1 = new LinkedList<>();
        Queue<TreeNode> queue2 = new LinkedList<>();
        queue1.add(node1);
        queue2.add(node2);


        while (!queue1.isEmpty() && !queue2.isEmpty()) {
            int queue1Size = queue1.size();
            int queue2Size = queue2.size();

            if (queue1Size != queue2Size) {
                System.out.println("Not mirror");
                return;
            }

            TreeNode[] array1 = queue1.toArray(new TreeNode[0]);
            TreeNode[] array2 = queue2.toArray(new TreeNode[0]);
            if (!mirror(array1, array2)) {
                System.out.println("Not mirror");
                return;
            }
            for ( int i = 0 ; i < queue1Size ; i++) {
                TreeNode node = queue1.poll();
                for (TreeNode child : node.children) {
                    if (child != null) {
                        queue1.add(child);
                    }
                }
            }

            for ( int i = 0 ; i < queue2Size ; i++) {
                TreeNode node = queue2.poll();
                for (TreeNode child : node.children) {
                    if (child != null) {
                        queue2.add(child);
                    }
                }
            }
        }
        if ((queue1.isEmpty() && !queue2.isEmpty()) || (!queue1.isEmpty() && queue2.isEmpty())){
            System.out.println("Not Mirror");
        } else {
            System.out.println("Mirror");
        }
    }

    private boolean mirror(TreeNode[] array1, TreeNode[] array2) {
        int index1 = 0;
        int index2 = array2.length-1;
        while (index2 >= 0 && array2[index2].data == Integer.MAX_VALUE) {
            index2--;
        }
        while (index1 < array1.length && index2 >= 0) {
            if ((array1[index1] != null && array2[index2] == null)
                    || (array1[index1] == null && array2[index2] != null)){
                return false;
            }

            if ((array1[index1] != null && array2[index2]!= null) && (array1[index1].data != array2[index2].data)) {
                return false;
            }
            index1++;
            index2--;
        }
        return true;
    }


    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(1);
        root1.addChild(0, 2);
        root1.addChild(1, 3);
        root1.addChild(2, 4);

        TreeNode root2 = new TreeNode(1);
        root2.addChild(0, 4);
        root2.addChild(1, 3);
        root2.addChild(2, 2);

        new CheckMirrorInNaryTree().checkMirror(root1, root2);
    }
}
