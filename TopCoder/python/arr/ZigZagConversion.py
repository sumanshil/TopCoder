#https://leetcode.com/problems/zigzag-conversion/description/

def zigzagprint (s, numRows):
    if len(s) <= numRows:
        return s
    startIndex = 0
    originalGap = 2 * numRows - 2
    if originalGap == 0:
        return s
    nextEndIndex = startIndex + originalGap
    retVal = ""
    for i in range(0, numRows):
        j = startIndex
        if i == 0 or i == numRows - 1:
            while j < len(s):
                retVal += s[j]
                j = j + originalGap
        else:
            nextEvenGap = (nextEndIndex - startIndex)
            nextOddGap = (startIndex + originalGap) - nextEndIndex
            #retVal += str[j]
            iteration = 0
            while j < len(s):
                retVal += s[j]
                if iteration % 2 == 0 :
                    j = j + nextEvenGap
                else:
                    j = j + nextOddGap
                iteration = iteration +1

        startIndex=startIndex+1
        nextEndIndex=nextEndIndex-1

    return retVal


str1 = "PAYPALISHIRING"
row = 4
retVal = zigzagprint(str1, row)
print(retVal)