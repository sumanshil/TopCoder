import sys


class Solution:
    def binaryGap(self, N):
        """
        maxDistance = 0
        currentIndex = 0
        lastsetbitindex = -1
        while N > 0 :
            currentbit = N & 1
            if currentbit == 1 and lastsetbitindex != -1:
                maxDistance = max(maxDistance, currentIndex - lastsetbitindex)
            if currentbit == 1:
                lastsetbitindex = currentIndex

            currentIndex = currentIndex + 1
            N = N >> 1
        return maxDistance
        """
        big = 0
        b = [i for i, v in enumerate(bin(N)) if v == '1']
        for x, y in zip(b, b[1:]):
            big = max(big, (y-x))
        return big

sol = Solution()
res = sol.binaryGap(22)
print(res)
