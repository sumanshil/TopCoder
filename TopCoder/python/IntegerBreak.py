class Solution:
    def integerBreak(self, n: int) -> int:
        if n <= 2:
            return 1
        if n == 3:
            return 2
        if n == 4:
            return 4

        dp = [0] * (n+1)
        for i in range(1,5):
            dp[i] = i

        for i in range(5, n+1):
            _max = 0
            for j in range(2, i-1):
                if dp[i-j] * j > _max:
                    _max = dp[i-j] * j

            dp[i] = _max

        return dp[n]

if __name__ == "__main__":
    sol = Solution()
    res = sol.integerBreak(20)
    print(res)