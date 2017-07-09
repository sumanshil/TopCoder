#http://www.geeksforgeeks.org/sort-stack-using-temporary-stack/

def sort(stack):
    tempStack = []
    while len(stack) > 0:
        value = stack.pop()
        insertInTempStack(stack, value, tempStack)

    while len(tempStack) > 0:
        value = tempStack.pop()
        print value

def insertInTempStack(originalStack, value, tempStack):
    count = 0
    if len(tempStack) == 0:
        tempStack.append(value)
        return

    while 1 > 0:
        lenStack = len(tempStack)
        if lenStack == 0:
            break
        if tempStack[lenStack-1] < value:
            originalStack.append(tempStack[lenStack-1])
            tempStack.remove(tempStack[lenStack-1])
            count = count + 1
        else:
            break
    tempStack.append(value)

    while count > 0:
        tempStack.append(originalStack.pop())
        count = count - 1

stack = [34, 3, 31, 98, 92, 23]
sort(stack)