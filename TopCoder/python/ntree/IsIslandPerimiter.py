class Solution:
    def islandPerimeter(self, grid):
        """
        :type grid: List[List[int]]
        :rtype: int
        """
        row = len(grid)
        col = len(grid[0])

        result = 0

        for r in range(row):
            for c in range(col):
                if grid[r][c] == 1:
                    if r + 1 >= row or grid[r+1][c] == 0:
                        result = result + 1
                    if r - 1 < 0 or grid[r-1][c] == 0:
                        result = result + 1
                    if c + 1 >= col or grid[r][c+1] == 0:
                        result = result + 1
                    if c - 1 < 0 or grid[r][c-1] == 0:
                        result = result + 1

        return result

grid = [[0,1,0,0],
        [1,1,1,0],
        [0,1,0,0],
        [1,1,0,0]]
#sol = Solution()
#res = sol.islandPerimeter(grid)
#print(res)

from operator import itemgetter
print(itemgetter(1)('ABCDEFG'))
print(itemgetter(1, 3, 5)('ABCDEFG'))
print(itemgetter(slice(2,None))('ABCDEFG'))
soldier = dict(rank='captain', name='dotterbart')
print(itemgetter('rank')(soldier))