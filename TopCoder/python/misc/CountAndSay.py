class Solution:
    def countAndSay(self, n: int) -> str:
        s1 = "1"
        while n > 1:
            count = 1
            string_builder = ""

            string_length = 1

            while string_length <= len(s1):
                if string_length == len(s1) or s1[string_length] != s1[string_length-1]:
                    string_builder = string_builder + str(count) + s1[string_length-1]
                    count = 1
                else:
                    count = count + 1
                string_length = string_length + 1
            s1 = string_builder
            n = n - 1
        return s1

if __name__ == "__main__":
    sol = Solution()
    res = sol.countAndSay(3)
    print(res)