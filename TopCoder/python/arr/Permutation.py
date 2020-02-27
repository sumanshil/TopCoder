class Solution:
    def permute(self, nums):
        l = len(nums)
        if l == 1:
            return [nums]


        a = nums[0]
        small = self.permute(nums[1:])

        ret = set()
        for thisL in small:
            for i in range(len(thisL) + 1):
                ret.add(thisL[:i]+[a]+thisL[i:])
        return ret

nums = [1,2,3]
sol = Solution()
res = sol.permute(nums)
for list in res:
    print(list)