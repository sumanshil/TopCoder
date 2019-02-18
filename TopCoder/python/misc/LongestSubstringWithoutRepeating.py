

class Solution:
    def lengthOfLongestSubstring(self, s):
        """
        map = {}
        currentlength = 0
        maxlength = 0
        currentstart = 0
        for i, val in enumerate(s):
            if val not in map:
                currentlength = currentlength + 1
            else:
                maxlength = max(maxlength, currentlength)
                lastseen = map.get(val)
                if lastseen >= currentstart:
                    currentstart = lastseen + 1
                    currentlength = (i - currentstart) + 1
            map[val] = i
            if currentlength > 1:
                maxlength = max(currentlength, maxlength)
        return maxlength
        """
        res = 0
        sub = ''
        for char in s:
            if char not in sub:
                sub = sub + char
                res = max(res, len(sub))
            else:
                cut = sub.index(char)
                sub = sub[cut+1:]+char
        return len(sub)
sol = Solution()
res = sol.lengthOfLongestSubstring("abab")
print(res)
