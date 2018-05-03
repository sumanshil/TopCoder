def plusOne(digits):

    s = ""
    for e in digits:
        s += str(e)

    s = str(int(s)+1)
    ret = []
    for i in range(0,len(s)):
        ret.append(int(s[i]))

    return ret

digits = [1,2,3]
result = plusOne(digits)
print (result)