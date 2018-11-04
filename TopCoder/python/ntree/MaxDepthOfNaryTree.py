import sys


class Node(object):
    def __init__(self, val, children):
        self.val = val
        self.children = children


class Solution(object):
    def maxDepth(self, root):
        """
        :type root: Node
        :rtype: int
        """
        """
        if root is None:
            return 0

        maxdepthvalue = -sys.maxsize - 1

        for child in root.children:
            maxdepthvalue = max(maxdepthvalue, self.maxDepth(child))

        if maxdepthvalue == -sys.maxsize - 1:
            return 1
        else:
            return maxdepthvalue + 1
        """
        """
        currentlist = [root]
        nextlist = []
        count = 1

        while currentlist:
            count = count + 1

            while currentlist:
                node = currentlist.pop(0)

                for child in node.children:
                    nextlist.append(child)

            currentlist = nextlist

        return count
        """
        if root is None:
            return 0

        max_depth = 1
        queue = [(1, root)]

        while len(queue):
            depth, node = queue.pop()
            max_depth = max(max_depth, depth)
            queue.extend([(depth+1, child) for child in node.children])

        return max_depth



node5 = Node(5, [])
node6 = Node(6, [])
node3 = Node(3, [node5, node6])
node2 = Node(2, [])
node4 = Node(4, [])
node1 = Node(1, [node3, node2, node4])
sol = Solution()
res = sol.maxDepth(node1)
print(res)