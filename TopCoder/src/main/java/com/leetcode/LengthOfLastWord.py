class Solution:
    def lengthOfLastWord(self, s: str) -> int:
        #s1 = s.strip()
        count = 0
        for i in range(len(s) - 1, -1, -1):
            if s[i] == ' ' and count > 0:
                break
            elif s[i] == ' ':
                continue
            count += 1

        return count

s = ' '
sol = Solution()
res = sol.lengthOfLastWord(s)
print(res)