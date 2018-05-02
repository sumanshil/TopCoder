import sys
def minPathSum( grid):
        M, N = len(grid), len(grid[0])
        dp = [0] + [sys.maxsize] * (N-1)
        for i in range(M):
            dp[0] = dp[0] + grid[i][0]
            for j in range(1, N):
                dp[j] = min(dp[j-1], dp[j]) + grid[i][j]
        return dp[-1]

def minPathSum1( grid):
        M, N = len(grid), len(grid[0])
        dp = [0] + [sys.maxsize] * (N-1)
        for i in range(M):
            dp[0] = dp[0] + grid[i][0]
            for j in range(1, N):
                dp[j] = min(dp[j-1],dp[j]) + grid[i][j]
        return dp[-1]


matrix = [
    [1,3,1],
    [1,5,1],
    [4,2,1]
]
res = minPathSum1(matrix)
print(res)