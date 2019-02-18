
class Solution:
    """
    def uncommonFromSentences(self, A, B):
        dict = {}

        arr = A.split(' ')

        for str in arr:
            dict[str] = dict.get(str, 0) + 1

        arr = B.split(' ')
        for str in arr:
            dict[str] = dict.get(str, 0) + 1

        result = [k for k, v in dict.items() if v == 1]
        return result
    """
    def uncommonFromSentences(self, A, B):
        da, db = {}, {}

        for wa in A.split(' '):
            da[wa] = da.get(wa, 0) + 1

        for wb in B.split(' '):
            db[wb] = db.get(wb, 0) + 1

        return [a for a in da if da[a] == 1 and (a not in db)] + [b for b in db if db[b] == 1 and (b not in da)]

A = 'this apple is sweet'
B = 'this apple is sour'

sol = Solution()
res = sol.uncommonFromSentences(A, B)
print(res)