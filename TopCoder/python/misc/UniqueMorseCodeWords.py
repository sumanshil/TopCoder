class Solution:
    def uniqueMorseRepresentations(self, words):
        codes = [".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."]
        resultcodes = []
        for word in words:
            result = ''
            for c in word:
                index = ord(c) - ord('a')
                result += codes[index]
            resultcodes.append(result)
        return len(set(resultcodes))


if __name__ == "__main__":
    sol = Solution()
    words = ["gin", "zen", "gig", "msg"]
    res = sol.uniqueMorseRepresentations(words)
    print(res)