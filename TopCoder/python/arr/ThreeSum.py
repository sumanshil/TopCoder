class Solution:
    def threeSum(self, nums):
        nums.sort()
        result = []

        for i in range(len(nums)-2):

            if  i > 0 and nums[i] == nums[i-1]:
                continue

            target = 0 - nums[i]

            low = i + 1
            high = len(nums) - 1

            while low < high:
                if nums[low] + nums[high] == target:
                    result.append([nums[i], nums[low], nums[high]])
                    while low + 1 < len(nums) and nums[low] == nums[low + 1]:
                        low = low + 1
                    while high - 1 >= 0 and nums[high] == nums[high-1]:
                        high = high - 1

                    low = low + 1
                    high = high - 1
                elif nums[low] + nums[high] > target:
                    high = high - 1
                else:
                    low = low + 1

        return result





sol = Solution()
res = sol.threeSum([0, 0, 0])
for list in res:
    for number in list:
        print(number)
    print("========")

