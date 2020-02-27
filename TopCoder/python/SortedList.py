class ListNode:
    def __init__(self, x):
        self.val = x
        self.next:ListNode = None


def merge(first_half: ListNode, second_half: ListNode) -> ListNode:

    dummy: ListNode = ListNode(-1)
    current: ListNode = dummy

    while first_half and second_half:
        if first_half.val < second_half.val:
            current.next = first_half
            first_half = first_half.next
            current = current.next
        else:
            current.next = second_half
            second_half = second_half.next
            current = current.next

    if first_half:
        current.next = first_half

    if second_half:
        current.next = second_half
    return dummy.next


class Solution:
    def sortList(self, head: ListNode) -> ListNode:
        if not head or not head.next:
            return head

        mid: ListNode = self.findMid(head)
        firstHalf : ListNode = self.sortList(head)
        secondHalf: ListNode = self.sortList(mid)

        return merge(firstHalf, secondHalf)

    def findMid(self, head) -> ListNode:
        slow: ListNode = head
        fast: ListNode = head

        while fast and fast.next:
            fast = fast.next.next
            if not fast or not fast.next:
                temp: ListNode = slow.next
                slow.next = None
                return temp
            else:
                slow = slow.next
        return slow


if __name__ == "__main__":
    head: ListNode = ListNode(4)
    head.next = ListNode(2)
    head.next.next = ListNode(1)
    head.next.next.next = ListNode(3)
    sol = Solution()
    result = sol.sortList(head)
    print(result)


