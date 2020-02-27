# Definition for singly-linked list.
class ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None

# Definition for a binary tree node.
class TreeNode:
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None



class Solution:
    def sortedListToBST(self, head: ListNode) -> TreeNode:

        result = self.helper(head)
        return result

    def helper(self, head: ListNode) -> TreeNode:
        if not head:
            return None

        if not head.next:
            return TreeNode(head.val)

        midNode = self.getMid(head)
        treeNode = TreeNode(midNode.val)
        treeNode.left = self.helper(head)
        treeNode.right = self.helper(midNode.next)

        return treeNode


    def getMid(self, head: ListNode) -> ListNode:
        fast = head
        slow = head
        pre = None

        while fast and fast.next:
            pre = slow
            slow = slow.next
            fast = fast.next.next

        pre.next = None
        return slow

if __name__ == '__main__':
    head = ListNode(-10)
    head.next = ListNode(-3)
    head.next.next = ListNode(0)
    head.next.next.next = ListNode(5)
    head.next.next.next.next = ListNode(9)

    sol = Solution()
    root = sol.sortedListToBST(head)
    print(root)
