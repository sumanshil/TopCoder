from greeting_decorator import greeting

@greeting
def f(x):
    return x + 4

f(10)
print("function name: " + f.__name__)
#print("docstring: " + f.__doc__)
print("module name: " + f.__module__)