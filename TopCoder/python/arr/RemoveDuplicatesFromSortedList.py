
from math import inf

class ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None


class Solution:
    def deleteDuplicates(self, head: ListNode) -> ListNode:
        if not head:
            return None
        if not head.next:
            return head
        fakeNode = ListNode(0)
        prev_val = inf

        current_temp = fakeNode

        while head is not None:
            if head.next is not None:
                if head.val == head.next.val or prev_val == head.val:
                    prev_val = head.val
                else:
                    current_temp.next = head
                    prev_val = head.val
                    current_temp = current_temp.next
            else:
                # last node
                if prev_val == head.val:
                    current_temp.next = None
                else:
                    current_temp.next = head
            prev_val = head.val
            head = head.next

        return fakeNode.next

if __name__ == "__main__":

    node = ListNode(1)
    node.next = ListNode(1)
    node.next.next = ListNode(2)
    #node.next.next.next = ListNode(2)
    #node.next.next.next.next = ListNode(3)

    sol = Solution()
    reesult = sol.deleteDuplicates(node)
    while reesult:
        print(reesult.val)
        reesult = reesult.next