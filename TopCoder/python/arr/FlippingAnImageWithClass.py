
class Solution:
    matrix = []

    def flipAndInvertImage(self, A):
        i = 0
        while i < len(A):
            start = 0
            end = len(A) - 1
            while start < end:
                temp = A[i][start]
                A[i][start] = A[i][end]
                A[i][end] = temp
                start = start + 1
                end = end - 1
            i = i + 1

        i = 0
        while i < len(A):
            j = 0
            while j < len(A[i]):
                if A[i][j] == 1:
                    A[i][j] = 0
                else:
                    A[i][j] = 1
                j = j + 1
            i = i + 1
        return A

    def printMatrix(self):
        index1 = 0
        while index1 < len(self.matrix):
            index2 = 0
            while index2 < len(self.matrix[index1]):
                print(self.matrix[index1][index2])
                index2 = index2 + 1
            index1 = index1 + 1
            print("\n")


matrix = [[1,1,0,0],[1,0,0,1],[0,1,1,1],[1,0,1,0]]

cl = Solution()
cl.flipAndInvertImage(matrix)
