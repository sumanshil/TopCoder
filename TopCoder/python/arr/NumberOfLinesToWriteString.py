import string

class Solution:
    def numberOfLines(self, widths, S):
        numberoflines = 0
        currentlettercount = 0
        for s in S:
            index = ord(s) - ord('a')
            prevlettercount = currentlettercount
            currentlettercount += widths[index]
            if currentlettercount == 100:
                numberoflines = numberoflines + 1
                currentlettercount = 0
            elif currentlettercount > 100:
                numberoflines = numberoflines + 1
                currentlettercount = currentlettercount - prevlettercount
        return numberoflines + 1, currentlettercount

    def numberOfLines1(self, widths, S):
        width_dict = {letter:widths[i] for i, letter in enumerate(string.ascii_lowercase)}
        row_count, space_count = 1, 0

        for s in S:
            if width_dict[s] + space_count > 100:
                row_count = row_count + 1
                space_count = width_dict[s]
            else:
                space_count += width_dict[s]
        return [row_count, space_count]



widths = [10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10]
S = "abcdefghijklmnopqrstuvwxyz"
solution = Solution()
numberoflines, lettercount =solution.numberOfLines1(widths, S)
print(numberoflines)
print(lettercount)