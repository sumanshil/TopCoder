
class ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None

class Solution:
    def removeNthFromEnd(self, head, n):
        start = head
        end = head
        count = 0

        while count < n:
            end = end.next
            count = count + 1

            if end is None:
                break

        if count < n:
            return head

        if end is None:
            return start.next

        while end is not None and end.next is not None:
            start = start.next
            end = end.next

        start.next = start.next.next

        return head


if __name__ == '__main__':

    head = ListNode(1)

    sol = Solution()
    res = sol.removeNthFromEnd(head, 1)
    print(res)



