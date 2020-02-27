from typing import List

class Solution:

    def exist(self, board, word) -> bool:
        for i in range(0, len(board)):
            for j in range(0, len(board[0])):
                if self.dfs(board, word, 0, i, j):
                    return True
        return False

    def dfs(self, board, word, index, row, col) -> bool:
        if index >= len(word):
            return False
        print("Index " , index)
        if board[row][col] == word[index]:
            board[row][col] = '$'
            if index == len(word)-1 or (row > 0 and self.dfs(board, word, index+1, row-1, col) or (row < len(board)-1 and self.dfs(board,word,index+1, row+1, col)) or (col > 0 and self.dfs(board, word, index + 1, row, col-1) or (col < len(board[0])-1 and self.dfs(board, word, index + 1, row, col+1)))):
                return True
            else:
                board[row][col] = word[index]
        return False

if __name__ == '__main__':
    matrix = [['A','B','C','E'],
              ['S','F','E','S']
             ,['A','D','E','E']]
    str =  'ABCESEEEFS'
    sol = Solution()
    res = sol.exist(matrix, str)
    print(res)