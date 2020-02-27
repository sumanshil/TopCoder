class Node:
    def __init__(self, val, neighbors):
        self.val = val
        self.neighbors = neighbors

class Solution:
    oldToNewMap = {}
    def cloneGraph(self, node: 'Node') -> 'Node':
        if not node:
            return None
        if node in Solution.oldToNewMap:
            return Solution.oldToNewMap[node]

        clonedNode = Node(node.val, [])
        Solution.oldToNewMap[node] = clonedNode
        for neighbor in node.neighbors:
            clonedNode.neighbors.append(self.cloneGraph(neighbor))

        return clonedNode


if __name__ == '__main__':

    node_1 = Node(1, [])
    node_2 = Node(2, [])
    node_3 = Node(3, [])
    node_4 = Node(4, [])

    node_1.neighbors.append(node_2)
    node_2.neighbors.append(node_1)

    node_1.neighbors.append(node_4)
    node_4.neighbors.append(node_1)

    node_2.neighbors.append(node_3)
    node_3.neighbors.append(node_2)

    node_4.neighbors.append(node_3)
    node_3.neighbors.append(node_4)

    sol = Solution()
    res = sol.cloneGraph(node_1)
    print(res)
