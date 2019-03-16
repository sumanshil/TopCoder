class Solution:
    def isValid(self, s: str) -> bool:
        stack = []

        for ss in s:
            if ss is '}':
                if len(stack) > 0 and stack[len(stack) - 1] != '{':
                    return False
                elif len(stack) == 0:
                    return False
                else:
                    stack.pop()
            elif ss is ']':
                if len(stack) > 0 and stack[len(stack) - 1] != '[':
                    return False
                elif len(stack) == 0:
                    return False
                else:
                    stack.pop()
            elif ss is ')':
                if len(stack) > 0 and stack[len(stack) - 1] != '(':
                    return False
                elif len(stack) == 0:
                    return False
                else:
                    stack.pop()
            else:
                stack.append(ss)
        return len(stack) == 0

if __name__ == '__main__':
    sol = Solution()
    res = sol.isValid('[[')
    print(res)


