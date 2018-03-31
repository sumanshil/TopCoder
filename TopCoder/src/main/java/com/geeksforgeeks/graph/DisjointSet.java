package com.geeksforgeeks.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by sshil on 5/10/2016.
 */
public class DisjointSet<T extends Comparable<T>> {

    private DisjointSetNode<T> representative = null;

    public List<DisjointSetNode<T>> getMembers() {
        return members;
    }

    public void setMembers(List<DisjointSetNode<T>> members) {
        this.members = members;
    }

    public DisjointSetNode<T> getRepresentative() {
        return this.representative;
    }

    public void setRepresentative(DisjointSetNode<T> representative) {
        this.representative = representative;
    }

    private List<DisjointSetNode<T>> members = new ArrayList<>();

    public static <T extends Comparable<T>> boolean isInSameSet(DisjointSetNode node1, DisjointSetNode node2){
        DisjointSetNode<T> representative1 = node1.getSet().getRepresentative();
        DisjointSetNode<T> representative2 = node2.getSet().getRepresentative();
        if (representative1 != null ){
            return representative1.equals(representative2);
        }
        return false;
    }

    public static <T extends Comparable<T>> DisjointSet  makeSet(DisjointSetNode<T> node) {
        if (node.getParent().equals(node)){
            DisjointSet<T> set = new DisjointSet<>();
            node.setParent(node);
            set.addMember(node);
            set.setRepresentative(node);
            node.setSet(set);
            return set;
        } else {
            return node.getSet();
        }
    }

    private void addMember(DisjointSetNode<T> node) {
        this.members.add(node);
        node.setSet(this);
        if (this.getRepresentative() != null){
            node.setParent(this.representative);
        }
    }

    public static <T extends Comparable<T>> DisjointSet<T> union(DisjointSet<T> disjointSet1,
                                             DisjointSet<T> disjointSet2,
                                             DisjointSetLeaderSelectionStrategy leaderSelectionStrategy) {
        DisjointSetNode<T> representative1 = disjointSet1.getRepresentative();
        DisjointSetNode<T> representative2 = disjointSet2.getRepresentative();
        if (representative1.getRank() != representative2.getRank()){
            DisjointSetNode<T> selectedParent = representative1.getData().compareTo(representative2.getData()) < 0 ? representative1 : representative2;
            DisjointSetNode<T> selectedChild = representative1.getData().compareTo(representative2.getData()) < 0 ? representative2 : representative1;
            selectedChild.setParent(selectedParent);
            selectedParent.setRank(selectedChild.getRank()+1);
            selectedChild.setRank(0);
            DisjointSet<T> parentSet = selectedParent.getSet();
            DisjointSet<T> childSet = selectedChild.getSet();
            childSet.getMembers().stream().forEach(parentSet::addMember);
            childSet = null;
            return parentSet;
        } else {
            Map<DisjointSetNode, Boolean> map = leaderSelectionStrategy.select(disjointSet1, disjointSet2);
            DisjointSetNode<T> selectedParent = null;
            DisjointSetNode<T> selectedChild = null;
            if (map.get(representative1)){
                selectedParent = representative1;
                selectedChild = representative2;
            } else {
                selectedParent = representative2;
                selectedChild = representative1;
            }
            selectedChild.setParent(selectedParent);
            selectedParent.setRank(selectedChild.getRank()+1);
            selectedChild.setRank(0);
            DisjointSet<T> parentSet = selectedParent.getSet();
            DisjointSet<T> childSet = selectedChild.getSet();
            childSet.getMembers().stream().forEach(parentSet::addMember);
            childSet = null;
            return parentSet;
        }
    }
}
