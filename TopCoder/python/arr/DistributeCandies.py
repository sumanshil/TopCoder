class Solution:
    def distributeCandies(self, candies):
        setOfCandies = set(candies)
        maxCandies = len(candies)/2

        if maxCandies < len(setOfCandies):
            return maxCandies
        else:
            return len(setOfCandies)

arr = [1,1,2,2,3,3]
sol = Solution()
res = sol.distributeCandies(arr)
print(res)