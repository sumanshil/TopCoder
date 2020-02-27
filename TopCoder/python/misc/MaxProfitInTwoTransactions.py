class Solution:
    def maxProfit(self, prices):
        max_profit_suffixes = [None] * len(prices)
        max_price = float('-inf')
        max_profit = 0

        for i in range(len(prices)-1, -1, -1):
            if prices[i] > max_price:
                max_price = prices[i]
            elif max_price - prices[i] > max_profit:
                max_profit = max_price - prices[i]
            max_profit_suffixes[i] = max_profit


        max_profit = 0
        min_price = float('inf')
        global_max_profit = 0
        for i in range(len(prices)):
            if prices[i] < min_price:
                min_price = prices[i]
            elif prices[i] - min_price > max_profit:
                max_profit = prices[i] - min_price

            if i == len(prices)-1:
                overall_profit = max_profit
            else:
                overall_profit = max_profit + max_profit_suffixes[i+1]

            if overall_profit > global_max_profit:
                global_max_profit = overall_profit

        return global_max_profit

prices = [1,2,3,4,5]
sol = Solution()
result = sol.maxProfit(prices)
print(result)