class Solution:
    def longestCommonPrefix(self, strs: 'List[str]') -> 'str':
        res = ""
        if strs == None or len(strs) == 0:
            return ""
        for i,val in enumerate(strs[0]):

            for str in strs[::1]:
                if i >= len(str) or val != str[i]:
                    return res

            res = res + val

        return res

arr = ['flower', 'flow', 'flight']
sol = Solution()
res = sol.longestCommonPrefix(arr)
print(res)