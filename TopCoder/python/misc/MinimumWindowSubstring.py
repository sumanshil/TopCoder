from collections import defaultdict, Counter
from math import inf

class Solution:
    def minWindow(self, s, t) :
        ans = ""
        n = len(s)
        t_cnt = Counter(t)
        cnt = defaultdict(lambda : 0)
        min_len = inf
        covered = 0
        start = 0

        for index, c in enumerate(s):
            cnt[c] += 1

            if c in t_cnt and  cnt[c] == t_cnt[c]:
                covered += 1

            while start < n:
                c = s[start]
                if c not in t_cnt or cnt[c] > t_cnt[c]:
                    cnt[c] -= 1
                    start += 1
                else:
                    break

            end_len = (index - start) + 1
            if covered == len(t_cnt) and end_len < min_len:
                min_len = end_len
                ans = s[start:(index+1)]

        return ans
if __name__ == "__main__":
    str = "aaaaaaaaaaaabbbbbcdd"
    pattern = "abcdd"
    sol = Solution()
    sol.minWindow(str, pattern)