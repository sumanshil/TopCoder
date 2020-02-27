from typing import List

class Solution:
    def setZeroes(self, matrix: List[List[int]]) -> None:
        """
        Do not return anything, modify matrix in-place instead.
        """
        rows = set()
        cols = set()

        for i in range(0, len(matrix)):
            for j in range(0, len(matrix[0])):
                if not matrix[i][j]:
                    rows.add(i)
                    cols.add(j)

        for i in range(0, len(matrix)):
            for j in range(0, len(matrix[0])):
                if i in rows or j in cols:
                    matrix[i][j] = 0

if __name__ == '__main__':
    matrix = [
        [1, 1, 1],
        [1, 0, 1],
        [1, 1, 1]
    ]
    sol = Solution()
    sol.setZeroes(matrix)

    for i in range(0, len(matrix)):
        for j in range(0, len(matrix[0])):
            print(matrix[i][j])
