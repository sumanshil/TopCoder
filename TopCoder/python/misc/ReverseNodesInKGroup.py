

class ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None

class Solution:
    def swapPairs(self, head: ListNode) -> ListNode:
        newHead = None
        current = head
        currNext = None
        prevNode = None

        while current:
            if not current.next:
                if not newHead:
                    newHead = current
                    break

            currNext = current.next
            current.next = currNext.next
            currNext.next = current

            if not newHead:
                newHead = currNext

            if prevNode:
                prevNode.next = currNext

            prevNode = current
            current = current.next

        return newHead

if __name__ == '__main__':
    start = ListNode(1)
    start.next = ListNode(2)
    start.next.next = ListNode(3)
    start.next.next.next = ListNode(4)

    sol = Solution()
    res = sol.swapPairs(start)
    while res:
        print(res.val)
        res = res.next

