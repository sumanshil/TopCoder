package com.geeksforgeeks.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;

/**
 * Created by sshil on 11/7/2015.
 */

//http://www.geeksforgeeks.org/mirror-of-n-ary-tree/
public class NaryTreeMirror {
    static class NaryTreeMirrorNode {
        private int value;
        private List<NaryTreeMirrorNode> childNodes;
        public NaryTreeMirrorNode(int value) {
            this.value = value;
            childNodes = new ArrayList<>();
        }

        public NaryTreeMirrorNode() {

        }

        public void addChild(int index, int child){
            childNodes.add(index, new NaryTreeMirrorNode(child));
        }

        public Optional<NaryTreeMirrorNode> getChild(int index){
            if (index < childNodes.size()) {
               return Optional.ofNullable(childNodes.get(index));
            }
            return Optional.empty();
        }

        public void reverseChildPositions(){
            int start = 0;
            int end = childNodes.size()-1;
            while (start < end){
                swapChildrenPositions(start, end);
                start++;
                end--;
            }
        }

        public int getValue() {
            return value;
        }

        public int getChildSize(){
            return childNodes.size();
        }

        private void swapChildrenPositions(int position1, int position2) {
            NaryTreeMirrorNode node1 = this.childNodes.get(position1);
            NaryTreeMirrorNode node2 = this.childNodes.get(position2);
            this.childNodes.set(position1, node2);
            this.childNodes.set(position2, node1);
        }
    }

    static class BoundaryNode extends NaryTreeMirrorNode {

        public BoundaryNode(int value) {
            super(value);
        }

        public BoundaryNode(){
            super();

        }
    }

    public void printBFS(NaryTreeMirrorNode root){
        Queue<NaryTreeMirrorNode> queue = new LinkedList<>();
        queue.add(root);
        queue.add(new BoundaryNode());
        while (!queue.isEmpty()){
            NaryTreeMirrorNode node = queue.remove();
            if ( (node instanceof  BoundaryNode)){
                System.out.println("#");
                if (queue.size() > 0){
                    queue.add(new BoundaryNode());
                }
            } else {
                System.out.println(node.getValue());
                for ( int i = 0 ; i < node.getChildSize(); i++) {
                    queue.add(node.getChild(i).get());
                }
            }

        }
    }


    public void mirror(NaryTreeMirrorNode root){
        boolean reverse = true;
        Queue<NaryTreeMirrorNode> queue = new LinkedList<>();
        queue.add(root);
        queue.add(new BoundaryNode());
        while(!queue.isEmpty()) {
           NaryTreeMirrorNode node = queue.remove();
           if ( !(node instanceof BoundaryNode)){
               if (reverse) {
                   node.reverseChildPositions();
               }
               for ( int i = 0 ; i < node.getChildSize() ; i++){
                   queue.add(node.getChild(i).get());
               }
           } else {
               if (queue.size() > 0) {
                   queue.add(new BoundaryNode());
               }
               //reverse = !reverse;
           }
        }

    }

    public static void main(String[] args) {
        NaryTreeMirrorNode root = new NaryTreeMirrorNode(10);
        root.addChild(0, 2);
        root.addChild(1, 34);
        root.addChild(2, 56);
        root.addChild(3, 100);

        root.getChild(1).get().addChild(0, 1);

        root.getChild(3).get().addChild(0, 7);
        root.getChild(3).get().addChild(1, 8);
        root.getChild(3).get().addChild(2, 9);

        root.getChild(1).get().getChild(0).get().addChild(0,3);
        root.getChild(1).get().getChild(0).get().addChild(1,4);
        root.getChild(1).get().getChild(0).get().addChild(2,5);
        NaryTreeMirror tree = new NaryTreeMirror();
        tree.printBFS(root);
        tree.mirror(root);
        tree.printBFS(root);

    }
}
