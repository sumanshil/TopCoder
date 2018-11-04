
class Solution:
    def reverserWords(self, s):
        result = ""
        for index, content in enumerate(s.split(" ")):
            result = result+content[::-1]+" "
        return result.rstrip()

sol = Solution()
ret = sol.reverserWords("Let's take LeetCode contest")
print(ret)