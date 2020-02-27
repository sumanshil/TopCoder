import collections

class Solution(object):

    def ladderLength(self, beginWord, endWord, wordList):
        memo = collections.defaultdict(list)

        for word in wordList:
            for i in range(len(word)):
                memo[word[:i]+'*'+word[i+1:]].append(word)

        seen = set()
        q = collections.deque([(beginWord, 1)])

        while q:
            word, steps = q.popleft()

            if word == endWord:
                return steps

            for i in range(len(word)):

                for next_word in memo[word[:i]+'*'+word[i+1:]]:
                    if next_word not in seen:
                        q.append((next_word, steps+1))
                        seen.add(next_word)
        return 0


if __name__ == '__main__':
    wordList = ["hot","dot", "dog", "lot", "log"]

    sol = Solution()
    print(sol.ladderLength('hit', 'cog', wordList))