class Solution:
    def calPoints(self, ops):
        list = []
        result = 0
        for op in ops:
            if op == '+':
                value = 0
                if len(list) > 0:
                    value = value + list[len(list) - 1]

                if len(list) > 1:
                    value = value + list[len(list) - 2]

                result = result + value
                list.append(value)
            elif op == 'D':
                value = 0
                if len(list) > 0:
                    value = list[len(list)-1]*2
                result = result + value
                list.append(value)
            elif op == 'C':
                if len(list) > 0:
                    value = list.pop()
                    result = result - value

            else:
                result = result + int(op)
                list.append(int(op))
        return result

ops = ["5","-2","4","C","D","9","+","+"]
sol = Solution()
res = sol.calPoints(ops)
print(res)