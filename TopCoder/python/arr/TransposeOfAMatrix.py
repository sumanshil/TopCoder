
class Solution:
    def transpose(self, A):
        retVal = [[A[j][i] for j in range(len(A))] for i in range(len(A[0]))]
        return retVal


matrix = [[1,2,3],[4,5,6],[7,8,9]]

cl = Solution()
res = cl.transpose(matrix)
i = 0
while i < len(res):
    print(res[i])
    i = i + 1