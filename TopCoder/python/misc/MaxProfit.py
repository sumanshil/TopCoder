class Solution:
    def maxProfit(self, prices):
        min_so_far = prices[0]
        max_so_far = min_so_far
        result = 0
        for i in range(1, len(prices)):
            if prices[i] < max_so_far:
                result += max_so_far - min_so_far
                min_so_far = prices[i]
                max_so_far = min_so_far
            elif prices[i] > max_so_far:
                max_so_far = prices[i]

        if max_so_far != min_so_far:
            result += max_so_far - min_so_far

        return result


prices = [1,2,3,4,5]
sol = Solution()
result1 = sol.maxProfit(prices)
print(result1)