def evening_greeting(func):
    def function_wrapper(x):
        print("Good evening, " +func.__name__+ " returns:")
        func(x)
    return function_wrapper

def morning_greeting(func):
    def function_wrapper(x):
        print("Good morning, " +func.__name__+ " returns:")
        func(x)
    return function_wrapper

@evening_greeting
def foo(x):
    print(42)

foo("Hi")


