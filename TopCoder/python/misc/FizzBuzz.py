class Solution:
    def fizzBuzz(self, n):
        """
        :type n: int
        :rtype: List[str]
        """
        i = 1
        retval = []
        while i <= n :
            if i % 3 == 0 and i % 5 == 0:
                retval.append("FizzBuzz")
            elif i % 3 == 0:
                retval.append("Fizz")
            elif i % 5 == 0:
                retval.append("Buzz")
            else:
                retval.append(str(i))
            i = i + 1
        return retval

sol = Solution()
ret = sol.fizzBuzz(1)
print(ret)