
class Solution:
    def removeDuplicates(self, nums) -> int:
        """
        start_index = 1
        target_index = 1
        number_to_search = nums[1]

        while start_index < len(nums):
            if number_to_search == nums[start_index]:
                start_index = start_index + 1
                continue
            else:
                nums[target_index] = nums[start_index]
                number_to_search = nums[start_index]
                start_index = start_index + 1
                target_index = target_index + 1

        return target_index
        """
        if len(nums) < 2:
            return len(nums)

        curr = 0
        while curr + 1 < len(nums):
            if nums[curr] == nums[curr+1]:
                del nums[curr+1]
            else:
                curr += 1

        return len(nums)

if __name__ == '__main__':
    nums = [0,0,1,1,1,2,2,3,3,4]
    sol = Solution()
    res = sol.removeDuplicates(nums)
    print(res)
