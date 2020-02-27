
from typing import List


class Solution:

    def longestConsecutive(self, nums: List[int]) -> int:

        nums_set = set(nums)
        max_value = 0
        for num in nums:
            if num-1 not in nums_set:
                next = num
                while next in nums_set:
                    next = next + 1

                max_value = max(max_value, (next - num))

        return max_value

if __name__ == '__main__':
    sol = Solution()
    res = sol.longestConsecutive([100, 4, 200, 1, 3, 2])
    print(res)