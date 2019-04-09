class ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None

class Solution:
    def removeElements(self, head: ListNode, val: int) -> ListNode:

        if not head:
            return None
        if head.val == val:
            return self.removeElements(head.next, val)

        prev_node = head
        current = head.next
        while current:
            if current.val == val:
                prev_node.next = current.next
            else:
                prev_node = current
            current = current.next
        return head
if __name__ == '__main__':
    head = ListNode(1)
    head.next = ListNode(2)
    head.next.next = ListNode(6)
    head.next.next.next = ListNode(3)
    head.next.next.next.next = ListNode(4)
    head.next.next.next.next.next = ListNode(5)
    head.next.next.next.next.next.next = ListNode(6)
    sol = Solution()
    res = sol.removeElements(head, 6)
    while res:
        print(res.val)
        res = res.next
