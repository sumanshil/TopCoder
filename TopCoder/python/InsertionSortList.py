import sys

class ListNode:
    def __init__(self, x):
        self.val = x
        self.next:ListNode = None

class Solution:
    def insertionSortList(self, head: ListNode) -> ListNode:
        dummyHead = ListNode(-sys.maxsize -1)
        dummyHead.next = head

        prev: ListNode = dummyHead
        next: ListNode = dummyHead.next

        while next:
            if prev.val > next.val:
                prev.next = next.next
                next.next = None
                self.addAtRightPlace(dummyHead, next)
                next = prev.next
            else:
                prev = next
                next = prev.next

        return dummyHead.next

    def addAtRightPlace(self, dummyHead, next):
        current: ListNode = dummyHead
        while current.next:
            if current.next.val > next.val:
                next.next = current.next
                current.next = next
                return
            current = current.next


if __name__ == "__main__":
    head: ListNode = ListNode(4)
    head.next = ListNode(2)
    head.next.next = ListNode(1)
    head.next.next.next = ListNode(3)
    res = Solution().insertionSortList(head)
    print(res)
