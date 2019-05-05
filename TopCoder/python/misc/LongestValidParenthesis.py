class Solution:
    def longestValidParentheses(self, s: str) -> int:
        stack = []
        stack.append(-1)
        maxLength = 0
        for i, c in enumerate(s):
            if c == '(':
                stack.append(i)
            else:
                stack.pop()
                if stack:
                    maxLength = max(maxLength, i-stack[-1])
                else:
                    stack.append(i)

        return maxLength
str = '(()'
sol = Solution()
res = sol.longestValidParentheses(str)
print(res)