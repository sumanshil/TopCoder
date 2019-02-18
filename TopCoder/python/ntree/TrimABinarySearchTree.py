class TreeNode:
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None
class Solution:
    def trimBST(self, root, L, R):
        if root is None:
            return None

        lNode = self.trimBST(root.left, L, R)
        rNode = self.trimBST(root.right, L, R)

        if root.val < L:
            return rNode
        if root.val > R:
            return lNode

        root.left = lNode
        root.right = rNode
        return root

node1 = TreeNode(1)
node1.left = TreeNode(0)
node1.right = TreeNode(2)

sol = Solution()
res = sol.trimBST(node1, 1, 2)
print(res)