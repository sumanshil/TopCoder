from typing import List

class TreeNode:
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None

class Solution:
    def preorderTraversal(self, root: TreeNode) -> List[int]:
        if not root:
            return []
        stack = []
        stack.append(root)
        result = []

        while stack:
            node = stack[0]
            stack = stack[1:]
            result.append(node.val)
            if node.right:
                stack.insert(0, node.right)

            if node.left:
                stack.insert(0, node.left)

        return result

if __name__ == "__main__":
    root = TreeNode(1)
    root.right = TreeNode(2)
    root.right.left = TreeNode(3)
    sol = Solution()
    print(sol.preorderTraversal(root))