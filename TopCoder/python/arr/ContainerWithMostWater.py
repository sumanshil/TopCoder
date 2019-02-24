class Solution:
    def maxArea(self, height: 'List[int]') -> 'int':
        low = 0
        high = len(height)-1

        maxval = 0

        while low < high:
            maxval = max(maxval, (high-low)*min(height[low], height[high]))
            if height[low] < height[high]:
                low = low + 1
            else:
                high = high - 1

        return maxval

arr = [1,8,6,2,5,4,8,3,7]
sol = Solution()
res = sol.maxArea(arr)
print(res)