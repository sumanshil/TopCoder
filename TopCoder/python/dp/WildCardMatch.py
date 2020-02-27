class Solution:
    def isMatch(self, s: str, p: str) -> bool:
        dp = []
        for i in range(len(s)+1):
            dp.append([])
            for j in range(len(p)+1):
                dp[i].append(False)

        print(dp)

        dp[0][0] = True

        for i in range(1, len(p)+1):
            if p[i-1] == '*':
                dp[0][i] = dp[0][i-1]

        for i in range(1, len(s) + 1):
            for j in range(1, len(p) + 1):
                if p[j-1] == '?' or p[j-1] == s[i-1]:
                    dp[i][j] = dp[i-1][j-1]
                if p[j-1] == '*':
                    dp[i][j] = dp[i-1][j] or dp[i][j-1]

        return dp[len(s)][len(p)]


sol = Solution()
res = sol.isMatch("aa", "a")
print(res)