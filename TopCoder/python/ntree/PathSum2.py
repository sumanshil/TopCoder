class TreeNode:
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None


class Solution:
    def pathSum(self, root: TreeNode, sum):
        results = []
        result = []
        self.helper1(root, [], results, 0, sum)
        return results

    def helper(self, root, result, results, currentSum, sum):
        if not root:
            return
        if not root.left and not root.right:
            if currentSum == sum:
                results.append(result)
            return

        if root.left:
            self.helper(root.left, result + [root.left.val], results, currentSum + root.left.val, sum)
        if root.right:
            self.helper(root.right, result + [root.right.val], results, currentSum + root.right.val, sum)

    def helper1(self, root, so_far, results, currentSum, sum):
        if not root:
            return
        if not root.left and not root.right and sum == root.val:
            so_far.append(root.val)
            results.append(so_far[:])
            so_far.pop()
            return
        so_far.append(root.val)
        if root.left:
            self.helper1(root.left, so_far, results, currentSum , sum - root.val)
        if root.right:
            self.helper1(root.right, so_far, results, currentSum, sum - root.val)
        so_far.pop()

if __name__ == '__main__':
    root = TreeNode(5)
    root.left = TreeNode(4)
    root.right = TreeNode(8)

    root.left.left = TreeNode(11)
    root.left.left.left = TreeNode(7)
    root.left.left.right = TreeNode(2)

    root.right.left = TreeNode(13)
    root.right.right = TreeNode(4)
    root.right.right.left = TreeNode(5)
    root.right.right.right = TreeNode(1)

    sol = Solution()
    res = sol.pathSum(root, 22)
    print(res)
