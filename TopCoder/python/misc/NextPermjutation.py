from typing import List

class Solution:
    def nextPermutation(self, nums: List[int]) -> None:
        """
        Do not return anything, modify nums in-place instead.


        index_to_swap = -1
        for i in range(len(nums)-1):
            if nums[i] < nums[i+1]:
                index_to_swap = i

        if index_to_swap == -1:
            nums.sort()
            return

        next_greater_index = -1
        i = len(nums)-1
        while i > index_to_swap:
            if next_greater_index == -1 and nums[i] > nums[index_to_swap]:
                next_greater_index = i
            elif nums[index_to_swap] < nums[i] < nums[next_greater_index]:
                next_greater_index = i
            i = i - 1

        if next_greater_index != -1:
            temp = nums[next_greater_index]
            nums[next_greater_index] = nums[index_to_swap]
            nums[index_to_swap] = temp

            nums[next_greater_index+1:] = sorted(nums[next_greater_index+1:])
         """

        index_to_swap = len(nums) - 2

        while index_to_swap >= 0:
            if nums[index_to_swap] < nums[index_to_swap + 1]:
                break
            index_to_swap = index_to_swap - 1

        if index_to_swap == -1:
            nums[0:] = nums[0:][::-1]
            return

        greater_value_index = len(nums) - 1

        while greater_value_index > index_to_swap:
            if nums[greater_value_index] > nums[index_to_swap]:
                nums[greater_value_index], nums[index_to_swap] = nums[index_to_swap], nums[greater_value_index]
                break
            greater_value_index = greater_value_index - 1

        nums[index_to_swap+1:] = nums[index_to_swap+1:][::-1]



nums = [1, 3, 2]
sol = Solution()
sol.nextPermutation(nums)
print(nums)