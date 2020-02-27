
import collections
class Solution:
    def minDistance(self, word1: str, word2: str) -> int:
        deque = collections.deque([(0, word1, word2)])
        seen = set()

        while deque:
            dist, w1, w2 = deque.popleft()

            if w1 == w2:
                return dist

            if (w1, w2) not in seen:
                seen.add((w1, w2))
            while w1 and w2 and w1[0] == w2[0]:
                w1 = w1[1:]
                w2 = w2[1:]
            else:
                deque.append((dist + 1, w1[1:], w2[1:]))
                deque.append((dist + 1, w1, w2[1:]))
                deque.append((dist + 1, w1[1:], w2))

if __name__ == '__main__':
    sol = Solution()
    res = sol.minDistance("park", "spake")
    print(res)