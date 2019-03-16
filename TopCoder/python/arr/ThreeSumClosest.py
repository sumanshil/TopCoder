
class Solution:
    def threeSumClosest(self, nums, target):
        nums.sort()
        minDiff = 1000
        result = 0
        for i in range(len(nums) - 2):

            low = i + 1
            high = len(nums) - 1

            found = False

            while low < high:
                sum = nums[i] + nums[low] + nums[high]

                diff = abs(target - sum)

                if diff == 0:
                    result = sum
                    found = True
                    break

                if diff < minDiff:
                    minDiff = diff
                    result = sum

                if sum < target:
                    low = low + 1
                else:
                    high = high - 1

            if found:
                break

        return result

nums = [-1, 2, 1, -4]
target = 1
sol = Solution()
res = sol.threeSumClosest(nums, target)
print(res)