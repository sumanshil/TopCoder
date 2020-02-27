class TreeNode:
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None


class Solution:
    def isSymmetric(self, root: TreeNode) -> bool:
        result = self.recursive(root.left, root.right)
        return result

    def recursive(self, root1: TreeNode, root2: TreeNode):
        if root1 is None and root2 is None:
            return True

        if root1 is None or root2 is None:
            return False

        return root1.val == root2.val and self.recursive(root1.left, root2.right) and self.recursive(root1.right, root2.left)