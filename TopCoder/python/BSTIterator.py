from typing import List

class TreeNode:
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None

class BSTIterator:

    def __init__(self, root: TreeNode):
        self.current: TreeNode = root
        self.stack: List[TreeNode] = []

    def next(self) -> int:
        """
        @return the next smallest number
        """
        next_val: TreeNode
        if self.current or self.stack:
            while self.current:
                self.stack.append(self.current)
                self.current = self.current.left
            next_val = self.stack.pop()
            self.current = next_val.right
        return next_val.val   


    def hasNext(self) -> bool:
        """
        @return whether we have a next smallest number
        """
        return self.current is not None or len(self.stack) > 0

if __name__ == "__main__":
    root = TreeNode(7)
    root.left = TreeNode(3)
    
    root.right = TreeNode(15)
    root.right.left = TreeNode(9)
    root.right.right = TreeNode(20)
    iterator = BSTIterator(root)
    print(iterator.next())
    print(iterator.next())
    print(iterator.hasNext())
    print(iterator.next())
    print(iterator.hasNext())
    print(iterator.next())
    print(iterator.hasNext())
    print(iterator.next())
    print(iterator.hasNext())