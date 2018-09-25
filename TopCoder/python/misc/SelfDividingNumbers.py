class Solution:
    def selfDividingNumbers(self, left, right):
        result = []
        for number in range(left, right + 1):
            strnumber = str(number)
            isselfdividing = True
            for s in strnumber:
                intnumber = int(s)
                if intnumber == 0:
                    isselfdividing = False
                    break
                if number % intnumber != 0:
                    isselfdividing = False
                    break

            if isselfdividing is True:
                result.append(number)
        return result

solution = Solution()
result = solution.selfDividingNumbers(1, 22)
print(result)