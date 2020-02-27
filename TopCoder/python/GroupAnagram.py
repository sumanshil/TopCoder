from typing import List

class Solution:
    def groupAnagrams(self, strs: List[str]) -> List[List[str]]:

        def convert(s):
            res = [0]*26
            for char in s:
                res[ord(char)-ord('a')] += 1
            return tuple(res)
        rec = {}

        for s in strs:
            t = convert(s)
            if t in rec:
                rec.get(t).append(s)
            else:
                res = []
                res.append(s)
                rec[t] = res

        return list(rec.values())

sol = Solution()
result = sol.groupAnagrams(["eat", "tea", "tan", "ate", "nat", "bat"])
print(result)