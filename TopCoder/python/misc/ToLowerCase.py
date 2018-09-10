
class Solution:
    def toLowerCase(self, string):
        retVal = ''
        for s in string:
            if 'A' <= s <= 'Z':
                intVal = ord(s)
                newVal = intVal + 32
                retVal += (chr(newVal))
            else:
                retVal += s

        print(ord('A'))
        print(ord('a'))
        print(retVal)

sol = Solution()
sol.toLowerCase('Abc')
