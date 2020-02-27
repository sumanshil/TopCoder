from typing import List

class Solution:
    def uniquePathsWithObstacles(self, obstacleGrid: List[List[int]]) -> int:
        if obstacleGrid[0][0] or obstacleGrid[-1][-1]:
            return 0
        row_length = len(obstacleGrid)
        col_length = len(obstacleGrid[0])

        dp = [[0] * col_length for _ in  range(row_length)]

        for i in range(0, row_length):
            for j in range(0, col_length):
                if i == 0 and j == 0:
                    dp[i][j] = 1
                elif i == 0 and not obstacleGrid[i][j]:
                    dp[i][j] = dp[i][j-1]
                elif j == 0 and not obstacleGrid[i][j]:
                    dp[i][j] = dp[i-1][j]
                elif not obstacleGrid[i][j]:
                    dp[i][j] = dp[i-1][j] + dp[i][j-1]

        return dp[row_length-1][col_length-1]

if __name__ == '__main__':
    matrix = [
        [0,0,0],
        [0,1,0],
        [0,0,0]
    ]
    sol = Solution()
    res = sol.uniquePathsWithObstacles(matrix)
    print(res)
