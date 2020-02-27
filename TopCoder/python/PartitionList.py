class ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None

class Solution:
    def partition(self, head: ListNode, x: int) -> ListNode:
        more = ListNode(0)
        moreHead = more

        less = ListNode(0)
        lessHead = less

        while head:
            if x > head.val:
                less.next = head
                less = head
            else:
                more.next = head
                more = head
            head = head.next

        less.next = moreHead.next
        more.next = None
        return lessHead.next

if __name__ == '__main__':
    node = ListNode(3)
    node.next = ListNode(1)
    node.next.next = ListNode(2)
    res = Solution().partition(node, 3)

    while res:
        print(res.val)
        res = res.next