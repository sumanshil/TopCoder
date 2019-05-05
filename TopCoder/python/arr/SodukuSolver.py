class Solution:
    def solveSoduku(self, board):
        self.board = board
        self.solve()

    def findUnoccupiedPlace(self):
        for row in range(9):
            for col in range(9):
                if self.board[row][col] == ".":
                        return row, col
        return -1, -1

    def solve(self):
        row, col = self.findUnoccupiedPlace()
        if row == -1 and col == -1:
            return True

        if self.board[row][col] == ".":
            for char in ["1", "2", "3", "4", "5", "6", "7", "8", "9"]:
                if self.isValid(row, col, char):
                    self.board[row][col] = char
                    if self.solve():
                        return True
                    else:
                        self.board[row][col] = "."
        return False

    def isValid(self, row, col, char):
        start_row = row - int(row % 3)
        start_col = col - int(col % 3)
        for i in range(9):
            if self.board[i][col] == char or self.board[row][i] == char or self.board[start_row + int(i/3)][start_col + int(i%3)] == char:
                return False
        return True

if __name__ == "__main__":
   board = [["5","3",".",".","7",".",".",".","."],
     ["6",".",".","1","9","5",".",".","."],
     [".","9","8",".",".",".",".","6","."],
     ["8",".",".",".","6",".",".",".","3"],
     ["4",".",".","8",".","3",".",".","1"],
     ["7",".",".",".","2",".",".",".","6"],
     [".","6",".",".",".",".","2","8","."],
     [".",".",".","4","1","9",".",".","5"],
     [".",".",".",".","8",".",".","7","9"]]
   sol = Solution()
   res = sol.solveSoduku(board)
   print(res)