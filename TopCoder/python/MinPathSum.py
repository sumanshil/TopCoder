from typing import List

class Solution:
    def minPathSum(self, grid: List[List[int]]) -> int:
        colLength = len(grid[0])
        rowLength = len(grid)
        dp = [[0]*colLength for _ in range(rowLength)]

        for i in range(0, rowLength):
            for j in range(0, colLength):
                if i == 0 and j == 0:
                    dp[i][j] = grid[i][j]
                elif i == 0:
                    dp[i][j] = grid[i][j] + dp[i][j-1]
                elif j == 0:
                    dp[i][j] = grid[i][j] + dp[i-1][j]
                else:
                    dp[i][j] = grid[i][j] + min(dp[i-1][j], dp[i][j-1])

        return dp[rowLength-1][colLength-1]

if __name__ == "__main__":
   matrix = [
       [1,3,1],
       [1,5,1],
       [4,2,1]
   ]
   sol = Solution()
   res = sol.minPathSum(matrix)
   print(res)