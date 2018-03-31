package com.geeksforgeeks.graph;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sshil on 5/10/2016.
 */
@FunctionalInterface
public interface DisjointSetLeaderSelectionStrategy {
    DisjointSetLeaderSelectionStrategy DEFAULT =(set1, set2)-> {
        Map<DisjointSetNode, Boolean> map = new HashMap<>();
        if (set1.getRepresentative().getData()
        .compareTo(set2.getRepresentative().getData()) < 0){
            map.put(set1.getRepresentative(), true);
            map.put(set2.getRepresentative(), false);
        } else {
            map.put(set1.getRepresentative(), false);
            map.put(set2.getRepresentative(), true);
        }
        return map;
    };
    Map<DisjointSetNode, Boolean> select(DisjointSet node1, DisjointSet node2);
}
