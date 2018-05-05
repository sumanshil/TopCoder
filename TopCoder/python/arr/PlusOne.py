def plusOne(digits):

    s = ""
    for e in digits:
        s += str(e)

    s = str(int(s)+1)
    ret = []
    for i in range(0,len(s)):
        ret.append(int(s[i]))

    return ret

def plusOne1(digits):

    return [int(i) for i in str(int(''.join(map(str,digits)))+1)]

digits = [1,2,3]
result = plusOne1(digits)
print (result)