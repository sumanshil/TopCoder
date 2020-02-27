from typing import List

class TreeNode:
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None


class Solution:
    def rightSideView(self, root):
        result: List[int] = []
        queue: List[TreeNode] = []

        if not root:
            return None

        queue.append(root)

        while queue:
            size: int = len(queue)

            index: int = 0

            while index < size:
                node: TreeNode = queue.pop(index)
                if node.left:
                    queue.append(node.left)
                if node.right:
                    queue.append(node.right)

                if index == 0:
                    result.append(node.val)
                index += 1

