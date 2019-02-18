from dataclasses import dataclass

class Solution:
    def numSpecialEquivGroups(self, A):
        """
        dict = {}
        for word in A:
            evens = []
            odds = []
            for i, c in enumerate(word):
                if i % 2 == 0:
                    evens.append(c)
                else:
                    odds.append(c)

            evens.sort()
            odds.sort()

            s = ''.join(evens)
            s = s + ''.join(odds)

            if dict.__contains__(s):
                dict[s] = dict.get(s) + 1
            else:
                dict[s] = 1

        return len(dict)
        """
        """
        def sort(str):
            l = len(str)
            if l == 1:
                return 1
            else:
                even = ''.join(sorted([str[i] for i in range(0, l, 2)]))
                odd = ''.join(sorted([str[i] for i in range(1, l, 2)]))
                return even+odd

        allKeys = {}

        for a in A:
            res = sort(a)
            if res not in allKeys:
                allKeys[res] = 1

        return len(allKeys)
        """
        B = set()
        for str in A:
            even, odd = str[::2], str[1::2]
            B.add((''.join(sorted(even)), ''.join(sorted(odd))))
        return len(B)

list = ["abc","acb","bac","bca","cab","cba"]
sol = Solution()
res = sol.numSpecialEquivGroups(list)
print(res)