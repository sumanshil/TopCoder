class Node:
    def __init__(self, val, next, random):
        self.val = val
        self.next = next
        self.random = random

class Solution:

    def copyRandomList(self, head: 'Node') -> 'Node':
        """
        current = head
        clonedHead = None
        while current:
            cloned = Node(0, None, None)
            if not clonedHead:
                clonedHead = cloned
            cloned.val = current.val
            nextNode = current.next
            current.next = cloned
            cloned.next = nextNode
            current = nextNode

        current = head

        while current:
            if current.random:
                current.next.random = current.random.next
            current = current.next.next

        current = head
        while current:
            nextNode = current.next.next
            if nextNode:
                current.next.next = nextNode.next
            current.next = nextNode
            current = nextNode

        return clonedHead
        """

        map = {}
        clonedHead: 'Node' = None
        clonedPrev: 'Node' = None
        current = head
        while current:
            newCloned = Node(0, None, None)
            map[current] = newCloned
            if not clonedHead:
                clonedHead = newCloned
            newCloned.val = current.val
            if clonedPrev:
                clonedPrev.next = newCloned
            clonedPrev = newCloned
            current = current.next

        for key, value in map.items():
            cloned = value
            original = key
            if original.random:
                cloned.random = map[original.random]

        return clonedHead


if __name__ == "__main__":
    node1 = Node(1, None, None)

    node2 = Node(2, None, None)

    node1.next = node2
    node1.random = node2

    node2.next = None
    node2.random = node2

    sol = Solution()
    res = sol.copyRandomList(node1)
    print(res)