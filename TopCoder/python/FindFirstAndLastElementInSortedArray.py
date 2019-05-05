from typing import List

class Solution:

    def searchRange(self, nums: List[int], target: int) -> List[int]:
        if not nums or len(nums) == 0:
            return [-1, -1]

        first_index = self.find_first_index(nums, 0, len(nums)-1, target)
        if first_index == -1:
            return [-1, -1]

        second_index = self.find_second_index(nums, first_index, len(nums)-1, target)
        return [first_index, second_index]

    def find_first_index(self, nums, low, high, target):
        if low > high:
            return -1

        mid = int(low + (high - low)/2)

        if nums[mid] == target and (mid - 1 < 0 or nums[mid - 1] != target):
            return mid

        if nums[mid] < target:
            return self.find_first_index(nums, mid + 1, high, target)
        else:
            return self.find_first_index(nums, low, mid - 1, target)

    def find_second_index(self, nums, low, high, target):
        if low > high:
            return -1

        mid = int(low + (high - low)/2)
        if nums[mid] == target and (mid + 1 >= len(nums) or nums[mid + 1] != target):
            return mid

        if nums[mid] <= target:
            return self.find_second_index(nums, mid + 1, high, target)
        else:
            return self.find_second_index(nums, low, mid - 1, target)

nums = [2,2]
target = 2
sol = Solution()
result = sol.searchRange(nums, target)
for i, val in enumerate(result):
    print(val)