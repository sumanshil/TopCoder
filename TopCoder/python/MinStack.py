from typing import List

class MinStack:

    def __init__(self):
        self.stack: List[tuple] = []

    def push(self, x: int) -> None:
        minVal = x if len(self.stack) == 0 else self.stack[len(self.stack) - 1][1]
        self.stack.append((x, min(x, minVal)))

    def pop(self) -> None:
        self.stack.pop()

    def top(self) -> int:
        return self.stack[len(self.stack)-1][0]

    def getMin(self) -> int:
        return self.stack[len(self.stack)-1][1]


if __name__ == "__main__":
    minStack = MinStack()
    minStack.push(-2)
    minStack.push(0)
    minStack.push(-3)
    print(minStack.getMin())
    minStack.pop()
    print(minStack.top())
    print(minStack.getMin())