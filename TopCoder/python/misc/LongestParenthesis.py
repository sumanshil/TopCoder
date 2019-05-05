class Solution:
    def longestValidParentheses(self, s):
        left = -1
        maxlen = 0
        stack = []

        for i, c in enumerate(s):
            if c == '(':
                stack.append(i)
            else:
                if stack:
                    stack.pop()
                    if not stack:
                        maxlen = max(maxlen, i - left)
                    else:
                        maxlen = max(maxlen, i - stack[-1])
                else:
                    stack = []
                    left = i

        return maxlen
s = "((()))(())"
sol = Solution()
res = sol.longestValidParentheses(s)
print(res)