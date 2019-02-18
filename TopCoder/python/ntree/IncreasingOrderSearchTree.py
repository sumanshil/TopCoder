
class TreeNode:
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None


class Solution:
    def increasingBST(self, root):
        """
        :type root: TreeNode
        :rtype: TreeNode
        """
        newRoot = None
        prevNode = None
        stack = []

        while root or stack:
            while root:
                stack.append(root)
                root = root.left

            root = stack.pop()

            if newRoot is None:
                newRoot = prevNode = root
            elif prevNode:
                root.left = None
                prevNode.right = prevNode = root

            root = root.right

        return newRoot

node5 = TreeNode(5)
node3 = TreeNode(3)
node6 = TreeNode(6)
node5.left = node3
node5.right = node6
node2 = TreeNode(2)
node4 = TreeNode(4)
node3.left = node2
node3.right = node4
node1 = TreeNode(1)
node2.left = node1
node8 = TreeNode(8)
node6.right = node8
node7 = TreeNode(7)
node9 = TreeNode(9)
node8.left = node7
node8.right = node9

sol = Solution()
newTreeNode = sol.increasingBST(node5)

print(newTreeNode)
