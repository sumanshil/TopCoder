
class TreeNode:
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None


class Solution:
    def maxDepth(self, root):
        """
        if root is None:
            return 0

        lDepth = self.maxDepth(root.left)
        rDepth = self.maxDepth(root.right)

        return max(lDepth, rDepth) + 1
        """
        if root is None:
            return 0

        stack = [(root, 1)]
        maxdepth = 0

        while len(stack) > 0:
            item = stack.pop()
            currnode = item[0]
            currdepth = item[1]
            maxdepth = max(currdepth, maxdepth)

            if currnode.left:
                stack.append((currnode.left, currdepth+1))

            if currnode.right:
                stack.append((currnode.right, currdepth+1))
        return maxdepth





if __name__ == "__main__":
    root = TreeNode(3)
    root.left = TreeNode(9)
    root.right = TreeNode(20)

    root.left.right = TreeNode(15)
    root.right.right = TreeNode(7)

    sol = Solution()
    res = sol.maxDepth(root)
    print(res)