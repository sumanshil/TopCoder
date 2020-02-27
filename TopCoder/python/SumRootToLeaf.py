class TreeNode:
    def __init__(self, val):
        self.val = val
        self.left = None
        self.right = None

class Solution:
    def sumNumbers(self, root):
        result = []
        self.recursive(root, result, [])
        return sum(result)

    def recursive(self, root, result, current_path):
        if not root:
            return result

        if not root.left and not root.right:
            current_path += [str(root.val)]
            result.append(int("".join(current_path)))
            return result

        self.recursive(root.left, result, current_path + [str(root.val)])
        self.recursive(root.right, result, current_path + [str(root.val)])

root = TreeNode(1)
root.left = TreeNode(2)
root.right = TreeNode(3)

sol = Solution()
print(sol.sumNumbers(root))