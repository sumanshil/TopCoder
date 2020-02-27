class TreeNode:
    def __init__(self, value):
        self.val = value
        self.left = None
        self.right = None

class Solution:
    max_value = float('-inf')
    def maxPathSum(self, root):
        self.recursive(root)
        return self.max_value

    def recursive(self, root):
        if not root:
            return 0

        l_sum = self.recursive(root.left)
        r_sum = self.recursive(root.right)

        total_value = root.val + l_sum + r_sum

        if total_value < 0:
            self.max_value = max(self.max_value, total_value)
            return 0

        self.max_value = max(self.max_value, total_value)
        return root.val + max(l_sum, r_sum)

if __name__ == '__main__':
    root = TreeNode(2)
    root.left = TreeNode(1)
    root.right = TreeNode(3)

    sol = Solution()
    print(sol.maxPathSum(root))