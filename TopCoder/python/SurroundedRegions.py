
from typing import List

class Solution:
    def solve(self, board: List[List[str]]) -> None:
        row_length: int = len(board)
        if row_length == 0:
            return
        col_length: int = len(board[0])

        col: int = 0
        visited: List[List[bool]] = []
        for i in range(row_length):
            visited.append([])
            for j in range(col_length):
                visited[i].append(False)

        while col < col_length:
            if board[0][col] == 'O':
                self.recursive(board, 0, col, visited)
            col += 1

        col: int = col_length - 1
        while col >= 0:
            if board[row_length-1][col] == 'O':
                self.recursive(board, row_length-1, col, visited)
            col -= 1

        row = 0

        while row < row_length:
            if board[row][col_length-1] == 'O':
                self.recursive(board, row, col_length-1, visited)
            row += 1

        row = row_length-1

        while row >= 0:
            if board[row][0] == 'O':
                self.recursive(board, row, 0, visited)
            row -= 1

        i: int = 0
        j: int = 0

        while i < row_length:
            j = 0
            while j < col_length:

                if board[i][j] == 'Y':
                    board[i][j] = 'O'
                else:
                    board[i][j] = 'X'
                j += 1
            i += 1

        i: int = 0
        j: int = 0

        while i < row_length:
            j = 0
            while j < col_length:
                print(board[i][j] + " ")
                j += 1
            i += 1
            print('\n')

    def recursive(self, board: List[List[str]], row: int, col: int, visited: List[List[bool]]):

        if not Solution.isvalid(board, row, col):
            return

        if visited[row][col]:
            return

        visited[row][col] = True
        board[row][col] = 'Y'
        self.recursive(board, row+1, col, visited)
        self.recursive(board, row-1, col, visited)
        self.recursive(board, row, col+1, visited)
        self.recursive(board, row, col-1, visited)


    @staticmethod
    def isvalid(board: List[List[str]], row: int, col: int) -> bool:
        return 0 <= row < len(board) and 0 <= col <= len(board[0]) and board[row][col] != 'X' and board[row][col] != 'Y'


if __name__ == '__main__':
    sol = Solution()
    board : List[List[str]] = [
    ['X', 'X', 'X', 'X'],
    ['X', 'O', 'O', 'X'],
    ['X', 'X', 'O', 'X'],
    ['X', 'O', 'X', 'X']
    ]
    sol.solve(board)