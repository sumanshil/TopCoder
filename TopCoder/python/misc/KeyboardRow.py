

class Solution:
    def findWords(self, words):
        keyMap = {}
        keyMap["Q"] = 0
        keyMap["W"] = 0
        keyMap["E"] = 0
        keyMap["R"] = 0
        keyMap["T"] = 0
        keyMap["Y"] = 0
        keyMap["U"] = 0
        keyMap["I"] = 0
        keyMap["O"] = 0
        keyMap["P"] = 0

        keyMap["A"] = 1
        keyMap["S"] = 1
        keyMap["D"] = 1
        keyMap["F"] = 1
        keyMap["G"] = 1
        keyMap["H"] = 1
        keyMap["J"] = 1
        keyMap["K"] = 1
        keyMap["L"] = 1

        keyMap["Z"] = 2
        keyMap["X"] = 2
        keyMap["C"] = 2
        keyMap["V"] = 2
        keyMap["B"] = 2
        keyMap["N"] = 2
        keyMap["M"] = 2

        result = []

        for word in words:
            value = -1
            found = True
            for c in word:
                if value == -1:
                    value = keyMap.get(c.upper())
                elif value != keyMap.get(c.upper()):
                    found = False
                    break
            if found:
                result.append(word)

        return result

words = ["Hello", "Alaska", "Dad", "Peace"]

sol = Solution()
result = sol.findWords(words)
print(result)