
class Solution:
    def numJewelsInStones(self, J, S):
        result = 0
        j_set = set(J)
        for s in S:
            if j_set.__contains__(s):
                result = result + 1
        return result


s = Solution()
res = s.numJewelsInStones("aA", "aAAbbbb")
print(res)