from typing import List
from queue import PriorityQueue

class ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None

    def __str__(self):
        return str(self.val)

class Solution:
    def mergeKLists(self, lists: List[ListNode]) -> ListNode:
        sorted_list_head = sorted_list_tail = ListNode(0)

        pq = PriorityQueue()

        def add_node_queue(idx, node):
            if node:
                pq.put((node.val, idx, node))

        for idx, node in enumerate(lists):
            add_node_queue(idx, node)

        while not pq.empty():
            _, idx, node = pq.get()
            add_node_queue(idx, node.next)
            sorted_list_tail.next = node
            sorted_list_tail = sorted_list_tail.next
        return sorted_list_head.next


if __name__ == '__main__':
    node0 = ListNode(1)
    node0.next = ListNode(4)
    node0.next.next = ListNode(5)

    node1 = ListNode(1)
    node1.next = ListNode(3)
    node1.next.next = ListNode(4)

    node2 = ListNode(2)
    node2.next = ListNode(6)

    numList : List[ListNode] = [node0, node1, node2]
    res = Solution().mergeKLists(numList)

    while res:
        print(str(res))
        res = res.next