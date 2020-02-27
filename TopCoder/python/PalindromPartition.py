from typing import List

class Solution:

    """
    def partition(self, s: str) -> List[List[str]]:
        i = 0
        dp = []

        while i < len(s):
            list_false = [False] * len(s)
            dp.append(list_false)
            i += 1
        i = 0
        level = 0
        while level < len(s):
            i = 0
            while i + level < len(s):
                j = i + level
                if i == j:
                    dp[i][j] = True
                elif i + 1 == j and s[i] == s[j]:
                    dp[i][j] = True
                elif s[i] == s[j] and dp[i+1][j-1]:
                    dp[i][j] = True
                i += 1
            level += 1
        result = []
        current = []
        self.util(0, s, result, current, dp)
        return result

    def util(self, index: int, s: str, result: List[List[str]], current: List[str], dp: List[List[bool]]):
        if index >= len(s):
            result.append(current[:])
            return

        level = 0
        while index + level < len(s):
            index1 = index
            index2 = index + level
            if dp[index1][index2]:
                sub_str = s[index1:index2+1]
                current.append(sub_str)
                self.util(index2+1, s, result, current, dp)
                del current[len(current) - 1]
            level += 1
    """
    """
    def partition(self, s: str) -> List[List[str]]:
        stack = [list(s)]
        dic = {}
        ret = []
        while(stack):
            lst = stack.pop()
            size = len(lst)
            h = ' '.join(lst)
            if h not in dic:
                dic[h] = True
                ret.append(lst)
                for i in range(size):
                    if(i>0 and lst[i]==lst[i-1]):
                        stack.append(lst[:i-1]+[lst[i-1]+lst[i]]+lst[i+1:])
                    if(i>1 and lst[i]==lst[i-2]):
                        stack.append(lst[:i-2]+[lst[i-2]+lst[i-1]+lst[i]]+lst[i+1:])
        return ret
    """
    """
    def partition(self, s):
        self.memo = {}
        return self.helper(s)

    def helper(self, s):
        if not s: return []
        if len(s) == 1: return [[s]]
        if s in self.memo:
            return self.memo[s]
        ans = []
        for i in range(1, len(s)):
            l, r = s[:i], s[i:]
            if l == l[::-1]:
                for sub in self.helper(r):
                    ans.append([l, *sub])
        if s == s[::-1]:
            ans.append([s])
        self.memo[s] = ans
        return ans
    """
    def partition(self, s):
        self.memo = {}
        return self.helper(s)

    def helper(self, s):
        if not s: return []

        if len(s) == 1: return [[s]]

        if s in self.memo:
            return self.memo[s]

        ans = []

        for i in range(1, len(s)):
            l, r = s[:i], s[i:]
            if l == l[::-1]:
                for sub in self.helper(r):
                    ans.append([l, *sub])
        if s == s[::-1]:
            ans.append([s])
        self.memo[s] = ans
        return ans

if __name__ == "__main__":
    str_input = "abb"
    sol = Solution()
    result = sol.partition(str_input)
    print(result)
