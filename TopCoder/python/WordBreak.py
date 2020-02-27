from typing import List

class Solution:
    def wordBreak(self, s: str, wordDict: List[str]) -> bool:
        #res = self.recursive(0, s, wordDict)
        dp = [False] * (len(s) + 1)
        dp[0] = True

        map = {}
        for word in wordDict:
            len_word = len(word)
            list = map.get(len_word, [])
            list.append(word)
            map[len_word] = list

        index = 1
        while index <= len(s):
            for key, value in map.items():
                if key <= index:
                    suffix = index - key
                    sub_str = s[suffix: suffix + key]
                    if dp[suffix] and map.get(len(sub_str), None) and sub_str in map.get(len(sub_str)):
                        dp[index] = True
            index += 1
        return dp[len(dp) - 1]



    def recursive(self, index: int, s: str, wordDict: List[str]) -> bool:
        if index >= len(s):
            return True

        for word in wordDict:
            sub_str = s[index: index + len(word)]
            if sub_str == word:
                if self.recursive(index+len(word), s, wordDict):
                    return True

        return False


    def isPresent(self, s: str, wordDict: List[str]) -> bool:
        try:
            if wordDict.index(s, 0, len(wordDict)) >= 0:
                return True
        except ValueError:
            return False

if __name__ == '__main__':
    sol = Solution()
    res = sol.wordBreak("catsandog",["cats", "dog", "sand", "and", "cat"])
    #res = sol.wordBreak("leetcode", ["leet", "code"])
    print(res)