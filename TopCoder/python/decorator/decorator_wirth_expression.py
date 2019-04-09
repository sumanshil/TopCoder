def greeting(expr):
    def greeting_decorator(func):
        def function_wrapper(x):
            print(expr +", "+ func.__name__ + " returns")
            func(x)
        return function_wrapper
    return greeting_decorator

#@greeting("καλημερα")
def foo(x):
    print(42)

greeting2 = greeting("καλημερα")
foo = greeting2(foo)
foo("Hi")