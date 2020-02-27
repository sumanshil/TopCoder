from typing import List

class Solution:
    def largestRectangleArea(self, heights):
        stack = [-1]
        max_area = 0

        for i in range(len(heights)):
            if stack[-1] == -1 or heights[stack[-1]] < heights[i]:
                stack.append(i)
            else:
                while heights[stack[-1]] > heights[i] and stack[-1] != -1:
                    max_area = max(max_area, heights[stack.pop()]*((i - stack[-1]) - 1))
                stack.append(i)

        while stack[-1] != -1:
            max_area = max(max_area, heights[stack.pop()]*((len(heights) - stack[-1]) - 1))
        return max_area

heights = [2, 1, 5, 6, 2, 3]
sol = Solution()
res = sol.largestRectangleArea(heights)
print(res)