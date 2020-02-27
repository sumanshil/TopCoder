class Solution:
    def getRow(self, rowIndex):
        if rowIndex == 0:
            return [1]
        if rowIndex == 1:
            return [1, 1]

        pre = self.getRow(rowIndex-1)

        for i in range(len(pre)-1):
            pre[i] = pre[i] + pre[i+1]

        return [1] + pre

sol = Solution()
print(sol.getRow(3))