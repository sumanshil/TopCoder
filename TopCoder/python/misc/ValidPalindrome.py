class Solution:
    def isPalindrome(self, s):

        start = 0
        end = len(s)-1
        while start < end:
            if not self.isAlpha(s[start]):
                start = start + 1
                continue

            if not self.isAlpha(s[end]):
                end = end - 1
                continue

            startChar = s[start].lower()
            endChar = s[end].lower()

            if startChar != endChar:
                return False
            start = start + 1
            end = end -1
        return True

    def isAlpha(self, ch):
        return (ch >= 'A' and ch <= 'Z') or (ch >= 'a' and ch <= 'z') or (ch >= '0' and ch <= '9')

if __name__ == '__main__':
    s = 'A man, a plan, a canal: Panama'
    sol = Solution()
    result = sol.isPalindrome(s)
    print(result)
    print(4/2)
    print(4//2)