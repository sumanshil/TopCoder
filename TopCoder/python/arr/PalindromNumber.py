class Solution:
    def isPalindrome(self, x: 'int') -> 'bool':
        original = x
        reversedNumber = 0
        if x < 0 :
            return False

        while x > 0:
            reversedNumber = (reversedNumber * 10) + int((x % 10))
            x = int(x / 10)

        return reversedNumber == original

sol = Solution()
res = sol.isPalindrome(121)
print(res)