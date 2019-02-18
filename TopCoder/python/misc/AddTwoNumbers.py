class ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None

class Solution:
    def addTwoNumbers(self, l1, l2):
        next = lambda x: x.val if x else 0

        c = 0
        node = ListNode(0)
        start = node

        while l1 or l2 or c:
            sumval = next(l1) + next(l2) + c
            c = 0
            node.next = ListNode(sumval % 10)

            if sumval >= 10:
                c = sumval // 10
            if l1:
                l1 = l1.next
            if l2:
                l2 = l2.next
            node = node.next
        return start.next

l1 = ListNode(2)
l1.next = ListNode(4)
l1.next.next = ListNode(3)

l2 = ListNode(5)
l2.next = ListNode(6)
l2.next.next = ListNode(4)

sol = Solution()
res = sol.addTwoNumbers(l1, l2)
print(res)