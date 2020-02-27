
class TreeNode:
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None


class Solution:
    def levelOrder(self, root):
        result = []
        parentList = []
        childList = []

        parentList.append(root)

        while parentList:
            res = []
            for node in parentList:
                res.append(node.val)
                if node.left:
                    childList.append(node.left)
                if node.right:
                    childList.append(node.right)
            result.append(res)
            parentList = childList
            childList = []
        return result

if __name__ == '__main__':
    node = TreeNode(3)
    node.left = TreeNode(9)
    node.right = TreeNode(20)
    node.right.left = TreeNode(15)
    node.right.right = TreeNode(7)
    sol = Solution()
    res = sol.levelOrder(node)
    for re in res:
        print(re)