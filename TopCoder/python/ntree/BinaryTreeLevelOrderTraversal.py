import queue

class TreeNode:
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None

class Solution:
    def levelOrderBottom(self, root):
        result = []
        if not root:
            return result


        q = queue.Queue()
        q.put(root)
        finalResult = []
        while not q.empty():
            size = q.qsize()

            currentResult = []
            for i in range(size):
                node = q.get()
                currentResult.append(node.val)

                if node.left:
                    q.put(node.left)

                if node.right:
                    q.put(node.right)

            finalResult.append(currentResult)

        return finalResult[::-1]

if __name__ == "__main__":
    root = TreeNode(3)
    root.left = TreeNode(9)

    root.right = TreeNode(20)

    root.right.left = TreeNode(15)
    root.right.right = TreeNode(7)
    sol = Solution()
    result = sol.levelOrderBottom(root)
    print(result)
