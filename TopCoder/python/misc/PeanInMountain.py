class Solution:
    def peakIndexInMountainArray(self, A):
        max = A[0]
        index = 0
        currentindex = 0
        for element in A:
            if element > max:
                max = element
                index = currentindex
            currentindex = currentindex + 1
        return index
sol = Solution()
res = sol.peakIndexInMountainArray([0, 1, 0])
print(res)