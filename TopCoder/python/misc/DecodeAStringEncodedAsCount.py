
def decode(str):
    stack = []
    for c in str:
        if ( c == ']'):
            t = stack.pop()
            list = []
            while t != "[":
                list.append(t)
                t = stack.pop()
            list.reverse()
            str1 = ''.join(list)
            count = int(stack.pop())
            stack.append(count*str1)
        else :
            stack.append(c)

    print stack.pop()


decode("3[b2[ca]]")
#print ('3' == ']')