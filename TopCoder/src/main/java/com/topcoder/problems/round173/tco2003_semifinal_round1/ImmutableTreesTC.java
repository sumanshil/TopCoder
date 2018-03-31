package com.topcoder.problems.round173.tco2003_semifinal_round1;

public class ImmutableTreesTC {

    private static int ct = 0;
    static class Node {
        private int data;
        private Node lNode;
        private Node rNode;
        private boolean markNotGarbage;

        public Node( int data ){
            this.data = data;
            this.markNotGarbage = false;
            this.lNode = null;
            this.rNode = null;
        }

        public Node (Node other){
            this.data = other.data;
            this.markNotGarbage = false;
            this.lNode = other.lNode;
            this.rNode = other.rNode;
        }

        public void markNotGarbage(){
            if (markNotGarbage){
                return;
            }
            markNotGarbage = true;
            ct++;
            if (lNode != null) {
                lNode.markNotGarbage();
            }
            if (rNode != null) {
                rNode.markNotGarbage();
            }
        }
    }

    private static Tree[] trees = null;
    static class Tree {
        private Node root;

        public Tree(int oldTree, int valToInsert) {
            if (oldTree == -1){
                root = new Node(valToInsert);
                return;
            }

            root = new Node(trees[oldTree].root);
            doIt(root, valToInsert);
        }

        private void doIt(Node root, int valToInsert) {
            if (valToInsert > root.data) {
                if (root.rNode == null) {
                    root.rNode = new Node(valToInsert);
                } else {
                    Node newNode = new Node(root.rNode);
                    root.rNode = newNode;
                    doIt(newNode, valToInsert);
                }
            }
        }

        public void markNotGarbage(){
            root.markNotGarbage();
        }

    }

}
