class Solution:
    def removeElement(self, nums, val: int) -> int:
       # nums.sort()
       """
       if not nums or len(nums) == 0:
           return 0
       if len(nums) == 1 and nums[0] == val:
           return 0
       if len(nums) == 1 and nums[0] != val:
           return 1

       start_index = 0
       last_index = len(nums) - 1
       last_seen_index = 0
       while start_index <= last_index:
           if nums[start_index] == val and nums[last_index] != val:
               last_seen_index = start_index
               temp = nums[start_index]
               nums[start_index] = nums[last_index]
               nums[last_index] = temp
               start_index = start_index + 1
               last_index = last_index - 1
           elif nums[start_index] != val:
               last_seen_index = start_index
               start_index = start_index + 1
           else:
               last_index = last_index - 1
       if last_seen_index == 0 and nums[last_seen_index] == val:
           return last_seen_index
       return last_seen_index+1
       """
       #for i in range(len(nums)):
       #    print(len(nums))
       #    print(i)
       #    del nums[i]
       start, end = 0, len(nums) - 1

       while start <= end:
           if nums[start] == val:
               nums[start], nums[end], end = nums[end], nums[start], end-1
           else:
               start = start + 1

       return start




if __name__ == '__main__':
    nums = [4,5]
    sol = Solution()
    res = sol.removeElement(nums, 4)
    print(res)


