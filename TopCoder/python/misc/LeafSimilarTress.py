class TreeNode:
     def __init__(self, x, left, right):
         self.val = x
         self.left = left
         self.right = right

class Solution:
    def leafSimilar(self, root1, root2):
        """
        :type root1: TreeNode
        :type root2: TreeNode
        :rtype: bool
        """
        leaves = []
        leaves1 = []
        self.dfs(root1, leaves)
        self.dfs(root2, leaves1)

        for i, element in enumerate(leaves):
            if element != leaves1[i]:
                return False

        return True

    def dfs(self, root, leaves):
        if root is None:
            return

        if root.left is None and root.right is None:
            leaves.append(root.val)

        self.dfs(root.left, leaves)
        self.dfs(root.right, leaves)

    def check(self, root2, leaves):
        if root2 is None:
            return True
        if root2.left is None and root2.right is None:
            if len(leaves) == 0:
                return False
            elif root2.val == leaves[0]:
                return self.check(root2.left, leaves[1:len(leaves)]) and\
                       self.check(root2.right, leaves[1:len(leaves)])
            else:
                return False

        return self.check(root2.left, leaves) and \
               self.check(root2.right, leaves)

def buildTree():
    node6 = TreeNode(6, None, None)

    node7 = TreeNode(7, None, None)
    node4 = TreeNode(4, None, None)

    node9 = TreeNode(9, None, None)
    node8 = TreeNode(8, None, None)

    node2 = TreeNode(2, node7, node4)

    node5 = TreeNode(5, node6, node2)

    node1 = TreeNode(1, node9, node8)

    node3 = TreeNode(3, node5, node1)

    return node3

sol = Solution()

tree1 = buildTree()
tree2 = buildTree()
res = sol.leafSimilar(tree1, tree2)
print(res)