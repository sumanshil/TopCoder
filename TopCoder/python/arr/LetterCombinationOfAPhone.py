class Solution:
    def letterCombinations(self, digits: str):

        if digits is None or len(digits) == 0:
            return []

        mapcodes = {'2': 'abc', '3': 'def', '4': 'ghi', '5': 'jkl', '6': 'mno', '7': 'pqrs', '8': 'tuv', '9': 'wxyz'}
        strcodes = mapcodes.get(digits[0])
        result = [s for s in strcodes]

        for i in range(1, len(digits)):
            newstr = mapcodes.get(digits[i])
            result = self.combinations(result, newstr)

        return result

    def combinations(self, resultlist, str):
        newresult = []
        for s1 in resultlist:
            for slist in str:
                newresult.append(s1+slist)
        return newresult


sol = Solution()
res = sol.letterCombinations('23')
for i in res:
    print(i)