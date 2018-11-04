class Node(object):
    def __init__(self, val):
        self.val = val
        self.children = []

    def setchildren(self, children):
        self.children = children

class Solution:
    def preorder(self, root):
        if root is None:
            return []
        stack = []
        stack.append(root)
        result = []
        while len(stack) > 0:
            top = stack.pop()
            result.append(top.val)
            for child in top.children[::-1]:
                stack.append(child)
        return result

    def recursive(self, root):
        output = []

        def helper(root):
            output.append(root.val)
            for child in root.children:
                helper(child)

        if root:
            helper(root)
        return output

solution = Solution();
node1 = Node(1)
node3 = Node(3)
node2 = Node(2)
node4 = Node(4)
node5 = Node(5)
node6 = Node(6)
child = [node3, node2, node4]
node1.setchildren(child)
child = [node5, node6]
node3.setchildren(child)
result = solution.recursive(node1)
print(result)