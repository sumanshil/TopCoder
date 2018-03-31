package com.geeksforgeeks.graph;

import org.junit.Assert;
import org.junit.Test;

import static com.geeksforgeeks.graph.DisjointSet.union;

/**
 * Created by sshil on 5/10/2016.
 */
public class DisjointSetTest {


    @Test
    public void testMakeSet(){
        DisjointSetNode<Integer> node = new DisjointSetNode<>(1);
        DisjointSet<Integer> disjointSet1 = DisjointSet.makeSet(node);
        Assert.assertTrue(disjointSet1 != null);
        Assert.assertTrue(node.getParent().equals(node));
        Assert.assertTrue(disjointSet1.getRepresentative().equals(node));
        Assert.assertTrue(disjointSet1.getMembers().size()== 1);
        Assert.assertTrue(disjointSet1.getRepresentative().getRank() == 0);
        assertListContainsMember(disjointSet1, node);
    }

    @Test
    public void testUnion(){
        DisjointSetNode<Integer> node1 = new DisjointSetNode<>(1);
        DisjointSetNode<Integer> node2 = new DisjointSetNode<>(2);
        DisjointSet<Integer> disjointSet1 = DisjointSet.makeSet(node1);
        DisjointSet<Integer> disjointSet2 = DisjointSet.makeSet(node2);
        DisjointSet<Integer> unionSet = union(disjointSet1,
                                              disjointSet2,
                                              DisjointSetLeaderSelectionStrategy.DEFAULT);
        Assert.assertNotNull(unionSet);
        Assert.assertTrue(unionSet.getRepresentative().equals(node1));
        Assert.assertTrue(unionSet.getMembers().size() == 2);

        DisjointSetNode<Integer> node3 = new DisjointSetNode<>(3);
        DisjointSet<Integer> disjointSet3 = DisjointSet.makeSet(node3);
        unionSet = DisjointSet.union(unionSet,disjointSet3, null);

        Assert.assertNotNull(unionSet);
        Assert.assertTrue(node3.getParent().equals(node1));
        Assert.assertTrue(unionSet.getRepresentative().equals(node1));
        Assert.assertTrue(unionSet.getMembers().size() == 3);

        DisjointSetNode<Integer> node4 = new DisjointSetNode<>(4);
        DisjointSetNode<Integer> node5 = new DisjointSetNode<>(5);
        DisjointSet<Integer> disjointSet4 = DisjointSet.makeSet(node4);
        DisjointSet<Integer> disjointSet5 = DisjointSet.makeSet(node5);
        DisjointSet<Integer> unionSet4 = union(disjointSet4,
                                               disjointSet5,
                                               DisjointSetLeaderSelectionStrategy.DEFAULT);

        Assert.assertNotNull(unionSet4);
        Assert.assertTrue(unionSet4.getRepresentative().equals(node4));
        Assert.assertTrue(unionSet4.getMembers().size() == 2);

        unionSet = union(unionSet, unionSet4, DisjointSetLeaderSelectionStrategy.DEFAULT);
        Assert.assertNotNull(unionSet);
        Assert.assertTrue(unionSet.getRepresentative().equals(node1));
        Assert.assertTrue(unionSet.getMembers().size() == 5);
        Assert.assertTrue(DisjointSet.isInSameSet(node1, node4));


    }

    private void assertListContainsMember(DisjointSet<Integer> disjointSet1, DisjointSetNode<Integer> node) {
        Assert.assertNotNull(disjointSet1.getMembers());
        Assert.assertTrue(disjointSet1.getMembers().contains(node));
    }
}

