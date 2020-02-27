
class TreeNode:
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None

class Solution:

    def __init__(self):
        self.postIndex = 0
        self.inmap = {}

    def buildTree(self, inorder, postorder):
        self.postIndex = len(postorder)-1
        for index, val in enumerate(inorder):
            self.inmap[val] = index

        return self.recursive(0, len(inorder) - 1, inorder, postorder)

    def recursive(self, instart, inend, inorder, postorder):
        if instart > inend:
            return None

        element = postorder[self.postIndex]
        self.postIndex -= 1
        inmapindex = self.inmap[element]
        root = TreeNode(element)
        root.right = self.recursive(inmapindex+1, inend, inorder, postorder)
        root.left = self.recursive(instart, inmapindex-1, inorder, postorder)
        return root

if __name__ == '__main__':
    sol = Solution()
    inorder = [9,3,15,20,7]
    postorder = [9,15,7,20,3]

    result = sol.buildTree(inorder, postorder)
    print(result)
