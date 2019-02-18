

class Solution:
    def reverseString(self, s):
        """
        :type s: str
        :rtype: str
        """
        i = 0
        length = len(s)
        arr = list(s)

        while i < length/2:
            arr[length-1], arr[i] = arr[i], arr[length-1]
            length = length - 1

        return ''.join(arr)

str = "A man, a plan, a canal: Panama"
sol = Solution()
res = sol.reverseString(str)
print(res)
