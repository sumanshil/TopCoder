package com.geeksforgeeks.tree;

import java.util.*;

//http://www.geeksforgeeks.org/find-root-tree-children-id-sum-every-node-given/
public class FindRootOfTheTreeWhereChildrenIdSumForEveryNode {

    public void find (Map<Integer, Integer> map) throws Exception {
        Map.Entry<Integer, Integer> rootToMap = findRoot(map);
        BinaryTreeNode root = recursiveUtil(rootToMap.getKey(), rootToMap.getValue(), map);
        levelOrderTraversal(root);
    }

    private void levelOrderTraversal(BinaryTreeNode root) {
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            for ( int i = 0 ; i < size ; i++) {
                BinaryTreeNode node = queue.remove();
                System.out.print(node.getData()+" ");
                if (node.getLeft() != null) {
                    queue.add(node.getLeft());
                }
                if (node.getRight() != null) {
                    queue.add(node.getRight());
                }
            }
            System.out.println("=================");
        }
    }

    private BinaryTreeNode recursiveUtil(Integer key, Integer value, Map<Integer, Integer> map) throws Exception {
        if (value == 0){
            return new BinaryTreeNode(key);
        }
        BinaryTreeNode root = new BinaryTreeNode(key);

        Map.Entry<Integer, Integer> arr[] = findChildNodes(key, value, map);
        root.setLeft(recursiveUtil(arr[0].getKey(), arr[0].getValue(), map));
        root.setRight(recursiveUtil(arr[1].getKey(), arr[1].getValue(), map));
        return root;
    }

    private Map.Entry<Integer, Integer>[] findChildNodes(Integer key, Integer value, Map<Integer, Integer> map) {
        map.remove(key, value);
        List<Map.Entry<Integer, Integer>> list = new LinkedList<>(map.entrySet());
        Collections.sort(list, (o1, o2) -> (o1.getKey())
                .compareTo(o2.getValue()));
        int startIndex = 0;
        int endIndex = list.size()-1;
        Map.Entry<Integer, Integer> result[] = new Map.Entry[2];
        while (startIndex < endIndex) {
            int currentSum = list.get(startIndex).getKey() + list.get(endIndex).getKey();
            if (currentSum == value) {
                result[0] = list.get(startIndex);
                result[1] = list.get(endIndex);
                break;
            } else if ( currentSum > value) {
                endIndex--;
            } else {
                startIndex++;
            }
        }
        return result;
    }

    private Map.Entry<Integer, Integer> findRoot(Map<Integer, Integer> map) {
        int maxChildSumValue = Integer.MIN_VALUE;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > maxChildSumValue) {
                maxChildSumValue = entry.getValue();
            }
        }

        Map.Entry<Integer, Integer> result = null;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getKey() > maxChildSumValue) {
                result = entry;
            }
        }
        return result;
    }

    public static void main(String[] args) throws Exception {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(1, 5);
        map.put(2, 0);
        map.put(3, 0);
        map.put(4, 0);
        map.put(5, 5);
        map.put(6, 5);
        new FindRootOfTheTreeWhereChildrenIdSumForEveryNode().find(map);
     }
}
