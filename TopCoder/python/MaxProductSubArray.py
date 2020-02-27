
from typing import List
import sys

class Solution:
    def maxProduct(self, nums: List[int]) -> int:
        prevMax = 1
        prevMin = 1
        maxProduct = -sys.maxsize - 1

        for num in nums:
            curr_min = min(prevMin * num, prevMax * num, num)
            curr_max = max(prevMax * num, prevMin * num, num)
            maxProduct = max(maxProduct, curr_max)
            prevMax = curr_max
            prevMin = curr_min

        return maxProduct

if __name__ == "__main__":
    sol = Solution()
    res = sol.maxProduct([2,3,-2,4])
    print(res)
