class Solution:
    def reverseWords(self, s: str) -> str:
        """
        s = s.strip()
        space_count = 0
        result = []
        stack = []

        for i in range(len(s) - 1, -1, -1):
            if s[i] != ' ':
                stack.append(s[i])
                space_count = 0
            else:
                if space_count == 0:
                    while stack:
                        result.append(stack.pop())
                    result.append(' ')
                    space_count += 1
        while stack:
            result.append(stack.pop())

        return ''.join(result)
        """
        s = s.strip()
        words = s.split(" ")
        newsent = ""

        for word in words[::-1]:
            if word == " " or not word:
                continue
            newsent = newsent + " " + word

        return newsent.strip()


if __name__ == "__main__":
    sol = Solution()
    result = sol.reverseWords("a good   example")
    print(result)
