
def conversion(str, rowNum):
    arr = [""] * rowNum
    lastSeenIndex = 0
    arrIndex = 0;

    for e in str:
        arr[arrIndex] += e
        if (arrIndex == 0):
            lastSeenIndex = 0
        elif arrIndex == rowNum-1:
            lastSeenIndex = rowNum - 1

        if lastSeenIndex == 0:
            arrIndex = arrIndex + 1
        elif lastSeenIndex == rowNum -1:
            arrIndex = arrIndex - 1


    retval = ""
    for s in arr:
        retval += s
    return  retval

str = "PAYPALISHIRING"
result = conversion(str, 4)
print(result)