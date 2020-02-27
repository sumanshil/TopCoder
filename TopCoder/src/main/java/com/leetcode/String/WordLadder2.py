from typing import List
import collections

class Solution:
    min = 100000
    def findLadders(self, beginWord: str, endWord: str, wordList: List[str]) -> List[List[str]]:
        graph = collections.defaultdict(set)
        seen = set()
        result = []
        queue = []
        word_set = set(wordList)

        if endWord not in word_set:
            return []

        found = False
        alphabets = 'abcdefghijklmnopqrstuvwxyz'
        while not found and len(queue) > 0:
            next = []

            for word in queue:
                for i in range(len(word)):
                    for j in alphabets:
                        key = word[:i] +j + word[i+1:]
                        if key in word_set:
                            graph[word].add(key)

                            if key not in seen:
                                seen.add(key)
                                next.append(key)

            queue = next

        current = [beginWord]


    def util(self, begin_word, end_word, graph, current, result):
        if begin_word == end_word:
            if len(current) > self.min:
                result.append(current)

