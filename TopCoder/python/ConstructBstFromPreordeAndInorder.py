from typing import List

class TreeNode:
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None

class Solution:

    def __init__(self):
        self.preorderindex = 0
        self.inorderMap = {}

    def buildTree(self, preorder, inorder):
        resultNode = self.recursive(0, len(inorder) - 1, preorder, inorder)
        for i in range(0, len(inorder)):
            self.inorderMap[inorder[i]] = i
        return resultNode

    def recursive(self, inOrderStart, inOrderEnd, preorder, inorder):

        if inOrderStart > inOrderEnd:
            return -1

        rootElement = preorder[self.preorderindex]
        self.preorderindex += 1

        inOrderIndex = self.getInorderIndex(rootElement, inOrderStart, inOrderEnd, inorder)

        newRoot = TreeNode(rootElement)
        newRoot.left = self.recursive(inOrderStart, inOrderIndex-1,preorder, inorder)
        newRoot.right = self.recursive(inOrderIndex+1, inOrderEnd, preorder, inorder)

        return newRoot

    def getInorderIndex(self, rootElement, inOrderStart, inOrderEnd, inorder):
        return self.inorderMap.get(rootElement)


