package com.geeksforgeeks.advanced;

public interface TreeNode extends Comparable<TreeNode> {
    public TreeNode addChildren(Object child);
    public void removeChildren();
    public TreeNode getChild(Object key);
    public void addPeer(TreeNode node);
    public TreeNode getPeer();
    public Object getQualifier();
    public void setQualifier(Object qualifier);
    public void setParent(TreeNode parent);
    public TreeNode getParent();
    public String print();
    public TreeNode[] getChildren();
}
