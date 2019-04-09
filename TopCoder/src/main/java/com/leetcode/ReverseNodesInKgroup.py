class ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None

class Solution:

    def reverseKGroup(self, head: ListNode, k: int) -> ListNode:
        """
        prev_node = ListNode(-1)
        current = ListNode(-1)
        while head:
            new_head, next_start = self.reverse(head, k)
            if current.val == -1:
                current = new_head
            prev_node.next = new_head
            prev_node = head
            head = next_start

        if current.val == -1:
            return  None
        else:
            return current
        """
        dummy = jump = ListNode(0)
        dummy.next = l = r = head
        while 1:
            count = 0
            while r and count < k:
                r = r.next
                count += 1
            pre, cur = r, l
            if count == k:
                for _ in range(k):
                    cur.next, cur, pre = pre, cur.next, cur
                jump.next, jump, l = pre, l, r
            else:
                return dummy.next


    def reverse(self, head, k):
        if not head:
            return None, None

        if not head.next:
            return head, None

        if k == 1:
            return head, head.next

        curr_next = head
        prev_node = None
        count = 1
        while count <= k:
            if curr_next:
                curr_next = head.next
                head.next = prev_node
                prev_node = head
                head = curr_next
            else:
                return self.reverse(prev_node, count-1)

            count = count + 1
        return prev_node, curr_next

if __name__ == '__main__':
    node0 = ListNode(1)
    node0.next = ListNode(2)
    node0.next.next = ListNode(3)
    node0.next.next.next = ListNode(4)
    node0.next.next.next.next = ListNode(5)

    sol = Solution()
    ret = sol.reverseKGroup(node0, 2)

    while ret:
        print(ret.val)
        ret = ret.next
