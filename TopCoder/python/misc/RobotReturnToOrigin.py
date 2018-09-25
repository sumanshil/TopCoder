class Solution:
    def judgeCircle(self, moves):
        numberoflefts = 0
        numberofrights = 0
        numberofups = 0
        numberofdowns = 0
        for m in moves:
            if m == 'U':
                numberofups = numberofups + 1
            if m == 'D':
                numberofdowns = numberofdowns + 1
            if m == 'L':
                numberoflefts = numberoflefts + 1
            if m == 'R':
                numberofrights = numberofrights + 1

        return numberofups == numberofdowns and numberoflefts == numberofrights

sol = Solution()
result = sol.judgeCircle("LL")
print(result)