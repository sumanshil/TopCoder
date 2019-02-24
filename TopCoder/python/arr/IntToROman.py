class Solution:
    def intToRoman(self, num: 'int') -> 'str':
        tuples = [(1000, 'M'), (900, 'CM'),  (500, 'D'),(400, 'CD'), (100, 'C'),(90, 'XC'),(50, 'L'), (40, 'XL'), (10, 'X'),(9, 'IX'),(5, 'V'), (4, 'IV'),(1, 'I')]

        res = ""

        for tuple in tuples:
            while num >= tuple[0]:
                num = num - tuple[0]
                res = res + tuple[1]
        return res

sol = Solution()
print(sol.intToRoman(3999))