package com.heap;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sshil on 4/18/2016.
 */

public class MinHeapWithMap {
    private Map<MinHeapKey, MinHeapNode> map = new HashMap<>();
    private MinHeap minHeap = null;

    public MinHeapWithMap(int size){
        this.minHeap = new MinHeap(size);
    }

    public void update(MinHeapKey key, int update){
        MinHeapNode minHeapNode;
        if (map.containsKey(key)){
            minHeapNode = map.get(key);
            minHeapNode.setHeapQualifier(minHeapNode.getHeapQualifier()+update);
        } else {
            //
            minHeapNode = setUpNewNode(key, update);
            map.put(key, minHeapNode);
        }
        if (minHeapNode.getHeapIndex() != 0) {
            minHeap.downHeap(minHeapNode.getHeapIndex());
        } else if (minHeap.getTop() != null && minHeap.getTop().getHeapQualifier() < minHeapNode.getHeapQualifier()) {
            MinHeapNode top = minHeap.extractMin();
            top.setHeapIndex(0);
            minHeap.insert(minHeapNode);
        } else if (minHeap.isSpaceAvailable()) {
            minHeap.insert(minHeapNode);
        }

    }

    private MinHeapNode setUpNewNode(MinHeapKey key, int update) {
        MinHeapNode minHeapNode = new MinHeapNode(this.minHeap);
        minHeapNode.setData((String)key.getKey());
        minHeapNode.setHeapQualifier(update);
        return minHeapNode;
    }

    public void update(MinHeapKey minHeapKey){
        update(minHeapKey, 1);
    }

    public MinHeapNode extract(){
        return minHeap.extractMin();
    }

    public static void main(String[] args) {
        String str = "aaa bbb ccc aaa bbb ccc aaa bbb ccc ddd eee fff ggg ddd ddd ddd ddd aaa bbb ccc ccc";
        String[] strArr = str.split("\\s+");
        MinHeapWithMap minHeapWithMap = new MinHeapWithMap(3);
        for (int i = 0 ; i < strArr.length ; i++) {
            System.out.println(strArr[i]);
            minHeapWithMap.update(new MinHeapKey(strArr[i]));
        }

        minHeapWithMap.minHeap.printContent();
    }
}
