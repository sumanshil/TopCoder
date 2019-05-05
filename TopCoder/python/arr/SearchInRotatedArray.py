from typing import List

class Solution:
    def search(self, nums: List[int], target: int) -> int:
        if list is None or len(nums) == 0:
            return 0

        low = 0
        high = len(nums) - 1

        while low <= high :
            mid = int(low + (high - low) / 2)

            if nums[mid] == target:
                return mid

            if nums[low] <= nums[mid] :
                if nums[low] <= target <= nums[mid]:
                    high = mid-1
                else:
                    low = mid+1
            else:
                if nums[mid] <= target <= nums[high]:
                    high = mid+1
                else:
                    low = mid-1
        return -1

arr = [4,5,6,7,0,1,2]
sol = Solution()
res = sol.search(arr, 0)
print(res)