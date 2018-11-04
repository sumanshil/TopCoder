class TreeNode:
    def __init__(self, x):
        self.value = x
        self.left = None
        self.right = None

    def searchInBst(self, root, value):
        if root == None:
            return None

        if root.value == value:
            return root

        result = self.searchInBst(root.left, value)
        if result == None:
            result = self.searchInBst(root.right, value)

        return result

    def inOrder(self, root):
        if root != None:
            self.inOrder(root.left)
            print(root.value)
            self.inOrder(root.right)


if __name__ == "__main__":
    root = TreeNode(4)
    root.left = TreeNode(2)
    root.right = TreeNode(7)
    root.left.left = TreeNode(1)
    root.left.right = TreeNode(3)

    result = root.searchInBst(root, 2)
    result.inOrder(result)