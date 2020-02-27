
class ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None


class Solution:
    def rotateRight(self, head: ListNode, k: int) -> ListNode:
        if k == 0 or head is None:
            return head

        current_node = head
        count = 1

        while current_node.next is not None:
            count += 1
            current_node = current_node.next

        k = k%count
        k = count - k

        step = 0
        current_node.next = head
        while step < k:
            current_node = current_node.next
            head = head.next
            step += 1

        current_node.next = None
        return head

if __name__ == '__main__':

    node = ListNode(1)
    node.next = ListNode(2)
    node.next.next = ListNode(3)
    node.next.next.next = ListNode(4)
    node.next.next.next.next = ListNode(5)

    sol = Solution()
    result = sol.rotateRight(node, 2)
    while result is not None:
        print(result.val)
        result = result.next

