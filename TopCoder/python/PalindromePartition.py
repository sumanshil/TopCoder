class Solution:
    def minCut(self, s: str) -> int:
        n = len(s)
        cut, dp = [-1] + [n] * n, [False] * n
        for i in range(n):
            for j in range(i+1):
                if s[i] == s[j] and (j+1 > i or dp[j+1]):
                    dp[j] = True
                    cut[i+1] = min(cut[i+1], cut[j] + 1)
                else:
                    dp[j] = False
        return cut[-1]

if __name__ == "__main__":
    sol = Solution()
    res = sol.minCut("abbc")
    print(res)