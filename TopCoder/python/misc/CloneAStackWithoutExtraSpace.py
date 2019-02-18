

class Stack:
    def __init__(self):
        self.arr = []

    def push(self, value):
        self.arr.append(value)

    def pop(self):
        return self.arr.pop()

    def top(self):
        return self.arr[len(self.arr)-1]

    def print(self):
        index = len(self.arr)-1
        while index >= 0:
            print(str(self.arr[index]))
            index = index - 1

    def size(self):
        return len(self.arr)

    def clone(self, newStack):
        counter_src_stack = 1
        elements_to_retrive = self.size() - counter_src_stack
        while elements_to_retrive >= 0:
            """ retrive elements from src and push it to dest """
            retrieve_counter = elements_to_retrive
            while retrieve_counter > 0:
                newStack.push(self.pop())
                retrieve_counter=retrieve_counter-1
            src_stack_top_val = self.top()

            retrieve_counter = elements_to_retrive

            while retrieve_counter > 0:
                self.push(newStack.pop())
                retrieve_counter = retrieve_counter - 1

            newStack.push(src_stack_top_val)

            elements_to_retrive = elements_to_retrive - 1

            print('source stack')
            self.print()

            print('destinaton stack')
            newStack.print()

stack = Stack()
stack.push(1)
stack.push(2)
stack.push(3)

stack.print()

newStack = Stack()

stack.clone(newStack)

print('New stack after clone')

newStack.print()