class Solution:
    def isMatch(self, s, p):
        rowlength = len(s) + 1
        collength = len(p) + 1
        table = [[False]*collength for _ in range(rowlength)]
        table[0][0] = True

        for i in range(2, collength):
            table[0][i] = table[0][i-2] and p[i-1] == '*'

        for i in range(1, rowlength):
            for j in range(1, collength):
                if p[j-1] == '*':
                    if j > 1:
                        table[i][j] = table[i][j-2]

                    if p[j-2] == '.' or s[i-1] == p[j-2]:
                        table[i][j] = table[i][j] or table[i-1][j-1]
                elif p[j-1] == s[i-1] or p[j-1] == '.':
                    table[i][j] = table[i-1][j-1]

        return table[rowlength-1][collength-1]

sol = Solution()
res = sol.isMatch('ab', '.*')
print(res)