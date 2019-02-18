
class Solution:
    """
    def shortestToChar(self, S, C):
        res = [1 for x in range(len(S))]
        n = len(S)
        pos = -n
        for i in range(len(S)):
            if S[i] == C:
                pos = i

            res[i] = i - pos

        for i in range(len(S)-1, -1, -1):
            if S[i] == C:
                pos = i

            res[i] = min(res[i], abs(pos - i))

        return res
    """

    def shortestToChar(self, S, C):
        result = []

        indexes = []

        for i in range(len(S)):
            if S[i] == C:
                indexes.append(i)
        index = 0
        lower = indexes[index]
        upper = indexes[index]

        for i in range(len(S)):
            if i < lower:
                result.append(lower - i)
            elif lower < i < upper:
                result.append(min(i-lower, upper-i))
            elif lower > upper:
                result.append(i-upper)
            elif i == lower:
                if index + 1 < len(indexes):
                    lower = upper
                    result.append(0)
                    index = index+1
                    upper = indexes[index]
                else:
                    upper = lower
            elif i == upper:
                result.append(0)
                if index + 1 < len(indexes):
                    lower = upper
                    index = index+1
                    upper = indexes[index]
                else:
                    lower = upper
        return result

S = "loveleetcode"
C = 'e'

sol = Solution()
resultNew = sol.shortestToChar(S, C)

print(resultNew)
