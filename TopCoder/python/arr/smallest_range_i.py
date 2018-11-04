class Solution:
    def smallestRangeI(self, A, K):
        min_val = min(A)
        max_val = max(A)

        avg = int((max_val + min_val)/2)

        if abs(max_val-avg) <= K and abs(avg-min_val) <= K:
            return 0
        return (max_val-K) - (K+min_val)


A = [10, 7, 1]
K = 3
solution = Solution()
result = solution.smallestRangeI(A, K)
print(result)