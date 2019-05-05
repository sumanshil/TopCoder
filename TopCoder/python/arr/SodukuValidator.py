from typing import List

class Solution:
    def isValidSudoku(self, board: List[List[str]]) -> bool:
        row_map = [set() for i in range(9)]
        col_map = [set() for j in range(9)]
        cube_map = [[set() for i in range(3)] for j in range(3)]

        for i in range(9):
            for j in range(9):
                entry = board[i][j]
                if entry == ".":
                    continue
                if entry in row_map[i] or entry in col_map[j] or entry in cube_map[i//3][j//3]:
                    return False

                row_map[i].add(entry)
                col_map[j].add(entry)
                cube_map[i//3][j//3].add(entry)

        return True

soduku =[
        ["8","3",".",".","7",".",".",".","."],
        ["6",".",".","1","9","5",".",".","."],
        [".","9","8",".",".",".",".","6","."],
        ["8",".",".",".","6",".",".",".","3"],
        ["4",".",".","8",".","3",".",".","1"],
        ["7",".",".",".","2",".",".",".","6"],
        [".","6",".",".",".",".","2","8","."],
        [".",".",".","4","1","9",".",".","5"],
        [".",".",".",".","8",".",".","7","9"]
    ]
sol = Solution()
res = sol.isValidSudoku(soduku)
print(res)

