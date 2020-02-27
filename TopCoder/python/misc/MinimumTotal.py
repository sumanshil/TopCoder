class Solution:
    def minimumTotal(self, triangle):
        dp = []

        last_list = triangle[len(triangle)-1]

        for i in last_list:
            dp.append(i)

        index = len(last_list) - 2

        while index >= 0:
            current_list = triangle[index]

            for i, val in enumerate(current_list):
                dp[i] = val + min(dp[i], dp[i+1])

            index -= 1

        return dp[0]


if __name__ == '__main__':
    input = [[2], [3, 4], [6, 5, 7], [4, 1, 8, 3]]
    sol = Solution()
    result = sol.minimumTotal(input)
    print(result)