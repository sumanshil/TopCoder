from typing import List
import sys

class Solution:
    def canJump(self, nums: List[int]) -> bool:
        for i in range(0, len(nums)-1):
            if nums[i] == 0:
                continue
            if i + nums[i] >= len(nums)-1:
                return True
            return False


if __name__ == "__main__":
    nums = [2,3,1,1,4]
    sol = Solution()
    print(sol.canJump(nums))

