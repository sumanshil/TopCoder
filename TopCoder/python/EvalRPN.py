from typing import List

class Solution:
    def evalRPN(self, tokens: List[str]) -> int:
        stack = []
        operators = {"*", "+", "-", "/"}
        for token in tokens:
            if token in operators:
                op2 = stack.pop()
                op1 = stack.pop()
                result = self.execute(token, op1, op2)
                stack.append(result)
            else:
                stack.append(token)
        return int(stack.pop())

    def execute(self, token, op1, op2) -> str:
        if token == "*":
            return str(int(op1) * int(op2))
        elif token == "+":
            return str(int(op1) + int(op2))
        elif token == "-":
            return str(int(op1) - int(op2))
        elif token == "/":
            return str(int(op1) / int(op2))


if __name__ == "__main__":
    tokens = ["2", "1", "+", "3", "*"]
    sol = Solution()
    result = sol.evalRPN(tokens)
    print(result)