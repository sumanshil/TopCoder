def maxProfit(prices):
    buy = 0
    m = float('inf')

    for price in prices:
        buy = max(buy, price - m)
        m = min(m, price)
    return buy


prices = [7, 1, 5, 3, 6, 4]
result = maxProfit(prices)
print(result)



