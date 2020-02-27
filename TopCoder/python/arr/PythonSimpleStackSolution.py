from typing import List

class Solution:
    def merge(self, intervals: List[List[int]]) -> List[List[int]]:
        stack = []
        intervals.sort(key= lambda i : i[0])

        for time in intervals:
            if len(stack) == 0:
                stack.append(time)
            else:
                if time[0] <= stack[-1][1]:
                    previous = stack.pop()
                    maxEnd = max(time[1], previous[1])
                    stack.append([previous[0], maxEnd])
                else:
                    stack.append(time)
        return stack

if __name__ == "__main__":
    arr = [[1,4],[0,4]]
    sol = Solution()
    sol.merge(arr)