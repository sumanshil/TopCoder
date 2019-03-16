
class ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None

class Solution:
    def mergeTwoLists(self, l1: ListNode, l2: ListNode) -> ListNode:
        head = ListNode(-1)
        temphead = head

        while l1 is not None and l2 is not None:
            if l1.val == l2.val:
                temphead.next = l1
                l1 = l1.next
                temphead.next.next = l2
                l2 = l2.next
                temphead = temphead.next.next
            elif l1.val < l2.val:
                temphead.next = l1
                l1 = l1.next
                temphead = temphead.next
            else:
                temphead.next = l2
                l2 = l2.next
                temphead = temphead.next

        while l1 is not None:
            temphead.next = l1
            l1 = l1.next
            temphead = temphead.next

        while l2 is not None:
            temphead.next = l2
            l2 = l2.next
            temphead = temphead.next

        return head.next
