class Solution:
    def hammingDistance(self, x, y):
        z = x ^ y
        count = 0
        while z > 0:
            val = z & 1
            if val > 0:
                count = count + 1
            z = z >> 1

        return count


solution = Solution()
print(solution.hammingDistance(1, 4))