from typing import List

class Solution:
    def findRepeatedDnaSequences(self, s: str) -> List[str]:
        seen = {s[:10]: 1}
        prev = s[:10]
        more_than_once = []
        for l in s[10:]:
            new = prev[1:] + l
            if new in seen: more_than_once.append(new)
            seen[new] = 1
            prev = new
        return more_than_once

if __name__ == "__main__":
    str_input = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
    sol = Solution()
    print(sol.findRepeatedDnaSequences(str_input))
